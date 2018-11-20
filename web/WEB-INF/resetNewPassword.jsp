<%-- 
    Document   : resetNewPassword
    Created on : Nov 20, 2018, 12:45:51 PM
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
        <h1>Enter a new password</h1>
        <form method="post" action="reset?action=change">
            <input type="password" name="newpassword"/>
            <input type="hidden" name="hiddenuuid" value="${uuid}"/>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
