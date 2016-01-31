package at.ac.tuwien.imw.helper;

public class PeriodeCounter {
   private static PeriodeCounter instance = null;
   
   protected PeriodeCounter() {
      // Exists only to defeat instantiation.
   }
   
   public static PeriodeCounter getInstance() {
      if(instance == null) {
         instance = new PeriodeCounter();
      }
      return instance;
   }
}