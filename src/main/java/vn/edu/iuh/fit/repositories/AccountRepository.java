package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;

import java.util.Optional;

public class AccountRepository {
    private Database database;
    public AccountRepository() {
        database = new Database();
    }

    public boolean login(String username, String password){
        return database.get(username).get().getPassword().equals(password);
    }
}
