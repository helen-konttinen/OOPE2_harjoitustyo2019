/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Sailova;
import java.util.LinkedList;

/**
 *
 * @author helen
 */
public class Hakemisto<T> extends Tieto implements Sailova<T> {
    /**
     * Attribuutit.
     * */
    OmaLista<Tieto> sisalto;
    
    private Hakemisto ylihakemisto;

    /**
     * Rakentajat.
     * */
    public Hakemisto() {
        this.sisalto = new OmaLista<>();
        ylihakemisto = null;
    }
    
    /**
     * 
     * @param hakemistoNimi
     * @param yHakemisto
     */
    public Hakemisto (StringBuilder hakemistoNimi, Hakemisto yHakemisto)throws IllegalArgumentException {
        super(hakemistoNimi);
        ylihakemisto(yHakemisto);
        this.sisalto = new OmaLista<>();
    }
    
    /**
     * Aksessorit.
     * @param yHakemisto
     */
    private void ylihakemisto(Hakemisto yHakemisto) {
        ylihakemisto = yHakemisto;
    }
    
    /**
     * Korvatut metodit.
     * */
    @Override
    public LinkedList hae(String hakusana) {
        try {
            LinkedList haettuLista = new LinkedList();
            
            for (int i = 0; i < sisalto.size(); i++) {
                Tieto seuraavaAlkio = sisalto.get(i);
                
                if (seuraavaAlkio.equals(hakusana)) {
                    haettuLista.add(seuraavaAlkio);
                }
            }
            return haettuLista;
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public boolean lisaa(Object lisattava) {
        OmaLista uusiListattava = (OmaLista)listattava;
        OmaLista.lisaa(lisattava);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean poista(Object poistettava) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Object-luokan korvatut metodit.
     */    
    @Override
    public String toString () {
        return super.toString() + "/ " + sisalto.size();
    }
}
