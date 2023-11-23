/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.GongKinhChiTiet;
import model.ChatLieu;
import model.Mausac;
import model.SanPham;
import model.Thuonghieu;
import service.GongKinh_Service;
import service.SPCT_Service;
import service.chatLieu_service;
import service.mausac_service;
import service.thuonghieu_service;
import view.ChatLieuForm;
import view.MauSacForm;
import view.ThuongHieuForm;

/**
 *
 * @author leduc
 */
public class TrongKinhChiTiet extends javax.swing.JPanel {

    DefaultTableModel model;
    mausac_service mssv = new mausac_service();
    Mausac ms = new Mausac();
    ChatLieu cl = new ChatLieu();
    chatLieu_service clsv = new chatLieu_service();
    SanPham sp = new SanPham();
    SPCT_Service spctService = new SPCT_Service();
    String linkAnh = null;
    //model.TrongKinhChiTiet tk = new model.TrongKinhChiTiet();
    int id = Integer.parseInt(QLTKForm.id);
    int index = -1;

    /**
     * Creates new form TrongKinhChiTiet
     */
    public TrongKinhChiTiet(String dataControner) {
        initComponents();
        fillTable(spctService.selectAll(id));
        CBo_ChatLieu();
        CBo_MauSac();
        showMaAndten();
        name();
        txt_search.setText("Tìm Kiếm");
        rboDB.enable(true);
    }

    private TrongKinhChiTiet() {
    }

    void name() {
        String[] table1 = {"ID", "Chất Liệu", "Màu Sắc", "Giá Bán", "Số Lượng", "Hình Ảnh", "Mô Tả", "Trạng thái"};
        for (int i = 0; i < table1.length; i++) {
            lblbang.getColumnModel().getColumn(i).setHeaderValue(table1[i]);
        }
    }

    void CBo_MauSac() {
        List<Mausac> listmausac = mssv.FILL_TO_CBO_MauSac();
        String[] cbo = new String[listmausac.size()];
        for (int i = 0; i < listmausac.size(); i++) {
            cbo[i] = listmausac.get(i).getTenMauSac();
        }
        cbomausac.setModel(new DefaultComboBoxModel<>(cbo));
        CBO_MS_check.setModel(new DefaultComboBoxModel<>(cbo));
    }

    void showMaAndten() {
        SanPham sp = spctService.Show(id);
        String ma = sp.getMaSP();
        String ten = sp.getTenSP();
        String name = ma + "-" + ten.replace("ơ", "o");
        this.lbl_magk.setText(name);
    }

    void CBo_ChatLieu() {
        List<ChatLieu> listchatlieu = clsv.FILL_TO_CBO_ChatLieu();
        String[] cbo = new String[listchatlieu.size()];
        for (int i = 0; i < listchatlieu.size(); i++) {
            cbo[i] = listchatlieu.get(i).getTenChatLieu();
        }
        cbochatlieu.setModel(new DefaultComboBoxModel<>(cbo));
        CBO_CL_check.setModel(new DefaultComboBoxModel<>(cbo));
    }

    void fillTable(List<model.SanPhamChiTiet> list) {
        model = (DefaultTableModel) lblbang.getModel();
        model.setRowCount(0);
        for (model.SanPhamChiTiet spct : list) {
            model.addRow(spct.todata());
        }
    }

    void Show(model.SanPhamChiTiet spct1) {
        model.SanPhamChiTiet sp = spct1;
        TxtSoLuong.setText(String.valueOf(sp.getSoluong()));
        txt_GiaBan.setText(String.valueOf(sp.getGiathanh()));
        txt_mota.setText(sp.getMota());
        cbomausac.setSelectedItem(sp.getColor().getTenMauSac());
        cbochatlieu.setSelectedItem(sp.getMaterial().getTenChatLieu());
        if (sp.getTrangthai().equals("Đang bán")) {
            rboDB.setSelected(true);
        } else {
            rboNB.setSelected(true);
        }
        lbl_anh.setIcon(null);
        linkAnh = null;
        try {
            File file = new File(sp.getHinhanh());
            Image img = ImageIO.read(file);
            int width = lbl_anh.getWidth();
            int height = lbl_anh.getHeight();
            Image resizedImage = img.getScaledInstance(width, height, 0);
            lbl_anh.setIcon(new ImageIcon(resizedImage));
            linkAnh = file.getAbsolutePath();
        } catch (Exception e) {
        }
    }

