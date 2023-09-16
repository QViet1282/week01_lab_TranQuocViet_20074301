package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {
    private static Database instance=null;
    private Connection connection=null;

    public Database() throws RuntimeException {
        String url = "jdbc:mariadb://localhost:3306/mydb";
        try {
            connection = DriverManager.getConnection(url,"root","sapassword");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Database getInstance() throws RuntimeException {
        if(instance==null){
            instance=new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
