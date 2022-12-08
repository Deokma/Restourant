<%--
  Created by IntelliJ IDEA.
  User: DeNiSOn4Ik
  Date: 08.10.2022
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title>Account</title>
    <link rel="shortcut icon" href="https://www.kfc.by/assets/img/desktop/favicon_new.ico">
</head>
<body>
<jsp:include page="components/navbar.jsp"/>
<div class="row justify-content-center">
    <div class="col-8">
        <h1>Profile</h1>
        <p>Hello, ${user.getFirstName()}😀!</p>
        <p>Role: ${role}</p>
    </div>
    <div class="row">
        <div class="col-md-9 bg-light p-5 border">
            <h2>🧾My Orders:</h2>
            <div class="container text-center">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 bg-info rounded-top">
                    <div class="col border border-dark"><h6>Order Number:</h6></div>
                    <div class="col border border-dark"><h6>Sum:</h6></div>
                    <div class="col border border-dark"><h6>Status:</h6></div>
                </div>
            </div>
            <c:forEach items="${order_list}" var="order" varStatus="status">
                <c:if test="${order.getUserid() == user.getUserId()}">
                    <form class="" action="${pageContext.request.contextPath}/controller">
                        <div class="container text-center">
                            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 bg-light">
                                <input type="hidden" name="orderid" value="${order}">
                                <div class="border border-dark">${order.getOrderid()}</div>
                                <div class="border border-dark">${order.getTotalsum()} б. р.</div>
                                <c:if test="${order.getStatusString() == \"INPROGRESS\" }" >
                                <div class="border border-dark text-danger">${order.getStatusString()}</div>
                                </c:if>
                                <c:if test="${order.getStatusString() == \"COMPLETE\" }" >
                                    <div class="border border-dark text-success">${order.getStatusString()}</div>
                                </c:if>
                            </div>
                        </div>
                    </form>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
