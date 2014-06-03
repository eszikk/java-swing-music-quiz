/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osztalyok.collection_tipus;

import java.util.ArrayList;
import java.util.List;

/**
 * Kérdés típusú objektumok kezeléséhez.
 * @author Krisztián
 */
public class Kerdes {

    private static final int ELOADO = 1;
    private static final int CIM = 2;
    private static final int MUFAJ = 3;
    private int kerdes;
    private String kerdesSz;
    private int jovalaszIndex;
    private List<Zene> valaszok = new ArrayList<>();

    public Kerdes(int kerdes, int jovalaszIndex, List<Zene> z) {
        this.kerdes = kerdes;
        this.jovalaszIndex = jovalaszIndex;
        valaszok.addAll(z);

        switch (kerdes) {
            case ELOADO:
                kerdesSz = "Melyik előadó dalát hallod?";
                break;
            case CIM:
                kerdesSz = "Mi az alábbi dal címe?";
                break;
            case MUFAJ:
                kerdesSz = "Mi az alábbi zene műfaja?";
                break;



        }


    }

    public void setKerdes(int kerdes) {
        this.kerdes = kerdes;
    }

    public void setJovalaszIndex(int jovalaszIndex) {
        this.jovalaszIndex = jovalaszIndex;
    }

    public int getKerdes() {
        return kerdes;
    }

    public int getJovalaszIndex() {
        return jovalaszIndex;
    }

    public List<Zene> getValaszok() {
        return valaszok;
    }

    public String getKerdesSz() {
        return kerdesSz;
    }
    

    @Override
    public String toString() {
        return "Kerdes{" + "kerdes=" + kerdes + ", jovalaszIndex=" + jovalaszIndex + ", valaszok=" + valaszok + '}';
    }
}
