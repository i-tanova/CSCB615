<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Page</title>
<link type="text/css" rel="stylesheet" href="css/style.css"></link>
<link type="text/css" rel="stylesheet" href="css/add-language-style.css"/>
</head>
<body>

<div id="container">
<h3>Add Page</h3>

<form action="PagesControllerServlet" method="get">
  <input type="hidden" name="command" value="ADD" />
  
  <table>
  <tbody>
  <tr> 
  <td><label>Name:</label></td>
  <td><input type="text" name="name"></td>
  </tr>
  
    <tr> 
  <td><label>Theme:</label></td>
  <td><input type="text" name="theme"></td>
  </tr>
  
  <tr>
  <td><label>Language:</label></td>
  <td>
  
  <select NAME="language">
              <c:forEach var="item" items="${LANGUAGES}">
                <option value="${item.id}" }>
                  <c:out value="${item.name}" />
                </option>
              </c:forEach>
            </select>
  
  </td>
  </tr>
  
  <tr>
  <td><label>Page Type:</label></td>
  <td>
  
  <select NAME="pageType">
              <c:forEach var="item" items="${PAGE_TYPES}">
                <option value="${item.id}" >
                  <c:out value="${item.name}" />
                </option>
              </c:forEach>
            </select>
  
  </td>
  </tr>
  
  <tr>
  <td></td>
  <td><input type="submit" value="Save" class="save"></td>
  </tr>
  
  </tbody>
  </table>

</form>

<div style="clear: both;"></div>
<p> 

<a href="PagesControllerServlet">Back</a>
</p>

</div>

</body>
</html>