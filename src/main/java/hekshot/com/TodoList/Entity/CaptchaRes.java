package hekshot.com.TodoList.Entity;

public class CaptchaRes {
    private String imageUrl;

    public CaptchaRes(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
