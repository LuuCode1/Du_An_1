/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.form;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.Gongkinh;
import model.QLGK;
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
public class GKCT extends javax.swing.JFrame {
    
    DefaultTableModel model;
    mausac_service mssv = new mausac_service();
    mausac ms = new mausac();
    chatLieu cl = new chatLieu();
    chatLieu_service clsv = new chatLieu_service();
    thuonghieu th = new thuonghieu();
    thuonghieu_service thsv = new thuonghieu_service();
    String linkAnh = null;
    Gongkinh gk = new Gongkinh();
    GongKinh_Service gksv = new GongKinh_Service();
    int ma = Integer.parseInt(QLGKForm.magk);
    int index = -1;
    

    /**
     * Creates new form QKCT
     */
    public GKCT(String dataControner) {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        fillTable();
        CBo_ChatLieu();
        CBo_MauSac();
        CBo_ThuongHieu();
        showMaAndten();

    }

    private GKCT() {
    }

    void name() {
        String[] table1 = {"Chất Liệu", "Màu Sắc", "Thương Hiệu", "Số Lượng", "Giá Bán", "Hình Ảnh", "Mô Tả", "Trạng thái"};
        for (int i = 0; i < table1.length; i++) {
            lblbang.getColumnModel().getColumn(i).setHeaderValue(table1[i]);
        }
    }

    void CBo_MauSac() {
        List<mausac> listmausac = mssv.FILL_TO_CBO_MauSac();
        String[] cbo = new String[listmausac.size()];
        for (int i = 0; i < listmausac.size(); i++) {
            cbo[i] = listmausac.get(i).getTenMauSac();
        }
        cbomausac.setModel(new DefaultComboBoxModel<>(cbo));

    }

    void showMaAndten() {
        QLGK qlgk = gksv.Show(ma);
        String ma = qlgk.getMaGK();
        String ten = qlgk.getTenGK();
        String name = ma + "-" + ten.replace("ơ", "o");
        this.lbl_magk.setText(name);
    }

    void CBo_ChatLieu() {
        List<chatLieu> listchatlieu = clsv.FILL_TO_CBO_ChatLieu();
        String[] cbo = new String[listchatlieu.size()];
        for (int i = 0; i < listchatlieu.size(); i++) {
            cbo[i] = listchatlieu.get(i).getTenChatLieu();
        }
        cbochatlieu.setModel(new DefaultComboBoxModel<>(cbo));

    }

    void CBo_ThuongHieu() {
        List<thuonghieu> listthuonghieu = thsv.FILL_TO_CBO_ThuongHieu();
        String[] cbo = new String[listthuonghieu.size()];
        for (int i = 0; i < listthuonghieu.size(); i++) {
            cbo[i] = listthuonghieu.get(i).getTenThuongHieu();

        }
        cbothuonghieu.setModel(new DefaultComboBoxModel<>(cbo));
        CBO_TH1_check.setModel(new DefaultComboBoxModel<>(cbo));
    }

    void fillTable() {

        model = (DefaultTableModel) lblbang.getModel();
        model.setRowCount(0);

        List<Gongkinh> list = gksv.selectAll(ma);
        for (Gongkinh gongkinh : list) {
            model.addRow(gongkinh.todata());
            
        }
        
    }

