package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import javafx.scene.control.Alert;

/**
 *
 * @author Homero Ramírez
 */
public class ConexionDB {

    private final Connection conexion;

    public ConexionDB() throws SQLException {
        String host = "localhost";
        String baseDatos = "medipet";
        String usuario = "root";
        String password = "";

        // Conexión sin base de datos para ver si existe
        Connection testConexion = DriverManager.getConnection("jdbc:mysql://" + host + "/", usuario, password);

        if (!existeBaseDeDatos(testConexion, baseDatos)) {
            crearBaseDeDatos(testConexion);
        }

        // Ya existe, conectar normalmente
        String cadenaConexion = "jdbc:mysql://" + host + "/" + baseDatos;
        conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
        conexion.setAutoCommit(true);
    }

    private boolean existeBaseDeDatos(Connection conn, String nombreBD) throws SQLException {
        ResultSet rs = conn.getMetaData().getCatalogs();
        while (rs.next()) {
            if (rs.getString(1).equalsIgnoreCase(nombreBD)) {
                rs.close();
                return true;
            }
        }
        rs.close();
        return false;
    }

    private void crearBaseDeDatos(Connection conn) {
        try {
            // Leer el archivo SQL desde recursos
            InputStream input = getClass().getResourceAsStream("/sql/medipet.sql");
            if (input == null) {
                MetodosSueltos.mostrarAlerta("No se pudo crear la base de datos...", Alert.AlertType.ERROR);
                
            }

            String sql = new String(input.readAllBytes());
            Statement stmt = conn.createStatement();
            for (String comando : sql.split(";")) {
                if (!comando.trim().isEmpty()) {
                    stmt.execute(comando.trim());
                }
            }
            
            MetodosSueltos.mostarVentana("Base de datos 'medipet' creada correctamente.", Alert.AlertType.INFORMATION);
        } catch (IOException | SQLException ex) {
            MetodosSueltos.mostrarAlerta("Error: " + ex.getMessage() + ".\nIntente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    public ResultSet ejecutarConsulta(String SQL) throws SQLException {
        Statement statement = this.conexion.createStatement();
        return statement.executeQuery(SQL);
    }

    public int ejecutarInstruccion(String SQL) throws SQLException {
        Statement statement = this.conexion.createStatement();
        return statement.executeUpdate(SQL);
    }

    public int ultimoID() throws SQLException {
        ResultSet rs = this.ejecutarConsulta("SELECT last_insert_id() as last_id;");
        rs.next();
        int id = rs.getInt("last_id");
        rs.close();
        return id;
    }

    public Connection getConexion() {
        return this.conexion;
    }

    public void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
}
