<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@taglib prefix="f" uri="http://library.web.bohdan.org/functionalTag" %>
<html>
<c:set var="title" value="${checkRegistration}"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <br>
    <label>
        <b>
            ${checkRegistration}
        </b>
    </label><br><br>
    <form action="/tours/view" method="post">
        <input type="hidden" name="command" value="viewTours">
        <button type="submit" class="btn btn-outline-dark">
            <fmt:message key="header.main"/>
        </button>
    </form>
</div>
</body>
</html>
