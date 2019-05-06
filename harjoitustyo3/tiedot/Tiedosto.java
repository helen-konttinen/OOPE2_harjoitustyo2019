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
      super();
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
   
   public int koko () {
        return koko;
    }
   
    public void koko(int uusiKoko) throws IllegalArgumentException{
        if (uusiKoko < 0) {
           throw new IllegalArgumentException("Virheellinen koko!"); 
        }
        koko = uusiKoko;
    }
    
    /**
     * Korvatut metodit.
     */
    @Override
    public Object kopioi() {
        throw new UnsupportedOperationException("Not supported yet."); 
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
