/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package feluletek;

import java.awt.Graphics;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import osztalyok.Jatek;
import osztalyok.collection_tipus.Zene;

/**
 *
 * @author Krisztián
 */
public class ujJatekPanel extends javax.swing.JPanel {

    private static final int ELOADO = 1;
    private static final int CIM = 2;
    private static final int MUFAJ = 3;
    private static final int VEGYES = 5;
    private zeneKvizFrame zenekvizframe;
    private int szel = 400, mag = 400;
    private java.sql.Statement utasitasObjektum;
    private ResultSet eredmenyHalmaz;
    private Connection kapcsolat;
    private List<Zene> zenek = new ArrayList<>();
    private int kerdestipus;
    private boolean kerdesmutat = false;
    private String sql;
    private PreparedStatement pst;


    public ujJatekPanel() {
        initComponents();
        this.setSize(szel, mag);
        komponensIncializal();
    }

    private void komponensIncializal() {
        rbtnEloado.setSelected(true);




    }
/**
 * Rádió gombok értékeinek beállítása.
 */
    private void radioGombok() {
        if (rbtnEloado.isSelected()) {
            kerdestipus = ELOADO;
        }
        if (rbtnCim.isSelected()) {
            kerdestipus = CIM;
        }
        if (rbtnMufaj.isSelected()) {
            kerdestipus = MUFAJ;
        }

        if (rbtnVegyes.isSelected()) {
            kerdestipus = VEGYES;
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
                super.paintComponent(g);
        Image kep = new ImageIcon(this.getClass().getResource("/kepek/kotta.jpg")).getImage();
        g.drawImage(kep, 0, 0, szel, mag, this);
    }
    
    

    public void setZenekvizframe(zeneKvizFrame zenekvizframe) {
        this.zenekvizframe = zenekvizframe;
    }
/**
 * Adatbázis kapcoslat megnyitása, ha nem létezik az adatbázis létrheozza, 
 * ha a tábla nem létezik létrehozza.
 * Adatok betöltése a zenek listába.
 * @throws Exception 
 */
    public void adatbazisKapcsolat() throws Exception {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String url = "jdbc:derby:zeneDB;creat=true";
        kapcsolat = DriverManager.getConnection(url);

        try {
            sql = "CREATE TABLE APP.zenek (id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), eloado varchar(32), cim varchar(32), mufaj varchar(32), eleres varchar(200),CONSTRAINT primary_key PRIMARY KEY (id))";
            utasitasObjektum = kapcsolat.createStatement();
            utasitasObjektum.execute(sql);
        } catch (SQLException e) {
            System.out.println("létezik");
        }

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

        String myCurrentDir = System.getProperty("user.dir") + "/zenek/";
        String nev;
        String url1;

        for (Zene e : zenek) {
            nev = new File(e.getElersiUt()).getName();
            url1 = new File(myCurrentDir + nev).getPath();
            

            sql = "UPDATE zenek SET eleres=? WHERE id=?";
            pst = kapcsolat.prepareStatement(sql);

            pst.setString(1, url1);
            pst.setInt(2, e.getId());
            pst.executeUpdate();
            pst.close();

        }



        try {
            utasitasObjektum.close();
            eredmenyHalmaz.close();
            kapcsolat.close();
        } catch (SQLException ex) {
            Logger.getLogger(ZenekKezelesePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrpKerdes = new javax.swing.ButtonGroup();
        btngrpIdo = new javax.swing.ButtonGroup();
        btngrpValasz = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtNev = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rbtnEloado = new javax.swing.JRadioButton();
        rbtnCim = new javax.swing.JRadioButton();
        rbtnMufaj = new javax.swing.JRadioButton();
        rbtnVegyes = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnJatek = new osztalyok.modellek.SajatGomb();
        btnVissza = new osztalyok.modellek.SajatGomb();
        txtKerdes = new javax.swing.JTextField();
        txtValasz = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(400, 400));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setName(""); // NOI18N

        jLabel1.setText("Játékos neve:");

        jLabel2.setText("Kérdések száma:");

        jLabel3.setText("Oldalankénti válaszok száma:");

        jLabel4.setText("Kérdések típusai:");

        btngrpKerdes.add(rbtnEloado);
        rbtnEloado.setText("Ki az előadó?");

        btngrpKerdes.add(rbtnCim);
        rbtnCim.setText("Mi a címe?");

        btngrpKerdes.add(rbtnMufaj);
        rbtnMufaj.setText("Mi a műfaja?");

        btngrpKerdes.add(rbtnVegyes);
        rbtnVegyes.setText("Vegyes kérdések.");

        btnJatek.setFont(new java.awt.Font("Vijaya", 1, 18)); // NOI18N
        btnJatek.setText("JÁTÉK!");
        btnJatek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJatekActionPerformed(evt);
            }
        });

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
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbtnEloado)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(117, 117, 117)
                                        .addComponent(rbtnCim))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(rbtnMufaj)
                                        .addGap(26, 26, 26)
                                        .addComponent(rbtnVegyes))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNev, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtKerdes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtValasz, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 46, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnVissza)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnJatek)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKerdes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtValasz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnEloado)
                    .addComponent(rbtnCim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnMufaj)
                    .addComponent(rbtnVegyes))
                .addGap(154, 154, 154)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVissza)
                    .addComponent(btnJatek))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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




    }//GEN-LAST:event_btnVisszaActionPerformed
