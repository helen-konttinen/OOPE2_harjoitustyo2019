
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.*;

/**
 * Abstrakti Tieto -luokka, joka toteuttaa Tietoinen ja Comparable
 * -rajapinnat. Luokka sisältää tiedostoille ja Hakemistoille yhteiset
 * tiedot.
 * <p>
 * Harjoitustyo, Olio-ohjelmoinnin perusteet II, 2019
 * <p>
 * @author Helen Konttinen, (helen.konttinen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */
public abstract class Tieto implements Tietoinen, Comparable<Tieto>{
   /**StringBuilder tyyppinen nimi tiedolle.*/
   private StringBuilder nimi;
   
   /** Oletusrakentaja jossa asetetaan uusi StringBuilder -tyyppien
    * tyhjä olio nimelle.
    */
   public Tieto () {
      nimi = new StringBuilder("");
   }
   
   /** 
    * Yksi parametrinen rakentaja, joka kutsuu nimen asettavaa
    * aksessoria.
    * @param uusiNimi parametrina annettu nimi, joka halutaan asettaa tiedolle.
    * @throws IllegalArgumentException jos parametrissa oli virhe.
    */
   public Tieto (StringBuilder uusiNimi) throws IllegalArgumentException {
       nimi(uusiNimi);
   }
   
   /**
    * Tiedon nimeä lukeva aksessori.
    * @return StringBuilder -tyyppisen nimen.
    */
   public StringBuilder nimi () {
       return nimi;
   }
   /**
    * Yritetään asettaa uusi nimi.
    * @param uusiNimi tiedon mahdollinen uusi nimi.
    * @throws IllegalArgumentException, jos nimi on virheellinen 
    */
   public void nimi (StringBuilder uusiNimi) throws IllegalArgumentException {
       // jos parametrina annettu nimi on tyhjä tai sen pituus on nolla
       // heitetään poikkeus.
       if (uusiNimi == null || uusiNimi.length() == 0) {
           throw new IllegalArgumentException("Error!");
       }
       
       // lippumuuttuja, jos on vain pisteitä parametrina annetussa nimessä
       boolean vainPisteita = true;
       
       // käydään nimeä läpi merkki merkiltä for silmukassa.
       for (int i = 0; i < uusiNimi.length(); i++) {
           char merkki  = uusiNimi.charAt(i);
           
           // jos nimen pituus on yksi merkki ja se merkki on piste
           // heitetään poikkeus.
           if ( uusiNimi.length() == 1) {
              if (merkki == '.') {
                 throw new IllegalArgumentException("Error!");
              }
           }
           // jos merkki ei ole a-z, A-Z, 0-9, "_" tai "." heitetään poikkeus.
           if ((merkki < 'a' || merkki > 'z') && (merkki < 'A' || merkki > 'Z') 
           && (merkki < '0' || merkki > '9') && (merkki != '_') && (merkki != '.')) {
               throw new IllegalArgumentException("Error!");
           }
           
           // jos merkki ei ole piste käännetään lippumuuttuja.
           if (merkki != '.') {
               vainPisteita = false;
           }
       }
       // jos nimen lopussa huomataan, että lippumuuttuja on edellen
       // true, eli nimestä löytyi vain pistetiä, heitetään poikkeus.
       if (vainPisteita) {
           throw new IllegalArgumentException("Error!");
       }
       // muuten asetetaan parametrina annettu nimi uudeksi nimeksi.
       nimi = uusiNimi;
   }
   
   /**
    * Object -luokan korvattu toString -metodi, joka
    * palauttaa olion nimen String -muodossa.
    * @return String -tyyppinen nimi.
    */
   @Override
   public String toString () {
      
      // palautetaan nimi .
      return nimi.toString();
   }
   
   /**
    * Korvataan Object-luokan equals metodi. Verrataan
    * tämän olion ja parametrina saadun olion nimiä.
    * @param kohde parametrina annettu olio, jota halutaan verrata.
    * @return true, jos nimet ovat samat, muuten false.
    */
   @Override
   public boolean equals (Object kohde) {
       try {
           // muunnetaan parametrina saatu kohde Tieto -tyyppiseksi
           // ja otetaan talteen sen nimi.
           Tieto kohteenTieto = (Tieto) kohde;
           String ekaNimi = kohteenTieto.nimi().toString();
           
           // otetaan myös talteen tämän olion nimi.
           String tokaNimi = this.nimi.toString();
           
           // verrataan nimiä equals -metodilla ja palautetaan paluuarvo.
           return tokaNimi.equals(ekaNimi);
       }

       // napataan kaikki poikkeukset ja palautetaan false.
       catch (Exception e) {
           return false;
       }
   }
   

