<%@ page pageEncoding="UTF-8" %>
<%@include file="WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Main" scope="page"/>
<%@include file="WEB-INF/jspf/head.jspf" %>
<body>

<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <%@ include file="/WEB-INF/jspf/locale.jspf" %>
    <tr>
        <td class="content">

            <form id="main" action="controller" method="post">
                <input type="hidden" name="command" value="viewTour">

                <h1><fmt:message key="main.name"/></h1>
                <%-- <p><a href='<c:url value="/create" />'>Create new <b>TOUR</b></a></p>--%>
                <table>
                    <c:forEach var="tour" items="${tours}">
                        <tr class="tour_view">
                            <td>
                                <fmt:message key="list_tour_jsp.table.header.name"/>: ${tour.name}<br>
                                <fmt:message key="list_tour_jsp.table.header.type"/>: ${tour.type}<br>
                                <fmt:message key="list_tour_jsp.table.header.country"/>: ${tour.country}<br>
                                <fmt:message key="list_tour_jsp.table.header.price"/>: ${tour.price}<br>
                                <input type="hidden" name="command" value="viewTour">
                                <input type="hidden" name="id" value="${tour.id}">
                                <input type="submit" value="View">
                            </td>
                                <%--                            <td>--%>
                                <%--                                <a href='<c:url value="/edit?id=${tour.id}" />'>Edit</a> |--%>
                                <%--                                <form method="post" action='<c:url value="/delete" />' style="display:inline;">--%>
                                <%--                                    <input type="hidden" name="id" value="${tour.id}">--%>
                                <%--                                    <input type="submit" value="Delete">--%>
                                <%--                                </form>--%>
                                <%--                            </td>--%>

                        </tr>
                    </c:forEach>

                </table>
            </form>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
