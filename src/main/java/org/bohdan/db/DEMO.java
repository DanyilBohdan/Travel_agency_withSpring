package org.bohdan.db;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.db.entity.Role;

public class DEMO {

    private static final Logger logger = Logger.getLogger(DEMO.class);

    public static void main(String[] args) {
        String role = "manager".toUpperCase();
        System.out.println(Role.valueOf(role).ordinal() + 1);
    }
}