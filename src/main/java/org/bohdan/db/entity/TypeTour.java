package org.bohdan.db.entity;

public class TypeTour {
    
    private Integer id;
    
    private String name_en;

    private String name_ru;

    public static TypeTour create(String name_en, String name_ru) {
        TypeTour typeTour = new TypeTour();
        typeTour.setName_en(name_en);
        typeTour.setName_ru(name_ru);
        return typeTour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_ru() {
        return name_ru;
    }

    public void setName_ru(String name_ru) {
        this.name_ru = name_ru;
    }

    @Override
    public String toString() {
        return "TypeTour{" +
                "id=" + id +
                ", name_en='" + name_en + '\'' +
                ", name_ru='" + name_ru + '\'' +
                '}';
    }
}
