
package harjoitustyo.toiminnot;

import harjoitustyo.apulaiset.In;
import java.util.LinkedList;
import harjoitustyo.tiedot.Tieto;

/**
 *
 * @author helen
 */
public class Kayttoliittyma {
    public void suorita () {
        boolean ajetaan = true;
        Tulkki tulkki = new Tulkki();
        while (ajetaan) {
            System.out.print("/");
            System.out.print(tulkki.annaPolku());
            String komento = In.readString();

            if (komento.startsWith("md")) {
                try {
                    if (komento.equals("md")) {
                        System.out.println("Error!");
                    }
                    else {
                        komento = komento.substring(3);
                        boolean onnistuiko = tulkki.luodaanAlihakemisto(komento);
                        
                        if (!onnistuiko) {
                        System.out.println("Error!");
                        }
                    }     
                }
                catch (Exception e) {
                    System.out.println("Error!");
                }
            }
            else if (komento.startsWith("mf")) {
                try {
                    if (komento.equals("mf")) {
                        System.out.println("Error!");
                    }
                    else {
                        komento = komento.substring(3);
                        String[] katkottu = komento.split(" ");
                        
                        if (katkottu.length < 2) {
                            System.out.println("Error!");
                        }
                        boolean onnistuiko = tulkki.luodaanTiedosto(katkottu[0], Integer.parseInt(katkottu[1]));

                        if (!onnistuiko) {
                            System.out.println("Error!");
                        }
                    }  
                }
                catch (Exception e) {
                    System.out.println("Error!");
                }
            }
            else if (komento.startsWith("cd")) {
                try {
                    if (komento.equals("cd")) {
                        komento = "";
                    }
                    else {
                        komento = komento.substring(3);
                    }

                    tulkki.siirtyma(komento);
                }
                catch (Exception e) {
                    System.out.println("Error!");
                }
            }
            else if (komento.startsWith("ls")) {
                try {
                    if (komento.equals("ls")) {
                        komento = "";
                    }
                    else {
                        komento = komento.substring(3);
                    }

                    LinkedList<Tieto> listattava = tulkki.listattavatTiedot(komento);
                    
                    if (!(komento.equals("")) && listattava.size() == 0 && !(komento.equals("*"))) {
                        System.out.println("Error!");
                    }
                    else {
                        for (Tieto tieto: listattava) {
                            System.out.println(tieto.toString());
                        }
                    }
                }
                catch (Exception e) {
                    
                }
            }
            else if (komento.startsWith("rm")) {
                try {
                    if (komento.equals("rm")) {
                        System.out.println("Error!");
                    }
                    else {
                        komento = komento.substring(3);
                        boolean loytyiPoistettavia = tulkki.poista(komento);
                        
                        if (!loytyiPoistettavia) {
                            throw new IllegalArgumentException();
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Error!");
                }
            }
            else if (komento.startsWith("mv")) {
                try {
                    if (komento.equals("mv")) {
                        System.out.println("Error!");
                    }
                    else {
                        komento = komento.substring(3);
                        String[] katkottu = komento.split(" ");

                        boolean nimettyUudelleen = tulkki.uudelleenNimeaminen(katkottu[0], katkottu[1]);

                        if (!nimettyUudelleen) {
                            System.out.println("Error!");
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Error!");
                }
            }
            else if (komento.equals("exit")) {
                ajetaan = false;
            }
            else {
                System.out.println("Error!");
            }
        }
    }
}
