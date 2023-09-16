package vn.edu.iuh.fit.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.Log;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class AccountServices {
    private AccountRepository accountRepository;
    private GrantAccessRepository grantAccessRepository;
    private LogRepository logRepository;
    private RoleRepository roleRepository;
    private Optional<Account> account;

    int logId;

    public AccountServices() {
        accountRepository = new AccountRepository();
        grantAccessRepository = new GrantAccessRepository();
        logRepository = new LogRepository();
        roleRepository = new RoleRepository();
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String password = req.getParameter("password");
        account = accountRepository.login(user,password);
        if(account.isPresent()) {
            req.setAttribute("account",account.get());
            if(grantAccessRepository.get(account.get().getAccount_id(),"admin").isPresent()){
                List<Account> dataAccount = accountRepository.getAll();
                req.setAttribute("dataAccount",dataAccount);
//                java.time.LocalDateTime current = java.time.LocalDateTime.now();
//                timestamplogin = Timestamp.valueOf(current);
//                Calendar now = Calendar.getInstance();
                logId = logRepository.create(account.get().getAccount_id(),"");
//                req.setAttribute("logId",logId);
                req.getRequestDispatcher("dashboard.jsp").forward(req,resp);


            }
            else {
                resp.setContentType("text/html");
//                resp.getWriter().println("Đăng nhập thành công<br>");
                logId = logRepository.create(account.get().getAccount_id(),"");
                req.getRequestDispatcher("pageuser.jsp").forward(req,resp);
            }


        }
        else req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logRepository.update(logId);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
