package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Homero Ramírez
 */
public class Usuario {
    
    private int id;
    private String usuario; 
    private String nombres;
    private String apellidos;
    private String password;
    private long numCelular;
    private String correo;
    private Fecha fecha;
    private String genero;

    public Usuario() {
        this(0, "", "", "", 0, "", null);
    }

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public Usuario(String usuario, String apellidos, String password, long numCelular, String correo, Fecha fecha) {
        this.usuario = usuario;
        this.apellidos = apellidos;
        this.password = password;
        this.numCelular = numCelular;
        this.correo = correo;
        this.fecha = fecha;
    }

    public Usuario(String usuario, String password, long numCelular, String correo, String genero) {
        this.usuario = usuario;
        this.password = password;
        this.numCelular = numCelular;
        this.correo = correo;
        this.genero = genero;
    }

    public Usuario(int id, String usuario, String apellidos, String password, long numCelular, String correo, Fecha fecha) {
        this.id = id;
        this.usuario = usuario;
        this.apellidos = apellidos;
        this.password = password;
        this.numCelular = numCelular;
        this.correo = correo;
        this.fecha = fecha;
    }

    public Usuario(String usuario, String nombres, String apellidos, String password, long numCelular, String correo, String genero) {
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.password = password;
        this.numCelular = numCelular;
        this.correo = correo;
        this.genero = genero;
    }

    public Usuario(String usuario, String nombres, String apellidos, String password, long numCelular, String correo, Fecha fecha, String genero) {
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.password = password;
        this.numCelular = numCelular;
        this.correo = correo;
        this.fecha = fecha;
        this.genero = genero;
    }

    public Usuario(int id, String usuario, String apellidos, String password, long numCelular, String correo, Fecha fecha, String genero) {
        this.id = id;
        this.usuario = usuario;
        this.apellidos = apellidos;
        this.password = password;
        this.numCelular = numCelular;
        this.correo = correo;
        this.fecha = fecha;
        this.genero = genero;
    }

    public Usuario(int id, String usuario, String nombres, String apellidos, String password, long numCelular, String correo, Fecha fecha, String genero) {
        this.id = id;
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.password = password;
        this.numCelular = numCelular;
        this.correo = correo;
        this.fecha = fecha;
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public long getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(long numCelular) {
        this.numCelular = numCelular;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Usuario login() throws SQLException {
        ConexionDB conexion = new ConexionDB();
        Usuario usuarioLogeado = null;

        String SQL = "SELECT * FROM usuarios WHERE lower(usuario) = ? AND password = ?";
        PreparedStatement ps = conexion.getConexion().prepareStatement(SQL);
        ps.setString(1, usuario.toLowerCase());
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String usuario = rs.getString("usuario");
            String nombres = rs.getString("nombres");
            String apellidos = rs.getString("apellidos");
            String password = rs.getString("password");
            long numCelular = rs.getLong("num_Celular");
            String correo = rs.getString("correo");
            Fecha fecha = new Fecha(rs.getDate("fecha"));
            String genero = rs.getString("genero");

            usuarioLogeado = new Usuario(id, usuario, nombres, apellidos, password, numCelular, correo, fecha, genero);
        }

        conexion.cerrarConexion();
        return usuarioLogeado;
    }
    
    public boolean insertar() throws SQLException {
        ConexionDB conexion = new ConexionDB();
    
        String SQL = "";
        SQL += "INSERT INTO usuarios VALUES (null, ";
        SQL += "'" + this.usuario + "', ";
        SQL += "'" + this.nombres + "', ";
        SQL += "'" + this.apellidos + "', ";
        SQL += "'" + this.password + "', ";
        SQL += this.numCelular + ", '";
        SQL += this.correo + "', '"; 
        SQL += this.fecha.toSqlDate() + "', '";
        SQL += this.genero + "')";

        int filas = conexion.ejecutarInstruccion(SQL);
        int ultimoID = conexion.ultimoID();

        this.id = ultimoID;
        conexion.cerrarConexion();

        return filas > 0;
    }
    
    public boolean actualizar(int idUsuario) throws SQLException{
        ConexionDB conexion = new ConexionDB();
        
        String SQL = "UPDATE usuarios SET " +
                 "usuario = '" + this.usuario + "', " +
                 "nombres = '" + this.nombres + "', " +
                 "apellidos = '" + this.apellidos + "', " +
                 "password = '" + this.password + "', " +
                 "correo = '" + this.correo + "', " +
                 "num_celular = " + this.numCelular + ", " +
                 "genero = '" + this.genero + "' " +
                 "WHERE id = " + idUsuario;
        
        int filas = conexion.ejecutarInstruccion(SQL);
        conexion.cerrarConexion();
        
        return filas > 0;
    }
}
