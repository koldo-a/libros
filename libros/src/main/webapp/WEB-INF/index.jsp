<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page
	import="com.ipartek.formacion.libros.accesodatos.LibroAccesoDatos,com.ipartek.formacion.libros.dtos.LibroDTO,java.util.ArrayList"%>

<html>
<head>
<title>Listado de libros</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/index.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body>

	<h1>Listado de libros</h1>
	<div class="wrapper">
		<ul class="list-group">

			<%
			ArrayList<LibroDTO> libros = (ArrayList<LibroDTO>) LibroAccesoDatos.obtenerTodosLibros();

			// Verificar si la lista de libros no está vacía antes de recorrerla
			if (libros != null && !libros.isEmpty()) {
				for (LibroDTO libro : libros) {
			%>
			<li class="list-group-item list-group-item-success">
				<div class="titulo">Título:</div> <%=libro.titulo()%><br> <!-- Puedes mostrar más detalles del libro si lo necesitas -->
			</li>
			<%
			}
			} else {
			%>
			<li>No se encontraron libros.</li>
			<%
			}
			%>
		</ul>
		<ul class="list-group">
			<%
			ArrayList<LibroDTO> libros2 = (ArrayList<LibroDTO>) LibroAccesoDatos.obtenerTodos();

			// Recorrer la lista de libros y mostrar los detalles de cada libro
			for (LibroDTO libro : libros2) {
			%>
			<li class="list-group-item list-group-item-info">
				<div class="titulo">Título:</div> <%=libro.titulo()%><br> ISBN:
				<%=libro.isbn()%><br>
				<div class="autor">
					Autor:
					<%=libro.autor().nombre()%>
					<%=libro.autor().apellidos()%></div> <br> <!-- Puedes mostrar más detalles del libro si lo necesitas -->
			</li>
			<%
			}
			%>
		</ul>
	</div>
</body>
</html>
