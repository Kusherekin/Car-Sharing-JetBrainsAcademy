package carsharing.repository;

import carsharing.entity.Customer;

import java.util.List;

public interface CustomerDao {

    void saveCustomer(Customer customer);
    void updateCustomer(Customer customer);
    List<Customer> findAllCustomers();
    Customer findByName(String name);
}