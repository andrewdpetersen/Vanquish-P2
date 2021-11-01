package Util;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CredentialValidator {
    // Registration Regex, Patterns, and Matcher
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9]\\w{5,20}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static Matcher matcher;

    // Validation Methods
    public static boolean emailIsValid (String email) {
        matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean usernameIsValid(String username) {
        matcher = USERNAME_PATTERN.matcher(username);
        return matcher.matches();
    }

    public static boolean passwordIsValid(String password) {
        matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}
