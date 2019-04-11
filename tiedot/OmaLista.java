/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.*;
import java.util.LinkedList;

/**
 *
 * @author helen
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    @Override
    public boolean lisaa(E uusi) {
        /** uusi alkion lisätään listalle siten,
        * että alkio sijoittuu kaikkien itseään pienempien tai yhtä suurien alkioiden
        * jälkeen ja ennen kaikkia itseään suurempia alkioita. */
        for (int i = 0; i < size(); i++) {
            Comparable eka = (Comparable) get(i);
            Comparable toka = (Comparable) uusi;
            
            int vastaus = toka.compareTo(eka);
            
            if (vastaus <= 0) {
                add(i, uusi);
                
            }
            else if (vastaus > 0) {
                add(0, uusi);
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int poista(E poistettava) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
