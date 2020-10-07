<%@ page pageEncoding="UTF-8" %>
<%@include file="WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Main" scope="page"/>
<%@include file="WEB-INF/jspf/head.jspf" %>
<body>

<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>


    <tr>
        <td class="content">

            <form id="main" action="controller" method="get">
                <input type="hidden" name="command" value="viewTours">

                <h1>Tour Agency</h1>
                <%-- <p><a href='<c:url value="/create" />'>Create new <b>TOUR</b></a></p>--%>
                <table>
                    <%--                    <tr>--%>
                    <%--                        <th>Name</th>--%>
                    <%--                        <th>Type</th>--%>
                    <%--                        <th>Country</th>--%>
                    <%--                        <th>Price</th>--%>
                    <%--                    </tr>--%>
                    <c:forEach var="tour" items="${tours}">
                        <tr class="tour_view">
                            <td>
                                Name: ${tour.name}<br>
                                Type: ${tour.type}<br>
                                Country: ${tour.country}<br>
                                Price: ${tour.price}$
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
