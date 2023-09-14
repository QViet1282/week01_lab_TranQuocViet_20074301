package vn.edu.iuh.fit.demo;

import vn.edu.iuh.fit.repositories.AccountRepository;

public class Demo {
    public static void main(String[] args) {
        AccountRepository repository = new AccountRepository();
        if(repository.login("met","123"))
            System.out.println("Login success");
        else
            System.out.println("Login failed");

    }
}
