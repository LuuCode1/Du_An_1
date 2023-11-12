/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Giongkinh;
import model.chatLieu;
import model.mausac;
import model.thuonghieu;
import service.GongKinh_Service;
import service.chatLieu_service;
import service.mausac_service;
import service.thuonghieu_service;

/**
 *
 * @author Asus
 */
public class QuanLyGongKinh extends javax.swing.JFrame {

    Giongkinh gk = new Giongkinh();
    GongKinh_Service gksv = new GongKinh_Service();
    int index = -1;
    DefaultTableModel model;
    mausac_service mssv = new mausac_service();
    mausac ms = new mausac();
    chatLieu cl = new chatLieu();
    chatLieu_service clsv = new chatLieu_service();
    thuonghieu th = new thuonghieu();
    thuonghieu_service thsv = new thuonghieu_service();
    String linkAnh = null;

    /**
     * Creates new form QuanLyGongKinh
     */
    public QuanLyGongKinh() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        fillTable(gksv.selectAll());
        CBo_MauSac();
        CBo_ChatLieu();
        CBo_ThuongHieu();
        name();
        txt_seach.setText("Tìm Kiếm");
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);
    }

    void name() {
        String[] table1 = {"Mã Gọng Kính", "Tên Giọng Kính", "Chất Liệu", "Màu Sắc", "Thương Hiệu", "Số Lượng", "Giá Bán", "Hình Ảnh", "Mô Tả"};
        for (int i = 0; i < table1.length; i++) {
            lblbang.getColumnModel().getColumn(i).setHeaderValue(table1[i]);
        }
    }

    void fillTable(List<Giongkinh> list) {
        model = (DefaultTableModel) lblbang.getModel();
        model.setRowCount(0);
        for (Giongkinh giongkinh : list) {
            model.addRow(giongkinh.todata());
        }
    }

    void CBo_MauSac() {
        List<mausac> listmausac = mssv.FILL_TO_CBO_MauSac();
        String[] cbo = new String[listmausac.size()];
        for (int i = 0; i < listmausac.size(); i++) {
            cbo[i] = listmausac.get(i).getTenMauSac();
        }
        CBo_MS.setModel(new DefaultComboBoxModel<>(cbo));

    }

    void CBo_ChatLieu() {
        List<chatLieu> listchatlieu = clsv.FILL_TO_CBO_ChatLieu();
        String[] cbo = new String[listchatlieu.size()];
        for (int i = 0; i < listchatlieu.size(); i++) {
            cbo[i] = listchatlieu.get(i).getTenChatLieu();
        }
        Cbo_chatLieu.setModel(new DefaultComboBoxModel<>(cbo));

    }

    void CBo_ThuongHieu() {
        List<thuonghieu> listthuonghieu = thsv.FILL_TO_CBO_ThuongHieu();
        String[] cbo = new String[listthuonghieu.size()];
        for (int i = 0; i < listthuonghieu.size(); i++) {
            cbo[i] = listthuonghieu.get(i).getTenThuongHieu();

        }
        CBO_TH.setModel(new DefaultComboBoxModel<>(cbo));
        CBO_TH1_check.setModel(new DefaultComboBoxModel<>(cbo));
    }

    void showmodel_gongKing(Giongkinh gk1) {
        Giongkinh gk = gk1;
        txt_maGK.setText(gk.getMaGongKinh());
        txt_tenGK.setText(gk.getTenGongKinh());
        txt_GiaBan.setText(String.valueOf(gk.getGiaThanh()));
        TxtSoLuong.setText(String.valueOf(gk.getSoLuong()));
        Cbo_chatLieu.setSelectedItem(gk.getTenChatLieu().getTenChatLieu());
        CBo_MS.setSelectedItem(gk.getTenMauSac().getTenMauSac());
        CBO_TH.setSelectedItem(gk.getTenThuongHieu().getTenThuongHieu());
        txt_mota.setText(gk.getMoTa());

        try {
            lbl_anh.setIcon(null);
            File file = new File(gk.getHinhAnh());
            Image img = ImageIO.read(file);
            int width = lbl_anh.getWidth();
            int height = lbl_anh.getHeight();
            Image resizedImage = img.getScaledInstance(width, height, 0);
            lbl_anh.setIcon(new ImageIcon(resizedImage));
            linkAnh = file.getAbsolutePath();
        } catch (Exception e) {
        }

    }

    Giongkinh readmodel_gongKinh() {
        gk.setMaGongKinh(txt_maGK.getText());
        gk.setTenGongKinh(txt_tenGK.getText());
        gk.setGiaThanh(Double.parseDouble(txt_GiaBan.getText()));
        gk.setHinhAnh(lbl_anh.getText());
        gk.setMoTa(txt_mota.getText());
        mausac ms = mssv.tenMauSac(CBo_MS.getSelectedItem().toString());
        gk.setTenMauSac(ms);
        chatLieu cl = clsv.tenchatLieu(Cbo_chatLieu.getSelectedItem().toString());
        gk.setTenChatLieu(cl);
        thuonghieu th = thsv.tenThuongHieu(CBO_TH.getSelectedItem().toString());
        gk.setTenThuongHieu(th);
        gk.setSoLuong(Integer.parseInt(TxtSoLuong.getText()));
        gk.setHinhAnh(linkAnh);
        return gk;
    }

    void reset() {
        txt_GiaBan.setText(null);
        TxtSoLuong.setText(null);
        txt_maGK.setText(null);
        txt_mota.setText(null);
        txt_tenGK.setText(null);
        Cbo_chatLieu.setSelectedIndex(0);
        CBo_MS.setSelectedIndex(0);
        CBO_TH.setSelectedIndex(0);
        lbl_anh.setIcon(null);
    }

    boolean check() {
        if (txt_maGK.getText().trim().isEmpty()) {
            lblCheckMaGongKinh.setText("Vui lòng nhập Mã Gọng Kính");
            lblCheckMaGongKinh.setForeground(Color.RED);
            return false;

        } else {
            lblCheckMaGongKinh.setText(null);
        }

// Kiểm tra trường Tên Gọng Kính
        if (txt_tenGK.getText().trim().isEmpty()) {
            lblCheckTenGongKinh.setText("Vui lòng nhập Tên Gọng Kính");
            lblCheckTenGongKinh.setForeground(Color.RED);
            return false;

        } else {
            lblCheckTenGongKinh.setText(null);
        }
        try {
            int soLuong = Integer.parseInt(TxtSoLuong.getText());

        } catch (NumberFormatException ex) {
            lblCheckSoLuong.setText("Số lượng phải là số nguyên");
            lblCheckSoLuong.setForeground(Color.RED);
            return false;
        }

// Kiểm tra trường Giá Bán
        try {
            float giaBan = Float.parseFloat(txt_GiaBan.getText());

        } catch (NumberFormatException ex) {
            lblCheckGiaBan.setText("Giá bán phải là số thực");
            lblCheckGiaBan.setForeground(Color.RED);

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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_maGK = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_tenGK = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtSoLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_GiaBan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbl_anh = new javax.swing.JLabel();
        btn_addAnh = new javax.swing.JButton();
        btn_deleteAnh = new javax.swing.JButton();
        CBo_MS = new javax.swing.JComboBox<>();
        Cbo_chatLieu = new javax.swing.JComboBox<>();
        CBO_TH = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_mota = new javax.swing.JTextArea();
        lblCheckMaGongKinh = new javax.swing.JLabel();
        lblCheckTenGongKinh = new javax.swing.JLabel();
        lblCheckSoLuong = new javax.swing.JLabel();
        lblCheckGiaBan = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lblbang = new javax.swing.JTable();
        btn_reset = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        txt_seach = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CBO_TH1_check = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lý Sản Phẩm"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Mã Gọng Kính");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 40, -1, 26));

        txt_maGK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maGKActionPerformed(evt);
            }
        });
        jPanel2.add(txt_maGK, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 40, 193, 26));

        jLabel5.setText("Tên Gọng Kính");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 96, -1, 26));

        txt_tenGK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenGKActionPerformed(evt);
            }
        });
        jPanel2.add(txt_tenGK, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 96, 193, 26));

        jLabel6.setText("Số Lượng");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 151, 56, 26));
        jPanel2.add(TxtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 151, 193, 26));

        jLabel7.setText("Giá Bán");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 207, 56, 26));
        jPanel2.add(txt_GiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 207, 193, 26));

        jLabel8.setText("Màu Sắc");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 40, 56, 26));

        jLabel9.setText("Chất Liệu");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 96, 56, 26));

        jLabel10.setText("Thương Hiệu");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 151, -1, 26));

        jLabel11.setText("Mô Tả");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 207, 56, 26));

        lbl_anh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(lbl_anh, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 202, 202));

        btn_addAnh.setText("Thêm ảnh");
        btn_addAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addAnhActionPerformed(evt);
            }
        });
        jPanel2.add(btn_addAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 250, -1, -1));

        btn_deleteAnh.setText("Xóa Ảnh");
        btn_deleteAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteAnhActionPerformed(evt);
            }
        });
        jPanel2.add(btn_deleteAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(829, 250, -1, -1));

        CBo_MS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBo_MS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBo_MSActionPerformed(evt);
            }
        });
        jPanel2.add(CBo_MS, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 40, 193, 26));

        Cbo_chatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Cbo_chatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cbo_chatLieuActionPerformed(evt);
            }
        });
        jPanel2.add(Cbo_chatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 96, 193, 26));

        CBO_TH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_TH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_THActionPerformed(evt);
            }
        });
        jPanel2.add(CBO_TH, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 151, 193, 26));

        txt_mota.setColumns(20);
        txt_mota.setRows(3);
        txt_mota.setTabSize(1);
        jScrollPane1.setViewportView(txt_mota);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 209, 210, 80));
        jPanel2.add(lblCheckMaGongKinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 68, 193, 20));
        jPanel2.add(lblCheckTenGongKinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 123, 193, 20));
        jPanel2.add(lblCheckSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 179, 193, 20));
        jPanel2.add(lblCheckGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 235, 193, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 930, 300));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Sản Phẩm"));

        lblbang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        lblbang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lblbang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 930, 220));

        btn_reset.setText("Làm Mới");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        getContentPane().add(btn_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, 90, 30));

        btn_add.setText("Thêm");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        getContentPane().add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 90, 30));

        btn_update.setText("Sửa");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 90, 30));

        btn_delete.setText("Xóa");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 90, 30));

        txt_seach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_seachFocusLost(evt);
            }
        });
        txt_seach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_seachMousePressed(evt);
            }
        });
        txt_seach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_seachActionPerformed(evt);
            }
        });
        txt_seach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_seachKeyReleased(evt);
            }
        });
        getContentPane().add(txt_seach, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 360, 250, 30));

        jLabel1.setText("Thương Hiệu");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 340, 70, 20));

        CBO_TH1_check.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_TH1_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_TH1_checkItemStateChanged(evt);
            }
        });
        getContentPane().add(CBO_TH1_check, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 360, 200, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tenGKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenGKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenGKActionPerformed

    private void CBo_MSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBo_MSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBo_MSActionPerformed

    private void Cbo_chatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cbo_chatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cbo_chatLieuActionPerformed

    private void CBO_THActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_THActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_THActionPerformed

    private void txt_maGKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maGKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maGKActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_add.setEnabled(true);
        txt_maGK.setEnabled(true);
        reset();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void txt_seachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_seachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_seachActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        if (check()) {
            Giongkinh gk = this.readmodel_gongKinh();
            if (gksv.selectByID(gk.getMaGongKinh()) != null) {
                lblCheckMaGongKinh.setText("Trùng mã");
                lblCheckMaGongKinh.setForeground(Color.RED);
            } else {
                lblCheckMaGongKinh.setText(null);
                if (gksv.Insert(gk) > 0) {
                    JOptionPane.showMessageDialog(this, "Thành Công");
                    fillTable(gksv.selectAll());
                    reset();
                }
            }
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        if (check()) {
            Giongkinh gk = this.readmodel_gongKinh();
            String ma = lblbang.getValueAt(index, 0).toString();
            if (gksv.update(gk, ma) > 0) {
                JOptionPane.showMessageDialog(this, "Thành Công");
                fillTable(gksv.selectAll());
                btn_add.setEnabled(true);
                txt_maGK.setEnabled(true);
                reset();
            }
        }

    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if (check()) {
            Giongkinh gk = this.readmodel_gongKinh();
            if (gksv.delete(gk.getMaGongKinh()) > 0) {
                JOptionPane.showMessageDialog(this, "Thành Công");
                fillTable(gksv.selectAll());
                btn_add.setEnabled(true);
                txt_maGK.setEnabled(true);
                reset();
            }
        }

    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_addAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addAnhActionPerformed
        try {

            JFileChooser jfc = new JFileChooser();

            jfc.showOpenDialog(null);

            File file = jfc.getSelectedFile();

            Image img = ImageIO.read(file);
            int width = lbl_anh.getWidth();
            int height = lbl_anh.getHeight();
            Image resizedImage = img.getScaledInstance(width, height, 0);
            lbl_anh.setIcon(new ImageIcon(resizedImage));
            linkAnh = file.getAbsolutePath();

        } catch (Exception e) {

            return;

        }
    }//GEN-LAST:event_btn_addAnhActionPerformed

    private void btn_deleteAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteAnhActionPerformed
        lbl_anh.setIcon(null);
        if (linkAnh != null) {
            linkAnh = null;
        } else {
            JOptionPane.showMessageDialog(this, "Chưa thêm ảnh");
        }
    }//GEN-LAST:event_btn_deleteAnhActionPerformed

    private void lblbangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbangMouseClicked
        index = lblbang.getSelectedRow();
        String ma = lblbang.getValueAt(index, 0).toString();
        if (gksv.selectByID(ma) != null) {
            Giongkinh gk = gksv.selectByID(ma);
            showmodel_gongKing(gk);
            txt_maGK.setEnabled(false);
            btn_add.setEnabled(false);
            btn_update.setEnabled(true);
            btn_delete.setEnabled(true);
        }
    }//GEN-LAST:event_lblbangMouseClicked

    private void txt_seachFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_seachFocusLost
        if (txt_seach.getText().isEmpty()) {
            txt_seach.setText("Tìm kiếm");
            fillTable(gksv.selectAll());
        }
    }//GEN-LAST:event_txt_seachFocusLost

    private void txt_seachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_seachMousePressed
        txt_seach.setText(null);
    }//GEN-LAST:event_txt_seachMousePressed

    private void txt_seachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_seachKeyReleased
        try {
            String check = '%' + txt_seach.getText() + '%';
            if (txt_seach.getText().isEmpty()) {
                fillTable(gksv.selectAll());

            } else {
                List<Giongkinh> listds = gksv.seach(check);
                fillTable(listds);
            }
        } catch (Exception e) {
            return;
        }
    }//GEN-LAST:event_txt_seachKeyReleased

    private void CBO_TH1_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_TH1_checkItemStateChanged
        try {
            String th1 = CBO_TH1_check.getSelectedItem().toString();
            List<Giongkinh> list = gksv.check_Cbo(th1);
            fillTable(list);
        } catch (Exception e) {
            return;
        }
    }//GEN-LAST:event_CBO_TH1_checkItemStateChanged

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
            java.util.logging.Logger.getLogger(QuanLyGongKinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyGongKinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyGongKinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyGongKinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyGongKinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBO_TH;
    private javax.swing.JComboBox<String> CBO_TH1_check;
    private javax.swing.JComboBox<String> CBo_MS;
    private javax.swing.JComboBox<String> Cbo_chatLieu;
    private javax.swing.JTextField TxtSoLuong;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_addAnh;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_deleteAnh;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCheckGiaBan;
    private javax.swing.JLabel lblCheckMaGongKinh;
    private javax.swing.JLabel lblCheckSoLuong;
    private javax.swing.JLabel lblCheckTenGongKinh;
    private javax.swing.JLabel lbl_anh;
    private javax.swing.JTable lblbang;
    private javax.swing.JTextField txt_GiaBan;
    private javax.swing.JTextField txt_maGK;
    private javax.swing.JTextArea txt_mota;
    private javax.swing.JTextField txt_seach;
    private javax.swing.JTextField txt_tenGK;
    // End of variables declaration//GEN-END:variables
}
