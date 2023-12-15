/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NguoiDung;
import service.NguoiDung_Service;

/**
 *
 * @author Lenovo
 */
public class NhanVienForm extends javax.swing.JPanel {

    NguoiDung nd = new NguoiDung();
    NguoiDung_Service ndsv = new NguoiDung_Service();
    DefaultTableModel model;
    int index = -1;

    /**
     * Creates new form NhanVienForm
     */
    public NhanVienForm() {
        initComponents();
        fillTable(ndsv.SelectAll());
        txtTimKiem.setText("Tìm Kiếm");
        Nametag();

    }

    void Nametag() {
        String[] table2 = {"Mã Nhân Viên", "Tên Nhân Viên", "Ngày Sinh", "Số Điện Thoại", "Mật Khẩu", "Giới Tính", "Email", "Vai Trò"};
        for (int i = 0; i < table2.length; i++) {
            tblNhanVien.getColumnModel().getColumn(i).setHeaderValue(table2[i]);
        }
    }

    void fillTable(List<NguoiDung> list) {
        model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        for (NguoiDung nguoiDung : list) {
            model.addRow(nguoiDung.todata_NguoiDung());
        }
    }

    void Show(NguoiDung nd1) {
        NguoiDung nd = nd1;
        txtMaNV.setText(nd.getMaND());
        txtTenNV.setText(nd.getTenND());
        Date_ngaysinh.setDate(nd.getNgaySinh());
        txtEmail.setText(nd.getEmail());
        txtMatKhau.setText(nd.getMatKhau());
        txtSoDT.setText(String.valueOf(nd.getSDT()));
        if (nd.getGioiTinh() == 0) {
            rbo_Nam.setSelected(true);
        } else {
            rbo_Nu.setSelected(true);
        }
        if (nd.getVaiTro() == 0) {
            rbo_master.setSelected(true);
        } else {
            rbo_newbie.setSelected(true);
        }
    }

    NguoiDung Read() {
        nd.setMaND(txtMaNV.getText());
        nd.setTenND(txtTenNV.getText());
        nd.setNgaySinh(Date_ngaysinh.getDate());
        nd.setSDT(txtSoDT.getText());
        nd.setMatKhau(txtMatKhau.getText());
        nd.setEmail(txtEmail.getText());
        if (rbo_Nam.isSelected()) {
            nd.setGioiTinh(0);
        } else {
            nd.setGioiTinh(1);
        }
        if (rbo_master.isSelected()) {
            nd.setVaiTro(0);
        } else {
            nd.setVaiTro(1);
        }
        nd.setTrangthai(0);
        return nd;

    }

    NguoiDung change_trangThai() {
        nd.setMaND(txtMaNV.getText());
        if (nd.getTrangthai() == 0) {
            nd.setTrangthai(1);
        } else if (nd.getTrangthai() == 1) {
            nd.setTrangthai(0);
        }
        return nd;
    }

    void reset() {
        txtEmail.setText(null);
        txtMaNV.setText(null);
        txtMatKhau.setText(null);
        txtSoDT.setText(null);
        txtTenNV.setText(null);
        Date_ngaysinh.setDate(null);
        rbo_Nam.setSelected(true);
        rbo_master.setSelected(true);
        txtMaNV.setEnabled(true);
        checkdate.setText(null);
        checkemail.setText(null);
        checkma.setText(null);
        checkpass.setText(null);
        checkphone.setText(null);
        checkten.setText(null);
    }

