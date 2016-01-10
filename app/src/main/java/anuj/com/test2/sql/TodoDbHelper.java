package anuj.com.test2.sql;

import com.activeandroid.query.Select;
import java.util.List;
import anuj.com.test2.MainActivity;

/**
* Created by anujacharya on 1/10/16.
*/
public class TodoDbHelper{
    private static final String TAG = MainActivity.class.getSimpleName();

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
        item.value = value;
        item.save();
        return item;
    }

    public Todo saveToTable(String value){

        Todo todo = new Todo();
        todo.value = value;
        todo.save();
        return todo;
    }
}
