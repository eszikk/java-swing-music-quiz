/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package feluletek;

import java.awt.Graphics;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Krisztián
 */
public class FoMenuPanel extends javax.swing.JPanel {

    private int szel = 400, mag = 400;
    private zeneKvizFrame zenekvizframe;

    public FoMenuPanel() {
        this.setSize(szel, mag);
        initComponents();
    }
/**
 * Zenekvizframe átadás a panelnek.
 * @param zenekvizframe 
 */
    public void setZenekvizframe(zeneKvizFrame zenekvizframe) {
        this.zenekvizframe = zenekvizframe;
    }
/**
 * Háttér és felirat kirajzolása.
 * @param g 
 */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image kep = new ImageIcon(this.getClass().getResource("/kepek/main.jpg")).getImage();
        g.drawImage(kep, 0, 0, szel, mag+30, this);
        Image szoveg = new ImageIcon(this.getClass().getResource("/kepek/zenekviz.gif")).getImage();
        g.drawImage(szoveg, 55, 50, 300, 150, this);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnUjJatek = new osztalyok.modellek.SajatGomb();
        btnZenekKezelese = new osztalyok.modellek.SajatGomb();
        btnRekordok = new osztalyok.modellek.SajatGomb();
        btnKilepes = new osztalyok.modellek.SajatGomb();

        setMaximumSize(new java.awt.Dimension(400, 400));
        setMinimumSize(new java.awt.Dimension(400, 400));

        btnUjJatek.setFont(new java.awt.Font("Vijaya", 1, 18)); // NOI18N
        btnUjJatek.setText("ÚJ JÁTÉK");
        btnUjJatek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUjJatekActionPerformed(evt);
            }
        });

        btnZenekKezelese.setFont(new java.awt.Font("Vijaya", 1, 18)); // NOI18N
        btnZenekKezelese.setText("ZENÉK KEZELÉSE");
        btnZenekKezelese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZenekKezeleseActionPerformed(evt);
            }
        });

        btnRekordok.setFont(new java.awt.Font("Vijaya", 1, 18)); // NOI18N
        btnRekordok.setText("REKORDOK");
        btnRekordok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRekordokActionPerformed(evt);
            }
        });

        btnKilepes.setFont(new java.awt.Font("Vijaya", 1, 18)); // NOI18N
        btnKilepes.setText("KILÉPÉS");
        btnKilepes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKilepesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUjJatek, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZenekKezelese, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRekordok, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKilepes, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(btnUjJatek)
                .addGap(6, 6, 6)
                .addComponent(btnZenekKezelese)
                .addGap(6, 6, 6)
                .addComponent(btnRekordok)
                .addGap(6, 6, 6)
                .addComponent(btnKilepes)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
/**
 * Program bezárása.
 * @param evt 
 */
    private void btnKilepesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKilepesActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnKilepesActionPerformed
/**
 * Zenekezelési panel előkészítése, megnyitása.
 * Adatbáziskapcsolat megnyitása.
 * @param evt 
 */
    private void btnZenekKezeleseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZenekKezeleseActionPerformed

       zenekvizframe.remove(this);
       zenekvizframe.add(zenekvizframe.getZenekKezelesePanel1());
       zenekvizframe.pack();
        try {
            zenekvizframe.getZenekKezelesePanel1().adatbazsiKapcsolat();
        } catch (Exception ex) {
            Logger.getLogger(FoMenuPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_btnZenekKezeleseActionPerformed
/**
 * UjJatekPanel előkészítése, megnyitása.
 * @param evt 
 */
    private void btnUjJatekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUjJatekActionPerformed
        zenekvizframe.remove(this);
        zenekvizframe.add(zenekvizframe.getUjJatekPanel());
        zenekvizframe.pack();


    }//GEN-LAST:event_btnUjJatekActionPerformed

    private void btnRekordokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRekordokActionPerformed
        zenekvizframe.remove(this);
        zenekvizframe.add(zenekvizframe.getRekordPanel1());
        zenekvizframe.pack();
        try {
            zenekvizframe.getRekordPanel1().setTabla();
        } catch (Exception ex) {
            Logger.getLogger(FoMenuPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnRekordokActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKilepes;
    private javax.swing.JButton btnRekordok;
    private javax.swing.JButton btnUjJatek;
    private javax.swing.JButton btnZenekKezelese;
    // End of variables declaration//GEN-END:variables
}
