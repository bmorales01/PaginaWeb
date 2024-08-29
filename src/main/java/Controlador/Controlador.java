package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoBD;
import Modelo.Producto;
import Modelo.DetalleVenta;
import Modelo.ProductoBD;
import Modelo.Venta;
import Modelo.VentaBD;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Controlador extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Controlador.class.getName());

    private final EmpleadoBD edao = new EmpleadoBD();
    private final ProductoBD productoBD = new ProductoBD();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu != null) {
            switch (menu.toLowerCase()) {
                case "principal":
                    request.getRequestDispatcher("Principal.jsp").forward(request, response);
                    break;
                case "empleado":
                    handleEmpleadoActions(request, response, accion);
                    break;
                case "producto":
                    request.getRequestDispatcher("Producto.jsp").forward(request, response);
                    break;
                case "nuevaventa":
                    handleNuevaVentaActions(request, response, accion);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    private void handleEmpleadoActions(HttpServletRequest request, HttpServletResponse response, String accion) throws ServletException, IOException {
        if (accion != null) {
            switch (accion.toLowerCase()) {
                case "listar":
                    List<Empleado> lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                    break;
                case "agregar":
                    agregarEmpleado(request, response);
                    break;
                case "editar":
                    editarEmpleado(request, response);
                    break;
                case "actualizar":
                    actualizarEmpleado(request, response);
                    break;
                case "delete":
                    eliminarEmpleado(request, response);
                    break;
                default:
                    request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                    break;
            }
        } else {
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
    }

    private void agregarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cedula = request.getParameter("txtcedula");
        String nom = request.getParameter("txtnombres");
        String tel = request.getParameter("txttelefono");
        String user = request.getParameter("txtusuario");

        if (cedula != null && !cedula.isEmpty() && nom != null && !nom.isEmpty() && tel != null && !tel.isEmpty() && user != null && !user.isEmpty()) {
            Empleado emp = new Empleado();
            emp.setCedula(cedula);
            emp.setNombres(nom);
            emp.setTelefono(tel);
            emp.setUser(user);
            edao.agregar(emp);
            request.setAttribute("mensaje", "Empleado agregado exitosamente");
        } else {
            request.setAttribute("mensaje", "Por favor, complete todos los campos");
        }

        request.getRequestDispatcher("Controlador?menu=Empleado&accion=listar").forward(request, response);
    }

    private void editarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Empleado e = edao.listarId(id);
            request.setAttribute("empleado", e);
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("Controlador?menu=Empleado&accion=listar");
        }
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("txtid"));
            String cedula = request.getParameter("txtcedula");
            String nombres = request.getParameter("txtnombres");
            String telefono = request.getParameter("txttelefono");
            String usuario = request.getParameter("txtusuario");

            Empleado em = new Empleado();
            em.setId(id);
            em.setCedula(cedula);
            em.setNombres(nombres);
            em.setTelefono(telefono);
            em.setUser(usuario);

            edao.actualizar(em);
            response.sendRedirect("Controlador?menu=Empleado&accion=listar");
        } catch (NumberFormatException e) {
            response.sendRedirect("Controlador?menu=Empleado&accion=listar");
        }
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            edao.delete(id);
            response.sendRedirect("Controlador?menu=Empleado&accion=listar");
        } catch (NumberFormatException e) {
            response.sendRedirect("Controlador?menu=Empleado&accion=listar");
        }
    }

    private void handleNuevaVentaActions(HttpServletRequest request, HttpServletResponse response, String accion) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<DetalleVenta> listaDetalles = (List<DetalleVenta>) session.getAttribute("listaDetalles");

        if (listaDetalles == null) {
            listaDetalles = new ArrayList<>();
            session.setAttribute("listaDetalles", listaDetalles);
        }

        double totalVenta = 0.0;

        if ("BuscarProducto".equals(accion)) {
            int idProducto = 0;
            try {
                idProducto = Integer.parseInt(request.getParameter("idproducto"));
            } catch (NumberFormatException e) {
                request.setAttribute("mensaje", "ID de producto no válido.");
                request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                return;
            }

            Producto producto = productoBD.buscarPorId(idProducto);

            if (producto != null) {

                request.setAttribute("producto", producto);
                request.setAttribute("mensaje", "Producto encontrado: " + producto.getNombres());
            } else {

                request.setAttribute("producto", null);
                request.setAttribute("mensaje", "Producto no encontrado");
            }

            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);

        } else if ("Agregar".equals(accion)) {

            int idProducto = Integer.parseInt(request.getParameter("idproducto"));
            int cantidad = Integer.parseInt(request.getParameter("cant"));
            Producto producto = productoBD.buscarPorId(idProducto);

            if (producto != null && cantidad > 0) {
                double subtotal = producto.getPrecio() * cantidad;
                int item = listaDetalles.size() + 1;

                DetalleVenta detalle = new DetalleVenta(item, producto.getIdProducto(), producto.getNombres(), producto.getPrecio(), cantidad, subtotal);

                listaDetalles.add(detalle);

                totalVenta = listaDetalles.stream().mapToDouble(DetalleVenta::getSubtotal).sum();

                request.setAttribute("mensaje", "Producto agregado correctamente");
                request.setAttribute("listaDetalles", listaDetalles);
                request.setAttribute("totalVenta", totalVenta);

                System.out.println("Lista de Detalles: ");
                for (DetalleVenta det : listaDetalles) {
                    System.out.println("Item: " + det.getItem() + ", Producto: " + det.getDescripcionP() + ", Cantidad: " + det.getCantidad() + ", Precio: " + det.getPrecio() + ", Subtotal: " + det.getSubtotal());
                }
                System.out.println("Total Venta: " + totalVenta);
            } else {
                request.setAttribute("mensaje", "Error al agregar el producto. Verifique el ID y la cantidad.");
            }

            session.setAttribute("listaDetalles", listaDetalles);
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);

        } else if ("delete".equals(accion)) {

            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int idProducto = Integer.parseInt(idStr);
                    listaDetalles.removeIf(detalle -> detalle.getIdProducto() == idProducto);
                    request.setAttribute("mensaje", "Producto eliminado correctamente");

                    totalVenta = listaDetalles.stream().mapToDouble(DetalleVenta::getSubtotal).sum();
                    request.setAttribute("totalVenta", totalVenta); // Pasar el total actualizado al JSP

                    System.out.println("Lista de Detalles después de eliminar: ");
                    for (DetalleVenta det : listaDetalles) {
                        System.out.println("Item: " + det.getItem() + ", Producto: " + det.getDescripcionP() + ", Cantidad: " + det.getCantidad() + ", Precio: " + det.getPrecio() + ", Subtotal: " + det.getSubtotal());
                    }
                    System.out.println("Total Venta después de eliminar: " + totalVenta);
                } catch (NumberFormatException e) {
                    request.setAttribute("mensaje", "ID de producto no válido.");
                }
            }

            session.setAttribute("listaDetalles", listaDetalles);
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);

        } else if ("GenerarVenta".equals(accion)) {

            try {
                Venta venta = new Venta();
                venta.setDetalles(listaDetalles);

                VentaBD ventaBD = new VentaBD();
                ventaBD.guardarVenta(venta);

                session.removeAttribute("listaDetalles");
                request.setAttribute("mensaje", "Venta generada exitosamente");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensaje", "Error al generar la venta.");
            }

            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);

        } else if ("Cancelar".equals(accion)) {
            session.removeAttribute("listaDetalles");
            request.setAttribute("mensaje", "Venta cancelada");
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador Principal";
    }
}
