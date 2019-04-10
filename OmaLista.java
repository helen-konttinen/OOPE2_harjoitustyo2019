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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int poista(E poistettava) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
