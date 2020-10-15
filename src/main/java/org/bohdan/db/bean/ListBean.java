package org.bohdan.db.bean;

import org.bohdan.db.entity.TypeTour;

public class ListBean {

    private Integer id;

    private String name;

    public static TypeTour create(String name) {
        TypeTour typeTour = new TypeTour();
        typeTour.setName_en(name);
        return typeTour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}