/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JOptionPane;
import model.thuonghieu;
import service.chatLieu_service;
import service.thuonghieu_service;

/**
 *
 * @author leduc
 */
public class ThuongHieuForm extends javax.swing.JFrame {
//l
    thuonghieu brand;
    thuonghieu_service brandService = new thuonghieu_service();

    /**
     * Creates new form ThuongHieuForm
     */
    public ThuongHieuForm() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
    }

    thuonghieu setModel() {
        brand.setMaThuongHieu(txt_ma.getText());
        brand.setTenThuongHieu(txt_ten.getText());
        return brand;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbn_check = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_ten = new javax.swing.JTextField();
        txt_ma = new javax.swing.JTextField();
        btn_exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbn_check.setText("Lưu");
        tbn_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbn_checkActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên Thương Hiệu");

        jLabel1.setText("Mã Thương Hiệu");

        txt_ten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenActionPerformed(evt);
            }
        });

        txt_ma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maActionPerformed(evt);
            }
        });

        btn_exit.setText("Thoát");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbn_check)
                        .addGap(18, 18, 18)
                        .addComponent(btn_exit))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(txt_ma, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addComponent(txt_ten)
                        .addComponent(jLabel2)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbn_check)
                    .addComponent(btn_exit))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbn_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbn_checkActionPerformed
        thuonghieu brand = setModel();
        if (brandService.insert(brand) > 0) {
            JOptionPane.showMessageDialog(this, "them thanh cong");
        }
    }//GEN-LAST:event_tbn_checkActionPerformed

    private void txt_tenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenActionPerformed

    private void txt_maActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        this.setVisible(false);
        setDefaultCloseOperation(ChatLieuForm.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btn_exitActionPerformed

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
            java.util.logging.Logger.getLogger(ThuongHieuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThuongHieuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThuongHieuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThuongHieuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThuongHieuForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton tbn_check;
    private javax.swing.JTextField txt_ma;
    private javax.swing.JTextField txt_ten;
    // End of variables declaration//GEN-END:variables
}