<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="View Tour"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>
<fieldset>
    <legend>
        Tour
    </legend>
    <label>Name: </label>
    <label>${tour.name}</label><br>

    <label>Type: </label>
    <label>${tour.type}</label><br>

    <label>Country: </label>
    <label>${tour.country}</label><br>

    <label>Price: </label>
    <label>${tour.price}</label><br>

    <label>Description: </label>
    <label>${tour.description}</label><br>

    <label>Number of person: </label>
    <label>${tour.count_people}</label><br>

    <label>Mark HOTEL: </label>
    <label>${tour.mark_hotel}</label><br>

    <label>Start Date: </label>
    <label>${tour.start_date}</label><br>

    <label>Days: </label>
    <label>${tour.days}</label><br>

    <label>Discount: </label>
    <label>${tour.discount}</label><br>
</fieldset>

<form action="controller" method="post">
    <fieldset>
        <legend>Your account</legend>
        <br>
        <label>
            <fmt:message key="account.label.username"/>
        </label>
        <p>${user.username}<br>
        <label>
            <fmt:message key="account.label.login"/>
        </label>
        <p>${user.login}<br>
        <label>
            <fmt:message key="account.label.phone_number"/>
        </label>
        <p>${user.phone_number}<br>
    </fieldset>
    <input type="hidden" name="command" value="registerTour"/>
    <input type="hidden" name="id" value="${tour.id}">
    <input type="submit" value="<fmt:message key="registrationTour.confirm"/>"/>
</form>
<br/>
</body>
</html>
