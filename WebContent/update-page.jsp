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
	<div id="wrapper">
		<div id="header">
			<h2>Update Page</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
			<form action="PagesControllerServlet" method="get">
				<input type="hidden" name="command" value="UPDATE" /> <input
					type="hidden" name="id" value="${page.id}" />

				<table>
					<tbody>
						<tr>
							<td><label>Name:</label></td>
							<td><input type="text" name="name" value="${page.name}"></td>
						</tr>

						<tr>
							<td><label>Theme:</label></td>
							<td><input type="text" name="theme" value="${page.theme}"></td>
						</tr>

						<tr>
							<td><label>Language:</label></td>
							<td><select NAME="language">
									<c:forEach var="item" items="${LANGUAGES}">
										<option value="${item.id}"}
										${item.id == page.language.id ? 'selected' : ''}>
											<c:out value="${item.name}" />
										</option>
									</c:forEach>
							</select></td>
						</tr>


						<tr>
							<td><label>Page Type:</label></td>
							<td><select NAME="pageType">
									<c:forEach var="item" items="${PAGE_TYPES}">
										<option value="${item.id}"
										 ${item.id == page.pageType.id ? 'selected' : ''}>
											<c:out value="${item.name}" />
										</option>
									</c:forEach>
							</select></td>
						</tr>

						<tr>
							<td></td>
							<td><input type="submit" value="Update" class="save"></td>
						</tr>

					</tbody>
				</table>

			</form>

			<div style="clear: both;"></div>
			<p>

				<a href="PagesControllerServlet">Back</a>
			</p>
		</div>

	</div>
	</div>

</body>
</html>