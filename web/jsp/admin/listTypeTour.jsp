<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="List Type" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="mainContainer">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <%@ include file="/WEB-INF/jspf/locale.jspf" %>
    <div class="container p-3 my-3 border">
        <h1><fmt:message key="account.admin.listTypeTour"/></h1>
        <div class="container p-3 my-3 border border-secondary">
            <form action="controller" method="get">
                <input type="hidden" name="command" value="createType"/>
                <label><fmt:message key="tour.create.nameEN"/>:</label>
                <input type="text" name="createNameEN" /><br>
                <label><fmt:message key="tour.create.nameRU"/>:</label>
                <input type="text" name="createNameRU" /><br>
                <button type="submit" class="btn btn-outline-dark">
                    Create New Type
                </button>
            </form>
        </div>
        <c:forEach var="type" items="${types}">
            <div class="border border-secondary">
                <form action="controller" method="post">
                    <label><fmt:message key="tour.create.nameEN"/>:</label>
                    <input name="nameEN" value="${type.name_en}"/><br>
                    <label><fmt:message key="tour.create.nameRU"/>:</label>
                    <input name="nameRU" value="${type.name_ru}"/><br>
                    <input type="hidden" name="command" value="editType">
                    <input type="hidden" name="id" value="${type.id}">
                    <button type="submit" class="btn btn-outline-dark">
                        Edit
                    </button>
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="deleteType">
                    <input type="hidden" name="id" value="${type.id}">
                    <button type="submit" class="btn btn-outline-danger">
                        Delete
                    </button>
                </form>
            </div>
        </c:forEach>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
