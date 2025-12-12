package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 *
 * @author Homero Ramírez
 */
public class Cita {
    private int id;
    private Fecha fecha;
    private String horario;
    private Mascota pacienteMascota;
    private String motivo;
    private double costo;
    private String estatus = "PENDIENTE";

    public Cita() {
        this(0, null, "", null, "", 0.0);
    }

    public Cita(Fecha fecha, String horario, String motivo, double costo) {
        this.fecha = fecha;
        this.horario = horario;
        this.motivo = motivo;
        this.costo = costo;
    }

    public Cita(Fecha fecha, String horario, Mascota pacienteMascota, String motivo, double costo) {
        this.fecha = fecha;
        this.horario = horario;
        this.pacienteMascota = pacienteMascota;
        this.motivo = motivo;
        this.costo = costo;
    }

    public Cita(int id, Fecha fecha, String horario, Mascota pacienteMascota, String motivo, double costo) {
        this.id = id;
        this.fecha = fecha;
        this.horario = horario;
        this.pacienteMascota = pacienteMascota;
        this.motivo = motivo;
        this.costo = costo;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setCitaFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Mascota getPacienteMascota() {
        return pacienteMascota;
    }

    public void setPacienteMascota(Mascota pacienteMascota) {
        this.pacienteMascota = pacienteMascota;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    public boolean insertar(int idUsuario) throws SQLException {
        ConexionDB conexion = new ConexionDB();
    
        String SQL = "";
        SQL += "INSERT INTO cita VALUES (null, ";
        SQL += "'" + this.fecha.toSqlDate() + "', ";
        SQL += "'" + this.horario + "', ";
        SQL += "'" + this.motivo + "', ";
        SQL += this.costo + ", ";
        SQL += this.pacienteMascota.getId(); 
        SQL += ", 'PENDIENTE', "; 
        SQL += idUsuario + ")";

        int filas = conexion.ejecutarInstruccion(SQL);
        int ultimoID = conexion.ultimoID();

        this.id = ultimoID;
        conexion.cerrarConexion();

        return filas > 0;
    }
    
    public ObservableList<Cita> getVacunasDeMascota(int idMascota, int idUsuario) throws SQLException {
        ObservableList<Cita> lista = FXCollections.observableArrayList();
        ConexionDB conexion = new ConexionDB();

        String sql = "SELECT c.fecha, c.horario, c.motivo, c.costo " +
                     "FROM cita c " +
                     "WHERE c.id_mascota = " + idMascota + " AND c.id_usuario = " + idUsuario;

        ResultSet rs = conexion.ejecutarConsulta(sql);

        while (rs.next()) {
            Fecha fecha = new Fecha(rs.getDate("fecha"));
            String horario = rs.getString("horario");
            String motivo = rs.getString("motivo");
            double costo = rs.getDouble("veterinario");

            Cita cita = new Cita(fecha, horario, motivo, costo);
            lista.add(cita);
        }

        rs.close();
        conexion.cerrarConexion();

        return lista;
    }
    
    public ObservableList<Cita> getCitas(String busqueda, int idUsuario) throws SQLException {
        ObservableList<Cita> citas = FXCollections.observableArrayList();
        ConexionDB conexion = new ConexionDB();
        
        String SQL = "SELECT c.id_usuario, c.id AS id_cita, c.fecha, c.motivo, c.horario, c.costo, c.estatus, "
                 + "m.id AS id_mascota, m.nombre AS nombre_mascota, m.animal, m.raza, m.tipo_sangre, m.peso, m.nacimiento, m.partida, "
                 + "p.id AS id_propietario, p.nombre AS nombre_propietario, p.apellido, p.num_celular "
                 + "FROM cita c "
                 + "JOIN mascota m ON c.id_mascota = m.id "
                 + "JOIN propietario p ON m.id_propietario = p.id " 
                 + "WHERE c.id_usuario = " + idUsuario;
        
        if (busqueda != null && !busqueda.isEmpty()){
            SQL += " AND LOWER(TRIM(m.nombre)) LIKE '%" + busqueda.toLowerCase().trim() + "%'";
        }
        
        ResultSet rs = conexion.ejecutarConsulta(SQL); //almacena datos de consulta SOLO CONSULTAS

        while (rs.next()){
            int idCita = rs.getInt("id_cita");
            Date fecha = rs.getDate("fecha");
            String motivo = rs.getString("motivo");
            String horario = rs.getString("horario");
            double costoCita = rs.getDouble("costo");
            String estatus = rs.getString("estatus");

            
            int idMascota = rs.getInt("id_mascota");
            String nombreMascota = rs.getString("nombre_mascota");
            String animal = rs.getString("animal");
            String raza = rs.getString("raza");
            Date nacimiento = rs.getDate("nacimiento");
            String tipoSangre = rs.getString("tipo_sangre");
            Double peso = rs.getDouble("peso");
            Date partida = rs.getDate("partida");
            
            int idProp = rs.getInt("id_propietario");
            String nombreProp = rs.getString("nombre_propietario");
            String apellidoProp = rs.getString("apellido");
            long celProp = rs.getLong("num_celular");
            
            Propietario p = new Propietario(nombreProp, apellidoProp, celProp);
            
            Fecha nacimientoFecha = new Fecha(nacimiento);
            Fecha partidaFecha = (partida != null) ? new Fecha(partida) : null;
            Fecha f = new Fecha(fecha);
            
            Mascota m = new Mascota(nombreMascota, animal, raza, nacimientoFecha, p, tipoSangre, peso, partidaFecha);
            m.setId(idMascota);
            m.getAmo().setId(idProp);
            
            
            Cita c = new Cita(idCita, f, horario, m, motivo, costoCita);
            
            if (estatus.equals("REALIZADA")) {
                c.setEstatus(estatus);
            } else if (estatus.equals("CANCELADA")) {
                c.setEstatus("CANCELADA");
            } else {
                c.setEstatus("PENDIENTE");
            }
            
            
            citas.add(c);
        }
            
        rs.close();
        conexion.cerrarConexion();
        
        return citas;
    }
    
    public void actualizarEstatusEnDB(int idUsuario) throws SQLException {
        ConexionDB conexion = new ConexionDB();

        String sql = "UPDATE cita SET estatus = '" + this.estatus + "' WHERE id = " + this.id + " AND id_usuario = " + idUsuario;

        conexion.ejecutarInstruccion(sql);
        conexion.cerrarConexion();
    }
    
    public double gananciaTotal(int idUsuario) throws SQLException {
        ConexionDB conexion = new ConexionDB();
        
        String SQL = "SELECT SUM(costo) AS total FROM cita WHERE DATE(fecha) = CURDATE() AND estatus = 'REALIZADA' AND id_usuario = " + idUsuario;
        
        ResultSet rs = conexion.ejecutarConsulta(SQL);
        double cantidad = 0;
        
        if (rs.next()) {
            cantidad = rs.getDouble("total");
        }
        
        conexion.cerrarConexion();
        
        return cantidad;
    } 
    
    public int citasDia(int idUsuario) throws SQLException {
        ConexionDB conexion = new ConexionDB();
        
        String SQL = "SELECT COUNT(id) FROM cita WHERE DATE(fecha) = CURDATE() AND estatus = 'PENDIENTE' AND id_usuario = " + idUsuario;
        
        ResultSet rs = conexion.ejecutarConsulta(SQL);
        int cantidad = 0;
        
        if (rs.next()) {
            cantidad = rs.getInt("COUNT(id)");
        }
        
        conexion.cerrarConexion();
        
        return cantidad;
    }
    
    public List<Pair<String, Double>> gananciasGrafico(int idUsuario) throws SQLException {
        ConexionDB conexion = new ConexionDB();
        
        String SQL = "SELECT DATE(fecha) AS dia, SUM(costo) AS total FROM cita WHERE estatus = 'REALIZADA' AND id_usuario = " + idUsuario + " GROUP BY dia ASC LIMIT 5";
        
        ResultSet rs = conexion.ejecutarConsulta(SQL);
        List<Pair<String, Double>> datos = new ArrayList<>();
        
        while (rs.next()) {
            String fecha = rs.getString("dia");
            double total = rs.getDouble("total");
            datos.add(new Pair<>(fecha, total));
        }
        
        conexion.cerrarConexion();
        
        return datos;
    } 
}
