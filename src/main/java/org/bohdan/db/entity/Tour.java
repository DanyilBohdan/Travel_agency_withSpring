package org.bohdan.db.entity;

import java.util.Date;

/**
 * Tour entity.
 *
 * @author Bohdan Daniel
 *
 */
public class Tour {

    private Integer id;

    private String nameEn;

    private String nameRu;
    
    private String descEn;

    private String descRu;

    private float price;

    private int countPeople;

    private int markHotel;

    private Date startDate;

    private int days;

    private float discount;

    private int typeTourId;

    private int countryId;

    public static Tour createTour(String nameEn, String nameRu, String descEn, String descRu,
                                  float price, int countPeople, int markHotel, Date startDate,
                                  int days, float discount, int typeTourId, int countryId) {
        Tour tour = new Tour();
        tour.setNameEn(nameEn);
        tour.setNameRu(nameRu);
        tour.setDescEn(descEn);
        tour.setDescRu(descRu);
        tour.setPrice(price);
        tour.setCountPeople(countPeople);
        tour.setMarkHotel(markHotel);
        tour.setStartDate(startDate);
        tour.setDays(days);
        tour.setDiscount(discount);
        tour.setTypeTourId(typeTourId);
        tour.setCountryId(countryId);
        return tour;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameRu='" + nameRu + '\'' +
                ", descEn='" + descEn + '\'' +
                ", descRu='" + descRu + '\'' +
                ", price=" + price +
                ", countPeople=" + countPeople +
                ", markHotel=" + markHotel +
                ", startDate=" + startDate +
                ", days=" + days +
                ", discount=" + discount +
                ", typeTourId=" + typeTourId +
                ", countryId=" + countryId +
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

    public String getDescEn() {
        return descEn;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public String getDescRu() {
        return descRu;
    }

    public void setDescRu(String descRu) {
        this.descRu = descRu;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public int getTypeTourId() {
        return typeTourId;
    }

    public void setTypeTourId(int typeTourId) {
        this.typeTourId = typeTourId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
