<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.entities.Account" %><%--
  Created by IntelliJ IDEA.
  User: quocv
  Date: 9/10/2023
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #cornerButton {
            position: fixed; /* Đặt vị trí tuyệt đối */
            top: 20px; /* Khoảng cách từ phía trên */
            right: 20px; /* Khoảng cách từ phía phải */
            padding: 10px 20px; /* Kích thước nút */
            cursor: pointer;
        }
    </style>
    <title>Title</title>

</head>
<body>
<h1>Dashboard Quản Lý Tài Khoản</h1>
<table>
    <thead>
    <tr>
        <th>Tên</th>
        <th>Email</th>
        <th>Quyền</th>
        <th>Thao Tác</th>
    </tr>
    </thead>
    <tbody>
    <form action="/control" method="post">
        <input type="hidden" name="action" value="logout">
        <button type="submit" id="cornerButton">Logout</button>
    </form>
    <!-- Sử dụng vòng lặp để hiển thị danh sách tài khoản -->
    <% List<Account> data = (List<Account>) request.getAttribute("dataAccount");
        for (Account account : data) { %>
            <tr>
                <td><%= account.getFull_name() %></td>
                <td><%= account.getEmail() %></td>
                <td><%= account.getPhone() %></td>
                <td>
                    <form action="/control" method="post">
                        <input type="hidden" name="id" value="<%= account.getAccount_id() %>">
                        <input type="hidden" name="action" value="update">
                        <button type="submit">Update</button>
                    </form>
                    <form action="/control" method="post">
                        <input type="hidden" name="id" value="<%= account.getAccount_id() %>">
                        <input type="hidden" name="action" value="delete">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
    <% } %>

    <!-- Thêm các dòng tương tự cho các tài khoản khác -->
    </tbody>
</table>
</body>
</html>
