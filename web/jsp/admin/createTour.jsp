<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Create Tour"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div id="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container p-3 my-3 border">
        <h2><fmt:message key="tour.create.newTour"/></h2>
        <form action="controller" method="post">

            <%--            <label><fmt:message key="tour.create.nameEN"/></label>--%>
            <%--            <input name="nameEN"/><br>--%>
            <%--            <label><fmt:message key="tour.create.nameRU"/></label>--%>
            <%--            <input name="nameRU"/><br>--%>

            <%--            <label><fmt:message key="tour.create.typeEN"/></label>--%>
            <%--            <select name="typeEN">--%>
            <%--                <c:forEach var="item" items="${typeTourOut}">--%>
            <%--                    <option>${item.name_en}</option>--%>
            <%--                </c:forEach>--%>
            <%--            </select><br>--%>
            <%--            <label><fmt:message key="tour.create.typeRU"/></label>--%>
            <%--            <select name="typeRU">--%>
            <%--                <c:forEach var="item" items="${typeTourOut}">--%>
            <%--                    <option>${item.name_ru}</option>--%>
            <%--                </c:forEach>--%>
            <%--            </select><br>--%>

            <%--            <label><fmt:message key="tour.create.countryEN"/></label>--%>
            <%--            <select name="countryEN">--%>
            <%--                <c:forEach var="item" items="${countryOut}">--%>
            <%--                    <option>${item.name_en}</option>--%>
            <%--                </c:forEach>--%>
            <%--            </select><br>--%>
            <%--            <label><fmt:message key="tour.create.countryRU"/></label>--%>
            <%--            <select name="countryRU">--%>
            <%--                <c:forEach var="item" items="${countryOut}">--%>
            <%--                    <option>${item.name_ru}</option>--%>
            <%--                </c:forEach>--%>
            <%--            </select><br>--%>

            <%--            <label><fmt:message key="tour.price"/></label>--%>
            <%--            <div class="input-group">--%>
            <%--                <span class="input-group-text">$</span>--%>
            <%--                <input name="price" min="100"/><br>--%>
            <%--            </div>--%>

            <%--            <div class="input-group">--%>
            <%--                <div class="input-group-prepend">--%>
            <%--                    <span class="input-group-text"><fmt:message key="tour.create.descriptionEN"/></span>--%>
            <%--                </div>--%>
            <%--                <textarea name="descriptionEN" class="form-control"--%>
            <%--                          aria-label="<fmt:message key="tour.create.descriptionEN"/>"></textarea>--%>
            <%--            </div>--%>
            <%--            <div class="input-group">--%>
            <%--                <div class="input-group-prepend">--%>
            <%--                    <span class="input-group-text"><fmt:message key="tour.create.descriptionRU"/></span>--%>
            <%--                </div>--%>
            <%--                <textarea name="descriptionRU" class="form-control"--%>
            <%--                          aria-label="<fmt:message key="tour.create.descriptionRU"/>"></textarea>--%>
            <%--            </div>--%>

            <%--            <label><fmt:message key="tour.count_people"/></label>--%>
            <%--            <input name="count_people" min="-1"/><br>--%>
            <%--            <label><fmt:message key="tour.mark_hotel"/></label>--%>
            <%--            <input name="mark_hotel" min="-1"/><br>--%>
            <%--            <label><fmt:message key="tour.start_date"/></label>--%>
            <%--            <input type="date" name="start_date"/><br>--%>
            <%--            <label><fmt:message key="tour.days"/></label>--%>
            <%--            <input name="days" min="0"/><br>--%>
            <%--            <label><fmt:message key="tour.discount"/></label>--%>
            <%--            <input name="discount" min="0" max="1"/>--%>
            <%--            <br><br>--%>
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

            <input type="hidden" name="command" value="createTour">
            <button type="submit" class="btn btn-outline-dark"><fmt:message key="tour.create.button"/></button>
            <c:if test="${not empty errorVal}">
                <h5 class="text-light bg-danger">${errorVal}</h5>
            </c:if>
        </form>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>