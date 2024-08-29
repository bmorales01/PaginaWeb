package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductoBD {
    private String url = "jdbc:mysql://localhost:3306/test"; // CONEXION A MI BD
    private String usuario = "root"; 
    private String contrasena = ""; 

    public Producto buscarPorId(int idProducto) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE idProducto = ?";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, idProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("Nombres");
                double precio = resultSet.getDouble("Precio");
                int stock = resultSet.getInt("Stock");

                producto = new Producto(idProducto, nombre, precio, stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return producto; 
    }

    public Producto buscarPorNombre(String nombreProducto) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE Nombres LIKE ?";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setString(1, "%" + nombreProducto + "%");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idProducto = resultSet.getInt("idProducto");
                String nombre = resultSet.getString("Nombres");
                double precio = resultSet.getDouble("Precio");
                int stock = resultSet.getInt("Stock");

                producto = new Producto(idProducto, nombre, precio, stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return producto; 
    }
}
