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
			<h2>Update Language</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
			<form action="LanguagesControllerServlet" method="get">
				<input type="hidden" name="command" value="UPDATE" />
				<input type="hidden" name="id" value="${language.id}" />
				
				<table>
					<tbody>
						<tr>
							<td><label>Name:</label></td>
							<td><input type="text" name="name" value="${language.name}"></td>
						</tr>

						<tr>
							<td><label>Acronym:</label></td>
							<td><input type="text" name="acronym" value="${language.acronym}"></td>
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

				<a href="LanguagesControllerServlet">Back</a>
			</p>
		</div>

	</div>
	</div>

</body>
</html>