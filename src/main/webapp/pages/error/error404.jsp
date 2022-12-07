<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <title>
            <fmt:message key="title.error.page.404"/>
        </title>
    </head>
    <body>
        <fmt:message key="title.error.page.404"/>
    </body>
</html>
