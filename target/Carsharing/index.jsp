<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Carsharing</title>
</head>
<body>
<div class="container mt-2">
    <%@ include file="/WEB-INF/parts/navbar.jsp" %>
</div>
<h2>Hello, guest!</h2>

<div>
    <form action="login.jsp">
        <input type="submit" value="Login" />
    </form>
</div>

</body>
</html>
