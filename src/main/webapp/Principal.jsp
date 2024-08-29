<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Principal</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <style>
            .welcome-message {
                font-size: 2rem;
                color: #fff;
                background-color: #17a2b8;
                padding: 20px;
                text-align: center;
                border-radius: 5px;
                margin: 20px 0;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
             .company-description {
                font-size: 1.2rem;
                color: #333;
                text-align: center;
                margin-bottom: 10px; 
                padding: 0 20px;
            }
        </style>
    </head>
    <body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                         <li class="nav-item">
                            <a class="btn btn-outline-light mx-2" href="#">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-outline-light mx-2" href="Controlador?menu=nuevaventa" target="myFrame">Nueva Venta</a>
                        </li>
                         <li class="nav-item">
                            <a class="btn btn-outline-light mx-2" href="Controlador?menu=Empleado&accion=listar" target="myFrame">Empleado</a>
                        </li>
                    </ul>
                    <div class="dropdown">
                        <button class="btn btn-danger dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            <c:out value="${usuario.nombres}" />
                        </button>
                        <ul class="dropdown-menu text-center" aria-labelledby="dropdownMenuButton1">
                            <li>
                                <img src="img/—Pngtree—user avatar placeholder black_6796227.png" alt="Usuario" width="30" class="ms-2"/>
                                <a class="dropdown-item" href="#">Usuario</a>
                                <a class="dropdown-item" href="#">usuario@gmail.com</a>
                            </li>
                            <li><a class="dropdown-item" href="#"><c:out value="${usuario.user}" /></a></li>
                            <li><hr class="dropdown-divider"></li>
                            <form action="Validar" method="POST">
                                <li><button name="accion" value="Salir" class="dropdown-item">Salir</button></li>
                            </form>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>

        <div class="welcome-message">
            ¡Bienvenido a Plásticos Guarenas, tu empresa de confianza!
        </div>
                             <div class="company-description">
            En Plásticos Guarenas, somos una empresa dedicada a la fabricación y distribución de productos plásticos de alta calidad. Nos enorgullece ofrecer soluciones innovadoras y eficientes para satisfacer las necesidades de nuestros clientes.
        </div>
                            

        <div class="m-4" style="height: 550px;">
            <iframe name="myFrame" style="height:100%; width: 100%; border: none"></iframe>
        </div>

        <footer class="bg-light py-3 mt-auto">
            <div class="container text-center">
                <p class="mb-0">© 2024 PLASTICOS GUARENAS</p>
            </div>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
