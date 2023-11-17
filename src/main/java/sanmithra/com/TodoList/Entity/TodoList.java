package sanmithra.com.TodoList.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class TodoList {
    @Id
    @GeneratedValue
    private int Id;
    private String tasks;
    private boolean completed=Boolean.FALSE;

    public TodoList(int id, String tasks, boolean completed) {
        Id = id;
        this.tasks = tasks;
        this.completed = completed;
    }

    public TodoList() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        if(completed==true){
            return true;
        }
        else {
            return false;
        }
    }
}
