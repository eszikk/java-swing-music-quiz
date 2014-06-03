/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osztalyok;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import osztalyok.collection_tipus.Kerdes;
import osztalyok.collection_tipus.Osszesit;
import osztalyok.collection_tipus.Zene;

/**
 *
 * @author Krisztián
 */
public class Jatek {

    private static final int ELOADO = 1;
    private static final int CIM = 2;
    private static final int MUFAJ = 3;
    private static final int VEGYES = 5;
    private static String felhasznaloNev;
    private int kerdesekSzama, valaszokSzama;
    private int kerdesTipus;
    private static List<Zene> adatbazis = new ArrayList<>();
    private static List<Zene> kivalasztott = new ArrayList<>();
    private static List<Kerdes> kerdesek = new ArrayList<>();
    private static List<Osszesit> osszesites = new ArrayList<>();

    public Jatek(String felhasznaloNev, int kerdesekSzama, int valaszokSzama, int kerdesTipus, List<Zene> lista) {
        this.felhasznaloNev = felhasznaloNev;
        this.kerdesekSzama = kerdesekSzama;
        this.valaszokSzama = valaszokSzama;
        this.kerdesTipus = kerdesTipus;
        this.adatbazis = lista;

    }

    public Jatek() {
    }

    public static List<Osszesit> getOsszesites() {
        return osszesites;
    }
/**
 * A megadott adatok alapján generál egy kérdés listátt.
 */
    public void kerdesGeneral() {

        List<Zene> adatbazisCopy = new ArrayList<>();
        List<Zene> valaszok = new ArrayList<>();
        Random r = new Random();
        int kerdesIndex;
        String kerdes;
        int kerdest;
        int veletlen;




        for (int i = 0; i <= kerdesekSzama - 1; i++) {
            adatbazisCopy.addAll(adatbazis);


            kerdest = kerdesTipus;

            if (kerdest == VEGYES) {
                kerdest = r.nextInt(3) + 1;
                System.out.println(kerdest);
            }


            for (int j = 0; j < valaszokSzama; j++) {

                System.out.println(adatbazis.size() + " " + adatbazisCopy.size() + " " + j);
                veletlen = r.nextInt(adatbazisCopy.size() - 1);
                valaszok.add(adatbazisCopy.get(veletlen));
                adatbazisCopy.remove(veletlen);



            }


            kerdesIndex = r.nextInt(valaszokSzama - 1);
            


            adatbazisCopy.clear();
            kerdesek.add(new Kerdes(kerdest, kerdesIndex, valaszok));
            osszesites.add(new Osszesit(kerdest, valaszok.get(kerdesIndex)));
            valaszok.clear();

        }
    }

    public void KerdesKiir() {
        for (Kerdes k : kerdesek) {
            System.out.println(k.toString());
        }

    }

    public static List<Kerdes> getKerdesek() {
        return kerdesek;
    }

    public static List<Zene> getAdatbazis() {
        return adatbazis;
    }

    public static List<Zene> getKivalasztott() {
        return kivalasztott;
    }

    public int adatbazisSize() {
        return adatbazis.size();
    }
/**
 * A felhasználó által megjelölt válaszok alapján az eredmények kiszámítása.
 * @return 
 */
    public double getEredmeny() {
        int joCount = 0;
        double eredmeny;

        for (Osszesit e : osszesites) {
            if (e.getJovalasz().equals(e.getValaszSz())) {
                joCount++;
            }
        }

        eredmeny = ((double) joCount / osszesites.size());

        return eredmeny * 100;


    }

    public String getFelhasznaloNev() {
        return felhasznaloNev;
    }
}
