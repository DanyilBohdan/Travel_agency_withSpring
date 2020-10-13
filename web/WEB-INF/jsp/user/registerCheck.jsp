<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="${checkRegistration}"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content center">
            <%@include file="/WEB-INF/jspf/header.jspf" %>
            <label>
                <b>
                    ${checkRegistration}
                </b>
            </label>
        </td>
    </tr>
</table>
</body>
</html>
