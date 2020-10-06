package org.bohdan.db.entity;

import java.util.Date;

public class Tour {

    private Integer id;

    private String name_en;

    private String name_ru;
    
    private String desc_en;

    private String desc_ru;

    private float price;

    private int count_people;

    private int mark_hotel;

    private Date start_date;

    private int days;

    private float discount;

    private int type_tour_id;

    private int country_id;

    public static Tour createTour(String name_en, String name_ru, String desc_en, String desc_ru,
                                  float price, int count_people, int mark_hotel, Date start_date,
                                  int days, float discount, int type_tour_id, int country_id) {
        Tour tour = new Tour();
        tour.setName_en(name_en);
        tour.setName_ru(name_ru);
        tour.setDesc_en(desc_en);
        tour.setDesc_ru(desc_ru);
        tour.setPrice(price);
        tour.setCount_people(count_people);
        tour.setMark_hotel(mark_hotel);
        tour.setStart_date(start_date);
        tour.setDays(days);
        tour.setDiscount(discount);
        tour.setType_tour_id(type_tour_id);
        tour.setCountry_id(country_id);
        return tour;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name_en='" + name_en + '\'' +
                ", name_ru='" + name_ru + '\'' +
                ", desc_en='" + desc_en + '\'' +
                ", desc_ru='" + desc_ru + '\'' +
                ", price=" + price +
                ", count_people=" + count_people +
                ", mark_hotel=" + mark_hotel +
                ", start_date=" + start_date +
                ", days=" + days +
                ", discount=" + discount +
                ", type_tour_id=" + type_tour_id +
                ", country_id=" + country_id +
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

    public String getDesc_en() {
        return desc_en;
    }

    public void setDesc_en(String desc_en) {
        this.desc_en = desc_en;
    }

    public String getDesc_ru() {
        return desc_ru;
    }

    public void setDesc_ru(String desc_ru) {
        this.desc_ru = desc_ru;
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

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getType_tour_id() {
        return type_tour_id;
    }

    public void setType_tour_id(int type_tour_id) {
        this.type_tour_id = type_tour_id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
