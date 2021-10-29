package DataGenerators;

import java.util.Random;

import static DataGenerators.DataLists.*;

public class UserGen {
    private static Random rand = new Random();

    public static String getAFirstName() {
        int index = rand.nextInt(names.length);
        return names[index];
    }

    public static String getALastName() {
        int index = rand.nextInt(names.length);
        return names[index];
    }

    public static String getAPassword() {
        int index = rand.nextInt(passwords.length);
        return passwords[index];
    }

    public static String getAUsername() {
        int index = rand.nextInt(usernames.length);
        return usernames[index];
    }

    public static String getAEmail() {
        int index = rand.nextInt(emails.length);
        return emails[index];
    }
}
