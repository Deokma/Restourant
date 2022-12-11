<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title>🧾Orders</title>
    <link rel="shortcut icon" href="https://www.kfc.by/assets/img/desktop/favicon_new.ico">
</head>
<body>
<jsp:include page="components/navbar.jsp"/>
<h3 class="d-flex text-danger container">In Progress:</h3>
<table class="table container bg-light">
    <thead>
    <tr>
        <th scope="col">Order Number</th>
        <th scope="col">Sum</th>
        <th scope="col">Status</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${order_list}" var="order" varStatus="status">
        <c:if test="${order.getStatus() == 2}">
            <tr>
                <td>${order.getOrderid()}</td>
                <td>${order.getTotalsum()} б. р.</td>
                <td>${order.getStatusString()}</td>
                <td>
                    <form class="" action="${pageContext.request.contextPath}/controller">
                        <div class="container text-center">
                            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 bg-light">
                                <input type="hidden" name="orderid" value="${order.getOrderid()}">
                                <div class="col border border-light"><input type="hidden" name="command"
                                                                            value="complete_order">
                                    <button class="btn btn-sm btn-success" type="submit">
                                        Complete
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>
<h3 class="d-flex text-success container">Complete:</h3>
<table class="table container bg-light">
    <thead>
    <tr>
        <th scope="col">Order Number</th>
        <th scope="col">Sum</th>
        <th scope="col">Status</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${order_list}" var="order" varStatus="status">
        <c:if test="${order.getStatus() == 1}">
            <tr>
                <td>${order.getOrderid()}</td>
                <td>${order.getTotalsum()} б. р.</td>
                <c:if test="${order.getStatusString() == \"COMPLETE\" }">
                    <td class="text-success">${order.getStatusString()}</td>
                </c:if>
                <c:if test="${order.getStatusString() == \"INPROGRESS\" }">
                    <td class="text-danger">${order.getStatusString()}</td>
                </c:if>
                <td>
                    <form class="" action="${pageContext.request.contextPath}/controller">
                        <div class="container text-center">
                            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 bg-light">
                                <input type="hidden" name="orderid" value="${order.getOrderid()}">
                                <div class="col border border-light"><input type="hidden" name="command"
                                                                            value="delete_order">
                                    <button class="btn btn-sm btn-danger" type="submit">
                                        Delete
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
