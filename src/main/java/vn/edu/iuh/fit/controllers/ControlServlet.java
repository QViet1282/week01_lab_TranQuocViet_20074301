package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;
import vn.edu.iuh.fit.services.AccountServices;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/ControlServlet","/control"})
public class ControlServlet extends HttpServlet {
    AccountServices accountServices;

    @Override
    public void init() throws ServletException {
        accountServices = new AccountServices();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "login":
                accountServices.login(req,resp);
                break;
//            case "logout":
//                logout(req,resp);
//                break;
//            case "register":
//                register(req,resp);
//                break;
//            case "update":
//                update(req,resp);
//                break;
//            case "delete":
//                delete(req,resp);
//                break;
//            case "grant":
//                grant(req,resp);
//                break;
//            case "revoke":
//                revoke(req,resp);
//                break;
//            case "addRole":
//                addRole(req,resp);
//                break;
//            case "updateRole":
//                updateRole(req,resp);
//                break;
//            case "deleteRole":
//                deleteRole(req,resp);
//                break;
//            case "addLog":
//                addLog(req,resp);
//                break;
//            case "updateLog":
//                updateLog(req,resp);
//                break;
//            case "deleteLog":
//                deleteLog(req,resp);
//                break;
            default:
                break;
        }


    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html");
//        String user = request.getParameter("username");
//        String password = request.getParameter("password");
//        // Hello
//        try {
//            if(accountRepository.login(user,password)) {
//
//                Account account = accountRepository.get(user).get();
//                response.getWriter().println("Đăng nhập thành công<br>");
//                response.getWriter().println("Fullname: "+account.getFull_name()+"<br>");
//                response.getWriter().println("Email: "+account.getEmail()+"<br>");
//                response.getWriter().println("Phone: "+account.getPhone()+"<br>");
//                response.getWriter().println("Status: "+account.getStatus()+"<br>");
//            }
//            else response.getWriter().println("Đăng nhập thất bại");
//        } catch (Exception e) {
//            response.getWriter().println("Đăng nhập thất bại");
//        }
    }
}
