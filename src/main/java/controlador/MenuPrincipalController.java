package controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import java.sql.Date;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.WARNING;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import modelo.Cita;
import modelo.Fecha;
import modelo.Mascota;
import modelo.Propietario;
import modelo.Usuario;
import modelo.Vacuna;
import modelo.MetodosSueltos;

/**
 * FXML Controller class
 *
 * @author Homero Ramírez
 */
public class MenuPrincipalController implements Initializable {
    
    private static Usuario usuario;
    
    @FXML
    private ComboBox<Mascota> cmbMascotaSalud;
    @FXML
    private Label labelNombreSalud;
    @FXML
    private Label labelAmoSalud;
    @FXML
    private Label labelEspecieSalud;
    @FXML
    private Label labelRazaSalud;
    @FXML
    private Label labelSexoSalud;
    @FXML
    private TableView<Vacuna> tblCartilla;
    @FXML
    private Label labelTablaSalud;
    @FXML
    private TextField txtFiltroMascotas;
    @FXML
    private TableView<Mascota> tblMascotasPacientes;
    @FXML
    private Button btnBorrar;
    @FXML
    private ComboBox<String> cmbEspecie;
    @FXML
    private DatePicker dateNacimiento;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtSangre;
    @FXML
    private TextField txtNombreAmo;
    @FXML
    private TextField txtApellidoAmo;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtCelular;
    @FXML
    private TextField txtPeso;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private TableView<Cita> tblCitas;
    @FXML
    private ComboBox<Mascota> cmbMascotas;
    @FXML
    private DatePicker dateCita;
    @FXML
    private ComboBox<String> cmbHora;
    private TextField txtMotivo;
    @FXML
    private Button btnAgendar;
    @FXML
    private Button btnCancelarCita;
    @FXML
    private TableColumn<Cita, String> clFechaCitas;
    @FXML
    private TableColumn<Cita, String> clHoraCitas;
    @FXML
    private TableColumn<Cita, String> clMascotaCitas;
    @FXML
    private TableColumn<Cita, String> clMotivoCitas;
    @FXML
    private Button btnPacientes;
    @FXML
    private Button btnCitas;
    @FXML
    private Button btnPerfil;
    @FXML
    private Button btnSalud;
    @FXML
    private AnchorPane formInicio;
    @FXML
    private AnchorPane formSalud;
    @FXML
    private AnchorPane formPacientes;
    @FXML
    private AnchorPane formCitas;
    @FXML
    private AnchorPane formPerfil;
    @FXML
    private Button btnInicio;
    @FXML
    private Label labelCitasDia;
    @FXML
    private Label labelMascotasRegistradas;
    @FXML
    private Label labelGananciasDia;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private ComboBox<String> cmbRaza;
    @FXML
    private TableColumn<Mascota, Integer> colID;
    @FXML
    private TableColumn<Mascota, String> colNombre;
    @FXML
    private TableColumn<Mascota, String> colProp;
    @FXML
    private TableColumn<Mascota, String> colEspecie;
    @FXML
    private TableColumn<Mascota, String> colRaza;
    @FXML
    private TableColumn<Mascota, String> colSexo;
    @FXML
    private TableColumn<Mascota, Date> colNacimiento;
    @FXML
    private TableColumn<?, ?> colPropNombre;
    @FXML
    private TableColumn<?, ?> colPropApellido;
    @FXML
    private TableColumn<?, ?> colPropCel;
    @FXML
    private Button btnActualizarDatos;
    @FXML
    private ComboBox<Propietario> cmbProp;
    @FXML
    private Button btnNuevoProp;
    @FXML
    private Label labelNacimientoSalud;
    @FXML
    private TableColumn<Vacuna, String> clFechaRSalud;
    @FXML
    private TableColumn<Vacuna, String> clVacunaSalud;
    @FXML
    private TableColumn<Vacuna, String> colDosisSalud;
    @FXML
    private TableColumn<Vacuna, String> colVeterinarioSalud;
    @FXML
    private TableColumn<Vacuna, String> colFechaApSalud;
    @FXML
    private TableColumn<Cita, Double> colCostoCitas;
    @FXML
    private ComboBox<Propietario> cmbAmoCitas;
    @FXML
    private ComboBox<String> cmbMotivoCita;
    @FXML
    private TextField txtCostoCita;
    @FXML
    private TextField txtFiltroCitas;
    @FXML
    private Button btnCitaCompletada;
    @FXML
    private Button btnLimpiarCita;
    @FXML
    private TableColumn<Cita, String> colEstatusCita;
    @FXML
    private Button btndDetallesCita;
    @FXML
    private PieChart mascotasGrafica;
    @FXML
    private AreaChart<String, Number> graficaGanancias;
    @FXML
    private TextField txtCelularPerfil;
    @FXML
    private Button btnActualizarPerfil;
    @FXML
    private PasswordField passNuevaPerfil;
    @FXML
    private PasswordField passConfirmarPerfil;
    @FXML
    private TextField txtCorreoPerfil;
    @FXML
    private RadioButton rdbFemenino;
    @FXML
    private RadioButton rdbMasculino;
    @FXML
    private Label txtIDUsuarioPerfil;
    @FXML
    private Label txtNombreAzul;
    @FXML
    private Label txtCorreoAzul;
    @FXML
    private Label txtFechaAzul;
    @FXML
    private Label txtUserAzul;
    @FXML
    private Label txtGeneroAzul;
    @FXML
    private TextField txtUserPerfil;
    @FXML
    private Button btnCancelarPerfil;
    @FXML
    private Label txtUsuarioBienvenida;
    @FXML
    private Button btnAgregarVacunaSalud;
    @FXML
    private RadioButton rdbMacho;
    @FXML
    private RadioButton rdbHembra;
    @FXML
    private Button btnActMascota;

