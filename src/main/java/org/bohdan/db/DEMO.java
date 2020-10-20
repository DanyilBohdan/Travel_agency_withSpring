package org.bohdan.db;

import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;

public class DEMO {

    public static void main(String[] args) {
        System.out.println(new CountryDao(DBManager.getInstance()).findAll());
    }
}
