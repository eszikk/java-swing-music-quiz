/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package feluletek;

import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import osztalyok.modellek.rekordTablaModell;

/**
 *
 * @author Krisztián
 */
public class rekordPanel extends javax.swing.JPanel {

private int szel=400,mag=400;
    private zeneKvizFrame zenekvizframe;
    private rekordTablaModell tmodel=new rekordTablaModell();
    
        private Connection kapcsolat;
    private String sql;
    private java.sql.Statement utasitasObjektum;
    private PreparedStatement pst;
        private ResultSet eredmenyHalmaz;

    public rekordPanel() {
        initComponents();
        this.setSize(szel, mag);
        tabla.setModel(tmodel);
    }

    public void setZenekvizframe(zeneKvizFrame zenekvizframe) {
        this.zenekvizframe = zenekvizframe;
    }
   /**
    * Adatbázis kapcsolat megnyitása, rekordok kimentése a táblából majd táblázatba töltése.
    * @throws Exception 
    */ 
    public void setTabla() throws Exception{
        int pont; String nev;
        
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

                utasitasObjektum = kapcsolat.createStatement();
        eredmenyHalmaz = utasitasObjektum.executeQuery("SELECT * FROM rekordok");


        while (eredmenyHalmaz.next()) {
            nev=eredmenyHalmaz.getString("nev");
                   pont=eredmenyHalmaz.getInt("pont");
            
           tmodel.addElement(nev, pont);


        }
        
        
        utasitasObjektum.close();
        kapcsolat.close();

    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image kep = new ImageIcon(this.getClass().getResource("/kepek/rekordok.jpg")).getImage();
        g.drawImage(kep, 0, 0, szel, mag, this);
        Image szoveg = new ImageIcon(this.getClass().getResource("/kepek/rekordok.gif")).getImage();
        g.drawImage(szoveg, 55, 20, 300, 100, this);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnVissza = new osztalyok.modellek.SajatGomb();

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
        jScrollPane1.setViewportView(tabla);

        btnVissza.setFont(new java.awt.Font("Vijaya", 1, 18)); // NOI18N
        btnVissza.setText("Vissza");
        btnVissza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisszaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVissza)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btnVissza)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
/**
 * Vissza a főmenübe.
 * @param evt 
 */
    private void btnVisszaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisszaActionPerformed
                            zenekvizframe.remove(this);
        zenekvizframe.add(zenekvizframe.getFoMenuPanel1());
        zenekvizframe.pack();
        tmodel.Clear();
    }//GEN-LAST:event_btnVisszaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVissza;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
