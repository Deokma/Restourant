<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h1>Hello, ${user.getFirstName()} 😀!</h1>
    <h2 class="d-flex">Cart</h2>
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 bg-info rounded-top border border-dark">
        <div class="col">Dish Name:</div>
        <div class="col">Price:</div>
        <div class="col"></div>
    </div>
</div>
<c:if test="${order.getUserid() == menuorder.getUserid()}">
<c:forEach items="${cart_list}" var="cart" varStatus="status">
    <c:if test="${menuorder.getOrderid() != 0}">
        <div class="col">
            <form class="" action="${pageContext.request.contextPath}/controller">
                <div class="container text-center">
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 bg-light border border-dark">
<%--                        <input type="hidden" name="userid" value="${request.getAttribute(userid)}">--%>
                        <div class="col">${menuorder.getDishname()}</div>
                        <div class="col">${menuorder.getPrice()} б. р.</div>
                        <div class="col"><input type="hidden" name="command" value="complete_cart">
                            <button class="btn btn-sm btn-success" type="submit">
                                Complete
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </c:if>
    </div>
</c:forEach>
<c:forEach items="${order_list}" var="order" varStatus="status">
        <form class="" action="${pageContext.request.contextPath}/controller">
            <div class="container text-center">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 bg-light border border-dark">
                    <input type="hidden" name="orderid" value="${order.getOrderid()}">
                    <div class="col">${order.getOrderid()}</div>
                    <div class="col">${order.getTotalsum()}</div>
                    <div class="col">${order.getStatusString()}</div>
                    </div>
                </div>
            </div>
        </form>
</c:forEach>
</c:if>
<%@include file="components/footer.jsp" %>
</body>
</html>
