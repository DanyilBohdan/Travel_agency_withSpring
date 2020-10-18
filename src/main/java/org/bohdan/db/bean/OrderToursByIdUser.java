package org.bohdan.db.bean;

import java.util.Date;

public class OrderToursByIdUser {

    private Integer order_id;

    private Integer tour_id;

    private Date start_date;

    private int days;

    private String status;

    public static OrderToursByIdUser createTour(Integer order_id, Integer tour_id, String status, Date start_date, int days) {
        OrderToursByIdUser order = new OrderToursByIdUser();
        order.setOrder_id(order_id);
        order.setTour_id(tour_id);
        order.setStatus(status);
        order.setStart_date(start_date);
        order.setDays(days);
        return order;
    }

    @Override
    public String toString() {
        return "OrderToursByIdUser{" +
                "order_id=" + order_id +
                ", tour_id=" + tour_id +
                ", start_date=" + start_date +
                ", days=" + days +
                ", status='" + status + '\'' +
                '}';
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
