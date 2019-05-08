
package harjoitustyo;

import harjoitustyo.toiminnot.Kayttoliittyma;

/**
 * Harjoitustyön ajoluokka, missä tervehditään,
 * kutsutaan Käyttöliittymä -luokkaa ja tulostetaan hyvästelyt
 * suljettaessa ohjelmaa.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * <p>
 * @author Helen Konttinen, (helen.konttinen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */
public class Oope2HT {
    

    /**
     * Oope2HT -luokan main metodi.
     * @param args komentorivin parametrit
     */
    public static void main(String[] args) {
        System.out.println("Welcome to SOS.");
        
        // Luodaan uusi käyttöliittymä
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        
         // kutsutaan käyttöliittymä -luokan suorita -operaatiota.
        kayttoliittyma.suorita();
        
        System.out.println("Shell terminated.");
    }
    
}
