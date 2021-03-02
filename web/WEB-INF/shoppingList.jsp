<%-- 
    Document   : shoppingList
    Created on : Feb 22, 2021, 7:16:31 PM
    Author     : 843876
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List Page</title>
    </head>
    <body>
        <form action="ShoppingListServlet" method="post">
            Hello, ${username} <a href="ShoppingListServlet?action=logout">Logout</a>
        </form>

        <form action="ShoppingListServlet" method="post">
            <h2>Add Item</h2>
            <input type="text" name="itemInput"><input type="submit" value="Add Item">
            <input type="hidden" name="action" value="add">
        </form>

        <form action="ShoppingListServlet" method="post">
            <table>
                <c:forEach var="item" items="${itemList}">
                    <tr>
                        <td><input type='radio' name='item' value='${item}'>${item}</td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
        </form>
    </body>
</html>
