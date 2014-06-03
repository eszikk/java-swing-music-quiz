/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osztalyok.modellek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import osztalyok.collection_tipus.Rekord;

/**
 * Rekord tábla modell, a rekordok panelon lévő táblázathoz.
 * @author Krisztián
 */
public class rekordTablaModell extends AbstractTableModel {

    private static final String[] header = {"#", "Név", "Pont"};
    private static List<Rekord> rekordok = new ArrayList<>();

    public rekordTablaModell() {
    }

    @Override
    public int getRowCount() {
        return rekordok.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int culomnIndex) {
        switch (culomnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return rekordok.get(rowIndex).getNev();
            case 2:
                return rekordok.get(rowIndex).getPont();
            default:
                return null;
        }

    }

    @Override
    public String getColumnName(int i) {
        return header[i];
    }

    public void addElement(String nev, int pont) {
        rekordok.add(new Rekord(pont, nev));
        sort();

    }

    public void sort() {
        Collections.sort(rekordok);
        fireTableDataChanged();
    }
    
    public void Clear(){
        rekordok.clear();
    }
}
