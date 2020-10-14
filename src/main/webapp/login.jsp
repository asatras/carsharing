<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login page</h1>

    <h1>Test block</h1>
    <form method="get" action="Servlet">
        <input type="hidden" name="command" value="login"/>
        Login<input type="text" name="login"/>
        Password<input type="password" name="password"/>
        <button type="submit">login</button>
    </form>
<br>
    <form method="get" action="Servlet">
        <input type="hidden" name="command" value="registration"/>
        <input type="submit" value="Registration"/>
    </form>
</body>
</html>