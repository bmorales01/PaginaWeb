<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
    <style>
        .text-center {
            text-align: center;
        }
        .container {
            padding: 30px; 
        }
    </style>
</head>
<body>
    <main class="container">
        <div class="row">

            <div class="col-12">
                <div class="card">
                    <div class="card-header text-center">
                        <h1>Bienvenido, Usuario!</h1>
                    </div>
                </div>
            </div>
        </div>

        <!-- Formulario de inicio de sesión -->
        <div class="row mt-4">
            <div class="col-12">
                <div class="card">
                    <div class="card-header text-center">
                        <h3>Iniciar Sesión</h3>
                    </div>
                    <div class="card-body">
                        <form action="Validar" method="POST">
                            <div class="form-group mb-3">
                                <label for="txtuser">Usuario:</label>
                                <input type="text" id="txtuser" name="txtuser" class="form-control" required>
                            </div>
                            <div class="form-group mb-3">
                                <label for="txtpass">Contraseña:</label>
                                <input type="password" id="txtpass" name="txtpass" class="form-control" required>
                            </div>
                            <button type="submit" name="accion" value="Ingresar" class="btn">Ingresar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
