
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Clase que guarda informacion como una tabla
 * internamente tiene un objeto de la clase Row por fila
 * @author SANTIAGO LEY
 * @version 1.0
 */

public class InfoManager
{
    /**
     * Constructor de la clase InfoManager
     * inicializa los atributos
     */
    public InfoManager()
    {
        tabla = new LinkedList<Row>();
    }

    /**
     * Metodo que obtiene toda la informacion de un ResultSet (junto con su ResultSetMetaData)
     * @param resultset el ResultSet obtenido usando JDBC
     * @param resultsetmetadata la metadata del mismo resultset
     */
    public void getAll(ResultSet resultset, ResultSetMetaData resultsetmetadata)
    {
        
        try
        {
            int i = resultsetmetadata.getColumnCount();
            Row row = null;
            for(; resultset.next(); tabla.add(row))
            {
                int j = resultset.getInt("ID");
                row = new Row(j);
                for(int k = 2; k <= i; k++)
                {
                    String s = resultsetmetadata.getColumnName(k);
                    row.insert(s, resultset.getString(s));
                }

            }

            Collections.sort(tabla);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    /**
     * Metodo que devuelve un Row (una fila de la tabla resultante)
     * @param i la i-esima fila de la tabla
     * @return objeto de tipo Row
     */
    public Row getRow(int i)
    {
        return tabla.get(i);
    }
    /**
     * Metodo que devuelve el tamaño de la tabla
     * @return el tamaño de la tabla
     */
    public int length()
    {
        return tabla.size();
    }

    private LinkedList<Row> tabla;
}