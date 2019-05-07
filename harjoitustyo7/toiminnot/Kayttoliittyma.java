
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
            System.out.print(tulkki.annaPolku());
            String komento = In.readString();

            if (komento.startsWith("md")) {
                komento = komento.substring(3);
                boolean onnistuiko = tulkki.luodaanAlihakemisto(komento);
                
                if (!onnistuiko) {
                    System.out.println("Error!");
                }
            }
            if (komento.startsWith("mf")) {
                komento = komento.substring(3);
                String[] katkottu = komento.split(" ");

                boolean onnistuiko = tulkki.luodaanTiedosto(katkottu[0], Integer.parseInt(katkottu[1]));
                
                if (!onnistuiko) {
                    System.out.println("Error!");
                }
            }
            if (komento.startsWith("cd")) {
                if (komento.equals("cd")) {
                    komento = "";
                }
                else {
                    komento = komento.substring(3);
                }
                
                tulkki.siirtyma(komento);
            }
            if (komento.startsWith("ls")) {
                if (komento.equals("ls")) {
                    komento = "";
                }
                else {
                    komento = komento.substring(3);
                }

                LinkedList<Tieto> listattava = tulkki.listattavatTiedot(komento);

                for (Tieto tieto: listattava) {
                    System.out.println(listattava.toString());
                }
            }
            if (komento.startsWith("rm")) {
                komento = komento.substring(3);

                boolean loydtyiPoistettavia = tulkki.poista(komento);
            }
            if (komento.startsWith("mv")) {
                komento = komento.substring(3);
                String[] katkottu = komento.split(" ");

                boolean nimettyUudelleen = tulkki.uudelleenNimeaminen(katkottu[0], katkottu[1]);
            }
            if (komento.equals("exit")) {
                ajetaan = false;
            }
        }
    }
}
