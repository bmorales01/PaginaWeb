package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoBD {
    private final Conexion conexion = new Conexion(); 
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    
    public Empleado validar(String user, String cedula) {
        Empleado empleado = null;
        String sql = "SELECT * FROM empleado WHERE user=? AND cedula=?";
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user);
            preparedStatement.setString(2, cedula);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    empleado = new Empleado();
                    empleado.setId(resultSet.getInt("idEmpleado"));
                    empleado.setUser(resultSet.getString("user"));
                    empleado.setCedula(resultSet.getString("cedula"));
                    empleado.setNombres(resultSet.getString("nombres"));
                    empleado.setTelefono(resultSet.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }

    
    public List<Empleado> listar() {
        String sql = "SELECT * FROM empleado";
        List<Empleado> listaEmpleados = new ArrayList<>();
        try {
            connection = conexion.getConexion();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("idEmpleado"));
                empleado.setCedula(resultSet.getString("cedula"));
                empleado.setNombres(resultSet.getString("nombres"));
                empleado.setTelefono(resultSet.getString("telefono"));
                empleado.setUser(resultSet.getString("user"));
                listaEmpleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaEmpleados;
    }

    
    public int agregar(Empleado empleado) {
        String sql = "INSERT INTO empleado (cedula, nombres, telefono, user) VALUES (?,?,?,?)";
        int resultado = 0; 
        try {
            connection = conexion.getConexion();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, empleado.getCedula());
            preparedStatement.setString(2, empleado.getNombres());
            preparedStatement.setString(3, empleado.getTelefono());
            preparedStatement.setString(4, empleado.getUser());
            resultado = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    
    public Empleado listarId(int id) {
        Empleado empleado = new Empleado();
        String sql = "SELECT * FROM empleado WHERE idEmpleado = ?";
        try {
            connection = conexion.getConexion();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                empleado.setId(resultSet.getInt("idEmpleado"));
                empleado.setCedula(resultSet.getString("cedula"));
                empleado.setNombres(resultSet.getString("nombres"));
                empleado.setTelefono(resultSet.getString("telefono"));
                empleado.setUser(resultSet.getString("user"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return empleado;
    }

    
    public int actualizar(Empleado empleado) {
        String sql = "UPDATE empleado SET cedula=?, nombres=?, telefono=?, user=? WHERE idEmpleado=?";
        int resultado = 0; 
        try {
            connection = conexion.getConexion();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, empleado.getCedula());
            preparedStatement.setString(2, empleado.getNombres());
            preparedStatement.setString(3, empleado.getTelefono());
            preparedStatement.setString(4, empleado.getUser());
            preparedStatement.setInt(5, empleado.getId());
            resultado = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    
    public void delete(int id) {
        String sql = "DELETE FROM empleado WHERE idEmpleado = ?";
        try {
            connection = conexion.getConexion();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
