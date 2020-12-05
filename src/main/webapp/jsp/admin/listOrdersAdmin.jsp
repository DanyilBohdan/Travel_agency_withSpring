<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="List Orders" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <h1><fmt:message key="account.admin.listOrders"/></h1>
    <div class="container p-3 my-3 border">
        <div class="row">
            <form class="form-inline" method="post" action="/order/view/searchByStatus">
                <input type="hidden" name="command" value="searchByStatusOrder">
                <select name="searchStatus" onchange="submit()">
                    <option value="registered" ${selectDef == "registered" ? 'selected' : ''}><fmt:message
                            key="order.status.registered"/></option>
                    <option value="paid" ${selectDef == "paid" ? 'selected' : ''}><fmt:message
                            key="order.status.paid"/></option>
                    <option value="canceled" ${selectDef == "canceled" ? 'selected' : ''}><fmt:message
                            key="order.status.canceled"/></option>
                </select>
            </form>
            <form action="/orders/view" method="post">
                <input type="hidden" name="command" value="listOrders"/>
                <button type="submit" class="btn btn-outline-dark"><fmt:message
                        key="search.reset"/></button>
            </form>
        </div>
        <c:forEach var="order" items="${orders}">
        <div class="border border-secondary">
            <fmt:message key="tour.name"/>: ${order.name}<br>
            <fmt:message key="account.label.login"/>: ${order.login}<br>
            <fmt:message key="account.label.status"/>: ${order.status}<br>
            <details>
                <summary>
                    <fmt:message key="order.more"/>>>
                </summary>
                <fieldset>
                    <fmt:message key="tour.price"/>: ${order.price}<br>
                    <fmt:message key="tour.count_people"/>: ${order.countPeople}<br>
                    <fmt:message key="tour.mark_hotel"/>: ${order.startDate}<br>
                    <fmt:message key="tour.days"/>: ${order.days}<br>
                    <fmt:message key="tour.discount"/>:
                    <form action="/order/view/update/discount" method="post">
                        <input type="hidden" name="command" value="updateDiscount">
                        <input type="hidden" name="id" value="${order.tourId}">
                        <input name="discount" value="${order.discount}" max="1" min="0"/>
                        <input type="submit" value="Save"/><br>
                    </form>
                </fieldset>
            </details>
            <div class="rightContainer">
                <form action="/order/view/update/status" method="post">
                    <input type="hidden" name="command" value="updateStatusOrder">
                    <input type="hidden" name="id" value="${order.orderId}">
                    <select name="selectStatus" onchange="submit()">
                        <option value="registered" ${order.status == 'registered' ? 'selected' : ''}>
                            <fmt:message key="order.status.registered"/>
                        </option>
                        <option value="paid" ${order.status == 'paid' ? 'selected' : ''}>
                            <fmt:message key="order.status.paid"/>
                        </option>
                        <option value="canceled" ${order.status == 'canceled' ? 'selected' : ''}>
                            <fmt:message key="order.status.canceled"/>
                        </option>
                    </select>
                </form>
                <form action="/order/view/delete" method="post">
                    <input type="hidden" name="command" value="deleteOrder">
                    <input type="hidden" name="id" value="${order.orderId}">
                    <button type="submit" class="btn btn-outline-danger">
                        Delete
                    </button>
                </form>
            </div>
            </c:forEach>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
</body>
</html>
