<%--
  Created by IntelliJ IDEA.
  User: vadim.aminov
  Date: 24.07.2019
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
    <title>User Info</title>
</head>
<body>

<h2>User Info Page</h2>

<h3>Welcome : ${pageContext.request.userPrincipal.name}</h3>
</body>
</html>
