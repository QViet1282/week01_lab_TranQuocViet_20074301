package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.entities.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GrantAccessRepository {
    private Connection connection;
    public GrantAccessRepository() throws RuntimeException {
        connection=Database.getInstance().getConnection();
    }

    public Optional<GrantAccess> get(String account, String role){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("Select * from grant_access where role_id=? and account_id=?");
            statement.setString(1, role);
            statement.setString(2, account);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                GrantAccess grantAccess = new GrantAccess(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                return Optional.of(grantAccess);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<GrantAccess> getAll(){
        PreparedStatement statement = null;
        List<GrantAccess>lst=new ArrayList<>();
        try {
            statement = connection.prepareStatement("Select * from grant_access ");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                GrantAccess grantAccess = new GrantAccess(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                lst.add(grantAccess);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    public boolean create(GrantAccess grantAccess){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("insert into grant_access values(?,?,?,?)");
            statement.setString(1, grantAccess.getRole_id());
            statement.setString(2, grantAccess.getAccount_id());
            statement.setInt(3, grantAccess.isIs_grant());
            statement.setString(4, grantAccess.getNote());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(GrantAccess grantAccess){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("update grant_access set is_grant=?, note=? where role_id=? and account_id=?");
            statement.setInt(1, grantAccess.isIs_grant());
            statement.setString(2, grantAccess.getNote());
            statement.setString(3, grantAccess.getRole_id());
            statement.setString(4, grantAccess.getAccount_id());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String account, String role){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("delete from grant_access where role_id=? and account_id=?");
            statement.setString(1, role);
            statement.setString(2, account);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
