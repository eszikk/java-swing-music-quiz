/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package feluletek;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;
import osztalyok.collection_tipus.Zene;
import osztalyok.modellek.dbTablaModell;

/**
 *
 * @author Krisztián
 */
public class ZenekKezelesePanel extends javax.swing.JPanel {

    private int szel = 400, mag = 400;
    private zeneKvizFrame zenekvizframe;
    private Connection kapcsolat;
    private java.sql.Statement utasitasObjektum;
    private ResultSet eredmenyHalmaz;
    private List<Zene> zenek = new ArrayList<>();
    private dbTablaModell tmodel = new dbTablaModell(zenek);
    private String sql;
    private PreparedStatement pst;
    
    
/**
 * Panelen lévő komponensek kezdeti értékének beállítása.
 * Tábla model beállítása.
 * Tábla kiválasztási típus beállítása.
 */
    public void komponensIncializal() {
        rbtnHozzaAd.setSelected(true);
        txtEleresiUt.setEditable(false);

        tabla.setModel(tmodel);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    }
    
/**
 * Adatbáziskapcsolat megnyitása.
 * Ha nincs DB, akkor automatikusan létrehoz eggyet.
 * Ha nincs tábla akkor automatikusan létrehoz eggyet a  megadott mezőkkel.
 * @throws Exception 
 */
    public void adatbazsiKapcsolat() throws Exception {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String url = "jdbc:derby:zeneDB;create=true;";
        kapcsolat = DriverManager.getConnection(url);

        try {
            sql = "CREATE TABLE APP.zenek (id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), eloado varchar(32), cim varchar(32), mufaj varchar(32), eleres varchar(200),CONSTRAINT primary_key PRIMARY KEY (id))";
            utasitasObjektum = kapcsolat.createStatement();
            utasitasObjektum.execute(sql);
        } catch (SQLException e) {
            System.out.println("A tábla már létezik!");
        }
        listaFrissit();
    }

/**
 * Lekéri az adatbázis összes elemét, listába tölti. (Ez a lista jelenik meg táblázatként)
 * Befrissíti a fájlok elérési útját a program aktuális helyével, 
 * így nem romlanak le az elérési utak az adatbázisban.
 * @throws Exception 
 */
    private void listaFrissit() throws Exception {

        

        
        
        utasitasObjektum = kapcsolat.createStatement();
        eredmenyHalmaz = utasitasObjektum.executeQuery("SELECT * FROM zenek");

        int id;
        String eloado, cim, mufaj, eleres;

        while (eredmenyHalmaz.next()) {


            id = eredmenyHalmaz.getInt("id");
            eloado = eredmenyHalmaz.getString("eloado");
            cim = eredmenyHalmaz.getString("cim");
            mufaj = eredmenyHalmaz.getString("mufaj");
            eleres = eredmenyHalmaz.getString("eleres");

            zenek.add(new Zene(id, eloado, cim, mufaj, eleres));
        }
        
        String myCurrentDir = System.getProperty("user.dir")+"/zenek/";
        String nev;
        String url;
        
        for(Zene e: zenek){
            nev=new File(e.getElersiUt()).getName();
            url=new File(myCurrentDir+nev).getPath();
            System.out.println(url);
            
                    sql = "UPDATE zenek SET eleres=? WHERE id=?";
                    pst = kapcsolat.prepareStatement(sql);

                    pst.setString(1, url);
                    pst.setInt(2, e.getId());
                    pst.executeUpdate();
                    pst.close();
            
        }
    }

