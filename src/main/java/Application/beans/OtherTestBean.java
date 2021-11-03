package Application.beans;

import org.springframework.stereotype.Component;

@Component
public class OtherTestBean {
    private String test;

    public OtherTestBean() {
        test = "test message.";
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}