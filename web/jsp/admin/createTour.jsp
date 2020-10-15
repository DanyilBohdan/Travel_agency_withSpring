<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Create Tour"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content center">
            <h2><fmt:message key="tour.create.newTour"/></h2>

            <form action="controller" method="post">
                <label><fmt:message key="tour.create.nameEN"/></label>
                <input name="nameEN"/><br>
                <label><fmt:message key="tour.create.nameRU"/></label>
                <input name="nameRU"/><br>

                <label><fmt:message key="tour.create.typeEN"/></label>
                <select name="typeEN">
                    <c:forEach var="item" items="${typeTourOut}">
                        <option>${item.name_en}</option>
                    </c:forEach>
                </select><br>
                <label><fmt:message key="tour.create.typeRU"/></label>
                <select name="typeRU">
                    <c:forEach var="item" items="${typeTourOut}">
                        <option>${item.name_ru}</option>
                    </c:forEach>
                </select><br>

                <label><fmt:message key="tour.create.countryEN"/></label>
                <select name="countryEN">
                    <c:forEach var="item" items="${countryOut}">
                        <option>${item.name_en}</option>
                    </c:forEach>
                </select><br>
                <label><fmt:message key="tour.create.countryRU"/></label>
                <select name="countryRU">
                    <c:forEach var="item" items="${countryOut}">
                        <option>${item.name_ru}</option>
                    </c:forEach>
                </select><br>

                <label><fmt:message key="tour.price"/></label>
                <input name="price" min="100"/><br>

                <label><fmt:message key="tour.create.descriptionEN"/></label>
                <input name="descriptionEN"/><br>
                <label><fmt:message key="tour.create.descriptionRU"/></label>
                <input name="descriptionRU"/><br>

                <label><fmt:message key="tour.count_people"/></label>
                <input name="count_people" min="-1"/><br>
                <label><fmt:message key="tour.mark_hotel"/></label>
                <input name="mark_hotel" min="-1"/><br>
                <label><fmt:message key="tour.start_date"/></label>
                <input type="date" name="start_date"/><br>
                <label><fmt:message key="tour.days"/></label>
                <input name="days" min="0"/><br>
                <label><fmt:message key="tour.discount"/></label>
                <input name="discount" min="0" max="1"/>
                <br><br>
                <input type="hidden" name="command" value="createTour">
                <input type="submit" value="<fmt:message key="tour.create.button"/>"/>
            </form>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>