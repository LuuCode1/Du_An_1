/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.GioHang;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.NguoiDung;
import model.SPChiTiet;
import service.HoaDon_Service;
import service.SanPhamService;

/**
 *
 * @author Lenovo
 */
public class BanHangForm extends javax.swing.JFrame {

    HoaDon hd = new HoaDon();
    HoaDon_Service HDSV = new HoaDon_Service();
    private DefaultTableModel modelGioHang;
    private DefaultTableModel modelSanPham;
    HoaDon_Service hdSer = new HoaDon_Service();
    private List<GioHang> listGioHang = new ArrayList<>();
    SanPhamService spSer = new SanPhamService();
    DefaultTableModel model;
    SPChiTiet spct1 = new SPChiTiet();
    int index = -1;
    int trangthai = 0;
    double tongtien = 0;
    int KhachHang = 0;
    int NguoiDung = 0;

    /**
     * Creates new form BanHangForm
     */
    public BanHangForm() {
        initComponents();
        initTable();
        configTolCol();
        modelGioHang = (DefaultTableModel) tbl_GioHang.getModel();
        fillTable(HDSV.Select());
        getListSP();

    }

    void initTable() {
        String[] table1 = {"MãHD", "TenNV", "Ngày tạo", "Tên KH"};
        for (int i = 0; i < table1.length; i++) {
            tblHoaDon.getColumnModel().getColumn(i).setHeaderValue(table1[i]);
        }
        String[] table2 = {"Mã SP", "Tên SP", "Chất liệu", "Màu sắc", "Thương hiệu", "SL", "Đơn Giá", "Thành Tiền"};
        for (int i = 0; i < table2.length; i++) {
            tbl_GioHang.getColumnModel().getColumn(i).setHeaderValue(table2[i]);
        }
        String[] table4 = {"Loại hàng", "Mã SP", "Tên SP", "Chất liệu", "Màu sắc", "Thương hiệu", "Độ cận", "Giá thành", "Số Lượng", "Mô tả"};
        for (int i = 0; i < table4.length; i++) {
            tbl_sanpham.getColumnModel().getColumn(i).setHeaderValue(table4[i]);
        }
    }

    public void configTolCol() {
        this.tbl_GioHang.getColumnModel().getColumn(0).setPreferredWidth(49);
        this.tbl_GioHang.getColumnModel().getColumn(1).setPreferredWidth(129);
        this.tbl_GioHang.getColumnModel().getColumn(2).setPreferredWidth(66);
        this.tbl_GioHang.getColumnModel().getColumn(3).setPreferredWidth(74);
        this.tbl_GioHang.getColumnModel().getColumn(4).setPreferredWidth(92);
        this.tbl_GioHang.getColumnModel().getColumn(5).setPreferredWidth(45);
        this.tbl_GioHang.getColumnModel().getColumn(6).setPreferredWidth(80);
        this.tbl_GioHang.getColumnModel().getColumn(7).setPreferredWidth(85);
    }

    private void getListSP() {
        model = (DefaultTableModel) tbl_sanpham.getModel();
        model.setRowCount(0);
        List<SPChiTiet> getList = spSer.getListSPCT("Đang bán");
        Object[] row = new Object[10];
        for (SPChiTiet x : getList) {
            row[0] = x.getLoai().getTenLoaiSP();
            row[1] = x.getSp().getMaSP();
            row[2] = x.getSp().getTenSP();
            row[3] = x.getColor().getTenMauSac();
            row[4] = x.getMaterial().getTenChatLieu();
            row[5] = x.getSp().getBrand().getTenThuongHieu();
            row[6] = x.getDoCan();
            row[7] = String.format("%.0f", x.getGiathanh());
            row[8] = x.getSoluong();
            row[9] = x.getMota();
            model.addRow(row);
        }
    }

    void fillTable(List<HoaDon> list) {
        if (hd.getTrangThai() == 0) {
            model = (DefaultTableModel) tblHoaDon.getModel();
            model.setRowCount(0);
            for (HoaDon hoaDon : list) {
                model.addRow(hoaDon.todata_HD());
            }
        }

    }

    HoaDon Read() {
        Random random = new Random();
        HashSet<Character> charSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        while (charSet.size() < 5) {
            int choice = random.nextInt(2);
            char c;
            if (choice == 0) {
                // Ký tự in hoa 
                c = (char) (random.nextInt(26) + 'A');
            } else {
                // Chữ số
                c = (char) (random.nextInt(10) + '0');
            }

            if (!charSet.contains(c)) {
                charSet.add(c);
                sb.append(c);
            }
        }

        String randomString = sb.toString();
        HoaDon hd = new HoaDon();
        hd.setMahD(randomString);
        hd.setTrangThai(0);
        return hd;
    }

