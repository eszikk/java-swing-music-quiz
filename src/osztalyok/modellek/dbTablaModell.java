/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osztalyok.modellek;

import osztalyok.collection_tipus.Zene;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Adatbázis tábla modell, a zenékkezelése panelen lévő táblázathoz.
 * @author Krisztián
 */
public class dbTablaModell extends AbstractTableModel {

    private static final String[] header = {"Előadó", "Cím", "Műfaj", "Elérési út"};
    private static List<Zene> zenek = new ArrayList<>();

    public dbTablaModell() {
    }
    
        public dbTablaModell(List<Zene> list) {
            zenek=list;
    }
    
    

    @Override
    public int getRowCount() {
        return zenek.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int culomnIndex) {
        switch (culomnIndex) {
            case 0:
                return zenek.get(rowIndex).getEloado();
            case 1:
                return zenek.get(rowIndex).getCim();
            case 2:
                return zenek.get(rowIndex).getMufaj();
            case 3:
                return zenek.get(rowIndex).getElersiUt();
            default:
                return null;
        }
        
    }

    @Override
    public String getColumnName(int i) {
        return header[i];
    }
    
    public void addElement(String eloado,String cim,String mufaj,String eleres){
        int id=zenek.size();
        zenek.add(new Zene(id, eloado, cim, mufaj, eleres));
        sort();
        
    }
    
    public void removeElement(int i){
        zenek.remove(i);
        sort();
    }
    
    public void sort(){
        Collections.sort(zenek);
        fireTableDataChanged();
    }
    
    public void modositElem(int i,String eloado,String cim,String mufaj,String eleres){
        zenek.get(i).setCim(cim);
        zenek.get(i).setEloado(eloado);
        zenek.get(i).setMufaj(mufaj);
        zenek.get(i).setElersiUt(eleres);
        
        sort();
    }
    
    public void Clear(){
        zenek.clear();
    }
    
    public int getElementID(int i){
        return zenek.get(i).getId();
    }
}
