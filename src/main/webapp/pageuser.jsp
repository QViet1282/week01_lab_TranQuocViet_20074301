<%@ page import="vn.edu.iuh.fit.entities.Account" %><%--
  Created by IntelliJ IDEA.
  User: quocv
  Date: 9/16/2023
  Time: 10:01 PM
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
    <title>PageUser</title>
</head>
<body>
<form action="/control" method="post">
    <input type="hidden" name="action" value="logout">
    <button type="submit" id="cornerButton">Logout</button>
</form>
<% Account account = (Account) request.getAttribute("account"); %>

    <h2>Name: <%= account.getFull_name() %></h2><br>
    <h2>Email: <%= account.getEmail() %></h2><br>
    <h2>Phone: <%= account.getPhone() %></h2><br>
    <h2>Status: <%= account.getStatus() %></h2><br>

</body>
</html>