    model.SanPhamChiTiet read() {
        model.SanPhamChiTiet tk = new model.SanPhamChiTiet();
        tk.setSp(new SanPham(id, null, null, null, null));
        tk.setGiathanh(Double.parseDouble(txt_GiaBan.getText()));
        tk.setMota(txt_mota.getText());
        tk.setSoluong(Integer.parseInt(TxtSoLuong.getText()));
        Mausac ms = mssv.tenMauSac(cbomausac.getSelectedItem().toString());
        tk.setColor(ms);
        ChatLieu cl = clsv.tenchatLieu(cbochatlieu.getSelectedItem().toString());
        tk.setMaterial(cl);
        tk.setHinhanh(linkAnh);
        if (rboDB.isSelected() == true) {
            tk.setTrangthai("Đang bán");
        } else {
            tk.setTrangthai("Ngưng bán");
        }
        return tk;
    }

    void reset() {
        TxtSoLuong.setText(null);
        txt_GiaBan.setText(null);
        txt_mota.setText(null);
        lbl_anh.setIcon(null);
        cbochatlieu.setSelectedIndex(0);
        cbomausac.setSelectedIndex(0);
        CBO_CL_check.setSelectedIndex(0);
        lblCheckGiaBan.setText(null);
        lblCheckSoLuong.setText(null);
        lblCheckDoCan.setText(null);
        fillTable(spctService.selectAll(id));
    }

    boolean check() {
        //soluong
        if (TxtSoLuong.getText().trim().isEmpty()) {
            lblCheckSoLuong.setText("Vui lòng nhập số lượng");
            lblCheckSoLuong.setForeground(java.awt.Color.red);

        } else {
            lblCheckSoLuong.setText(null);
        }
        try {
            int soLuong = Integer.parseInt(TxtSoLuong.getText());
            if (soLuong <= 0) {
                lblCheckSoLuong.setText("Số lượng lớn hơn 0");
                lblCheckSoLuong.setForeground(java.awt.Color.red);

            }
        } catch (Exception e) {
            lblCheckSoLuong.setText("Số lượng phải là số nguyên");
            lblCheckSoLuong.setForeground(java.awt.Color.red);

        }

        //gia
        if (txt_GiaBan.getText().trim().isEmpty()) {
            lblCheckGiaBan.setText("Vui lòng nhập giá thành");
            lblCheckGiaBan.setForeground(java.awt.Color.red);

        } else {
            lblCheckGiaBan.setText(null);
        }

        try {
            Double soLuong = Double.parseDouble(txt_GiaBan.getText());
            if (soLuong <= 0) {
                lblCheckGiaBan.setText("Giá bán lớn hơn 0");
                lblCheckGiaBan.setForeground(java.awt.Color.red);

            }
        } catch (Exception e) {
            lblCheckGiaBan.setText("Giá bán phải là số nguyên");
            lblCheckGiaBan.setForeground(java.awt.Color.red);

        }

        //do can
        //gia
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        color3 = new com.raven.form.Color();
        lbl_magk = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        form2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        TxtSoLuong = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_GiaBan = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_anh = new javax.swing.JLabel();
        btn_addAnh1 = new javax.swing.JButton();
        btn_deleteAnh1 = new javax.swing.JButton();
        cbochatlieu = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_mota = new javax.swing.JTextArea();
        lblCheckDoCan = new javax.swing.JLabel();
        lblCheckGiaBan = new javax.swing.JLabel();
        themnhanhmausac1 = new javax.swing.JPanel();
        cbomausac = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        rboDB = new javax.swing.JRadioButton();
        rboNB = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblCheckSoLuong = new javax.swing.JLabel();
        btn_Delete = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        CBO_CL_check = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lblbang = new javax.swing.JTable();
        CBO_MS_check = new javax.swing.JComboBox<>();

        color3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_magk.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lbl_magk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_magk.setToolTipText("");
        lbl_magk.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        color3.add(lbl_magk, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 17, 900, 56));

