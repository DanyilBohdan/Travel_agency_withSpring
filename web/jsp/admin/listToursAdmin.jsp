<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Main" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>

<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">

            <form id="main" action="controller" method="post">

                <h1><fmt:message key="main.name"/></h1>

                <form action="controller" method="get">
                    <input type="hidden" name="command" value="getCreateTour"/>
                    <input type="submit" value="Create New Tour"/>
                </form>

                <table>
                    <c:forEach var="tour" items="${tours}">
                        <tr class="tour_view">
                            <td>
                                <fmt:message key="tour.name"/>: ${tour.name}<br>
                                <fmt:message key="tour.type"/>: ${tour.type}<br>
                                <fmt:message key="tour.country"/>: ${tour.country}<br>
                                <fmt:message key="tour.price"/>: ${tour.price}<br>
                            </td>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="getEditTour">
                                    <input type="hidden" name="id" value="${tour.id}">
                                    <button type="submit" class="btn btn-outline-dark">
                                        Edit
                                    </button>
                                </form>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="deleteTour">
                                    <input type="hidden" name="id" value="${tour.id}">
                                    <button type="submit" class="btn btn-outline-red">
                                        Delete
                                    </button>
                                </form>
                            </td>

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
