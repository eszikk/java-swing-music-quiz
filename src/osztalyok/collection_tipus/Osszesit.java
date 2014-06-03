/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osztalyok.collection_tipus;

/**
 * A felhasználó válaszainak kezeléséhez.
 * @author Krisztián
 */
public class Osszesit {
        private static final int ELOADO = 1;
    private static final int CIM = 2;
    private static final int MUFAJ = 3;
    private static final int FILM = 4;
    
    private int kerdes;
    private String kerdesSz;
    private String jovalasz;
    private String valaszSz;
    private Zene valasz;


    public Osszesit(int kerdes, Zene zene) {
        this.kerdes = kerdes;

                switch (kerdes) {
            case ELOADO:
                kerdesSz = "Melyik előadó dalát hallod?";
                valaszSz=zene.getEloado();
                break;
            case CIM:
                kerdesSz = "Mi az alábbi dal címe?";
                valaszSz=zene.getCim();
                break;
            case MUFAJ:
                kerdesSz = "Mi az alábbi zene műfaja?";
                valaszSz=zene.getMufaj();
                break;

        }

    }
    

    public String getKerdesSz() {
        return kerdesSz;
    }

    public String getJovalasz() {
        return jovalasz;
    }

    public String getValaszSz() {
        return valaszSz;
    }

    public void setJovalasz(String jovalasz) {
        this.jovalasz = jovalasz;
    }

    


    
    
    
}
