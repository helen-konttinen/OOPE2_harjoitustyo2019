
package harjoitustyo.toiminnot;
import harjoitustyo.tiedot.*;
import java.util.LinkedList;
import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tieto;

/**
 * Tulkki -luokka, joka saa komentoja Kayttöliittymä -luokalta ja suorittaa ne
 * kutsumalla mm. Hakemisto ja Tieto -luokkia.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * <p>
 * @author Helen Konttinen (helen.konttinen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */
public class Tulkki {
    
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
        // Luodaan LinkedList -tyyppinen muuttuja, jonka geneeriseksi
        // tyypiksi on kiinnitetty Tieto, joka hakee parametrina annettua nimeä
        // jo luoduista hakemistoista.
        LinkedList<Tieto> haettu = tyohakemisto.hae(hNimi);
        
        //Jos palautettu listan koko on erisuuri kuin 0, eli listalla
        // on jotain palautetaan false. Parametrina annettu nimi on siis 
        // jo olemassa.
        if (haettu.size() != 0) {
            return false;
        }
        
        //Luodaan uusi hakemisto parametrilla annetulla nimellä ja nykyinen
        // työhakemisto ylihakemistona.
        Hakemisto uusiHakemisto = new Hakemisto(new StringBuilder(hNimi), tyohakemisto);
        // Yritetään lisätä luotu hakemisto listaan ja palautetaan onnistuiko
        // vai ei.
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
        // Luodaan uusi LinkedList geneerisellä tyypillä Tieto. Haetaan
        // parametina annetulla nimellä jo luoduista hakemistoista ja tiedostoista.
        LinkedList<Tieto> haettuTiedosto = tyohakemisto.hae(tNimi);
        
