<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title>Cart</title>
    <%@ include file="components/head.jsp" %>
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
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 bg-info rounded-top border border-dark">
        <div class="col">Dish Name:</div>
        <div class="col">Price:</div>
        <div class="col"></div>
    </div>
</div>
<%--<c:if test="${order.getUserid() == menuorder.getUserid()}">--%>
<c:forEach items="${menuorder}" var="menuorder" varStatus="status">
    <c:if test="${menuorder.getUserid() == user.getUserId()}">
        <div class="col">
            <form class="" action="${pageContext.request.contextPath}/controller">
                <div class="container text-center">
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 bg-light border border-dark">
                        <input type="hidden" name="userid" value="${request.getAttribute(userid)}">
                        <div class="col">${menuorder.getDishname()}</div>
                        <div class="col">${menuorder.getPrice()} б. р.</div>
                    </div>
                </div>
            </form>
        </div>
    </c:if>
    </div>
</c:forEach>
<div class="container text-center">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 bg-secondary rounded-bottom border border-dark">
        <div class="col text-light">Total sum:</div>
        <div class="col text-light">$totalsum$</div>
        <div class="col">
            <div class="col">
<%--                <c:forEach items="${menuorder}" var="menuorder" varStatus="status">--%>
                <form class="d-flex" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="userid" value="${userid}">
                    <input type="hidden" name="command" value="complete_cart">
                    <button class="btn btn-sm btn-warning border border-info" type="submit">
                        Complete
                    </button>
                </form>
<%--                </c:if>--%>
<%--                </c:forEach>--%>
            </div>
        </div>

    </div>
</div>

<c:forEach items="${order_list}" var="order" varStatus="status">
    <form class="" action="${pageContext.request.contextPath}/controller">
        <div class="container text-center">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 bg-light border border-dark">
                <input type="hidden" name="orderid" value="${menuorder.getOrderid()}">
                <div class="col">${menuorder.getOrderid()}</div>
                <div class="col">${menuorder.getTotalsum()}</div>
                <div class="col">${menuorder.getStatusString()}</div>
            </div>
        </div>
        </div>
    </form>
</c:forEach>
<%--</c:if>--%>
<%@include file="components/footer.jsp" %>
</body>
</html>
