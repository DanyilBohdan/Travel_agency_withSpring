package org.bohdan.db.bean;

import java.util.Date;

/**
 * Bean for find tours by id user
 *
 * @author Bohdan Daniel
 */
public class OrderToursByIdUser {

    private Integer orderId;

    private Integer tourId;

    private Date startDate;

    private int days;

    private String status;

    public static OrderToursByIdUser createTour(Integer orderId, Integer tourId, String status,
                                                Date startDate, int days) {
        OrderToursByIdUser order = new OrderToursByIdUser();
        order.setOrderId(orderId);
        order.setTourId(tourId);
        order.setStatus(status);
        order.setStartDate(startDate);
        order.setDays(days);
        return order;
    }

    @Override
    public String toString() {
        return "OrderToursByIdUser{" +
                "order_id=" + orderId +
                ", tour_id=" + tourId +
                ", start_date=" + startDate +
                ", days=" + days +
                ", status='" + status + '\'' +
                '}';
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
