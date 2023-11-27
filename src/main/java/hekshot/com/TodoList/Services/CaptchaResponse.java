package hekshot.com.TodoList.Services;


import lombok.Getter;

public class CaptchaResponse {

    @Getter
    private boolean success;
    private String challenge_ts;
    private String hostname;

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
