<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Account"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container p-3 my-3 border">
        <div class="border border-secondary">
            <label>
                <fmt:message key="account.label.username"/>:
            </label>    ${user.username}
        </div>
        <div class="border border-secondary">
            <label>
                <fmt:message key="account.label.login"/>:
            </label>    ${user.login}
        </div>
        <div class="border border-secondary">
            <label>
                <fmt:message key="account.label.password"/>:
            </label>    ${user.password}
        </div>
        <div class="border border-secondary">
            <label>
                <fmt:message key="account.label.phone_number"/>:
            </label>    ${user.phone_number}
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
            </label>    ${userRole}
        </div>

        <fieldset>
            <legend>
                <fmt:message key="account.legend.myListTours"/>
            </legend>
            <c:forEach var="order" items="${orders}">
                <div class="border border-secondary">
                    Name tour: ${order.name}<br>
                    Price: ${order.price}<br>
                    Status: ${order.status}<br>
                    <details>
                        <summary>
                            More>>
                        </summary>
                        <fieldset>
                            Count people: ${order.count_people}<br>
                            Mark hotel: ${order.start_date}<br>
                            Days: ${order.days}<br>
                            Discount: ${order.discount}<br>
                        </fieldset>
                    </details>
                </div>
            </c:forEach>
        </fieldset>
        <div class="container p-3 my-3 border">
            <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        </div>
</body>
</html>
