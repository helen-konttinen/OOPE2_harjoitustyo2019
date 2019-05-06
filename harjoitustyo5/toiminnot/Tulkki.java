
package harjoitustyo.toiminnot;
import harjoitustyo.tiedot.*;
import java.util.LinkedList;
import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tieto;

/**
 *
 * @author helen
 */
public class Tulkki {
    /**
     * Attribuutit.
     * @return 
     */
    private Hakemisto juuri;
    private Hakemisto tyohakemisto;
    
    /** 
     * Rakentajat.
     * 
     * */
    public Tulkki () {
        juuri = new Hakemisto();
        tyohakemisto = juuri;
    }
    
    public boolean luodaanAlihakemisto (String hNimi) {
        
        LinkedList<Tieto> haettu = tyohakemisto.hae(hNimi);
        
        if (haettu.size() > 0) {
            return false;
        }
        Hakemisto uusiHakemisto = new Hakemisto(new StringBuilder(hNimi), tyohakemisto);
        boolean onnistuikoLisays = tyohakemisto.lisaa(uusiHakemisto);
        return onnistuikoLisays;
    }
    
    public boolean luodaanTiedosto (String tNimi, int uusiKoko) {
        LinkedList<Tieto> haettuTiedosto = tyohakemisto.hae(tNimi);
        
        if (haettuTiedosto.size() > 0) {
            return false;
        }
        Tiedosto uusiTiedosto = new Tiedosto(new StringBuilder(tNimi), uusiKoko);
        boolean onnistuikoLisays = tyohakemisto.lisaa(uusiTiedosto);
        return onnistuikoLisays;
    }
    
    public void siirtyma (String siirrytaanHakemistoon) {
        
        if (siirrytaanHakemistoon.equals("..")) {
            tyohakemisto = tyohakemisto.ylihakemisto();
            
        }
        else if (siirrytaanHakemistoon.equals("")) {
            tyohakemisto = juuri;
        }
        else {
            LinkedList<Tieto> loytyneet = tyohakemisto.hae(siirrytaanHakemistoon);
            if (loytyneet != null) {
                if (loytyneet.size() == 1 && loytyneet.get(0) instanceof Hakemisto) {
                    tyohakemisto = (Hakemisto)loytyneet.get(0);
                }
            }
            
        }
    }
    
    public LinkedList<Tieto> listattavatTiedot (String hakusana) {
        LinkedList<Tieto> palautettavaLista = new LinkedList<Tieto>();
        
        if (hakusana.equals("")) {
            LinkedList<Tieto> nykyinenHakemistoSisalto= tyohakemisto.hae(tyohakemisto.toString());
            return nykyinenHakemistoSisalto;
        }
        else {
            return tyohakemisto.hae(hakusana);
        }
    }
    
    public boolean poista (String hakusana) { 
        LinkedList<Tieto> loydetyt = tyohakemisto.hae(hakusana);
        if (loydetyt.isEmpty()) {
            return false;
        }
        for (Tieto tieto: loydetyt) {
            tyohakemisto.poista(tieto);
        }
        return true;
    }
    
    public boolean uudelleenNimeaminen (String nykyinenNimi, String uusiNimi) throws IllegalArgumentException {
        LinkedList<Tieto> loydettyNyky = tyohakemisto.hae(nykyinenNimi);
        LinkedList<Tieto> loydettyUusi = tyohakemisto.hae(uusiNimi);
        if (!loydettyNyky.isEmpty() && loydettyUusi.isEmpty()) {
            for (Tieto tieto: loydettyNyky) {
                tyohakemisto.nimi(new StringBuilder(uusiNimi));
            }
            return true;
        }
        return false;
    }
    /*
    public boolean sulje () {
        
    }*/
}
