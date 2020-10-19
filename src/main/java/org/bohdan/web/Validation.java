package org.bohdan.web;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Za-z0-9_.-]+@[a-z]+.[a-z]{2,5}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PHONE_REGEX =
            Pattern.compile("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");

    public static final Pattern VALID_VAR_REGEX =
            Pattern.compile("[A-Za-z0-9 ]+");

    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^[A-Za-z0-9]{4,20}$");

    public static final Pattern VALID_LATIN_REGEX =
            Pattern.compile("^[a-zA-Z ]{4,}$");

    public static final Pattern VALID_CYRILLIC_REGEX =
            Pattern.compile("^[а-яА-Я ]{4,}$");

    public static final Pattern VALID_FLOAT_REGEX =
            Pattern.compile("^\\d+(,\\d{3})*(\\.\\d{1,2})?$");

    public static final Pattern VALID_INT_REGEX =
            Pattern.compile("^\\d+$");

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

    public static boolean validateLatin(String latin) {
        Matcher matcher = VALID_LATIN_REGEX.matcher(latin);
        return matcher.find();
    }

    public static boolean validateCyrillic(String cyrillic) {
        Matcher matcher = VALID_CYRILLIC_REGEX.matcher(cyrillic);
        return matcher.find();
    }

    public static boolean validateFloat(float fl,  float rangeStart, float rangeFinish) {
        if (fl < rangeStart || fl > rangeFinish){
            return false;
        }
        Matcher matcher = VALID_FLOAT_REGEX.matcher(Float.toString(fl));
        return matcher.find();
    }

    public static boolean validateInt(int count, int rangeStart, int rangeFinish) {
        if (count < rangeStart || count > rangeFinish){
            return false;
        }
        Matcher matcher = VALID_INT_REGEX.matcher(String.valueOf(count));
        return matcher.find();
    }

    public static boolean validateDate(Date date) {
        Date curDate = new Date();
        return date.compareTo(curDate) > 0;
    }

    public static String validateTour(String nameEN, String nameRU, String typeEN, String typeRU,
                                       String countryEN, String countryRU, String descEN, String descRU,
                                       float price, int count_people, int mark_hotel, Date start_date,
                                       int days, float discount){

        String check = "null";
        if(!validateLatin(nameEN)){
            check = "Name (EN) must be: only latin";
        }
        if(!validateCyrillic(nameRU)){
            check = "Name (RU) must be: only cyrillic";
        }
        if(!validateLatin(typeEN)){
            check = "Type (EN) must be: only latin";
        }
        if(!validateCyrillic(typeRU)){
            check = "Type (RU) must be: only cyrillic";
        }
        if(!validateLatin(countryEN)){
            check = "Country (EN) must be: only latin";
        }
        if(!validateCyrillic(countryRU)){
            check = "Country (RU) must be: only cyrillic";
        }
        if(!validateLatin(descEN)){
            check = "Description (EN) must be: only latin";
        }
        if(!validateCyrillic(descRU)){
            check = "Description (RU) must be: only cyrillic";
        }
        if(!validateFloat(price, 30, 10000)){
            check = "Price must be: xxx.xx";
        }
        if(!validateInt(count_people, 1, 20)){
            check = "Count people must be: only numbers from 1 - 20";
        }
        if(!validateInt(mark_hotel, 1, 5)){
            check = "Mark hotel must be: only numbers from 1 - 5";
        }
        if(!validateInt(days, 1, 20)){
            check = "Days must be: only numbers from 1 - 20";
        }
        if(!validateFloat(discount, 0, 1)){
            check = "Discount must be: only numbers from 0 - 1";
        }
        if(!validateDate(start_date)){
            check = "Date must be: only future dates";
        }
        return check;
    }
}
