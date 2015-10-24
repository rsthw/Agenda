import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(Test1.class);
      
      for (Failure failure : result.getFailures()) {
         System.out.println("Fallos: "+failure.toString());
      }
      if(result.wasSuccessful()) // true if it was succesful
      	System.out.println("Pruebas realizadas con exito."); 
   }
}  