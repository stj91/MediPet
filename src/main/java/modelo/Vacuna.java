package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vacuna {

    private static int cont = 0;
    private int id;
    private String nombre;
    private double dosisMl;
    private Fecha fechaAplicacion;
    private Fecha fechaSiguiente;
    private String veterinario;
    private int idMascota;

    public Vacuna() {
        this(0, "", 0.0, null, null, "");
    }

    public Vacuna(String nombre, double dosisMl, Fecha fechaAplicacion, Fecha fechaSiguiente, String veterinario) {
        this.nombre = nombre;
        this.dosisMl = dosisMl;
        this.fechaAplicacion = fechaAplicacion;
        this.fechaSiguiente = fechaSiguiente;
        this.veterinario = veterinario;
        this.id = ++cont;
    }

    public Vacuna(int id, String nombre, double dosisMl, Fecha fechaAplicacion, Fecha fechaSiguiente, String veterinario) {
        this.id = id;
        this.nombre = nombre;
        this.dosisMl = dosisMl;
        this.fechaAplicacion = fechaAplicacion;
        this.fechaSiguiente = fechaSiguiente;
        this.veterinario = veterinario;
    }

    public Vacuna(String nombre, double dosisMl, Fecha fechaAplicacion, Fecha fechaSiguiente, String veterinario, int idMascota) {
        this.nombre = nombre;
        this.dosisMl = dosisMl;
        this.fechaAplicacion = fechaAplicacion;
        this.fechaSiguiente = fechaSiguiente;
        this.veterinario = veterinario;
        this.idMascota = idMascota;
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

    public double getDosisMl() {
        return dosisMl;
    }

    public void setDosisMl(double dosisMl) {
        this.dosisMl = dosisMl;
    }

    public Fecha getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Fecha fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public Fecha getFechaSiguiente() {
        return fechaSiguiente;
    }

    public void setFechaSiguiente(Fecha fechaSiguiente) {
        this.fechaSiguiente = fechaSiguiente;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }
    
    public String datosVacuna() {
        return "Nombre: " + this.nombre + "\n"
                + "Dósis aplicada: " + this.dosisMl + "\n"
                + "Fecha de aplicación: " + this.fechaAplicacion.verFecha() + "\n"
                + "Fecha de revacunación: " + this.fechaSiguiente.verFecha() + "\n";
    }
    
    public boolean insertar() throws SQLException {
        ConexionDB conexion = new ConexionDB();
    
        String SQL = "";
        SQL += "INSERT INTO vacuna VALUES (null, ";
        SQL += "'" + this.nombre + "', ";
        SQL += this.dosisMl + ", ";
        SQL += "'" + this.fechaAplicacion.toSqlDate() + "', ";
        SQL += "'" + this.fechaSiguiente.toSqlDate() + "', ";
        SQL += "'" + this.veterinario + "', ";
        SQL += this.idMascota + ")";

        int filas = conexion.ejecutarInstruccion(SQL);
        int ultimoID = conexion.ultimoID();

        this.id = ultimoID;
        conexion.cerrarConexion();

        return filas > 0;
    }
    
    public ObservableList<Vacuna> getVacunasDeMascota(int idMascota, int idUsuario) throws SQLException {
        ObservableList<Vacuna> lista = FXCollections.observableArrayList();
        ConexionDB conexion = new ConexionDB();

        String sql = "SELECT v.fecha_aplicacion, v.nombre, v.dosis_ml, v.veterinario, v.fecha_siguiente " +
             "FROM vacuna v " +
             "JOIN mascota m ON v.id_mascota = m.id " +
             "JOIN propietario p ON m.id_propietario = p.id " +
             "WHERE v.id_mascota = " + idMascota + " AND p.id_usuario = " + idUsuario;

        ResultSet rs = conexion.ejecutarConsulta(sql);

        while (rs.next()) {
            Fecha fechaAplicacion = new Fecha(rs.getDate("fecha_aplicacion"));
            String nombreVacuna = rs.getString("nombre");
            double dosis = rs.getDouble("dosis_ml");
            String nombreVeterinario = rs.getString("veterinario");
            Fecha fechaProxima = rs.getDate("fecha_siguiente") != null ? new Fecha(rs.getDate("fecha_siguiente")) : null;

            Vacuna vacuna = new Vacuna(nombreVacuna, dosis, fechaAplicacion, fechaProxima, nombreVeterinario);
            lista.add(vacuna);
        }

        rs.close();
        conexion.cerrarConexion();

        return lista;
    }
}
