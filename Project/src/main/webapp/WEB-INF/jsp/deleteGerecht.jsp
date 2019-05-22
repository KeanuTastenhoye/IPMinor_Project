<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Delete meal</title>
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
                <li><a href="/gerecht/add">Add meal</a></li>
            </ul>
        </nav>
        <h2>Delete meal</h2>
    </header>
    <c:if test="${errors!=null}">
        <div>
            <c:forEach items="${errors}" var="error">
                <p>${error.field}: ${error.defaultMessage}</p>
            </c:forEach>
        </div>
    </c:if>
    <p>Are you sure that you want to delete the following meal from the menu?</p>
    <p>Description: ${gerecht.description}</p>
    <p>Type: ${gerecht.type}</p>
    <p>Price: ${gerecht.price}</p>
    <form method="post" action="/gerecht/delete/?id=${gerecht.id}">
        <input type="submit" value="Yes">
    </form>
    <form method="get" action="/gerecht">
        <input type="submit" value="No">
    </form>
</div>
</body>
</html>