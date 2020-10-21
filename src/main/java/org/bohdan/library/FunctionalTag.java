package org.bohdan.library;

import java.util.Locale;
import java.util.ResourceBundle;

public class FunctionalTag {
    FunctionalTag() {}

    public static String getResultRegistration(String result, String lang){
        if (result.equals("Successful registration")){
            return ResourceBundle.getBundle("resources", new Locale(lang)).getString("registrationTour.successful");
        }
            return ResourceBundle.getBundle("resources", new Locale(lang)).getString("registrationTour.unsuccessful");
    }
}
