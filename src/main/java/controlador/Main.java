package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import modelo.MetodosSueltos;

/**
 * JavaFX Main
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/LoginVista.fxml"));
            
            Pane ventana = (Pane) loader.load();
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            primaryStage.setTitle("Iniciar Sesión");
            primaryStage.show();
            MetodosSueltos.iconoVentana(primaryStage);
            
        } catch (IOException ex) {
            MetodosSueltos.mostrarAlerta("Algo ocurrió mal. Intente de nuevo...", Alert.AlertType.ERROR);
        }
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}