<%@include file="WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="LogIn"/>
<%@include file="WEB-INF/jspf/head.jspf" %>
<body>
<div id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container p-3 my-3 border">
        <form id="login_form" action="controller" method="post">
            <input type="hidden" name="command" value="login"/>
            <fieldset>
                <legend>
                    <fmt:message key="login_jsp.label.login"/>
                </legend>
                <input name="login"/><br/>
            </fieldset>
            <br/>
            <fieldset>
                <legend>
                    <fmt:message key="login_jsp.label.password"/>
                </legend>
                <input type="password" name="password"/>
            </fieldset>
            <button type="submit" class="btn btn-outline-dark"><fmt:message key="login_jsp.button.login"/></button>
        </form>
        <form id="register" action="controller" method="post">
            <input type="hidden" name="command" value="register">
            <button type="submit" class="btn btn-outline-dark"><fmt:message key="login_jsp.button.registration"/></button>
        </form>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
