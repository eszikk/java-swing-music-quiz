/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osztalyok.collection_tipus;

/**
 * Zenék kezeléséhez.
 * @author Krisztián
 */
public class Zene implements Comparable<Zene> {
    private int id;
    private String eloado,cim,mufaj,elersiUt;

    public Zene(int id, String eloado, String cim, String mufaj, String elersiUt) {
        this.id = id;
        this.eloado = eloado;
        this.cim = cim;
        this.mufaj = mufaj;
        this.elersiUt = elersiUt;
    }

    @Override
    public String toString() {
        return eloado + ": " + cim;
    }
    
    
    
    
    
//setterek
    public void setId(int id) {
        this.id = id;
    }

    public void setEloado(String eloado) {
        this.eloado = eloado;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public void setMufaj(String mufaj) {
        this.mufaj = mufaj;
    }


    public void setElersiUt(String elersiUt) {
        this.elersiUt = elersiUt;
    }
    
    
//getterek
    public int getId() {
        return id;
    }

    public String getEloado() {
        return eloado;
    }

    public String getCim() {
        return cim;
    }

    public String getMufaj() {
        return mufaj;
    }


    public String getElersiUt() {
        return elersiUt;
    }

    @Override
    public int compareTo(Zene t) {
        return this.getCim().compareTo(t.getCim());
    }
    
    
    
}
