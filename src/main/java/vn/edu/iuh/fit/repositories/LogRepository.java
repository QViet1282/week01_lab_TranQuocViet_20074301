package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.Log;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class LogRepository {
    private Connection connection;
    public LogRepository(){connection = Database.getInstance().getConnection();
    }

//    public Optional<Log> get(String account_id, Timestamp login_time){
//        PreparedStatement statement = null;
//        try {
//            statement = connection.prepareStatement("Select * from log where account_id=? and login_time=?");
//            statement.setString(1, account_id);
//            statement.setTimestamp(2, login_time);
//            ResultSet rs = statement.executeQuery();
//            if(rs.next()){
//                Log log = new Log(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5));
//                return Optional.of(log);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return Optional.empty();
//    }

//    public List<Log> getAll(){
//        PreparedStatement statement = null;
//        List<Log>lst=new ArrayList<>();
//        try {
//            statement = connection.prepareStatement("Select * from log ");
//            ResultSet rs = statement.executeQuery();
//            while(rs.next()){
//                Log log = new Log(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5));
//                lst.add(log);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return lst;
//    }

    public int create(String account_id, String notes) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("INSERT INTO log (account_id, login_time, notes) VALUES (?, ?, ?)", statement.RETURN_GENERATED_KEYS);
            statement.setString(1, account_id);
            LocalDateTime current = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(current);
            statement.setTimestamp(2, timestamp);
//            statement.setTimestamp(3, null);

            statement.setString(3, notes);
            int rs = statement.executeUpdate();
            if (rs == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    return generatedId;
                } else {
                    throw new SQLException("Không lấy được ID của bản ghi vừa được tạo.");
                }
            } else {
                throw new SQLException("Chèn bản ghi thất bại, không có bản ghi nào được thêm.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("update log set logout_time=? where id=?");
//            Calendar now = Calendar.getInstance();
            LocalDateTime current = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(current);
            statement.setTimestamp(1, timestamp);
            statement.setInt(2, id);
            int rs = statement.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
//    public boolean updateLogout_time (int id, Date logout_time) {
//        PreparedStatement statement = null;
//        java.time.LocalDateTime current = java.time.LocalDateTime.now();
//        Timestamp timestamp = Timestamp.valueOf(current);
//        try {
//            statement = connection.prepareStatement("UPDATE log SET logout_time=? WHERE id=? ");
//            statement.setTimestamp(1, l);
//            statement.setInt(2, id);
//
//            int rs = statement.executeUpdate();
//            if (rs > 0)
//                return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

}
