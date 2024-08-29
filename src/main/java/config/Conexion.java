package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String URL = "jdbc:mysql://localhost:3306/test"; // Nombre de la base de datos
    private final String USER = "root"; // Usuario de la base de datos
    private final String PASSWORD = ""; // Contraseña de la base de datos, vacía si no tiene

    public Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar el driver de MySQL", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }
        return con;
    }
}
