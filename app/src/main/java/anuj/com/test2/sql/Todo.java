package anuj.com.test2.sql;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
* Created by anujacharya on 1/10/16.
*/

@Table(name = "Todo")
public class Todo extends Model {
    @Column(name = "value")
    public String value;

    public Todo(){
        super();
    }

    @Override
    public String toString() {
        return value;
    }
}