<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="View Tour"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div id="main-container">
    <%@include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container p-3 my-3 border">
        <h5>
            <fmt:message key="tour.title"/>
        </h5>
        <label><fmt:message key="tour.name"/>: </label>
        <label>${tour.name}</label><br>

        <label><fmt:message key="tour.type"/> : </label>
        <label>${tour.type}</label><br>

        <label><fmt:message key="tour.country"/>: </label>
        <label>${tour.country}</label><br>

        <label><fmt:message key="tour.price"/>: </label>
        <label>${tour.price}$</label><br>

        <label><fmt:message key="tour.description"/>: </label>
        <label>${tour.description}</label><br>

        <label><fmt:message key="tour.count_people"/>: </label>
        <label>${tour.countPeople}</label><br>

        <label><fmt:message key="tour.mark_hotel"/>: </label>
        <label>${tour.markHotel}</label><br>

        <label><fmt:message key="tour.start_date"/>: </label>
        <label>${tour.startDate}</label><br>

        <label><fmt:message key="tour.days"/>: </label>
        <label>${tour.days}</label><br>

        <label><fmt:message key="tour.discount"/>: </label>
        <label>${tour.discount}</label><br>
        <br/>
        <c:if test="${not empty user}">
            <form method="post" action="controller">
                <input type="hidden" name="command" value="registerTourView">
                <input type="hidden" name="id" value="${tour.id}">
                <button type="submit" class="btn btn-outline-dark"><fmt:message key="login_jsp.button.registration"/></button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
