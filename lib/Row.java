
import java.util.HashMap;
/**
 * Clase que representa una fila de una tabla de base de datos, muy parecido a un HashMap
 * @author SANTIAGO LEY
 * @version 1.0
 */
public class Row implements Comparable<Row>
{

    private Row()
    {
    }
    /**
     * Unico constructor de la clase, la inicializa
     * @param i el indice, el cual es el valor indice principal de la fila en la tabla
     */
    public Row(int i)
    {
        id = i;
        columnas = new HashMap<String, String>();
    }
    /**
     * Metodo que inserta cierta informacion en la fila segun el nombre de la columna como un hashMap
     * @param s el nombre de la columna (la llave de lo que se quiere insertar)
     * @param s1 el contenido en dicha columna
     */
    public void insert(String s, String s1)
    {
        columnas.put(s, s1);
    }
    /**
     * Metodo que devuelve el valor de la clase evaluada en un String
     * @param s el valor de la columna
     * @return el contenido de la fila en la columna s
     */
    public String get(String s)
    {
        String s1 = (String)columnas.get(s);
        return s1;
    }
    /**
     * Metodo que devuelve el indice que identifica a la fila
     * @return el indice
     */
    public int getIdx()
    {
        return id;
    }
    /**
     * metodo que compara 2 objetos de la clase Row a partir de la fecha, se comporta como usualmente un objeto de la clase Comparable
     * @param row el objeto que se va a comparar con este
     * @return cual es menor
     */
    public int compareTo(Row row)
    {
        String s = (new StringBuilder()).append(get("FECHA").substring(6, 10)).append(get("FECHA").substring(3, 5)).append(get("FECHA").substring(0, 2)).toString();
        String s1 = (new StringBuilder()).append(row.get("FECHA").substring(6, 10)).append(row.get("FECHA").substring(3, 5)).append(row.get("FECHA").substring(0, 2)).toString();
        return s.compareTo(s1);
    }

    private HashMap<String, String> columnas;
    private int id;
}