        txt_search.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_searchFocusLost(evt);
            }
        });
        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_searchMousePressed(evt);
            }
        });
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        color3.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, 341, 43));

        btn_add.setText("Thêm");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        color3.add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 83, 40));

        btn_update.setText("Sửa");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        color3.add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 83, 40));

        form2.setBackground(java.awt.Color.white);
        form2.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lý Sản Phẩm"));
        form2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setText("Số Lượng");
        form2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 60, 20));
        form2.add(TxtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 193, 26));

        jLabel16.setText("Giá Bán");
        form2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 56, 26));
        form2.add(txt_GiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 193, 26));

        jLabel17.setText("Màu Sắc");
        form2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 56, 30));

        jLabel18.setText("Chất Liệu");
        form2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 56, 30));

        jLabel20.setText("Mô Tả");
        form2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 56, 30));

        lbl_anh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        form2.add(lbl_anh, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 310, 220));

        btn_addAnh1.setText("Thêm ảnh");
        btn_addAnh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addAnh1ActionPerformed(evt);
            }
        });
        form2.add(btn_addAnh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 260, -1, -1));

        btn_deleteAnh1.setText("Xóa Ảnh");
        btn_deleteAnh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteAnh1ActionPerformed(evt);
            }
        });
        form2.add(btn_deleteAnh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 260, -1, -1));

        cbochatlieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbochatlieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbochatlieuMousePressed(evt);
            }
        });
        cbochatlieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbochatlieuActionPerformed(evt);
            }
        });
        form2.add(cbochatlieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 193, 30));

        txt_mota.setColumns(20);
        txt_mota.setRows(3);
        txt_mota.setTabSize(1);
        jScrollPane2.setViewportView(txt_mota);

        form2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 210, 80));
        form2.add(lblCheckDoCan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 230, 20));
        form2.add(lblCheckGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 230, 20));

        javax.swing.GroupLayout themnhanhmausac1Layout = new javax.swing.GroupLayout(themnhanhmausac1);
        themnhanhmausac1.setLayout(themnhanhmausac1Layout);
        themnhanhmausac1Layout.setHorizontalGroup(
            themnhanhmausac1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        themnhanhmausac1Layout.setVerticalGroup(
            themnhanhmausac1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        form2.add(themnhanhmausac1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 230, 0));

        cbomausac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbomausac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbomausacMousePressed(evt);
            }
        });
        cbomausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomausacActionPerformed(evt);
            }
        });
        form2.add(cbomausac, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 193, 30));

        jLabel1.setText("Trạng Thái");
        form2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 60, 20));

        buttonGroup1.add(rboDB);
        rboDB.setSelected(true);
        rboDB.setText("Đang bán");
        form2.add(rboDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, -1, -1));

        buttonGroup1.add(rboNB);
        rboNB.setText("Ngưng bán");
        form2.add(rboNB, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("*");
        form2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 20, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("*");
        form2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 20, 20));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Microsoft-Fluentui-Emoji-Mono-Plus.24.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        form2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 30, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Microsoft-Fluentui-Emoji-Mono-Plus.24.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        form2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 30, 30));
        form2.add(lblCheckSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 230, 20));

        color3.add(form2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 85, 1058, 300));

        btn_Delete.setText("Xóa");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });
        color3.add(btn_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 83, 41));

        btn_reset.setText("Làm Mới");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        color3.add(btn_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 83, 40));

        CBO_CL_check.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_CL_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_CL_checkItemStateChanged(evt);
            }
        });
        CBO_CL_check.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CBO_CL_checkMousePressed(evt);
            }
        });
        CBO_CL_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_CL_checkActionPerformed(evt);
            }
        });
        color3.add(CBO_CL_check, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 420, 90, 40));

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Sản Phẩm"));

        lblbang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lblbang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lblbang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        color3.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 474, -1, -1));

        CBO_MS_check.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_MS_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_MS_checkItemStateChanged(evt);
            }
        });
        CBO_MS_check.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBO_MS_checkMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CBO_MS_checkMousePressed(evt);
            }
        });
        CBO_MS_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_MS_checkActionPerformed(evt);
            }
        });
        color3.add(CBO_MS_check, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 420, 90, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusLost
        if (txt_search.getText().isEmpty()) {
            txt_search.setText("Tìm Kiếm");
            fillTable(spctService.selectAll(id));
        }
    }//GEN-LAST:event_txt_searchFocusLost

    private void txt_searchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMousePressed
        txt_search.setText(null);
    }//GEN-LAST:event_txt_searchMousePressed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        try {
            String search = "%" + txt_search.getText() + "%";
            if (txt_search.getText().isEmpty()) {
                fillTable(spctService.selectAll(id));
            } else {
                List<model.SanPhamChiTiet> listsearch = spctService.seach(id, search);
                fillTable(listsearch);
            }
        } catch (Exception e) {
            return;
        }
    }//GEN-LAST:event_txt_searchKeyReleased

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        if (check()) {
            model.SanPhamChiTiet sp = this.read();
            if (spctService.Insert(sp) > 0) {
                JOptionPane.showMessageDialog(this, "Them thanh cong");
                fillTable(spctService.selectAll(id));
                reset();
            }
        }

    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        if (check()) {
            model.SanPhamChiTiet sp = this.read();
            int b = (int) lblbang.getValueAt(index, 0);
            if (spctService.update(sp, b) > 0) {
                JOptionPane.showMessageDialog(this, "Sua thanh cong");
                fillTable(spctService.selectAll(id));
                reset();
            }
        }

    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_addAnh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addAnh1ActionPerformed
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
    }//GEN-LAST:event_btn_addAnh1ActionPerformed

    private void btn_deleteAnh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteAnh1ActionPerformed
        lbl_anh.setIcon(null);
        if (linkAnh != null) {
            linkAnh = null;
        } else {
            JOptionPane.showMessageDialog(this, "Chưa thêm ảnh");
        }
    }//GEN-LAST:event_btn_deleteAnh1ActionPerformed

    private void cbochatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbochatlieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbochatlieuActionPerformed

    private void cbomausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomausacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbomausacActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        if (check()) {
            int a = (int) lblbang.getValueAt(index, 0);
            if (spctService.delete(a) > 0) {
                JOptionPane.showMessageDialog(this, "Xoa thành công");
                fillTable(spctService.selectAll(id));
                reset();
            }
        }

    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        reset();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void CBO_CL_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_CL_checkItemStateChanged
        String name = CBO_CL_check.getSelectedItem().toString();
        if (spctService.check_Cbo_CL(id, name) != null) {
            List<model.SanPhamChiTiet> clcheck = spctService.check_Cbo_CL(id, name);
            fillTable(clcheck);
        } else {
            fillTable(spctService.selectAll(id));
        }
    }//GEN-LAST:event_CBO_CL_checkItemStateChanged

    private void CBO_CL_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_CL_checkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_CL_checkActionPerformed

    private void lblbangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbangMouseClicked
        index = lblbang.getSelectedRow();
        int id = (int) lblbang.getValueAt(index, 0);
        model.SanPhamChiTiet spct = spctService.selectByID(id);
        Show(spct);
    }//GEN-LAST:event_lblbangMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        MauSacForm ql = new MauSacForm();
        ql.setVisible(true);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        ChatLieuForm ql = new ChatLieuForm();
        ql.setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked

    private void cbomausacMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbomausacMousePressed
        // TODO add your handling code here:
        CBo_MauSac();
    }//GEN-LAST:event_cbomausacMousePressed

    private void cbochatlieuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbochatlieuMousePressed
        // TODO add your handling code here:
        CBo_ChatLieu();
    }//GEN-LAST:event_cbochatlieuMousePressed

    private void CBO_MS_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_MS_checkItemStateChanged
        // TODO add your handling code here:
        String name = CBO_MS_check.getSelectedItem().toString();
        if (spctService.check_Cbo_MS(id, name) != null) {
            List<model.SanPhamChiTiet> clcheck = spctService.check_Cbo_MS(id, name);
            fillTable(clcheck);
        } else {
            fillTable(spctService.selectAll(id));
        }
    }//GEN-LAST:event_CBO_MS_checkItemStateChanged

    private void CBO_MS_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_MS_checkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_MS_checkActionPerformed

    private void CBO_MS_checkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBO_MS_checkMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_MS_checkMouseClicked

    private void CBO_MS_checkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBO_MS_checkMousePressed
        // TODO add your handling code here:
        CBo_MauSac();
    }//GEN-LAST:event_CBO_MS_checkMousePressed

    private void CBO_CL_checkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBO_CL_checkMousePressed
        // TODO add your handling code here:
        CBo_ChatLieu();
    }//GEN-LAST:event_CBO_CL_checkMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBO_CL_check;
    private javax.swing.JComboBox<String> CBO_MS_check;
    private javax.swing.JTextField TxtSoLuong;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_addAnh1;
    private javax.swing.JButton btn_deleteAnh1;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbochatlieu;
    private javax.swing.JComboBox<String> cbomausac;
    private com.raven.form.Color color3;
    private javax.swing.JPanel form2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCheckDoCan;
    private javax.swing.JLabel lblCheckGiaBan;
    private javax.swing.JLabel lblCheckSoLuong;
    private javax.swing.JLabel lbl_anh;
    private javax.swing.JLabel lbl_magk;
    private javax.swing.JTable lblbang;
    private javax.swing.JRadioButton rboDB;
    private javax.swing.JRadioButton rboNB;
    private javax.swing.JPanel themnhanhmausac1;
    private javax.swing.JTextField txt_GiaBan;
    private javax.swing.JTextArea txt_mota;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
