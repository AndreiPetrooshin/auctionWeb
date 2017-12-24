package com.petrushin.service.validator;

import java.util.regex.Pattern;

public class ValidatorUtil {

    public static boolean validateEmail(String email) {
        return Pattern.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", email);
    }

    public static boolean validateLogin(String login) {
        return login != null && Pattern.matches("[a-zA-Z][a-zA-Z0-9]{5,30}", login);
    }

    public static boolean validatePassword(String password) {
        return password != null && Pattern.matches("[a-zA-Z0-9]{5,30}", password);
    }
}
