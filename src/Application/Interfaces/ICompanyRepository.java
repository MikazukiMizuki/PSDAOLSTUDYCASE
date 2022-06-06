package Application.Interfaces;

import java.util.ArrayList;

import Domain.Entity.Company;
import Domain.ValueObject.Address;

public interface ICompanyRepository {
    public boolean updateCompany(String companyID, String companyName, Address companyAddress, String companyEmail);

    public boolean deleteCompany(String companyID);

    public void addCompany(String companyID, String companyName, Address companyAddress, String companyEmail);

    public ArrayList<Company> getAllCompany();

    public Company getCompany(String companyID);
}
