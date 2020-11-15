package org.bohdan.model;

/**
 * Order entity.
 *
 * @author Bohdan Daniel
 *
 */
public class Order {

    private Integer id;

    private String status;

    private Object dateReg;

    private int tourId;

    private int userId;

    public static Order createOrderTour(String status, Object dateReg, int tourId, int userId) {
        Order orderTour = new Order();
        orderTour.setStatus(status);
        orderTour.setDateReg(dateReg);
        orderTour.setTourId(tourId);
        orderTour.setUserId(userId);
        return orderTour;
    }

    @Override
    public String toString() {
        return "OrderTour{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", tour_id=" + tourId +
                ", user_id=" + userId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getDateReg() {
        return dateReg;
    }

    public void setDateReg(Object dateReg) {
        this.dateReg = dateReg;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
