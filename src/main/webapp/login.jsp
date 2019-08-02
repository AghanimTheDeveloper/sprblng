<%--
  Created by IntelliJ IDEA.
  User: vadim.aminov
  Date: 24.07.2019
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in page</title>
</head>
<body>
<form action="/login" method="post">
    <table border="2">
        <tr>
            <td>Login</td>
            <td>Password</td>
        </tr>
        <tr>
            <td><input type="text" name="login"/></td>
            <td><input type="text" name="password"/></td>
        </tr>
        <br><input type="submit" value="Login"></br>
    </table>
</form>
<form action="/signup" method="get">
    <input type="submit" name="Sign Up"/>
</form>
</body>
</html>
