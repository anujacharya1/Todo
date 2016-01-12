package anuj.com.test2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by anujacharya on 1/12/16.
 */
public class AddItemActivity extends ActionBarActivity implements DatePickerDialog.OnDateSetListener{

    EditText as_name;
    Switch as_switch;
    String date;

    private String priority = "LOW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        as_name = (EditText) findViewById(R.id.as_name);
        as_name.setText("");

        // by default current date
        Date now = new Date();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        date = sdfDate.format(now);
        ((TextView) findViewById(R.id.dateText)).setText(date);


        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");


        //priority
        as_switch = (Switch) findViewById(R.id.as_switch);
        //set the switch to ON
        as_switch.setChecked(false);
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


    }
//    public void showTextNotification(String msgToDisplay) {
//        Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
//    }

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
    public void onSubmitAddItem(View view) {
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("priority", priority);
        data.putExtra("name", as_name.getText().toString());
        data.putExtra("date", date);

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
