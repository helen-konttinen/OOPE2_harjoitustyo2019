
package harjoitustyo.toiminnot;
import harjoitustyo.tiedot.*;
import java.util.LinkedList;
import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tieto;

/**
 * Tulkki -luokka, joka saa komentoja Käyttöliittymä -luokalta ja suorittaa ne
 * kutsumalla mm. Hakemisto ja Tieto luokkia.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * <p>
 * @author Helen Konttinen (helen.konttinen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */
public class Tulkki {
    /**
     * Attribuutit.
     */
    
    /**
     * Hakemisto -tyyppinen attribuutti juurihakemistolle.
     */
    private Hakemisto juuri;
    
    /**
     * Hakemisto -tyyppinen attribuutti nykyiselle hakemistolle,
     * eli työstettävälle hakemistolle.
     */
    private Hakemisto tyohakemisto;
    
    /** 
     * Rakentajat.
     * 
     * */
    
    /**
     * Oletusrakentaja tulkille.
     * Asettaa uuden juurihakemiston ja asettaa sen
     * työhakemistoksi.
     */
    public Tulkki () {
        juuri = new Hakemisto();
        tyohakemisto = juuri;
    }
    
    /**
     * Luodaan alihakemisto nykyiselle hakemistolle.
     * @param hNimi parametrina annettu nimi uudelle alihakemistolle.
     * @return true, jos onnistutaan luomaan alihakemisto, ja false jos ei
     * onnistuta.
     */
    public boolean luodaanAlihakemisto (String hNimi) {
        /**Luodaan LinkedList -tyyppinen muuttuja, jonka geneeriseksi
         * tyypiksi on kiinnitetty Tieto, joka hakee parametrina annettua nimeä
         * jo luoduista hakemistoista.
         */
        LinkedList<Tieto> haettu = tyohakemisto.hae(hNimi);
        
        /** Jos palautettu listan koko on erisuuri kuin 0, eli listalla
         * on jotain palautetaan false. Parametrina annettu nimi on siis 
         * jo olemassa.
         */
        if (haettu.size() != 0) {
            return false;
        }
        
        /** Luodaan uusi hakemisto parametrilla annetulla nimellä ja nykyinen
         * työhakemisto ylihakemistona.
         * 
         */
        Hakemisto uusiHakemisto = new Hakemisto(new StringBuilder(hNimi), tyohakemisto);
        /** Yritetään lisätä luotu hakemisto listaan ja palautetaan onnistuiko
         * vai ei.
         */
        boolean onnistuikoLisays = tyohakemisto.lisaa(uusiHakemisto);
        return onnistuikoLisays;
    }
    
    /**
     * Luodaan uusi Tiedosto nykyiseen hakemistoon.
     * @param tNimi tiedoston nimelle,
     * @param uusiKoko tiedoston koolle
     * @return true, jos tiedoston lisääminen onnistui, muuten false.
     */
    public boolean luodaanTiedosto (String tNimi, int uusiKoko) {
        /**
         * Luodaan uusi LinkedList geneerisellä tyypillä Tieto. Haetaan
         * parametina annetulla nimellä jo luoduista hakemistoista ja tiedostoista.
         */
        LinkedList<Tieto> haettuTiedosto = tyohakemisto.hae(tNimi);
        
        /** 
         * Jos listalla on jotain, eli nimi on jo käytössä, palautetaan false.
         */
        if (haettuTiedosto.size() != 0) {
            return false;
        }
        /**
         * Luodaan uusi tiedosto parametrina annetulla nimellä ja koolla.
         * Yritetään lisätä tiedosto listalle. Palautetaan paluuarvo.
         */
        Tiedosto uusiTiedosto = new Tiedosto(new StringBuilder(tNimi), uusiKoko);
        boolean onnistuikoLisays = tyohakemisto.lisaa(uusiTiedosto);
        return onnistuikoLisays;
    }
    
    /**
     * Vaihdetaan hakemistoa yli-, ali-, juuri- tai tietyn nimiseen hakemistoon.
     * @param siirrytaanHakemistoon nimi, hakemistolle minne halutaan siirtyä.
     * @throws IllegalArgumentException jos parametreissa oli virhe.
     */
    public void siirtyma (String siirrytaanHakemistoon)throws IllegalArgumentException {
        
        /** 
         * Jos parametina annettu merkkijono on "..",
         * tarkistetaan onko nykyhakemisto juurihakemisto. Silloin valitetaan.
         * Muutenasetetaan ylihakemisto nykyhakemistoksi.
         */
        if (siirrytaanHakemistoon.equals("..")) {
            if (juuri == tyohakemisto) {
                System.out.println("Error!");
            }
            else {
                tyohakemisto = tyohakemisto.ylihakemisto();
            }
            
        }
        /**
         * Jos parametrina
         */
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
        if (loydettyNyky.size() == 1 && loydettyUusi.isEmpty()) {
            Tieto nyky = loydettyNyky.get(0);
            try {
                poista(nyky.nimi().toString());
                nyky.nimi(new StringBuilder(uusiNimi));
                tyohakemisto.lisaa(nyky);
            }
            catch (Exception e){
                nyky.nimi(new StringBuilder(nyky.nimi().toString()));
                tyohakemisto.lisaa(nyky);
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
