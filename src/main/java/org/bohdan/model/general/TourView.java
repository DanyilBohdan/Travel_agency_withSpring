package org.bohdan.model.general;

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

    private int countPeople;

    private int markHotel;

    private Date startDate;

    private int days;

    private float discount;

    public static TourView createTour(String name, String type, String country, String desc,
                                      float price, int countPeople, int markHotel, Date startDate,
                                      int days, float discount) {
        TourView tourView = new TourView();
        tourView.setName(name);
        tourView.setType(type);
        tourView.setCountry(country);
        tourView.setDescription(desc);
        tourView.setPrice(price);
        tourView.setCountPeople(countPeople);
        tourView.setMarkHotel(markHotel);
        tourView.setStartDate(startDate);
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

    public int getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(int countPeople) {
        this.countPeople = countPeople;
    }

    public int getMarkHotel() {
        return markHotel;
    }

    public void setMarkHotel(int markHotel) {
        this.markHotel = markHotel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
                ", countPeople=" + countPeople +
                ", markHotel=" + markHotel +
                ", startDate=" + startDate +
                ", days=" + days +
                ", discount=" + discount +
                '}';
    }
}
