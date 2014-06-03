/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osztalyok.collection_tipus;

/**
 * Rekordok kezeléséhez.
 * @author Krisztián
 */
public class Rekord implements Comparable<Rekord> {
    private int pont;
    private String nev;

    public Rekord(int pont, String nev) {
        this.pont = pont;
        this.nev = nev;
    }

    public int getPont() {
        return pont;
    }

    public void setPont(int pont) {
        this.pont = pont;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    @Override
    public int compareTo(Rekord t) {
        if(this.pont<t.pont){
            return 1;
        }else if(this.pont>t.pont){
            return -1;
        }else{
            return 0;
        }
    }
    
    
    
}
