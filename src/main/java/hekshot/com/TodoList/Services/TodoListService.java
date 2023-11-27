package hekshot.com.TodoList.Services;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class TodoListService {
    private String tasks;


    public TodoListService() {
    }

    public TodoListService(String tasks) {
        this.tasks = tasks;
    }

    public void setTasks(String content) {
        this.tasks = tasks;
    }
}