    public ZenekKezelesePanel() {
        this.setSize(szel, mag);
        initComponents();
        komponensIncializal();

    }
/**
 * Zenekvizframe átadása.
 * @param zenekvizframe 
 */
    public void setZenekvizframe(zeneKvizFrame zenekvizframe) {
        this.zenekvizframe = zenekvizframe;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpVezerlo = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtEloado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCim = new javax.swing.JTextField();
        txtMufaj = new javax.swing.JTextField();
        txtEleresiUt = new javax.swing.JTextField();
        btnTallozas = new javax.swing.JButton();
        btnVissza = new javax.swing.JButton();
        btnMuvelet = new javax.swing.JButton();
        btnTorles = new javax.swing.JButton();
        rbtnHozzaAd = new javax.swing.JRadioButton();
        rbtnModosit = new javax.swing.JRadioButton();

        setMaximumSize(new java.awt.Dimension(400, 400));
        setMinimumSize(new java.awt.Dimension(400, 400));

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
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jLabel1.setText("Előadó:");

        jLabel2.setText("Cím:");

        jLabel3.setText("Műfaj:");

        jLabel4.setText("Elérési út:");

        btnTallozas.setText("Tallózás");
        btnTallozas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTallozasActionPerformed(evt);
            }
        });

        btnVissza.setText("Vissza");
        btnVissza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisszaActionPerformed(evt);
            }
        });

        btnMuvelet.setText("Hozzáadás");
        btnMuvelet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuveletActionPerformed(evt);
            }
        });

        btnTorles.setText("Törlés");
        btnTorles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTorlesActionPerformed(evt);
            }
        });

        btnGrpVezerlo.add(rbtnHozzaAd);
        rbtnHozzaAd.setText("Új Hozzáadása");
        rbtnHozzaAd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnHozzaAdActionPerformed(evt);
            }
        });

        btnGrpVezerlo.add(rbtnModosit);
        rbtnModosit.setText("Módosítás");
        rbtnModosit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnModositActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVissza)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMuvelet, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addComponent(btnTorles, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbtnHozzaAd)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                        .addComponent(rbtnModosit))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtEleresiUt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                .addComponent(txtMufaj, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(12, 12, 12)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtCim, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtEloado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTallozas)))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnHozzaAd)
                    .addComponent(rbtnModosit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEloado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMufaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEleresiUt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTallozas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMuvelet)
                    .addComponent(btnTorles))
                .addGap(18, 18, 18)
                .addComponent(btnVissza)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
/**
 * Vissza megy a főmenüben, lezárja az adatbázis kapcsolatokat.
 * @param evt 
 */
    private void btnVisszaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisszaActionPerformed


        zenekvizframe.remove(this);
        zenekvizframe.add(zenekvizframe.getFoMenuPanel1());
        zenekvizframe.pack();
        try {
            utasitasObjektum.close();
            eredmenyHalmaz.close();
            kapcsolat.close();
            if (pst != null) {
                pst.close();


            }
        } catch (SQLException ex) {
            Logger.getLogger(ZenekKezelesePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        tmodel.Clear();

    }//GEN-LAST:event_btnVisszaActionPerformed

    /**
     * Gomb és szöveg mező feliratok megváltoztatása.
     * 
     * @param evt 
     */
    private void rbtnHozzaAdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnHozzaAdActionPerformed
        btnMuvelet.setText("Hozzáadás");
        txtEloado.setText("");
        txtCim.setText("");
        txtMufaj.setText("");
        txtEleresiUt.setText("");
    }//GEN-LAST:event_rbtnHozzaAdActionPerformed
