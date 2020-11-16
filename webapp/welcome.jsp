<%@ page pageEncoding="UTF-8" %>
<%@include file="WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Welcome" scope="page"/>
<%@include file="WEB-INF/jspf/head.jspf" %>
<body>
<div class="container p-3 my-3 border">
    <form method="post" action="controller/tour/view" >
        <input type="hidden" name="command" value="viewTours">
        <input type="hidden" name="lang" value="EN">
        <button type="submit" class="btn btn-outline-dark btn-lg btn-block">
            WELCOME
        </button>
    </form>
</div>
</body>
</html>
