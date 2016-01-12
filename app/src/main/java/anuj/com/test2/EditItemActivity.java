package anuj.com.test2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import anuj.com.test2.sql.Todo;


public class EditItemActivity extends ActionBarActivity implements DatePickerDialog.OnDateSetListener{

    EditText et;
    private int position;
    private int id;

    private String date;
    private String priority;
    Switch as_switch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Todo todo = (Todo)getIntent().getExtras().getSerializable("todo");
        String text = todo.getValue();
        priority = todo.getPriority();
        date = todo.getDate();
        ((TextView) findViewById(R.id.dateText)).setText(date);


        DialogFragment newFragment = new DatePickerFragmentForEdit();
        newFragment.show(getSupportFragmentManager(), "datePicker");

        position = getIntent().getIntExtra("position", 0);
        id = getIntent().getIntExtra("id", 0);

        et = (EditText) findViewById(R.id.as_name);
        et.setText(text);
        et.setSelection(text.length());


        //priority
        as_switch = (Switch) findViewById(R.id.as_switch);


        if(priority.equals("LOW")){
            as_switch.setChecked(false);
        }
        else{
            as_switch.setChecked(true);
        }
        //attach a listener to check for changes in state
        as_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    priority = "HIGH";
                }
            }
        });

        //setting up the focus
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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

    // button click send the text back
    public void onSubmit(View view) {
        String newText = et.getText().toString();

        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("newText", newText);
        data.putExtra("position", position);
        data.putExtra("id", id);
        data.putExtra("newPriority", priority);
        data.putExtra("newDate", date);

        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");

        Date dateAndTime = calendar.getTime();
        date = dd.format(dateAndTime);
        //do some stuff for example write on log and update TextField on activity
        ((TextView) findViewById(R.id.dateText)).setText(date);
    }
}
