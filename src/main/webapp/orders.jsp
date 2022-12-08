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
<div class="container text-center">
    <h3 class="d-flex text-danger">In Progress:</h3>
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 bg-info rounded-top">
        <div class="col border border-dark"><h6>Order Number:</h6></div>
        <div class="col border border-dark"><h6>Sum:</h6></div>
        <div class="col border border-dark"><h6>Status:</h6></div>
        <div class="col bg-light border border-light"></div>
    </div>
</div>
<c:forEach items="${order_list}" var="order" varStatus="status">
    <c:if test="${order.getStatus() == 2}">
        <form class="" action="${pageContext.request.contextPath}/controller">
            <div class="container text-center">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 bg-light">
                    <input type="hidden" name="orderid" value="${order.getOrderid()}">
                    <div class="col border border-dark"> ${order.getOrderid()}</div>
                    <div class="col border border-dark">${order.getTotalsum()} б. р.</div>
                    <div class="col border border-dark text-danger">${order.getStatusString()}</div>
                    <div class="col border border-light"><input type="hidden" name="command" value="complete_order">
                        <button class="btn btn-sm btn-success" type="submit">
                            Complete
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </c:if>
</c:forEach>
<div class="container text-center">
    <h3 class="d-flex text-success">Complete:</h3>
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 bg-info rounded-top">
        <div class="col border border-dark"><h6>Order Number:</h6></div>
        <div class="col border border-dark"><h6>Sum:</h6></div>
        <div class="col border border-dark"><h6>Status:</h6></div>
        <div class="col bg-light border border-light"></div>
    </div>
</div>
<c:forEach items="${order_list}" var="order" varStatus="status">
    <c:if test="${order.getStatus() == 1}">
        <form class="" action="${pageContext.request.contextPath}/controller">
            <div class="container text-center">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 bg-light">
                    <input type="hidden" name="orderid" value="${order.getOrderid()}">
                    <div class="border border-dark">${order.getOrderid()}</div>
                    <div class="border border-dark">${order.getTotalsum()} б. р.</div>
                    <div class="border border-dark text-success">${order.getStatusString()}</div>
                    <div class="col border border-light"><input type="hidden" name="command" value="delete_order">
                        <button class="btn btn-sm btn-danger" type="submit">
                            Delete
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </c:if>
</c:forEach>
</body>
</html>
