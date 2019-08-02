<%--
  Created by IntelliJ IDEA.
  User: vadim.aminov
  Date: 02.08.2019
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">

    <a href="${pageContext.request.contextPath}/index">Home</a>

    | &nbsp;

    <a href="${pageContext.request.contextPath}/user">User Info</a>

    | &nbsp;

    <a href="${pageContext.request.contextPath}/admin">Admin</a>

    <c:if test="${pageContext.request.userPrincipal.name != null}">

        | &nbsp;
        <a href="${pageContext.request.contextPath}/logout">Logout</a>

    </c:if>

</div>
