package Infrastructure.Repository;

import java.util.ArrayList;

import Domain.Entity.Company;
import Domain.ValueObject.Address;
import Infrastructure.Interface.ICompanyRepository;

public class CompanyRepository implements ICompanyRepository {
    private ICompanyRepository shopediaDB;
    private ArrayList<Company> companies = null;
    private String query = null;
    private Company company = null;

    private void resetQuery() {
        companies = null;
        query = null;
        company = null;
    }

    @Override
    public boolean updateCompany(String companyID, String companyName, Address companyAddress, String companyEmail) {
        resetQuery();
        return shopediaDB.updateCompany(companyID, companyName, companyAddress, companyEmail);
    }

    @Override
    public boolean deleteCompany(String companyID) {
        resetQuery();
        return shopediaDB.deleteCompany(companyID);
    }

    @Override
    public void addCompany(String companyID, String companyName, Address companyAddress, String companyEmail) {
        resetQuery();
        shopediaDB.addCompany(companyID, companyName, companyAddress, companyEmail);
    }

    @Override
    public ArrayList<Company> getAllCompany() {
        if (companies == null || query != null) {
            resetQuery();
            companies = shopediaDB.getAllCompany();
            if (companies == null) {
                return new ArrayList<>();
            }
        }
        return companies;
    }

    @Override
    public Company getCompany(String companyID) {
        if (company == null || query != null) {
            resetQuery();
            company = shopediaDB.getCompany(companyID);
            if (company == null) {
                return null;
            }
        }
        return company;
    }

}
