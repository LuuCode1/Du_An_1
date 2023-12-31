/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.LoaiSP;
import model.SanPham;
import model.Thuonghieu;
import service.DBconnect;
import service.SP_Service;
import service.loaisp_service;
import service.thuonghieu_service;
import view.ThuongHieuForm;
import java.sql.*;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 *
 * @author Lenovo
 */
public class SanPhamForm extends javax.swing.JPanel {

    DefaultTableModel tbl_Model;
    private MainForm mainForm;
    SP_Service spSerVice = new SP_Service();
    SanPham sp = new SanPham();
    loaisp_service lspSerVice = new loaisp_service();
    LoaiSP lsp = new LoaiSP();
    thuonghieu_service Brandservice = new thuonghieu_service();
    Thuonghieu th = new Thuonghieu();
    public static String id;
    public static String tenTheLoai;
    int index = -1;
    Connection conn;
    PreparedStatement ps;
    String sql;
    ResultSet rs;
    long count, soTrang, trang = 1;

    public SanPhamForm(MainForm main) {
        this.mainForm = main;
        initComponents();
        initTable_QL_TK();
        CBo_LoaiSP();
        CBo_ThuongHieu();
        txt_search.setText("Tìm kiếm");
        //fillTable(spSerVice.getAllSP());
        CountDB();
        if (count % 5 == 0) {
            soTrang = count / 5;
        } else {
            soTrang = count / 5 + 1;
        }
        lbl_soTrang.setText("1/" + soTrang);
        loadData(1);
    }

    public void initTable_QL_TK() {
        tbl_Model = (DefaultTableModel) tbl_sp.getModel();
        String[] row = new String[]{
            "ID", "Mã San Pham", "Tên San Pham", "Loai San Pham", "Thương Hiệu"
        };
        tbl_Model.setColumnIdentifiers(row);
    }