/**
 * Játék konstruktorának meghívása adatokkal. 
 * JatekPanel nek átadja a kezdő kérdés számát majd meg is növeli 1 el.
 * @param nev
 * @param kerdes
 * @param valasz
 * @param kerdestipus
 * @param mutat
 * @param zenek 
 */
    private void Jatek(String nev, int kerdes, int valasz, int kerdestipus, boolean mutat, List<Zene> zenek) {
        zenekvizframe.remove(this);
        zenekvizframe.add(zenekvizframe.getJatekPanel1());
        zenekvizframe.pack();

        Jatek jatek = new Jatek(nev, kerdes, valasz, kerdestipus, zenek);
        jatek.kerdesGeneral();
        jatek.KerdesKiir();
        zenekvizframe.getJatekPanel1().panelBeallit(0);
        zenekvizframe.getJatekPanel1().setPanel(1);
    }
/**
 * Jatek metódus meghívása. Kivételek, hibalehetőségek lekezelése.
 * @param evt 
 */
    private void btnJatekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJatekActionPerformed
        try {
            adatbazisKapcsolat();
        } catch (Exception ex) {
            Logger.getLogger(ujJatekPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean ures=false;
        int kerdes=0;
        int valasz=0;
        System.out.println("A ZENE LISTA MÉRETE:"+zenek.size());


        try {
            kerdes = Integer.parseInt(txtKerdes.getText());
            valasz = Integer.parseInt(txtValasz.getText());
        } catch (Exception e) 
        {ures=true;
        }
        
        if(!ures){
        if(valasz>=2 && valasz<=6 && kerdes>=5 && kerdes<=30){
        if (zenek.size() > valasz) {
            radioGombok();
            System.out.println("Kérdés típus" + kerdestipus);

                Jatek(txtNev.getText(), kerdes, valasz, kerdestipus, kerdesmutat, zenek);
                

        } else {
            JOptionPane.showMessageDialog(this, "Túl kevés adat van az adatbázisban!", "Hiba!", 0);
        }}else{JOptionPane.showMessageDialog(this, "A válaszok számát 2 és 6 közé lehet beállítani!\n A kérdések száma 5 és 30 között lehet!", "Hiba!", 0);}
        zenek.clear();
        }else{
            JOptionPane.showMessageDialog(this, "Üres mezők!", "Hiba!", 0);
        }
        zenek.clear();
    }//GEN-LAST:event_btnJatekActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJatek;
    private javax.swing.JButton btnVissza;
    private javax.swing.ButtonGroup btngrpIdo;
    private javax.swing.ButtonGroup btngrpKerdes;
    private javax.swing.ButtonGroup btngrpValasz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rbtnCim;
    private javax.swing.JRadioButton rbtnEloado;
    private javax.swing.JRadioButton rbtnMufaj;
    private javax.swing.JRadioButton rbtnVegyes;
    private javax.swing.JTextField txtKerdes;
    private javax.swing.JTextField txtNev;
    private javax.swing.JTextField txtValasz;
    // End of variables declaration//GEN-END:variables
}
