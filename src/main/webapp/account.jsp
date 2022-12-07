<%--
  Created by IntelliJ IDEA.
  User: DeNiSOn4Ik
  Date: 08.10.2022
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
    <link rel="shortcut icon" href="https://www.kfc.by/assets/img/desktop/favicon_new.ico">
</head>
<body>
<jsp:include page="components/navbar.jsp"/>
<div class="row justify-content-center">
    <div class="col-8">
        <h1>Personal account</h1>
        <p>Hello, ${login}</p>
        <p>Role: ${role}</p>
    </div>
<div class="row">
    <jsp:include page="components/accountorders.jsp"/>
    <jsp:include page="components/key.jsp"/>
</div>
</div>
</body>
</html>
