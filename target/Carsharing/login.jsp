<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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