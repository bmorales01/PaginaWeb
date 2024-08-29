<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.Empleado" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <title>Empleado</title>
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <!-- Mensaje de Estado -->
            <div class="col-12">
                <%
                    String mensaje = (String) request.getAttribute("mensaje");
                    if (mensaje != null && !mensaje.isEmpty()) {
                %>
                <div class="alert alert-info" role="alert">
                    <%= mensaje %>
                </div>
                <%
                    }
                %>
            </div>
            <!-- Formulario -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header bg-info text-white">
                        <h4 class="mb-0">Formulario de Registro</h4>
                    </div>
                    <div class="card-body">
                        <form action="Controlador?menu=Empleado" method="post">
                            <input type="hidden" name="txtid" value="<%= request.getAttribute("empleado") != null ? ((Empleado) request.getAttribute("empleado")).getId() : "" %>"> <!-- Campo oculto para el ID -->
                            <div class="form-group">
                                <label for="cedula">Cédula</label>
                                <input type="text" value="<%= request.getAttribute("empleado") != null ? ((Empleado) request.getAttribute("empleado")).getCedula() : "" %>" id="cedula" name="txtcedula" class="form-control" placeholder="Ingrese su cédula" required>
                            </div>
                            <div class="form-group">
                                <label for="nombres">Nombres</label>
                                <input type="text" value="<%= request.getAttribute("empleado") != null ? ((Empleado) request.getAttribute("empleado")).getNombres() : "" %>" id="nombres" name="txtnombres" class="form-control" placeholder="Ingrese sus nombres" required>
                            </div>
                            <div class="form-group">
                                <label for="telefono">Teléfono</label>
                                <input type="text" value="<%= request.getAttribute("empleado") != null ? ((Empleado) request.getAttribute("empleado")).getTelefono() : "" %>" id="telefono" name="txttelefono" class="form-control" placeholder="Ingrese su teléfono" required>
                            </div>
                            <div class="form-group">
                                <label for="usuario">Usuario</label>
                                <input type="text" value="<%= request.getAttribute("empleado") != null ? ((Empleado) request.getAttribute("empleado")).getUser() : "" %>" id="usuario" name="txtusuario" class="form-control" placeholder="Ingrese su nombre de usuario" required>
                            </div>

                            <%
                                Empleado empleado = (Empleado) request.getAttribute("empleado");
                                if (empleado == null || empleado.getId() == 0) {
                            %>
                            <button type="submit" name="accion" value="agregar" class="btn btn-info btn-block">Agregar</button>
                            <%
                            } else {
                            %>
                            <button type="submit" name="accion" value="actualizar" class="btn btn-warning btn-block">Actualizar</button>
                            <%
                                }
                            %>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Tabla -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header bg-info text-white">
                        <h4 class="mb-0">Lista de Empleados</h4>
                    </div>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Cédula</th>
                                    <th>Nombres</th>
                                    <th>Teléfono</th>
                                    <th>Usuario</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
                                    if (empleados != null && !empleados.isEmpty()) {
                                        for (Empleado emp : empleados) {
                                %>
                                <tr>
                                    <td><%= emp.getId() %></td>
                                    <td><%= emp.getCedula() %></td>
                                    <td><%= emp.getNombres() %></td>
                                    <td><%= emp.getTelefono() %></td>
                                    <td><%= emp.getUser() %></td>
                                    <td>
                                        <a href="Controlador?menu=Empleado&accion=editar&id=<%= emp.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                                        <a href="Controlador?menu=Empleado&accion=delete&id=<%= emp.getId() %>" class="btn btn-danger btn-sm">Eliminar</a>
                                    </td>
                                </tr>
                                <%
                                        }
                                    } else {
                                %>
                                <tr>
                                    <td colspan="6" class="text-center">No hay empleados disponibles</td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table> 
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>