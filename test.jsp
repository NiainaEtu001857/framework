<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Votre page JSP</title>
</head>
<body>
    
    <%-- Vérifier si l'attribut existe --%>
    <% if (request.getAttribute("resp") != null) { %>
        <h1><%= request.getAttribute("id")%> </h1>
        <h1><%= request.getAttribute("valeur") %></h1>
    <% } else { %>
        <h1>L'attribut n'existe pas</h1>
    <% } %>
</body>
</html>
