<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title>Profile</title>
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
                    <c:if test="${order.getUserid() == user.getUserId()}">
                        <tr>
                            <td>${order.getOrderid()}</td>
                            <td>${order.getTotalsum()} б. р.</td>
                            <c:if test="${order.getStatusString() == \"COMPLETE\" }">
                                <td class="text-success">${order.getStatusString()}</td>
                            </c:if>
                            <c:if test="${order.getStatusString() == \"INPROGRESS\" }">
                                <td class="text-danger">${order.getStatusString()}</td>
                            </c:if>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
