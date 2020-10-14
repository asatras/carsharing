<%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<%--<fmt:setLocale value="${sessionScope.lang}"/>--%>
<%--<fmt:setBundle basename="messages"/>--%>

<%--<html lang="${sessionScope.lang}">--%>
<%--<head>--%>
<%--<meta charset="UTF-8">--%>
<%--<title>Registration</title>--%>
<%--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
<%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"--%>
<%--      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"--%>
<%--      crossorigin="anonymous">--%>
<%--</head>--%>




<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h1>This is reg page!</h1>


    <h1>Test block</h1>
    <form method="post" action="Servlet">
        <input type="hidden" name="command" value="registration"/>
        Login<input type="text" name="login"/>
        Password<input type="password" name="password"/>
        Email<input type="text" name="email"/>
        Name<input type="text" name="name"/>
        Surname<input type="text" name="surname"/>
        Passport<input type="text" name="passport"/>
        <button type="submit">Registration</button>
    </form>
    <br>
    <p class="message"><a href="${pageContext.request.contextPath}/index.jsp"> <fmt:message key="message.sign.in"/></a>
    </p>


<%--    <div class="container mt-2">--%>

<%--        <c:if test="${requestScope.error eq true}">--%>
<%--            <div class="alert alert-danger" align="center">--%>
<%--                <strong>Invalid email or password</strong>--%>
<%--            </div>--%>
<%--        </c:if>--%>

<%--        <form action="Servlet" method="post">--%>
<%--            <!-- Username -->--%>
<%--            <div class="form-group row">--%>
<%--                <label class="col-sm-2 col-form-label">--%>
<%--                    <fmt:message key="label.login"/>--%>
<%--                </label>--%>
<%--                <div class="col-sm-4">--%>
<%--                    <input type="text" name="login" class="form-control" required autofocus--%>
<%--                           placeholder="<fmt:message key="placeholder.username"/>"/>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <!-- Password -->--%>
<%--            <div class="form-group row">--%>
<%--                <label class="col-sm-2 col-form-label">--%>
<%--                    <fmt:message key="label.password"/>--%>
<%--                </label>--%>
<%--                <div class="col-sm-4 ">--%>
<%--                    <input type="password" name="password" class="form-control" required--%>
<%--                           placeholder="<fmt:message key="placeholder.password"/>"/>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <button type="submit" class="btn btn-primary">--%>
<%--                <fmt:message key="label.registration"/>--%>
<%--            </button>--%>
<%--        </form>--%>

<%--        <div class="mt-2">--%>
<%--            <a href="${pageContext.request.contextPath}/exhibition/login"--%>
<%--               class="btn btn-light">--%>
<%--                <fmt:message key="label.login"/>--%>
<%--            </a>--%>
<%--        </div>--%>

<%--    </div>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"--%>
<%--            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"--%>
<%--            crossorigin="anonymous"></script>--%>
<%--    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"--%>
<%--            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"--%>
<%--            crossorigin="anonymous"></script>--%>
</body>
</html>
