<%-- 
    Document   : home
    Created on : 5 thg 6, 2024, 21:37:09
    Author     : mb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Manager</title>
    </head>
    <body>
        <h1>Student Manager:</h1>
        <form action="NewServlet" >
            <label for="name">Input Student Name: </label>
            <input type="text" id="name" name="name"><br><br>
            <input type="submit" value="Submit">
        </form>
        
        
<!--------------------------VIEW-------------------------------------------->
        <h2>List of Students:</h2>
        <h3>Request Scope:</h3>
        <c:forEach items="${requestScope.listByRequest}" var="r" >    
            ${r}             
        </c:forEach>
                    
        <h3>Session Scope:</h3>
        <c:forEach items="${sessionScope.listBySession}" var="s" >    
            ${s}                   
        </c:forEach>
                    
        <h3>Cookie Scope:</h3>
        
        <c:forEach items="${requestScope.listByCookie}" var="c" >    
            ${c}          
        </c:forEach>
<!            ${cookie.listByCookie.value}>

    </body>
</html>