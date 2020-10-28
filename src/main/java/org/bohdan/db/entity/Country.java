package org.bohdan.db.entity;

/**
 * Country entity.
 *
 * @author Bohdan Daniel
 *
 */
public class Country {

    private Integer id;

    private String nameEn;

    private String nameRu;

    public static Country create(String name_en, String name_ru) {
        Country country = new Country();
        country.setNameEn(name_en);
        country.setNameRu(name_ru);
        return country;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name_en='" + nameEn + '\'' +
                ", name_ru='" + nameRu + '\'' +
                '}';
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
}
