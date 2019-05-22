<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Meals</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Meal site</span></h1>
        <nav>
            <ul>
                <li><a href="/home">Home</a></li>
                <li id="actual"><a href="/gerecht">View meals</a></li>
                <li><a href="/weekMenu">View menus</a></li>
                <li><a href="/gerecht/add">Add meal</a></li>
            </ul>
        </nav>
        <h2>Meals</h2>
    </header>
    <table>
        <thead>
            <th>Id</th>
            <th>Description</th>
            <th>Type</th>
            <th>Price</th>
            <th>Update</th>
            <th>Delete</th>
        </thead>
        <tbody>
        <c:forEach var="gerecht" items="${gerechten}">
            <tr>
                <td>${gerecht.id}</td>
                <td>${gerecht.description}</td>
                <td>${gerecht.type}</td>
                <td>${gerecht.price}</td>
                <td><a href="/gerecht/update/?id=${gerecht.id}">Update</a></td>
                <td><a href="/gerecht/delete/?id=${gerecht.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
</div>
</body>
</html>