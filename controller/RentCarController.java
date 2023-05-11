package carsharing.controller;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;
import carsharing.service.DaoImpl;
import carsharing.utils.Input;
import carsharing.utils.Menu;

import java.util.List;

public class RentCarController {

    public static void run(Customer customer, DaoImpl dao) {
        if (customer.getRented_car_id() != null) {
            System.out.println("You've already rented a car!");
        } else {
            List<Company> companies = dao.findAllCompanies();
            Menu.printCompanies(companies);
            int companyNr = Input.getInt();
            if (companyNr != 0) {
                int companyId = companies.get(companyNr - 1).getId();
                List<Car> cars = dao.findAllNotRentedCarsByCompanyId(companyId);
                Menu.printCars(cars);
                int carNr = Input.getInt();
                if (carNr != 0) {
                    Car car = cars.get(carNr - 1);
                    car.setRented(true);
                    dao.updateCar(car);
                    customer.setRented_car_id(car.getId());
                    customer.setHasReturned(false);
                    dao.updateCustomer(customer);
                    System.out.printf("You rented '%s'%n", cars.get(carNr - 1).getName());
                }
            }
        }
    }
}