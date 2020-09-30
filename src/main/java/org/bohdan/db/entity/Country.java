package org.bohdan.db.entity;

public class Country {

    private Integer id;

    private String name;

    public static Country createTypeTour(String name) {
        Country country = new Country();
        country.setName(name);
        return country;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
