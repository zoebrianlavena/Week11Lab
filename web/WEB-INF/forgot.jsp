<%-- 
    Document   : forgot
    Created on : Nov 16, 2018, 1:00:59 PM
    Author     : 743953
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>Forgot Password</h1>
        <form method="post" action="forgot">
            Please enter your email address to retrieve your password.
            Email Address: <input type="text" name="email"/>
            <input type="submit" value="Submit"/>
        </form>
        ${message}
    </body>
</html>
