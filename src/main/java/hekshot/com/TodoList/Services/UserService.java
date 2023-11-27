package sanmithra.com.TodoList.Services;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private String userName;
    private String userPassword;

    public UserService() {
    }

    public UserService(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
