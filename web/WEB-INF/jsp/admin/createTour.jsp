<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<html>
<c:set var="title" value="Create Tour"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<h1>New Tour</h1>
<form method="post" >

    <label>NameEN</label>
    <input name="nameEN"/><br>
    <label>NameRU</label>
    <input name="nameRU"/><br>

    <label>TypeEN</label>
    <select name="typeEN">
        <c:forEach var="item" items="${typeTourOut}">
            <option>${item.name_en}</option>
        </c:forEach>
    </select><br>
    <label>TypeRU</label>
    <select name="typeRU">
        <c:forEach var="item" items="${typeTourOut}">
            <option>${item.name_ru}</option>
        </c:forEach>
    </select><br>

    <label>CountryEN</label>
    <select name="countryEN">
        <c:forEach var="item" items="${countryOut}">
            <option>${item.name_en}</option>
        </c:forEach>
    </select><br>
    <label>CountryRU</label>
    <select name="countryRU">
        <c:forEach var="item" items="${countryOut}">
            <option>${item.name_ru}</option>
        </c:forEach>
    </select><br>

    <label>Price</label>
    <input name="price" min="100"/><br>

    <label>DescriptionEN</label>
    <input name="descriptionEN"/><br>
    <label>DescriptionRU</label>
    <input name="descriptionRU"/><br>

    <label>Number of person</label>
    <input name="count_people" min="-1"/><br>
    <label>Mark HOTEL</label>
    <input name="mark_hotel" min="-1"/><br>
    <label>Start Date</label>
    <input type="date" name="start_date"/><br>
    <label>Days</label>
    <input name="days" min="0"/><br>
    <label>Discount</label>
    <input name="discount" min="0" max="1"/>
    <br><br>
    <input type="hidden" name="command" value="createTour">
    <input type="submit" value="Save"/>
</form>
</body>
</html>
