<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@taglib prefix="f" uri="http://library.web.bohdan.org/functionalTag" %>
<html>
<c:set var="title" value="Account"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="float-right">
        ${f:getDate()}
    </div>
    <%@ include file="/WEB-INF/jspf/locale.jspf" %>
    <div class="container p-3 my-3 border">
        <div class="container float-right ">
            <form id="editAcc" action="/user/account/edit/view" method="get">
                <input type="hidden" name="command" value="getEditAccount"/>
                <button type="submit" class="btn btn-outline-dark"><fmt:message
                        key="account.label.EditAccount"/></button>
            </form>
        </div>
        <div class="container float-left">
            <div class="border border-secondary">
                <label>
                    <fmt:message key="account.label.username"/>:
                </label> ${user.username}
            </div>
            <div class="border border-secondary">
                <label>
                    <fmt:message key="account.label.login"/>:
                </label> ${user.login}
            </div>
            <div class="border border-secondary">
                <label>
                    <fmt:message key="account.label.password"/>:
                </label> ${user.password}
            </div>
            <div class="border border-secondary">
                <label>
                    <fmt:message key="account.label.phone_number"/>:
                </label> ${user.phoneNumber}
            </div>
            <c:if test="${user.status} == false">
                <div class="border border-secondary">
                    <label>
                        <fmt:message key="account.label.status"/>:
                    </label><fmt:message key="account.label.block"/>
                </div>
            </c:if>
            <div class="border border-secondary">
                <label>
                    <fmt:message key="account.label.role"/>:
                </label> ${userRole}
            </div>
        </div>

        <div class="container p-3 my-3">
            <label>
                <fmt:message key="account.legend.myListTours"/>
            </label>
            <c:forEach var="order" items="${orders}">
                <div class="border border-secondary">
                    <div class="float-right ">
                        <form id="statusCanceled" action="/order/canceled" method="post">
                            <input type="hidden" name="command" value="statusCanceled"/>
                            <input type="hidden" name="id" value="${order.orderId}"/>
                            <button type="submit" class="btn btn-outline-dark" name="canceled" value="canceled">
                                <fmt:message key="account.label.canceled"/></button>
                        </form>
                    </div>
                    <fmt:message key="tour.name"/>: ${order.name}<br>
                    <fmt:message key="tour.price"/>: ${order.price}<br>
                    <fmt:message key="tour.start_date"/>: ${order.startDate}<br>
                    <fmt:message key="account.label.status"/>: ${order.status}<br>
                    <details>
                        <summary>
                            <fmt:message key="order.more"/>
                        </summary>
                        <fieldset>
                            <fmt:message key="tour.count_people"/>: ${order.countPeople}<br>
                            <fmt:message key="tour.days"/>: ${order.days}<br>
                            <fmt:message key="tour.discount"/>: ${order.discount}<br>
                        </fieldset>
                    </details>
                </div>
            </c:forEach>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
</body>
</html>
