package org.bohdan.db;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DEMO {

    public static void main(String[] args) {

        Date d = new Date();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(d.getTime()));
    }
}
