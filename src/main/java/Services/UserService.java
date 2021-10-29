package Services;

import Models.User;
import Models.UserInfo;
import org.hibernate.query.Query;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Util.CredentialValidator.*;

@Service
public class UserService {
    // Variables
    boolean success;

    // Hibernate Variable
    Query<User> query;
    Transaction tx;

    // Get All
    public ArrayList<User> allUsers(){
        ArrayList<User> users = new ArrayList<>();

        // Query for all users in database

        return users;
    }

    // Register
    public boolean registerUser(JSONObject registrationData){
        try {
            String firstName, lastName, username, password, email;
            firstName = registrationData.getString("firstName");
            lastName = registrationData.getString("lastName");
            username = registrationData.getString("username");
            password = registrationData.getString("password");
            email = registrationData.getString("email");

            if (emailIsValid(email) && usernameIsValid(username) && passwordIsValid(password)) {
                User user = new User(firstName, lastName);
                UserInfo userInfo = new UserInfo(username, password, email);

                user.setUserInfo(userInfo);

                /*tx = getSession.startTransaction();
                getSession.save(user);*/

                tx.commit();
            } else {
                success = false;
            }
        } catch (Exception e) {
            // if tx != null
            // tx.rollback();
            success = false;
            e.printStackTrace();
        }

        return success;
    }
}
