<%@include file="WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="LogIn"/>
<%@include file="WEB-INF/jspf/head.jspf" %>
<body>

<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">

            <form id="view" action="controller" method="post">
                <input type="hidden" name="command" value="viewTours"/>

                <h1>Tour Agency</h1>
                <label>
                    <select name="locale">
                        <option selected value="EN">EN</option>
                        <option value="RU">RU</option>
                    </select>
                </label>
                <p><a href='<c:url value="/create" />'>Create new <b>TOUR</b></a></p>
                <table class="tour_view">
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Country</th>
                        <th>Price</th>
                    </tr>
                    <c:forEach var="tour" items="${tours}">
                        <tr>
                            <td>${tour.name}</td>
                            <td>${tour.type}</td>
                            <td>${tour.country}</td>
                            <td>${tour.price}$</td>
                            <td>
                                <a href='<c:url value="/edit?id=${tour.id}" />'>Edit</a> |
                                <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                                    <input type="hidden" name="id" value="${tour.id}">
                                    <input type="submit" value="Delete">
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
