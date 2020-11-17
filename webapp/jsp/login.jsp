<%@include file="../WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="../WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="LogIn"/>
<%@include file="../WEB-INF/jspf/head.jspf" %>
<body>
<div id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container p-3 my-3 border">
        <form id="login_form" action="/user/login" method="post">
            <input type="hidden" name="command" value="login"/>
            <div class="input-group mb-3">
                <label>
                    <fmt:message key="login_jsp.label.login"/>
                </label>
                <input type="text" name="login" placeholder="">
            </div>
            <br/>
            <div class="input-group mb-3">
                <label>
                    <fmt:message key="login_jsp.label.password"/>
                </label>
                <input type="password" name="password"/>
            </div>
            <button type="submit" class="btn btn-outline-dark"><fmt:message key="login_jsp.button.login"/></button>
        </form>
        <form id="register" action="/user/register" method="get">
            <input type="hidden" name="command" value="register">
            <button type="submit" class="btn btn-outline-dark"><fmt:message key="login_jsp.button.registration"/></button>
        </form>

        <c:if test="${not empty errorVal}">
            <h5 class="text-light bg-danger">${errorVal}</h5>
        </c:if>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
