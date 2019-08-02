<%--
  Created by IntelliJ IDEA.
  User: vadim.aminov
  Date: 24.07.2019
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@page session="false"%>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="menu.jsp" />
<h1>Message : ${message}</h1>
<form action="signup" method="get">
    <input type="submit" value="Sign up"/>
</form>
<form action="/login" method="get">
    <input type="submit" value="Login"/>
</form>
</body>
</html>