    boolean check() {
        boolean isValid = true;
        String ma = txtMaNV.getText();
        boolean macheck = ma.matches("^[a-zA-Z0-9]+$");
        if (txtMaNV.getText().trim().isEmpty()) {
            checkma.setText("Vui lòng nhập mã nhân viên");
            checkma.setForeground(java.awt.Color.RED);
            isValid = false;
        } else if (!macheck) {
            checkma.setText("Mã Nhân viên không hợp lệ");
            checkma.setForeground(java.awt.Color.RED);
            isValid = false;
        } else {
            checkma.setText(null);
        }
        String input = txtTenNV.getText();
        boolean isJavaClassName = input.matches("^[0-9\\-.,@!#$%^&*()<>?*-~`+Ee\\s]+$");

        if (txtTenNV.getText().trim().isEmpty()) {
            checkten.setText("Vui lòng nhập tên nhân viên");
            checkten.setForeground(java.awt.Color.RED);
            isValid = false;
        } else if (isJavaClassName) {
            checkten.setText("Tên Không hợp lệ");
            checkten.setForeground(java.awt.Color.RED);
            isValid = false;
        } else {
            checkten.setText(null);
        }

        Date ngaythang = Date_ngaysinh.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        if (ngaythang == null) {
            checkdate.setText("Vui lòng chọn ngày sinh");
            checkdate.setForeground(java.awt.Color.RED);
            isValid = false;
        } else {
            if (sdf.format(ngaythang).isEmpty()) {
                checkdate.setText("Vui lòng chọn ngày sinh");
                checkdate.setForeground(java.awt.Color.RED);
                isValid = false;
            } else {
                checkdate.setText(null);
            }
        }
        if (txtSoDT.getText().trim().isEmpty()) {
            checkphone.setText("Vui lòng nhập số điện thoại");
            checkphone.setForeground(java.awt.Color.RED);
            isValid = false;
        } else {
            if (!Pattern.matches("^09[0-9]{8}$|^03[0-9]{8}$", txtSoDT.getText())) {
                checkphone.setText("Số điện thoại phải bắt đầu bằng 09 hoặc 03");
                checkphone.setForeground(java.awt.Color.RED);
                isValid = false;
            } else {
                if (!Pattern.matches("[0-9]{10}", txtSoDT.getText())) {
                    checkphone.setText("Số điện thoại không hợp lệ");
                    checkphone.setForeground(java.awt.Color.RED);
                    isValid = false;
                } else {
                    checkphone.setText(null);
                }
            }
        }
        if (txtMatKhau.getText().trim().isEmpty()) {
            checkpass.setText("Vui lòng nhập mật khẩu");
            checkpass.setForeground(java.awt.Color.RED);
            isValid = false;
        } else {
            if (txtMatKhau.getText().length() < 6) {
                checkpass.setText("Mật khẩu phải có ít nhất 6 ký tự");
                checkpass.setForeground(java.awt.Color.RED);
                isValid = false;
            } else {
                if (!txtMatKhau.getText().matches("^[a-zA-Z0-9]+$")) {
                    checkpass.setText("Mật khẩu chỉ được chứa chữ, chữ hoa và số");
                    checkpass.setForeground(java.awt.Color.RED);
                    isValid = false;
                } else {
                    checkpass.setText(null);
                }
            }
        }
        if (txtEmail.getText().trim().isEmpty()) {
            checkemail.setText("Vui lòng nhập email");
            checkemail.setForeground(java.awt.Color.RED);
            isValid = false;
        } else {
            if (!Pattern.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$", txtEmail.getText())) {
                checkemail.setText("Email không hợp lệ");
                checkemail.setForeground(java.awt.Color.RED);
                isValid = false;
            } else {
                checkemail.setText(null);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollBar1 = new javax.swing.JScrollBar();
        canvas1 = new java.awt.Canvas();
        list1 = new java.awt.List();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rbo_Nam = new javax.swing.JRadioButton();
        rbo_Nu = new javax.swing.JRadioButton();
        rbo_master = new javax.swing.JRadioButton();
        rbo_newbie = new javax.swing.JRadioButton();
        txtMaNV = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Date_ngaysinh = new com.toedter.calendar.JDateChooser();
        checkemail = new javax.swing.JLabel();
        checkma = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        checkten = new javax.swing.JLabel();
        checkdate = new javax.swing.JLabel();
        checkpass = new javax.swing.JLabel();
        checkphone = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        lblPage = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý nhân viên"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Name");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));
        jPanel2.add(txtTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 300, -1));

        jLabel2.setText("Date");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel3.setText("Mật Khẩu");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));
        jPanel2.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 300, -1));
        jPanel2.add(txtSoDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 302, -1));

        jLabel5.setText("Số điện thoại");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        jLabel6.setText("Email");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 37, -1));
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 302, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("giới tính");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, 20));

        jLabel8.setText("Vai trò");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 60, 20));

        buttonGroup1.add(rbo_Nam);
        rbo_Nam.setSelected(true);
        rbo_Nam.setText("Nam");
        rbo_Nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbo_NamActionPerformed(evt);
            }
        });
        jPanel4.add(rbo_Nam, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        buttonGroup1.add(rbo_Nu);
        rbo_Nu.setText("Nữ");
        jPanel4.add(rbo_Nu, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        buttonGroup2.add(rbo_master);
        rbo_master.setText("Quản Lý");
        jPanel4.add(rbo_master, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        buttonGroup2.add(rbo_newbie);
        rbo_newbie.setSelected(true);
        rbo_newbie.setText("Nhân Viên");
        jPanel4.add(rbo_newbie, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 400, 90));
        jPanel2.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 300, -1));

        jLabel9.setText("Mã số");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        Date_ngaysinh.setDateFormatString("dd/MM/yyyy");
        jPanel2.add(Date_ngaysinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 340, -1));
        jPanel2.add(checkemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 300, 20));
        jPanel2.add(checkma, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 300, 20));
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 300, 20));
        jPanel2.add(checkten, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 300, 20));
        jPanel2.add(checkdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 320, 30));
        jPanel2.add(checkpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 300, 30));
        jPanel2.add(checkphone, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 300, 20));

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

        btnReset.setText("Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusLost(evt);
            }
        });
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTimKiemMousePressed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên")));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jButton2.setText(">");

        lblPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPage.setText("1");

        jButton3.setText("<");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(lblPage)
                    .addComponent(jButton3))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1058, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 741, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 10, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 11, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (check()) {
            NguoiDung nd = Read();
            if (ndsv.Check_MaNV(nd.getMaND()) != null) {
                JOptionPane.showMessageDialog(this, "Mã Nhân Viên Đã Tồn Tại");
            } else {
                if (ndsv.Insert(nd) > 0) {
                    JOptionPane.showMessageDialog(this, "Thành công");
                    fillTable(ndsv.SelectAll());

//                    final String username = "hall3baycup@gmail.com";
//                    final String password = "nobita123z";
//
//                    Properties prop = new Properties();
//                    prop.put("mail.smtp.host", "smtp.gmail.com");
//                    prop.put("mail.smtp.port", "587");
//                    prop.put("mail.smtp.auth", "true");
//                    prop.put("mail.smtp.starttls.enable", "true"); //TLS
//
//                    Session session = Session.getInstance(prop,
//                            new javax.mail.Authenticator() {
//                        protected PasswordAuthentication getPasswordAuthentication() {
//                            return new PasswordAuthentication(username, password);
//                        }
//                    });
//
//                    try {
//
//                        Message message = new MimeMessage(session);
//                        message.setFrom(new InternetAddress("bheos72@gmail.com"));
//                        message.setRecipients(
//                                Message.RecipientType.TO,
//                                InternetAddress.parse(txtEmail.getText())
//                        );
//                        message.setSubject("Chào mừng " + txtTenNV.getText() + " tham gia hệ thống");
//                        message.setText("Kính chào " + txtTenNV.getText() + ",\n\n"
//                                + "Chúng tôi xin gửi tới bạn thông tin tài khoản để truy cập hệ thống:\n"
//                                + "- Tên đăng nhập: " + txtEmail.getText() + "\n"
//                                + "- Mật khẩu: " + txtMatKhau.getText() + "\n\n"
//                                + "Nếu cần hỗ trợ gì thêm, vui lòng liên hệ với bộ phận hỗ trợ.\n\n"
//                                + "Trân trọng,\n"
//                                + "Ban quản trị hệ thống");
//
//                        Transport.send(message);
//
//                        System.out.println("Done");
//
//                    } catch (MessagingException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }

    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (check()) {
            int test = JOptionPane.showConfirmDialog(this, "Bạn muốn thay đổi thông tin ");
            if (test == 0) {

                txtMaNV.setEnabled(true);
                NguoiDung nd = Read();
                if (ndsv.update(nd, nd.getMaND()) > 0) {
                    fillTable(ndsv.SelectAll());
                    reset();
                    JOptionPane.showMessageDialog(this, "Thành công");
                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
//        btnSua.setEnabled(false);
//        btnDTT.setEnabled(false);
//        btnLuu.setEnabled(true);
//        txtTenNV.setEnabled(true);
//        Date_ngaysinh.setDate(null);
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        if (txtTimKiem.getText().isEmpty()) {
            fillTable(ndsv.SelectAll());
            txtTimKiem.setText("Tìm Kiếm");
        }
    }//GEN-LAST:event_txtTimKiemFocusLost

    private void txtTimKiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMousePressed
        txtTimKiem.setText(null);
    }//GEN-LAST:event_txtTimKiemMousePressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String a = "%" + txtTimKiem.getText() + "%";
        if (ndsv.check_All(a) == null) {
            fillTable(ndsv.SelectAll());
        } else {
            List<NguoiDung> nd3 = ndsv.check_All(a);
            fillTable(nd3);
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        reset();
        txtMaNV.setEnabled(false);
        index = tblNhanVien.getSelectedRow();
        String manv = tblNhanVien.getValueAt(index, 0).toString();
        NguoiDung nd2 = ndsv.Check_MaNV(manv);
        Show(nd2);
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void rbo_NamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbo_NamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbo_NamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date_ngaysinh;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private java.awt.Canvas canvas1;
    private javax.swing.JLabel checkdate;
    private javax.swing.JLabel checkemail;
    private javax.swing.JLabel checkma;
    private javax.swing.JLabel checkpass;
    private javax.swing.JLabel checkphone;
    private javax.swing.JLabel checkten;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPage;
    private java.awt.List list1;
    private javax.swing.JRadioButton rbo_Nam;
    private javax.swing.JRadioButton rbo_Nu;
    private javax.swing.JRadioButton rbo_master;
    private javax.swing.JRadioButton rbo_newbie;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
