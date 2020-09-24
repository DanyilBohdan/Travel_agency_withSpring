package db.entity;

public class Tour {

    private int id;

    private String name;

    private float price;

    private int count_people;

    private String type_hotel;

    private int mark;

    private int info_tour_id;

    private int type_tour_id;

    public static Tour createTour(String name, float price, int count_people, String type_hotel,
                                  int mark, int info_tour_id, int type_tour_id) {
        Tour tour = new Tour();
        tour.setName(name);
        tour.setPrice(price);
        tour.setCount_people(count_people);
        tour.setType_hotel(type_hotel);
        tour.setMark(mark);
        tour.setInfo_tour_id(info_tour_id);
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
                ", type_hotel='" + type_hotel + '\'' +
                ", mark=" + mark +
                ", info_tour_id=" + info_tour_id +
                ", type_tour_id=" + type_tour_id +
                '}';
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

    public String getType_hotel() {
        return type_hotel;
    }

    public void setType_hotel(String type_hotel) {
        this.type_hotel = type_hotel;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getInfo_tour_id() {
        return info_tour_id;
    }

    public void setInfo_tour_id(int info_tour_id) {
        this.info_tour_id = info_tour_id;
    }

    public int getType_tour_id() {
        return type_tour_id;
    }

    public void setType_tour_id(int type_tour_id) {
        this.type_tour_id = type_tour_id;
    }
}
