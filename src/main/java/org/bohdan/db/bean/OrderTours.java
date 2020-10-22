package org.bohdan.db.bean;

import java.util.Date;

/**
 * Locale class for table Order
 *
 * @author Bohdan Daniel
 */
public class OrderTours {

    private Integer order_id;

    private Integer tour_id;

    private String name;

    private String type;

    private String country;

    private float price;

    private int count_people;

    private int mark_hotel;

    private Date start_date;

    private int days;

    private float discount;

    private String status;

    private Date date_reg;

    private String login;

    public static OrderTours createTour(Integer order_id, Integer tour_id, String name, String type,
                                        String country, String status, String login,
                                        float price, int count_people, int mark_hotel, Date start_date,
                                        int days, float discount, Date date_reg) {
        OrderTours order = new OrderTours();
        order.setOrder_id(order_id);
        order.setTour_id(tour_id);
        order.setName(name);
        order.setType(type);
        order.setCountry(country);
        order.setPrice(price);
        order.setCount_people(count_people);
        order.setMark_hotel(mark_hotel);
        order.setStart_date(start_date);
        order.setDays(days);
        order.setDiscount(discount);
        order.setStatus(status);
        order.setDate_reg(date_reg);
        order.setLogin(login);
        return order;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getTour_id() {
        return tour_id;
    }

    public void setTour_id(Integer tour_id) {
        this.tour_id = tour_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate_reg() {
        return date_reg;
    }

    public void setDate_reg(Date date_reg) {
        this.date_reg = date_reg;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
