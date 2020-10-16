package org.bohdan.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Za-z0-9_.-]+@[a-z]+.[a-z]{2,5}$", Pattern.CASE_INSENSITIVE);

    public static final  Pattern VALID_PHONE_REGEX = 
            Pattern.compile("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");

    public static final  Pattern VALID_VAR_REGEX =
            Pattern.compile("[A-Za-z0-9]+");

    public static final  Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^[A-Za-z0-9]{4,20}$");

    public static boolean validateAllVar(String vars) {
        Matcher matcher = VALID_VAR_REGEX.matcher(vars);
        return matcher.find();
    }

    public static boolean validateLogin(String login) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(login);
        return matcher.find();
    }

    public static boolean validatePhone(String phone) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
        return matcher.find();
    }

    public static boolean validatePassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }
}
