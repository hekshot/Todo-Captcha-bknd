package sanmithra.com.TodoList.Services;


public class CaptchaResponse {

    private boolean success;
    private String challenge_ts;
    private String hostname;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
