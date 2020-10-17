<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Main" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="mainContainer">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <%@ include file="/WEB-INF/jspf/locale.jspf" %>
    <div class="container p-3 my-3 border">
        <h1><fmt:message key="account.admin.listTours"/></h1>

        <form action="controller" method="get">
            <input type="hidden" name="command" value="getCreateTour"/>
            <button type="submit" class="btn btn-outline-dark">
                <fmt:message key="account.admin.createNewTour"/>
            </button>
        </form>
        <div class="row">
            <form action="controller" method="get">
                <input type="hidden" name="command" value="listType"/>
                <button type="submit" class="btn btn-outline-dark">
                    <fmt:message key="account.admin.listTypeTour"/>
                </button>
            </form>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="listCountry"/>
                <button type="submit" class="btn btn-outline-dark">
                    <fmt:message key="account.admin.listCountry"/>
                </button>
            </form>
        </div>
        <div class="row">
            <form class="form-inline" method="post" action="controller">
                <input type="hidden" name="command" value="listTours">
                <input type="hidden" name="method" value="typeTour">
                <select name="searchType" onchange="submit()">
                    <c:forEach var="item" items="${typeTourOut}">
                        <option ${typeDef == item.name ? 'selected' : ''}>${item.name}</option>
                    </c:forEach>
                </select>
            </form>
            <form class="form-inline" method="post" action="controller">
                <input type="hidden" name="command" value="listTours">
                <input type="hidden" name="method" value="countryTour">
                <select name="searchCountry" onchange="submit()">
                    <c:forEach var="item" items="${countryOut}">
                        <option ${countryDef == item.name ? 'selected' : ''}>${item.name}</option>
                    </c:forEach>
                </select>
            </form>
            <form class="form-inline" method="post" action="controller">
                <input type="hidden" name="command" value="listTours">
                <input type="hidden" name="method" value="nameTour">
                <input type="text" name="searchText" class="form-control" value="${searchName}"
                       placeholder="<fmt:message key="search.placeholder"/>">
                <button type="submit" class="btn btn-outline-dark"><fmt:message key="search.button"/></button>
            </form>
        </div>
        <div class="container">
            <form class="form-inline" method="post" action="controller">
                <input type="hidden" name="command" value="listTours">
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
                <input type="hidden" name="command" value="listTours">
                <button type="submit" class="btn-outline-dark"><fmt:message key="search.reset"/></button>
            </form>
        </div>
        <div class="row row-cols-2 row-cols-md-2">
            <c:forEach var="tour" items="${tours}">
                <div class="card border-dark mb-4 h-100">
                    <div class="card-header">${tour.name}</div>
                    <div class="card-body text-dark">
                        <p class="card-text">
                            <fmt:message key="tour.country"/>: ${tour.country}<br>
                            <fmt:message key="tour.type"/>: ${tour.type}<br>
                            <fmt:message key="tour.price"/>: ${tour.price}<br>
                            <fmt:message key="tour.count_people"/>: ${tour.count_people}<br>
                            <fmt:message key="tour.mark_hotel"/>: ${tour.mark_hotel}<br>
                        </p>
                    </div>
                    <div class="card-footer bg-transparent border-dark">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="getEditTour">
                            <input type="hidden" name="id" value="${tour.id}">
                            <button type="submit" class="btn btn-outline-dark">
                                Edit
                            </button>
                        </form>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="deleteTour">
                            <input type="hidden" name="id" value="${tour.id}">
                            <button type="submit" class="btn btn-outline-danger">
                                Delete
                            </button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:forEach var="i" begin="1" end="${countPage}">
            <div class="btn-group mr-2" role="group" aria-label="First group">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="listTours">
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
