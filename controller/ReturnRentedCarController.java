package carsharing.controller;

import carsharing.entity.Car;
import carsharing.entity.Customer;
import carsharing.service.DaoImpl;

public class ReturnRentedCarController {

    public static void run(Customer customer, DaoImpl dao) {
        if (customer.isHasReturned()) {
            System.out.println("You've returned a rented car!");
        } else if (customer.getRented_car_id() == null) {
            System.out.println("You didn't rent a car!");
        } else {
            Car car = dao.findCarById(customer.getRented_car_id());
            car.setRented(false);
            dao.updateCar(car);
            customer.setRented_car_id(null);
            customer.setHasReturned(true);
            dao.updateCustomer(customer);
            System.out.println("You've returned a rented car!");
        }
    }
}