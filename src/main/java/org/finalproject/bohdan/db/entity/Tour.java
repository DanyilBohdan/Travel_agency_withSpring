package org.finalproject.bohdan.db.entity;

import java.util.Date;

public class Tour {

    private Integer id;

    private String name;

    private String country;

    private float price;

    private int count_people;

    private String description;

    private int mark_hotel;

    private Date start_date;

    private int days;

    private float discount;

    private int type_tour_id;

    public static Tour createTour(String name, String country, float price,
                                  int count_people, int mark_hotel,
                                  Date start_date, int days, String description,
                                  int type_tour_id, float discount) {
        Tour tour = new Tour();
        tour.setName(name);
        tour.setCountry(country);
        tour.setPrice(price);
        tour.setCount_people(count_people);
        tour.setDescription(description);
        tour.setMark_hotel(mark_hotel);
        tour.setStart_date(start_date);
        tour.setDays(days);
        tour.setDiscount(discount);
        tour.setType_tour_id(type_tour_id);
        return tour;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", count_people=" + count_people +
                ", description='" + description + '\'' +
                ", mark_hotel='" + mark_hotel + '\'' +
                ", start_date='" + start_date + '\'' +
                ", days=" + days +
                ", discount=" + discount +
                ", type_tour_id=" + type_tour_id +
                '}';
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount_people() {
        return count_people;
    }

    public void setCount_people(int count_people) {
        this.count_people = count_people;
    }

    public int getMark_hotel() {
        return mark_hotel;
    }

    public void setMark_hotel(int mark_hotel) {
        this.mark_hotel = mark_hotel;
    }

    public int getType_tour_id() {
        return type_tour_id;
    }

    public void setType_tour_id(int type_tour_id) {
        this.type_tour_id = type_tour_id;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
