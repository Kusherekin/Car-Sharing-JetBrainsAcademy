package carsharing.controller;

import carsharing.service.DaoImpl;
import carsharing.utils.Input;
import carsharing.utils.Menu;

public class ManagerController {

    public static void run(DaoImpl dao) {
        boolean back = false;
        while (!back) {
            Menu.printCompany();
            switch (Input.getInt()) {
                case 0: {
                    back = true;
                    break;
                }
                case 1: {
                    ChooseCompanyController.run(dao);
                    break;
                }
                case 2: {
                    CreateCompanyController.run(dao);
                    break;
                }
            }
        }
    }
}