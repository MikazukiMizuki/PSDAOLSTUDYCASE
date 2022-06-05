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
                "SELECT * FROM user " +
                        "WHERE userID = %s",
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteCompany(String companyID) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void addCompany(String companyID, String companyName, Address companyAddress, String companyEmail) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ArrayList<Company> getAllCompany() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Company getCompany(String companyID) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
