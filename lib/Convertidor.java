
import java.io.PrintStream;
import java.util.HashMap;


/**
 * Clase Convertidor usado para convertir Strings a comandos en SQL, ejecutarlos y obtener la informacion
 * @author Santiago Ley
 * @version 1.0
 */

public class Convertidor
{
    /**
     * Constructor de la clase Convertidor
     * inicializa sus atributos
     */
    public Convertidor(){
        inicializar();
    }

    /**
     * Metodo que recibe la informacion de una tarea y la elimina de la base de datos SQLite
     * @param i el indice de la tarea
     * @param s el nombre de la tarea
     * @param s1 la descripcion de la tarea
     * @param s2 el tipo de la tarea
     * @param s3 la prioridad de la tarea
     * @param s4 la materia de la tarea
     * @param s5 la fecha de la tarea
     */
    public void borrarTarea(int i, String s, String s1, String s2, String s3, String s4, String s5)
    {
        SQLiteManager.executeUpdateSQL((new StringBuilder()).append("DELETE FROM PRINCIPAL_PRIORIDAD WHERE ID_PRINCIPAL = ").append(i).append(";").toString());
        SQLiteManager.executeUpdateSQL((new StringBuilder()).append("DELETE FROM PRINCIPAL_TIPO WHERE ID_PRINCIPAL = ").append(i).append(";").toString());
        SQLiteManager.executeUpdateSQL((new StringBuilder()).append("DELETE FROM PRINCIPAL_MATERIA WHERE ID_PRINCIPAL = ").append(i).append(";").toString());
        SQLiteManager.executeUpdateSQL((new StringBuilder()).append("DELETE FROM PRINCIPAL_FECHA_FINAL WHERE ID_PRINCIPAL = ").append(i).append(";").toString());
        SQLiteManager.executeUpdateSQL((new StringBuilder()).append("DELETE FROM PRINCIPAL WHERE ID = ").append(i).append(";").toString());
        SQLiteManager.executeUpdateSQL((new StringBuilder()).append("DELETE FROM MATERIA WHERE ID = ").append(i).append(";").toString());
        SQLiteManager.executeUpdateSQL((new StringBuilder()).append("DELETE FROM FECHA_FINAL WHERE ID = ").append(i).append(";").toString());
    }
    /**
     * Metodo que reinicia la base de datos
     * Elimina todas las relaciones y todos los datos menos de las tablas: tipo y prioridad
     */
    public void reset()
    {
        boolean flag = true;
        String s = "DELETE FROM PRINCIPAL;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");

