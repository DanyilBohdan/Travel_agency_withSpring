package org.bohdan.db;

import org.bohdan.db.DAO.TourDao;

public class DEMO {

    public static void main(String[] args) {
        System.out.println(new TourDao().findEntityById(1).getName());
    }
}
