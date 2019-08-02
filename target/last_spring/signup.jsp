<%--
  Created by IntelliJ IDEA.
  User: vadim.aminov
  Date: 24.07.2019
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up page</title>
</head>
<body>
<form action="/signup" method="post">
    <table border="2">
        <tr>
            <td>Name</td>
            <td>Login</td>
            <td>Password</td>
        </tr>
        <tr>
            <td><input type="text" name="name"/></td>
            <td><input type="text" name="login"/></td>
            <td><input type="text" name="password"/></td>
        </tr>
        <br><input type="submit" value="Sign up"/></br>
    </table>
</form>
<a href="/index">To main</a>
</body>
</html>
