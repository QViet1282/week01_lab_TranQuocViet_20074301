package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {
    private Connection connection=null;

    public Database() throws RuntimeException {
        String url = "jdbc:mariadb://localhost:3306/mydb";
        try {
            connection = DriverManager.getConnection(url,"root","sapassword");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
}
