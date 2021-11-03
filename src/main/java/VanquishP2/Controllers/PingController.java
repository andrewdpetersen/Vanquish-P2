package VanquishP2.Controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "PingServlet", value = "/ping")
public class PingController {
    JSONObject jObj = new JSONObject();

    @CrossOrigin
    public String pong(){
        try {
            jObj.put("Response", "pong!");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jObj.toString();
    }
}
