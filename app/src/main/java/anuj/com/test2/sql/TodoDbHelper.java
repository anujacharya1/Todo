package anuj.com.test2.sql;

import com.activeandroid.query.Select;

import java.util.List;

/**
* Created by anujacharya on 1/10/16.
*/
public class TodoDbHelper{
    private static final String TAG = TodoDbHelper.class.getSimpleName();

    public List<Todo> getAll() {
        List<Todo> todoList =  new Select()
                .from(Todo.class)
                .execute();

        return todoList;
    }

    public void deleteRecord(Todo todo){
        Todo item = Todo.load(Todo.class, todo.getId());
        item.delete();
    }

    public Todo updateTodo(String value, int id){
        Todo item = Todo.load(Todo.class, id);
        item.setValue(value);
        item.save();
        return item;
    }

    public Todo saveToTable(String name, String priority, String date){

        Todo todo = new Todo();
        todo.setValue(name);
        todo.setPriority(priority);
        todo.setDate(date);
        todo.save();
        return todo;
    }
}
