package Controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "HelloServlet", value = "/hello")
public class HelloController {
    JSONObject jObj = new JSONObject();

    @CrossOrigin
    public String helloWorld(){
        try {
            jObj.put("Response", "Hello World!");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jObj.toString();
    }
}