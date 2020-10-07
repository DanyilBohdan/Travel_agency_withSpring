<%--@elvariable id="user" type="org.bohdan.db.entity.User"--%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Account"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <table id="main-container">
        <tr>
            <td class="content center">
                <form id="personalAcc" action="controller" method="post">

                    <input type="hidden" name="command" value="accountUser"/>

                    <fieldset >
                        <legend>
                            <fmt:message key="account.label.username"/>
                        </legend>
                        <p>${user.username}</p>
                    </fieldset><br/>

                    <fieldset>
                        <legend>
                            <fmt:message key="account.label.login"/>
                        </legend>
                        <p>${user.login}</p>
                    </fieldset><br/>

                    <fieldset>
                        <legend>
                            <fmt:message key="account.label.password"/>
                        </legend>
                        <p>${user.password}</p>
                    </fieldset><br/>

                    <fieldset>
                        <legend>
                            <fmt:message key="account.label.phone_number"/>
                        </legend>
                        <p>${user.phone_number}</p>
                    </fieldset><br/>

                    <fieldset>
                        <legend>
                            <fmt:message key="account.label.status"/>
                        </legend>
                        <p>
                            <c:if test="${user.status} == false">
                                <fmt:message key="account.label.block"/>
                            </c:if>
                        </p>
                    </fieldset><br/>
                    <fieldset>
                        <legend>
                            <fmt:message key="account.label.role"/>
                        </legend>
                        <p>${userRole}</p>
                    </fieldset><br/>
                </form>
            </td>
        </tr>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </table>
</body>
</html>
