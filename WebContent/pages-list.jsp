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
	<c:url var="addLink" value="PagesControllerServlet">
		<c:param name="command" value="ADD" />
	</c:url>


	<form action="PagesControllerServlet" method="get">
		<input type="hidden" name="command" value="GO_ADD" /> <input
			type="submit" value="Add page" class="add-student-button"> </input>
	</form>

	<div id="wrapper">
		<div id="header">
			<h2>Pages List</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
			<table>
				<tr>
					<th>Name</th>
					<th>Theme</th>
					<th>Language</th>
					<th>Page Type</th>
					<th>Date Created</th>
					<th>Action</th>
				</tr>

				<c:forEach var="temp" items="${PAGES}">
					<c:url var="updateLink" value="PagesControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="id" value="${temp.id}" />
					</c:url>

					<c:url var="deleteLink" value="PagesControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="id" value="${temp.id}" />
					</c:url>

					<c:url var="openLanguageLink" value="LanguagesControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="id" value="${temp.language.id}" />
					</c:url>

					<c:url var="openPageTypeLink" value="PageTypesControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="id" value="${temp.pageType.id}" />
					</c:url>

					<tr>
						<td>${temp.name}</td>
						<td>${temp.theme}</td>
						<td><a href="${openLanguageLink}">${temp.language.name}</a></td>
						<td><a href="${openPageTypeLink}">${temp.pageType.name}</a></td>
						<td>${temp.date}</td>
						<td><a href="${updateLink}">Update</a> | <a
							onclick="if(!(confirm('Are you shure?'))) return false"
							href="${deleteLink}">Delete</a></td>
					</tr>
				</c:forEach>


			</table>

		</div>
	</div>

	<p>

		<a href="LanguagesControllerServlet">Languages</a> <a
			href="PageTypesControllerServlet">Page Types</a>
	</p>

</body>
</html>