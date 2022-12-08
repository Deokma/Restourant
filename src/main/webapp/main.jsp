<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="shortcut icon" href="https://www.kfc.by/assets/img/desktop/favicon_new.ico">
    <title>KFC</title>
    <%@ include file="components/head.jsp" %>
</head>
<body>
<jsp:include page="components/navbar.jsp"/>
<jsp:include page="components/carousel.jsp"/>
<div class="container">
    <div class="row row-cols-1 row-cols-md-5 g-4">
        <c:forEach items="${menu_list}" var="menu" varStatus="status">
            <div class="col">
                <div class="card h-100">
                    <img src="${menu.getImage()}" width="500" height="200" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h6 class="card-title">
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="dish_id" value="${menu.getDishid()}">
                                <input type="hidden" name="command" value="get_menu">
                                <a class="nav-link active" href=#
                                   onclick="document.getElementById('${menu.getDishid()}').submit()" tabindex="-1"
                                   aria-disabled="true">${menu.getDishName()}</a>
                            </form>
                        </h6>
                    </div>
                    <div class="card-footer">
                        <h3>${menu.getPrice()}</h3><h4 class="text-muted">б. р.</h4>
                        <c:choose>
                            <c:when test="${role == 'ADMIN' || role == 'USER'}">
<%--                                <input type="hidden" name="command" value="add_dish_to_cart">--%>
<%--                                <a class="btn btn-dark ms-3" href=""></a>--%>
                                    <form class="d-flex" action="${pageContext.request.contextPath}/controller">
                                        <input type="hidden" name="userid" value="${user.getUserId()}">
                                        <input type="hidden" name="dishid" value="${menu.getDishid()}">
                                        <input type="hidden" name="price" value="${menu.getPrice()}">
                                        <input type="hidden" name="command" value="add_dish_to_cart">
                                        <button class="btn btn-dark ms-3" type="submit">
                                            <fmt:message key="get_menu.text.menu.add.to.cart.button"/>
                                        </button>
                                    </form>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="get_menu_list.sign.in.to.add.to.cart"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
</body>
</html>
