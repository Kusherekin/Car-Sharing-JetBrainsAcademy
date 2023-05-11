package carsharing.controller;

import carsharing.service.DaoImpl;
import carsharing.utils.Input;
import carsharing.utils.Menu;

public class LogController {

    public static void run(DaoImpl dao) {
        boolean exit = false;
        while (!exit) {
            Menu.printLog();
            switch (Input.getInt()) {
                case 0: {
                    exit = true;
                    break;
                }
                case 1: {
                    ManagerController.run(dao);
                    break;
                }
                case 2: {
                    CustomerController.run(dao);
                    break;
                }
                case 3: {
                    CustomerController.create(dao);
                    break;
                }
            }
        }
        System.out.println("Bye!");
    }
}