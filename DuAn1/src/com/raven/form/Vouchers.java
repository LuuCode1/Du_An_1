/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Voucher;
import service.VouchersService;

/**
 *
 * @author leduc
 */
public class Vouchers extends javax.swing.JPanel {

    DefaultTableModel tblModel;
    //   List<VouchersService> kmSer = new ArrayList<>();
    VouchersService kmSer = new VouchersService();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Voucher km = new Voucher();

    /**
     * Creates new form Vouchers
     */
    public Vouchers() {
        initComponents();
//        configTblCol();
        getComboBox();
        getListKhuyenMai(kmSer.getAll());

    }

//    public void configTblCol() {
//        this.tblKhuyenMai.getColumnModel().getColumn(0).setPreferredWidth(50);
//        this.tblKhuyenMai.getColumnModel().getColumn(1).setPreferredWidth(82);
//        this.tblKhuyenMai.getColumnModel().getColumn(2).setPreferredWidth(190);
//        this.tblKhuyenMai.getColumnModel().getColumn(3).setPreferredWidth(100);
//        this.tblKhuyenMai.getColumnModel().getColumn(4).setPreferredWidth(90);
//        this.tblKhuyenMai.getColumnModel().getColumn(5).setPreferredWidth(105);
//        this.tblKhuyenMai.getColumnModel().getColumn(6).setPreferredWidth(105);
//        this.tblKhuyenMai.getColumnModel().getColumn(7).setPreferredWidth(85);
//        this.tblKhuyenMai.getColumnModel().getColumn(8).setPreferredWidth(125);
//    }
    public void getComboBox() {
        String[] str = new String[]{
            "Còn hạn", "Hết hạn"};
        cboTrangThai.setModel(new DefaultComboBoxModel<>(str));
    }

    public void getListKhuyenMai(List<Voucher> list) {
        tblModel = (DefaultTableModel) tblKhuyenMai.getModel();
        tblModel.setRowCount(0);
        int stt = 1;
        for (Voucher x : list) {
            tblModel.addRow(new Object[]{
                stt,
                //                tblKhuyenMai.getRowCount() + 1,
                x.getMaVouchers(),
                x.getTenVouchers(),
                String.format("%.0f", x.getGiatri()),
                x.getSoluong(),
                x.getNgaytao(),
                x.getNgayketthuc(),
                x.getSoluongdadung(),
                x.getTrangthai()
            });
            stt++;
        }
    }

    public Voucher getModel() {
        Voucher k = new Voucher();
//        String date1 = sdf.format(dateBD.getDate());
//        String date2 = sdf.format(dateKT.getDate());
        k.setMaVouchers(txtMaKM.getText());
        k.setTenVouchers(txtTenKM.getText());
//        k.setNgay(dateBD.getDate());
        k.setNgayketthuc(dateKT.getDate());
        k.setSoluong(Integer.parseInt(txtSoLuongMa.getText()));
        k.setGiatri(Float.parseFloat(txtGiaTriGiam.getText()));
        k.setTrangthai((String) cboTrangThai.getSelectedItem());
        return k;
    }

    public void setModel(Voucher k) {
        txtMaKM.setText(k.getMaVouchers());
        txtTenKM.setText(k.getTenVouchers());
        txtSoLuongMa.setText(String.valueOf(k.getSoluong()));

        dateKT.setDate(k.getNgayketthuc());
        txtGiaTriGiam.setText(String.valueOf(k.getGiatri()));
        cboTrangThai.setSelectedItem(k.getTrangthai());
    }

    public void Clear() {
        txtMaKM.setText(null);
        txtTenKM.setText(null);
        txtSoLuongMa.setText(null);
        checkgiatri.setText(null);
        checkma.setText(null);
        checkngayketthuc.setText(null);
        checksoluong.setText(null);
        checkten.setText(null);
        dateKT.setDate(null);
        txtGiaTriGiam.setText(null);
        cboTrangThai.setSelectedItem(0);
    }

