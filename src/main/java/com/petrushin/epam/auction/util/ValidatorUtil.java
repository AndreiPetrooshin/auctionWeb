package com.petrushin.epam.auction.util;

import java.util.regex.Pattern;

/**
 * Util class witch helps validate parameters.
 *
 * @author Andrei Petruhin
 * @version 1.0.0
 */
public final class ValidatorUtil {

    public static boolean validateEmail(String email) {
        return Pattern.matches("^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\\.)+[a-z]{2,6}$", email);
    }

    public static boolean validateLogin(String login) {
        return login != null && Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}", login);
    }

    public static boolean validatePassword(String password) {
        return password != null && Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{6,}", password);
    }
}
