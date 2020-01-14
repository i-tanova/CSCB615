<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Language</title>
<link type="text/css" rel="stylesheet" href="css/style.css"></link>
<link type="text/css" rel="stylesheet" href="css/add-language-style.css"/>
</head>
<body>

<div id="wrapper">
      <div id="header">
       <h2>NBU Pages</h2>
      </div>
</div>

<div id="container">
<h3>Add Language</h3>

<form action="LanguagesControllerServlet" method="get">
  <input type="hidden" name="command" value="ADD" />
  
  <table>
  <tbody>
  <tr> 
  <td><label>Name:</label></td>
  <td><input type="text" name="name"></td>
  </tr>
  
    <tr> 
  <td><label>Acronym:</label></td>
  <td><input type="text" name="acronym"></td>
  </tr>
  
  <td></td>
  <td><input type="submit" value="Save" class="save"></td>
  </tr>
  
  </tbody>
  </table>

</form>

<div style="clear: both;"></div>
<p> 

<a href="LanguagesControllerServlet">Back</a>
</p>

</div>

</body>
</html>