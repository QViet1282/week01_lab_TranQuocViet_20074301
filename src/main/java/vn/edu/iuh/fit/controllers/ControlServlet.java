package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.repositories.AccountRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/ControlServlet","/control"})
public class ControlServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);


    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        // Hello
        AccountRepository repository = new AccountRepository();
        try {
            if(repository.login(user,password))
                response.getWriter().println("Đăng nhập thành công");
            else response.getWriter().println("Đăng nhập thất bại");
        } catch (Exception e) {
            response.getWriter().println("Đăng nhập thất bại");
        }




    }
}