    boolean checkValidate() {
        boolean isValid = true;
        String ma = txtMaKM.getText();
        boolean macheck = ma.matches("^[a-zA-Z0-9]+$");
        if (txtSoLuongMa.getText().isEmpty()) {
            checksoluong.setText("Bạn chưa nhập số lượng mã khuyến mãi");
            checksoluong.setForeground(java.awt.Color.RED);
            isValid = false;
        } else {
            checksoluong.setText(null);
        }

        if (txtMaKM.getText().isEmpty()) {
            checkma.setText("Bạn chưa nhập mã khuyến mãi");
            checkma.setForeground(java.awt.Color.RED);
            isValid = false;
        } else if (!macheck) {
            checkma.setText("Mã Không hợp lệ");
            checkma.setForeground(java.awt.Color.RED);
            isValid = false;
        } else {
            checkma.setText(null);
        }
        String input = txtTenKM.getText();
        boolean isJavaClassName = input.matches("[\\p{Punct}&&[^a-zA-Z]]");

        if (txtTenKM.getText().isEmpty()) {
            checkten.setText("Bạn chưa nhập tên khuyến mãi");
            checkten.setForeground(java.awt.Color.RED);
            isValid = false;
        } else if (isJavaClassName) {
            checkten.setText("Tên Không hợp lệ.");
            checkten.setForeground(java.awt.Color.RED);
            isValid = false;
        } else {
            checkten.setText(null);
        }

        Date currentDate = new Date();

        Date ngaythang = dateKT.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        if (ngaythang == null) {
            // Ngày kết thúc null
            checkngayketthuc.setText("Bạn chưa chọn ngày kết thúc");
            checkngayketthuc.setForeground(java.awt.Color.RED);
            isValid = false;

        } else if (ngaythang.before(currentDate)) {
            // Ngày kết thúc nhỏ hơn ngày hiện tại
            checkngayketthuc.setText("Ngày kết thúc phải sau ngày hiện tại");
            checkngayketthuc.setForeground(java.awt.Color.RED);
            isValid = false;

        } else if (sdf.format(ngaythang).isEmpty()) {
            checkngayketthuc.setText("Ngày kết thúc phải sau ngày hiện tại");
            checkngayketthuc.setForeground(java.awt.Color.RED);
            isValid = false;
        } else {
            checkngayketthuc.setText(null);
        }

        try {
            int soLuong = Integer.parseInt(txtSoLuongMa.getText());
            if (soLuong <= 0) {
                checksoluong.setText("Số lượng phải lớn hơn 0");
                isValid = false;
            }
        } catch (NumberFormatException e) {
            checksoluong.setText("Số lượng phải là số");
            checksoluong.setForeground(java.awt.Color.RED);
            isValid = false;
        }

        if (txtGiaTriGiam.getText().isEmpty()) {
            checkgiatri.setText("Bạn chưa nhập giá trị giảm");
            checkgiatri.setForeground(java.awt.Color.RED);
            isValid = false;
        } else {
            try {
                float giaTriGiam = Float.parseFloat(txtGiaTriGiam.getText());
                if (giaTriGiam <= 0) {
                    checkgiatri.setText("Giá trị giảm phải lớn hơn 0");
                    checkgiatri.setForeground(java.awt.Color.RED);
                    isValid = false;
                } else {
                    checkgiatri.setText(null);
                }
            } catch (NumberFormatException e) {
                checkgiatri.setText("Giá trị giảm phải là số");
                checkgiatri.setForeground(java.awt.Color.RED);
                isValid = false;
            }
        }

        return isValid;
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
        jLabel1 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKM = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGiaTriGiam = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuongMa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dateKT = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        checkngayketthuc = new javax.swing.JLabel();
        checkgiatri = new javax.swing.JLabel();
        checkma = new javax.swing.JLabel();
        checkten = new javax.swing.JLabel();
        checksoluong = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        txtTimKM = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnGetAll = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Mã khuyến mãi");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 32, -1, -1));
        jPanel1.add(txtMaKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 60, 275, -1));

