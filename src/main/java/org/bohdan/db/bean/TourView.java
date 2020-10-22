package org.bohdan.db.bean;

import java.util.Date;

/**
 * Bean for displaying only one locale
 *
 * @author Bohdan Daniel
 */
public class TourView {

    private Integer id;

    private String name;

    private String type;

    private String country;

    private float price;

    private String description;

    private int count_people;

    private int mark_hotel;

    private Date start_date;

    private int days;

    private float discount;

    public static TourView createTour(String name, String type, String country, String desc,
                                      float price, int count_people, int mark_hotel, Date start_date,
                                      int days, float discount) {
        TourView tourView = new TourView();
        tourView.setName(name);
        tourView.setType(type);
        tourView.setCountry(country);
        tourView.setDescription(desc);
        tourView.setPrice(price);
        tourView.setCount_people(count_people);
        tourView.setMark_hotel(mark_hotel);
        tourView.setStart_date(start_date);
        tourView.setDays(days);
        tourView.setDiscount(discount);
        return tourView;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "TourView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", count_people=" + count_people +
                ", mark_hotel=" + mark_hotel +
                ", start_date=" + start_date +
                ", days=" + days +
                ", discount=" + discount +
                '}';
    }
}
