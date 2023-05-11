package carsharing.controller;

import carsharing.service.DaoImpl;

public class CarSharingController {
    public static void run(String[] args) {
        DaoImpl dao = new DaoImpl();
        dao.create(args);
        LogController.run(dao);
    }
}