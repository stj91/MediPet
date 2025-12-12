package controlador;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import modelo.Fecha;
import modelo.Usuario;
import modelo.MetodosSueltos;

/**
 * FXML Controller class
 *
 * @author Homero Ramírez
 */
public class RegistrarController implements Initializable {

    @FXML
    private TextField txtNombresPerfil;
    @FXML
    private TextField txtApellidosPerfil;
    @FXML
    private TextField txtCelularPerfil;
    @FXML
    private TextField txtUserPerfil;
    @FXML
    private RadioButton rdbFemenino;
    @FXML
    private PasswordField passNuevaPerfil;
    @FXML
    private PasswordField passConfirmarPerfil;
    @FXML
    private TextField txtCorreoPerfil;
    @FXML
    private RadioButton rdbMasculino;
    @FXML
    private Button btnRegistrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        this.rdbMasculino.setToggleGroup(group);
        this.rdbFemenino.setToggleGroup(group);
    }    

    @FXML
    private void registrarPerfil(ActionEvent event) {
        boolean errores = false;
        
        String nombres = this.txtNombresPerfil.getText();
        String apellidos = this.txtApellidosPerfil.getText();
        String correo = this.txtCorreoPerfil.getText();
        String pass = this.passNuevaPerfil.getText();
        String passConf = this.passConfirmarPerfil.getText();
        String user = this.txtUserPerfil.getText();
        
        if (nombres.isBlank()) {
            errores = true;
        }
        
        if (apellidos.isBlank()) {
            errores = true;
        }
        
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
        
        if (this.rdbMasculino.getToggleGroup().getSelectedToggle() == null) {
            errores = true;
        }
        
        if (!errores) {
            try {
                long numCelular = Long.parseLong(this.txtCelularPerfil.getText());
                RadioButton seleccion = (RadioButton) this.rdbMasculino.getToggleGroup().getSelectedToggle();
                String genero = seleccion.getText();
                LocalDate local = LocalDate.now();
                Fecha f = new Fecha(local);
                
                Usuario aux = new Usuario(user, nombres, apellidos, passConf, numCelular, correo, f, genero);
                
                if (aux.insertar()) {
                    MetodosSueltos.mostarVentana("¡La cuenta ha sido creado éxitosamente!", Alert.AlertType.INFORMATION);
                    Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
                    myStage.close();
                }
            } catch (SQLException ex) {
                MetodosSueltos.mostrarAlerta("Error: " + ex.getErrorCode() + ".\nAlgo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
            }
        } else {
            MetodosSueltos.mostrarAlerta("Por favor, completa todos los campos correctamente...", Alert.AlertType.WARNING);
        }
    }
}
