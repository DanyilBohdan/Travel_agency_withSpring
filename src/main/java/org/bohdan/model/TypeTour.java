package org.bohdan.model;

import org.springframework.stereotype.Component;

/**
 * TypeTour entity.
 *
 * @author Bohdan Daniel
 *
 */

public class TypeTour {
    
    private Integer id;
    
    private String nameEn;

    private String nameRu;

    public static TypeTour create(String nameEn, String nameRu) {
        TypeTour typeTour = new TypeTour();
        typeTour.setNameEn(nameEn);
        typeTour.setNameRu(nameRu);
        return typeTour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @Override
    public String toString() {
        return "TypeTour{" +
                "id=" + id +
                ", name_en='" + nameEn + '\'' +
                ", name_ru='" + nameRu + '\'' +
                '}';
    }
}
