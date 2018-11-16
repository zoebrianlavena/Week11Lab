<%-- 
    Document   : reset
    Created on : Nov 16, 2018, 4:26:23 PM
    Author     : 743953
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        Please enter your email address to reset your password. <br>
        <form method="post" action="reset">
            Email Address: <input type="text" name="email"/>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
