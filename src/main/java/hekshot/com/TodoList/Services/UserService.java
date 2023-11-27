package hekshot.com.TodoList.Services;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@Data
public class UserService {
    private String userName;
    private String userPassword;

    public UserService() {
    }

    public UserService(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

}
