/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.login;

import com.raven.main.Main;
import java.awt.Color;
import java.awt.GradientPaint;
import javax.swing.JOptionPane;
import model.Check_user_login;
import model.NguoiDung;
import service.NguoiDung_Service;

/**
 *
 * @author Asus
 */
public class DangNhap extends javax.swing.JFrame {
    
    NguoiDung_Service ndsv = new NguoiDung_Service();

    /**
     * Creates new form DangNhap
     */
    public DangNhap() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        txt_email.setText(null);
        txt_pass.setText(null);
        
    }
    
    void dangnhap() {
        Main main = new Main();
        String email = txt_email.getText().trim();
        String matKhau = txt_pass.getText().trim();
        try {
            
            if (ndsv.dangNhap_email(email) != null) {
                if (ndsv.dangNhap(email, matKhau) != null) {
                    JOptionPane.showMessageDialog(this, "Thanh cong ");
                    NguoiDung nd = ndsv.dangNhap_email(email);
                    Check_user_login.mand = nd.getMaND();
                    Check_user_login.vaitro = nd.getVaiTro();
                    this.dispose();
                    
                    main.setVisible(true);
                    
                } else {
                    JOptionPane.showMessageDialog(this, "mat khau khong dung");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tai khoan khong ton tai");
            }
            
        } catch (Exception e) {
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

        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_pass = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Right1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));
        Left.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("LOGIN");
        Left.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 51, -1, -1));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Email");
        Left.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 139, -1, -1));

        txt_email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_email.setForeground(new java.awt.Color(102, 102, 102));
        Left.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 171, 343, 40));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Password");
        Left.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 229, -1, -1));
        Left.add(txt_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 261, 343, 40));

        btn_login.setBackground(new java.awt.Color(102, 0, 255));
        btn_login.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_login.setForeground(new java.awt.Color(255, 255, 255));
        btn_login.setText("Login");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        Left.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 341, 343, 36));

        jLabel8.setText("Đổi mật khẩu");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        Left.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 313, -1, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Pictogrammers-Material-Logout-variant.24.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        Left.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 80, 80));

        getContentPane().add(Left, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 400, 500));

        Right1.setBackground(new java.awt.Color(102, 51, 255));
        Right1.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel16.setFont(new java.awt.Font("Showcard Gothic", 1, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Six Store");

        jLabel17.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("copyright © company name All rights reserved");

        javax.swing.GroupLayout Right1Layout = new javax.swing.GroupLayout(Right1);
        Right1.setLayout(Right1Layout);
        Right1Layout.setHorizontalGroup(
            Right1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Right1Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Right1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(79, 79, 79))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Right1Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(54, 54, 54))
        );
        Right1Layout.setVerticalGroup(
            Right1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Right1Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(66, 66, 66))
        );

        getContentPane().add(Right1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        if (txt_email.getText().equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập tài khoản");
        } else {
            dangnhap();
        }
    }//GEN-LAST:event_btn_loginActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        DoiMatKHau dmk = new DoiMatKHau();
        this.dispose();
        dmk.setVisible(true);
        dmk.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Main main = new Main();
        this.dispose();
        main.setVisible(true);

    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JPanel Right1;
    private javax.swing.JButton btn_login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txt_email;
    private javax.swing.JPasswordField txt_pass;
    // End of variables declaration//GEN-END:variables
}
