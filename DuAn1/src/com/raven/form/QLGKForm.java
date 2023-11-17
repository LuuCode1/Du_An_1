/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.form.*;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.QLGK;

import model.QLTK;
import service.QLGKForm_Service;
import service.QLTK_Serivce;

import service.QLGK_Service;


/**
 *
 * @author Lenovo
 */
public class QLGKForm extends javax.swing.JPanel {


    DefaultTableModel tblModel;
    QLGK qlgk = new QLGK();
    QLGKForm_Service gksv = new QLGKForm_Service();
    int index = -1;
//public static String magk;

    public QLGKForm() {
        initComponents();
        initTable();
        setOpaque(false);
        fillTable(gksv.getALLQLGK());
        txt_tim_gk.setText("Tìm kiếm");
    }

    public void initTable() {
        tblModel = (DefaultTableModel) tbl_thongtin_gk.getModel();
        String[] table = new String[]{
            "STT", "Mã SP", "Tên SP"};
        tblModel.setColumnIdentifiers(table);
    }

    public void fillTable(List<QLGK> list) {
        tblModel = (DefaultTableModel) tbl_thongtin_gk.getModel();
        tblModel.setRowCount(0);
        for (QLGK qlgk : list) {
            tblModel.addRow(qlgk.todata());
        }
    }

    public QLGK getModel() {
        QLGK gk = new QLGK();
        gk.setMaGK(txt_ma_gk.getText());
        gk.setTenGK(txt_ten_gk.getText());
        return gk;
    }

    public void setModel(int index) {
        QLGK gk = gksv.getALLQLGK().get(index);
        txt_ma_gk.setText(gk.getMaGK());
        txt_ten_gk.setText(gk.getTenGK());
        lbl_id_gk.setText(String.valueOf(gk.getId()));
    }

    public boolean checkValidate() {
        if (txt_ma_gk.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nhập mã sản phẩm");
            return false;
        } else {
            txt_ma_gk.setText(null);
        }
        if (txt_ten_gk.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nhập tên sản phẩm");
            return false;
        } else {
            txt_ten_gk.setText(null);
        }
        return true;
    }