   public boolean equals (String kohde) {
       // otetaan talteen tämän olion nimi.
       String vertailtava = this.nimi.toString();
       
       // jos parametrina annettu merkkijono on tyhjä tai se on null arvoinen,
       // palautetaan heti false.
       if ( kohde == null || kohde == "") {
           return false;
       }
       // muuten aletaan käymään parametrina saatua merkkijonoa
       else {
           // jos merkkijono on "*" tai tämän olion nimi on sama kuin 
           // parametrina annetun olion nimi, palautetaan true.
           if (kohde == "*" || vertailtava.equals(kohde)) {
               return true;
           } 
           // muuten, jos parametrina annettu merkkijonon ensimmäinen ja viimeinen merkki
           // on asteriksi (*)...
           else if (kohde.charAt(0) =='*' && kohde.charAt(kohde.length()-1) == '*') {
               //ja pituus on kaksi, palautetaan false. Merkkijono on siis vain "**".
               if( kohde.length() == 2) {
                   return false;
               }
               // ja pituus muu kuin 2 tehdään uusi merkkijono ja asetetaan se tyhjäksi.
               // Käydään merkkijonoa läpi, alottaen 1 merkistä ja lopetetaan toiseksi viimeiseen
               // merkkiin ja lisätään kaikki merkit juuri luotuun muuttujaan.
               // Tarkistetaan String -luokan contains -metodilla onko tämän olion nimessä
               // uutta hakusanaa. Lopuksi palautetaan totuusarvo.
               else {
                   String uusiHakusana = "";
                   for(int i = 1; i < kohde.length()-1; i++ ) {
                       uusiHakusana += kohde.charAt(i);
                   }
                   return vertailtava.contains(uusiHakusana);
               }
           }
           // jos asteriksi löytyy parametrina annetun merkkijonon alusta, käydään silmukoiden
           // läpi ja katsotaan onko tämän olion nimen merkki ja parametrina annetun nimen merkki
           // samassa kohtaa erit. Jos on erit palautetaan false. Muuten palautetaan true.
           else if(kohde.charAt(0) == '*') {
               for (int j = vertailtava.length()-kohde.length()+1; j < nimi.length(); j++) {
                   for( int k = 1; k < kohde.length(); k++) {
                       if (vertailtava.charAt(j-1 + k) != kohde.charAt(k)) {
                           return false;
                       } 
                   }
                   return true;
               }
               return true;
           }
           // jos asteriksi löytyy parametrina annetun merkkijonon lopusta, silmukoidaan 
           // se läpi, ja katsotaan ovatko tämän olion nimen merkki eri kuin parametrina annetun
           // merkkijonon merkki samassa kohtaa. Jos on erit, palautetaan false. Muuten palautetaan
           // true.
           else if (kohde.charAt(kohde.length()-1) == '*') {
               for (int m = 0; m < kohde.length()-1; m++) {
                   if(nimi.charAt(m) != kohde.charAt(m)) {
                       return false;
                   }
               }
               return true;
           }
           // ellei tähtiä löydy ollenkaa parametrina annetusta merkkijonosta vertaillaan
           // olioiden nimiä equals metodilla ja palautetaan totuusarvo.
           else {
               return (vertailtava.equals(kohde));
           }    
       }
   }
   
   /**
    * Comparable-rajapinnan korvattu compareTo metodi,
    * joka vertaa olioiden nimiä keskenään.
    * @param toinen Tieto tyyppinen parametri, jota verrataan
    * tämän olioon.
    * @return 1, jos tämä olio > parametrina saatu olio, 0, jos
    * tämä olio == parametrina saatu olio, ja -1, jos tämä olio
    * on pienempi kuin parametrina saatu olio.
    */
   @Override
   public int compareTo (Tieto toinen) {
      // otetaan talteen parametrina saadun olion nimi ja
      // tämän olion nimi muuttujiin.
      String toka = toinen.nimi().toString();
      String eka = this.nimi().toString();
      // Tämä olio < parametrina saatu olio.
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
