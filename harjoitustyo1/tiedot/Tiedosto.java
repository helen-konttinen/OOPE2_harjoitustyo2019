/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Syvakopioituva;

/**
 *
 * @author helen
 */
public class Tiedosto extends Tieto implements Syvakopioituva {
    /**
    * Attribuutit.
    */
   private int koko;

   /**
    * Rakentajat. 
    */
   public Tiedosto () {
      koko = 0; 
   }
   
   /**
    * @param uusiNimi
    * @param uusiKoko
    */
   public Tiedosto (StringBuilder uusiNimi, int uusiKoko) throws IllegalArgumentException{
       super(uusiNimi);
       koko(uusiKoko);
   }
    

    /**
     * Aksessorit.
     * @param uusiKoko
     * */
    private void koko(int uusiKoko) throws IllegalArgumentException{
        if (koko < 0) {
           throw new IllegalArgumentException("Virheellinen koko!"); 
        }
        else {
            koko = uusiKoko;
        }
    }
    private int koko () {
        return koko;
    }
    
    /**
     * Korvatut metodit.
     */
    @Override
    public Object kopioi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Object-luokan korvatut metodit.
     */
   @Override
    public String toString () {
       // Yhdistetään Tiedoston tiedot yliluokan tietojen perään.
      return super.toString() + " " + koko;
    }
}
