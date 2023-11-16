/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ThuongHieu1;
import service.THService;

/**
 *
 * @author Dat
 */
public class ThuongHieuu extends javax.swing.JPanel {

    DefaultTableModel tblModel;
    ThuongHieu1 th = new ThuongHieu1();
    private final THService thService = new THService();
    int index = -1;
    public ThuongHieuu() {
        initComponents();
        initTable();
        fillTable(thService.getThuongHieu());
        txtSearch.setText("Tìm Kiếm");
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    public void initTable() {
        tblModel = (DefaultTableModel) tblTHuongHieu.getModel();
        String[] cols = new String[]{
            "Mã thương hiệu", "Tên thương hiệu"};
        tblModel.setColumnIdentifiers(cols);
    }

    public void fillTable(List<ThuongHieu1> list) {
        tblModel = (DefaultTableModel) tblTHuongHieu.getModel();
        tblModel.setRowCount(0);
        Object[] row = new Object[2];
        for (ThuongHieu1 tH : list) {
            row[0] = tH.getMaTH();
            row[1] = tH.getTenTH();
            tblModel.addRow(row);
        }
    }

    public ThuongHieu1 getModel() {
        ThuongHieu1 th = new ThuongHieu1();
        th.setMaTH(txtMaTH.getText());
        th.setTenTH(txtTenTH.getText());
        return th;
    }

    public void setModel(ThuongHieu1 t) {
        txtMaTH.setText(t.getMaTH());
        txtTenTH.setText(t.getTenTH());
    }

    public void Reset() {
        txtMaTH.setText("");
        txtTenTH.setText("");
    }

    public boolean checkValidate() {
        if (txtMaTH.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập mã");
            return false;
        }

        ThuongHieu1 th = this.getModel();
        if (thService.selectByID(th.getMaTH()) != null) {
            JOptionPane.showMessageDialog(this, "Mã thương hiệu đã tồn tại");
            return false;
        }

        if (txtTenTH.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập tên");
            return false;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenTH = new javax.swing.JTextField();
        txtMaTH = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTHuongHieu = new javax.swing.JTable();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thương Hiệu");

        jLabel2.setText("Mã thương hiệu:");

        jLabel3.setText("Tên thương hiệu:");

        tblTHuongHieu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTHuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTHuongHieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTHuongHieu);

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

        btnReset.setText("Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSearchMousePressed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaTH, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                            .addComponent(txtTenTH))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(305, Short.MAX_VALUE)
                        .addComponent(btnLuu)
                        .addGap(29, 29, 29)
                        .addComponent(btnSua)
                        .addGap(28, 28, 28)
                        .addComponent(btnXoa)
                        .addGap(29, 29, 29)
                        .addComponent(btnReset)
                        .addGap(362, 362, 362))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnReset))
                .addGap(39, 39, 39)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblTHuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTHuongHieuMouseClicked
        index = tblTHuongHieu.getSelectedRow();
        if (index >= 0) {
            ThuongHieu1 th = thService.getThuongHieu().get(index);
            setModel(th);
            txtMaTH.setEditable(false);
            btnLuu.setEnabled(false);
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
        }
    }//GEN-LAST:event_tblTHuongHieuMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (checkValidate()) {
            ThuongHieu1 th = getModel();
            if (thService.Save(th) > 0) {
                JOptionPane.showMessageDialog(this, "Thành Công");
                fillTable(thService.getThuongHieu());
                Reset();
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        ThuongHieu1 t = getModel();
        String ma = tblTHuongHieu.getValueAt(index, 0).toString();
        if (thService.Update(t, ma) > 0) {
            JOptionPane.showMessageDialog(this, "Thành Công");
            fillTable(thService.getThuongHieu());
            btnLuu.setEnabled(true);
            txtMaTH.setEnabled(true);
            Reset();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        for (ThuongHieu1 tH : thService.getThuongHieu()) {
            if (tH.getMaTH().equals(txtMaTH.getText())) {
                int choice = JOptionPane.showConfirmDialog(this, "Chắc chắn muốn xóa ?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_NO_OPTION) {
                    thService.Delete(txtMaTH.getText());
                    JOptionPane.showMessageDialog(this, "Đã xóa");
                    fillTable(thService.getThuongHieu());
                    btnLuu.setEnabled(true);
                    txtMaTH.setEnabled(true);
                    Reset();
                    return;
                } else {
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Không tồn tại dữ liệu để xóa");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtMaTH.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(true);
        Reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        if (txtSearch.getText().isEmpty()) {
            txtSearch.setText("Tìm kiếm");
            fillTable(thService.getThuongHieu());
        }
    }//GEN-LAST:event_txtSearchFocusLost

    private void txtSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMousePressed
        txtSearch.setText(null);
    }//GEN-LAST:event_txtSearchMousePressed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            String check = '%' + txtSearch.getText() + '%';
            if (txtSearch.getText().isEmpty()) {
                fillTable(thService.getThuongHieu());

            } else {
                List<ThuongHieu1> listds = thService.Search(check);
                fillTable(listds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTHuongHieu;
    private javax.swing.JTextField txtMaTH;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenTH;
    // End of variables declaration//GEN-END:variables
}
