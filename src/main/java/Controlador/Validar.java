package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoBD;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Validar extends HttpServlet {
    private final EmpleadoBD edao = new EmpleadoBD();
    private static final String ACCION_INGRESAR = "Ingresar";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (ACCION_INGRESAR.equalsIgnoreCase(accion)) {
            String user = request.getParameter("txtuser");
            String pass = request.getParameter("txtpass");

           
            if (user == null || pass == null || user.isEmpty() || pass.isEmpty()) {
                request.setAttribute("errorMessage", "Usuario o contraseña no pueden estar vacíos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            Empleado empleado = edao.validar(user, pass);
            if (empleado != null) {
                request.getSession().setAttribute("usuario", empleado);
                response.sendRedirect("Controlador?menu=Principal");
            } else {
                request.setAttribute("errorMessage", "Datos incorrectos. Por favor, intente nuevamente.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
