
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Tietoinen;

/**
 *
 * @author Helen Konttinen, helen.konttinen@tuni.fi
 */
public abstract class Tieto implements Tietoinen {
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
   
   public StringBuilder nimi () {
       return nimi;
   }
   
   /**
    * Object-luokan korvatut metodit.
    * */
   @Override
   public String toString () {
      
      // palautetaan nimi .
      return nimi;
   }
   
   @Override
   public boolean equals (Object kohde) {
       try {
           // asetetaan olioon Tieto-viite, jotta voidaan kutsua
           // Tieto-luokan aksessoreita.
           Tieto toinen = (Tieto)kohde;
           
           // oliot ovat samat, jos attribuuttien arvot ovat samat.
           return (nimi == toinen.nimi());
       }
       catch (Exception e) {
           return false;
       }
   }
   /**
    * Comparable-rajapinnan
    * korvattu metodi.
    *
     * @param toinen
     * @return  */
   public int compareTo (Tieto toinen) {
      // Tämä olio < parametrina saatu olio.
      String eka = toinen.nimi().toString();
      String toka = this.nimi().toString();
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
