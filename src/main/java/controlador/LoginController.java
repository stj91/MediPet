package controlador;

import modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.MetodosSueltos;

/**
 * FXML Controller class
 *
 * @author Homero RamÃ­rez
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtUsuario;
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnRegistrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void comprobarLogin(ActionEvent event) throws SQLException {
        try {
            String usuario = this.txtUsuario.getText();
            String password = this.txtPass.getText();
            
            Usuario u = new Usuario(usuario, password);
            Usuario usuarioAutenticado = u.login();
            
            if (usuarioAutenticado != null){
                MetodosSueltos.mostarVentana("Inicio de sesión correcto", Alert.AlertType.INFORMATION);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuPrincipalVista.fxml"));
                
                Parent root = loader.load();
                
                MenuPrincipalController controller = loader.getController();
                controller.setUsuario(usuarioAutenticado);
                controller.cargarTodoInicio();
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("MediPet");
                stage.show();
                MetodosSueltos.iconoVentana(stage);
                
                Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
                myStage.close();
                
            } else {
                MetodosSueltos.mostrarAlerta("Inicio de sesión incorrecto...", Alert.AlertType.ERROR);
            }
        } catch (IOException | RuntimeException ex) {
            MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/RegistrarVista.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Crear Cuenta");
            stage.show();
            MetodosSueltos.iconoVentana(stage);
        } catch (IOException ex) {
            MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    }
}
