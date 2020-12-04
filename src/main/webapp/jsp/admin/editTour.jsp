<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Edit Tour"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div id="main-container">
    <%@include file="/WEB-INF/jspf/header.jspf" %>
        <div class="container p-3 my-3 border">
            <h1><fmt:message key="tour.create.editTour"/></h1>
            <form action="/tours/admin/tour/edit" method="post">

                <label><fmt:message key="tour.create.nameEN"/></label>
                <input name="nameEN" value="${tour.nameEn}"/><br>
                <label><fmt:message key="tour.create.nameRU"/></label>
                <input name="nameRU" value="${tour.nameRu}"/><br>

                <label><fmt:message key="tour.create.typeEN"/></label>
                <select name="typeEN">
                    <c:forEach var="item" items="${typeTourOut}">
                        <option  ${typeDef.nameEn == item.nameEn ? 'selected' : ''}>${item.nameEn}</option>
                    </c:forEach>
                </select><br>
                <label><fmt:message key="tour.create.typeRU"/></label>
                <select name="typeRU">
                    <c:forEach var="item" items="${typeTourOut}">
                        <option  ${typeDef.nameRu == item.nameRu ? 'selected' : ''}>${item.nameRu}</option>
                    </c:forEach>
                </select><br>

                <label><fmt:message key="tour.create.countryEN"/></label>
                <select name="countryEN">
                    <c:forEach var="item" items="${countryOut}">
                        <option ${countryDef.nameEn == item.nameEn ? 'selected' : ''}>${item.nameEn}</option>
                    </c:forEach>
                </select><br>
                <label><fmt:message key="tour.create.countryRU"/></label>
                <select name="countryRU">
                    <c:forEach var="item" items="${countryOut}">
                        <option ${countryDef.nameRu == item.nameRu ? 'selected' : ''}>${item.nameRu}</option>
                    </c:forEach>
                </select><br>

                <label><fmt:message key="tour.price"/></label>
                <input name="price" value="${tour.price}" min="100"/><br>

                <label><fmt:message key="tour.create.descriptionEN"/></label>
                <input name="descriptionEN" value="${tour.descEn}"/><br>
                <label><fmt:message key="tour.create.descriptionRU"/></label>
                <input name="descriptionRU" value="${tour.descRu}"/><br>

                <label><fmt:message key="tour.count_people"/></label>
                <input name="count_people" value="${tour.countPeople}" min="-1"/><br>
                <label><fmt:message key="tour.mark_hotel"/></label>
                <input name="mark_hotel" value="${tour.markHotel}" min="-1"/><br>
                <label><fmt:message key="tour.start_date"/></label>
                <input type="date" name="start_date" value="${tour.startDate}"><br>
                <label><fmt:message key="tour.days"/></label>
                <input name="days" value="${tour.days}" min="0"><br>
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
        </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
