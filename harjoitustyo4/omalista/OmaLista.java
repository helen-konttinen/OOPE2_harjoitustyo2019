
package harjoitustyo.omalista;

import harjoitustyo.apulaiset.*;
import java.util.LinkedList;

/**
 *
 * @author helen
 * @param <E>
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    @SuppressWarnings("unchecked")
    @Override
    public boolean lisaa(E uusi) {
        /** uusi alkion lisätään listalle siten,
        * että alkio sijoittuu kaikkien itseään pienempien tai yhtä suurien alkioiden
        * jälkeen ja ennen kaikkia itseään suurempia alkioita. */
        try {
            if (uusi == null) {
                return false;
            }
            Comparable lisattava = (Comparable) uusi;
            if ( size() == 0) {
                addFirst(uusi);
                return true;
            }
            else {
                for (int i = 0; i < size(); i++) {

                    int vastaus = lisattava.compareTo(get(i));

                    if (vastaus < 0) {
                        add(i, uusi);
                        return true;
                    }
                }
                addLast(uusi);
                return true;
            }
        }
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
            
            for(int j = 0; j < size(); j++) {
                
                if (get(j)==poistettava) {
                    remove(j);
                    poistetutAlkiot++;
                    j--;
                }
            }
            return poistetutAlkiot;
        }
        catch (IndexOutOfBoundsException e) {
            return 0;
        }
        catch (Exception e) {
            return 0;
        }
    }
    
}