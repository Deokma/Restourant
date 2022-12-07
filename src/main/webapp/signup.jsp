<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<!DOCTYPE html>
<link href="css/login.css" rel="stylesheet" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<html>
<head>
    <meta charset="ISO-8859-1">
    <title><fmt:message key="title.signup"/></title>
    <link rel="shortcut icon" href="https://www.kfc.by/assets/img/desktop/favicon_new.ico">
</head>
<body>
<jsp:include page="components/navbar.jsp"/>
<div class="container-fluid d-flex h-100 justify-content-center align-items-center p-0">

    <div class="row bg-white shadow-sm">

        <div class="col border rounded p-4">
            <h3 class="text-center mb-4">Registration</h3>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="sign_up">
                <div class="form-group">
                    <label for="floatingLogin">
                        <fmt:message key="signup.label.login"/>
                    </label>
                    <input type="text" class="form-control" name="login" id="floatingLogin" placeholder="<fmt:message key="signup.label.login"/>" name="login" <c:if test="${not empty login}">value="${login}"</c:if> aria-describedby="emailHelp">
                </div>
                <div class="form-group">
                    <label for="floatingFirstName">
                        <fmt:message key="signup.label.firstname"/>
                    </label>
                    <input type="text" class="form-control" name="firstname" id="floatingFirstName" placeholder="<fmt:message key="signup.label.firstname"/>" <c:if test="${not empty firstname}">value="${firstname}"</c:if> aria-describedby="emailHelp">
                </div>
                <div class="form-group">
                    <label for="floatingLastName">
                        <fmt:message key="signup.label.lastname"/>
                    </label>
                    <input type="text" class="form-control" name="lastname" id="floatingLastName" placeholder="<fmt:message key="signup.label.lastname"/>" <c:if test="${not empty lastname}">value="${lastname}"</c:if> aria-describedby="emailHelp">
                </div>
                <div class="form-group">
                    <label for="floatingPassword">
                        <fmt:message key="signup.label.password"/>
                    </label>
                    <input type="password" class="form-control" name="password" id="floatingPassword" placeholder="<fmt:message key="signup.label.password"/>">
                </div>
                <div class="form-group">
                    <label for="floatingRepeatPassword">
                        <fmt:message key="signup.label.repeat.password"/>
                    </label>
                    <input type="password" class="form-control" id="floatingRepeatPassword" placeholder="<fmt:message key="signup.label.password"/>" name="repeated_password">
                </div>
                <c:if test="${not empty error_message}">
                    <div class="badge bg-danger text-wrap" style="width: 6rem;">
                            ${error_message}
                    </div>
                </c:if>
                <label></label>
                <button type="submit" value="Submit" class="btn btn-primary w-100">
                    <fmt:message key="signup.label.create.account"/>
                </button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="components/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script type="text/javascript">
    var storedHash = window.location.hash;
    function changeHashOnLoad() {
        window.location.hash = "1";
    }
    window.onhashchange = function () {
        window.location.hash = storedHash;
    }
</script>
</body>
</html>