    public void inicioMascotasTotales() {
        try {
            Mascota m = new Mascota();

            int cont = m.mascotasTotal(usuario.getId());

            this.labelMascotasRegistradas.setText(String.valueOf(cont));
        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    public void inicioGanancias() {
        try {
            Cita c = new Cita();
            double cont = c.gananciaTotal(usuario.getId());

            this.labelGananciasDia.setText("$" + String.valueOf(cont));
        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    public void inicioCitasDia() {
        try {
            Cita c = new Cita();
            int cont = c.citasDia(usuario.getId());

            this.labelCitasDia.setText(String.valueOf(cont));
        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    public void iniciografica() {
        try {
            this.mascotasGrafica.getData().clear();
            Mascota m = new Mascota();
            Map<String, Integer> datos = m.graficaMascotas(usuario.getId());

            for (Map.Entry<String, Integer> entry : datos.entrySet()) {
                this.mascotasGrafica.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }

        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }
    
    public void graficaGanancias() {
        try {
            this.graficaGanancias.getData().clear();
            
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Ganancias");

            Cita c = new Cita();
            List<Pair<String, Double>> datos = c.gananciasGrafico(usuario.getId());

            for (Pair<String, Double> dato : datos) {
                series.getData().add(new XYChart.Data<>(dato.getKey(), dato.getValue()));
            }

            this.graficaGanancias.getData().add(series);
        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    public static Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        if (usuario != null) {
            MenuPrincipalController.usuario = usuario;
        } 
    }
    
    public void iniciarPerfil() {
        this.txtIDUsuarioPerfil.setText("");
    }
    
    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == btnPacientes) {
            formPacientes.setVisible(true);
            formInicio.setVisible(false);
            formSalud.setVisible(false);
            formCitas.setVisible(false);
            formPerfil.setVisible(false);
            
            borrarRegistroPaciente();
            this.cargarMascotas();
            this.iniciarProp();
            
        } else if (event.getSource() == btnSalud) {
            formPacientes.setVisible(false);
            formInicio.setVisible(false);
            formSalud.setVisible(true);
            formCitas.setVisible(false);
            formPerfil.setVisible(false);

            this.cmbMascotaSalud.getSelectionModel().clearSelection();
            tblCartilla.setItems(FXCollections.observableArrayList());
            this.actualizarPanelSalud();
            this.limpiarLabelsSalud();
            this.iniciarMascotasSalud();
            
        } else if (event.getSource() == btnCitas) {
            formPacientes.setVisible(false);
            formInicio.setVisible(false);
            formSalud.setVisible(false);
            formCitas.setVisible(true);
            formPerfil.setVisible(false);
            btndDetallesCita.setDisable(true);

            this.txtCostoCita.setText("");
            tblCitas.setItems(FXCollections.observableArrayList());
            limpiarCita();
            this.actualizarPanelCitas();
            this.cargarCitas();

        } else if (event.getSource() == btnPerfil) {
            formPacientes.setVisible(false);
            formInicio.setVisible(false);
            formSalud.setVisible(false);
            formCitas.setVisible(false);
            formPerfil.setVisible(true);
            
            this.iniciarDatosPerfil();
        } else if (event.getSource() == btnInicio) {
            formPacientes.setVisible(false);
            formInicio.setVisible(true);
            formSalud.setVisible(false);
            formCitas.setVisible(false);
            formPerfil.setVisible(false);

            this.inicioMascotasTotales();
            this.inicioGanancias();
            this.inicioCitasDia();
            this.iniciografica();
            this.graficaGanancias();
        }
    }

    public void iniciarAnimal() {
        try {
            Map<String, List<String>> datos;
            ObjectMapper mapper = new ObjectMapper();

            // Cargar el archivo con codificación ISO-8859-1
            InputStream is = getClass().getResourceAsStream("/animales/EspeciesRazas.json");
            InputStreamReader reader = new InputStreamReader(is, "ISO-8859-1");

            // Leer el JSON como mapa
            datos = mapper.readValue(reader, Map.class);

            // Agregar las especies al ComboBox
            cmbEspecie.getItems().addAll(datos.keySet());
            // Cambiar razas cuando se selecciona especie
            cmbEspecie.setOnAction(e -> {
                String especie = cmbEspecie.getValue();
                List<String> razas = datos.get(especie);
                if (razas != null) {
                    cmbRaza.getItems().setAll(razas);
                } else {
                    cmbRaza.getItems().clear(); // Limpiar si no hay razas
                }
            });
        } catch (IOException ex) {
            MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    public void iniciarMotivos() {
        try {
            Map<String, Double> datos;
            ObjectMapper mapper = new ObjectMapper();

            // Cargar el archivo con codificación ISO-8859-1
            InputStream is = getClass().getResourceAsStream("/animales/MotivosCostos.json");
            InputStreamReader reader = new InputStreamReader(is, "ISO-8859-1");

            // Leer el JSON como mapa
            datos = mapper.readValue(reader, new TypeReference<Map<String, Double>>() {
            });

            // Agregar las especies al ComboBox
            cmbMotivoCita.getItems().addAll(datos.keySet());
            // Cambiar razas cuando se selecciona especie
            cmbMotivoCita.setOnAction(e -> {
                String motivo = cmbMotivoCita.getValue();
                Double costo = datos.get(motivo);

                if (costo != null) {
                    txtCostoCita.setText(String.format("%.2f", costo));
                } else {
                    txtCostoCita.clear(); // Limpiar si no hay razas
                }

                if ("Vacuna".equals(motivo)) {
                    btndDetallesCita.setDisable(false);
                } else {
                    btndDetallesCita.setDisable(true);
                }
            });
        } catch (IOException ex) {
            MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    public void iniciarHorarios() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/animales/Horarios.json");
            InputStreamReader reader = new InputStreamReader(is, "ISO-8859-1");

            // Leer el JSON como lista de Strings
            List<String> horarios = mapper.readValue(reader, new TypeReference<List<String>>() {
            });

            // Agregar al ComboBox
            cmbHora.getItems().addAll(horarios);
        } catch (IOException ex) {
            MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }
    
    public void iniciarProp() {
        try {
            Propietario propietarios = new Propietario();
            ObservableList<Propietario> obs = propietarios.getTodosLosPropietarios(usuario.getId());
            this.cmbProp.getItems().setAll(obs);
        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    public void iniciarMascotasSalud() {
        try {
            Mascota m = new Mascota();
            ObservableList<Mascota> mascotas = m.getTodasLasMascotas(usuario.getId());
            this.cmbMascotaSalud.setItems(mascotas);
            
            if (mascotas.isEmpty()) {
                MetodosSueltos.mostrarAlerta("No hay mascotas registradas", Alert.AlertType.INFORMATION);
                limpiarLabelsSalud();
            }
            // Resto del código...
        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    public void datosCartilla(Mascota m) {
        if (m != null) {
            this.labelNombreSalud.setText(m.getNombre());
            this.labelEspecieSalud.setText(m.getAnimal());
            this.labelNacimientoSalud.setText(m.getNacimiento().verFecha());
            this.labelAmoSalud.setText(m.getNombreAmo() + " " + m.getApellidoAmo());
            this.labelRazaSalud.setText(m.getRaza());
            this.labelSexoSalud.setText(m.getGenero());
            
        } else {
            limpiarLabelsSalud(); // Limpiar si la mascota es null
            tblCartilla.setItems(FXCollections.observableArrayList());
        }
    }

    private void cargarVacunasDeMascota(Mascota m) {
        try {
            Vacuna v = new Vacuna();
            ObservableList<Vacuna> vacunas = v.getVacunasDeMascota(m.getId(), usuario.getId());
            
            if (vacunas.isEmpty()) {
                labelTablaSalud.setText("No hay registros de vacunas");
                tblCartilla.getItems().clear();
            } else {
                labelTablaSalud.setText("Historial de vacunas de: " + m.getNombre());
                tblCartilla.setItems(vacunas);
            }
        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    public void actualizarPanelCitas() {
        try {
            Propietario propietarios = new Propietario();
            ObservableList<Propietario> obs = propietarios.getTodosLosPropietarios(usuario.getId());
            this.cmbAmoCitas.getItems().setAll(obs);

            // Limpiar datos si no hay mascotas
            if (obs.isEmpty()) {
                limpiarCita(); //ahora para citas
            }

            this.cmbAmoCitas.setOnAction(e -> {
                Propietario aux = this.cmbAmoCitas.getValue();
                if (aux != null) {
                    this.datosCita(aux);
                } else {
                    limpiarCita(); // Limpiar si se deselecciona
                }
            });
        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }
    
    public void datosCita(Propietario p) {
        if (p != null) {
            try {
                ObservableList<Mascota> mascotas = p.getMascotasAmo(p.getId());
                this.cmbMascotas.setItems(mascotas);
                
            } catch (SQLException ex) {
                MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }
        } else {
            limpiarLabelsSalud(); // Limpiar si la mascota es null
            tblCartilla.setItems(FXCollections.observableArrayList());
        }
    }

    public void actualizarPanelSalud() {
        try {
            Mascota m = new Mascota();
            ObservableList<Mascota> mascotas = m.getTodasLasMascotas(usuario.getId());
            this.cmbMascotaSalud.setItems(mascotas);

            // Limpiar datos si no hay mascotas
            if (mascotas.isEmpty()) {
                limpiarLabelsSalud();
            }

            this.cmbMascotaSalud.setOnAction(e -> {
                Mascota aux = this.cmbMascotaSalud.getValue();
                if (aux != null) {
                    this.datosCartilla(aux);
                    this.cargarVacunasDeMascota(aux);
                } else {
                    limpiarLabelsSalud(); // Limpiar si se deselecciona
                }
            });
        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    private void limpiarLabelsSalud() {
        this.cmbMascotaSalud.getSelectionModel().clearSelection();
        this.labelTablaSalud.setText("Mascota no seleccionada...");
        this.labelNombreSalud.setText("");
        this.labelEspecieSalud.setText("");
        this.labelNacimientoSalud.setText("");
        this.labelAmoSalud.setText("");
        this.labelRazaSalud.setText("");
        this.labelSexoSalud.setText("");
        tblCartilla.getItems().clear();
    }
    
    public void cargarTodoInicio() {
        if (usuario != null) {
            this.inicioMascotasTotales();
            this.inicioGanancias();
            this.inicioCitasDia();
            this.iniciografica();
            this.graficaGanancias();
            this.txtUsuarioBienvenida.setText(usuario.getNombres());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        this.rdbMasculino.setToggleGroup(group);
        this.rdbFemenino.setToggleGroup(group);
        
        ToggleGroup genMascota = new ToggleGroup();
        this.rdbMacho.setToggleGroup(group);
        this.rdbHembra.setToggleGroup(group);
        
        //Pacientes
        this.colID.setCellValueFactory(new PropertyValueFactory("id"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPropNombre.setCellValueFactory(new PropertyValueFactory("nombreAmo"));
        this.colPropApellido.setCellValueFactory(new PropertyValueFactory("apellidoAmo"));
        this.colPropCel.setCellValueFactory(new PropertyValueFactory("celularAmo"));
        this.colEspecie.setCellValueFactory(new PropertyValueFactory("animal"));
        this.colRaza.setCellValueFactory(new PropertyValueFactory("raza"));
        //this.colSexo.setCellValueFactory(new PropertyValueFactory("sexo"));
        this.colNacimiento.setCellValueFactory(new PropertyValueFactory("nacimiento"));

        cmbEspecie.setItems(FXCollections.observableArrayList()); // Inicializa con lista vacía
        cmbRaza.setItems(FXCollections.observableArrayList());   // Inicializa con lista vacía
        cmbProp.setItems(FXCollections.observableArrayList());   // Inicializa con lista vacía
        this.iniciarAnimal();
        this.cmbMascotaSalud.setItems(FXCollections.observableArrayList());

        //Cartilla
        this.clFechaRSalud.setCellValueFactory(new PropertyValueFactory("fechaAplicacion"));
        this.clVacunaSalud.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colDosisSalud.setCellValueFactory(new PropertyValueFactory("dosisMl"));
        this.colVeterinarioSalud.setCellValueFactory(new PropertyValueFactory("veterinario"));
        this.colFechaApSalud.setCellValueFactory(new PropertyValueFactory("fechaSiguiente"));
        this.tblCartilla.setItems(FXCollections.observableArrayList());
        
        //Citas
        this.clFechaCitas.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.clHoraCitas.setCellValueFactory(new PropertyValueFactory("horario"));
        this.clMascotaCitas.setCellValueFactory(new PropertyValueFactory("pacienteMascota"));
        this.clMotivoCitas.setCellValueFactory(new PropertyValueFactory("motivo"));
        this.colCostoCitas.setCellValueFactory(new PropertyValueFactory("costo"));
        this.colEstatusCita.setCellValueFactory(new PropertyValueFactory("estatus"));
        this.tblCitas.setItems(FXCollections.observableArrayList());
        this.iniciarMotivos();
        this.iniciarHorarios();
    }

    @FXML
    private void verMascotas(ActionEvent event) {

    }

    private void cargarMascotas() {
        try {
            String busqueda = this.txtFiltroMascotas.getText();

            Mascota mas = new Mascota();
            ObservableList<Mascota> obs = mas.getMascotas(busqueda, usuario.getId());
            this.tblMascotasPacientes.setItems(obs);

        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void filtrarMascotas(KeyEvent event) {
        this.cargarMascotas();
    }

    @FXML
    private void borrarMascota(ActionEvent event) {

        Mascota m = (Mascota) this.tblMascotasPacientes.getSelectionModel().getSelectedItem();
        
        if (m == null) {
            MetodosSueltos.mostrarAlerta("Debe seleccionar una fila de la tabla...", WARNING);
        } else {
            Propietario p = m.getAmo();
            try {
                if (m.contarMascotas(usuario.getId())) {
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                    alerta.setTitle("Confirmar acción");
                    alerta.setHeaderText("AVISO");
                    alerta.setContentText("Este propietario ya no tendrá mascotas después de esta acción.\n¿Desea también eliminar al propietario?");

                    // Mostrar el diálogo y esperar respuesta
                    Optional<ButtonType> resultado = alerta.showAndWait();
                    
                    if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                        if (p.borrar(usuario.getId())) {
                            MetodosSueltos.mostarVentana("¡Se ha borrado la mascota de la base de datos!", Alert.AlertType.INFORMATION);
                        } else {
                            MetodosSueltos.mostrarAlerta("No se pudo eliminar la mascota...", Alert.AlertType.ERROR);
                        }
                    } 
                    
                } else {
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                    alerta.setTitle("Confirmar acción");
                    alerta.setHeaderText("AVISO");
                    alerta.setContentText("¿Seguro que desea eliminar a la mascota?");
                    
                    // Mostrar el diálogo y esperar respuesta
                    Optional<ButtonType> resultado = alerta.showAndWait();
                    
                    if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                        if (m.borrar(usuario.getId())) {
                            MetodosSueltos.mostarVentana("¡Se ha borrado la mascota de la base de datos!", Alert.AlertType.INFORMATION);
                        } else {
                            MetodosSueltos.mostrarAlerta("No se pudo eliminar la mascota...", Alert.AlertType.ERROR);
                        }
                    }
                }
            } catch (SQLException ex) {
                MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }

            this.cargarMascotas();
            this.tblMascotasPacientes.refresh();
        }
    }
    
    @FXML
    private void calendario(ActionEvent event) {
    }
    
    public void borrarRegistroPaciente() {
        this.borrarDatos();
        this.btnGuardar.setVisible(true);
        this.btnNuevoProp.setVisible(true);
        this.btnActMascota.setVisible(false);
        this.cmbProp.setDisable(false);
        this.tblMascotasPacientes.getSelectionModel().clearSelection();
        this.rdbHembra.setSelected(false);
        this.rdbMacho.setSelected(false);
        
        cmbEspecie.setDisable(false);
        cmbRaza.setDisable(false);
        rdbHembra.setDisable(false);
        rdbMacho.setDisable(false);
        dateNacimiento.setDisable(false);
        cmbProp.setDisable(false);
        txtSangre.setDisable(false);
        
        txtNombreAmo.setDisable(false);
        txtApellidoAmo.setDisable(false);
        txtEdad.setDisable(false);
        txtCelular.setDisable(false);
    }   

    @FXML
    private void cancelarRegistro(ActionEvent event) {
        this.borrarDatos();
        this.btnGuardar.setVisible(true);
        this.btnNuevoProp.setVisible(true);
        this.cmbProp.setDisable(false);
        this.tblMascotasPacientes.getSelectionModel().clearSelection();
        this.rdbHembra.setSelected(false);
        this.rdbMacho.setSelected(false);
        borrarRegistroPaciente();
    }
    

    @FXML
    private void registrarMascota(ActionEvent event) {

        boolean errores = false;

        String nombre = this.txtNombre.getText();
        String tipoSangre = this.txtSangre.getText();
        String nombreAmo = this.txtNombreAmo.getText();
        String apellidoAmo = this.txtApellidoAmo.getText();

        if (nombre.isBlank()) {
            errores = true;
        }
        if (tipoSangre.isBlank()) {
            errores = true;
        }
        if (nombreAmo.isBlank()) {
            errores = true;
        }
        if (apellidoAmo.isBlank()) {
            errores = true;
        }

        if (this.cmbEspecie.getValue() == null) {
            errores = true;
        }

        if (this.cmbRaza.getValue() == null) {
            errores = true;
        }

        if (this.dateNacimiento.getValue() == null) {
            errores = true;
        }

        if (!MetodosSueltos.validaNumeroReal(this.txtPeso.getText())) {
            errores = true;
        }

        if (!MetodosSueltos.validaNumeroEntero(this.txtEdad.getText())) {
            errores = true;
        }
        if (!MetodosSueltos.validaNumeroEntero(this.txtCelular.getText())) {
            errores = true;
        }
        
        if (this.rdbMacho.getToggleGroup().getSelectedToggle() == null) {
            errores = true;
        }

        Propietario seleccionado = cmbProp.getSelectionModel().getSelectedItem();

        if (!errores && seleccionado == null) { //Agrega Mascota y propietario nuevo
            try {
                String especie = this.cmbEspecie.getValue();
                String raza = this.cmbRaza.getValue();
                LocalDate nacimiento = this.dateNacimiento.getValue();
                Fecha n = new Fecha(nacimiento);
                Double peso = Double.parseDouble(this.txtPeso.getText());
                int edadAmo = Integer.parseInt(this.txtEdad.getText());
                long celular = Long.parseLong(this.txtCelular.getText());
                RadioButton seleccion = (RadioButton) this.rdbMacho.getToggleGroup().getSelectedToggle();
                String genero = seleccion.getText();
                
                Propietario p = new Propietario(nombreAmo, apellidoAmo, edadAmo, celular, usuario.getId());

                if (p.insertar()) {
                    Mascota aux = new Mascota(nombre, especie, raza, n, p, tipoSangre, peso, genero);
                    
                    if (aux.insertar()) {
                        MetodosSueltos.mostarVentana("La mascota ha sido registrada...", Alert.AlertType.INFORMATION);
                        this.cargarMascotas();
                        borrarRegistroPaciente();
                        borrarDatos();
                    }
                } else {
                    MetodosSueltos.mostrarAlerta("Por favor, completa todos los campos correctamente...", WARNING);
                }
            } catch (SQLException ex) {
                MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }
        } else if (!errores && seleccionado != null) { //Agrega mascota con propietario existente
            try {
                String especie = this.cmbEspecie.getValue();
                String raza = this.cmbRaza.getValue();
                LocalDate nacimiento = this.dateNacimiento.getValue();
                Fecha n = new Fecha(nacimiento);
                Double peso = Double.parseDouble(this.txtPeso.getText());
                int edadAmo = Integer.parseInt(this.txtEdad.getText());
                long celular = Long.parseLong(this.txtCelular.getText());
                RadioButton seleccion = (RadioButton) this.rdbMacho.getToggleGroup().getSelectedToggle();
                String genero = seleccion.getText();
                
                Mascota aux = new Mascota(nombre, especie, raza, n, seleccionado, tipoSangre, peso, genero);

                if (aux.insertar()) {
                    MetodosSueltos.mostarVentana("La mascota ha sido registrada...", Alert.AlertType.INFORMATION);
                    this.cargarMascotas();
                    borrarRegistroPaciente();
                    borrarDatos();
                } else {
                    MetodosSueltos.mostrarAlerta("Por favor, completa todos los campos correctamente...", WARNING);
                }
            } catch (SQLException ex) {
                MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }

        } else {
            MetodosSueltos.mostrarAlerta("Por favor, completa todos los campos correctamente...", WARNING);
        }
    }

    @FXML
    private void verMascotasCitas(ActionEvent event) {
    }

    @FXML
    private void verFechas(ActionEvent event) {
    }

    @FXML
    private void verHorarios(ActionEvent event) {
    }
    
    public int idMas = 0;
    
    @FXML
    private void agendarCita(ActionEvent event) {
        boolean errores = false;

        if (this.cmbAmoCitas.getValue() == null) {
            errores = true;
        }

        if (this.cmbMascotas.getValue() == null) {
            errores = true;
        }

        if (this.dateCita.getValue() == null) {
            errores = true;
        }

        if (this.cmbHora.getValue() == null) {
            errores = true;
        }

        if (this.cmbMotivoCita.getValue() == null) {
            errores = true;
        }

        if (!errores) {
            try {
                Propietario p = this.cmbAmoCitas.getValue();
                Mascota m = this.cmbMascotas.getValue();
                LocalDate fecha = this.dateCita.getValue();
                String motivo = this.cmbMotivoCita.getValue();
                String horario = this.cmbHora.getValue();
                double costo = Double.parseDouble(txtCostoCita.getText());
                Fecha f = new Fecha(fecha);
                
                idMas = m.getId();
                Cita cita = new Cita(f, horario, m, motivo, costo);

                if (cita.insertar(usuario.getId())) {
                    MetodosSueltos.mostarVentana("¡La cita ha sido registrada!", Alert.AlertType.INFORMATION);
                    this.cargarCitas();
                    limpiarCita();
                    tblCitas.refresh();
                    //idMas = 0;
                }
            } catch (SQLException ex) {
                MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }

        } else {
            MetodosSueltos.mostrarAlerta("Por favor, completa todos los campos correctamente...", WARNING);
        }
    }

    @FXML
    private void cancelarCita(ActionEvent event) {
        Cita seleccionada = this.tblCitas.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
            try {
                seleccionada.setEstatus("CANCELADA");
                seleccionada.actualizarEstatusEnDB(usuario.getId());
                MetodosSueltos.mostarVentana("La cita ha sido cancelada", WARNING);
                tblCitas.refresh();
            } catch (SQLException ex) {
                MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CERRAR SESIÓN");
        alert.setContentText("¿Deseas salir de la aplicación?");
        alert.setHeaderText(null);
        // Estilizar
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        MetodosSueltos.iconoVentana(stage); // Tu ícono de error

        alert.getDialogPane().setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #bbdefb, #0d47a1);"
                + // De morado a azul
                "-fx-text-fill: white;"
                + "-fx-font-size: 12px;"
                + "-fx-font-family: 'Arial';"
                + "-fx-font-weight: bold;"
        );

        // Estilo para el contenido
        Label contentLabel = new Label("¿Deseas salir de la aplicación?");
        contentLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        alert.getDialogPane().setContent(contentLabel);

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @FXML
    private void cargarRaza(ActionEvent event) {
    }

    public void borrarDatos() {
        txtNombre.clear();
        txtSangre.clear();
        txtPeso.clear();

        txtNombreAmo.clear();
        txtApellidoAmo.clear();
        txtEdad.clear();
        txtCelular.clear();

        cmbEspecie.getSelectionModel().clearSelection();
        cmbRaza.getSelectionModel().clearSelection();
        cmbProp.getSelectionModel().clearSelection();
        dateNacimiento.getEditor().clear();

    }

    private void limpiarRegistro(ActionEvent event) {
        borrarDatos();

    }

    @FXML
    private void agregarProp(ActionEvent event) {
        this.borrarDatos();
        this.cmbProp.setDisable(true);

        txtNombreAmo.clear();
        txtApellidoAmo.clear();
        txtEdad.clear();
        txtCelular.clear();

        txtNombreAmo.setDisable(false);
        txtApellidoAmo.setDisable(false);
        txtEdad.setDisable(false);
        txtCelular.setDisable(false);
    }

    @FXML
    private void seleccionProp(ActionEvent event) {
        txtNombreAmo.setDisable(true);
        txtApellidoAmo.setDisable(true);
        txtEdad.setDisable(true);
        txtCelular.setDisable(true);

        Propietario seleccionado = cmbProp.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            txtNombreAmo.setText(seleccionado.getNombre());
            txtApellidoAmo.setText(seleccionado.getApellido());
            txtEdad.setText(String.valueOf(seleccionado.getEdad()));
            txtCelular.setText(String.valueOf(seleccionado.getNumCelular()));
        }
    }
    
    int idAux = 0, idPropAux = 0;
    
    @FXML
    private void actualizarMascota(ActionEvent event) {
        Mascota m = (Mascota) this.tblMascotasPacientes.getSelectionModel().getSelectedItem();

        if (m != null) {
            idAux = m.getId();
            btnGuardar.setVisible(false);
            btnNuevoProp.setVisible(false);
            Propietario p = m.getAmo();
            idPropAux = p.getId();
            
            txtNombre.setText(m.getNombre());
            cmbEspecie.setValue(m.getAnimal());
            cmbRaza.setValue(m.getRaza());
            
            cmbEspecie.setDisable(true);
            cmbRaza.setDisable(true);
            
            if ("Macho".equals(m.getGenero())) {
                rdbMacho.setSelected(true);
                rdbMacho.setDisable(true);
                rdbHembra.setDisable(true);
            } else {
                rdbHembra.setSelected(true);
                rdbHembra.setDisable(true);
                rdbMacho.setDisable(true);
            }
            
            txtSangre.setText(m.getTipoSangre());
            txtSangre.setDisable(true);
            txtPeso.setText(String.valueOf(m.getPeso()));
            dateNacimiento.setValue(m.getNacimiento().toLocalDate());
            dateNacimiento.setDisable(true);
            cmbProp.setDisable(true);

            txtNombreAmo.setText(p.getNombre());
            txtApellidoAmo.setText(p.getApellido());
            txtEdad.setText(String.valueOf(p.getEdad()));
            txtCelular.setText(String.valueOf(p.getNumCelular()));
            btnNuevoProp.setVisible(false);
            btnGuardar.setVisible(false);
            btnActMascota.setVisible(true);
        } else {
            MetodosSueltos.mostrarAlerta("Debes seleccionar una mascota...", WARNING);
        }
    }

    public void cargarCitas() {
        try {
            String busqueda = this.txtFiltroCitas.getText();
            Cita cita = new Cita();
            ObservableList<Cita> obs = cita.getCitas(busqueda, usuario.getId());

            this.tblCitas.setItems(obs);

        } catch (SQLException ex) {
            MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void filtrarCitas(KeyEvent event) {
        cargarCitas();
    }

    @FXML
    private void citaCompletada(ActionEvent event) {
        Cita seleccionada = this.tblCitas.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
            try {
                seleccionada.setEstatus("REALIZADA");
                seleccionada.actualizarEstatusEnDB(usuario.getId());
                MetodosSueltos.mostarVentana("La cita ha sido completada", WARNING);
                tblCitas.refresh();
            } catch (SQLException ex) {
                MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }
        } else {
            MetodosSueltos.mostrarAlerta("Seleccione una fila de la tabla...", WARNING);
        }
    }
    
    public void limpiarCita() {
        btndDetallesCita.setDisable(false);
        btnLimpiarCita.setDisable(false);

        this.dateCita.getEditor().clear();
        this.cmbHora.getSelectionModel().clearSelection();
        this.cmbAmoCitas.getSelectionModel().clearSelection();
        this.cmbMascotas.getSelectionModel().clearSelection();
        this.cmbMotivoCita.getSelectionModel().clearSelection();
        this.txtCostoCita.setText("");
    }

    @FXML
    private void limpiarRegistroCita(ActionEvent event) {
        limpiarCita();
    }
    
    @FXML
    private void verDetallesMotivo(ActionEvent event) {
        boolean error = false;

        if (this.cmbMascotas.getValue() == null) {
            error = true;
        }

        if (!error) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/AgregarVacuna.fxml"));
                Parent root = loader.load();
                AgregarVacunaController controller = loader.getController();

                Mascota aux = this.cmbMascotas.getValue();
                
                if (aux != null) {
                    controller.setIdMascota(aux.getId());
                    controller.setVeterinario(usuario.getNombres() + " " + usuario.getApellidos());
                }

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL); //abre ventana y no se puede abrir otra
                stage.setScene(scene);
                stage.setTitle("Registrar Vacuna");
                stage.show();
                MetodosSueltos.iconoVentana(stage);
                
                btndDetallesCita.setDisable(true);
                btnLimpiarCita.setDisable(true);
            } catch (IOException ex) {
                MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }
        } else {
            MetodosSueltos.mostrarAlerta("Por favor, seleccione una mascota...", WARNING);
        }
    }
    
    public void iniciarDatosPerfil() {
        this.txtIDUsuarioPerfil.setText(String.valueOf(usuario.getId()));
        this.txtUserAzul.setText(usuario.getUsuario());
        this.txtNombreAzul.setText(usuario.getNombres() + " " + usuario.getApellidos());
        this.txtGeneroAzul.setText(usuario.getGenero());
        this.txtCorreoAzul.setText(usuario.getCorreo());
        this.txtFechaAzul.setText(String.valueOf(usuario.getFecha()));
    }

    @FXML
    private void limpiarRegistroPerfil(ActionEvent event) {
        this.borrarDatosPerfil();
    }
    
    public void borrarDatosPerfil() {
        this.txtCorreoPerfil.setText("");
        this.passNuevaPerfil.setText("");
        this.passConfirmarPerfil.setText("");
        this.txtUserPerfil.setText("");
        this.txtCelularPerfil.setText("");
        
        this.rdbFemenino.setSelected(false);
        this.rdbMasculino.setSelected(false);
    }

    @FXML
    private void actualizarPerfil(ActionEvent event) {
        boolean errores = false;
        
        String correo = this.txtCorreoPerfil.getText();
        String pass = this.passNuevaPerfil.getText();
        String passConf = this.passConfirmarPerfil.getText();
        String user = this.txtUserPerfil.getText();
        
        if (correo.isBlank()) {
            errores = true;
        }
        
        if (pass.isBlank() || passConf.isBlank()) {
            errores = true;
        }
        
        if (passConf.isBlank() || !passConf.equals(pass)) {
            errores = true;
        }
        
        if (user.isBlank()) {
            errores = true;
        }
        
        if (!MetodosSueltos.validaNumeroEntero(this.txtCelularPerfil.getText())) {
            errores = true;
        }
        //checar que lo haga bien!!!
        if (this.rdbMasculino.getToggleGroup().getSelectedToggle() == null) {
            errores = true;
        }
        
        if (!errores) {
            try {
                int id = usuario.getId();
                long numCelular = Long.parseLong(this.txtCelularPerfil.getText());
                RadioButton seleccion = (RadioButton) this.rdbMasculino.getToggleGroup().getSelectedToggle();
                String genero = seleccion.getText();
                
                Usuario aux = new Usuario(user, passConf, numCelular, correo, genero);
                aux.setId(id);
                aux.setNombres(usuario.getNombres());
                aux.setApellidos(usuario.getApellidos());
                aux.setFecha(usuario.getFecha());
                
                this.setUsuario(aux);
                
                if (aux.actualizar(id)) {
                    MetodosSueltos.mostarVentana("Los datos han sido actualizados...", Alert.AlertType.INFORMATION);
                    this.iniciarDatosPerfil();
                    this.borrarDatosPerfil();
                }
            } catch (SQLException ex) {
                MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }
            
        } else {
            MetodosSueltos.mostrarAlerta("Por favor, completa todos los campos correctamente...", WARNING);
        }
        
    }
    
    @FXML
    private void registroVacuna(ActionEvent event) {
        boolean error = false;

        if (this.cmbMascotaSalud.getValue() == null) {
            error = true;
        }
        
        if (!error) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/AgregarVacuna.fxml"));
                Parent root = loader.load();
                AgregarVacunaController controller = loader.getController();
                
                Mascota aux = this.cmbMascotaSalud.getValue();
                
                if (aux != null) {
                    controller.setIdMascota(aux.getId());
                    controller.setVeterinario(usuario.getNombres() + " " + usuario.getApellidos());
                }

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL); //abre ventana y no se puede abrir otra
                stage.setScene(scene);
                stage.setTitle("Registrar Vacuna");
                stage.show();
                MetodosSueltos.iconoVentana(stage);
            } catch (IOException ex) {
                MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }
        } else {
            MetodosSueltos.mostrarAlerta("Por favor, seleccione una mascota...", WARNING);
        }
    } 

    @FXML
    private void actualizarDatosMascota(ActionEvent event) {
        boolean errores = false;

        String nombre = this.txtNombre.getText();
        String tipoSangre = this.txtSangre.getText();
        String nombreAmo = this.txtNombreAmo.getText();
        String apellidoAmo = this.txtApellidoAmo.getText();

        if (nombre.isBlank()) {
            errores = true;
        }
        if (tipoSangre.isBlank()) {
            errores = true;
        }
        if (nombreAmo.isBlank()) {
            errores = true;
        }
        if (apellidoAmo.isBlank()) {
            errores = true;
        }

        if (!MetodosSueltos.validaNumeroReal(this.txtPeso.getText())) {
            errores = true;
        }

        if (!MetodosSueltos.validaNumeroEntero(this.txtEdad.getText())) {
            errores = true;
        }
        if (!MetodosSueltos.validaNumeroEntero(this.txtCelular.getText())) {
            errores = true;
        }

        if (!errores) { //Agrega Mascota y propietario nuevo
            try {
                String especie = this.cmbEspecie.getValue();
                String raza = this.cmbRaza.getValue();
                LocalDate nacimiento = this.dateNacimiento.getValue();
                Fecha n = new Fecha(nacimiento);
                Double peso = Double.parseDouble(this.txtPeso.getText());
                int edadAmo = Integer.parseInt(this.txtEdad.getText());
                long celular = Long.parseLong(this.txtCelular.getText());
                RadioButton seleccion = (RadioButton) this.rdbMacho.getToggleGroup().getSelectedToggle();
                String genero = seleccion.getText();
                
                Propietario p = new Propietario(nombreAmo, apellidoAmo, edadAmo, celular);
                
                if (p.actualizar(idPropAux)) {
                    Mascota aux = new Mascota(nombre, especie, raza, n, p, tipoSangre, peso, genero);
                    
                    if (aux.actualizar(idAux, idPropAux)) {
                        MetodosSueltos.mostarVentana("La mascota ha sido actualizada...", Alert.AlertType.INFORMATION);
                        this.cargarMascotas();
                        borrarRegistroPaciente();
                        borrarDatos();
                    }
                } else {
                    MetodosSueltos.mostrarAlerta("Por favor, completa todos los campos correctamente...", WARNING);
                }
            } catch (SQLException ex) {
                MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }
        }  else {
            MetodosSueltos.mostrarAlerta("Por favor, completa todos los campos correctamente...", WARNING);
        }
    }
}
