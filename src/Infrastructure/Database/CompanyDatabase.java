package Infrastructure.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domain.Entity.Company;
import Domain.ValueObject.Address;
import Infrastructure.Connector.Connect;
import Infrastructure.Interface.ICompanyRepository;
import Infrastructure.Service.IExist;

public class CompanyDatabase implements ICompanyRepository, IExist {
    private final Connect db = Connect.getInstance();
    private String query = null;
    private AddressDatabase ad = new AddressDatabase();

    public boolean isNotExist(String id) {
        String query = String.format(
                "SELECT * FROM company " +
                        "WHERE companyID = %s",
                id);
        try (ResultSet result = db.executeQuery(query)) {
            return !result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateCompany(String companyID, String companyName, Address companyAddress, String companyEmail) {
        if (isNotExist(companyID)) {
            return false;
        }
        query = String.format(
                "UPDATE company "
                        + "SET companyName = %s, companyEmail = %s WHERE companyID = %s",
                companyName, companyEmail, companyID);
        db.executeUpdate(query);
        ad.editAddress(companyID, companyAddress.getStreet(), companyAddress.getCity(), companyAddress.getZipCode());
        return true;
    }

    @Override
    public boolean deleteCompany(String companyID) {
        if (isNotExist(companyID)) {
            return false;
        }
        query = String.format("DELETE FROM company " + "WHERE companyID = %s", companyID);
        db.executeUpdate(query);
        ad.deleteAddress(companyID);
        return true;
    }

    @Override
    public void addCompany(String companyID, String companyName, Address companyAddress, String companyEmail) {
        query = String.format(
                "INSERT INTO company(companyID, companyName, companyEmail) "
                        + "VALUES(%s, %s, %s)",
                companyID, companyName, companyEmail);
        db.executeUpdate(query);
        ad.addAddress(companyID, companyAddress.getStreet(), companyAddress.getCity(), companyAddress.getZipCode());
    }

    private ArrayList<Company> getCompanyWithQuery(String query) {
        ArrayList<Company> companies = new ArrayList<>();
        ResultSet rs = db.executeQuery(query);

        try {
            while (rs.next()) {
                String companyID = rs.getString("companyID");
                String companyName = rs.getString("companyName");
                String companyEmail = rs.getString("companyEmail");
                companies.add(new Company(companyID, companyName, null, companyEmail));
            }
            for (Company company : companies) {
                String addressQuery = String.format("SELECT street, name, zipcode FROM address " + "WHERE ID = %s",
                        company.getCompanyID());
                ResultSet addressResult = db.executeQuery(addressQuery);
                String street = addressResult.getString("street");
                String city = addressResult.getString("city");
                String zipCode = addressResult.getString("zipcode");
                Address address = new Address(street, city, zipCode);
                company.setCompanyAddress(address);
            }
            return companies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Company> getAllCompany() {
        String query = String.format("SELECT * FROM company");
        return getCompanyWithQuery(query);
    }

    @Override
    public Company getCompany(String companyID) {
        String query = String.format("SELECT * FROM company " + "WHERE companyID = %s", companyID);
        ArrayList<Company> companies = getCompanyWithQuery(query);
        return companies.get(0);
    }

}
