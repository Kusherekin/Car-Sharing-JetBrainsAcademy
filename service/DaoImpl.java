package carsharing.service;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;
import carsharing.repository.CarDao;
import carsharing.repository.CompanyDao;
import carsharing.repository.CustomerDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoImpl implements CompanyDao, CarDao, CustomerDao {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static String db_url = "jdbc:h2:./src/carsharing/db/";

    @Override
    public void create(String[] args) {
        String databaseFileName = "carsharing";
        if (args != null) {
            databaseFileName = Arrays.stream(args)
                    .dropWhile("-databaseFileName"::equals)
                    .findFirst()
                    .orElse("carsharing");
        }
        db_url += databaseFileName;
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(db_url);

            // STEP 3: Enable the auto-commit mode so that all changes are automatically saved in the database file
            conn.setAutoCommit(true);

            //STEP 4: Create table COMPANY if not exists
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(ID INT NOT NULL AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL, " +
                    "PRIMARY KEY (ID), " +
                    "UNIQUE (NAME))";
            stmt.executeUpdate(sql);

            // STEP 5: Create table CAR if not exists
            stmt = conn.createStatement();
            sql =  "CREATE TABLE IF NOT EXISTS CAR " +
                    "(ID INT NOT NULL AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL, " +
                    "COMPANY_ID INT NOT NULL, " +
                    "RENTED BOOLEAN NOT NULL, " +
                    "PRIMARY KEY (ID), " +
                    "FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID), " +
                    "UNIQUE (NAME))";
            stmt.executeUpdate(sql);

            // STEP 6: Create table CUSTOMER if not exists
            stmt = conn.createStatement();
            sql =  "CREATE TABLE IF NOT EXISTS CUSTOMER " +
                    "(ID INT NOT NULL AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL, " +
                    "RENTED_CAR_ID INT, " +
                    "HAS_RETURNED BOOLEAN NOT NULL, " +
                    "PRIMARY KEY (ID), " +
                    "FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID), " +
                    "UNIQUE (NAME))";
            stmt.executeUpdate(sql);

            // STEP 7: Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    @Override
    public Company saveCompany(Company company) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // ADD a new company to the table COMPANY
            stmt = conn.createStatement();
            String sql =  String.format("INSERT INTO COMPANY (NAME) VALUES ('%s')", company.getName());
            stmt.executeUpdate(sql);

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        return company;
    }


    @Override
    public List<Company> findAllCompanies() {
        Connection conn = null;
        Statement stmt = null;
        List<Company> companies = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // select all companies from table COMPANY
            stmt = conn.createStatement();
            String sql =  "SELECT * FROM COMPANY ORDER BY ID";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");

                companies.add(new Company(id, name));
            }

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        return companies;
    }

    @Override
    public Company findCompanyById(int id) {
        Connection conn = null;
        Statement stmt = null;
        Company company = null;
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // select all companies from table COMPANY
            stmt = conn.createStatement();
            String sql =  String.format("SELECT * FROM COMPANY WHERE ID = %d", id);
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String name = rs.getString("NAME");
                company = new Company(id, name);
            }

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        return company;
    }

    @Override
    public int findCompanyIdByName(String name) {
        Connection conn = null;
        Statement stmt = null;
        int id = -1;
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // select all companies from table COMPANY
            stmt = conn.createStatement();
            String sql =  String.format("SELECT ID FROM COMPANY WHERE NAME = '%s'", name);
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                id = rs.getInt("ID");
            }

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        return id;
    }

    @Override
    public void saveCar(Car car) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // ADD a new car to the table CAR
            stmt = conn.createStatement();
            String sql =  String.format("INSERT INTO CAR (NAME, COMPANY_ID, RENTED) VALUES ('%s', %d, %b)",
                    car.getName(),
                    car.getCompany_id(),
                    car.isRented());
            stmt.executeUpdate(sql);

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    @Override
    public List<Car> findAllCarsByCompanyId(int company_id) {
        Connection conn = null;
        Statement stmt = null;
        List<Car> cars = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // select all cars from table CAR for companyId
            stmt = conn.createStatement();
            String sql =  String.format("SELECT * FROM CAR WHERE COMPANY_ID = %d ORDER BY ID", company_id);
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                boolean rented = rs.getBoolean("RENTED");

                cars.add(new Car(id, name, company_id, rented));
            }

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        return cars;
    }

    @Override
    public List<Car> findAllNotRentedCarsByCompanyId(int company_id) {
        Connection conn = null;
        Statement stmt = null;
        List<Car> cars = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // select all cars from table CAR for companyId
            stmt = conn.createStatement();
            String sql =  String.format("SELECT * FROM CAR WHERE COMPANY_ID = %d AND RENTED = FALSE ORDER BY ID", company_id);
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                boolean rented = rs.getBoolean("RENTED");

                cars.add(new Car(id, name, company_id, rented));
            }

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        return cars;
    }

    @Override
    public Car findCarById(int id) {
        Connection conn = null;
        Statement stmt = null;
        Car car = null;
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // select all companies from table COMPANY
            stmt = conn.createStatement();
            String sql =  String.format("SELECT * FROM CAR WHERE ID = %d", id);
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String name = rs.getString("NAME");
                int companyId = rs.getInt("COMPANY_ID");
                boolean rented = rs.getBoolean("RENTED");
                car = new Car(id, name, companyId, rented);
            }

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        return car;
    }

    @Override
    public void updateCar(Car car) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // ADD a new car to the table CAR
            stmt = conn.createStatement();
            String sql =  String.format("UPDATE CAR SET RENTED = %b WHERE ID = %d",
                    car.isRented(),
                    car.getId());
            stmt.executeUpdate(sql);

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    @Override
    public void saveCustomer(Customer customer) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // ADD a new customer to the table CUSTOMER
            stmt = conn.createStatement();
            String sql =  String.format("INSERT INTO CUSTOMER (NAME, HAS_RETURNED) VALUES ('%s', %b)",
                    customer.getName(),
                    customer.isHasReturned());
            stmt.executeUpdate(sql);

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    @Override
    public void updateCustomer(Customer customer) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // UPDATE customer in table CUSTOMER
            stmt = conn.createStatement();
            String sql =  String.format("UPDATE CUSTOMER SET RENTED_CAR_ID = %d, HAS_RETURNED = %b WHERE NAME = '%s'",
                    customer.getRented_car_id(),
                    customer.isHasReturned(),
                    customer.getName());
            stmt.executeUpdate(sql);

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    @Override
    public List<Customer> findAllCustomers() {
        Connection conn = null;
        Statement stmt = null;
        List<Customer> customers = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // select all companies from table COMPANY
            stmt = conn.createStatement();
            String sql =  "SELECT * FROM CUSTOMER ORDER BY ID";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                Integer rented_car_id = rs.getObject("RENTED_CAR_ID", Integer.class);
                boolean hasReturned = rs.getBoolean("HAS_RETURNED");

                customers.add(new Customer(id, name, rented_car_id, hasReturned));
            }

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        return customers;
    }

    @Override
    public Customer findByName(String name) {
        Connection conn = null;
        Statement stmt = null;
        Customer customer = null;
        try {
            conn = DriverManager.getConnection(db_url);
            conn.setAutoCommit(true);

            // select all companies from table COMPANY
            stmt = conn.createStatement();
            String sql =  String.format("SELECT * FROM CUSTOMER WHERE NAME = '%s'", name);
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("ID");
                int rented_car_id = rs.getInt("RENTED_CAR_ID");
                boolean hasReturned = rs.getBoolean("HAS_RETURNED");
                customer = new Customer(id, name, rented_car_id, hasReturned);
            }

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        return customer;
    }
}