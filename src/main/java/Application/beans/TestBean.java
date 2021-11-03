package Application.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestBean {
    private OtherTestBean otherBean;

    @Autowired
    public TestBean(OtherTestBean otherBean) {
        this.otherBean = otherBean;
    }

    public OtherTestBean getOtherBean() {
        return otherBean;
    }

    public void setOtherBean(OtherTestBean otherBean) {
        this.otherBean = otherBean;
    }
}