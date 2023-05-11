package carsharing.entity;

public class Customer {

    private int id;
    private String name;
    private Integer rented_car_id;
    private boolean hasReturned;

    public Customer(int id, String name, Integer rented_car_id, boolean hasReturned) {
        this.id = id;
        this.name = name;
        this.rented_car_id = rented_car_id;
        this.hasReturned = false;
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

    public Integer getRented_car_id() {
        return rented_car_id;
    }

    public void setRented_car_id(Integer rented_car_id) {
        this.rented_car_id = rented_car_id;
    }

    public boolean isHasReturned() {
        return hasReturned;
    }

    public void setHasReturned(boolean hasReturned) {
        this.hasReturned = hasReturned;
    }
}