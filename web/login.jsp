<%@include file="WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="WEB-INF/jspf/directive/page.jspf" %>
<html>
    <c:set var="title" value="LogIn"/>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body>
    <table id="main-container">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <tr>
            <td class="content center">
                <form id="login_form" action="controller" method="post">

                    <input type="hidden" name="command" value="login"/>

                    <fieldset >
                        <legend>
                            <fmt:message key="login_jsp.label.login"/>
                        </legend>
                        <input name="login"/><br/>
                    </fieldset><br/>
                    <fieldset>
                        <legend>
                            <fmt:message key="login_jsp.label.password"/>
                        </legend>
                        <input type="password" name="password"/>
                    </fieldset><br/>

                    <input type="submit" value='<fmt:message key="login_jsp.button.login"/>'><br>

                    <<fmt:message key="login_jsp.label.registration"/>
                </form>
            </td>
        </tr>

        <%@ include file="/WEB-INF/jspf/footer.jspf"%>

    </table>
    </body>
</html>
