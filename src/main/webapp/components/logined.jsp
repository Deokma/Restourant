<%--
  Created by IntelliJ IDEA.
  User: DeNiSOn4Ik
  Date: 07.10.2022
  Time: 12:46
  To change this template use File | Settings | File Templates.
  Этот блок предназначен для залогинившихся пользователей
--%>
<ul class="navbar-nav d-flex mb-2 mb-lg-0">
    <li class="nav-item">
        <a class="nav-link active d-flex" aria-current="page" href="/account.jsp">Account</a>
    </li>
    <li class="nav-item">
        <a class="nav-link active d-flex" aria-current="page" href="/deletecookies" action="<%=request.getContextPath()%>/deletecookies" method="get">Logout</a>
    </li>
</ul>