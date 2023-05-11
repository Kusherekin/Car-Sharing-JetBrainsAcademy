package carsharing.repository;

import carsharing.entity.Company;

import java.util.List;

public interface CompanyDao {
    void create(String[] args);
    Company saveCompany(Company company);
    List<Company> findAllCompanies();
    Company findCompanyById(int id);
    int findCompanyIdByName(String name);
}