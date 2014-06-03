/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package feluletek;

import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author Krisztián
 */
public class zeneKvizFrame extends javax.swing.JFrame {

    private int szel = 400, mag = 400;
    private String title = "ZENE KVÍZ v1.0";
    private JPanel aktualis;
    
    private HelpSet hs;
    private HelpBroker hb;
    private URL hsURL;

    public zeneKvizFrame() {
        singleInstance();
        createDir();

        this.setSize(szel, mag);
        this.setTitle(title);
        this.setResizable(false);
        initComponents();
        btnHelp.setZenekvizframe(this);
        zenekKezelesePanel1.setZenekvizframe(this);
        ujJatekPanel1.setZenekvizframe(this);
        jatekPanel1.setZenekvizframe(this);
        jatekVegePanel1.setZenekvizframe(this);
        rekordPanel1.setZenekvizframe(this);
        //helpMenu.setAction(openHelp());

    }
/**
 * Ha nem létezik a zene könyvtár létrehozza
 */
    private void createDir() {
        String myCurrentDir = System.getProperty("user.dir");

        File directory = new File(myCurrentDir + "/zenek");
        directory.mkdirs();
    }
/**
 * Létrehoz egy szerver socketet a 9999 -es porton, és hallgatózik. 
 * Ha egy újabb példány is létre akarja hozni akkor nem tudja és kivételt kap.
 * A kivétel kezelve van és kilépteti, majd egy üzenetet kap.
 * 
 * Így biztosítottam azt hogy egyszerre csak egy példány fusson.
 * (Tudom, hogy a singleton design pattern elvileg erre való, 
 * de sok egyébb teendőm közt nem jutottam el odáig)
 */
    private void singleInstance() {
        try {
            ServerSocket socket =
                    new ServerSocket(9999, 10, InetAddress.getLocalHost());

        } catch (java.net.BindException b) {
            JOptionPane.showMessageDialog(this, "A program már fut!", "Hiba!", 0);
            System.exit(1);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(1);
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

        zenekKezelesePanel1 = new feluletek.ZenekKezelesePanel();
        ujJatekPanel1 = new feluletek.ujJatekPanel();
        jatekPanel1 = new feluletek.jatekPanel();
        jatekVegePanel1 = new feluletek.jatekVegePanel();
        rekordPanel1 = new feluletek.rekordPanel();
        btnHelp = new feluletek.FoMenuPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(btnHelp, java.awt.BorderLayout.CENTER);

        jMenu2.setText("Help");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu2MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        openHelp();
    }//GEN-LAST:event_jMenu2MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(zeneKvizFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(zeneKvizFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(zeneKvizFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(zeneKvizFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new zeneKvizFrame().setVisible(true);
            }
        });
    }

    /**
     * Panelek lekérése
     *  
     */
    public FoMenuPanel getFoMenuPanel1() {
        return btnHelp;
    }

    public ZenekKezelesePanel getZenekKezelesePanel1() {
        return zenekKezelesePanel1;
    }

    public ujJatekPanel getUjJatekPanel() {
        return ujJatekPanel1;
    }

    public jatekPanel getJatekPanel1() {
        return jatekPanel1;
    }

    public jatekVegePanel getJatekVegePanel1() {
        return jatekVegePanel1;
    }

    public rekordPanel getRekordPanel1() {
        return rekordPanel1;
    }
    
    /**
     *
     */
/**
 * Help fájl megnyitása
 */
 public void openHelp() {
    // Identify the location of the .hs file 
    String pathToHS = "/javahelp/zenekvizhelp/docs/appwithhelp-hs.xml";
    //Create a URL for the location of the help set
    try {
      URL hsURL = this.getClass().getResource(pathToHS);
        hs = new HelpSet(null, hsURL);
    } catch (Exception ee) {
        // Print info to the console if there is an exception
        System.out.println( "HelpSet " + ee.getMessage());
        System.out.println("Help Set "+ pathToHS +" not found");
        return;
    }
  
    // Create a HelpBroker object for manipulating the help set
    hb = hs.createHelpBroker();
    //Display help set
    hb.setDisplayed(true);
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private feluletek.FoMenuPanel btnHelp;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private feluletek.jatekPanel jatekPanel1;
    private feluletek.jatekVegePanel jatekVegePanel1;
    private feluletek.rekordPanel rekordPanel1;
    private feluletek.ujJatekPanel ujJatekPanel1;
    private feluletek.ZenekKezelesePanel zenekKezelesePanel1;
    // End of variables declaration//GEN-END:variables
}
