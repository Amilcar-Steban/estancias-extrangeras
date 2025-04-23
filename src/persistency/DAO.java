package persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO {
    protected Connection conexion = null;
    protected ResultSet resultSet = null;
    protected Statement statement = null;
    protected PreparedStatement preparedStatement = null;

    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String DATABSE = "estancias_exterior";
    private final String USER = "root";
    private final String PASSWORD = "Pass123.";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String ZONA = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    protected void connectDataBase() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABSE+ZONA;
            conexion = java.sql.DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void closeDataBase() throws SQLException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) { // Cierra el PreparedStatement si existe
                preparedStatement.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexiones: " + e.getMessage());
            throw e;
        }
    }

    protected void crud(String sql, Object... params) throws SQLException, ClassNotFoundException {
        try {
            connectDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            
            // Asigna los parámetros dinámicamente
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            
            preparedStatement.executeUpdate();
        } finally {
            closeDataBase(); // Siempre cierra recursos
        }
    }

    protected void consultarDataBase(String sql, Object... params) throws SQLException, ClassNotFoundException {
        try {
            connectDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            closeDataBase();
            throw e;
        }
    }
    

}
