<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Edit Tour"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">
    <%@include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content center">
            <h1><fmt:message key="tour.create.editTour"/></h1>
            <form action="controller" method="post">

                <label><fmt:message key="tour.create.nameEN"/></label>
                <input name="nameEN" value="${tour.name_en}"/><br>
                <label><fmt:message key="tour.create.nameRU"/></label>
                <input name="nameRU" value="${tour.name_ru}"/><br>

                <label><fmt:message key="tour.create.typeEN"/></label>
                <select name="typeEN">
                    <c:forEach var="item" items="${typeTourOut}">
                        <option  ${typeDef.name_en == item.name_en ? 'selected' : ''}>${item.name_en}</option>
                    </c:forEach>
                </select><br>
                <label><fmt:message key="tour.create.typeRU"/></label>
                <select name="typeRU">
                    <c:forEach var="item" items="${typeTourOut}">
                        <option  ${typeDef.name_ru == item.name_ru ? 'selected' : ''}>${item.name_ru}</option>
                    </c:forEach>
                </select><br>

                <label><fmt:message key="tour.create.countryEN"/></label>
                <select name="countryEN">
                    <c:forEach var="item" items="${countryOut}">
                        <option ${countryDef.name_en == item.name_en ? 'selected' : ''}>${item.name_en}</option>
                    </c:forEach>
                </select><br>
                <label><fmt:message key="tour.create.countryEN"/></label>
                <select name="countryRU">
                    <c:forEach var="item" items="${countryOut}">
                        <option ${countryDef.name_ru == item.name_ru ? 'selected' : ''}>${item.name_ru}</option>
                    </c:forEach>
                </select><br>

                <label><fmt:message key="tour.price"/></label>
                <input name="price" value="${tour.price}" min="100"/><br>

                <label><fmt:message key="tour.create.descriptionEN"/></label>
                <input name="descriptionEN" value="${tour.desc_en}"/><br>
                <label><fmt:message key="tour.create.descriptionRU"/></label>
                <input name="descriptionRU" value="${tour.desc_ru}"/><br>

                <label><fmt:message key="tour.count_people"/></label>
                <input name="count_people" value="${tour.count_people}" min="-1"/><br>
                <label><fmt:message key="tour.mark_hotel"/></label>
                <input name="mark_hotel" value="${tour.mark_hotel}" min="-1"/><br>
                <label><fmt:message key="tour.start_date"/></label>
                <input type="date" name="start_date" value="${tour.start_date}"><br>
                <label><fmt:message key="tour.days"/></label>
                <input name="days" value="${tour.days}" min="0"><br>
                <label><fmt:message key="tour.discount"/></label>
                <input name="discount" value="${tour.discount}" min="0" max="1">
                <br><br>
                <input type="hidden" name="command" value="editTour">
                <input type="hidden" name="id" value="${tour.id}">
                <button type="submit" class="btn btn-outline-dark">
                    <fmt:message key="save"/>
                </button>
                <c:if test="${not empty errorVal}">
                    <h5 class="text-light bg-danger">${errorVal}</h5>
                </c:if>
            </form>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
