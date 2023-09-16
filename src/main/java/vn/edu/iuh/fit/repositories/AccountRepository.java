package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private Connection connection;
    public AccountRepository(){
        connection = Database.getInstance().getConnection();
    }

    public List<Account> getAll(){
        PreparedStatement statement = null;
        List<Account>lst=new ArrayList<>();
        try {
            statement = connection.prepareStatement("Select * from account ");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Account st = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                lst.add(st);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    public Optional<Account> get(String id){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("Select * from account where account_id=?");
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Account st = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                return Optional.of(st);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public Optional<Account> login(String id, String password){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("Select * from account where account_id=? and password=?");
            statement.setString(1, id);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Account ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                return Optional.of(ac);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean create(Account account){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("insert into account values(?,?,?,?,?,?)");
            statement.setString(1, account.getAccount_id());
            statement.setString(2, account.getFull_name());
            statement.setString(3, account.getPassword());
            statement.setString(4, account.getEmail());
            statement.setString(5, account.getPhone());
            statement.setString(6, account.getStatus());
            int rs = statement.executeUpdate();
            if(rs>0){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean update(Account account){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("update account set full_name=?, password=?, email=?, phone=?, status=? where account_id=?");
            statement.setString(1, account.getFull_name());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getEmail());
            statement.setString(4, account.getPhone());
            statement.setString(5, account.getStatus());
            statement.setString(6, account.getAccount_id());
            int rs = statement.executeUpdate();
            if(rs>0){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean delete(String id){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("delete from account where account_id=?");
            statement.setString(1, id);
            int rs = statement.executeUpdate();
            if(rs>0){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
