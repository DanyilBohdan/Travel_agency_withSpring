package org.finalproject.bohdan.db;

public class DEMO {

    public static void main(String[] args) {

        System.out.println(new TypeTourDao().findAllTypeTour());
        System.out.println(new UserDao().findAllUser());
        System.out.println(new TourDao().findAllTour());
    }
}
