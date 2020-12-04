<%-- 
    Document   : index
    Created on : Oct 19, 2020, 7:21:25 AM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="Login" method="POST">
            <input type="text" name="txtUsername" placeholder="Email" value="${requestScope.IDUSERNAME}" required=""/><br/><br/>
            <input type="password" name="txtPassword" placeholder="Password" required=""/><br/><br/>
            <font color="tomato">${requestScope.INVALIDDD}</font>
            <input type="submit" name="action"/><br/><br/>
            <a href="register.jsp">Register</a>
        </form>
    </body>
</html>
