package db.entity;

public class TypeTour {
    
    private int id;
    
    private String name;

    public static TypeTour createTypeTour(String name) {
        TypeTour typeTour = new TypeTour();
        typeTour.setName(name);
        return typeTour;
    }

    @Override
    public String toString() {
        return "TypeTour{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
}
