package hekshot.com.TodoList.Entity;

import lombok.Getter;

import javax.persistence.Transient;

@Getter
public class Login {
    private String userName;
    private String userPassword;

    @Transient
    private String captcha;

    @Transient
    private String hiddenCaptcha;

    @Transient
    private String realCaptcha;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