        // Jos listalla on jotain, eli nimi on jo käytössä, palautetaan false.
        if (haettuTiedosto.size() != 0) {
            return false;
        }
        // Luodaan uusi tiedosto parametrina annetulla nimellä ja koolla.
        // Yritetään lisätä tiedosto listalle. Palautetaan paluuarvo.
        Tiedosto uusiTiedosto = new Tiedosto(new StringBuilder(tNimi), uusiKoko);
        boolean onnistuikoLisays = tyohakemisto.lisaa(uusiTiedosto);
        return onnistuikoLisays;
    }
    
    /**
     * Vaihdetaan hakemistoa yli-, ali-, juuri- tai tietyn nimiseen hakemistoon.
     * @param siirrytaanHakemistoon nimi hakemistolle minne halutaan siirtyä.
     * @throws IllegalArgumentException jos parametreissa oli virhe.
     */
    public void siirtyma (String siirrytaanHakemistoon)throws IllegalArgumentException {
        
        // Jos parametina annettu merkkijono on "..",
        // tarkistetaan onko nykyhakemisto juurihakemisto. Silloin valitetaan.
        // Muuten asetetaan ylihakemisto nykyhakemistoksi.
        if (siirrytaanHakemistoon.equals("..")) {
            if (juuri == tyohakemisto) {
                System.out.println("Error!");
            }
            else {
                tyohakemisto = tyohakemisto.ylihakemisto();
            }
            
        }
        // Jos parametrina on saatu tyhjä merkkijono asetetaan nykyinen
        // hakemisto juuri hakemistoksi.
        else if (siirrytaanHakemistoon.equals("")) {
            tyohakemisto = juuri;
        }
        // muuten haetaan parametrilla saatua merkkijonoa kutsumalla Hakemisto
        // -luokan hae -metodia, joka palauttaa LikedListin, geneeriseksi tyypiksi kiinitetty
        // Tieto.
        else {
            LinkedList<Tieto> loytyneet = tyohakemisto.hae(siirrytaanHakemistoon);
            
            // jos lista ei ole tyhjä ja sen koko on suurempi kuin nolla,
            // eli listalta löytyi parametrina annetun niminen hakemisto,
            // tarkistetaan että löytyi vain yksi hakemisto sillä nimellä ja 
            // että listan ensimmäinen alkio on Hakemisto -tyyppiä.
            if (loytyneet != null && loytyneet.size() > 0) {
                if (loytyneet.size() == 1 && (loytyneet.getFirst() instanceof Hakemisto)) {
                    // nykyiseksi hakemistoksi asetetaan listasta löytynyt hakemisto.
                    tyohakemisto = (Hakemisto)loytyneet.get(0);
                }
                // muuten heitetään poikkeus.
                else {
                    throw new IllegalArgumentException();
                }
            }
            // muuten heitetään poikkeus.
            else {
                throw new IllegalArgumentException();
            }
        }
    }
    
    /**
     * Metodi, joka listaa nykyisen tai parametrilla annetun hakemiston
     * hakemiston alihakemistot ja tiedostot.
     * @param hakusana parametrina annettu nimi hakemistolle, jota halutaan
     * listata.
     * @return Linkitetyn listan, jonka geneeriseksi tyypiksi on kiinnitetty Tieto.
     * Lista sisältää listattavat tiedostot ja alihakemistot.
     */
    public LinkedList<Tieto> listattavatTiedot (String hakusana) {
        
        // jos parametrina annettu hakusana on tyhjä merkkijono haetaan nykyisen
        // hakemiston sisältö, kutsumalla Hakemisto -luokan sisalto -aksessoria.
        // Palautetaan saatu linkitetty lista.
        if (hakusana.equals("")) {
            LinkedList<Tieto> nykyinenHakemistoSisalto= tyohakemisto.sisalto();
            return nykyinenHakemistoSisalto;
        }
        // muuten kutsutaan Hakemisto -luokan hae -metodia paramerina hakusana
        // ja palautetaan se.
        else {
            return tyohakemisto.hae(hakusana);
        }
    }
    
    /**
     * Metodi, joka poistaa parametrina annetun nimisen tiedoston tai hakemiston.
     * @param hakusana parametrina annettu nimi hakemistolle tai tiedostolle, joka
     * halutaan poistaa.
     * @return true, jos onnistutaan poistamaan, muuten false.
     */
    public boolean poista (String hakusana) {
        // Kutsutaan Hakemisto -luokan hae -metodia parametrina annetulla hakusanalla.
        // Palauttaa linkitetyn listan.
        LinkedList<Tieto> loydetyt = tyohakemisto.hae(hakusana);
        // jos linkitetty lista on tyhjä, eli ei löydetty hakusanan
        // nimistä hakemistoa tai tiedostoa, palautetaan false.
        if (loydetyt.isEmpty()) {
            return false;
        }
        // muuten käydään läpi listaa alkio alkiolta ja 
        // kutsutaan Hakemisto-luokan poista -metodia.
        // Lopuksi palautetaan true.
        for (Tieto tieto: loydetyt) {
            tyohakemisto.poista(tieto);
        }
        return true;
    }
    
    /**
     * Metodi, joka uudelleen nimää tiedoston tai hakemiston, joka annetaan parametrina.
     * @param nykyinenNimi nimi hakemistolle/tiedostolle, joka halutaan nimetä uudelleen.
     * @param uusiNimi uusi nimi hakemistolle/tiedostolle, joka halutaan asettaa.
     * @return true, jos uudelleennimeäminen onnistui, muuten false.
     * @throws IllegalArgumentException jos parametri on virheellinen.
     */
    public boolean uudelleenNimeaminen (String nykyinenNimi, String uusiNimi) throws IllegalArgumentException {
        // Kutsutaan Hakemisto -luokan hae -metodia ja katsoaan löytyykö
        // nykyisellä nimellä ja uudella nimellä hakemistoa tai tiedostoa.
        LinkedList<Tieto> loydettyNyky = tyohakemisto.hae(nykyinenNimi);
        LinkedList<Tieto> loydettyUusi = tyohakemisto.hae(uusiNimi);
        // jos nykyisen hakemiston/tiedoston nimi löytyy ja uutta nimeä ei löydy
        if (loydettyNyky.size() == 1 && loydettyUusi.isEmpty()) {
            // otetaan talteen nykyisen hakemiston/tiedoston tiedot.
            Tieto nyky = loydettyNyky.get(0);
            try {
                // poistetaan nykyisen hakemiston/tiedoston nimi ja liitetään
                // siihen uusi nimi. Lopuksi kutsutaan Hakemisto -luokan lisaa 
                //-metodia joka lisää uuden nimen listaan oikeeseen kohtaan.
                poista(nyky.nimi().toString());
                nyky.nimi(new StringBuilder(uusiNimi));
                tyohakemisto.lisaa(nyky);
            }
            // napataan poikkeus, esim. uusi nimi on virheellinen, ja
            // asetetaan nykyisen hakemiston/tiedoston nimi takaisin ja 
            // lisätään se listaan oikeeseen kohta, sekä heitetään poikkeus.
            catch (Exception e){
                nyky.nimi(new StringBuilder(nyky.nimi().toString()));
                tyohakemisto.lisaa(nyky);
                throw new IllegalArgumentException();
            }

            // muuten palautetaan true
            return true;
        }
        // muuten heitetään poikkeus.
        else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Metodi, joka antaa hakemistopuun polun, eli missä Hakemistossa
     * sijaitaan tällä hetkellä.
     * @param nykyH nykyinen hakemisto.
     * @return merkkijonona hakemistopuun polku.
     */
    public String annaPolku (Hakemisto nykyH) {
        // alustetaan polku ">" -merkillä.
        String polku = ">";
        
        // käydään läpi silmukkaa niin kauan kun nykyinen hakemisto ei ole tyhjä.
        while (nykyH != null) {
            // otetaan talteen nykyisen hakemiston nimi kutsumalla Tieto -luokan
            // nimi -aksessoria.
            StringBuilder hNimi = nykyH.nimi();
            
            // sijainti on juuressa, jos parametrina annettu hakemisto on null.
            if(nykyH.ylihakemisto() == null) {
                polku = hNimi + "" + polku;
            }
            // muuten lisätään nykyisen hakemiston nimi ja kautta -viiva, sekä
            // loppuun ">" -merkki.
            else {
                polku = hNimi + "/" + polku;
            }
            // asetetaan nykyinen hakemisto nykyisen hakemiston ylihakemistoksi.
            nykyH = nykyH.ylihakemisto();
        }
        // palautetaan merkkijonon muodossa polku.
        return polku;
    }
    
    /**
     * Metodi kutsuu parametrillista annaPolku -metodia nykyisellä
     * hakemistolla.
     * @return merkkijonon muodossa hakemistopuun polun.
     */
    public String annaPolku () {
        return annaPolku(tyohakemisto);
    }
}
