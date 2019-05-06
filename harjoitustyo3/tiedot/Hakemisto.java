/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Sailova;
import harjoitustyo.omalista.OmaLista;
import java.util.LinkedList;
/**
 *
 * @author helen
 */
public class Hakemisto extends Tieto implements Sailova<Tieto> {
    /**
     * Attribuutit.
     * */
    private OmaLista<Tieto> sisalto;
    
    private Hakemisto ylihakemisto;

    /**
     * Rakentajat.
     * */
    public Hakemisto() {
        super();
        this.sisalto = new OmaLista<Tieto>();
        ylihakemisto = null;
    }
    
    /**
     * 
     * @param hakemistoNimi
     * @param yHakemisto
     */
    public Hakemisto (StringBuilder hakemistoNimi, Hakemisto yHakemisto) {
        super(hakemistoNimi);
        ylihakemisto(yHakemisto);
        this.sisalto = new OmaLista<Tieto>();
    }
    
    /**
     * Aksessorit.
     * @param yHakemisto
     */
    public void ylihakemisto(Hakemisto yHakemisto) {
        ylihakemisto = yHakemisto;
    }
    
    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }
    
    public OmaLista<Tieto> sisalto() {
        return sisalto;
    }
    
    public void sisalto(harjoitustyo.omalista.OmaLista<harjoitustyo.tiedot.Tieto> uusiSisalto) {
        sisalto = uusiSisalto;
    }
    
    
    /**
     * Korvatut metodit.
     * */
    @SuppressWarnings("unchecked")
    @Override
    public LinkedList<Tieto> hae(String hakusana) {
        try {
            LinkedList<Tieto> haettuLista = new LinkedList<Tieto>();
            
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
    public boolean lisaa(Tieto lisattava) {
        return sisalto.lisaa(lisattava);  
    }

    @Override
    public boolean poista(Tieto poistettava) {
        if (poistettava == null) {
            return false;
        }
        
        int vastaus = sisalto.poista(poistettava);
        
        if (vastaus == 0) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * Object-luokan korvatut metodit.
     */    
    @Override
    public String toString () {
        return super.toString() + "/ " + sisalto.size();
    }
}