    void Show(Gongkinh gk1) {
        Gongkinh gk = gk1;
        TxtSoLuong1.setText(String.valueOf(gk.getSoLuong()));
        txt_GiaBan1.setText(String.valueOf(gk.getGiaThanh()));
        txt_mota1.setText(gk.getMoTa());
        cbomausac.setSelectedItem(gk.getTenMauSac().getTenMauSac());
        cbochatlieu.setSelectedItem(gk.getTenChatLieu().getTenChatLieu());
        cbothuonghieu.setSelectedItem(gk.getTenThuongHieu().getTenThuongHieu());
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

    Gongkinh Read() {
        Gongkinh gk = new Gongkinh();
        gk.setGiaThanh(Double.parseDouble(txt_GiaBan1.getText()));
        gk.setMoTa(txt_mota1.getText());
        gk.setSoLuong(Integer.parseInt(TxtSoLuong1.getText()));
        gk.setTenChatLieu((chatLieu) cbochatlieu.getSelectedItem());
        gk.setTenMauSac((mausac) cbomausac.getSelectedItem());
        gk.setTenThuongHieu((thuonghieu) cbothuonghieu.getSelectedItem());
        return gk;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton10 = new javax.swing.JButton();
        lbl_maGK = new javax.swing.JLabel();
        form1 = new javax.swing.JPanel();
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
        lblCheckSoLuong = new javax.swing.JLabel();
        lblCheckGiaBan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        themnhanhmausac = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        CBO_TH1 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        color1 = new com.raven.form.Color();
        jLabel14 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        lbl_magk = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        form2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        TxtSoLuong1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_GiaBan1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_anh1 = new javax.swing.JLabel();
        btn_addAnh1 = new javax.swing.JButton();
        btn_deleteAnh1 = new javax.swing.JButton();
        cbochatlieu = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_mota1 = new javax.swing.JTextArea();
        lblCheckSoLuong1 = new javax.swing.JLabel();
        lblCheckGiaBan1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        themnhanhmausac1 = new javax.swing.JPanel();
        cbothuonghieu = new javax.swing.JComboBox<>();
        cbomausac = new javax.swing.JComboBox<>();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        CBO_TH1_check = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lblbang = new javax.swing.JTable();

        jButton10.setText("jButton1");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        lbl_maGK.setFont(new java.awt.Font("STLiti", 0, 48)); // NOI18N
        lbl_maGK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_maGK.setToolTipText("");
        lbl_maGK.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        form1.setBackground(java.awt.Color.white);
        form1.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lý Sản Phẩm"));
        form1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Số Lượng");
        form1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 56, 26));
        form1.add(TxtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 193, 26));

        jLabel7.setText("Giá Bán");
        form1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 56, 26));
        form1.add(txt_GiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 193, 26));

        jLabel8.setText("Màu Sắc");
        form1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 56, 30));

        jLabel9.setText("Chất Liệu");
        form1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 56, 30));

        jLabel10.setText("Thương Hiệu");
        form1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, 30));

        jLabel11.setText("Mô Tả");
        form1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 56, 30));

        lbl_anh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        form1.add(lbl_anh, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 310, 220));

        btn_addAnh.setText("Thêm ảnh");
        btn_addAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addAnhActionPerformed(evt);
            }
        });
        form1.add(btn_addAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 260, -1, -1));

        btn_deleteAnh.setText("Xóa Ảnh");
        btn_deleteAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteAnhActionPerformed(evt);
            }
        });
        form1.add(btn_deleteAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 260, -1, -1));

        CBo_MS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBo_MS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBo_MSActionPerformed(evt);
            }
        });
        form1.add(CBo_MS, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 193, 30));

        Cbo_chatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Cbo_chatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cbo_chatLieuActionPerformed(evt);
            }
        });
        form1.add(Cbo_chatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 193, 30));

        CBO_TH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_TH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_THActionPerformed(evt);
            }
        });
        form1.add(CBO_TH, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 193, 30));

        txt_mota.setColumns(20);
        txt_mota.setRows(3);
        txt_mota.setTabSize(1);
        jScrollPane1.setViewportView(txt_mota);

        form1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 210, 170));
        form1.add(lblCheckSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 230, 20));
        form1.add(lblCheckGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 230, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Microsoft-Fluentui-Emoji-Mono-Plus.24.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setName(""); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        form1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 30, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Microsoft-Fluentui-Emoji-Mono-Plus.24.png"))); // NOI18N
        jLabel3.setText("              ");
        jLabel3.setToolTipText("");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        form1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 30, 20));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Colebemis-Feather-Plus.16.png"))); // NOI18N
        jLabel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        form1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 30, 30));

        javax.swing.GroupLayout themnhanhmausacLayout = new javax.swing.GroupLayout(themnhanhmausac);
        themnhanhmausac.setLayout(themnhanhmausacLayout);
        themnhanhmausacLayout.setHorizontalGroup(
            themnhanhmausacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        themnhanhmausacLayout.setVerticalGroup(
            themnhanhmausacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        form1.add(themnhanhmausac, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 230, 0));

        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel13.setText("Thương Hiệu");

        CBO_TH1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_TH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_TH1ActionPerformed(evt);
            }
        });

        jButton5.setText("jButton1");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton8.setText("jButton1");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("jButton1");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel14.setText("Thương Hiệu");

        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        lbl_magk.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 48)); // NOI18N
        lbl_magk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_magk.setToolTipText("");
        lbl_magk.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_add.setText("Thêm");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        jButton6.setText("jButton1");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        form2.setBackground(java.awt.Color.white);
        form2.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lý Sản Phẩm"));
        form2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setText("Số Lượng");
        form2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 56, 26));
        form2.add(TxtSoLuong1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 193, 26));

        jLabel16.setText("Giá Bán");
        form2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 56, 26));
        form2.add(txt_GiaBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 193, 26));

        jLabel17.setText("Màu Sắc");
        form2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 56, 30));

        jLabel18.setText("Chất Liệu");
        form2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 56, 30));

        jLabel19.setText("Thương Hiệu");
        form2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, 30));

        jLabel20.setText("Mô Tả");
        form2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 56, 30));

        lbl_anh1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        form2.add(lbl_anh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 310, 220));

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
        cbochatlieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbochatlieuActionPerformed(evt);
            }
        });
        form2.add(cbochatlieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 193, 30));

        txt_mota1.setColumns(20);
        txt_mota1.setRows(3);
        txt_mota1.setTabSize(1);
        jScrollPane2.setViewportView(txt_mota1);

        form2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 210, 170));
        form2.add(lblCheckSoLuong1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 230, 20));
        form2.add(lblCheckGiaBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 230, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Microsoft-Fluentui-Emoji-Mono-Plus.24.png"))); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.setName(""); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        form2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 30, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Microsoft-Fluentui-Emoji-Mono-Plus.24.png"))); // NOI18N
        jLabel5.setText("              ");
        jLabel5.setToolTipText("");
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        form2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 30, 20));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Colebemis-Feather-Plus.16.png"))); // NOI18N
        jLabel21.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        form2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 30, 30));

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

        cbothuonghieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbothuonghieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbothuonghieuActionPerformed(evt);
            }
        });
        form2.add(cbothuonghieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 193, 30));

        cbomausac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbomausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomausacActionPerformed(evt);
            }
        });
        form2.add(cbomausac, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 193, 30));

        jButton12.setText("jButton1");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("jButton1");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        CBO_TH1_check.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_TH1_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_TH1_checkActionPerformed(evt);
            }
        });

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Sản Phẩm"));

        lblbang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
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
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout color1Layout = new javax.swing.GroupLayout(color1);
        color1.setLayout(color1Layout);
        color1Layout.setHorizontalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(color1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(color1Layout.createSequentialGroup()
                        .addGroup(color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(form2, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(color1Layout.createSequentialGroup()
                                .addGap(807, 807, 807)
                                .addComponent(jLabel14))
                            .addGroup(color1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CBO_TH1_check, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(color1Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(lbl_magk, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        color1Layout.setVerticalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(color1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lbl_magk, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(form2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel14)
                .addGap(2, 2, 2)
                .addGroup(color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(color1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(color1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(color1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(color1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(color1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(CBO_TH1_check, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

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

    private void CBo_MSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBo_MSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBo_MSActionPerformed

    private void Cbo_chatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cbo_chatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cbo_chatLieuActionPerformed

    private void CBO_THActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_THActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_THActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void CBO_TH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_TH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_TH1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed

    }//GEN-LAST:event_btn_addActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

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
            //            linkAnh = file.getAbsolutePath();

        } catch (Exception e) {

            return;

        }
    }//GEN-LAST:event_btn_addAnh1ActionPerformed

    private void btn_deleteAnh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteAnh1ActionPerformed
        //        lbl_anh.setIcon(null);
        //        if (linkAnh != null) {
        //            linkAnh = null;
        //        } else {
        //            JOptionPane.showMessageDialog(this, "Chưa thêm ảnh");
        //        }
    }//GEN-LAST:event_btn_deleteAnh1ActionPerformed

    private void cbochatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbochatlieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbochatlieuActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void CBO_TH1_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_TH1_checkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_TH1_checkActionPerformed

    private void lblbangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbangMouseClicked
        index = lblbang.getSelectedRow();
        int a = (int) lblbang.getValueAt(index, 0);
        Gongkinh gk = gksv.selectByID(a);
        Show(gk);
    }//GEN-LAST:event_lblbangMouseClicked

    private void cbothuonghieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbothuonghieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbothuonghieuActionPerformed

    private void cbomausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomausacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbomausacActionPerformed

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
            java.util.logging.Logger.getLogger(GKCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GKCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GKCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GKCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GKCT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBO_TH;
    private javax.swing.JComboBox<String> CBO_TH1;
    private javax.swing.JComboBox<String> CBO_TH1_check;
    private javax.swing.JComboBox<String> CBo_MS;
    private javax.swing.JComboBox<String> Cbo_chatLieu;
    private javax.swing.JTextField TxtSoLuong;
    private javax.swing.JTextField TxtSoLuong1;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_addAnh;
    private javax.swing.JButton btn_addAnh1;
    private javax.swing.JButton btn_deleteAnh;
    private javax.swing.JButton btn_deleteAnh1;
    private javax.swing.JComboBox<String> cbochatlieu;
    private javax.swing.JComboBox<String> cbomausac;
    private javax.swing.JComboBox<String> cbothuonghieu;
    private com.raven.form.Color color1;
    private javax.swing.JPanel form1;
    private javax.swing.JPanel form2;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblCheckGiaBan;
    private javax.swing.JLabel lblCheckGiaBan1;
    private javax.swing.JLabel lblCheckSoLuong;
    private javax.swing.JLabel lblCheckSoLuong1;
    private javax.swing.JLabel lbl_anh;
    private javax.swing.JLabel lbl_anh1;
    private javax.swing.JLabel lbl_maGK;
    private javax.swing.JLabel lbl_magk;
    private javax.swing.JTable lblbang;
    private javax.swing.JPanel themnhanhmausac;
    private javax.swing.JPanel themnhanhmausac1;
    private javax.swing.JTextField txt_GiaBan;
    private javax.swing.JTextField txt_GiaBan1;
    private javax.swing.JTextArea txt_mota;
    private javax.swing.JTextArea txt_mota1;
    // End of variables declaration//GEN-END:variables
}
