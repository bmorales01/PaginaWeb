<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
        <title>Registrar Venta</title>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <form action="Controlador?menu=nuevaventa" method="POST">
                            <input type="hidden" name="menu" value="nuevaventa">
                            <div class="card-body p-4">
                                <c:if test="${not empty mensaje}">
                                    <div class="alert alert-danger">${mensaje}</div>
                                </c:if>

                                <!-- TABLA 1RA SECCION -->
                                <h5 class="card-title mt-4">Datos del Producto</h5>
                                <div class="form-group row mb-3">
                                    <div class="col-sm-4">
                                        <input type="number" name="idproducto" class="form-control" placeholder="ID Producto" required>
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="text" name="nombreproducto" class="form-control" value="${producto != null ? producto.getNombres() : ''}" readonly>
                                    </div>
                                    <div class="col-sm-4">
                                        <button type="submit" name="accion" value="BuscarProducto" class="btn btn-outline-info w-100">Buscar Producto</button>
                                    </div>
                                </div>

                                <!-- TABLA 2DA SECCION -->
                                <h5 class="card-title mt-4">Datos Adicionales</h5>
                                <div class="form-group row mb-3">
                                    <div class="col-sm-4">
                                        <input type="text" name="precio" class="form-control" value="${producto != null ? producto.getPrecio() : ''}" readonly>
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="number" name="cant" class="form-control" placeholder="Cantidad" min="1" required>
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="text" name="stock" class="form-control" value="${producto != null ? producto.getStock() : ''}" readonly>
                                    </div>
                                </div>

                                <!-- BOTON PARA AGREGAR EL PRODUCTO -->
                                <div class="form-group mt-4">
                                    <button type="submit" name="accion" value="Agregar" class="btn btn-outline-primary w-100">Agregar Producto</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- ESTA ES LA TABLA Y EL TOTAL -->
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body p-0">
                            <table class="table table-hover table-bordered table-responsive">
                                <thead>
                                    <tr class="text-center">
                                        <th>FACTURA</th>

                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr> 
                                        <td colspan="3" class="text-center"><strong>TOTAL COMPRA:</strong></td>
                                        <td>${totalVenta}</td>
                                    </tr>
                                </tfoot>

                            </table>
                        </div>
                        <div class="card-footer">
                            <!-- BOTON DE COMPRAR Y CANCELAR -->
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="Controlador?menu=nuevaventa&accion=GenerarVenta" class="btn btn-success w-100">Comprar</a>
                                </div>
                                <div class="col-sm-6">
                                    <button type="button" class="btn btn-danger w-100" onclick="window.location.href = 'Controlador?menu=nuevaventa&accion=Cancelar'">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
