

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase Principal del programa
 * @author SANTIAGO LEY
 * @version 1.0
 */
public class Agenda extends Application {
    /**
     * Metodo start de la Clase Agenda, el cual viene de Application, este carga el FXML con la estructura de la interfaz grafica
     * @param stage of Class Stage
     * @throws Exception of the FXMLLoader.load()
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * metodo main de la clase
     * @param args no hace nada, el main ejecuta la Application (Crea la interfaz grafica)
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
