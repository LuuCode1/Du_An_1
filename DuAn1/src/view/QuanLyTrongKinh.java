/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.TrongKinh;
import model.chatLieu;
import model.mausac;
import model.thuonghieu;
import service.DBconnect;
import service.TrongKinhService;
import service.chatLieu_service;
import service.mausac_service;
import service.thuonghieu_service;
//l
/**
 *
 * @author leduc
 */
public class QuanLyTrongKinh extends javax.swing.JFrame {

    DefaultTableModel tbl_Model;
    String linkAnh = null;
    mausac color;
    mausac_service colorService = new mausac_service();
    chatLieu material;
    chatLieu_service materialService = new chatLieu_service();
    thuonghieu brand;
    thuonghieu_service brandService = new thuonghieu_service();
    TrongKinh tk;
    TrongKinhService tkService = new TrongKinhService();
    ArrayList<TrongKinh> arr = new ArrayList<TrongKinh>();

    /**
     * Creates new form QuanLyTrongKinh
     */
    public QuanLyTrongKinh() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        initTable_QL_TK();
        FILL_TO_CBO_MauSac();
        FILL_TO_CBO_ChatLieu();
        FILL_TO_CBO_ThuongHieu();
        fillTable(tkService.getAllTK());
    }

    public void initTable_QL_TK() {
        tbl_Model = (DefaultTableModel) tbl_QL_TK.getModel();
        String[] row = new String[]{
            "Mã Tròng Kính", "Tên Tròng Kính", "Màu Sắc", "Chất Liệu", "Thương Hiệu", "Giá Thành", "Độ Cận", "Số Lượng", "Ảnh", "Mô tả"
        };
        tbl_Model.setColumnIdentifiers(row);
    }

    // add item to cbo_mausacTK
    public void FILL_TO_CBO_MauSac() {
        try (Connection connection = DBconnect.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT idMauSac,maMauSac,tenMauSac FROM mau_sac");
            String[] values = new String[0];
            cbo_mausacTK.setModel(new DefaultComboBoxModel(values));
            while (rs.next()) {
                int idMauSac = rs.getInt(1);
                String maMauSac = rs.getString(2);
                String tenMauSac = rs.getString(3);
                cbo_mausacTK.addItem(tenMauSac);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // add item to cbo_mausacTK
    public void FILL_TO_CBO_ChatLieu() {
        try (Connection connection = DBconnect.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT idChatLieu,maChatLieu,tenChatLieu FROM chat_lieu");
            String[] values = new String[0];
            cbo_chatlieuTK.setModel(new DefaultComboBoxModel(values));
            while (rs.next()) {
                int idChatLieu = rs.getInt(1);
                String maChatLieu = rs.getString(2);
                String tenChatLieu = rs.getString(3);
                // todo change code
                cbo_chatlieuTK.addItem(tenChatLieu);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // add item to cbo_mausacTK
    public void FILL_TO_CBO_ThuongHieu() {
        try (Connection connection = DBconnect.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT idThuongHieu,maThuongHieu,tenThuongHieu FROM thuong_hieu");
            String[] values = new String[0];
            cbo_thuonghieuTK.setModel(new DefaultComboBoxModel(values));
            cbo_search_thuonghieu.setModel(new DefaultComboBoxModel(values));
            while (rs.next()) {
                Integer idThuongHieu = rs.getInt(1);
                String maThuongHieu = rs.getString(2);
                String tenThuongHieu = rs.getString(3);
                // todo change code
                cbo_thuonghieuTK.addItem(tenThuongHieu);
                cbo_search_thuonghieu.addItem(tenThuongHieu);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void fillTable(List<TrongKinh> list) {
        tbl_Model = (DefaultTableModel) tbl_QL_TK.getModel();
        tbl_Model.setRowCount(0);
        for (TrongKinh tk : list) {
            tbl_Model.addRow(tk.todata());
        }
    }

    public TrongKinh getModel_tbl_QL_TK() {
        TrongKinh tk = new TrongKinh();
        tk.setMaTrongKinh(String.valueOf(txt_maTK.getText()));
        tk.setTenTrongKinh(txt_tenTK.getText());
        mausac color = colorService.tenMauSac(cbo_mausacTK.getSelectedItem().toString());
        tk.setColor(color);
        chatLieu material = materialService.tenchatLieu(cbo_chatlieuTK.getSelectedItem().toString());
        tk.setMaterial(material);
        thuonghieu brand = brandService.tenThuongHieu(cbo_thuonghieuTK.getSelectedItem().toString());
        tk.setBrand(brand);
        tk.setGiathanh(Double.parseDouble(txt_giaTK.getText()));
        tk.setDocan(Double.parseDouble(txt_docan.getText()));
        tk.setSoluong(Integer.valueOf(txt_soluongTK.getText()));
        if (linkAnh == null) {
            tk.setHinhanh("No Avata");
        } else {
            tk.setHinhanh(linkAnh);
        }
        tk.setMota(txt_motaTK.getText());
        return tk;
    }

    public TrongKinh setModel_tbl_QL_TK(TrongKinh tk) {
        txt_maTK.setText(tk.getMaTrongKinh());
        txt_tenTK.setText(tk.getTenTrongKinh());
        cbo_chatlieuTK.setSelectedItem(tk.getMaterial().getTenChatLieu());
        cbo_mausacTK.setSelectedItem(tk.getColor().getTenMauSac());
        cbo_thuonghieuTK.setSelectedItem(tk.getBrand().getTenThuongHieu());
        txt_giaTK.setText(tk.getGiathanh() + "");
        txt_docan.setText(tk.getGiathanh() + "");
        txt_soluongTK.setText(tk.getSoluong() + "");
        txt_motaTK.setText(tk.getMota());
        return tk;
    }

    public boolean CheckValidate_QL_TK() {
        if (txt_maTK.getText().trim().isEmpty()) {
            lbl_validate_maTK.setText("Vui lòng nhập Mã Tròng Kính");
            lbl_validate_maTK.setForeground(Color.RED);
            return false;

        } else {
            lbl_validate_maTK.setText(null);
        }

// Kiểm tra trường Tên Gọng Kính
        if (txt_tenTK.getText().trim().isEmpty()) {
            lbl_validate_tenTK.setText("Vui lòng nhập Tên Tròng Kính");
            lbl_validate_tenTK.setForeground(Color.RED);
            return false;

        } else {
            lbl_validate_tenTK.setText(null);
        }
        
        
        //soluong
        if (txt_soluongTK.getText().trim().isEmpty()) {
            lbl_validate_soluongTK.setText("Vui lòng nhập số lượng");
            lbl_validate_soluongTK.setForeground(Color.RED);
            return false;

        } else {
            lbl_validate_soluongTK.setText(null);
        }
        
        if (Integer.parseInt(txt_soluongTK.getText()) < 0) {
            lbl_validate_soluongTK.setText("Số lượng phải là số nguyên");
            lbl_validate_soluongTK.setForeground(Color.RED);
            return false;

        } else {
            lbl_validate_soluongTK.setText(null);
        }
        
        try {
            int soLuong = Integer.parseInt(txt_soluongTK.getText());

        } catch (NumberFormatException ex) {
            lbl_validate_soluongTK.setText("Số lượng phải là số nguyên");
            lbl_validate_soluongTK.setForeground(Color.RED);
            return false;
        }

        //gia
        if (txt_giaTK.getText().trim().isEmpty()) {
            lbl_validate_giabanTk.setText("Vui lòng nhập giá thành");
            lbl_validate_giabanTk.setForeground(Color.RED);
            return false;

        } else {
            lbl_validate_giabanTk.setText(null);
        }
        
        try {
            float giaBan = Float.parseFloat(txt_giaTK.getText());

        } catch (NumberFormatException ex) {
            lbl_validate_giabanTk.setText("Giá bán phải là số thực");
            lbl_validate_giabanTk.setForeground(Color.RED);

            return false;
        }
        
        //docan
        if (txt_docan.getText().trim().isEmpty()) {
            lbl_validate_docanTK.setText("Vui lòng nhập giá thành");
            lbl_validate_docanTK.setForeground(Color.RED);
            return false;

        } else {
            lbl_validate_docanTK.setText(null);
        }
        
        try {
            float doCan = Float.parseFloat(txt_docan.getText());

        } catch (NumberFormatException ex) {
            lbl_validate_docanTK.setText("Độ cận phải là số thực");
            lbl_validate_docanTK.setForeground(Color.RED);

            return false;
        }
       
        return true;
    }

    public void Reset() {
        txt_maTK.setText("");
        txt_tenTK.setText("");
        cbo_mausacTK.setSelectedIndex(0);
        cbo_chatlieuTK.setSelectedIndex(0);
        cbo_thuonghieuTK.setSelectedIndex(0);
        txt_giaTK.setText("");
        txt_docan.setText("");
        txt_soluongTK.setText("");
        txt_motaTK.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_QL_TK = new javax.swing.JTable();
        btn_new = new javax.swing.JButton();
        btn_add_TK = new javax.swing.JButton();
        btn_update_TK = new javax.swing.JButton();
        btn_delete_TK = new javax.swing.JButton();
        txt_sreach_TK = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbo_search_thuonghieu = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_maTK = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_tenTK = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_soluongTK = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_docan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbl_anh_TK = new javax.swing.JLabel();
        btn_addAnh = new javax.swing.JButton();
        btn_deleteAnh = new javax.swing.JButton();
        cbo_mausacTK = new javax.swing.JComboBox<>();
        cbo_chatlieuTK = new javax.swing.JComboBox<>();
        cbo_thuonghieuTK = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_motaTK = new javax.swing.JTextArea();
        lbl_validate_maTK = new javax.swing.JLabel();
        lbl_validate_tenTK = new javax.swing.JLabel();
        lbl_validate_soluongTK = new javax.swing.JLabel();
        lbl_validate_docanTK = new javax.swing.JLabel();
        lbl_add_thuonghieu = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_giaTK = new javax.swing.JTextField();
        lbl_validate_giabanTk = new javax.swing.JLabel();
        lbl_add_chatlieu = new javax.swing.JLabel();
        lbl_add_mausac = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(930, 620));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Sản Phẩm"));

        tbl_QL_TK.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_QL_TK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_QL_TKMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_QL_TK);

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

        btn_new.setText("Làm Mới");
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });

        btn_add_TK.setText("Thêm");
        btn_add_TK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_TKActionPerformed(evt);
            }
        });

        btn_update_TK.setText("Sửa");
        btn_update_TK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_TKActionPerformed(evt);
            }
        });

        btn_delete_TK.setText("Xóa");
        btn_delete_TK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_TKActionPerformed(evt);
            }
        });

        txt_sreach_TK.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_sreach_TKFocusLost(evt);
            }
        });
        txt_sreach_TK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_sreach_TKMousePressed(evt);
            }
        });
        txt_sreach_TK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sreach_TKActionPerformed(evt);
            }
        });
        txt_sreach_TK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_sreach_TKKeyReleased(evt);
            }
        });

        jLabel1.setText("Thương Hiệu");

        cbo_search_thuonghieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_search_thuonghieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_search_thuonghieuItemStateChanged(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lý Sản Phẩm"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Mã Gọng Kính");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 40, -1, 26));

        txt_maTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maTKActionPerformed(evt);
            }
        });
        jPanel2.add(txt_maTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 40, 193, 26));

        jLabel5.setText("Tên Gọng Kính");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 96, -1, 26));

        txt_tenTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenTKActionPerformed(evt);
            }
        });
        jPanel2.add(txt_tenTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 96, 193, 26));

        jLabel6.setText("Số Lượng");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 151, 56, 26));
        jPanel2.add(txt_soluongTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 151, 193, 26));

        jLabel7.setText("Độ Cận");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 56, 26));
        jPanel2.add(txt_docan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 193, 26));

        jLabel8.setText("Màu Sắc");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 56, 26));

        jLabel9.setText("Chất Liệu");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 56, 26));

        jLabel10.setText("Thương Hiệu");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, 26));

        jLabel11.setText("Mô Tả");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 56, 26));

        lbl_anh_TK.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_anh_TK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_anh_TKMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_anh_TK, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 202, 202));

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

        cbo_mausacTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_mausacTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_mausacTKActionPerformed(evt);
            }
        });
        jPanel2.add(cbo_mausacTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 193, 26));

        cbo_chatlieuTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_chatlieuTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_chatlieuTKActionPerformed(evt);
            }
        });
        jPanel2.add(cbo_chatlieuTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 193, 26));

        cbo_thuonghieuTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_thuonghieuTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_thuonghieuTKActionPerformed(evt);
            }
        });
        jPanel2.add(cbo_thuonghieuTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 193, 26));

        txt_motaTK.setColumns(20);
        txt_motaTK.setRows(3);
        txt_motaTK.setTabSize(1);
        jScrollPane1.setViewportView(txt_motaTK);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, 210, 80));
        jPanel2.add(lbl_validate_maTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 68, 193, 20));
        jPanel2.add(lbl_validate_tenTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 123, 193, 20));
        jPanel2.add(lbl_validate_soluongTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 179, 193, 20));
        jPanel2.add(lbl_validate_docanTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 193, 20));

        lbl_add_thuonghieu.setText("+");
        lbl_add_thuonghieu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_add_thuonghieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_add_thuonghieuMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_add_thuonghieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 30, 20));

        jLabel13.setText("Giá Bán");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 207, 56, 26));
        jPanel2.add(txt_giaTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 207, 193, 26));
        jPanel2.add(lbl_validate_giabanTk, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 235, 193, 20));

        lbl_add_chatlieu.setText("+");
        lbl_add_chatlieu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_add_chatlieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_add_chatlieuMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_add_chatlieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 30, 20));

        lbl_add_mausac.setText("+");
        lbl_add_mausac.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_add_mausac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_add_mausacMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_add_mausac, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 30, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(710, 710, 710)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btn_add_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btn_update_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btn_delete_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btn_new, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txt_sreach_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cbo_search_thuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_add_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_update_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_delete_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_new, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sreach_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_search_thuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_QL_TKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_QL_TKMouseClicked
        int position = tbl_QL_TK.getSelectedRow();
        System.out.println(position);
        if (position >= 0) {
            setModel_tbl_QL_TK(tkService.getAllTK().get(position));
        }

    }//GEN-LAST:event_tbl_QL_TKMouseClicked

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
        btn_update_TK.setEnabled(false);
        btn_delete_TK.setEnabled(false);
        btn_add_TK.setEnabled(true);
        txt_maTK.setEnabled(true);
        Reset();
    }//GEN-LAST:event_btn_newActionPerformed

    private void btn_add_TKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_TKActionPerformed
        if (CheckValidate_QL_TK()) {
            try {
                TrongKinh tk = getModel_tbl_QL_TK();

                if (tkService.addTrongKinh(tk) > 0) {
                    fillTable(tkService.getAllTK());
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    Reset();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập đúng hoặc chưa đầy đủ thông tin");
            }

        }

    }//GEN-LAST:event_btn_add_TKActionPerformed

    private void btn_update_TKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_TKActionPerformed
        if (CheckValidate_QL_TK()) {
            try {
                // TODO add your handling code here:
                tkService.update(getModel_tbl_QL_TK());
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            JOptionPane.showMessageDialog(this, "Update thanh cong");
            fillTable(tkService.getAllTK());
            Reset();
        }

    }//GEN-LAST:event_btn_update_TKActionPerformed

    private void btn_delete_TKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_TKActionPerformed
        if (CheckValidate_QL_TK()) {
            tkService.Delete(txt_maTK.getText());
            JOptionPane.showMessageDialog(this, "Đã xóa thành công");
            fillTable(tkService.getAllTK());
            Reset();
        }

    }//GEN-LAST:event_btn_delete_TKActionPerformed

    private void txt_sreach_TKFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_sreach_TKFocusLost
        if (txt_sreach_TK.getText().isEmpty()) {
            txt_sreach_TK.setText("Tìm kiếm");
            fillTable(tkService.getAllTK());
        }
    }//GEN-LAST:event_txt_sreach_TKFocusLost

    private void txt_sreach_TKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_sreach_TKMousePressed
        txt_sreach_TK.setText(null);
    }//GEN-LAST:event_txt_sreach_TKMousePressed

    private void txt_sreach_TKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sreach_TKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sreach_TKActionPerformed

    private void txt_sreach_TKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sreach_TKKeyReleased
        try {
            String check = '%' + txt_sreach_TK.getText() + '%';
            if (txt_sreach_TK.getText().isEmpty()) {
                fillTable(tkService.getAllTK());
            } else {
                List<TrongKinh> listds = tkService.seach(check);
                fillTable(listds);
            }
        } catch (Exception e) {
            return;
        }
    }//GEN-LAST:event_txt_sreach_TKKeyReleased

    private void cbo_search_thuonghieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_search_thuonghieuItemStateChanged
        try {
            String tenthuonghieu = cbo_search_thuonghieu.getSelectedItem().toString();
            List<TrongKinh> list = tkService.search_Cbo_thuonghieu(tenthuonghieu);
            fillTable(list);
        } catch (Exception e) {
            return;
        }
    }//GEN-LAST:event_cbo_search_thuonghieuItemStateChanged

    private void txt_maTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maTKActionPerformed

    private void txt_tenTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenTKActionPerformed

    private void btn_addAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addAnhActionPerformed
        try {

            JFileChooser jfc = new JFileChooser();

            jfc.showOpenDialog(null);

            File file = jfc.getSelectedFile();

            Image img = ImageIO.read(file);
            int width = lbl_anh_TK.getWidth();
            int height = lbl_anh_TK.getHeight();
            Image resizedImage = img.getScaledInstance(width, height, 0);
            lbl_anh_TK.setIcon(new ImageIcon(resizedImage));
            linkAnh = file.getAbsolutePath();

        } catch (Exception e) {

            return;

        }
    }//GEN-LAST:event_btn_addAnhActionPerformed

    private void btn_deleteAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteAnhActionPerformed
        lbl_anh_TK.setIcon(null);
        if (linkAnh != null) {
            linkAnh = null;
        } else {
            JOptionPane.showMessageDialog(this, "Chưa thêm ảnh");
        }
    }//GEN-LAST:event_btn_deleteAnhActionPerformed

    private void cbo_mausacTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_mausacTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_mausacTKActionPerformed

    private void cbo_chatlieuTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_chatlieuTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_chatlieuTKActionPerformed

    private void cbo_thuonghieuTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_thuonghieuTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_thuonghieuTKActionPerformed

    private void lbl_add_thuonghieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_add_thuonghieuMouseClicked
        ThuongHieuForm thf = new ThuongHieuForm();
        thf.setVisible(true);
        FILL_TO_CBO_ThuongHieu();
    }//GEN-LAST:event_lbl_add_thuonghieuMouseClicked

    private void lbl_anh_TKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_anh_TKMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_lbl_anh_TKMouseClicked

    private void lbl_add_chatlieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_add_chatlieuMouseClicked
        // TODO add your handling code here:
        ChatLieuForm clf = new ChatLieuForm();
        clf.setVisible(true);
        FILL_TO_CBO_ChatLieu();
    }//GEN-LAST:event_lbl_add_chatlieuMouseClicked

    private void lbl_add_mausacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_add_mausacMouseClicked
        // TODO add your handling code here:
        MauSacForm msf = new MauSacForm();
        msf.setVisible(true);
        FILL_TO_CBO_MauSac();
    }//GEN-LAST:event_lbl_add_mausacMouseClicked

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
            java.util.logging.Logger.getLogger(QuanLyTrongKinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyTrongKinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyTrongKinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyTrongKinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyTrongKinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addAnh;
    private javax.swing.JButton btn_add_TK;
    private javax.swing.JButton btn_deleteAnh;
    private javax.swing.JButton btn_delete_TK;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_update_TK;
    private javax.swing.JComboBox<String> cbo_chatlieuTK;
    private javax.swing.JComboBox<String> cbo_mausacTK;
    private javax.swing.JComboBox<String> cbo_search_thuonghieu;
    private javax.swing.JComboBox<String> cbo_thuonghieuTK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_add_chatlieu;
    private javax.swing.JLabel lbl_add_mausac;
    private javax.swing.JLabel lbl_add_thuonghieu;
    private javax.swing.JLabel lbl_anh_TK;
    private javax.swing.JLabel lbl_validate_docanTK;
    private javax.swing.JLabel lbl_validate_giabanTk;
    private javax.swing.JLabel lbl_validate_maTK;
    private javax.swing.JLabel lbl_validate_soluongTK;
    private javax.swing.JLabel lbl_validate_tenTK;
    private javax.swing.JTable tbl_QL_TK;
    private javax.swing.JTextField txt_docan;
    private javax.swing.JTextField txt_giaTK;
    private javax.swing.JTextField txt_maTK;
    private javax.swing.JTextArea txt_motaTK;
    private javax.swing.JTextField txt_soluongTK;
    private javax.swing.JTextField txt_sreach_TK;
    private javax.swing.JTextField txt_tenTK;
    // End of variables declaration//GEN-END:variables
}
