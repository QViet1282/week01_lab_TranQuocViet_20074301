package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepository {
    Connection connection;
    public RoleRepository() throws RuntimeException {
        connection=Database.getInstance().getConnection();
    }

    public Optional<Role> get(String id){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("Select * from account where account_id=?");
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
               Role role = new Role(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                return Optional.of(role);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Role> getAll(){
        PreparedStatement statement = null;
        List<Role>lst=new ArrayList<>();
        try {
            statement = connection.prepareStatement("Select * from role ");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Role role = new Role(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                lst.add(role);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    public boolean create(Role role){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("insert into role values(?,?,?,?)");
            statement.setString(1, role.getRole_id());
            statement.setString(2, role.getRole_Name());
            statement.setString(3, role.getDescription());
            statement.setInt(4, role.getStatus());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Role role){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("update role set role_name=?, description=?, status=? where role_id=?");
            statement.setString(1, role.getRole_Name());
            statement.setString(2, role.getDescription());
            statement.setInt(3, role.getStatus());
            statement.setString(4, role.getRole_id());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String id){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("delete from role where role_id=?");
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
