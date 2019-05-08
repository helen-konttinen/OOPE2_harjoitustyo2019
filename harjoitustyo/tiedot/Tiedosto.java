
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Syvakopioituva;

/**
 * Tiedosto -luokka, joka periytyy Tieto -luokasta ja toteuttaa Syvakopioituva
 * -rajapinnan. Luokka sisältää tiedostoille tyyppilliset tiedot.
 * <p>
 * Harjoitustyo, Olio-ohjelmoinnin perusteet II, 2019
 * <p>
 * @author Helen Konttinen, (helen.konttinen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */
public class Tiedosto extends Tieto implements Syvakopioituva {
    /** int -tyyppinrn attribuutti tiedoston koolle.*/
   private int koko;

   /** Oletusrakentaja Tiedostolle, missä kutsutaan yliluokan oletusrakentajaa,
    * ja asetetaan tiedoston kooksi 0.
    */
   public Tiedosto () {
      super();
      koko = 0; 
   }
   
   /**
    * Kaksiparametrinen rakentaja Tiedostolle, joka asettaa nimen kutsumalla
    * yliluokan yksiparametrista rakentajaa, ja koon, kutsumalla asettavaa aksessoria
    * koolle.
    * @param uusiNimi Stringbuilder -tyyppinen parametri nimelle, joka halutaan asettaa
    * tiedostolle.
    * @param uusiKoko int -tyyppinen parametri koolle, joka halutaan asettaa tiedostolle.
    */
   public Tiedosto (StringBuilder uusiNimi, int uusiKoko) throws IllegalArgumentException{
       super(uusiNimi);
       koko(uusiKoko);
   }
    

   /**
    * Kokoa lukeva aksessori.
    * @return int -tyyppisenä koon tiedostolle.
    */
   public int koko () {
        return koko;
    }
    
    /**
     * Kokoa asettava akessori.
     * @param uusiKoko int -tyyppinen parametri tiedoston koolle.
     * @throws IllegalArgumentException jos parametrissa on virhe.
     */
    public void koko(int uusiKoko) throws IllegalArgumentException{
        // jos parametrina annettu koko on alle 0, heitetään poikkeus.
        if (uusiKoko < 0) {
           throw new IllegalArgumentException("Virheellinen koko!"); 
        }
        // muuten asetetaan parametina annettu koko kooksi.
        koko = uusiKoko;
    }
    

    @Override
    public Object kopioi() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
   /**
    * Object -luokan korvattu metodi toString, missä kutsutaan yliluokan
    * toString -metodia.
    * @return String -tyyppinen muuttuja.
    */
   @Override
    public String toString () {
       // Yhdistetään Tiedoston tiedot yliluokan tietojen perään.
      return super.toString() + " " + koko;
    }
}
