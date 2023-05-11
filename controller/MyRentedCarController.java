package carsharing.controller;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;
import carsharing.service.DaoImpl;

public class MyRentedCarController {

    public static void run(Customer customer, DaoImpl dao) {
        if (customer.getRented_car_id() == null) {
            System.out.println("You didn't rent a car!");
        } else {
            System.out.println("Your rented car:");
            int rentedCarId = customer.getRented_car_id();
            Car car = dao.findCarById(rentedCarId);
            Company company = dao.findCompanyById(car.getCompany_id());
            System.out.println(car.getName());
            System.out.println("Company:");
            System.out.println(company.getName());
        }
    }
}