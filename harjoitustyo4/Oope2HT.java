/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo;

import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.omalista.OmaLista;
import harjoitustyo.tiedot.Tiedosto;
import harjoitustyo.tiedot.Tieto;

/**
 *
 * @author helen
 */
public class Oope2HT {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hakemisto juuri = new Hakemisto();
        Hakemisto tyohakemisto = juuri;
        
        Hakemisto h1 = new Hakemisto(new StringBuilder("pics"), null);
        boolean v1 = tyohakemisto.luodaanAlihakemisto("cats");
        
        /*Hakemisto h1 = new Hakemisto();
        System.out.println(h1);
        
        Hakemisto h2 = new Hakemisto(new StringBuilder("h2"),h1);
        
        System.out.println(h2);
        
        Tiedosto t1 = new Tiedosto(new StringBuilder("t1"), 300);
        
        h1.lisaa(t1);
        
        System.out.println(h1);
        
        for (int i = 0; i < h1.sisalto().size(); i++) {
            
            System.out.println(h1.sisalto().get(i).toString());
        }
        
        OmaLista n1 = new OmaLista();
        
        n1.lisaa("A");
        n1.lisaa("H");
        n1.lisaa("B");
        
        for (int m = 0; m < n1.size(); m++) {
            System.out.println(n1.get(m));
        }
        
        
        String v1 = new String("A");
        String v2 = new String("B");
        String v3 = new String("C");
        
        OmaLista lista1 = new OmaLista();
        
        lista1.lisaa(v1);
        lista1.lisaa(v2);
        lista1.lisaa(v2);
        lista1.lisaa(v3);
        
        for (int k = 0; k < lista1.size(); k++) {
            System.out.println(lista1.get(k));
        }
        
        lista1.poista(v2);
        
        
        System.out.println(lista1.size());
        for (int j = 0; j < lista1.size(); j++) {
            
            System.out.println(lista1.get(j));
        }*/
        
  
    }
    
}
