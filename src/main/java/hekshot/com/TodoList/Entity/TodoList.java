package hekshot.com.TodoList.Entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class TodoList {
    @Getter
    @Id
    @GeneratedValue
    private int Id;
    @Getter
    private String tasks;
    private boolean completed=Boolean.FALSE;

    public TodoList(int id, String tasks, boolean completed) {
        Id = id;
        this.tasks = tasks;
        this.completed = completed;
    }

    public TodoList() {

    }

    public void setId(int id) {
        Id = id;
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
