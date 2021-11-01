package Services;

import Models.User;
import org.hibernate.query.Query;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import javax.transaction.Transaction;
import java.util.ArrayList;
import static Util.CredentialValidator.*;

/**
 *
 * @date 10/29/2021
 * @author
 */

@Service
public class UserService {
    // Variables
    private static boolean success;
    private static User user;

    // Hibernate Variable
    private static Query<User> query;
    private static Transaction tx;

    static {
        user = new User();
    }

    // Get All
    @Bean
    public static ArrayList<User> allUsers(){
        ArrayList<User> users = new ArrayList<>();

        // Query for all users in database

        return users;
    }

    @Bean
    public static User getByID(int ID){
        // User with this ID
        // Query<User> query = getSession().createQuery("from User where ID = :id", User.class);
        // user = query.getFirstResult();

        return user;
    }

    public static boolean save(@Autowired User user){
        try{
            // getSession.save(user);
            success = true;
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }

        return success;
    }

    // Register
    public static boolean registerUser(JSONObject registrationData){
        try {
            String firstName, lastName, username, password, email;
            firstName = registrationData.getString("firstName");
            lastName = registrationData.getString("lastName");
            username = registrationData.getString("username");
            password = registrationData.getString("password");
            email = registrationData.getString("email");

            if (emailIsValid(email) && usernameIsValid(username) && passwordIsValid(password)) {
                User user = new User();

                /*tx = getSession.startTransaction();
                getSession.save(user);*/

                tx.commit();
            } else {
                success = false;
            }
        } catch (Exception e) {
            // if tx != null {
            // tx.rollback();
            // }
            success = false;
            e.printStackTrace();
        }

        return success;
    }
}
