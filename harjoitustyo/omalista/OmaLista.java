
package harjoitustyo.omalista;

import harjoitustyo.apulaiset.*;
import java.util.LinkedList;

/**
 * OmaLista -luokka, joka periytyy LinkedList -luokasta ja toteuttaa Ooperoiva
 * -rajapinnan. Luokka lisää ja poistaa LinkedList -tyyppisille listoille tietoa.
 * <p>
 * Harjoitustyo, Olio-ohjelmoinnin perusteet II, 2019
 * <p>
 * @author Helen Konttinen, (helen.konttinen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 * @param <E> geneerinen -tyyppi, joka periytyy LinkedLististä.
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    @SuppressWarnings("unchecked")
    @Override
    public boolean lisaa(E uusi) {
        // uusi alkion lisätään listalle siten,
        //että alkio sijoittuu kaikkien itseään pienempien tai yhtä suurien alkioiden
        // jälkeen ja ennen kaikkia itseään suurempia alkioita. */
        try {
            // jos parametrina annettu muuttuja on null -arvoinen, palautetaan
            // false.
            if (uusi == null) {
                return false;
            }
            // muuten tehdään parametrista Comparable -tyyppinen muuttuja.
            Comparable lisattava = (Comparable) uusi;
            // jos tämän olion koko on 0, eli listalla ei ole muita hakemsitoja/tiedostoja,
            // lisätään paametri ensimmäiseksi listalle, ja palautetaan true.
            if ( size() == 0) {
                addFirst(uusi);
                return true;
            }
            // jos koko on muu kuin 0, käydään läpi listaa ja katsotaan onko
            // tämä olio < parametrina saatu olio kutsumalla compareTo metodia Tieto-luokassa.
            else {
                for (int i = 0; i < size(); i++) {
                    int vastaus = lisattava.compareTo(get(i));
                    // jos ämä olio < parametrina saatu olio, lisätään parametri
                    // laskurin kohdalle ja palautetaan true.
                    if (vastaus < 0) {
                        add(i, uusi);
                        return true;
                    }
                }
                // muuten lisätään parametri listan viimeiseksi, ja palautetaan true.
                addLast(uusi);
                return true;
            }
        }
        // napataan kiinni poikkeukset ja palautetaan false.
        catch (IndexOutOfBoundsException e) {
             return false;   
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public int poista(E poistettava) {
        try {
            // poistettujen alkijoiden laskin
            int poistetutAlkiot = 0;
            
            // käydään listaa läpi silmukassa ja verrataan
            // listan alkiota parametriin.
            for(int j = 0; j < size(); j++) {
                
                // jos listan alkio == parametri, poistetaan se listasta 
                // ja kasvatetaan poistettujen alkioiden laskinta, sekä pienennentään
                // silmukan laskinta.
                if (get(j)==poistettava) {
                    remove(j);
                    poistetutAlkiot++;
                    j--;
                }
            }
            // palautetaan poisettujen alkioiden laskin.
            return poistetutAlkiot;
        }
        // napataan poikkeukset ja palautetaan 0.
        catch (IndexOutOfBoundsException e) {
            return 0;
        }
        catch (Exception e) {
            return 0;
        }
    }
    
}