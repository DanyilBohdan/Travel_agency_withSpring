package db.entity;


public class TourInfo {

    private int id;

    private String description;

    public static TourInfo createTourInfo(String ti) {
        TourInfo tourInfo = new TourInfo();
        tourInfo.setDescription(ti);
        return tourInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TourInfo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
