package sanmithra.com.TodoList.Services;

import org.springframework.stereotype.Service;

@Service
public class TodoListService {
    private String tasks;


    public TodoListService() {
    }

    public TodoListService(String tasks) {
        this.tasks = tasks;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String content) {
        this.tasks = tasks;
    }
}
