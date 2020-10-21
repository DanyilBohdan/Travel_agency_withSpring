<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@taglib prefix="f" uri="http://library.web.bohdan.org/functionalTag" %>
<html>
<c:set var="title" value="Account"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="float-right">
        ${f:getDate()}
    </div>
    <%@ include file="/WEB-INF/jspf/locale.jspf" %>
    <div class="container p-3 my-3 border">
        <div class="container float-right ">
            <form id="editAcc" action="controller" method="post">
                <input type="hidden" name="command" value="getEditAccount"/>
                <button type="submit" class="btn btn-outline-dark"><fmt:message
                        key="account.label.EditAccount"/></button>
            </form>
        </div>
        <div class=" container float-left">
            <form id="personalAcc" action="controller" method="post">
                <input type="hidden" name="command" value="accountAdmin"/>
                <div class="border border-secondary">
                    <label>
                        <fmt:message key="account.label.username"/>:
                    </label>${user.username}
                </div>
                <div class="border border-secondary">
                    <label>
                        <fmt:message key="account.label.login"/>:
                    </label>${user.login}
                </div>
                <div class="border border-secondary">
                    <label>
                        <fmt:message key="account.label.password"/>:
                    </label>${user.password}
                </div>
                <div class="border border-secondary">
                    <label>
                        <fmt:message key="account.label.phone_number"/>:
                    </label>${user.phone_number}
                </div>
                <c:if test="${user.status} == false">
                    <div class="border border-secondary">
                        <label>
                            <fmt:message key="account.label.status"/>:
                        </label><fmt:message key="account.label.block"/>
                    </div>
                </c:if>
                <div class="border border-secondary">
                    <label>
                        <fmt:message key="account.label.role"/>:
                    </label>${userRole}
                </div>
            </form>
        </div>
        <div class="row">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="listOrders"/>
                <button type="submit" class="btn btn-outline-dark"><fmt:message
                        key="account.admin.listOrders"/></button>
            </form>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
