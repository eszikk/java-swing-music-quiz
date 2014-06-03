/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package feluletek;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javazoom.jl.decoder.JavaLayerException;
import osztalyok.Jatek;
import osztalyok.collection_tipus.Kerdes;
import osztalyok.collection_tipus.Osszesit;
import osztalyok.mp3;

/**
 *
 * @author Krisztián
 */
public class jatekPanel extends javax.swing.JPanel {

    private static final int ELOADO = 1;
    private static final int CIM = 2;
    private static final int MUFAJ = 3;
    private static final int VEGYES = 5;
    private static final int PLAY = 1;
    private static final int PAUSE = 2;
    private static final int STOP = 3;
    private int szel = 400, mag = 400;
    private zeneKvizFrame zenekvizframe;
    private Jatek jatek = new Jatek();
    private List<Kerdes> kerdesek = jatek.getKerdesek();
    private List<Osszesit> Osszesites = jatek.getOsszesites();
    private mp3 lejatszo;
    private int panel;
    private int playstate = 1;
    private String userValasz;

    public void setZenekvizframe(zeneKvizFrame zenekvizframe) {
        this.zenekvizframe = zenekvizframe;
    }

    public jatekPanel() {
        initComponents();

        this.setSize(szel, mag);
        this.setMinimumSize(new Dimension(szel, mag));



    }
/**
 * A play gombbon play és pause felirat megváltoztatása.
 */
    private void playGombstate() {
        if (playstate == PLAY) {
            btnPlay.setText("PLAY");
            lejatszo.pause();
            playstate = PAUSE;
        } else {
            btnPlay.setText("PAUSE");
            lejatszo.resume();
            playstate = PLAY;
        }
    }
/**
 * Beállítja az aktuális kérdést és válaszokat.
 * @param i 
 */
    public void panelBeallit(int i) {
        rbtnValasz1.setVisible(false);
        rbtnValasz2.setVisible(false);
        rbtnValasz3.setVisible(false);
        rbtnValasz4.setVisible(false);
        rbtnValasz5.setVisible(false);
        rbtnValasz6.setVisible(false);
        rbtnValasz1.setSelected(true);
        

        if (lejatszo != null) {
            lejatszo.close();
        }

        lblKerdes.setText(kerdesek.get(i).getKerdesSz());


        if (kerdesek.get(i).getValaszok().size() >= 1) {
            rbtnValasz1.setVisible(true);


            switch (kerdesek.get(i).getKerdes()) {
                case ELOADO:
                    rbtnValasz1.setText(kerdesek.get(i).getValaszok().get(0).getEloado());

                    break;
                case CIM:
                    rbtnValasz1.setText(kerdesek.get(i).getValaszok().get(0).getCim());

                    break;
                case MUFAJ:
                    rbtnValasz1.setText(kerdesek.get(i).getValaszok().get(0).getMufaj());

                    break;

            }

        }
        if (kerdesek.get(i).getValaszok().size() >= 2) {

            rbtnValasz2.setVisible(true);

            switch (kerdesek.get(i).getKerdes()) {
                case ELOADO:

                    rbtnValasz2.setText(kerdesek.get(i).getValaszok().get(1).getEloado());
                    break;
                case CIM:

                    rbtnValasz2.setText(kerdesek.get(i).getValaszok().get(1).getCim());
                    break;
                case MUFAJ:

                    rbtnValasz2.setText(kerdesek.get(i).getValaszok().get(1).getMufaj());
                    break;

            }

        }

        if (kerdesek.get(i).getValaszok().size() >= 3) {
            rbtnValasz3.setVisible(true);


            switch (kerdesek.get(i).getKerdes()) {
                case ELOADO:
                    rbtnValasz3.setText(kerdesek.get(i).getValaszok().get(2).getEloado());

                    break;
                case CIM:
                    rbtnValasz3.setText(kerdesek.get(i).getValaszok().get(2).getCim());

                    break;
                case MUFAJ:
                    rbtnValasz3.setText(kerdesek.get(i).getValaszok().get(2).getMufaj());

                    break;

            }

        }

        if (kerdesek.get(i).getValaszok().size() >= 4) {
            rbtnValasz4.setVisible(true);


            switch (kerdesek.get(i).getKerdes()) {
                case ELOADO:
                    rbtnValasz4.setText(kerdesek.get(i).getValaszok().get(3).getEloado());

                    break;
                case CIM:
                    rbtnValasz4.setText(kerdesek.get(i).getValaszok().get(3).getCim());

                    break;
                case MUFAJ:
                    rbtnValasz4.setText(kerdesek.get(i).getValaszok().get(3).getMufaj());

                    break;

            }
        }
        if (kerdesek.get(i).getValaszok().size() >= 5) {
            rbtnValasz5.setVisible(true);


            switch (kerdesek.get(i).getKerdes()) {
                case ELOADO:
                    rbtnValasz5.setText(kerdesek.get(i).getValaszok().get(4).getEloado());

                    break;
                case CIM:
                    rbtnValasz5.setText(kerdesek.get(i).getValaszok().get(4).getCim());

                    break;
                case MUFAJ:
                    rbtnValasz5.setText(kerdesek.get(i).getValaszok().get(4).getMufaj());

                    break;

            }
        }
        if (kerdesek.get(i).getValaszok().size() == 6) {
            rbtnValasz6.setVisible(true);

            switch (kerdesek.get(i).getKerdes()) {
                case ELOADO:
                    rbtnValasz6.setText(kerdesek.get(i).getValaszok().get(5).getEloado());

                    break;
                case CIM:
                    rbtnValasz6.setText(kerdesek.get(i).getValaszok().get(5).getCim());

                    break;
                case MUFAJ:
                    rbtnValasz6.setText(kerdesek.get(i).getValaszok().get(5).getMufaj());

                    break;

            }
        }
        try {
            lejatszo = new mp3(kerdesek.get(i).getValaszok().get(kerdesek.get(i).getJovalaszIndex()).getElersiUt());
        } catch (Exception ex) {
            Logger.getLogger(jatekPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(kerdesek.get(i).getValaszok().get(kerdesek.get(i).getJovalaszIndex()).getElersiUt());
        try {
            lejatszo.play();
        } catch (JavaLayerException ex) {
            Logger.getLogger(jatekPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        zenekvizframe.pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrp = new javax.swing.ButtonGroup();
        btnVissza = new javax.swing.JButton();
        btnMegjelol = new javax.swing.JButton();
        lblKerdes = new javax.swing.JLabel();
        rbtnValasz1 = new javax.swing.JRadioButton();
        rbtnValasz2 = new javax.swing.JRadioButton();
        rbtnValasz3 = new javax.swing.JRadioButton();
        rbtnValasz4 = new javax.swing.JRadioButton();
        rbtnValasz5 = new javax.swing.JRadioButton();
        rbtnValasz6 = new javax.swing.JRadioButton();
        btnPlay = new javax.swing.JButton();

        btnVissza.setText("Vissza");
        btnVissza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisszaActionPerformed(evt);
            }
        });

        btnMegjelol.setText("Megjelöl");
        btnMegjelol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMegjelolActionPerformed(evt);
            }
        });

        lblKerdes.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblKerdes.setText("jLabel1");

        btnGrp.add(rbtnValasz1);
        rbtnValasz1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rbtnValasz1.setText("jRadioButton1");

        btnGrp.add(rbtnValasz2);
        rbtnValasz2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rbtnValasz2.setText("jRadioButton1");

        btnGrp.add(rbtnValasz3);
        rbtnValasz3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rbtnValasz3.setText("jRadioButton1");

        btnGrp.add(rbtnValasz4);
        rbtnValasz4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rbtnValasz4.setText("jRadioButton1");

        btnGrp.add(rbtnValasz5);
        rbtnValasz5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rbtnValasz5.setText("jRadioButton1");

        btnGrp.add(rbtnValasz6);
        rbtnValasz6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rbtnValasz6.setText("jRadioButton1");

        btnPlay.setText("PAUSE");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnVissza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMegjelol))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblKerdes, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(rbtnValasz1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(rbtnValasz2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(rbtnValasz3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(rbtnValasz4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(rbtnValasz5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(rbtnValasz6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(btnPlay)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblKerdes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(rbtnValasz1)
                .addGap(3, 3, 3)
                .addComponent(rbtnValasz2)
                .addGap(3, 3, 3)
                .addComponent(rbtnValasz3)
                .addGap(3, 3, 3)
                .addComponent(rbtnValasz4)
                .addGap(3, 3, 3)
                .addComponent(rbtnValasz5)
                .addGap(3, 3, 3)
                .addComponent(rbtnValasz6)
                .addGap(18, 18, 18)
                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVissza)
                    .addComponent(btnMegjelol))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
/**
 * Kiválasztott kérdés megjelölésre kerül, és átugrik a következő kérdésre.
 * @param evt 
 */
    private void btnMegjelolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMegjelolActionPerformed
        if (rbtnValasz1.isSelected()) {
            userValasz = rbtnValasz1.getText();
        }
        if (rbtnValasz2.isSelected()) {
            userValasz = rbtnValasz2.getText();
        }
        if (rbtnValasz3.isSelected()) {
            userValasz = rbtnValasz3.getText();
        }
        if (rbtnValasz4.isSelected()) {
            userValasz = rbtnValasz4.getText();
        }
        if (rbtnValasz5.isSelected()) {
            userValasz = rbtnValasz5.getText();
        }
        if (rbtnValasz6.isSelected()) {
            userValasz = rbtnValasz6.getText();
        }

        Osszesites.get(panel - 1).setJovalasz(userValasz);

        if (panel == kerdesek.size()) {
            if (lejatszo != null) {
                lejatszo.close();
            }

            zenekvizframe.remove(this);
            zenekvizframe.add(zenekvizframe.getJatekVegePanel1());
            zenekvizframe.pack();
            zenekvizframe.getJatekVegePanel1().pontoz();
            

        } else {
            panelBeallit(panel);
            panel++;
        }
        




    }//GEN-LAST:event_btnMegjelolActionPerformed

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image kep = new ImageIcon(this.getClass().getResource("/kepek/quiz.jpg")).getImage();
        g.drawImage(kep, 0, 0, szel, mag + 30, this);
    }

    public void setPanel(int panel) {
        this.panel = panel;
    }
    
    

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        playGombstate();
    }//GEN-LAST:event_btnPlayActionPerformed
/**
 * Kitörli a listákat, hogy ne legyen duplikált elem bennük, az újra hívásnál.
 * @param evt 
 */
    private void btnVisszaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisszaActionPerformed
            jatek.getOsszesites().clear();
            jatek.getKerdesek().clear();
            jatek.getAdatbazis().clear();
//            jatek.getFilmek().clear();
            jatek.getKivalasztott().clear();
            
                    zenekvizframe.remove(this);
        zenekvizframe.add(zenekvizframe.getUjJatekPanel());
        zenekvizframe.pack();
        
        lejatszo.close();
    }//GEN-LAST:event_btnVisszaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGrp;
    private javax.swing.JButton btnMegjelol;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnVissza;
    private javax.swing.JLabel lblKerdes;
    private javax.swing.JRadioButton rbtnValasz1;
    private javax.swing.JRadioButton rbtnValasz2;
    private javax.swing.JRadioButton rbtnValasz3;
    private javax.swing.JRadioButton rbtnValasz4;
    private javax.swing.JRadioButton rbtnValasz5;
    private javax.swing.JRadioButton rbtnValasz6;
    // End of variables declaration//GEN-END:variables
}
