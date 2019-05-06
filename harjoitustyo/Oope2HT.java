
package harjoitustyo;

import harjoitustyo.toiminnot.Kayttoliittyma;

/**
 *
 * @author helen
 */
public class Oope2HT {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to SOS.");
        
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        
        kayttoliittyma.suorita();
        
        System.out.println("Shell terminated.");
    }
    
}