        jLabel2.setText("Tên khuyến mãi");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 108, -1, -1));

        txtTenKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKMActionPerformed(evt);
            }
        });
        jPanel1.add(txtTenKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 138, 275, -1));

        jLabel3.setText("Giá trị khuyến mãi");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 32, -1, -1));
        jPanel1.add(txtGiaTriGiam, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 60, 275, -1));

        jLabel5.setText("Số lượng");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 186, -1, -1));
        jPanel1.add(txtSoLuongMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 214, 275, -1));

        jLabel6.setText("Ngày kết thúc");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, -1, -1));

        dateKT.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(dateKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 275, -1));

        jLabel7.setText("Trạng thái");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, -1, -1));

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cboTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, 270, -1));

        btnSave.setText("Thêm");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 71, 132, 35));

        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 137, 132, 34));

        btnNew.setText("Mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 201, 132, 35));
        jPanel1.add(checkngayketthuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, 270, 30));
        jPanel1.add(checkgiatri, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 270, 30));
        jPanel1.add(checkma, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 270, 30));
        jPanel1.add(checkten, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 270, 30));
        jPanel1.add(checksoluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 270, 30));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Khuyến mãi"));

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã KM", "Tên khuyến mãi", "Giá trị giảm", "Số lượng mã", "Ngày bắt đầu", "Ngày kết thúc", "SL đã dùng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMai.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblKhuyenMai.setRowHeight(25);
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhuyenMai);

        txtTimKM.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKMFocusLost(evt);
            }
        });
        txtTimKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTimKMMousePressed(evt);
            }
        });
        txtTimKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKMKeyReleased(evt);
            }
        });

        jLabel11.setText("Tìm kiếm");

        btnGetAll.setText("Get All");
        btnGetAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnGetAll, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGetAll, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txtTimKM))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (checkValidate()) {

            if (kmSer.checkMa(txtMaKM.getText(), txtTenKM.getText()) != null) {
                JOptionPane.showMessageDialog(this, "Mã và tên khuyến mãi đã tồn tại");
                return;
            }

//        if (kmSer.checkTen(txtTenKM.getText()) != null) {
//            JOptionPane.showMessageDialog(this, "Tên khuyến mãi đã tồn tại");
//            return;
//        }
            try {
                if (txtSoLuongMa.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa nhập số lượng mã khuyến mãi");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Voucher km = getModel();

            if (kmSer.Save(km) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công");
                getListKhuyenMai(kmSer.getAll());
                Clear();
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (checkValidate()) {

            Voucher ma = getModel();
            if (kmSer.Update(ma, ma.getMaVouchers()) > 0) {
                JOptionPane.showMessageDialog(this, "Update thành công");
                getListKhuyenMai(kmSer.getAll());
                Clear();
            }
            txtMaKM.setEditable(false);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        Clear();
        txtMaKM.setEditable(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        // TODO add your handling code here:
        int index = tblKhuyenMai.getSelectedRow();
        if (index >= 0) {
            setModel(kmSer.getAll().get(index));
            txtMaKM.setEditable(false);
        }
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void txtTimKMFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKMFocusLost
        // TODO add your handling code here:
        if (txtTimKM.getText().isEmpty()) {
            getListKhuyenMai(kmSer.getAll());
        }
    }//GEN-LAST:event_txtTimKMFocusLost

    private void txtTimKMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKMMousePressed
        // TODO add your handling code here:
        //        txtTimKM.setText(null);
    }//GEN-LAST:event_txtTimKMMousePressed

    private void txtTimKMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKMKeyReleased
        // TODO add your handling code here:
        if (txtTimKM.getText() != null) {
            String text = "%" + txtTimKM.getText() + "%";
            if (kmSer.SearchAll(text) == null) {
                JOptionPane.showMessageDialog(this, "Không có khuyến mãi nào");
                return;
            }
            List<Voucher> listKm = kmSer.SearchAll(text);
            getListKhuyenMai(listKm);
            return;
        }
    }//GEN-LAST:event_txtTimKMKeyReleased

    private void btnGetAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllActionPerformed
        // TODO add your handling code here:

        txtTimKM.setText(null);
        getListKhuyenMai(kmSer.getAll());
    }//GEN-LAST:event_btnGetAllActionPerformed

    private void txtTenKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGetAll;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel checkgiatri;
    private javax.swing.JLabel checkma;
    private javax.swing.JLabel checkngayketthuc;
    private javax.swing.JLabel checksoluong;
    private javax.swing.JLabel checkten;
    private com.toedter.calendar.JDateChooser dateKT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTextField txtGiaTriGiam;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtSoLuongMa;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTimKM;
    // End of variables declaration//GEN-END:variables
}