    public void getListGioHang() {
        modelGioHang = (DefaultTableModel) tbl_GioHang.getModel();
        modelGioHang.setRowCount(0);
        for (GioHang x : listGioHang) {
            modelGioHang.addRow(new Object[]{
                x.getMaSP(),
                x.getTenSP(),
                x.getMauSac(),
                x.getChatLieu(),
                x.getThuongHieu(),
                x.getSoLuong(),
                String.format("%.0f", x.getDonGia()),
                String.format("%.0f", x.getThanhTien())
            });
        }
    }

    public void getListGioHangHDCT(String MaHD) {
        modelGioHang = (DefaultTableModel) tbl_GioHang.getModel();
        modelGioHang.setRowCount(0);
        List<HoaDonChiTiet> list = hdSer.getListHoaDonChiTiet(MaHD);
        if (list.isEmpty()) {
            return;
        }
        for (HoaDonChiTiet x : list) {
            GioHang gioHang = new GioHang();
            gioHang.setMaSP(x.getSanPham().getSp().getMaSP());
            gioHang.setTenSP(x.getSanPham().getSp().getTenSP());
            gioHang.setMauSac(x.getSanPham().getColor().getTenMauSac());
            gioHang.setChatLieu(x.getSanPham().getMaterial().getTenChatLieu());
            gioHang.setThuongHieu(x.getSanPham().getSp().getBrand().getTenThuongHieu());
            gioHang.setSoLuong(x.getSoluong());
            gioHang.setDonGia(x.getDonGia());
            gioHang.getThanhTien();
            listGioHang.add(gioHang);
            getListGioHang();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnXoaGH = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_GioHang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btn_TaoHD = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_sanpham = new javax.swing.JTable();
        txt_timsp = new javax.swing.JTextField();
        txt_sl_mua = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        a = new javax.swing.JLabel();
        txt_TienKhach = new javax.swing.JTextField();
        ok = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_TenKH = new javax.swing.JTextField();
        c = new javax.swing.JLabel();
        x = new javax.swing.JLabel();
        btn_Pay = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_Vouchers = new javax.swing.JTextField();
        lbl_tien_can_tra = new javax.swing.JLabel();
        lbl_tong_tien = new javax.swing.JLabel();
        lbl_maHD = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_tien_thua = new javax.swing.JLabel();
        lbl_nhan_vien = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHoaDon);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ Hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnXoaGH.setText("Xóa sản phẩm");

        tbl_GioHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_GioHang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbl_GioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_GioHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_GioHang);
        if (tbl_GioHang.getColumnModel().getColumnCount() > 0) {
            tbl_GioHang.getColumnModel().getColumn(0).setResizable(false);
            tbl_GioHang.getColumnModel().getColumn(1).setResizable(false);
            tbl_GioHang.getColumnModel().getColumn(2).setResizable(false);
            tbl_GioHang.getColumnModel().getColumn(3).setResizable(false);
            tbl_GioHang.getColumnModel().getColumn(4).setResizable(false);
            tbl_GioHang.getColumnModel().getColumn(5).setResizable(false);
            tbl_GioHang.getColumnModel().getColumn(6).setResizable(false);
            tbl_GioHang.getColumnModel().getColumn(7).setResizable(false);
        }

        jButton1.setText("Sửa sản phẩm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnXoaGH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaGH)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        btn_TaoHD.setText("Tạo Hóa Đơn");
        btn_TaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoHDActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        tbl_sanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_sanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanphamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_sanpham);
        if (tbl_sanpham.getColumnModel().getColumnCount() > 0) {
            tbl_sanpham.getColumnModel().getColumn(0).setResizable(false);
            tbl_sanpham.getColumnModel().getColumn(1).setResizable(false);
            tbl_sanpham.getColumnModel().getColumn(2).setResizable(false);
            tbl_sanpham.getColumnModel().getColumn(3).setResizable(false);
            tbl_sanpham.getColumnModel().getColumn(4).setResizable(false);
            tbl_sanpham.getColumnModel().getColumn(5).setResizable(false);
            tbl_sanpham.getColumnModel().getColumn(6).setResizable(false);
            tbl_sanpham.getColumnModel().getColumn(7).setResizable(false);
            tbl_sanpham.getColumnModel().getColumn(8).setResizable(false);
            tbl_sanpham.getColumnModel().getColumn(9).setResizable(false);
        }

        jLabel3.setText("Số lượng mua");

