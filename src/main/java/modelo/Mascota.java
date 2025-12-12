package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Homero Ramírez
 */
public class Mascota {

    private String nombre;
    private int id;
    private String animal;
    private String raza;
    private Fecha nacimiento;
    private Propietario amo;
    private String tipoSangre;
    private double peso;
    private Fecha partida;
    private String genero;

    /**
     * Constructor vacio
     */
    public Mascota() {
        this("", 0, "", "", null, null, "", 0.0, null);
    }

    public Mascota(int id, String nombre, String animal, String raza) {
        this.id = id;
        this.nombre = nombre;
        this.animal = animal;
        this.raza = raza;
    }

    public Mascota(String nombre, String animal, String raza, Fecha nacimiento, Propietario amo, String tipoSangre, double peso, String genero) {
        this.nombre = nombre;
        this.animal = animal;
        this.raza = raza;
        this.nacimiento = nacimiento;
        this.amo = amo;
        this.tipoSangre = tipoSangre;
        this.peso = peso;
        this.genero = genero;
    }

    /**
     *
     * @param nombre
     * @param animal
     * @param raza
     * @param nacimiento
     * @param amo
     * @param tipoSangre
     * @param peso
     */
    public Mascota(String nombre, String animal, String raza, Fecha nacimiento, Propietario amo, String tipoSangre, double peso) {
        this.nombre = nombre;
        this.animal = animal;
        this.raza = raza;
        this.nacimiento = nacimiento;
        this.amo = amo;
        this.tipoSangre = tipoSangre;
        this.peso = peso;
    }
    
    public Mascota(String nombre, String animal, String raza, Fecha nacimiento, Propietario amo, String tipoSangre, double peso, Fecha partida) {
        this.nombre = nombre;
        this.animal = animal;
        this.raza = raza;
        this.nacimiento = nacimiento;
        this.amo = amo;
        this.tipoSangre = tipoSangre;
        this.peso = peso;
        this.partida = partida;
    }

    public Mascota(String nombre, String animal, String raza, Fecha nacimiento, Propietario amo, String tipoSangre, double peso, Fecha partida, String genero) {
        this.nombre = nombre;
        this.animal = animal;
        this.raza = raza;
        this.nacimiento = nacimiento;
        this.amo = amo;
        this.tipoSangre = tipoSangre;
        this.peso = peso;
        this.partida = partida;
        this.genero = genero;
    }

    /**
     * Constructor para el vacio
     *
     * @param nombre Nombre de la mascota.
     * @param id Id de la mascota.
     * @param animal El tipo de animal (perro, gato, etc...).
     * @param raza Raza del animal.
     * @param nacimiento Fecha de nacimiento.
     * @param amo Dueño de la mascota.
     * @param tipoSangre Tipo de sangre que tiene la mascota.
     * @param peso Peso en kilogramos de la mascota.
     * @param partida Fecha de fallecimiento (opcional).
     */
    public Mascota(String nombre, int id, String animal, String raza, Fecha nacimiento, Propietario amo, String tipoSangre, double peso, Fecha partida) {
        this.nombre = nombre;
        this.id = id;
        this.animal = animal;
        this.raza = raza;
        this.nacimiento = nacimiento;
        this.amo = amo;
        this.tipoSangre = tipoSangre;
        this.peso = peso;
        this.partida = partida;
    }

    /**
     * Devuelve el nombre de la mascota.
     *
     * @return nombre de la mascota
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la mascota.
     *
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el id de la mascota.
     *
     * @return id de la mascota
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el id de la mascota.
     *
     * @param id Id nuevo.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getAnimal() {
        return animal;
    }

    /**
     *
     * @param animal
     */
    public void setAnimal(String animal) {
        this.animal = animal;
    }

    /**
     *
     * @return
     */
    public String getRaza() {
        return raza;
    }

    /**
     *
     * @param raza
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     *
     * @return
     */
    public Fecha getNacimiento() {
        return nacimiento;
    }

    /**
     *
     * @param nacimiento
     */
    public void setNacimiento(Fecha nacimiento) {
        this.nacimiento = nacimiento;
    }

    /**
     *
     * @return
     */
    public Propietario getAmo() {
        return amo;
    }

    /**
     *
     * @param amo
     */
    public void setAmo(Propietario amo) {
        this.amo = amo;
    }

    /**
     *
     * @return
     */
    public String getTipoSangre() {
        return tipoSangre;
    }

    /**
     *
     * @param tipoSangre
     */
    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    /**
     *
     * @return
     */
    public double getPeso() {
        return peso;
    }

    /**
     *
     * @param peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     *
     * @return
     */
    public Fecha getPartida() {
        return partida;
    }

