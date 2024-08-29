package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentaBD {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void guardarVenta(Venta venta) {
        String sqlVenta = "INSERT INTO ventas (id_venta, total) VALUES (?, ?)";
        String sqlDetalle = "INSERT INTO detalles_venta (id_venta, item, id_producto, descripcion, precio, cantidad, subtotal) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement psVenta = conn.prepareStatement(sqlVenta);
             PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle)) {

            
            psVenta.setInt(1, venta.getIdVenta());
            psVenta.setDouble(2, venta.calcularTotal());
            psVenta.executeUpdate();

            
            for (DetalleVenta detalle : venta.getDetalles()) {
                psDetalle.setInt(1, venta.getIdVenta());
                psDetalle.setInt(2, detalle.getItem());
                psDetalle.setInt(3, detalle.getIdProducto());
                psDetalle.setString(4, detalle.getDescripcionP());
                psDetalle.setDouble(5, detalle.getPrecio());
                psDetalle.setInt(6, detalle.getCantidad());
                psDetalle.setDouble(7, detalle.getSubtotal());
                psDetalle.addBatch();
            }
            psDetalle.executeBatch();

          } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}