package sanmithra.com.TodoList.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CaptchaValidator {
    @Autowired
    private RestTemplate rt;
    public  Boolean isValid(String captcha){
        String url="https://www.google.com/recaptcha/api/siteverify";
        String params="?secret=6Lc3xNIoAAAAAANlgT8i0RjlWHk_7-UNGaV5-zdS&response="+captcha;

        CaptchaResponse cr=rt.postForObject(url+params,null, CaptchaResponse.class);
            return cr.isSuccess();
    }
}