    /**
     *
     * @param partida
     */
    public void setPartida(Fecha partida) {
        this.partida = partida;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Mascota: " + nombre; //+ ".\nDueño: " + amo.getNombre() + " " + amo.getApellido();
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Muestra los datos de la mascota.
     *
     * @return
     */
    public String mostrarInfo() {
        return "Dueño: " + this.amo.getNombre() + " " + this.amo.getApellido() + "\n"
                + "Mascota: " + this.nombre + "\n"
                + "Animal: " + this.animal + "\t" + "Raza: " + this.raza + "\n"
                + "Tipo de sangre: " + this.tipoSangre + "\n"
                + "Peso: " + this.peso + "\n"
                + "Fecha de nacimiento: " + this.nacimiento.verFecha() + "\n";
    }
    
    public ObservableList<Mascota> getMascotas(String busqueda, int idUsuario) throws SQLException {
        ObservableList<Mascota> mascotas = FXCollections.observableArrayList();
        ConexionDB conexion = new ConexionDB();
        
        String SQL = "";
        SQL += "SELECT m.id, m.nombre AS nombre_mascota, m.animal, m.raza, m.genero, m.nacimiento, m.tipo_sangre, m.peso, m.partida, ";
        SQL += "p.id AS id_propietario, p.nombre AS nombre_propietario, p.apellido, p.edad, p.num_celular ";
        SQL += "FROM mascota m ";
        SQL += "JOIN propietario p ON m.id_propietario = p.id ";
        SQL += "WHERE p.id_usuario = " + idUsuario;
        
        if (busqueda != null && !busqueda.isEmpty()){
            SQL += " AND TRIM(LOWER(m.nombre)) LIKE '%" + busqueda.toLowerCase().trim() + "%'";
        }
        
        ResultSet rs = conexion.ejecutarConsulta(SQL); //almacena datos de consulta SOLO CONSULTAS

        while (rs.next()){
            int idMascota = rs.getInt("id");
            String nombreMascota = rs.getString("nombre_mascota");
            String animal = rs.getString("animal");
            String raza = rs.getString("raza");
            String genero = rs.getString("genero");
            Date nacimiento = rs.getDate("nacimiento");
            String tipoSangre = rs.getString("tipo_sangre");
            Double peso = rs.getDouble("peso");
            Date partida = rs.getDate("partida");
            
            int idProp = rs.getInt("id_propietario");
            String nombreProp = rs.getString("nombre_propietario");
            String apellidoProp = rs.getString("apellido");
            int edad = rs.getInt("edad");
            long celProp = rs.getLong("num_celular");
            
            Propietario p = new Propietario(nombreProp, apellidoProp, celProp);
            p.setEdad(edad);
            Fecha nacimientoFecha = new Fecha(nacimiento);
            Fecha partidaFecha = (partida != null) ? new Fecha(partida) : null;
            
            Mascota m = new Mascota(nombreMascota, animal, raza, nacimientoFecha, p, tipoSangre, peso, partidaFecha);
            m.setId(idMascota);
            m.getAmo().setId(idProp);
            m.setGenero(genero);
            
            mascotas.add(m);
        }
            
        rs.close();
        conexion.cerrarConexion();
        
        return mascotas;
    }
    
    public boolean insertar() throws SQLException {
    ConexionDB conexion = new ConexionDB();
    
        String SQL = "";
        SQL += "INSERT INTO mascota VALUES (null, ";
        SQL += "'" + this.nombre + "', ";
        SQL += "'" + this.animal + "', ";
        SQL += "'" + this.raza + "', ";
        SQL += "'" + this.genero + "', ";
        SQL += "'" + this.nacimiento.toSqlDate() + "', ";
        SQL += "'" + this.tipoSangre + "', ";
        SQL += this.peso + ", ";
        SQL += (this.partida != null ? "'" + this.partida.toSqlDate() + "'" : "NULL") + ", ";
        SQL += this.amo.getId(); // ID del propietario
        SQL += ")";

        int filas = conexion.ejecutarInstruccion(SQL);
        int ultimoID = conexion.ultimoID();

        this.id = ultimoID;
        conexion.cerrarConexion();

        return filas > 0;
    }
    
    public boolean actualizar(int idMascota, int idAmo) throws SQLException{
        ConexionDB conexion = new ConexionDB();
        
        String SQL = "UPDATE mascota SET " +
                 "nombre = '" + this.nombre + "', " +
                 "animal = '" + this.animal + "', " +
                 "raza = '" + this.raza + "', " +
                 "genero = '" + this.genero + "', " +
                 "nacimiento = '" + this.nacimiento.toSqlDate() + "', " +
                 "tipo_sangre = '" + this.tipoSangre + "', " +
                 "peso = " + this.peso + ", " +
                 "partida = " + (this.partida != null ? "'" + this.partida.toSqlDate() + "'" : "NULL") + 
                 " WHERE id = " + idMascota + " AND id_propietario = " + idAmo;
        
        int filas = conexion.ejecutarInstruccion(SQL);
        conexion.cerrarConexion();
        
        return filas > 0;
    }
    
    public String getNombreAmo() {
        return amo.getNombre();
    }

    public String getApellidoAmo() {
        return amo.getApellido();
    }

    public long getCelularAmo() {
        return amo.getNumCelular();
    }
    
    public boolean borrar(int idUsuario) throws SQLException{
        ConexionDB conexion = new ConexionDB();
        
        String SQL = "";
        SQL += "DELETE m FROM mascota m ";
        SQL += "JOIN propietario p ON m.id_propietario = p.id ";
        SQL += "WHERE m.id = " + this.id + " ";
        SQL += "AND p.id_usuario = " + idUsuario;
                
        int filas = conexion.ejecutarInstruccion(SQL);
        conexion.cerrarConexion();
        
        return filas > 0;
    }
    
    public boolean contarMascotas(int idUsuario) throws SQLException{
        ConexionDB conexion = new ConexionDB();
        String SQL = "";
        SQL += "SELECT COUNT(*) FROM mascota m ";
        SQL += "JOIN propietario p ON m.id_propietario = p.id ";
        SQL += "WHERE m.id_propietario = " + this.getAmo().getId() + " ";
        SQL += "AND p.id_usuario = " + idUsuario;
        
        ResultSet rs = conexion.ejecutarConsulta(SQL);
        int cantidad = 0;
        
        if (rs.next()) {
            cantidad = rs.getInt(1);
        }
        
        conexion.cerrarConexion();
        
        return cantidad == 1;
    }
    
    public ObservableList<Mascota> getTodasLasMascotas(int idUsuario) throws SQLException {
        ObservableList<Mascota> lista = FXCollections.observableArrayList();
        ConexionDB conexion = new ConexionDB();

        String sql = "SELECT m.id, m.nombre AS nombre_mascota, m.animal, m.raza, m.genero, m.nacimiento, m.tipo_sangre, m.peso, m.partida, " +
                     "p.id AS id_propietario, p.nombre AS nombre_propietario, p.apellido, p.num_celular " +
                     "FROM mascota m " +
                     "JOIN propietario p ON m.id_propietario = p.id " +
                     "WHERE p.id_usuario = " + idUsuario;

        ResultSet rs = conexion.ejecutarConsulta(sql);

        while (rs.next()) {
            int idMascota = rs.getInt("id");
            String nombreMascota = rs.getString("nombre_mascota");
            String animal = rs.getString("animal");
            String raza = rs.getString("raza");
            String genero = rs.getString("genero");
            Date nacimiento = rs.getDate("nacimiento");
            String tipoSangre = rs.getString("tipo_sangre");
            Double peso = rs.getDouble("peso");
            Date partida = rs.getDate("partida");

            int idProp = rs.getInt("id_propietario");
            String nombreProp = rs.getString("nombre_propietario");
            String apellidoProp = rs.getString("apellido");
            long celProp = rs.getLong("num_celular");

            Propietario p = new Propietario(nombreProp, apellidoProp, celProp);
            p.setId(idProp);

            Fecha nacimientoFecha = new Fecha(nacimiento);
            Fecha partidaFecha = (partida != null) ? new Fecha(partida) : null;

            Mascota m = new Mascota(nombreMascota, animal, raza, nacimientoFecha, p, tipoSangre, peso, partidaFecha);
            m.setGenero(genero);
            m.setId(idMascota);

            lista.add(m);
        }

        rs.close();
        conexion.cerrarConexion();

        return lista;
    }
    
    public int mascotasTotal(int idUsuario) throws SQLException {
        ConexionDB conexion = new ConexionDB();
        
        String SQL = "SELECT COUNT(m.id) AS total FROM mascota m "
                   + "JOIN propietario p ON m.id_propietario = p.id " 
                   + "WHERE p.id_usuario = " + idUsuario;
        
        ResultSet rs = conexion.ejecutarConsulta(SQL);
        int cantidad = 0;
        
        if (rs.next()) {
            cantidad = rs.getInt("total");
        }
        
        conexion.cerrarConexion();
        
        return cantidad;
    } 
    
    public Map<String, Integer> graficaMascotas(int idUsuario) throws SQLException {
        ConexionDB conexion = new ConexionDB();
        
        String SQL = "SELECT m.animal, COUNT(*) AS cantidad " +
                 "FROM mascota m " +
                 "JOIN propietario p ON m.id_propietario = p.id " +
                 "WHERE p.id_usuario = " + idUsuario + " " +
                 "GROUP BY m.animal";
        
        ResultSet rs = conexion.ejecutarConsulta(SQL);
        Map<String, Integer> datos = new HashMap<>();
        
        while (rs.next()) {
            String raza = rs.getString("animal");
            int cantidad = rs.getInt("cantidad");
            
            datos.put(raza, cantidad);
        }
        
        conexion.cerrarConexion();
        
        return datos;
    }
    
}

