<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="View Tour"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>

<div class="mainContainer">
    <%@include file="/WEB-INF/jspf/header.jspf" %>

    <form action="/order/register" method="post">
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
            <c:choose>
                <c:when test="${not empty noAvailable}">
                    <h5 class="text-light bg-danger">${noAvailable}</h5>
                </c:when>
                <c:otherwise>
                    <div>
                        <h6><fmt:message key="registrationTour.yourAc"/></h6>
                        <label><fmt:message key="account.label.username"/>: </label>
                            ${user.username}<br>
                        <label><fmt:message key="account.label.login"/>: </label>
                            ${user.login}<br>
                        <label><fmt:message key="account.label.phone_number"/>: </label>
                            ${user.phoneNumber}<br>
                    </div>
                    <input type="hidden" name="command" value="registerTour"/>
                    <input type="hidden" name="id" value="${tour.id}">
                    <button type="submit" class="btn btn-outline-dark"><fmt:message key="registrationTour.confirm"/></button>
                </c:otherwise>
            </c:choose>
        </div>
    </form>
    <br/>
</div>
</body>
</html>
