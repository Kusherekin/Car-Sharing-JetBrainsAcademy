package carsharing.utils;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;

import java.util.List;

public class Menu {


    public static void printLog() {
        System.out.println("1. Log in as a manager\n" +
                "2. Log in as a customer\n" +
                "3. Create a customer\n" +
                "0. Exit");
    }

    public static void printCar() {
        System.out.println("\n1. Car list\n2. Create a car\n0. Back");
    }

    public static void printRentCar() {
        System.out.println("1. Rent a car\n" +
                "2. Return a rented car\n" +
                "3. My rented car\n" +
                "0. Back");
    }

    public static void printCompany() {
        System.out.println("\n1. Company list\n2. Create a company\n0. Back");
    }

    public static void printCompanies(List<Company> companies) {
        System.out.println("Choose a company:");
        for (int i = 0; i < companies.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, companies.get(i).getName());
        }
        System.out.println("0. Back");
    }

    public static void printCars (List<Car> cars) {
        System.out.println("Car list:");
        for (int i = 0; i < cars.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, cars.get(i).getName());
        }
    }

    public static void printCustomers(List<Customer> customers) {
        System.out.println("Customer list:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, customers.get(i).getName());
        }
        System.out.println("0. Back");
    }
}