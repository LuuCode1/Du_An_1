/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
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
     * Creates new form QLGK
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
            lbl_check_giaBan.setText("Không được để trống");
            lbl_check_giaBan.setForeground(Color.red);
            return false;
        } else {
            lbl_check_giaBan.setText(null);
        }

        if (txt_tenGK.getText().trim().isEmpty()) {
            // Hiển thị thông báo lỗi
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_maGK = new javax.swing.JTextField();
        txt_tenGK = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_GiaBan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtSoLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_mota = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_check_giaBan = new javax.swing.JLabel();
        lbl_anh = new javax.swing.JLabel();
        btn_addAnh = new javax.swing.JButton();
        CBo_MS = new javax.swing.JComboBox<>();
        CBO_TH = new javax.swing.JComboBox<>();
        Cbo_chatLieu = new javax.swing.JComboBox<>();
        btn_deleteAnh = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lbl_check_soLuong = new javax.swing.JLabel();
        txtcheckma2 = new javax.swing.JLabel();
        lbl_checkten1 = new javax.swing.JLabel();
        lbl_checkten2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblbang = new javax.swing.JTable();
        btn_add = new javax.swing.JButton();
        add_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        txt_seach = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        CBO_TH1_check = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lý Sản Phẩm"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Mã Gọng Kính");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 24, 115, -1));

        txt_maGK.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txt_maGK.setPreferredSize(new java.awt.Dimension(64, 22));
        jPanel2.add(txt_maGK, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 46, 255, -1));

        txt_tenGK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenGKActionPerformed(evt);
            }
        });
        jPanel2.add(txt_tenGK, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 116, 255, -1));

        jLabel2.setText("Tên Gọng Kính");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 94, 115, -1));

        jLabel3.setText("Chất Liệu");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 172, 115, -1));

        jLabel4.setText("Màu Sắc");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 247, 115, -1));
        jPanel2.add(txt_GiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 194, 250, -1));

        jLabel6.setText("Giá Bán");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 172, 115, -1));

        TxtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSoLuongActionPerformed(evt);
            }
        });
        jPanel2.add(TxtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 116, 250, -1));

        jLabel7.setText("Số Lượng");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 94, 115, -1));

        jLabel8.setText("Thương Hiệu");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 24, 115, -1));

        txt_mota.setColumns(20);
        txt_mota.setRows(5);
        jScrollPane3.setViewportView(txt_mota);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 277, 304, 40));

        jLabel10.setText("Mô Tả");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 115, -1));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 88, -1, -1));
        jPanel2.add(lbl_check_giaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 250, 20));

        lbl_anh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbl_anh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_anhMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_anh, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 220, 210));

        btn_addAnh.setText("Thêm ảnh");
        btn_addAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addAnhActionPerformed(evt);
            }
        });
        jPanel2.add(btn_addAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 260, 90, 30));

        CBo_MS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(CBo_MS, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 260, -1));

        CBO_TH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(CBO_TH, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 250, -1));

        Cbo_chatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(Cbo_chatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 260, -1));

        btn_deleteAnh.setText("Xóa Ảnh");
        btn_deleteAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteAnhActionPerformed(evt);
            }
        });
        jPanel2.add(btn_deleteAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, 90, 30));

        jLabel11.setText("+");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 20, 20));
        jPanel2.add(lbl_check_soLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 250, 20));
        jPanel2.add(txtcheckma2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 250, 20));
        jPanel2.add(lbl_checkten1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 250, 20));
        jPanel2.add(lbl_checkten2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 250, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1012, 330));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Sản Phẩm"));

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
        jScrollPane1.setViewportView(lblbang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 1012, 210));

        btn_add.setText("Thêm");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        getContentPane().add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 80, 20));

        add_update.setText("Sửa");
        add_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_updateActionPerformed(evt);
            }
        });
        getContentPane().add(add_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 80, 20));

        btn_delete.setText("Xóa");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 80, 20));

        btn_reset.setText("Làm Mới");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        getContentPane().add(btn_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 90, 20));

        txt_seach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_seachFocusLost(evt);
            }
        });
        txt_seach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_seachMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_seachMouseExited(evt);
            }
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
        getContentPane().add(txt_seach, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 250, 30));

        jLabel13.setText("Thương Hiệu");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, 30));

        CBO_TH1_check.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_TH1_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_TH1_checkItemStateChanged(evt);
            }
        });
        CBO_TH1_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_TH1_checkActionPerformed(evt);
            }
        });
        CBO_TH1_check.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CBO_TH1_checkKeyReleased(evt);
            }
        });
        getContentPane().add(CBO_TH1_check, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 390, 162, 34));

        jLabel12.setText("jLabel12");
        jLabel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 40, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tenGKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenGKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenGKActionPerformed

    private void TxtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSoLuongActionPerformed

    private void lbl_anhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_anhMouseClicked


    }//GEN-LAST:event_lbl_anhMouseClicked

    private void btn_addAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addAnhActionPerformed
        // TODO add your handling code here:
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

    private void lblbangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbangMouseClicked
        index = lblbang.getSelectedRow();
        String ma = lblbang.getValueAt(index, 0).toString();
        if (gksv.selectByID(ma) != null) {
            Giongkinh gk = gksv.selectByID(ma);
            showmodel_gongKing(gk);
        }
    }//GEN-LAST:event_lblbangMouseClicked

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        if (check()) {
            Giongkinh gk = this.readmodel_gongKinh();
            if (gksv.Insert(gk) > 0) {
                JOptionPane.showMessageDialog(this, "Thành Công");
                fillTable(gksv.selectAll());
                reset();
            }
        }

    }//GEN-LAST:event_btn_addActionPerformed

    private void add_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_updateActionPerformed
        // TODO add your handling code here:
        Giongkinh gk = this.readmodel_gongKinh();
        String ma = lblbang.getValueAt(index, 0).toString();
        if (gksv.update(gk, ma) > 0) {
            JOptionPane.showMessageDialog(this, "Thành Công");
            fillTable(gksv.selectAll());
            reset();
        }
    }//GEN-LAST:event_add_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        Giongkinh gk = this.readmodel_gongKinh();
        if (gksv.delete(gk.getMaGongKinh()) > 0) {
            JOptionPane.showMessageDialog(this, "Thành Công");
            fillTable(gksv.selectAll());
            reset();
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        reset();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_deleteAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteAnhActionPerformed
        lbl_anh.setIcon(null);
        if (linkAnh != null) {
            linkAnh = null;
        } else {
            JOptionPane.showMessageDialog(this, "Chưa thêm ảnh");
        }
    }//GEN-LAST:event_btn_deleteAnhActionPerformed

    private void CBO_TH1_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_TH1_checkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_TH1_checkActionPerformed

    private void CBO_TH1_checkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBO_TH1_checkKeyReleased

    }//GEN-LAST:event_CBO_TH1_checkKeyReleased

    private void CBO_TH1_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_TH1_checkItemStateChanged
        // TODO add your handling code here:
        try {
            String th1 = CBO_TH1_check.getSelectedItem().toString();
            List<Giongkinh> list = gksv.check_Cbo(th1);
            fillTable(list);
        } catch (Exception e) {
            return;
        }

    }//GEN-LAST:event_CBO_TH1_checkItemStateChanged

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

    private void txt_seachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_seachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_seachActionPerformed

    private void txt_seachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_seachMouseClicked
       
    }//GEN-LAST:event_txt_seachMouseClicked

    private void txt_seachMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_seachMouseExited
        
    }//GEN-LAST:event_txt_seachMouseExited

    private void txt_seachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_seachMousePressed
        // TODO add your handling code here:
        txt_seach.setText(null);
    }//GEN-LAST:event_txt_seachMousePressed

    private void txt_seachFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_seachFocusLost
        if(txt_seach.getText().isEmpty()) {
      txt_seach.setText("Tìm kiếm");
            fillTable(gksv.selectAll());
    } 
    }//GEN-LAST:event_txt_seachFocusLost

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JButton add_update;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_addAnh;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_deleteAnh;
    private javax.swing.JButton btn_reset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_anh;
    private javax.swing.JLabel lbl_check_giaBan;
    private javax.swing.JLabel lbl_check_soLuong;
    private javax.swing.JLabel lbl_checkten1;
    private javax.swing.JLabel lbl_checkten2;
    private javax.swing.JTable lblbang;
    private javax.swing.JTextField txt_GiaBan;
    private javax.swing.JTextField txt_maGK;
    private javax.swing.JTextArea txt_mota;
    private javax.swing.JTextField txt_seach;
    private javax.swing.JTextField txt_tenGK;
    private javax.swing.JLabel txtcheckma2;
    // End of variables declaration//GEN-END:variables
}
