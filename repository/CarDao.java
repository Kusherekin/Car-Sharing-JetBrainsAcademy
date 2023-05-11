package carsharing.repository;

import carsharing.entity.Car;

import java.util.List;

public interface CarDao {
    void saveCar(Car car);
    List<Car> findAllCarsByCompanyId(int company_id);
    List<Car> findAllNotRentedCarsByCompanyId(int company_id);
    Car findCarById(int id);
    void updateCar(Car car);
}