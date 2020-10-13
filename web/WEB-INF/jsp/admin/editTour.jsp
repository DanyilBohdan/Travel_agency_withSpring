<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Edit Tour"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">
    <%@include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content center">
            <h1>Edit Tour</h1>
            <form action="controller" method="post">

                <label>NameEN</label>
                <input name="nameEN" value="${tour.name_en}"/><br>
                <label>NameRU</label>
                <input name="nameRU" value="${tour.name_ru}"/><br>

                <label>TypeEN</label>
                <select name="typeEN">
                    <c:forEach var="item" items="${typeTourOut}">
                        <option  ${typeDef.name_en == item.name_en ? 'selected' : ''}>${item.name_en}</option>
                    </c:forEach>
                </select><br>
                <label>TypeRU</label>
                <select name="typeRU">
                    <c:forEach var="item" items="${typeTourOut}">
                        <option  ${typeDef.name_ru == item.name_ru ? 'selected' : ''}>${item.name_ru}</option>
                    </c:forEach>
                </select><br>

                <label>CountryEN</label>
                <select name="countryEN">
                    <c:forEach var="item" items="${countryOut}">
                        <option ${countryDef.name_en == item.name_en ? 'selected' : ''}>${item.name_en}</option>
                    </c:forEach>
                </select><br>
                <label>CountryRU</label>
                <select name="countryRU">
                    <c:forEach var="item" items="${countryOut}">
                        <option ${countryDef.name_ru == item.name_ru ? 'selected' : ''}>${item.name_ru}</option>
                    </c:forEach>
                </select><br>

                <label>Price</label>
                <input name="price" value="${tour.price}" min="100"/><br>

                <label>DescriptionEN</label>
                <input name="descriptionEN" value="${tour.desc_en}"/><br>
                <label>DescriptionRU</label>
                <input name="descriptionRU" value="${tour.desc_ru}"/><br>

                <label>Number of person</label>
                <input name="count_people" value="${tour.count_people}" min="-1"/><br>
                <label>Mark HOTEL</label>
                <input name="mark_hotel" value="${tour.mark_hotel}" min="-1"/><br>
                <label>Start Date</label>
                <input type="date" name="start_date" value="${tour.start_date}"><br>
                <label>Days</label>
                <input name="days" value="${tour.days}" min="0"><br>
                <label>Discount</label>
                <input name="discount" value="${tour.discount}" min="0" max="1">
                <br><br>
                <input type="hidden" name="command" value="editTour">
                <input type="hidden" name="id" value="${tour.id}">
                <input type="submit" value="Save">
            </form>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
