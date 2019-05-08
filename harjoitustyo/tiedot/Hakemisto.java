
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Sailova;
import harjoitustyo.omalista.OmaLista;
import java.util.LinkedList;
/**
 * Hakemisto -luokka, joka periytyy Tieto -luokasta ja toteuttaa Sailova
 * -rajapinnan. Luokka sisältää hakemistolle tyypilliset tiedot.
 * <p>
 * Harjoitustyo, Olio-ohjelmoinnin perusteet II, 2019
 * <p>
 * @author Helen Konttinen, (helen.konttinen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */
public class Hakemisto extends Tieto implements Sailova<Tieto> {
    /** OmaLista -tyyppinen attribuutti hakemiston sisällölle.*/
    private OmaLista<Tieto> sisalto;
    /** Hakemisto -tyyppinen attribuutti tämän hakemiston ylihakemistolle.*/
    private Hakemisto ylihakemisto;

    /**
     * Oletusrakentaja Hakemsitolle, joka kutsuu yliluokan oletusrakentajaa, 
     * ja jossa sisalto-attribuuttiin liitetään tyhjä OmaLista<Tieto>-tyyppinen 
     * listaolio, sekä asetetaan ylihakemiston
     * null -arvoiseksi.
     */
    public Hakemisto() {
        super();
        this.sisalto = new OmaLista<Tieto>();
        ylihakemisto = null;
    }
    
    /**
     * Kaksiparametrinen rakentaja, joka kutsuu yliluokan rakentajaa nimen asettamiselle,
     * ja joka asettaa parametrina annetun ylihakemiston kutsumalla ylihakemsitoa asettavaa
     * aksessoria. Rakentajassa luodaan tyhjä listaolio ja liitetään siihen attribuutti.
     * @param hakemistoNimi StringBuilder -tyyppinen parametri nimelle, joka halutaan asettaa 
     * hakemistolle.
     * @param yHakemisto Hakemsito -tyyppinen parametri hakemistolle, joka halutaan asettaa
     * nykyisen hakemiston ylihakemistoksi.
     */
    public Hakemisto (StringBuilder hakemistoNimi, Hakemisto yHakemisto) {
        super(hakemistoNimi);
        ylihakemisto(yHakemisto);
        this.sisalto = new OmaLista<Tieto>();
    }
    
    /**
     * Ylihakemsitoa asettava aksessori.
     * @param yHakemisto Hakemisto -tyyppinen parametri hakemistolle, joka halutaan
     * asettaa ylihakemistoksi.
     */
    public void ylihakemisto(Hakemisto yHakemisto) {
        ylihakemisto = yHakemisto;
    }
    
    /**
     * Ylihakemistoa lukeva aksessori.
     * @return viitteen hakemiston ylihakemistoon.
     */
    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }
    
    /**
     * Sisältöä lukeva aksessori.
     * @return viitteen hakemiston sisältöön.
     */
    public OmaLista<Tieto> sisalto() {
        return sisalto;
    }
    
    /**
     * Sisältöä asettava aksessori.
     * @param uusiSisalto parametri uudelle sisällölle, joka halutaan asettaa.
     */
    public void sisalto(harjoitustyo.omalista.OmaLista<harjoitustyo.tiedot.Tieto> uusiSisalto) {
        sisalto = uusiSisalto;
    }
    
    
    @SuppressWarnings("unchecked")
    @Override
    public LinkedList<Tieto> hae(String hakusana) {
        LinkedList<Tieto> haettuLista = new LinkedList<Tieto>();
        try {
            for (int i = 0; i < sisalto.size(); i++) {
                Tieto seuraavaAlkio = sisalto.get(i);
                
                if (seuraavaAlkio.equals(hakusana)) {
                    haettuLista.add(seuraavaAlkio);
                }
            }
            return haettuLista;
        }
        catch (IndexOutOfBoundsException e) {
            return haettuLista;
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
     * Object-luokan korvattu toString -metodi, missä kutsutaan
     * yliluokan toString -metodia.
     */    
    @Override
    public String toString () {
        return super.toString() + "/ " + sisalto.size();
    }
}
