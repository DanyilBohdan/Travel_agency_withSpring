<%@ page pageEncoding="UTF-8" %>
<%@include file="WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Main" scope="page"/>
<%@include file="WEB-INF/jspf/head.jspf" %>
<body>
<table>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <%@ include file="/WEB-INF/jspf/locale.jspf" %>
    <tr>
        <td>
            <form id="main" action="controller" method="post">
                <h1><fmt:message key="main.name"/></h1>
                <div class="card-deck">
                    <c:forEach var="tour" items="${tours}">
                        <div class="card border-dark mb-3" style="max-width: 25rem;">
                            <div class="card-header">${tour.name}</div>
                            <div class="card-body text-dark">
                                <p class="card-text">
                                    <fmt:message key="list_tour_jsp.table.header.country"/>: ${tour.country}<br>
                                    <fmt:message key="list_tour_jsp.table.header.type"/>: ${tour.type}<br>
                                    <fmt:message key="list_tour_jsp.table.header.price"/>: ${tour.price}<br>
                                </p>
                            </div>
                            <div class="card-footer bg-transparent border-success">
                                <input type="hidden" name="command" value="viewTour">
                                <input type="hidden" name="id" value="${tour.id}">
                                <button type="submit" class="btn btn-outline-dark">
                                    <fmt:message key="list_tour_jsp.table.view"/>
                                </button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </form>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
