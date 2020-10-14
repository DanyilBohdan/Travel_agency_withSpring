<%@ page pageEncoding="UTF-8" %>
<%@include file="WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Main" scope="page"/>
<%@include file="WEB-INF/jspf/head.jspf" %>
<body>
<div class="mainContainer">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <%@ include file="/WEB-INF/jspf/locale.jspf" %>
    <div class="container p-3 my-3 border">
        <h1><fmt:message key="main.name"/></h1>
        <div class="row row-cols-1 row-cols-md-2">
            <c:forEach var="tour" items="${tours}">
                <div class="card border-dark mb-4 h-100">
                    <div class="card-header">${tour.name}</div>
                    <div class="card-body text-dark">
                        <p class="card-text">
                            <fmt:message key="tour.country"/>: ${tour.country}<br>
                            <fmt:message key="tour.type"/>: ${tour.type}<br>
                            <fmt:message key="tour.price"/>: ${tour.price}<br>
                        </p>
                    </div>
                    <div class="card-footer bg-transparent border-dark">
                        <form id="main" action="controller" method="post">
                            <input type="hidden" name="command" value="viewTour">
                            <input type="hidden" name="id" value="${tour.id}">
                            <button type="submit" class="btn btn-outline-dark">
                                <fmt:message key="tour.view"/>
                            </button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:forEach var="i" begin="1" end="${countPage}">
        <div class="btn-group mr-2" role="group" aria-label="First group">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="viewTours">
                <button class="btn btn-secondary" type="submit" name="page" value="${i}">${i}</button>
            </form>
        </div>
        </c:forEach>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
