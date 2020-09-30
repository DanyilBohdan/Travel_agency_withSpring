package org.bohdan.db.entity;

public class Order {

    private Integer id;

    private String status;

    private int tour_id;

    private int user_id;

    public static Order createOrderTour(String status) {
        Order orderTour = new Order();
        orderTour.setStatus(status);
        return orderTour;
    }

    @Override
    public String toString() {
        return "OrderTour{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", tour_id=" + tour_id +
                ", user_id=" + user_id +
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

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
