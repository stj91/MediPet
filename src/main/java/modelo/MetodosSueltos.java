package modelo;

import controlador.MenuPrincipalController;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Mascota;

public class MetodosSueltos {
    
    public static boolean validaNumeroReal(String texto){
        return texto.matches("^-?[0-9]+([\\.,][0-9]+)?$");
    }
    
    public static boolean validaNumeroEntero(String texto){
        return texto.matches("^-?[0-9]+$");
    }
    
    public static ObservableList<Mascota> cargarMascotas() throws SQLException{
        ObservableList<Mascota> obsAeropuertos = FXCollections.observableArrayList();
        Mascota mascota = new Mascota();
        
        ObservableList<Mascota> obsMascotas = mascota.getMascotas("", MenuPrincipalController.getUsuario().getId());
        
        for (Mascota m : obsMascotas){
            obsAeropuertos.add(m);
        }
        
        return obsAeropuertos;
    }
    
    public static void iconoVentana(Stage stage){
        stage.getIcons().add(new Image("/img/icon.png"));
    }
    
    public static void logoImagen(Stage stage){
        stage.getIcons().add(new Image("/img/logo.png"));
    }
    
    public static void iconoError(Stage stage){
        stage.getIcons().add(new Image("/img/advertencia.png"));
    }
    
    public static void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("¡Ups!");
        alert.setHeaderText("Ocurrió un problema");
        alert.setContentText(mensaje);

        // Estilizar
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        MetodosSueltos.iconoError(stage); // Tu ícono de error

        alert.getDialogPane().setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #bbdefb, #0d47a1);"
                + // De morado a azul
                "-fx-text-fill: white;"
                + "-fx-font-size: 14px;"
                + "-fx-font-family: 'Arial';"
        );

        // Estilo para el contenido
        Label contentLabel = new Label(mensaje);
        contentLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        alert.getDialogPane().setContent(contentLabel);

        alert.showAndWait();
    }
    
    public static void mostarVentana(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡ÉXITO!");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

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
        Label contentLabel = new Label(mensaje);
        contentLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        alert.getDialogPane().setContent(contentLabel);

        alert.showAndWait();
        
    }
}
