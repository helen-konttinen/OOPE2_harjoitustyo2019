
package harjoitustyo.toiminnot;

import harjoitustyo.apulaiset.In;
import java.util.LinkedList;
import harjoitustyo.tiedot.Tieto;

/**
 * Pääsilmukan sisältävä Käyttöliittymä -luokka, jossa luetaan In -luokan
 * avulla annetut kehotteet ja työstetään komentoja kutsumalla tulkki -luokkaa.
 * <p>
 * Harjoitustyo, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * <p>
 * @author Helen Konttinen, (helen.konttinen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */
public class Kayttoliittyma {
    /**
     * Metodi, joka lähtee suorittamaan eri komentoja, jotka ollaan 
     * saatu käyttäjältä.
     */
    public void suorita () {
        // asetetaan lippumuuttuja pääsilmukalle, ja sen arvoksi true.
        boolean ajetaan = true;
        // luodaan uusi tulkki.
        Tulkki tulkki = new Tulkki();
        // pääsilmukka.
        while (ajetaan) {
            // tulostetaan joka kierroksella kautta -viiva komentoikkunan alkuun.
            System.out.print("/");
            // kutsutaan Tulkki -luokan parametritonta annaPolku -metodia
            // joka palauttaa tämän hetkisen hakemistopuun polun. Tulostetaan
            // polku heti kautta -viivan perään joka kierroksella.
            System.out.print(tulkki.annaPolku());
            // luetaan käyttäjän antama komento In -luokan read -metodin avulla.
            String komento = In.readString();

            // jos komento alkaa "md"
            if (komento.startsWith("md")) {
                try {
                    // jos komento on ainoastaan "md" (sen jälkeen ei tule mitään)
                    // annetaan virheilmoitus.
                    if (komento.equals("md")) {
                        System.out.println("Error!");
                    }
                    // muuten karsitaan pois "md " komennon alusta ja kutsutaan
                    // Tulkki -luokan luodaanAlihakemisto -metodia, parametrina komento.
                    else {
                        komento = komento.substring(3);
                        boolean onnistuiko = tulkki.luodaanAlihakemisto(komento);
                        
                        // jos paluuarvo on false, eli alihakemiston luonti ei onnistunut,
                        // ilmoitetaan virheestä.
                        if (!onnistuiko) {
                        System.out.println("Error!");
                        }
                    }     
                }
                // napataan kiinni kaikista poikkeuksista ja tulostetaan virheilmoitus.
                catch (Exception e) {
                    System.out.println("Error!");
                }
            }
            // jos komento alkaa "mf"
            else if (komento.startsWith("mf")) {
                try {
                    // jos komento on pelkästään "mf" annetaan virheilmoitus.
                    if (komento.equals("mf")) {
                        System.out.println("Error!");
                    }
                    // muuten karsitaan pois "mf " komennon alusta ja jaetaan 
                    // komento String -luokan split -metodilla välilyöntejen perusteella.
                    else {
                        komento = komento.substring(3);
                        String[] katkottu = komento.split(" ");
                        
                        // jos taulukossa on enemmän kuin kaksi alkiota, eli käyttäjä
                        // on antanut nimen ja koon lisäksi kolmannen parametrin heitetään
                        // poikkeus.
                        if (katkottu.length < 2) {
                            throw new IllegalArgumentException();
                        }
                        // jos taulukossa on vähemmän kuin kaksi alkiota, eli ollaan annettu
                        // liian vähän parametreja, heitetään poikkeus.
                        if (katkottu.length > 2) {
                            throw new IllegalArgumentException();
                        }
                        // kutsutaan Tulkki -luokan luodaanTiedosto -metodia taulukon nolla
                        // kohdassa olevan alkion (tiedoston nimen), ja ykkös kohdassa olevan
                        // alkion (tieoston koon) kanssa. Muunnetaan tiedoston koko parametri
                        // Stringistä intiksi parseIntin avulla.
                        boolean onnistuiko = tulkki.luodaanTiedosto(katkottu[0], Integer.parseInt(katkottu[1]));
                        
                        // jos tiedoston luominen ei onnistunut, heitetään poikkeus.
                        if (!onnistuiko) {
                            throw new IllegalArgumentException();
                        }
                    }  
                }
                // otetaan kiinni kaikki poikkeukset ja annetaan virheilmoitus.
                catch (Exception e) {
                    System.out.println("Error!");
                }
            }
            // jos komento alkaa "cd"
            else if (komento.startsWith("cd")) {
                try {
                    // jos komento on pelkästään "cd" annetaan komennon
                    // arvoksi tyhjä merkkijono.
                    if (komento.equals("cd")) {
                        komento = "";
                    }
                    // muuten karsitaan pois "cd " komennon alusta ja kutsutaan
                    // Tulkki -luokan siirtyma -metodia parametrina komento.
                    else {
                        komento = komento.substring(3);
                    }

                    tulkki.siirtyma(komento);
                }
                // napataan kaikki poikkeukset ja annetaan virheilmoitus.
                catch (Exception e) {
                    System.out.println("Error!");
                }
            }
            // jos komento alkaa "ls".
            else if (komento.startsWith("ls")) {
                try {
                    //jos komento on pelkästään "ls" asetetaan komennon
                    // arvoksi tyhjä merkkijono.
                    if (komento.equals("ls")) {
                        komento = "";
                    }
                    // muuten karsitaan pois "ls " komennon alusta.
                    else {
                        komento = komento.substring(3);
                    }

                    // kutsutaan Tulkki -luokan listattavatTiedot -metodia parametrina komento.
                    // Paluuarvona saadaan LinkedList geneerisellä Tieto -tyypillä.
                    LinkedList<Tieto> listattava = tulkki.listattavatTiedot(komento);
                    
                    // jos komento oli vain ls ja palautetun listan koko on 0 ja komento ei ollut
                    // "*" annetaan virheilmoitus.
                    if (!(komento.equals("")) && listattava.size() == 0 && !(komento.equals("*"))) {
                        System.out.println("Error!");
                    }
                    //muuten käydään läpi palautettua listaa ja tulostetaan
                    // listan alkiot.
                    else {
                        for (Tieto tieto: listattava) {
                            System.out.println(tieto.toString());
                        }
                    }
                }
                // napataan kaikki poikkeukset ja annetaan virheilmoitus.
                catch (Exception e) {
                    
                }
            }
            // jos komento alkaa "rm"
            else if (komento.startsWith("rm")) {
                try {
                    // jos komento on pelkästään "rm" annetaan virheilmoitus.
                    if (komento.equals("rm")) {
                        System.out.println("Error!");
                    }
                    // muuten karsitaan pois "rm " komennon alusta ja kutsutaan
                    // Tulkki-luokan poista -metodia parametrina komento.
                    else {
                        komento = komento.substring(3);
                        boolean loytyiPoistettavia = tulkki.poista(komento);
                        
                        // jos paluuarvo on false, eli ei onnistuttu poistamaan, heitetään
                        // poikkeus.
                        if (!loytyiPoistettavia) {
                            throw new IllegalArgumentException();
                        }
                    }
                }
                // napataan kaikki poikkeukset ja annetaan virheilmoitus.
                catch (Exception e) {
                    System.out.println("Error!");
                }
            }
            // jos komento alkaa "mv"
            else if (komento.startsWith("mv")) {
                try {
                    // jos komento on pelkästään "mv" annetaan virheilmoitus.
                    if (komento.equals("mv")) {
                        System.out.println("Error!");
                    }
                    // muuten karsitaan pois "mv " komennon alusta ja jaetaan komento
                    // String -luokan split -metodilla taulukoksi.
                    else {
                        komento = komento.substring(3);
                        String[] katkottu = komento.split(" ");
                        
                        // jos taulukon koko on suurempi kuin kaksi, eli käyttäjä
                        // on antanut liian monta parametria heitetään poikkeus.
                        if (katkottu.length > 2) {
                            throw new IllegalArgumentException();
                        }
                        
                        // kutsutaan Tulkki-luokan uudelleenNimeaminen -metodia taulukon
                        // nolla indeksin alkiolla (nykyisen nimi) ja ykkös indeksin alkiolla
                        // (uusi nimi).
                        boolean nimettyUudelleen = tulkki.uudelleenNimeaminen(katkottu[0], katkottu[1]);

                        // jos uudelleen nimeäminen epäonnistuu, eli paluuarvo on false,
                        // annetaan virheilmoitus.
                        if (!nimettyUudelleen) {
                            System.out.println("Error!");
                        }
                    }
                }
                // napataan kaikki poikkeukset ja annetaan virheilmoitus.
                catch (Exception e) {
                    System.out.println("Error!");
                }
            }
            // jos komento on == "exit" käännetään lippumuuttuja ja siirrytään
            // Ajoluokkaan, Oope2HT.java.
            else if (komento.equals("exit")) {
                ajetaan = false;
            }
            // jos komento ei ole mikään yllä mainituista annetaan virheilmoitus.
            else {
                System.out.println("Error!");
            }
        }
    }
}
