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
        <div class="container row">
            <form class="form-inline" method="post" action="controller">
                <input type="hidden" name="command" value="viewTours">
                <input type="hidden" name="method" value="typeTour">
                <select name="searchType" onchange="submit()">
                    <c:forEach var="item" items="${typeTourOut}">
                        <option ${typeDef == item.name ? 'selected' : ''}>${item.name}</option>
                    </c:forEach>
                </select>
            </form>
            <form class="form-inline" method="post" action="controller">
                <input type="hidden" name="command" value="viewTours">
                <input type="hidden" name="method" value="countryTour">
                <select name="searchCountry" onchange="submit()">
                    <c:forEach var="item" items="${countryOut}">
                        <option ${countryDef == item.name ? 'selected' : ''}>${item.name}</option>
                    </c:forEach>
                </select>
            </form>
            <form class="form-inline" method="post" action="controller">
                <input type="hidden" name="command" value="viewTours">
                <input type="hidden" name="method" value="nameTour">
                <input type="text" name="searchText" class="form-control" value="${searchName}"
                       placeholder="<fmt:message key="search.placeholder"/>">
                <button type="submit" class="btn btn-outline-dark"><fmt:message key="search.button"/></button>
            </form>
        </div>
        <div class="container">
            <form class="form-inline" method="post" action="controller">
                <input type="hidden" name="command" value="viewTours">
                <input type="hidden" name="method" value="rangeTour">
                <input type="text" name="searchBegin" placeholder="begin" value="${beginDef}">
                <input type="text" name="searchEnd" placeholder="end" value="${endDef}">
                <button type="submit" class="btn btn-outline-dark"><fmt:message key="search.button"/></button>
                <select id="search" name="searchSelect">
                    <option value="price" ${selectDef == "price" ? 'selected' : ''}><fmt:message
                            key="tour.price"/></option>
                    <option value="count_people" ${selectDef == "count_people" ? 'selected' : ''}><fmt:message
                            key="tour.count_people"/></option>
                    <option value="mark_hotel" ${selectDef == "mark_hotel" ? 'selected' : ''}><fmt:message
                            key="tour.mark_hotel"/></option>
                </select>
            </form>
        </div>
        <div class="container">
            <form class="form-inline" method="post" action="controller">
                <input type="hidden" name="command" value="viewTours">
                <button type="submit" class="btn-outline-dark"><fmt:message key="search.reset"/></button>
            </form>
        </div>
        <div class="row row-cols-3 row-cols-md-2">
            <c:forEach var="tour" items="${tours}">
                <div class="card border-dark mb-4 h-100">
                    <div class="card-header">${tour.name}</div>
                    <div class="card-body text-dark">
                        <p class="card-text">
                            <fmt:message key="tour.country"/>: ${tour.country}<br>
                            <fmt:message key="tour.type"/>: ${tour.type}<br>
                            <fmt:message key="tour.price"/>: ${tour.price}<br>
                            <fmt:message key="tour.start_date"/>: ${tour.startDate}<br>
                            <fmt:message key="tour.count_people"/>: ${tour.countPeople}<br>
                            <fmt:message key="tour.mark_hotel"/>: ${tour.markHotel}<br>
                        </p>
                    </div>
                    <div class="card-footer bg-transparent border-dark">
                        <form id="main" action="controller" method="post">
                            <input type="hidden" name="command" value="viewTour">
                            <input type="hidden" name="id" value="${tour.id}">
                            <button type="submit" class="btn btn-outline-dark" style="horiz-align: center">
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
                    <input type="hidden" name="method" value="${methodDef}">
                    <button class="btn btn-secondary" type="submit" name="page" value="${i}">${i}</button>
                </form>
            </div>
        </c:forEach>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
