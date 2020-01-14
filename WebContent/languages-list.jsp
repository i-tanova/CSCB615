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

	<input type="button" value="Add language"
		onclick="window.location.href='add-language.jsp'; return false;"
		class="add-student-button">
	</input>

	<div id="wrapper">
		<div id="header">
			<h2>Languages List</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
			<table>
				<tr>
					<th>Name</th>
					<th>Acronym</th>
					<th>Actions</th>
				</tr>

				<c:forEach var="temp" items="${LANGUAGES}">
					<c:url var="updateLink" value="LanguagesControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="id" value="${temp.id}" />
					</c:url>
					
					<c:url var="deleteLink" value="LanguagesControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="id" value="${temp.id}" />
					</c:url>
					<tr>
						<td>${temp.name}</td>
						<td>${temp.acronym}</td>
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