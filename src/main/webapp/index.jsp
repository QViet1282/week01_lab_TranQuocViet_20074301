<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<form action="/ControlServlet" method="get">
  <%
    try {
      // Load the MariaDB JDBC driver
      Class.forName("org.mariadb.jdbc.Driver");

      // Now you can establish a database connection and perform other database-related tasks if needed.
    } catch (Exception e) {
      e.printStackTrace();
    }
  %>
  <label for="username">Tài khoản:</label>
  <input type="text" id="username" name="username" required><br><br>

  <label for="password">Mật khẩu:</label>
  <input type="password" id="password" name="password" required><br><br>

  <input type="submit" value="Đăng nhập">
</form>
</body>
</html>