<%@ page import="java.io.PrintWriter" %>
<%@ page import="jakarta.servlet.http.Cookie" %><%--
  Created by IntelliJ IDEA.
  User: DeNiSOn4Ik
  Date: 08.10.2022
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
        for (Cookie aCookie : cookies) {
            aCookie.setMaxAge(0);
            response.addCookie(aCookie);
        }

    }
%>
</body>
</html>