        s = "DELETE FROM UBICACION;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");

        s = "DELETE FROM PROGRESS;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");

        s = "DELETE FROM FECHA_FINAL;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");
        s = "DELETE FROM MATERIA;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");
        s = "DELETE FROM PRINCIPAL_PRIORIDAD;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");
        s = "DELETE FROM PRINCIPAL_TIPO;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");
        s = "DELETE FROM PRINCIPAL_GENERO;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");
        s = "DELETE FROM PRINCIPAL_UBICACION;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");
        s = "DELETE FROM PRINCIPAL_PROGRESS;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");
        s = "DELETE FROM PRINCIPAL_FECHA_FINAL;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");
        s = "DELETE FROM PRINCIPAL_MATERIA;";
        flag = SQLiteManager.executeUpdateSQL(s);
        if(!flag)
            System.out.println("Error en RESET");
        idx = 1;
    }
    private void inicializar()
    {
        tipos = new HashMap<String, Integer>();
        prioridades = new HashMap<String, Integer>();
        generos = new HashMap<String, Integer>();
        String as[] = {
            "Tarea", "Proyecto", "Pelicula", "Libro", "Tramite", "Cita", "Serie", "Juego", "Video", "Presupuesto", 
            "Otro"
        };
        for(int i = 0; i < as.length; i++)
            tipos.put(as[i], Integer.valueOf(i + 1));

        String as1[] = {
            "MUY ALTA", "ALTA", "MEDIA", "BAJA"
        };
        for(int j = 0; j < as1.length; j++)
            prioridades.put(as1[j], Integer.valueOf(j + 1));

        String as2[] = {
            "Drama", "Comedia", "AcciÃ³n", "Aventura", "Terror", "Ciencia FicciÃ³n", "Romance", "Musical", "Tragedia", "Suspenso", 
            "FantasÃ­a", "Informativo", "Porno", "Lucha", "Arcade", "Plataformas", "Disparos", "Estrategia", "Carreras", "Deporte", 
            "RPG", "Sandbox", "Logica"
        };
        for(int k = 0; k < as2.length; k++)
            generos.put(as2[k], Integer.valueOf(k + 1));

        generateIdx();
    }

    private boolean insertarBasico(String s, String s1, String s2, String s3)
    {
        boolean flag = true;
        String s4 = (new StringBuilder()).append("INSERT INTO PRINCIPAL VALUES(").append(idx).append(", '").append(s).append("', '").append(s1).append("');").toString();
        flag = SQLiteManager.executeUpdateSQL(s4);
        if(!flag)
        {
            System.out.println("Error en PRINCIPAL");
            return false;
        }
        int i = ((Integer)prioridades.get(s3)).intValue();
        int j = ((Integer)tipos.get(s2)).intValue();
        s4 = (new StringBuilder()).append("INSERT INTO PRINCIPAL_PRIORIDAD VALUES(").append(idx).append(", ").append(i).append(");").toString();
        flag = SQLiteManager.executeUpdateSQL(s4);
        if(!flag)
        {
            System.out.println("Error en PRINCIPAL_PRIORIDAD");
            return false;
        }
        s4 = (new StringBuilder()).append("INSERT INTO PRINCIPAL_TIPO VALUES(").append(idx).append(", ").append(j).append(");").toString();
        flag = SQLiteManager.executeUpdateSQL(s4);
        if(!flag)
        {
            System.out.println("Error en PRINCIPAL_TIPO");
            return false;
        }
        return true;
        
    }
    /**
     * Metodo que inserta una tarea en la base de datos
     * @param s el nombre de la tarea
     * @param s1 la descripcion de la tarea
     * @param s2 el tipo de la tarea
     * @param s3 la prioridad de la tarea
     * @param s4 la materia de la tarea
     * @param s5 la fecha de la tarea
     * @return true si fue exitoso, false de lo contrario
     */
    public boolean insertarTarea(String s, String s1, String s2, String s3, String s4, String s5)
    {
        boolean flag = true;
        flag = insertarBasico(s, s1, s2, s3);
        if(!flag)
        {
            System.out.println("Error basico");
            return false;
        }
        String s6 = (new StringBuilder()).append("INSERT INTO MATERIA VALUES(").append(idx).append(", '").append(s4).append("');").toString();
        flag = SQLiteManager.executeUpdateSQL(s6);
        if(!flag)
        {
            System.out.println("Error en MATERIA");
            return false;
        }
        s6 = (new StringBuilder()).append("INSERT INTO FECHA_FINAL VALUES(").append(idx).append(", '").append(s5).append("');").toString();
        flag = SQLiteManager.executeUpdateSQL(s6);
        if(!flag)
        {
            System.out.println("Error en FECHA_FINAL");
            return false;
        }
        s6 = (new StringBuilder()).append("INSERT INTO PRINCIPAL_MATERIA VALUES(").append(idx).append(", ").append(idx).append(");").toString();
        flag = SQLiteManager.executeUpdateSQL(s6);
        if(!flag)
        {
            System.out.println("Error en FECHA_FINAL");
            return false;
        }
        s6 = (new StringBuilder()).append("INSERT INTO PRINCIPAL_FECHA_FINAL VALUES(").append(idx).append(", ").append(idx).append(");").toString();
        flag = SQLiteManager.executeUpdateSQL(s6);
        if(!flag)
        {
            System.out.println("Error en FECHA_FINAL");
            return false;
        } 
        idx++;
        return true;
    }
    /**
     * Metodo que obtiene las tareas de la base de datos
     * @return un objeto de tipo InfoManager que representa una tabla con tareas ordenadas por fecha
     */
    public InfoManager obtenerTareas()
    {
        InfoManager infomanager = SQLiteManager.executeQuerySQL("SELECT PRINCIPAL.ID, NOMBRE, DESCRIPCION, TIPO, NIVEL, MATERIA, FECHA FROM PRINCIPAL JOIN PRINCIPAL_PRIORIDAD ON PRINCIPAL.ID = PRINCIPAL_PRIORIDAD.ID_PRINCIPAL JOIN PRIORIDAD ON PRINCIPAL_PRIORIDAD.ID_PRIORIDAD = PRIORIDAD.ID JOIN PRINCIPAL_TIPO ON PRINCIPAL.ID = PRINCIPAL_TIPO.ID_PRINCIPAL JOIN TIPO ON PRINCIPAL_TIPO.ID_TIPO = TIPO.ID JOIN PRINCIPAL_MATERIA ON PRINCIPAL.ID = PRINCIPAL_MATERIA.ID_PRINCIPAL JOIN MATERIA ON PRINCIPAL_MATERIA.ID_MATERIA = MATERIA.ID JOIN PRINCIPAL_FECHA_FINAL ON PRINCIPAL.ID = PRINCIPAL_FECHA_FINAL.ID_PRINCIPAL JOIN FECHA_FINAL ON PRINCIPAL_FECHA_FINAL.ID_FECHA_FINAL = FECHA_FINAL.ID WHERE TIPO.ID = 1;");
        return infomanager;
    }
    /**
     * Metodo que obtiene todo de la base de datos
     * @return un objeto de tipo InfoManager que representa una tabla con todo ordenadas por fecha
     */
    public InfoManager obtenerTodo()
    {
        InfoManager infomanager = SQLiteManager.executeQuerySQL("SELECT PRINCIPAL.ID, NOMBRE, DESCRIPCION, TIPO, NIVEL, MATERIA, FECHA FROM PRINCIPAL JOIN PRINCIPAL_PRIORIDAD ON PRINCIPAL.ID = PRINCIPAL_PRIORIDAD.ID_PRINCIPAL JOIN PRIORIDAD ON PRINCIPAL_PRIORIDAD.ID_PRIORIDAD = PRIORIDAD.ID JOIN PRINCIPAL_TIPO ON PRINCIPAL.ID = PRINCIPAL_TIPO.ID_PRINCIPAL JOIN TIPO ON PRINCIPAL_TIPO.ID_TIPO = TIPO.ID JOIN PRINCIPAL_MATERIA ON PRINCIPAL.ID = PRINCIPAL_MATERIA.ID_PRINCIPAL JOIN MATERIA ON PRINCIPAL_MATERIA.ID_MATERIA = MATERIA.ID JOIN PRINCIPAL_FECHA_FINAL ON PRINCIPAL.ID = PRINCIPAL_FECHA_FINAL.ID_PRINCIPAL JOIN FECHA_FINAL ON PRINCIPAL_FECHA_FINAL.ID_FECHA_FINAL = FECHA_FINAL.ID;");
        return infomanager;
    }
    /**
     * Metodo que obtiene los proyectos de la base de datos
     * @return un objeto de tipo InfoManager que representa una tabla con proyectos ordenados por fecha
     */
    public InfoManager obtenerProyectos()
    {
        InfoManager infomanager = SQLiteManager.executeQuerySQL("SELECT PRINCIPAL.ID, NOMBRE, DESCRIPCION, TIPO, NIVEL, MATERIA, FECHA FROM PRINCIPAL JOIN PRINCIPAL_PRIORIDAD ON PRINCIPAL.ID = PRINCIPAL_PRIORIDAD.ID_PRINCIPAL JOIN PRIORIDAD ON PRINCIPAL_PRIORIDAD.ID_PRIORIDAD = PRIORIDAD.ID JOIN PRINCIPAL_TIPO ON PRINCIPAL.ID = PRINCIPAL_TIPO.ID_PRINCIPAL JOIN TIPO ON PRINCIPAL_TIPO.ID_TIPO = TIPO.ID JOIN PRINCIPAL_MATERIA ON PRINCIPAL.ID = PRINCIPAL_MATERIA.ID_PRINCIPAL JOIN MATERIA ON PRINCIPAL_MATERIA.ID_MATERIA = MATERIA.ID JOIN PRINCIPAL_FECHA_FINAL ON PRINCIPAL.ID = PRINCIPAL_FECHA_FINAL.ID_PRINCIPAL JOIN FECHA_FINAL ON PRINCIPAL_FECHA_FINAL.ID_FECHA_FINAL = FECHA_FINAL.ID WHERE TIPO.ID = 2;");
        return infomanager;
    }

    private void generateIdx()
    {
        InfoManager infomanager = SQLiteManager.executeQuerySQL("SELECT * FROM PRINCIPAL WHERE ID = (SELECT MAX(ID) FROM PRINCIPAL);");
        int i = infomanager.length();
        if(i == 0)
        {
            idx = 1;
        } else
        {
            Row row = infomanager.getRow(0);
            idx = row.getIdx() + 1;
        }
    }

    private HashMap<String, Integer> tipos;
    private HashMap<String, Integer> prioridades;
    private HashMap<String, Integer> generos;
    private int idx;
}