    public void Reset() {
        lbl_id_gk.setText(null);
        txt_ma_gk.setText(null);
        txt_ten_gk.setText(null);

    DefaultTableModel tblModel = new DefaultTableModel();
    private final QLGK_Service qlgks = new QLGK_Service();
    /**
     * Creates new form QLGKForm
     */
    public QLGKForm() {
        initComponents();
        txt_tim_gk.setText("Tìm kiếm");
        tbl_thongtin_gk.setModel(tblModel);
        String header[] = {
            "ID", "Mã Gọng Kính", "Tên Gọng Kính"
        };
        tblModel.setColumnIdentifiers(header);
        
        tblModel = (DefaultTableModel) tbl_thongtin_gk.getModel();
        LoadDataToTable(qlgks.getALLQLGK());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_ten_gk = new javax.swing.JTextField();
        txt_ma_gk = new javax.swing.JTextField();
        lbl_id_gk = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnSPChiTiet = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_thongtin_gk = new javax.swing.JTable();
        txt_tim_gk = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tròng kính");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("ID:");

        jLabel3.setText("Tên sản phẩm:");

        jLabel4.setText("Mã sản phẩm:");

        lbl_id_gk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnSPChiTiet.setText("SP chi tiết");
        btnSPChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_ten_gk, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(txt_ma_gk)
                    .addComponent(lbl_id_gk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSPChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(80, 80, 80))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lbl_id_gk, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnLuu)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_ma_gk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_ten_gk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnSPChiTiet)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        tbl_thongtin_gk.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_thongtin_gk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_thongtin_gkMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_thongtin_gk);

        txt_tim_gk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_tim_gkFocusLost(evt);
            }
        });
        txt_tim_gk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_tim_gkMousePressed(evt);
            }
        });
        txt_tim_gk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tim_gkKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_tim_gk, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txt_tim_gk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 99, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void tbl_thongtin_gkMouseClicked(java.awt.event.MouseEvent evt) {                                             
        // TODO add your handling code here:
        try {
            showDetail();
        } catch (Exception e) {
            System.out.println(e);
        }
    }                                            

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        try {
            if(txt_ma_gk.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã gọng kính"); 
                return;
            }else if (txt_ten_gk.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên gọng kính"); 
                return;
            }
            String ma = txt_ma_gk.getText();
            if(checkTrung(ma)){
                JOptionPane.showMessageDialog(this, "Trùng mã");
                return ;
            }
            
            QLGK qlgk = getQLGKFormInput();
            if(qlgks.AddQLGK(qlgk) != null){
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                LoadDataToTable(qlgks.getALLQLGK());
            }else {
                JOptionPane.showMessageDialog(this, "Không thêm thành công");
            }
            
        } catch (Exception e) {
        }
    }                                      

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        try {
            if(txt_ma_gk.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã gọng kính"); 
                return;
            }else if (txt_ten_gk.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên gọng kính"); 
                return;
            }
//            String ma = txt_ma_gk.getText();
//            if(checkTrung(ma)){
//                JOptionPane.showMessageDialog(this, "Trùng mã");
//                return ;
//            }
            
            QLGK qlgk = getQLGKFormInput();
            if(qlgks.UpdateQLGK(qlgk) != null){
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                LoadDataToTable(qlgks.getALLQLGK());
            }else {
                JOptionPane.showMessageDialog(this, "Không sửa thành công");
            }
            
        } catch (Exception e) {
        }
    }                                      

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (checkValidate()) {
            String ma = tbl_thongtin_gk.getValueAt(index, 0).toString();
            if (gksv.DeleteQLGK(ma) > 0) {
                JOptionPane.showMessageDialog(this, "thành công");
                fillTable(gksv.getALLQLGK());
            }

            String ma = txt_ma_gk.getText();
//            if(checkTrung(ma)){
//                JOptionPane.showMessageDialog(this, "Trùng mã");
//                return ;
//            }
            
            QLGK qlgk = getQLGKFormInput();
            if(qlgks.DeleteQLGK(ma) != null){
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                LoadDataToTable(qlgks.getALLQLGK());
            }else {
                JOptionPane.showMessageDialog(this, "Không xóa thành công");
            }
            
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (checkValidate()) {
            QLGK gk1 = this.getModel();
            String ma = tbl_thongtin_gk.getValueAt(index, 0).toString();
            if (gksv.UpdateQLGK(gk1, ma) > 0) {
                JOptionPane.showMessageDialog(this, "thành công");
                fillTable(gksv.getALLQLGK());
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (checkValidate()) {
            QLGK gk = this.getModel();
            if (gksv.AddQLGK(gk) > 0) {
                JOptionPane.showMessageDialog(this, "thành công");
                fillTable(gksv.getALLQLGK());
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSPChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPChiTietActionPerformed
//        magk = txt_ma_gk.getText();
//        GKCT gkct = new GKCT(txt_ma_gk.getText());
//        gkct.setVisible(true);
        String maGK = txt_ma_gk.getText();
        GKCT gkct = new GKCT(maGK);
        gkct.setVisible(true);
    }//GEN-LAST:event_btnSPChiTietActionPerformed


    private void tbl_thongtin_gkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_thongtin_gkMouseClicked
        index = tbl_thongtin_gk.getSelectedRow();
        this.setModel(index);
    }//GEN-LAST:event_tbl_thongtin_gkMouseClicked

    private void txt_tim_gkFocusLost(java.awt.event.FocusEvent evt) {                                     
        if (txt_tim_gk.getText().isEmpty()) {
            txt_tim_gk.setText("Tìm kiếm");
            fillTable(gksv.getALLQLGK());
        }
    }                                    

    private void txt_tim_gkMousePressed(java.awt.event.MouseEvent evt) {                                        
        txt_tim_gk.setText(null);
    }                                       

    private void txt_tim_gkKeyReleased(java.awt.event.KeyEvent evt) {                                       
        String text = "%" + txt_tim_gk.getText() + "%";
        if (txt_tim_gk.getText().isEmpty()) {
            fillTable(gksv.getALLQLGK());
        } else {
            List<QLGK> list = gksv.selectByID(text);
            fillTable(list);
        }
    }                                      
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        java.awt.Color lightPurple = new java.awt.Color(177, 156, 217, 80); // Màu tím nhạt với alpha: 80

        // Tạo GradientPaint với màu xanh biển và màu tím nhạt có alpha thấp
        GradientPaint gra = new GradientPaint(0, 0, new java.awt.Color(0, 119, 190), getWidth(), 0, lightPurple);
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    private void txt_tim_gkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tim_gkKeyReleased
        // TODO add your handling code here:
        try {
            String text = "%" + txt_tim_gk.getText() + "%";
            if(txt_tim_gk.getText().isEmpty()){
                LoadDataToTable(qlgks.getALLQLGK());
            }else {
                ArrayList<QLGK> list = qlgks.Search(text);
                LoadDataToTable(list);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txt_tim_gkKeyReleased

    private void txt_tim_gkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tim_gkMousePressed
        // TODO add your handling code here:
        txt_tim_gk.setText(null);
    }//GEN-LAST:event_txt_tim_gkMousePressed

    private void txt_tim_gkFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tim_gkFocusLost
        // TODO add your handling code here:
        if(txt_tim_gk.getText().isEmpty()){
            txt_tim_gk.setText("Tìm kiếm");
            LoadDataToTable(qlgks.getALLQLGK());
        }
    }//GEN-LAST:event_txt_tim_gkFocusLost



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSPChiTiet;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_id_gk;
    private javax.swing.JTable tbl_thongtin_gk;
    private javax.swing.JTextField txt_ma_gk;
    private javax.swing.JTextField txt_ten_gk;
    private javax.swing.JTextField txt_tim_gk;
    // End of variables declaration//GEN-END:variables


    private void LoadDataToTable(ArrayList<QLGK> list) {
        //list = qlgks.getALLQLGK();
        tblModel.setRowCount(0);
        for (QLGK qlgk : list) {
            tblModel.addRow(new Object[]{
                qlgk.getId(),
                qlgk.getMaGK(),
                qlgk.getTenGK()
            });
        }
    }
    
    private void showDetail(){
        int index = tbl_thongtin_gk.getSelectedRow();
        System.out.println(index);
        lbl_id_gk.setText(tblModel.getValueAt(index, 0).toString());
        txt_ma_gk.setText(tblModel.getValueAt(index, 1).toString());
        txt_ten_gk.setText(tblModel.getValueAt(index, 2).toString());
    }
    
    private QLGK getQLGKFormInput(){
        QLGK qlgk = new QLGK();
        qlgk.setMaGK(txt_ma_gk.getText());
        qlgk.setTenGK(txt_ten_gk.getText());
        return qlgk;
    }
    
    private boolean checkTrung(String maGongKinh){
        for (int i = 0; i < tbl_thongtin_gk.getRowCount() - 1; i++) {
            if(tbl_thongtin_gk.getValueAt(i, 1).toString().equalsIgnoreCase(maGongKinh)){
                return true;
            }
        }
        return false;
    }


}
