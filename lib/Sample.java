
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Sample
{
    @FXML
    private VBox zona;
    @FXML
    private BorderPane todo;
    @FXML
    private TextField nombre_tf;
    @FXML
    private TextArea descripcion_ta;
    @FXML
    private TextField materia_tf;
    @FXML
    private TextField fecha_tf;
    @FXML
    private MenuButton tipo_mb;
    @FXML
    private MenuButton prioridad_mb;
    @FXML
    private Label label;
    @FXML
    private Stage info;
    @FXML
    private VBox pane2;

    private boolean proyecto_ok()
    {
        if(!tipo_mb.getText().equals("Proyecto"))
            return false;
        if(prioridad_mb.getText().equals("Prioridad"))
            return false;
        if(nombre_tf.getText().length() < 1)
            return false;
        if(materia_tf.getText().length() < 1)
            return false;
        return fecha_tf.getText().length() >= 1;
    }

    private boolean tarea_ok()
    {
        if(!tipo_mb.getText().equals("Tarea")){
            label.setText("Error: No especifica el tipo.");
            return false;
        }
        if(prioridad_mb.getText().equals("Prioridad")){
            label.setText("Error: no especifica la prioridad.");
            return false;
        }
        if(nombre_tf.getText().length() < 1){
            label.setText("Error: no especifica el nombre.");
            return false;
        }
            
        if(materia_tf.getText().length() < 1){
            label.setText("Error: no especifica la materia.");
            return false;
        }
        boolean fecha_ok = true;
        String fecha_txt = fecha_tf.getText();
        if(fecha_txt.length() != 10){
            label.setText("Error: Formato de fecha incorrecto.");
            return false;
        }

        int a[] = {0,1,3,4,6,7,8,9};
        int c[] = {2,5};
        for(int b : a){
            if(fecha_txt.charAt(b)>'9' || fecha_txt.charAt(b)<'0'){
                label.setText("Error: Formato de fecha incorrecto.");
                return false;
            }
        }
        for(int b : c){
            if(fecha_txt.charAt(b) != '/'){
                label.setText("Error: Formato de fecha incorrecto.");
                return false;
            }
        }

        return true;
    }
    /**
     * Metodo que se ejecuta al presionar "Nuevo Pendiente"
     * Guarda la informacion en la base de datos si esta es correcta.
     * Devuelve algun error en el label de la interfaz grafica si los datos no son correctos.
     */
    public void nuevo()
    {
        if(tarea_ok())
        {
            nueva_tarea();
            nombre_tf.setText("");
            descripcion_ta.setText("");
            materia_tf.setText("");
            fecha_tf.setText("");
            tipo_mb.setText("Tipo");
            prioridad_mb.setText("Prioridad");
            label.setText("Tarea Agregada a la base de Datos");

            nombre_tf.setDisable(true);
            materia_tf.setDisable(true);
            fecha_tf.setDisable(true);
            descripcion_ta.setDisable(true);
        } else
        if(proyecto_ok())
        {
            nuevo_proyecto();
            nombre_tf.setText("");
            descripcion_ta.setText("");
            materia_tf.setText("");
            fecha_tf.setText("");
            tipo_mb.setText("Tipo");
            prioridad_mb.setText("Prioridad");
            label.setText("Proyecto Agregado a la base de Datos");

            nombre_tf.setDisable(true);
            materia_tf.setDisable(true);
            fecha_tf.setDisable(true);
            descripcion_ta.setDisable(true);
        }
    }

    private void nuevo_proyecto()
    {
        nueva_tarea();
    }

    private void elimina_tarea(Row row)
    {
        Controlador.eliminaTarea(row.getIdx(), row.get("NOMBRE"), row.get("DESCRIPCION"), row.get("TIPO"), row.get("NIVEL"), row.get("MATERIA"), row.get("FECHA"));
    }

    private void nueva_tarea()
    {
        Controlador.nuevaTarea(nombre_tf.getText(), descripcion_ta.getText(), tipo_mb.getText(), prioridad_mb.getText(), materia_tf.getText(), fecha_tf.getText());
    }
    /**
     * Metodo que cambia el estado del menu de prioridad a MUY ALTA
     */
    public void muy_alta()
    {
        prioridad_mb.setText("MUY ALTA");
    }
    /**
     * Metodo que cambia el estado del menu de prioridad a MEDIA
     */
    public void media()
    {
        prioridad_mb.setText("MEDIA");
    }
    /**
     * Metodo que cambia el estado del menu de prioridad a ALTA
     */
    public void alta()
    {
        prioridad_mb.setText("ALTA");
    }
    /**
     * Metodo que cambia el estado del menu de tipo a Tarea
     */
    public void tipo_tarea()
    {
        tipo_mb.setText("Tarea");
        nombre_tf.setDisable(false);
        materia_tf.setDisable(false);
        fecha_tf.setDisable(false);
        descripcion_ta.setDisable(false);
    }
    /**
     * Metodo que cambia el estado del menu de tipo a Proyecto
     */
    public void tipo_proyecto()
    {
        tipo_mb.setText("Proyecto");
        nombre_tf.setDisable(false);
        materia_tf.setDisable(false);
        fecha_tf.setDisable(false);
        descripcion_ta.setDisable(false);
    }
    
    private void popUpTarea(Row row)
    {
        pane2 = new VBox();
        Label label1 = new Label((new StringBuilder()).append("Nombre: ").append(row.get("NOMBRE")).toString());
        Label label2 = new Label((new StringBuilder()).append("Descripcion: ").append(row.get("DESCRIPCION")).toString());
        Label label3 = new Label((new StringBuilder()).append("Tipo: ").append(row.get("TIPO")).toString());
        Label label4 = new Label((new StringBuilder()).append("Prioridad: ").append(row.get("NIVEL")).toString());
        Label label5 = new Label((new StringBuilder()).append("Materia: ").append(row.get("MATERIA")).toString());
        Label label6 = new Label((new StringBuilder()).append("Fecha de entrega: ").append(row.get("FECHA")).toString());
        Button button = new Button("Terminado");
        button.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                elimina_tarea(row);
                todoClicked();
                info.close();
            }
        }
        );
        button.setPrefWidth(pane2.getWidth());
        VBox.setVgrow(button, Priority.ALWAYS);
        button.setMaxWidth(1.7976931348623157E+308D);
        pane2.getChildren().addAll(new Node[] {
            label1, label2, label3, label4, label5, label6, button
        });
        Scene scene = new Scene(pane2, 600D, 150D);
        info = new Stage();
        info.setScene(scene);
        info.initModality(Modality.APPLICATION_MODAL);
        info.setTitle("Informaci\363n Completa");
        label.setText("Ventana Abierta");
        info.showAndWait();
        label.setText("Ventana Cerrada");
    }

    /**
     * Metodo que abre una ventana al hacer click en "Ayuda", la ventana tiene información de ayuda.
     */
    public void popUpInfo()
    {
        pane2 = new VBox();
        TextArea textarea = new TextArea("Este programa sirve para guardar informaci\363n de tus tareas y proyectos futuros.\nEn la parte superior se puede llenar de alguna tarea o proyecto que se quiera agregar al registro, una vez finalizado el llenar los campos se oprime el boton de 'NUEVO PENDIENTE' para guardar la informacion.\nPara cargar todos los trabajos pendientes se oprime el boton de 'Todo'.\nPara cargar solo las tareas se oprime 'Tareas'.\nPara cargar solo los proyectos se oprime 'Proyectos'.\nPara limpiar la zona de los trabajos pendientes se oprime 'Clear'.\nPara ver la informacion completa de un trabajo, se le hace click al trabajo deseado.");
        textarea.setWrapText(true);
        pane2.getChildren().add(textarea);
        Scene scene = new Scene(pane2, 800D, 300D);
        info = new Stage();
        info.setScene(scene);
        info.initModality(Modality.APPLICATION_MODAL);
        info.setTitle("Información del Programa");
        label.setText("Ventana Abierta");
        info.showAndWait();
        label.setText("Ventana Cerrada");
    }
    /**
     * Metodo que genera los botones de los trabajos pendientes sacados de la base de datos, al hacer click en "Todo"
     */
    public void todoClicked()
    {
        clear();
        InfoManager infomanager = Controlador.getTodo();
        for(int i = 0; i < infomanager.length(); i++)
        {
            Row row = infomanager.getRow(i);
            Button button = new Button((new StringBuilder()).append("").append(row.get("FECHA")).append(", ").append(row.get("NOMBRE")).toString());
            
            button.setOnAction( new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    popUpTarea(row);
                }
            }
            );
            button.setPrefWidth(todo.getWidth());
            VBox.setVgrow(button, Priority.ALWAYS);
            button.setMaxWidth(1.7976931348623157E+308D);
            zona.getChildren().add(button);
            label.setText("Todo Cargado");
        }

    }
    /**
     * Metodo que genera los botones de las tareas pendientes sacados de la base de datos, al hacer click en "Tarea"
     */
    public void tareasClicked()
    {
        clear();
        InfoManager infomanager = Controlador.getTareas();
        for(int i = 0; i < infomanager.length(); i++)
        {
            Row row = infomanager.getRow(i);
            Button button = new Button((new StringBuilder()).append("").append(row.get("FECHA")).append(", ").append(row.get("NOMBRE")).toString());
            
            button.setOnAction( new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    popUpTarea(row);
                }
            }
            );
            
            button.setPrefWidth(todo.getWidth());
            VBox.setVgrow(button, Priority.ALWAYS);
            button.setMaxWidth(1.7976931348623157E+308D);
            zona.getChildren().add(button);
        }

        label.setText("Tareas Cargadas");
    }
    /**
     * Metodo que genera los botones de los proyectos pendientes sacados de la base de datos, al hacer click en "Proyecto"
     */
    public void proyectosClicked()
    {
        clear();
        InfoManager infomanager = Controlador.getProyectos();
        for(int i = 0; i < infomanager.length(); i++)
        {
            Row row = infomanager.getRow(i);
            Button button = new Button((new StringBuilder()).append("").append(row.get("FECHA")).append(", ").append(row.get("NOMBRE")).toString());
            
            button.setOnAction( new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    popUpTarea(row);
                }
            }
            );
            
            button.setPrefWidth(todo.getWidth());
            VBox.setVgrow(button, Priority.ALWAYS);
            button.setMaxWidth(1.7976931348623157E+308D);
            zona.getChildren().add(button);
        }

        label.setText("Proyectos Cargados");
    }
    /**
     * Metodo que limpia el area donde aparecen los botones que hay en la interfaz grafica, al hacer click en "Todo"
     */
    public void clear()
    {
        zona.getChildren().clear();
        label.setText("Vaciado");
    }

    


}