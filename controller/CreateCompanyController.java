package carsharing.controller;

import carsharing.entity.Company;
import carsharing.service.DaoImpl;
import carsharing.utils.Input;

public class CreateCompanyController {

    public static void run(DaoImpl dao) {
        Input.getString(); // consume the "end of line" after nextInt()
        System.out.println("Enter the company name:");
        dao.saveCompany(new Company(0, Input.getString()));
        System.out.println("The company was created!");
    }
}