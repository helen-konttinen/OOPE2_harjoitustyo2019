
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
        
        if (haettu.size() != 0) {
            return false;
        }
        Hakemisto uusiHakemisto = new Hakemisto(new StringBuilder(hNimi), tyohakemisto);
        boolean onnistuikoLisays = tyohakemisto.lisaa(uusiHakemisto);
        return onnistuikoLisays;
    }
    
    public boolean luodaanTiedosto (String tNimi, int uusiKoko) {
        LinkedList<Tieto> haettuTiedosto = tyohakemisto.hae(tNimi);
        
        if (haettuTiedosto.size() != 0) {
            return false;
        }
        Tiedosto uusiTiedosto = new Tiedosto(new StringBuilder(tNimi), uusiKoko);
        boolean onnistuikoLisays = tyohakemisto.lisaa(uusiTiedosto);
        return onnistuikoLisays;
    }
    
    public void siirtyma (String siirrytaanHakemistoon)throws IllegalArgumentException {
        
        if (siirrytaanHakemistoon.equals("..")) {
            if (juuri == tyohakemisto) {
                System.out.println("Error!");
            }
            else {
                tyohakemisto = tyohakemisto.ylihakemisto();
            }
            
        }
        else if (siirrytaanHakemistoon.equals("")) {
            tyohakemisto = juuri;
        }
        else {
            LinkedList<Tieto> loytyneet = tyohakemisto.hae(siirrytaanHakemistoon);
            
            if (loytyneet != null && loytyneet.size() > 0) {
                if (loytyneet.size() == 1 && (loytyneet.getFirst() instanceof Hakemisto)) {
                    tyohakemisto = (Hakemisto)loytyneet.get(0);
                }
                else {
                    throw new IllegalArgumentException();
                }
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public LinkedList<Tieto> listattavatTiedot (String hakusana) {
        //LinkedList<Tieto> palautettavaLista = new LinkedList<Tieto>();
        
        if (hakusana.equals("")) {
            LinkedList<Tieto> nykyinenHakemistoSisalto= tyohakemisto.sisalto();
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
                tieto.nimi(new StringBuilder(uusiNimi)); 
                boolean onnistuikoListaus = tyohakemisto.lisaa(tieto);
                
                if (!onnistuikoListaus) {
                    return false;
                }
            }
            return true;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    public String annaPolku (Hakemisto nykyH) {
        String polku = ">";
        
        while (nykyH != null) {
            StringBuilder hNimi = nykyH.nimi();
            
            // sijainti on juuressa, jos parametrina annettu hakemisto on null
            if(nykyH.ylihakemisto() == null) {
                polku = hNimi + "" + polku;
            }
            else {
                polku = hNimi + "/" + polku;
            }
            nykyH = nykyH.ylihakemisto();
        }
        return polku;
    }
    
    public String annaPolku () {
        return annaPolku(tyohakemisto);
    }
}
