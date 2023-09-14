package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class LogRepository {
    private Connection connection;
    public LogRepository(){
        connection = Database.getInstance().getConnection();
    }

    public Optional<Log> get(int id){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("Select * from account where id=?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Log log = new Log(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5));
                return Optional.of(log);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<Log> getAll(){
        PreparedStatement statement = null;
        List<Log>lst=new ArrayList<>();
        try {
            statement = connection.prepareStatement("Select * from log ");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Log log = new Log(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5));
                lst.add(log);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    public boolean create(Log log){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("insert into log values(?,?,?,?,?)");
            statement.setInt(1, log.getId());
            statement.setString(2, log.getAccount_id());
//            statement.setDate(3, LocalDate.now());
//            statement.setDate(4, log.getUpdated_at());
            statement.setString(5, log.getNotes());
            int rs = statement.executeUpdate();
            if(rs>0){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean update(Log log) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("update log set logout_time=? where id=?");
//            statement.setDate(1, LocalDateTime.now());
            statement.setInt(2, log.getId());
            int rs = statement.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
