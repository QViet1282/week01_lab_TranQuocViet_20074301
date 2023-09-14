package vn.edu.iuh.fit.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.util.Optional;

public class AccountServices {
    private AccountRepository accountRepository;
    private GrantAccessRepository grantAccessRepository;
    private LogRepository logRepository;
    private RoleRepository roleRepository;

    public AccountServices() {
        accountRepository = new AccountRepository();
        grantAccessRepository = new GrantAccessRepository();
        logRepository = new LogRepository();
        roleRepository = new RoleRepository();
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<Account> account = accountRepository.login(user,password);
        if(account.isPresent()) {
            req.setAttribute("account",account.get());
            if(grantAccessRepository.get(account.get().getAccount_id(),"admin").isPresent()){
                //            req.getRequestDispatcher("views/account.jsp").forward(req,resp);
            }
            else {
                resp.setContentType("text/html");
                resp.getWriter().println("Đăng nhập thành công<br>");
                resp.getWriter().println("Fullname: "+account.get().getFull_name()+"<br>");
                resp.getWriter().println("Email: "+account.get().getEmail()+"<br>");
                resp.getWriter().println("Phone: "+account.get().getPhone()+"<br>");
                resp.getWriter().println("Status: "+account.get().getStatus()+"<br>");
            }


        }
        else req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
