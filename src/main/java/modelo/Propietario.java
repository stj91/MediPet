package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Homero Ramírez
 */
public class Propietario {

    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private long numCelular;
    private int idUsuario;

    public Propietario() {
        this(0, "", "", 0, 0);
    }

    public Propietario(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Propietario(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public Propietario(String nombre, String apellido, long numCelular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numCelular = numCelular;
    }

    public Propietario(String nombre, String apellido, int edad, long numCelular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numCelular = numCelular;
    }

    public Propietario(int id, String nombre, String apellido, int edad, long numCelular) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numCelular = numCelular;
    }

    public Propietario(String nombre, String apellido, int edad, long numCelular, int idUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numCelular = numCelular;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public long getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(long numCelular) {
        this.numCelular = numCelular;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return id + ". " + nombre + " " + apellido + ".";
    }

    public boolean insertar() throws SQLException {
        ConexionDB conexion = new ConexionDB();
        String SQL = "INSERT INTO propietario VALUES (null, '" + this.nombre + "', '" + this.apellido + "', " + this.edad + ", " + this.numCelular + ", " + this.idUsuario + ")";

        int filas = conexion.ejecutarInstruccion(SQL);
        this.id = conexion.ultimoID(); // Guarda el id generado

        conexion.cerrarConexion();

        return filas > 0;
    }
    
    public boolean actualizar(int idAmo) throws SQLException{
        ConexionDB conexion = new ConexionDB();
        
        String SQL = "UPDATE propietario SET " +
                 "nombre = '" + this.nombre + "', " +
                 "apellido = '" + this.apellido + "', " +
                 "edad = " + this.edad + ", " +
                 "num_celular = " + this.numCelular + " " +
                 "WHERE id = " + idAmo;
        
        int filas = conexion.ejecutarInstruccion(SQL);
        conexion.cerrarConexion();
        
        return filas > 0;
    }

    public boolean borrar(int idUsuario) throws SQLException {
        ConexionDB conexion = new ConexionDB();
        String SQL = "DELETE FROM propietario WHERE id = " + this.id + " AND id_usuario = " + idUsuario;

        int filas = conexion.ejecutarInstruccion(SQL);
        conexion.cerrarConexion();

        return filas > 0;
    }

    public ObservableList<Propietario> getTodosLosPropietarios(int idUsuario) throws SQLException {
        ObservableList<Propietario> lista = FXCollections.observableArrayList();
        ConexionDB conexion = new ConexionDB();

        String sql = "SELECT id, nombre, apellido, num_celular, edad FROM propietario WHERE id_usuario = " + idUsuario;

        ResultSet rs = conexion.ejecutarConsulta(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            long celular = rs.getLong("num_celular");
            int edad = rs.getInt("edad");

            Propietario p = new Propietario(nombre, apellido, celular);
            p.setId(id);
            p.setEdad(edad);
            p.setIdUsuario(idUsuario);

            lista.add(p);
        }

        rs.close();
        conexion.cerrarConexion();

        return lista;
    }

    public ObservableList<Mascota> getMascotasAmo(int idAmo) throws SQLException {
        ObservableList<Mascota> lista = FXCollections.observableArrayList();
        ConexionDB conexion = new ConexionDB();

        String sql = "SELECT * "
                + "FROM mascota "
                + "WHERE id_propietario = " + idAmo;

        ResultSet rs = conexion.ejecutarConsulta(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombreMascota = rs.getString("nombre");
            String animal = rs.getString("animal");
            String raza = rs.getString("raza");

            Mascota m = new Mascota(id, nombreMascota, animal, raza);
            lista.add(m);
        }

        rs.close();
        conexion.cerrarConexion();

        return lista;
    }
}
