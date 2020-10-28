package org.bohdan.db.bean;

import java.util.Date;

/**
 * Locale class for table Order
 *
 * @author Bohdan Daniel
 */
public class OrderTours {

    private Integer orderId;

    private Integer tourId;

    private String name;

    private String type;

    private String country;

    private float price;

    private int countPeople;

    private int markHotel;

    private Date startDate;

    private int days;

    private float discount;

    private String status;

    private Date dateReg;

    private String login;

    public static OrderTours createTour(Integer orderId, Integer tourId, String name, String type,
                                        String country, String status, String login,
                                        float price, int countPeople, int markHotel, Date startDate,
                                        int days, float discount, Date dateReg) {
        OrderTours order = new OrderTours();
        order.setOrderId(orderId);
        order.setTourId(tourId);
        order.setName(name);
        order.setType(type);
        order.setCountry(country);
        order.setPrice(price);
        order.setCountPeople(countPeople);
        order.setMarkHotel(markHotel);
        order.setStartDate(startDate);
        order.setDays(days);
        order.setDiscount(discount);
        order.setStatus(status);
        order.setDateReg(dateReg);
        order.setLogin(login);
        return order;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
