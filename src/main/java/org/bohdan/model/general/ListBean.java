package org.bohdan.model.general;

import org.bohdan.model.TypeTour;
import org.springframework.stereotype.Component;

/**
 * General class for displaying tables:
 * Country and TypeTour
 *
 * @author Bohdan Daniel
 */

public class ListBean {

    private Integer id;

    private String name;

    public static ListBean create(Integer id, String name) {
        ListBean listBean = new ListBean();
        listBean.setId(id);
        listBean.setName(name);
        return listBean;
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
