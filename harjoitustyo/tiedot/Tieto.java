
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.*;

/**
 *
 * @author Helen Konttinen, helen.konttinen@tuni.fi
 */
public abstract class Tieto implements Tietoinen, Comparable<Tieto>{
   /**
    * Attribuutit.
    */
   private StringBuilder nimi;
   
   /**
    * Rakentajat.
    */
   public Tieto () {
      nimi = new StringBuilder("");
   }
   
   public Tieto (StringBuilder uusiNimi) throws IllegalArgumentException {
       nimi(uusiNimi);
   }
   
   /**
    * Aksessoirt.
    */
   
   /**
    * Yritetään asettaa uusi nimi.
    * @param uusiNimi tiedon mahdollinen uusi nimi.
    * @throws IllegalArgumentException, jos nimi on virheellinen 
    */
   public void nimi (StringBuilder uusiNimi) throws IllegalArgumentException {
       for (int i = 0; i < uusiNimi.length(); i++) {
           char merkki  = uusiNimi.charAt(i);
           
           if (uusiNimi == null) {
               throw new IllegalArgumentException("Error!");
               
           }
           if ( uusiNimi.length() == 1) {
              if (merkki == '.') {
                 throw new IllegalArgumentException("Error!");
              }
           }
           if ((merkki < 'a' && merkki > 'z') && (merkki < 'A' && merkki > 'Z') 
           && (merkki < '0' && merkki > '9') && (merkki != '_') && (merkki != '.')) {
               throw new IllegalArgumentException("Error!");
           }
       }
       nimi = uusiNimi;
   }
   
   public String nimi () {
       return nimi.toString();
   }
   
   /**
    * Object-luokan korvatut metodit.
    * */
   @Override
   public String toString () {
      
      // palautetaan nimi .
      return nimi.toString();
   }
   
   @Override
   public boolean equals (String kohde) {
       String vertailtava = nimi.toString();
       
       if ( kohde == null || kohde == "") {
           return false;
       }
       else {
           String olionNimi = nimi.toString();
          
           if (kohde.charAt(0) =='*' && kohde.charAt(kohde.length()-1) == '*') {
               if( kohde.length() == 2) {
                   return false;
               }
               else {
                   String uusiHakusana = "";
                   for(int i = 1; i < kohde.length()-1; i++ ) {
                       uusiHakusana += kohde.charAt(i);
                   }
                   return vertailtava.contains(uusiHakusana);
               }
           }
           else if(kohde.charAt(0) == '*') {
               for (int j = vertailtava.length()-kohde.length()+1; j < nimi.length(); j++) {
                   for( int k = 1; k < kohde.length(); k++) {
                       if (vertailtava.charAt(k) != kohde.charAt(k)) {
                           return false;
                       }
                   }
               }
               return true;
           }
           else if (kohde.charAt(kohde.length()-1) == '*') {
               for (int m = 0; m < kohde.length()-1; m++) {
                   if(nimi.charAt(m) != kohde.charAt(m)) {
                       return false;
                   }
               }
               return true;
           }
           else {
               return (vertailtava.equals(kohde));
           }    
       }
   }
   /**
    * Comparable-rajapinnan
    * korvattu metodi.
    *
     * @param toinen
     * @return  */
   @Override
   public int compareTo (Tieto toinen) {
      // Tämä olio < parametrina saatu olio.
      String toka = toinen.nimi();
      String eka = this.nimi().toString();
      if (eka.compareTo(toka) < 0) {
         return -1;
      }
      // Tämä olio == parametrina saatu olio.
      else if (eka.compareTo(toka) == 0) {
         return 0;
      }
      // Tämä olio > parametrina saatu olio.
      else {
         return 1;
      } 
   }
}
