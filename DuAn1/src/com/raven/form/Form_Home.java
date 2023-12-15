package com.raven.form;

import com.raven.dialog.Message;
import com.raven.login.DangNhap;
import com.raven.main.Main;
import com.raven.model.ModelCard;
import com.raven.model.ModelStudent;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import com.raven.swing.noticeboard.ModelNoticeBoard;
import com.raven.swing.table.EventAction;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Form_Home extends javax.swing.JPanel {
    DangNhap login = new DangNhap();
    public Form_Home() {
        initComponents();
       
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        color1 = new com.raven.form.Color();
        jLabel2 = new javax.swing.JLabel();

        color1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 100)); // NOI18N
        jLabel2.setText("SIX STORE");
        color1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 500, 171));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.form.Color color1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
