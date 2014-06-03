/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osztalyok.modellek;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A costum cellrenderer osztály segít kiszinezni a táblázatok sorait.
 * Ha a jó és a felhasználóválasza eggyezik akkor zöld egyébbként piros.
 * @author Krisztián
 */
public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component roW = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       
        if (((String) table.getValueAt(row, 2)).equals((String) (table.getValueAt(row, 3)))) {

            roW.setBackground(Color.green);
        } else {

            roW.setBackground(Color.red);
        }


        return roW;
    }
}
