<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Menus</title>
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
                <li id="actual"><a href="/weekMenu">View menus</a></li>
                <li><a href="/gerecht/add">Add meal</a></li>
            </ul>
        </nav>
        <h2>Menus</h2>
    </header>
</div>
</body>
</html>