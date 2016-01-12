package anuj.com.test2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import anuj.com.test2.sql.Todo;
import anuj.com.test2.sql.TodoDbHelper;


public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Todo> toDoAdapter;
    private EditText et;
    private ListView lv;
    private final int REQUEST_CODE = 20;
    private final int REQUEST_CODE_ADD = 21;

    private TodoDbHelper todoDbHelper;
    private ArrayList<Todo> todoItems;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SQL
        todoDbHelper = new TodoDbHelper();
        readItemsFromSql();

        toDoAdapter = new TodoAdapter(this, todoItems);
        lv = (ListView)findViewById(R.id.todoLv);
        lv.setAdapter(toDoAdapter);

        //removing item in the list
        setUpListenerOnToDoList();

        //setup Edit redirect to another Intent
        setUpRedirectIntent();
    }

    private void setUpRedirectIntent(){

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("text", todoItems.get(position).toString());
                i.putExtra("id", todoItems.get(position).getId().intValue());
                i.putExtra("position", position);
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    private void readItemsFromSql(){
        todoItems = (ArrayList<Todo>) todoDbHelper.getAll();
    }

    private void setUpListenerOnToDoList(){
        lv.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Todo todo = todoItems.remove(position);
                        toDoAdapter.notifyDataSetChanged();
                        // delete from sql
                        todoDbHelper.deleteRecord(todo);
                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // button click intent
    public void onSubmit(View view) {

        // redirect to the page and call the db helper and return the object from that intent
        Intent launchAddItemActivity= new Intent(MainActivity.this,AddItemActivity.class);
        startActivityForResult(launchAddItemActivity, REQUEST_CODE_ADD);

//        et = (EditText) findViewById(R.id.editText);
//        String textEntered = et.getText().toString();
//        Todo todo = todoDbHelper.saveToTable(textEntered);
//        todoItems.add(todo);
//        et.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String newText = data.getExtras().getString("newText");
            int position = data.getExtras().getInt("position", 0);
            int id =  data.getExtras().getInt("id", 0);
            Todo todo = todoDbHelper.updateTodo(newText, id);
            todoItems.set(position, todo);
            toDoAdapter.notifyDataSetChanged();
        }


        /*
         data.putExtra("priority", priority);
        data.putExtra("name", as_name.getText().toString());
        data.putExtra("date", date);
         */
        // FOR CODE=21 adding the task
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_ADD) {
            // Extract name value from result extras
            String priority = data.getExtras().getString("priority");
            String name = data.getExtras().getString("name");
            String date = data.getExtras().getString("date");


            Todo todo = todoDbHelper.saveToTable(name, priority, date);
            todoItems.add(todo);
            toDoAdapter.notifyDataSetChanged();
        }
    }

}
