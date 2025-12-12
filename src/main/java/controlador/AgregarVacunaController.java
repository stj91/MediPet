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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Fecha;
import modelo.Vacuna;
import modelo.MetodosSueltos;

/**
 * FXML Controller class
 *
 * @author Homero Ramírez
 */
public class AgregarVacunaController implements Initializable {

    @FXML
    private TextField txtNombreVacuna;
    @FXML
    private TextField txtDosisVacuna;
    @FXML
    private DatePicker dpFechaAplicacion;
    @FXML
    private DatePicker dpFechaReaplicacion;
    @FXML
    private Button btnGuardarVacuna;
    
    private int idMascota;
    private String veterinario;

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    @FXML
    private void guardar(ActionEvent event) {
        boolean errores = false;
        
        String nombre = this.txtNombreVacuna.getText();
        
        if (nombre.isBlank()) {
            errores = true;
        }
        
        if (this.dpFechaAplicacion.getValue() == null) {
            errores = true;
        }
        
        if (this.dpFechaReaplicacion.getValue() == null) {
            errores = true;
        }
        
        if (!MetodosSueltos.validaNumeroReal(this.txtDosisVacuna.getText())) {
            errores = true;
        }
        
        if (!errores) {
            try {
                double dosis = Double.parseDouble(this.txtDosisVacuna.getText());
                LocalDate aplicacion = this.dpFechaAplicacion.getValue();
                LocalDate reapli = this.dpFechaReaplicacion.getValue();
                Fecha f1 = new Fecha(aplicacion);
                Fecha f2 = new Fecha(reapli);
                
                Vacuna v = new Vacuna(nombre, dosis, f1, f2, veterinario, idMascota);
                
                if (v.insertar()) {
                    MetodosSueltos.mostarVentana("¡La vacuna ha sido registrada!", Alert.AlertType.INFORMATION);
                    Stage myStage = (Stage) this.btnGuardarVacuna.getScene().getWindow();
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
