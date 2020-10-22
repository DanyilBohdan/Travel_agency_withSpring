package org.bohdan.db.entity;

/**
 * Country entity.
 *
 * @author Bohdan Daniel
 *
 */
public class Country {

    private Integer id;

    private String name_en;

    private String name_ru;

    public static Country create(String name_en, String name_ru) {
        Country country = new Country();
        country.setName_en(name_en);
        country.setName_ru(name_ru);
        return country;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name_en='" + name_en + '\'' +
                ", name_ru='" + name_ru + '\'' +
                '}';
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
}
