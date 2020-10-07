<<%@ page pageEncoding="UTF-8" %>
<%@include file="WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Welcome" scope="page"/>
<%@include file="WEB-INF/jspf/head.jspf" %>
<body>
<form method="post" action='<c:url value="/controller" />' style="display:inline;">
    <input type="hidden" name="command" value="viewTours">
    <input class="submit" type="submit" value="Welcome">
</form>

</body>
</html>
