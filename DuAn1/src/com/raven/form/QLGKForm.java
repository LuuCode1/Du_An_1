/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.QLGK;
import service.QLGK_service;

/**
 *
 * @author Asus
 */
public class QLGKForm extends javax.swing.JPanel {

    DefaultTableModel tblModel = new DefaultTableModel();
    private final QLGK_service qlgks = new QLGK_service();
    private MainForm mainForm;
//    DefaultTableModel tblModel;
//    QLGK qlgk = new QLGK();
//    QLGK_service gksv = new QLGK_service();
//    int index = -1;
    public static String magk;
////    String linkanh = null;

    /**
     * Creates new form QLGKForm
     */
    public QLGKForm(MainForm main) {
        this.mainForm = main;
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

    public void initTable() {
        tblModel = (DefaultTableModel) tbl_thongtin_gk.getModel();
        String[] table = new String[]{
            "STT", "Mã SP", "Tên SP"};
        tblModel.setColumnIdentifiers(table);
    }

//    public void fillTable(List<QLGK> list) {
//        tblModel = (DefaultTableModel) tbl_thongtin_gk.getModel();
//        tblModel.setRowCount(0);
//        for (QLGK qlgk : list) {
//            tblModel.addRow(qlgk.todata());
//        }
//    }
//
//    public QLGK getModel() {
//        QLGK gk = new QLGK();
//        gk.setMaGK(txt_ma_gk.getText());
//        gk.setTenGK(txt_ten_gk.getText());
//        return gk;
//    }
//
//    public void setModel(int index) {
//        QLGK gk = gksv.getALLQLGK().get(index);
//        txt_ma_gk.setText(gk.getMaGK());
//        txt_ten_gk.setText(gk.getTenGK());
//        lbl_id_gk.setText(String.valueOf(gk.getId()));
//    }
//
//    public boolean checkValidate() {
//        if (txt_ma_gk.getText().isEmpty()) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Nhập mã sản phẩm");
//            return false;
//        } else {
//            txt_ma_gk.setText(null);
//        }
//        if (txt_ten_gk.getText().isEmpty()) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Nhập tên sản phẩm");
//            return false;
//        } else {
//            txt_ten_gk.setText(null);
//        }
//        return true;
//    }
//
//    public void Reset() {
//        lbl_id_gk.setText(null);
//        txt_ma_gk.setText(null);
//        txt_ten_gk.setText(null);
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        color1 = new com.raven.form.Color();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_ten_gk = new javax.swing.JTextField();
        txt_ma_gk = new javax.swing.JTextField();
        lbl_id_gk = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_thongtin_gk = new javax.swing.JTable();
        txt_tim_gk = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSPChiTiet = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gọng kính");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("ID:");

        jLabel3.setText("Tên sản phẩm:");

        jLabel4.setText("Mã sản phẩm:");

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
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_id_gk, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_ma_gk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_ten_gk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

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
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(txt_tim_gk, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txt_tim_gk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSPChiTiet.setText("SP chi tiết");
        btnSPChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout color1Layout = new javax.swing.GroupLayout(color1);
        color1.setLayout(color1Layout);
        color1Layout.setHorizontalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(color1Layout.createSequentialGroup()
                .addGroup(color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(color1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSPChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(color1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(color1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        color1Layout.setVerticalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(color1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGroup(color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(color1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(color1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(btnLuu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua)
                        .addGap(8, 8, 8)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSPChiTiet)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            if (txt_ma_gk.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã gọng kính");
                return;
            } else if (txt_ten_gk.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên gọng kính");
                return;
            }
            String ma = txt_ma_gk.getText();
//            if(checkTrung(ma)){
//                JOptionPane.showMessageDialog(this, "Trùng mã");
//                return ;
//            }

            QLGK qlgk = getQLGKFormInput();
            if (qlgks.DeleteQLGK(ma) != null) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                LoadDataToTable(qlgks.getALLQLGK());
            } else {
                JOptionPane.showMessageDialog(this, "Không xóa thành công");
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            if (txt_ma_gk.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã gọng kính");
                return;
            } else if (txt_ten_gk.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên gọng kính");
                return;
            }
//            String ma = txt_ma_gk.getText();
//            if(checkTrung(ma)){
//                JOptionPane.showMessageDialog(this, "Trùng mã");
//                return ;
//            }

            QLGK qlgk = getQLGKFormInput();
            if (qlgks.UpdateQLGK(qlgk) != null) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                LoadDataToTable(qlgks.getALLQLGK());
            } else {
                JOptionPane.showMessageDialog(this, "Không sửa thành công");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        try {
            if (txt_ma_gk.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã gọng kính");
                return;
            } else if (txt_ten_gk.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên gọng kính");
                return;
            }
            String ma = txt_ma_gk.getText();
            if (checkTrung(ma)) {
                JOptionPane.showMessageDialog(this, "Trùng mã");
                return;
            }

            QLGK qlgk = getQLGKFormInput();
            if (qlgks.AddQLGK(qlgk) != null) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                LoadDataToTable(qlgks.getALLQLGK());
            } else {
                JOptionPane.showMessageDialog(this, "Không thêm thành công");
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSPChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPChiTietActionPerformed
        try {
            magk = lbl_id_gk.getText();
            mainForm.showForm(new GongKinhChiTiet(lbl_id_gk.getText()));
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnSPChiTietActionPerformed

    private void tbl_thongtin_gkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_thongtin_gkMouseClicked
        try {
            showDetail();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbl_thongtin_gkMouseClicked

    private void txt_tim_gkFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tim_gkFocusLost
        if (txt_tim_gk.getText().isEmpty()) {
            LoadDataToTable(qlgks.getALLQLGK());
        }
        txt_tim_gk.setText("Tìm kiếm");
        LoadDataToTable(qlgks.getALLQLGK());

    }//GEN-LAST:event_txt_tim_gkFocusLost

    private void txt_tim_gkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tim_gkMousePressed
        txt_tim_gk.setText(null);
    }//GEN-LAST:event_txt_tim_gkMousePressed

    private void txt_tim_gkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tim_gkKeyReleased
        try {
            String text = "%" + txt_tim_gk.getText() + "%";
            if (txt_tim_gk.getText().isEmpty()) {
                LoadDataToTable(qlgks.getALLQLGK());
            } else {
                ArrayList<QLGK> list = qlgks.Search(text);
                LoadDataToTable(list);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_txt_tim_gkKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSPChiTiet;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnXoa;
    private com.raven.form.Color color1;
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

    private void showDetail() {
        int index = tbl_thongtin_gk.getSelectedRow();
        System.out.println(index);
        lbl_id_gk.setText(tblModel.getValueAt(index, 0).toString());
        txt_ma_gk.setText(tblModel.getValueAt(index, 1).toString());
        txt_ten_gk.setText(tblModel.getValueAt(index, 2).toString());
    }

    private QLGK getQLGKFormInput() {
        QLGK qlgk = new QLGK();
        qlgk.setMaGK(txt_ma_gk.getText());
        qlgk.setTenGK(txt_ten_gk.getText());
        return qlgk;
    }

    private boolean checkTrung(String maGongKinh) {
        for (int i = 0; i < tbl_thongtin_gk.getRowCount() - 1; i++) {
            if (tbl_thongtin_gk.getValueAt(i, 1).toString().equalsIgnoreCase(maGongKinh)) {
                return true;
            }
        }
        return false;
    }
}
