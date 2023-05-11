package carsharing.controller;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.service.DaoImpl;
import carsharing.utils.Input;
import carsharing.utils.Menu;

import java.util.List;

public class ChooseCompanyController {

    public static void run(DaoImpl dao) {
        List<Company> companies = dao.findAllCompanies();
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            boolean backCar = false;
            while (!backCar) {
                Menu.printCompanies(companies);
                int companyNr = Input.getInt();
                if (companyNr == 0) {
                    backCar = true;
                } else {
                    String companyName = companies.get(companyNr - 1).getName();
                    int company_id = dao.findCompanyIdByName(companyName);
                    System.out.printf("%n'%s' company%n", companyName);
                    boolean backCarList = false;
                    while (!backCarList) {
                        Menu.printCar();
                        switch (Input.getInt()) {
                            case 0: {
                                backCarList = true;
                                backCar = true;
                                break;
                            }
                            case 1: {
                                List<Car> cars = dao.findAllCarsByCompanyId(company_id);
                                if (cars.isEmpty()) {
                                    System.out.println("The car list is empty!");
                                } else {
                                    Menu.printCars(cars);
                                }
                                break;
                            }
                            case 2: {
                                Input.getString(); // consume the "end of line" after nextInt()
                                System.out.println("Enter the car name:");
                                dao.saveCar(new Car(0, Input.getString(), company_id, false));
                                System.out.println("The car was added!");
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}