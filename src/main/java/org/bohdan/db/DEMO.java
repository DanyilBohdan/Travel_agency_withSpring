package org.bohdan.db;

import org.bohdan.db.DAO.TourDao;

public class DEMO {

    public static void main(String[] args) {
        TourDao.setFilter(0);
        System.out.println(new TourDao().findAllByLocale("EN", 1, 5));
    }
}
