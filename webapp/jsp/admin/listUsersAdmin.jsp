<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Main" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>
<div class="container p-3 my-3 border">
    <div class="container p-3 my-3 border">
        <form class="form-inline" method="post" action="controller">
            <input type="hidden" name="command" value="searchUser">
            <input type="text" name="searchText" class="form-control"
                   placeholder="<fmt:message key="search.placeholder"/>">
            <button type="submit" class="btn btn-outline-dark"><fmt:message key="search.button"/></button>
            <select id="search" name="searchSelect">
                <option value="username" selected><fmt:message key="account.label.username"/></option>
                <option value="login"><fmt:message key="login_jsp.button.login"/></option>
                <option value="phone_number"><fmt:message key="account.label.phone_number"/></option>
            </select>
        </form>
    </div>

    <div class="container p-3 my-3 border">
        <c:forEach var="user" items="${users}">
            <div class="row">
                <div class="container">
                    <fmt:message key="account.label.username"/>: ${user.username}<br>
                    <fmt:message key="account.label.login"/>: ${user.login}<br>
                    <fmt:message key="account.label.phone_number"/>: ${user.phoneNumber}<br>
                    <label><fmt:message key="account.label.status"/>:</label>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="updateStatusUser">
                        <input type="hidden" name="id" value="${user.id}">
                        <c:if test="${user.role != 'admin'}">
                            <input type="submit" value="${user.status}">
                        </c:if>
                    </form>
                </div>
                <div class="container">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="updateRole">
                        <input type="hidden" name="id" value="${user.id}">
                        <label><fmt:message key="role"/> :</label>
                        <c:if test="${user.role != 'admin'}">
                            <select name="selectRole" onchange="submit()">
                                <option value="user" ${user.role == 'user' ? 'selected' : ''}><fmt:message
                                        key="user"/></option>
                                <option value="manager" ${user.role == 'manager' ? 'selected' : ''}><fmt:message
                                        key="manager"/></option>
                            </select>
                        </c:if>
                        <c:if test="${user.role == 'admin'}">
                            <label><fmt:message key="admin"/></label>
                        </c:if>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