    public void CountDB() {
        try {
            String query = "Select count(*) from san_pham";
            conn = DBconnect.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadData(long trang) {
        initTable_QL_TK();
        tbl_Model.getDataVector().removeAllElements();
        try {
            String query = "SELECT top 5 idsp,masp,tensp,lsp.tenloai_sp,th.tenThuongHieu\n"
                    + "from san_pham sp INNER JOIN\n"
                    + "loai_sp lsp ON lsp.idloai_sp = sp.idloai_sp INNER JOIN\n"
                    + "thuong_hieu th ON th.idThuongHieu = sp.idThuongHieu \n"
                    + "where idsp not in (Select top " + (trang * 5 - 5) + " idsp from san_pham)";
            conn = DBconnect.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Vector v = new Vector();
                Integer id = rs.getInt(1);
                String masp = rs.getString(2);
                String tensp = rs.getString(3);
                String tenloaisp = rs.getString(4);
                String tenth = rs.getString(5);
                v.add(id);
                v.add(masp);
                v.add(tensp);
                v.add(tenloaisp);
                v.add(tenth);
                tbl_Model.addRow(v);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("");
        }
    }

    void CBo_LoaiSP() {
        List<LoaiSP> listlsp = lspSerVice.FILL_TO_CBO_LOAISP();
        String[] cbo = new String[listlsp.size()];
        for (int i = 0; i < listlsp.size(); i++) {
            cbo[i] = listlsp.get(i).getTenLoaiSP();
        }
        cbo_loaiSP.setModel(new DefaultComboBoxModel<>(cbo));
    }

    void CBo_ThuongHieu() {
        List<Thuonghieu> listthuonghieu = Brandservice.FILL_TO_CBO_ThuongHieu();
        String[] cbo = new String[listthuonghieu.size()];
        for (int i = 0; i < listthuonghieu.size(); i++) {
            cbo[i] = listthuonghieu.get(i).getTenThuongHieu();

        }
        cbo_thuonghieu.setModel(new DefaultComboBoxModel<>(cbo));
    }

    void fillTable(List<SanPham> list) {
        tbl_Model = (DefaultTableModel) tbl_sp.getModel();
        tbl_Model.setRowCount(0);
        for (SanPham sp : list) {
            tbl_Model.addRow(sp.todata());
        }
    }

    public SanPham getModel_SP() {
        model.SanPham sp = new SanPham();
        sp.setMaSP(txt_ma.getText());
        sp.setTenSP(txt_ten.getText());
        LoaiSP lsp = lspSerVice.tenLoaiSP(cbo_loaiSP.getSelectedItem().toString());
        sp.setLoaisp(lsp);
        Thuonghieu th = Brandservice.tenThuongHieu(cbo_thuonghieu.getSelectedItem().toString());
        sp.setBrand(th);
        return sp;
    }

    public SanPham setModel_SP(SanPham sp) {
        lbl_id.setText(String.valueOf(sp.getIdSP()));
        txt_ma.setText(String.valueOf(sp.getMaSP()));
        txt_ten.setText(String.valueOf(sp.getTenSP()));
        cbo_loaiSP.setSelectedItem(sp.getLoaisp().getTenLoaiSP());
        cbo_thuonghieu.setSelectedItem(sp.getBrand().getTenThuongHieu());
        return sp;
    }

    public void Reset() {
        txt_ma.setText("");
        txt_ma.enable(true);
        txt_ten.setText("");
        lbl_id.setText("");
        cbo_loaiSP.setSelectedIndex(0);
        cbo_thuonghieu.setSelectedIndex(0);
        loadData(1);
    }

    public boolean CheckValidate() {
        String ma = txt_ma.getText();
        boolean checkma = ma.matches("^[a-zA-Z0-9]+$");
        if (txt_ma.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã san pham còn trống");
            return false;
        }else if (!checkma) {
            JOptionPane.showMessageDialog(this, "Mã san pham Không đúng");
            return false;
        }

        if (txt_ten.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên san pham còn trống");
            return false;
        }
        String input = txt_ten.getText();
        boolean isJavaClassName = input.matches("[\\p{Punct}&&[^a-zA-Z]]");

        if (isJavaClassName) {
            JOptionPane.showMessageDialog(this, "Tên Không hợp lệ.");
            return false;// Trả về false nếu không phải là tên lớp Java hợp lệ// Trả về true nếu là tên lớp Java hợp lệ
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_ten = new javax.swing.JTextField();
        txt_ma = new javax.swing.JTextField();
        lbl_id = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnSPChiTiet = new javax.swing.JButton();
        cbo_loaiSP = new javax.swing.JComboBox<>();
        cbo_thuonghieu = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sp = new javax.swing.JTable();
        txt_search = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lbl_soTrang = new javax.swing.JLabel();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("San Pham");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 17, 1047, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("ID:");

        jLabel3.setText("Tên sản phẩm:");

        jLabel4.setText("Mã sản phẩm:");

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

        cbo_loaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbo_thuonghieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_thuonghieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_thuonghieuItemStateChanged(evt);
            }
        });

        jLabel5.setText("Loai san pham");

        jLabel6.setText("Thuong Hieu");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Microsoft-Fluentui-Emoji-Mono-Plus.24.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Lam moi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_ten)
                    .addComponent(txt_ma)
                    .addComponent(lbl_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(cbo_loaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbo_thuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSPChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(lbl_id, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnLuu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSPChiTiet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbo_loaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_thuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 74, -1, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        tbl_sp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_sp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_spMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_sp);
        if (tbl_sp.getColumnModel().getColumnCount() > 0) {
            tbl_sp.getColumnModel().getColumn(0).setResizable(false);
            tbl_sp.getColumnModel().getColumn(1).setResizable(false);
            tbl_sp.getColumnModel().getColumn(2).setResizable(false);
            tbl_sp.getColumnModel().getColumn(3).setResizable(false);
            tbl_sp.getColumnModel().getColumn(4).setResizable(false);
        }

        txt_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_searchFocusLost(evt);
            }
        });
        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_searchMousePressed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        jButton3.setText("<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText(">");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lbl_soTrang.setText("jLabel7");

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
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(396, 396, 396)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(lbl_soTrang)
                .addGap(30, 30, 30)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(lbl_soTrang))
                .addGap(47, 47, 47))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 326, 1047, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        String search = "%" + txt_search.getText() + "%";
        List<model.SanPham> listsearch = spSerVice.seach(search);
        fillTable(listsearch);
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_searchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMousePressed
        txt_search.setText(null);
    }//GEN-LAST:event_txt_searchMousePressed

    private void txt_searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusLost
        if (txt_search.getText().isEmpty()) {
            txt_search.setText("Tìm Kiếm");
            loadData(1);
        }
    }//GEN-LAST:event_txt_searchFocusLost

    private void tbl_spMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_spMouseClicked
        index = tbl_sp.getSelectedRow();
        int id = (int) tbl_sp.getValueAt(index, 0);
        model.SanPham sp = spSerVice.selectByID(id);
        setModel_SP(sp);
    }//GEN-LAST:event_tbl_spMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (CheckValidate()) {
            try {
                SanPham sp = getModel_SP();
                if (spSerVice.selectByMa(sp.getMaSP()) != null) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại");
                } else {
                    if (spSerVice.addSP(sp) > 0) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                        CountDB();
                        if (count % 5 == 0) {
                            soTrang = count / 5;
                        } else {
                            soTrang = count / 5 + 1;
                        }
                        lbl_soTrang.setText("1/" + soTrang);
                        loadData(1);
                        Reset();
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập đúng hoặc chưa đầy đủ thông tin");
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (CheckValidate()) {
            int a = JOptionPane.showConfirmDialog(this, "Bạn muốn cập nhật sản phẩm này");

            if (a == 0) {

                SanPham sp = getModel_SP();
                if (spSerVice.updateSP(sp) > 0) {
                    JOptionPane.showMessageDialog(this, "Cap nhat thành công");
                    CountDB();
                    if (count % 5 == 0) {
                        soTrang = count / 5;
                    } else {
                        soTrang = count / 5 + 1;
                    }
                    lbl_soTrang.setText("1/" + soTrang);
                    loadData(1);
                    Reset();
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnSPChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPChiTietActionPerformed
        if (CheckValidate()) {
//            try {
//                id = lbl_id.getText();
//                mainForm.showForm(new SanPhamCTForm1(lbl_id.getText()));
//            } catch (Exception e) {
//                System.out.println(e.toString());
//            }
            try {
                id = lbl_id.getText();
                tenTheLoai = (String) cbo_loaiSP.getSelectedItem().toString();
                //name = tenTheLoai;
                if (tenTheLoai.equals("Gong Kinh")) {
                    mainForm.showForm(new SanPhamCTForm((lbl_id.getText())));
                } else {
                    mainForm.showForm(new SanPhamCTForm1((lbl_id.getText())));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnSPChiTietActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        ThuongHieuForm ql = new ThuongHieuForm();
        ql.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Add_thuonghieu dialog = new Add_thuonghieu(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
        CBo_ThuongHieu();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbo_thuonghieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_thuonghieuItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_thuonghieuItemStateChanged

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchFocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (trang > 1) {
            trang--;
            loadData(trang);
            lbl_soTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (trang < soTrang) {
            trang++;
            loadData(trang);
            lbl_soTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSPChiTiet;
    private javax.swing.JButton btnSua;
    private javax.swing.JComboBox<String> cbo_loaiSP;
    private javax.swing.JComboBox<String> cbo_thuonghieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JLabel lbl_soTrang;
    private javax.swing.JTable tbl_sp;
    private javax.swing.JTextField txt_ma;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_ten;
    // End of variables declaration//GEN-END:variables
}
