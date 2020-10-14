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

            <fieldset>
                <div id="legend">
                    <legend class="">Register</legend>
                </div>

                <div class="control-group">
                    <!-- Username -->
                    <label class="control-label" for="username">Username</label>
                    <div class="controls">
                        <input type="text" id="username" name="username" placeholder="" class="input-xlarge">
                        <p class="help-block">Username can contain any letters or numbers, without spaces</p>
                    </div>
                </div>

                <div class="control-group">
                    <!-- E-mail -->
                    <label class="control-label" for="login">Login</label>
                    <div class="controls">
                        <input type="text" id="login" name="login" placeholder="" class="input-xlarge">
                        <p class="help-block">Please provide your login</p>
                    </div>
                </div>

                <div class="control-group">
                    <!-- E-mail -->
                    <label class="control-label" for="phone">Phone</label>
                    <div class="controls">
                        <input type="text" id="phone" name="phone" placeholder="" class="input-xlarge">
                        <p class="help-block">Please provide your login</p>
                    </div>
                </div>

                <div class="control-group">
                    <!-- Password-->
                    <label class="control-label" for="password">Password</label>
                    <div class="controls">
                        <input type="password" id="password" name="password" placeholder="" class="input-xlarge">
                        <p class="help-block">Password should be at least 4 characters</p>
                    </div>
                </div>

                <div class="control-group">
                    <!-- Password -->
                    <label class="control-label" for="password_confirm">Password (Confirm)</label>
                    <div class="controls">
                        <input type="password" id="password_confirm" name="password_confirm" placeholder=""
                               class="input-xlarge">
                        <p class="help-block">Please confirm password</p>
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
            </fieldset>
        </form>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
