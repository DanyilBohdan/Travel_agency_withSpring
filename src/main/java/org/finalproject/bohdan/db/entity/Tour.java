package org.finalproject.bohdan.db.entity;

public class Tour {

    private int id;

    private String name;

    private float price;

    private int count_people;

    private String description;

    private String mark_hotel;

    private String start_date;

    private int days;

    private float discount;

    private int type_tour_id;

    public static Tour createTour(String name, float price, int count_people, String mark_hotel,
                                  String start_date, int days, String description, int type_tour_id, float discount) {
        Tour tour = new Tour();
        tour.setName(name);
        tour.setPrice(price);
        tour.setCount_people(count_people);
        tour.setDescription(description);
        tour.setMark_hotel(mark_hotel);
        tour.setStart_date(start_date);
        tour.setDays(days);
        tour.setDiscount(discount);
        tour.setType_tour_id(type_tour_id);
        return tour;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count_people=" + count_people +
                ", mark_hotel='" + mark_hotel + '\'' +
                ", start_date='" + start_date + '\'' +
                ", days=" + days +
                ", description='" + description + '\'' +
                ", type_tour_id=" + type_tour_id +
                '}';
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMark_hotel() {
        return mark_hotel;
    }

    public void setMark_hotel(String mark_hotel) {
        this.mark_hotel = mark_hotel;
    }

    public int getType_tour_id() {
        return type_tour_id;
    }

    public void setType_tour_id(int type_tour_id) {
        this.type_tour_id = type_tour_id;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
