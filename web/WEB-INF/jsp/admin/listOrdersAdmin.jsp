<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="List Orders" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">

            <form id="main" action="controller" method="post">

                <h1><fmt:message key="main.name"/></h1>
                <table>
                    <c:forEach var="order" items="${orders}">
                        <tr class="order_view">
                            <td>
                                Name tour: ${order.name}<br>
                                Login: ${order.login}<br>
                                Status: ${order.status}<br>
                                <details>
                                    <summary>
                                        More>>
                                    </summary>
                                    <fieldset>
                                        Price: ${order.price}<br>
                                        Count people: ${order.count_people}<br>
                                        Mark hotel: ${order.start_date}<br>
                                        Days: ${order.days}<br>
                                        Discount: ${order.discount}
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="updateDiscount">
                                            <input type="hidden" name="id" value="${order.tour_id}">
                                            <input name="discount" value="${order.discount}" max="1" min="0"/>
                                            <input type="submit" value="Save"/><br>
                                        </form>
                                    </fieldset>
                                </details>
                            </td>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="updateStatusOrder">
                                    <input type="hidden" name="id" value="${order.order_id}">
                                    <select name="selectStatus" onchange="submit()">
                                        <option value="registered" ${order.status == 'registered' ? 'selected' : ''}>
                                            registered
                                        </option>
                                        <option value="paid" ${order.status == 'paid' ? 'selected' : ''}>
                                            paid
                                        </option>
                                        <option value="canceled" ${order.status == 'canceled' ? 'selected' : ''}>
                                            canceled
                                        </option>
                                    </select>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </form>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
