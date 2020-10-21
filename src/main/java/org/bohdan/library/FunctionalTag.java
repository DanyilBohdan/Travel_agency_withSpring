package org.bohdan.library;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FunctionalTag {
    FunctionalTag() {}

    public static String getDate(){
        Date d = new Date();
        return new SimpleDateFormat("yyyy-MM-dd").format(d.getTime());
    }
}
