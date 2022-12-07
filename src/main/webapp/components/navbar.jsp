<%--
  Created by IntelliJ IDEA.
  User: DeNiSOn4Ik
  Date: 08.10.2022
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.popolamov.restourant.controller.LoginServlet" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
      <img src="https://e7.pngegg.com/pngimages/694/227/png-clipart-monumental-restaurant-elche-logo-kitchen-kitchen-miscellaneous-kitchen.png" alt="" width="35" height="30" class="d-inline-block align-text-top">
      Restaurant++</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Меню</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Features</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">О компании</a>
        </li>
      </ul>
      <%String name = (String)request.getAttribute("username");%>
      <%= name%>
      <c:choose>
        <c:if test="${name != null}">
          <jsp:include page="logined.jsp"/>
        </c:if>
        <c:if test="${name == null}">
          <jsp:include page="nologin.jsp"/>
        </c:if>
      </c:choose>
    </div>
  </div>
</nav>
