
package harjoitustyo.tiedot;
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
        
        if (siirrytaanHakemistoon.equals("cd ..")) {
            tyohakemisto = tyohakemisto.ylihakemisto();
            
        }
        else if (siirrytaanHakemistoon.equals("cd")) {
            tyohakemisto = juuri;
        }
        else {
            siirrytaanHakemistoon = siirrytaanHakemistoon.substring(3);
            LinkedList<Tieto> loytyneet = tyohakemisto.hae(siirrytaanHakemistoon);
            if (loytyneet != null) {
                if (loytyneet.size() == 1 && loytyneet.get(0) instanceof Hakemisto) {
                    tyohakemisto = (Hakemisto)loytyneet.get(0);
                }
            }
            
        }
    }
    /*
    public boolean listattavatTiedot () {
        
    }
    
    public boolean poista () {
        
    }
    
    public boolean uudelleenNimeaminen () {
        
    }
    
    public boolean sulje () {
        
    }*/
}
