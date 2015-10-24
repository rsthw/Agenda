

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.sql.*;

public class SQLiteManager
{

    /**
     * Metodo que abre una conexión con la base de datos
     */
    public static void open_connection()
    {
        actualizarURL();
        conn = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    /**
     * Metodo que cierra la conexión con la base de datos
     */
    public static void close_connection()
    {
        try
        {
            if(conn != null)
                conn.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    /**
     * Metodo que devuelve la tabla sacada de la base de datos al llamar el String con formato de SQLite
     * @param s cadena para SQLite que se quiere ejecutar
     * @return El contenido de la tabla en forma de un objeto de la clase InfoManager
     */
    public static InfoManager executeQuerySQL(String s)
    {
        ResultSet resultset = null;
        InfoManager infomanager = null;
        stmt = null;
        resultset = null;
        try{
            infomanager = new InfoManager();
            if(conn != null){
                stmt = conn.createStatement();
                resultset = stmt.executeQuery(s);
                infomanager.getAll(resultset, resultset.getMetaData());
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try
            {
                if(stmt != null)
                    stmt.close();
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
            try
            {
                if(resultset != null)
                    resultset.close();
            }
            catch(Exception exception1)
            {
                exception1.printStackTrace();
            }
            
        }
        return infomanager;
    }
    /**
     * Metodo que ejecuta un String de la forma de SQLite
     * @param s el comando de SQLite
     * @return si la instruccion se ejecuto con exito
     */
    public static boolean executeUpdateSQL(String s)
    {
        boolean flag;
        flag = true;
        stmt = null;
        try {
            if(conn != null)
            {
                stmt = conn.createStatement();
                stmt.executeUpdate(s);
            } else
            {
                throw new Exception();
            }
        }catch(Exception e){
            e.printStackTrace();
            flag = false;
        } finally {
            try
            {
                if(stmt != null)
                    stmt.close();
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
        
        return flag;
    }
    private static void actualizarURL()
    {
        SQLiteManager sqlitemanager = new SQLiteManager();
        String s = sqlitemanager.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        DB_URL = (new StringBuilder()).append("jdbc:sqlite:/").append(s.substring(5, s.length() - 4)).append("db/Agenda.db").toString();
        //System.out.println(DB_URL);
    }

    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static String DB_URL = "jdbc:sqlite:../db/Agenda.db";
    private static Connection conn;
    private static Statement stmt;

}