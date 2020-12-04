<%-- 
    Document   : register
    Created on : Sep 22, 2020, 2:02:50 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="register.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="regis">
            <center>
                <h1>Sign up Page</h1>
                <h2>Sign up to archive more features</h2>

                <form action="Register" method="POST">
                    <input class="memId" type="text" name="txtMemberId" placeholder="Email" required="" value="${requestScope.MAIL}"><br/>
                    <font style="color: #e7caa9; font-size: 20px">${requestScope.INVALID}</font><br/>
                    <input class="memId" type="text" name="txtFullname" placeholder="Full name" required="" value="${requestScope.FN}"><br/>
                    <br/>
                    <input class="memId" type="password" name="txtPassword" placeholder="Password" required=""><br/>
                    <br/>
                    <input class="memId" type="password" name="txtConfirmPass" placeholder="Confirm your password" required=""><br/>
                    <font style="color: #e7caa9; font-size: 20px">${requestScope.CONFIRM}</font><br/>
                    <input type="text" name="txtAddress" placeholder="Address" required=""/><br/><br/><br/>
                    <input class="btnR" type="submit" value="Sign Up" name="action">
                </form>
            </center>
        </div>
    </body>
</html>
