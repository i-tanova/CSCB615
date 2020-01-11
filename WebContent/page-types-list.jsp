<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pages app</title>
<link type="text/css" rel="stylesheet" href="css/style.css"></link>
</head>
<body>

	<input type="button" value="Add page type"
		onclick="window.location.href='add-page-type.jsp'; return false;"
		class="add-student-button">
	</input>

	<div id="wrapper">
		<div id="header">
			<h2>Page Type List</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
			<table>
				<tr>
					<th>Name</th>
					<th>Metadata</th>
				</tr>

				<c:forEach var="temp" items="${PAGE_TYPES}">
					<c:url var="updateLink" value="PageTypesControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="id" value="${temp.id}" />
					</c:url>
					
					<c:url var="deleteLink" value="PageTypesControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="id" value="${temp.id}" />
					</c:url>
					<tr>
						<td>${temp.name}</td>
						<td>${temp.metadata}</td>
						<td><a href="${updateLink}">Update</a> 
						| 
						<a onclick="if(!(confirm('Are you shure?'))) return false" href="${deleteLink}">Delete</a></td>
					</tr>
				</c:forEach>


			</table>

		</div>
	</div>
	
	<p> 

<a href="PagesControllerServlet">Pages</a>
</p>

</body>
</html>