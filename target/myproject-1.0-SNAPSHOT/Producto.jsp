<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
    <title>Productos</title>
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <!-- Formulario para buscar producto -->
            <div class="col-md-12">
                <div class="card">
                    <form action="Controlador?menu=Producto" method="POST">
                        <input type="hidden" name="menu" value="producto">
                        <div class="card-body p-4">
                            <h5 class="card-title">Buscar Producto</h5>
                            <div class="form-group row mb-3">
                                <div class="col-sm-4">
                                    <input type="text" name="codigoproducto" class="form-control" placeholder="Código Producto" required>
                                </div>
                                <div class="col-sm-2">
                                    <input type="submit" name="accion" value="Buscar" class="btn btn-outline-info ms-2">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Tabla de productos -->
            <div class="col-md-12 mt-4">
                <div class="card">
                    <div class="card-body p-0">
                        <table class="table table-hover table-bordered table-responsive">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Descripción</th>
                                    <th>Precio</th>
                                    <th>Stock</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Filas de la tabla aquí -->
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
