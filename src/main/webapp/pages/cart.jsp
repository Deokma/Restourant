<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title>Cart</title>
    <style type="text/css">
        .table tbody td {
            vertical-align: middle;
        }
    </style>
    <link rel="shortcut icon" href="https://www.kfc.by/assets/img/desktop/favicon_new.ico">
</head>
<body>
<jsp:include page="components/navbar.jsp"/>
<div class="container text-center">
    <h2 class="d-flex">🛒Cart</h2>
</div>

<table class="table container table-striped">
    <thead>
    <tr>
        <th scope="col">Dish</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${menuorder}" var="menuorder" varStatus="status">
        <c:if test="${menuorder.getUserid() == user.getUserId()}">
            <tr>
                <td>${menuorder.getDishname()}</td>
                <td>${menuorder.getPrice()} б. р.</td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>

<div class="container text-center">
    <form class="d-flex" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="userid" value="${userid}">
        <input type="hidden" name="command" value="complete_cart">
        <button class="btn d-grid gap-2 col-6 mx-auto btn-warning border border-info" type="submit">
            Buy
        </button>
    </form>
</div>
</body>
</html>
