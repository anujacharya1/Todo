package anuj.com.test2.sql;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
* Created by anujacharya on 1/10/16.
*/

@Table(name = "Todo")
public class Todo extends Model implements Serializable {

    @Column(name = "value")
    public String value;

    @Column(name = "date")
    public String date;

    @Column(name = "priority")
    public String priority;

    public Todo(){
        super();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getValue()+"\t\t"+getDate()+"\t\t"+getPriority();
    }
}