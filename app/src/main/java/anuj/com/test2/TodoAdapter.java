package anuj.com.test2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import anuj.com.test2.sql.Todo;

/**
 * Created by anujacharya on 1/12/16.
 */
public class TodoAdapter extends ArrayAdapter<Todo> {


    public TodoAdapter(Context context, ArrayList<Todo> todos) {
        super(context, 0, todos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Todo todo = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_single, parent, false);
        }
        // Lookup view for data population
        TextView main_name = (TextView) convertView.findViewById(R.id.main_name);
        TextView main_date = (TextView) convertView.findViewById(R.id.main_date);
        TextView main_priority = (TextView) convertView.findViewById(R.id.main_priority);

        // Populate the data into the template view using the data object
        main_name.setText(todo.getValue());
        main_date.setText(todo.getDate());


        main_priority.setText(todo.getPriority());

        if(todo.getPriority().equals("LOW")){
            main_priority.setTextColor(Color.BLUE);
        }
        else{
            main_priority.setTextColor(Color.RED);
        }

        // Return the completed view to render on screen
        return convertView;
    }

}
