/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package feluletek;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import osztalyok.Jatek;
import osztalyok.collection_tipus.Osszesit;
import osztalyok.collection_tipus.Zene;
import osztalyok.modellek.CustomTableCellRenderer;
import osztalyok.modellek.osszesitTablaModell;

/**
 *
 * @author Krisztián
 */
public class jatekVegePanel extends javax.swing.JPanel {

    private zeneKvizFrame zenekvizframe;
    private Jatek jatek = new Jatek();
    private List<Osszesit> osszesites = jatek.getOsszesites();
    private int jovalaszokCount = 0;
    private osszesitTablaModell tmodel = new osszesitTablaModell(osszesites);
    private int szazalek;
    private int szel = 400, mag = 400;
    private int meret = 1;
    private int pontok;
    private Connection kapcsolat;
    private String sql;
    private java.sql.Statement utasitasObjektum;
    private PreparedStatement pst;
        private ResultSet eredmenyHalmaz;

    public jatekVegePanel() {
        this.setSize(szel, mag);
        initComponents();
        tabla.setModel(tmodel);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

    }
/**
 * Táblázat kitöltése, eredmény kiszámítása, majd adatbázis kapcsolat megnyitása.
 */
    public void pontoz() {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(1);
        meret = osszesites.size();
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());
        }

        DecimalFormat df = new DecimalFormat("#.##");
        lblEredmeny.setText("Az ön jó válaszainak aránya: " + df.format(jatek.getEredmeny()) + "%");

        pontok = (int) (osszesites.size() * jatek.getEredmeny());
        lblPont.setText(pontok + " pont");

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image kep = new ImageIcon(this.getClass().getResource("/kepek/vege.jpg")).getImage();
        g.drawImage(kep, 0, 0, szel, mag + 30, this);
        Image szoveg = new ImageIcon(this.getClass().getResource("/kepek/jatekvege.gif")).getImage();
        g.drawImage(szoveg, 55, 20, 300, 150, this);


    }
/**
 * A zenekvizframe példányának megkapása.
 * @param zenekvizframe 
 */
    public void setZenekvizframe(zeneKvizFrame zenekvizframe) {
        this.zenekvizframe = zenekvizframe;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new osztalyok.modellek.SajatGomb();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        lblEredmeny = new javax.swing.JLabel();
        lblPont = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(400, 400));

        btnOk.setFont(new java.awt.Font("Vijaya", 1, 18)); // NOI18N
        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabla);

        lblEredmeny.setFont(new java.awt.Font("Vijaya", 1, 18)); // NOI18N
        lblEredmeny.setText("jLabel2");

        lblPont.setFont(new java.awt.Font("Vijaya", 1, 18)); // NOI18N
        lblPont.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(btnOk)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEredmeny)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPont)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEredmeny)
                    .addComponent(lblPont))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnOk)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
/**
 * Adatbázis kapcsolat megnyitása, eredmények a rekordok táblába mentése.
 * @throws Exception 
 */
    private void adatbazisKapcsolat() throws Exception {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String url = "jdbc:derby:zeneDB;create=true;";
        kapcsolat = DriverManager.getConnection(url);

        try {
            sql = "CREATE TABLE APP.rekordok ( nev varchar(32), pont int)";
            utasitasObjektum = kapcsolat.createStatement();
            utasitasObjektum.execute(sql);
        } catch (SQLException e) {
            System.out.println("A tábla rekordok már létezik!");
        }

        System.out.println(tmodel.getRowCount());
        sql = "INSERT INTO APP.rekordok(nev,pont) VALUES (?,?)";
        pst = kapcsolat.prepareStatement(sql);
        pst.setString(1, jatek.getFelhasznaloNev());
        pst.setInt(2, pontok);
        pst.executeUpdate();
        pst.close();
        
                utasitasObjektum = kapcsolat.createStatement();
        eredmenyHalmaz = utasitasObjektum.executeQuery("SELECT * FROM rekordok");


        while (eredmenyHalmaz.next()) {
            System.out.println("név:"+eredmenyHalmaz.getString("nev")+" Pont:"+eredmenyHalmaz.getInt("pont"));


        }
        
        
        utasitasObjektum.close();
        kapcsolat.close();

    }
/**
 * Főmenüpanel megnyitása, az eddig generált adatok kitörlése.
 * @param evt 
 */
    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        try {
            adatbazisKapcsolat();
        } catch (Exception ex) {
            Logger.getLogger(jatekVegePanel.class.getName()).log(Level.SEVERE, null, ex);
        }


        zenekvizframe.remove(this);
        zenekvizframe.add(zenekvizframe.getFoMenuPanel1());
        zenekvizframe.pack();
        jatek.getOsszesites().clear();
        jatek.getKerdesek().clear();
        jatek.getAdatbazis().clear();
        jatek.getKivalasztott().clear();
    }//GEN-LAST:event_btnOkActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEredmeny;
    private javax.swing.JLabel lblPont;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
