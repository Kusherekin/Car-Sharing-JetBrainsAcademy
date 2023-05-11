package carsharing.controller;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;
import carsharing.service.DaoImpl;
import carsharing.utils.Input;
import carsharing.utils.Menu;

import java.util.List;

public class CustomerController {

    public static void create(DaoImpl dao) {
        Input.getString(); // consume the "end of line" after nextInt()
        System.out.println("Enter the customer name:");
        dao.saveCustomer(new Customer(0, Input.getString(), null, false));
        System.out.println("The customer was added!");
    }

    public static void run(DaoImpl dao) {

        List<Customer> customers = dao.findAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("The customer list is empty!");
        } else {
            Menu.printCustomers(customers);
            int customerNr = Input.getInt();
            if (customerNr != 0) {
                Customer customer = customers.get(customerNr - 1);
                boolean backCustomer = false;
                while (!backCustomer) {
                    Menu.printRentCar();
                    switch (Input.getInt()) {
                        case 0: {
                            backCustomer = true;
                            break;
                        }
                        case 1: {
                            RentCarController.run(customer, dao);
                            break;
                        }
                        case 2: {
                            ReturnRentedCarController.run(customer, dao);
                            break;
                        }
                        case 3: {
                            MyRentedCarController.run(customer, dao);
                            break;
                        }
                    }
                }
            }
        }
    }
}