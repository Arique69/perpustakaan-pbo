/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buat.gui;

/**
 *
 * @author ASUS
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NewRegisrasi extends javax.swing.JFrame {

    /**
     * Creates new form NewRegisrasi
     */
    public NewRegisrasi() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mukak = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pANEL = new javax.swing.JPanel();
        NAMA_LENGKAP = new javax.swing.JLabel();
        jText_nama_lengkap = new javax.swing.JTextField();
        newEmail = new javax.swing.JLabel();
        jText_email = new javax.swing.JTextField();
        newpassword = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        TGLLAHIR = new javax.swing.JLabel();
        TGLLAHIR1 = new javax.swing.JLabel();
        buatakun = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jText_alamat = new javax.swing.JTextArea();
        Kliklogin = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        mukak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buat/gui/picture-bangun-tidur.png"))); // NOI18N
        mukak.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Regisrasi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(mukak, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 185, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mukak, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pANEL.setBackground(new java.awt.Color(153, 153, 153));
        pANEL.setForeground(new java.awt.Color(153, 153, 153));

        NAMA_LENGKAP.setBackground(new java.awt.Color(255, 255, 255));
        NAMA_LENGKAP.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        NAMA_LENGKAP.setForeground(new java.awt.Color(255, 255, 255));
        NAMA_LENGKAP.setText("NAMA LENGKAP");

        jText_nama_lengkap.setBackground(new java.awt.Color(204, 204, 204));
        jText_nama_lengkap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_nama_lengkapActionPerformed(evt);
            }
        });

        newEmail.setBackground(new java.awt.Color(255, 255, 255));
        newEmail.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        newEmail.setForeground(new java.awt.Color(255, 255, 255));
        newEmail.setText("E-MAIL");

        jText_email.setBackground(new java.awt.Color(204, 204, 204));

        newpassword.setBackground(new java.awt.Color(255, 255, 255));
        newpassword.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        newpassword.setForeground(new java.awt.Color(255, 255, 255));
        newpassword.setText("PASSWORD");

        jPasswordField1.setBackground(new java.awt.Color(204, 204, 204));

        TGLLAHIR.setBackground(new java.awt.Color(255, 255, 255));
        TGLLAHIR.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        TGLLAHIR.setForeground(new java.awt.Color(255, 255, 255));
        TGLLAHIR.setText("TANGGAL LAHIR");

        TGLLAHIR1.setBackground(new java.awt.Color(255, 255, 255));
        TGLLAHIR1.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        TGLLAHIR1.setForeground(new java.awt.Color(255, 255, 255));
        TGLLAHIR1.setText("ALAMAT");

        buatakun.setBackground(new java.awt.Color(153, 153, 153));
        buatakun.setText("BUAT AKUN");
        buatakun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buatakunActionPerformed(evt);
            }
        });

        jText_alamat.setColumns(20);
        jText_alamat.setRows(5);
        jScrollPane2.setViewportView(jText_alamat);

        Kliklogin.setBackground(new java.awt.Color(204, 204, 204));
        Kliklogin.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Kliklogin.setForeground(new java.awt.Color(204, 204, 204));
        Kliklogin.setText("klik untuk login");
        Kliklogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Kliklogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KlikloginMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pANELLayout = new javax.swing.GroupLayout(pANEL);
        pANEL.setLayout(pANELLayout);
        pANELLayout.setHorizontalGroup(
            pANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pANELLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TGLLAHIR, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(NAMA_LENGKAP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newEmail)
                    .addComponent(newpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TGLLAHIR1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jText_nama_lengkap)
                    .addComponent(jText_email)
                    .addComponent(jPasswordField1)
                    .addComponent(jScrollPane2)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pANELLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buatakun, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pANELLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Kliklogin)
                .addGap(173, 173, 173))
        );
        pANELLayout.setVerticalGroup(
            pANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pANELLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(pANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NAMA_LENGKAP, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jText_nama_lengkap))
                .addGap(18, 18, 18)
                .addGroup(pANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jText_email))
                .addGap(18, 18, 18)
                .addGroup(pANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newpassword, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jPasswordField1))
                .addGap(18, 18, 18)
                .addGroup(pANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TGLLAHIR, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addGroup(pANELLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pANELLayout.createSequentialGroup()
                        .addComponent(TGLLAHIR1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addGap(361, 361, 361))
                    .addGroup(pANELLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(buatakun, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Kliklogin)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pANEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jText_nama_lengkapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_nama_lengkapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_nama_lengkapActionPerformed

    private void buatakunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buatakunActionPerformed
        String name = jText_nama_lengkap.getText();
        String mail = jText_email.getText();
        String pass = String.valueOf(jPasswordField1.getPassword());
        String bdate = null;
        String address = jText_alamat.getText();
        
        if(name.equals("")){
            JOptionPane.showMessageDialog(null,"Masukkan Nama Lengkap");
        }
        else if(mail.equals("")){
            JOptionPane.showMessageDialog(null,"Masukkan Email");
        }
        else if(cekemail(mail)){
            JOptionPane.showMessageDialog(null, "Email Sudah digunakan");
        }  
        else if(pass.equals("")){
            JOptionPane.showMessageDialog(null,"Masukkan Password");
        }
   
        
        else{
            
            if(jDateChooser1.getDate() != null){
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                bdate = dateformat.format(jDateChooser1.getDate());
        }
            
        PreparedStatement st;
        String query = "INSERT INTO `register_user`(`nama_lengkap`, `new_email`, `new_password`, `tgl_lahir`, `new_address`) VALUES (?,?,?,?,?)";
        
        try {
            st= NewConnection.getConnection().prepareStatement(query);
            st.setString(1, name);
            st.setString(2, mail);
            st.setString(3, pass);
            
            if(bdate != null){
                st.setString(4, bdate);
            }else{
                st.setNull(4, 0);
            }
            
            st.setString(5, address);
            
            if (st.executeUpdate()>0)
            {
                JOptionPane.showMessageDialog(null,"Akun berhasil dibuat");
                GUI gui = new GUI();
                gui.setVisible(true);
                gui.pack();
                gui.setLocationRelativeTo(null);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NewRegisrasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }//GEN-LAST:event_buatakunActionPerformed
    
    public boolean  cekemail(String mail){
        PreparedStatement ps;
        ResultSet rs;
        boolean cekmail = false;
        
        String query = "SELECT * FROM `register_user` WHERE `new_email`=?";
        
        try {
            ps = NewConnection.getConnection().prepareStatement(query);
            ps.setString(1, mail);
            rs = ps.executeQuery();
            
            if(rs.next()){
                cekmail = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewRegisrasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cekmail;
    }
    
    
    private void KlikloginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KlikloginMouseClicked
        GUI lgn = new GUI();
        lgn.setVisible(true);
        lgn.pack();
        lgn.setLocationRelativeTo(null);
        lgn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose(); 
    }//GEN-LAST:event_KlikloginMouseClicked

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
            java.util.logging.Logger.getLogger(NewRegisrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewRegisrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewRegisrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewRegisrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewRegisrasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Kliklogin;
    private javax.swing.JLabel NAMA_LENGKAP;
    private javax.swing.JLabel TGLLAHIR;
    private javax.swing.JLabel TGLLAHIR1;
    private javax.swing.JButton buatakun;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jText_alamat;
    private javax.swing.JTextField jText_email;
    private javax.swing.JTextField jText_nama_lengkap;
    private javax.swing.JLabel mukak;
    private javax.swing.JLabel newEmail;
    private javax.swing.JLabel newpassword;
    private javax.swing.JPanel pANEL;
    // End of variables declaration//GEN-END:variables
}
