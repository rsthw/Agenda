

/**
 * Clase Controlador que representa el controlador del MVC (modelo, vista, controlador)
 * @author SANTIAGO LEY
 * @version 1.0
 */
import java.io.PrintStream;

public class Controlador
{
    /**
     * Metodo que elimina una tarea de la base de datos
     * @param i el indice de la tarea
     * @param s el nombre de la tarea
     * @param s1 la descripcion de la tarea
     * @param s2 el tipo de la tarea
     * @param s3 la prioridad de la tarea
     * @param s4 la materia de la tarea
     * @param s5 la fecha de la tarea
     */
    public static void eliminaTarea(int i, String s, String s1, String s2, String s3, String s4, String s5)
    {
        SQLiteManager.open_connection();
        Convertidor convertidor = new Convertidor();
        convertidor.borrarTarea(i, s, s1, s2, s3, s4, s5);
        SQLiteManager.close_connection();
    }
    /**
     * Metodo que obtiene las tareas de la base de datos
     * @return un objeto de tipo InfoManager que representa una tabla con tareas ordenadas por fecha
     */
    public static InfoManager getTareas()
    {
        SQLiteManager.open_connection();
        Convertidor convertidor = new Convertidor();
        InfoManager infomanager = convertidor.obtenerTareas();
        SQLiteManager.close_connection();
        return infomanager;
    }
    /**
     * Metodo que obtiene las tareas de la base de datos
     * @return un objeto de tipo InfoManager que representa una tabla con proyectos ordenados por fecha
     */
    public static InfoManager getProyectos()
    {
        SQLiteManager.open_connection();
        Convertidor convertidor = new Convertidor();
        InfoManager infomanager = convertidor.obtenerProyectos();
        SQLiteManager.close_connection();
        return infomanager;
    }
    /**
     * Metodo que obtiene las tareas de la base de datos
     * @return un objeto de tipo InfoManager que representa una tabla con trabajos de cualquier tipo ordenadas por fecha
     */
    public static InfoManager getTodo()
    {
        SQLiteManager.open_connection();
        Convertidor convertidor = new Convertidor();
        InfoManager infomanager = convertidor.obtenerTodo();
        SQLiteManager.close_connection();
        return infomanager;
    }
    /**
     * Metodo que añade una tarea de la base de datos
     * @param s el nombre de la tarea
     * @param s1 la descripcion de la tarea
     * @param s2 el tipo de la tarea
     * @param s3 la prioridad de la tarea
     * @param s4 la materia de la tarea
     * @param s5 la fecha de la tarea
     */
    public static void nuevaTarea(String s, String s1, String s2, String s3, String s4, String s5)
    {
        SQLiteManager.open_connection();
        Convertidor convertidor = new Convertidor();
        convertidor.insertarTarea(s, s1, s2, s3, s4, s5);
        SQLiteManager.close_connection();
    }
/*
    public static void main(String args[])
    {
        SQLiteManager.open_connection();
        Convertidor convertidor = new Convertidor();
        convertidor.insertarTarea("Tarea Probabilidad", "Tarea opcional", "Tarea", "MEDIA", "Probabilidad", "15/09/2015");
        InfoManager infomanager = convertidor.obtenerTareas();
        for(int i = 0; i < infomanager.length(); i++)
        {
            Row row = infomanager.getRow(i);
            int j = row.getIdx();
            System.out.print((new StringBuilder()).append("ID = ").append(j).append(", ").toString());
            String s = row.get("NOMBRE");
            System.out.println(s);
            s = row.get("DESCRIPCION");
            System.out.println(s);
            s = row.get("TIPO");
            System.out.println(s);
            s = row.get("NIVEL");
            System.out.println(s);
            s = row.get("MATERIA");
            System.out.println(s);
            s = row.get("FECHA");
            System.out.println(s);
        }

        SQLiteManager.close_connection();
    }
*/
}