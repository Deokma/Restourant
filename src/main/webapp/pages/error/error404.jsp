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
    <jsp:include page="../components/navbar.jsp"/>
    <img class="position-absolute top-50 start-50 translate-middle" src="https://assets.atlanticbt.com/content/uploads/2016/02/404_atlanticbt_blog-1140x510.jpg">
    </body>
</html>
