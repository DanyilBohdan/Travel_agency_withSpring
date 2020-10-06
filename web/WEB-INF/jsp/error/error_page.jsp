<%@page isErrorPage="false" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
    <c:set var="title" value="Error" scope="page"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <table id="main-content">
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <tr>
            <td class="content">
                <h2 class="error">
                    The following error occurred
                </h2>

            </td>
        </tr>
        <%@include file="/WEB-INF/jspf/footer.jspf"%>
    </table>
</body>
</html>
