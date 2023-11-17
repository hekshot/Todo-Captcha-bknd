package sanmithra.com.TodoList.Entity;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Users {
    @GeneratedValue
    @Id
    private int userId;
    private String userName;
    @Column(unique = true)
    private String userPassword;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TodoList> todoList = new ArrayList<>();
    public Users(int userId, String userName, String userPassword, List<TodoList> todoList) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.todoList = todoList;
    }

    public Users() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<TodoList> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoList> todoList) {
        this.todoList = todoList;
    }

}
