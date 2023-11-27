package hekshot.com.TodoList.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@Data
public class Users {
    @GeneratedValue
    @Id
    private int userId;

    @Column(unique = true)
    private String userName;

    private String userPassword;

    @Transient
    private String captcha;

    @Transient
    private String hiddenCaptcha;

    @Transient
    private String realCaptcha;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TodoList> todoList = new ArrayList<>();
    public Users(int userId, String userName, String userPassword, List<TodoList> todoList) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.todoList = todoList;
    }

    public Users(int userId, String userName, String userPassword, String captcha, String hiddenCaptcha, String realCaptcha, List<TodoList> todoList) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.captcha = captcha;
        this.hiddenCaptcha = hiddenCaptcha;
        this.realCaptcha = realCaptcha;
        this.todoList = todoList;
    }

    public Users() {

    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setTodoList(List<TodoList> todoList) {
        this.todoList = todoList;
    }

}
