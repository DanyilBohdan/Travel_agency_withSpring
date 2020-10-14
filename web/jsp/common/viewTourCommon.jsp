<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="View Tour"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div id="main-container">
    <%@include file="/WEB-INF/jspf/header.jspf" %>
            <fieldset>
                <legend>
                    <fmt:message key="tour.title"/>
                </legend>
                <label><fmt:message key="tour.name"/>: </label>
                <label>${tour.name}</label><br>

                <label><fmt:message key="tour.type"/>: </label>
                <label>${tour.type}</label><br>

                <label><fmt:message key="tour.country"/>: </label>
                <label>${tour.country}</label><br>

                <label><fmt:message key="tour.price"/>: </label>
                <label>${tour.price}</label><br>

                <label><fmt:message key="tour.description"/>: </label>
                <label>${tour.description}</label><br>

                <label><fmt:message key="tour.count_people"/>: </label>
                <label>${tour.count_people}</label><br>

                <label><fmt:message key="tour.mark_hotel"/>: </label>
                <label>${tour.mark_hotel}</label><br>

                <label><fmt:message key="tour.start_date"/>: </label>
                <label>${tour.start_date}</label><br>

                <label><fmt:message key="tour.days"/>: </label>
                <label>${tour.days}</label><br>

                <label><fmt:message key="tour.discount"/>: </label>
                <label>${tour.discount}</label><br>
            </fieldset>
            <br/>

            <c:if test="${not empty user}">
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="registerTourView">
                    <input type="hidden" name="id" value="${tour.id}">
                    <input class="submit" type="submit" value="<fmt:message key="login_jsp.button.registration"/>">
                </form>
            </c:if>
</div>
</body>
</html>