        jButton4.setText("Thêm giỏ hàng");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(txt_timsp, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_sl_mua, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(87, 87, 87))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_sl_mua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jButton4))
                    .addComponent(txt_timsp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn"));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        a.setText("Mã giảm giá");

        ok.setText("Tổng tiền");

        jLabel6.setText("Tiền khách đưa");

        jLabel7.setText("Tên NV");

        c.setText("Khách hàng");

        x.setText("Mã HD");

        btn_Pay.setText("Thanh Toán");

        jLabel1.setText("Tiền cần trả");

        lbl_tien_can_tra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tien_can_tra.setText("0");

        lbl_tong_tien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tong_tien.setText("0");

        lbl_maHD.setBackground(new java.awt.Color(255, 255, 255));
        lbl_maHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_maHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setText("Tiền thừa");

        lbl_tien_thua.setText("0");

        lbl_nhan_vien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton2.setText("Hủy");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ok, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                .addComponent(x, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(46, 46, 46)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_tien_can_tra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_TienKhach, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(txt_TenKH, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_Vouchers)
                    .addComponent(lbl_tong_tien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_maHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_tien_thua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_nhan_vien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btn_Pay, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbl_nhan_vien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(x)
                    .addComponent(lbl_maHD, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tong_tien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ok))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Vouchers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tien_can_tra, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TienKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_tien_thua, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Pay)
                    .addComponent(jButton2))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btn_TaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btn_TaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }
        listGioHang.clear();
        String MaHD = tblHoaDon.getValueAt(row, 0).toString();
        try {
            getListGioHangHDCT(MaHD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tbl_GioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_GioHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_GioHangMouseClicked

    private void btn_TaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoHDActionPerformed
        try {
            HoaDon hd1 = Read();
            if (HDSV.Insert(hd1) > 0) {
                System.out.println("thanh cong");
                fillTable(HDSV.Select());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_TaoHDActionPerformed

    private void tbl_sanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanphamMouseClicked
        // TODO add your handling code here:
        int row = tbl_sanpham.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        SPChiTiet sp = spSer.IDSP(row);
        int SoLuong = Integer.parseInt(tbl_sanpham.getValueAt(row, 8).toString());
        System.out.println(SoLuong);
        

//        try {
//            int slMua = Integer.parseInt(txt_sl_mua.getText());
//            String MaSP = tbl_sanpham.getValueAt(row, 1).toString();
//            String TenSP = tbl_sanpham.getValueAt(row, 2).toString();
//            int SoLuong = Integer.parseInt(tbl_sanpham.getValueAt(row, 9).toString());
//            Double DonGia = Double.parseDouble(tbl_sanpham.getValueAt(row, 8).toString());
//            List<HoaDonChiTiet> listh = hdSer.getListHoaDonChiTiet(tblHoaDon.getValueAt(rowHD, 1).toString());
//            if (SoLuong >= slMua) {
//                for (HoaDonChiTiet x : listh) {
//                    if (x.getSanPham().getSp().getMaSP().equals(MaSP)) {
//                        JOptionPane.showMessageDialog(this, "Sản Phẩm Đã Có Trên Giỏ Hàng");
//                        return;
//                    }
//                }
//                getListGioHang();
//                int kq = SoLuong - slMua;
////                spSer.updateSoLuongSP(MaSP, kq);
//                List<SPChiTiet> list = spSer.getListSPCT("Đang bán");
//                list.clear();
//                getListSP();
////                
//            } else if (SoLuong < slMua) {
//                JOptionPane.showMessageDialog(this, "Sản phẩm không đủ ");
//                return;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_tbl_sanphamMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int row = tbl_sanpham.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn sản phẩm để thêm giỏ hàng");
            return;
        }
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn hóa đơn để thêm sản phẩm!");
            return;
        }
        if (txt_sl_mua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn cần nhập số lượng muốn mua");
            return;
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(BanHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHangForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a;
    private javax.swing.JButton btnXoaGH;
    private javax.swing.JButton btn_Pay;
    private javax.swing.JButton btn_TaoHD;
    private javax.swing.JLabel c;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbl_maHD;
    private javax.swing.JLabel lbl_nhan_vien;
    private javax.swing.JLabel lbl_tien_can_tra;
    private javax.swing.JLabel lbl_tien_thua;
    private javax.swing.JLabel lbl_tong_tien;
    private javax.swing.JLabel ok;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tbl_GioHang;
    private javax.swing.JTable tbl_sanpham;
    private javax.swing.JTextField txt_TenKH;
    private javax.swing.JTextField txt_TienKhach;
    private javax.swing.JTextField txt_Vouchers;
    private javax.swing.JTextField txt_sl_mua;
    private javax.swing.JTextField txt_timsp;
    private javax.swing.JLabel x;
    // End of variables declaration//GEN-END:variables
}
