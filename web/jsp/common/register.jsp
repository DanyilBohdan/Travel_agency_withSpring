<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Registration"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container register-form">
        <form class="form-horizontal" action="controller" method="post">
            <input type="hidden" name="command" value="registerUser">
            <div class="container p-3 my-3 border">
                <div id="legend">
                    <label class=""><fmt:message key="account.register.registration"/></label>
                </div>

                <div class="control-group">
                    <!-- Username -->
                    <label class="control-label" for="username"><fmt:message key="account.register.username"/></label>
                    <div class="controls">
                        <input type="text" id="username" name="username" placeholder="" class="input-xlarge">
                        <p class="help-block"><fmt:message key="account.register.username.help"/></p>
                    </div>
                </div>

                <div class="control-group">
                    <!-- Login -->
                    <label class="control-label" for="login"><fmt:message key="account.register.login"/></label>
                    <div class="controls">
                        <input type="text" id="login" name="login" placeholder="example@example.com" class="input-xlarge">
                        <p class="help-block"><fmt:message key="account.register.login.help"/></p>
                    </div>
                </div>

                <div class="control-group">
                    <!-- Phone -->
                    <label class="control-label" for="phone"><fmt:message key="account.register.phone"/></label>
                    <div class="controls">
                        <input type="text" id="phone" name="phone" placeholder="(123) 456-7890" class="input-xlarge">
                        <p class="help-block"><fmt:message key="account.register.phone.help"/></p>
                    </div>
                </div>

                <div class="control-group">
                    <!-- Password-->
                    <label class="control-label" for="password"><fmt:message key="account.register.password"/></label>
                    <div class="controls">
                        <input type="password" id="password" name="password" placeholder="" class="input-xlarge">
                        <p class="help-block"><fmt:message key="account.register.password.help"/></p>
                    </div>
                </div>

                <div class="control-group">
                    <!-- Password -->
                    <label class="control-label" for="password_confirm"><fmt:message key="account.register.passwordConfirm"/></label>
                    <div class="controls">
                        <input type="password" id="password_confirm" name="password_confirm" placeholder=""
                               class="input-xlarge">
                        <p class="help-block"><fmt:message key="account.register.passwordConfirm.help"/></p>
                    </div>
                </div>

                <div class="control-group">
                    <!-- Button -->
                    <div class="controls">
                        <button type="submit" class="btn btn-outline-dark">
                            <fmt:message key="login_jsp.button.registration"/>
                        </button>
                    </div>
                </div>

                <c:if test="${not empty errorVal}">
                    <h5 class="text-light bg-danger">${errorVal}</h5>
                </c:if>
            </div>
        </form>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
