package osztalyok.modellek;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;



/**
 * Saját gomb osztály 20% ban átlátszó gombok készítéséhez.
 * @author Krisztián
 */

public class SajatGomb extends JButton {
    private Color color=new Color(255, 150,0);
    
        public SajatGomb() {
            super();
        setOpaque(false);
        setBackground(color);
        
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        super.paint(g2);
        g2.dispose();
        
    }
}
