<%--
  Created by IntelliJ IDEA.
  User: DeNiSOn4Ik
  Date: 08.10.2022
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container">
        <a class="navbar-brand" href="/">
            <img src="https://i1.wp.com/user-images.githubusercontent.com/17777237/54872862-e06a1e80-4dd3-11e9-9987-2de90444dc98.jpg"
                 alt="" width="35" height="30" class="d-inline-block align-text-top">
            KFC</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href=""></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href=""></a>
                </li>
            </ul>
            <c:if test="${role == 'ADMIN' || role == 'USER'}">
                <form class="d-flex" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="userid" value="${user.getUserId()}">
                    <input type="hidden" name="command" value="move_to_cart_page">
                    <button class="btn btn-light btn-sm" aria-current="page" type="submit">
                        <h5><fmt:message key="navbar.common.cart"/></h5>
                    </button>
                </form>
            </c:if>
            <c:if test="${role == 'ADMIN'}">
                <form class="d-flex" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="move_to_orders_page">
                    <button class="btn btn-light btn-sm" aria-current="page" type="submit">
                        <h5><fmt:message key="navbar.common.orders"/></h5>
                    </button>
                </form>
            </c:if>
            <c:if test="${role != 'ADMIN' && role != 'USER'}">
                <div>
                    <form class="d-flex" action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="move_to_login_page">
                        <button class="btn btn-primary ms-1" type="submit">
                            <fmt:message key="navbar.common.sign.in"/>
                        </button>
                    </form>
                </div>
                <div>
                    <form class="d-flex" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="move_to_signup_page">
                        <button class="btn btn-primary ms-1" type="submit">
                            <fmt:message key="login.button.sign.up"/>
                        </button>
                    </form>
                </div>
            </c:if>
            <c:if test="${role == 'ADMIN' || role == 'USER'}">
                <div class="col d-flex justify-content-end align-items-end">
                    <form class="d-flex" action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="sign_out">
                        <button class="btn btn-primary d-flex" type="submit">
                            <fmt:message key="navbar.common.logout"/>
                        </button>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</nav>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
