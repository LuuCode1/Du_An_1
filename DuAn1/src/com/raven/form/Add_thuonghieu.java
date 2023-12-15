/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import javax.swing.JOptionPane;
import model.Thuonghieu;
import service.thuonghieu_service;

/**
 *
 * @author leduc
 */
public class Add_thuonghieu extends javax.swing.JDialog {

    Thuonghieu brand = new Thuonghieu();
    thuonghieu_service brandService = new thuonghieu_service();

    /**
     * Creates new form Add_thuonghieu
     */
    public Add_thuonghieu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setLocationRelativeTo(null);
        initComponents();
    }

    Thuonghieu setModel() {
        brand.setMaThuongHieu(txt_math.getText());
        brand.setTenThuongHieu(txt_tenth.getText());
        return brand;
    }

    public boolean Validate() {
        if (txt_math.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã thương hiệu không được để trống");
            return false;
        }
        if (txt_tenth.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên thương hiệu không được để trống");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_math = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_tenth = new javax.swing.JTextField();
        tbn_check = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Mã Thương Hiệu");

        txt_math.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mathActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên Thương Hiệu");

        txt_tenth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenthActionPerformed(evt);
            }
        });

        tbn_check.setText("Lưu");
        tbn_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbn_checkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbn_check)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(txt_math, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addComponent(txt_tenth)
                        .addComponent(jLabel2)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_math, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tenth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbn_check)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_mathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mathActionPerformed

    private void txt_tenthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenthActionPerformed

    private void tbn_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbn_checkActionPerformed
        if (Validate()) {
            Thuonghieu brand = setModel();
            if (brandService.insert(brand) > 0) {
                JOptionPane.showMessageDialog(this, "them thanh cong");
            }
        }
    }//GEN-LAST:event_tbn_checkActionPerformed

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
            java.util.logging.Logger.getLogger(Add_thuonghieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_thuonghieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_thuonghieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_thuonghieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_thuonghieu dialog = new Add_thuonghieu(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton tbn_check;
    private javax.swing.JTextField txt_math;
    private javax.swing.JTextField txt_tenth;
    // End of variables declaration//GEN-END:variables
}