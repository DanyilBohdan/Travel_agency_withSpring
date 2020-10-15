<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Main" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">
    <form class="form-inline" method="post" action="controller">
        <input type="hidden" name="command" value="searchUser">
        <input type="text" name="searchText" class="form-control" placeholder="Search roll no..">
        <button type="submit" class="btn-primary">Search</button>
        <select id="search" name="searchSelect">
            <option value="username" selected>username</option>
            <option value="login">login</option>
            <option value="phone_number">phone number</option>
        </select>
    </form>
</div>

<form id="main" action="controller" method="post">
    <table>
        <c:forEach var="user" items="${users}">
            <tr class="tour_view">
                <td>
                    Username: ${user.username}<br>
                    Login: ${user.login}<br>
                    Phone: ${user.phone_number}<br>
                </td>
                <td>
                    <label>Status:</label>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="updateStatusUser">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="${user.status}">
                    </form>
                    <br>
                    <label>Role:</label>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="updateRole">
                        <input type="hidden" name="id" value="${user.id}">
                        <c:if test="${user.role != 'admin'}">
                            <select name="selectRole" onchange="submit()">
                                <option value="user" ${user.role == 'user' ? 'selected' : ''}>user</option>
                                <option value="manager" ${user.role == 'manager' ? 'selected' : ''}>manager</option>
                            </select>
                        </c:if>
                        <label>${user.role == 'admin' ? 'admin' : ''}</label>
                    </form>
                </td>

            </tr>
        </c:forEach>

    </table>
</form>
</body>
</html>