/**
 *  Gomb és szöveg mező feliratok megváltoztatása.
 * @param evt 
 */
    private void rbtnModositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnModositActionPerformed
        btnMuvelet.setText("Módosítás");
    }//GEN-LAST:event_rbtnModositActionPerformed

    private void btnTallozasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTallozasActionPerformed
        String myCurrentDir = System.getProperty("user.dir");
        String  nev=myCurrentDir+"/zenek";
        final File file=new File(nev);

        JFileChooser valaszto = new JFileChooser(file);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Music Files", "mp3");
        valaszto.setFileFilter(filter);

        valaszto.setFileView(new FileView() {
            @Override
            public Boolean isTraversable(File f) {
                return file.equals(f);
            }
        });

        int valasztas = valaszto.showOpenDialog(null);
        if (valasztas == JFileChooser.APPROVE_OPTION) {
            txtEleresiUt.setText(valaszto.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnTallozasActionPerformed

    private void btnMuveletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuveletActionPerformed

        if (!txtEloado.getText().isEmpty() && !txtCim.getText().isEmpty() && !txtEloado.getText().isEmpty() && !txtMufaj.getText().isEmpty()) {
            if (rbtnHozzaAd.isSelected()) {
                tmodel.addElement(
                        txtEloado.getText(),
                        txtCim.getText(),
                        txtMufaj.getText(),
                        txtEleresiUt.getText());

                try {
                    System.out.println(tmodel.getRowCount());
                    sql = "INSERT INTO zenek(eloado,cim,mufaj,eleres) VALUES (?,?,?,?)";
                    pst = kapcsolat.prepareStatement(sql);
                    pst.setString(1, txtEloado.getText());
                    pst.setString(2, txtCim.getText());
                    pst.setString(3, txtMufaj.getText());
                    pst.setString(4, txtEleresiUt.getText());
                    pst.executeUpdate();
                    pst.close();


                } catch (Exception ex) {
                    Logger.getLogger(ZenekKezelesePanel.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rbtnModosit.isSelected()) {



                try {
                    sql = "UPDATE zenek SET eloado=?,cim=?,mufaj=?,eleres=? WHERE id=?";
                    pst = kapcsolat.prepareStatement(sql);

                    pst.setString(1, txtEloado.getText());
                    pst.setString(2, txtCim.getText());
                    pst.setString(3, txtMufaj.getText());
                    pst.setString(4, txtEleresiUt.getText());
                    pst.setInt(5, tmodel.getElementID(tabla.getSelectedRow()));
                    pst.executeUpdate();
                    pst.close();




                } catch (SQLException ex) {
                    Logger.getLogger(ZenekKezelesePanel.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                tmodel.modositElem(tabla.getSelectedRow(), 
                        txtEloado.getText(), 
                        txtCim.getText(), 
                        txtMufaj.getText(), 
                        txtEleresiUt.getText());
            }




            txtEloado.setText("");
            txtCim.setText("");
            txtMufaj.setText("");
            txtEleresiUt.setText("");


        }

    }//GEN-LAST:event_btnMuveletActionPerformed

    private void btnTorlesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTorlesActionPerformed
        if (!zenek.isEmpty() && tabla.getSelectedRowCount() != 0) {

            try {
                sql = "DELETE FROM zenek where id = ?";
                pst = kapcsolat.prepareStatement(sql);
                pst.setInt(1, zenek.get(tabla.getSelectedRow()).getId());
                pst.executeUpdate();
                pst.close();

            } catch (SQLException ex) {
                Logger.getLogger(ZenekKezelesePanel.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            tmodel.removeElement(tabla.getSelectedRow());

        }

    }//GEN-LAST:event_btnTorlesActionPerformed

    private void tablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMousePressed
        int i = tabla.getSelectedRow();
        if (rbtnModosit.isSelected()) {
            txtEloado.setText(zenek.get(i).getEloado());
            txtCim.setText(zenek.get(i).getCim());
            txtMufaj.setText(zenek.get(i).getMufaj());
            txtEleresiUt.setText(zenek.get(i).getElersiUt());


        }


    }//GEN-LAST:event_tablaMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGrpVezerlo;
    private javax.swing.JButton btnMuvelet;
    private javax.swing.JButton btnTallozas;
    private javax.swing.JButton btnTorles;
    private javax.swing.JButton btnVissza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtnHozzaAd;
    private javax.swing.JRadioButton rbtnModosit;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtCim;
    private javax.swing.JTextField txtEleresiUt;
    private javax.swing.JTextField txtEloado;
    private javax.swing.JTextField txtMufaj;
    // End of variables declaration//GEN-END:variables
}
