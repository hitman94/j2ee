<%-- 
    Document   : form
    Created on : 26 janv. 2015, 16:41:46
    Author     : fguif
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="jquery-1.11.2.min.js"></script>
        <script src="control.js"></script>
    </head>
    <body>
        <form name="form" method="post"  >
            Name <input id="name" type="text" name="name" value="" /><br/>
            Prenom <input id="prenom" type="text" name="prenom" value="" /> <br/>
            Email <input id="email" type="text" name="email" value="" /><br/>
            sex <input id ="homme" type="radio" name="sex" value="Homme" checked="checked" disabled="disabled" />
            <input id ="femme" type="radio" name="sex" value="Femme" /><br/>
            number <input id="number" type="text" name="numTel" value="" /><br/>
            country <input id="pays" type="text" name="country" value="" /><br/>
            street <input id="street" type="text" name="street" value="" /> 
            <input id="submit_button"  type="submit" value="validate" name="validate-button" />
        </form>
    </body>
</html>
