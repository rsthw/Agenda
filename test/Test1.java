import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Pruebas básicas para los objetos tipo Row
 */
public class Test1 {
   
    @Test
    public void testRow() {
    	String columnas[] = {"Nombre", "Descripcion", "Tipo", "Algo", "Fecha"};
    	String contenidos[] = {"mi_nombre", "mi_descripcion","mi_tipo","mi_algo", "15/09/1111"};
    	int idx = 1234;

	    // Se crea el Row y se llena
	    Row test_sample = new Row(idx);
	    for(int i = 0; i<columnas.length; i++){
	    	test_sample.insert(columnas[i], contenidos[i]);
	    }

	    // se ve que devuelva lo mismo que le guardas a excepcion del 3
	    for(int i = 0; i<columnas.length; i++){ 
	    	assertEquals(contenidos[i],test_sample.get(columnas[i]));
	    }


	    assertEquals(idx, test_sample.getIdx());
   }
}