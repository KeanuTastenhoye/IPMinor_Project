<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Add meal</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Meal site</span></h1>
        <nav>
            <ul>
                <li><a href="/home">Home</a></li>
                <li><a href="/gerecht">View meals</a></li>
                <li><a href="/weekMenu">View menus</a></li>
                <li id="actual"><a href="/gerecht/add">Add meal</a></li>
            </ul>
        </nav>
        <h2>Add meal</h2>
    </header>
    <c:if test="${errors!=null}">
        <div>
            <c:forEach items="${errors}" var="error">
                <p>${error.field}: ${error.defaultMessage}</p>
            </c:forEach>
        </div>
    </c:if>
    <form method="post" action="/gerecht/add">
        <p>
            <label>Description</label>
            <input type="text" name="description" />
        </p>
        <p>
            <label>Price</label>
            <input type="number" name="price" step="0.01" />
        </p>
        <p>
            <label>Type</label>
            <select name="type">
                <option value="" disabled selected>Select type</option>
                <option value="Soep">Soep</option>
                <option value="Dagschotel">Dagschotel</option>
                <option value="Veggie">Veggie</option>
            </select>
        </p>
        <p>
            <input type="submit" value="Add"/>
            <input type="reset" value="Reset"/>
        </p>
    </form>
</div>
</body>
</html>