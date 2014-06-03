/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osztalyok.modellek;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import osztalyok.collection_tipus.Osszesit;

/**
 * Osszesítés modell a játékvége panelon található táblázathoz.
 * @author Krisztián
 */
public class osszesitTablaModell extends AbstractTableModel {

    private static final String[] header = {"#", "Kérdés", "Helyes válasz", "Felhasználó válasza"};
    private static List<Osszesit> osszesites = new ArrayList<>();
    private int counter=0;

    public osszesitTablaModell() {
    }
    
        public osszesitTablaModell(List<Osszesit> list) {
            osszesites=list;
    }
    
    

    @Override
    public int getRowCount() {
        return osszesites.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int culomnIndex) {
        switch (culomnIndex) {
            case 0:               
                return rowIndex+1; 
            case 1:
                return osszesites.get(rowIndex).getKerdesSz();
            case 2:
                return osszesites.get(rowIndex).getValaszSz();
            case 3:
                return osszesites.get(rowIndex).getJovalasz();
            default:
                return null;
        }


    }
    

    @Override
    public String getColumnName(int i) {
        return header[i];
    }

    
    
    public void Clear(){
        osszesites.clear();
        fireTableDataChanged();
    }
    
    public void addElement(Osszesit e){
        osszesites.add(e);
        fireTableDataChanged();
    }
    
    public Osszesit getElement(int i){
         return osszesites.get(i);
    }
}
