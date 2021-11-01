package DataGenerators;

import java.util.Random;

import static DataGenerators.DataLists.*;

public class UserGen {
    private static Random rand = new Random();
    private static int index;

    public static String getAFirstName() {
        index = rand.nextInt(names.length);
        return names[index];
    }

    public static String getALastName() {
        index = rand.nextInt(names.length);
        return names[index];
    }

    public static String getAPassword() {
        index = rand.nextInt(passwords.length);
        return passwords[index];
    }

    public static String getAUsername() {
        index = rand.nextInt(usernames.length);
        return usernames[index];
    }

    public static String getAEmail() {
        index = rand.nextInt(emails.length);
        return emails[index];
    }
}
