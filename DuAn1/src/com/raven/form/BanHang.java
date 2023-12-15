/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
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
import service.DBconnect;
import service.HoaDon_Service;
import service.SanPhamService;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import model.ChatLieu;
import model.Check_user_login;
import model.Mausac;
import model.SanPham;
import model.SanPhamChiTiet;
import model.Voucher;
import service.HoaDonChiTiet_service;
import service.KhachHangService;
import service.NguoiDung_Service;
import service.SPCT1_Service;
import service.SPCT_Service;
import service.Vouchers_Service;

/**
 *
 * @author leduc
 */
public class BanHang extends javax.swing.JPanel {

    HoaDon hd = new HoaDon();
    HoaDon_Service HDSV = new HoaDon_Service();
    HoaDonChiTiet hdct = new HoaDonChiTiet();
    private DefaultTableModel modelGioHang;
    //private DefaultTableModel modelSanPham;
    HoaDon_Service hdSer = new HoaDon_Service();
    private List<GioHang> listGioHang = new ArrayList<>();
    private List<HoaDonChiTiet> listhdct = new ArrayList<>();
    SanPhamService spSer = new SanPhamService();
    DefaultTableModel model;
    SPChiTiet spct1 = new SPChiTiet();
    int index = -1;
    int trangthai = 0;
    int KhachHang = 0;
    int NguoiDung = 0;
    int idkh = 1;
    KhachHang KH = new KhachHang();
    KhachHangService KH_serVice = new KhachHangService();

    NguoiDung ND = new NguoiDung();
    NguoiDung_Service ND_serVice = new NguoiDung_Service();

    Vouchers VC = new Vouchers();
    Vouchers_Service VC_serVice = new Vouchers_Service();

    DefaultTableModel model1;
    DefaultTableModel model2;
    DefaultTableModel model3;
    Connection conn;
    PreparedStatement ps;
    String sql;
    ResultSet rs;
    long countHD, soTrangHD, trangHD = 1;
    long countSP, soTrangSP, trangSP = 1;
    long countHDCT, soTrangHDCT, trangHDCT = 1;
    SPCT1_Service spctService = new SPCT1_Service();
    SanPhamChiTiet spct = new SanPhamChiTiet();
    String Manv = null;
    int checkUserLogin = -1;
    double tientra = 0.0;
    String trangthaiVC = "Còn hạn";

    /**
     * Creates new form BanHang
     */
    public BanHang() {
        initComponents();
        //configTolCol();
        checkUserLogin = Check_user_login.vt;
        Manv = Check_user_login.mand;
        System.out.println(checkUserLogin);
        System.out.println(Manv);
        //initTable();

        // hóa đơn
        name_tbl_HD();
        CountDB_HD(0);
        if (countHD % 5 == 0) {
            soTrangHD = countHD / 5;
        } else {
            soTrangHD = countHD / 5 + 1;
        }
        lbl_soTrang_HD.setText("1/" + soTrangHD);
        loadData_tbl_HD(1, 0);

        // sản phẩm
        CountDB_SP();
        //System.out.println(countSP);
        if (countSP % 5 == 0) {
            soTrangSP = countSP / 5;
        } else {
            soTrangSP = countSP / 5 + 1;
        }
        lbl_soTrang_SP.setText("1/" + soTrangSP);
        loadData_tbl_SP(1);

        // hóa đơn
        name_tbl_GH();
        //modelGioHang = (DefaultTableModel) tbl_GioHang.getModel();
        //fillTable(HDSV.Select());
        //getListSP();
        FILL_TO_CBO_KhachHang();
        FILL_TO_CBO_Vouchers(trangthaiVC);
        configTolCol();

    }

    public void configTolCol() {
        this.tbl_GioHang.getColumnModel().getColumn(0).setWidth(1);
        this.tbl_GioHang.getColumnModel().getColumn(1).setWidth(1);
        this.tbl_GioHang.getColumnModel().getColumn(2).setWidth(1);
        this.tbl_GioHang.getColumnModel().getColumn(3).setWidth(1);
        this.tbl_GioHang.getColumnModel().getColumn(4).setWidth(1);
        this.tbl_GioHang.getColumnModel().getColumn(5).setWidth(1);
        this.tbl_GioHang.getColumnModel().getColumn(6).setWidth(1);
        this.tbl_GioHang.getColumnModel().getColumn(7).setWidth(1);
        this.tbl_GioHang.getColumnModel().getColumn(8).setWidth(1);
        this.tbl_GioHang.getColumnModel().getColumn(9).setWidth(1);
    }

//    void initTable() {
//        String[] table1 = {"MãHD", "TenNV", "Ngày tạo", "Tên KH"};
//        for (int i = 0; i < table1.length; i++) {
//            tblHoaDon.getColumnModel().getColumn(i).setHeaderValue(table1[i]);
//        }
//        String[] table2 = {"Mã SP", "Tên SP", "Chất liệu", "Màu sắc", "Thương hiệu", "SL", "Đơn Giá", "Thành Tiền"};
//        for (int i = 0; i < table2.length; i++) {
//            tbl_GioHang.getColumnModel().getColumn(i).setHeaderValue(table2[i]);
//        }
//        String[] table4 = {"Loại hàng", "Mã SP", "Tên SP", "Chất liệu", "Màu sắc", "Thương hiệu", "Độ cận", "Giá thành", "Số Lượng", "Mô tả"};
//        for (int i = 0; i < table4.length; i++) {
//            tbl_sanpham.getColumnModel().getColumn(i).setHeaderValue(table4[i]);
//        }
//    }
    // add item to cbo_mausacTK
    public void FILL_TO_CBO_KhachHang() {
        try (Connection connection = DBconnect.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT tenKhachHang from khach_hang");
            String[] values = new String[0];
            cbo_KH.setModel(new DefaultComboBoxModel(values));
            while (rs.next()) {
                String tenKH = rs.getString(1);
                cbo_KH.addItem(tenKH);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // add item to cbo_mausacTK
    public void FILL_TO_CBO_Vouchers(String ttvoucher) {
        String query = "SELECT tenVouchers FROM vouchers WHERE trangthai = ?";

        try (Connection connection = DBconnect.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, ttvoucher); // Thiết lập giá trị cho tham số trong câu lệnh SQL

            ResultSet rs = statement.executeQuery();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

            while (rs.next()) {
                String maVouchers = rs.getString("tenVouchers");
                model.addElement(maVouchers);
            }

            cbo_Vouchers.setModel(model); // Đặt model cho ComboBox

        } catch (SQLException e) {
            e.printStackTrace(); // In ra thông tin lỗi để gỡ lỗi
            // Hoặc có thể ghi thông tin lỗi ra log file
        }
    }

    model.HoaDon read_HD(List<HoaDonChiTiet> getListHDCT) {
        model.HoaDon hd = new model.HoaDon();
        index = tblHoaDon.getSelectedRow();
        int id = (int) tblHoaDon.getValueAt(index, 0);
        //List<HoaDonChiTiet> getListHDCT = HDSV.getAll_HDCT(id);
        double tongtien = 0.0;
        for (HoaDonChiTiet hoaDonChiTiet : getListHDCT) {
            tongtien = tongtien + hoaDonChiTiet.getDonGia();
        }
        System.out.println(tongtien);
        String strSoThuc = String.format("%.0f", tongtien);
        lbl_tong_tien.setText(strSoThuc + "");
        lbl_tien_can_tra.setText(strSoThuc + "");
        tientra = Double.parseDouble(lbl_tien_can_tra.getText());
        hd.setTongtien(tientra);
        NguoiDung nd = ND_serVice.findBy_MaNV(Manv);
        hd.setNG(nd);
        KhachHang kh = KH_serVice.findBYTenKH(cbo_KH.getSelectedItem().toString());
        hd.setKH(kh);
        Voucher vc = VC_serVice.findByMaVoucher(cbo_Vouchers.getSelectedItem().toString());
        hd.setVouchers(vc);
        return hd;
    }

    model.HoaDon read_HD1(List<HoaDonChiTiet> getListHDCT) {
        model.HoaDon hd = new model.HoaDon();
        index = tblHoaDon.getSelectedRow();
        int id = (int) tblHoaDon.getValueAt(index, 0);
        //List<HoaDonChiTiet> getListHDCT = HDSV.getAll_HDCT(id);
        double tongtien = 0.0;
        for (HoaDonChiTiet hoaDonChiTiet : getListHDCT) {
            tongtien = tongtien + hoaDonChiTiet.getDonGia();
        }
        NguoiDung nd = ND_serVice.findBy_MaNV(Manv);
        hd.setNG(nd);
        tientra = Double.parseDouble(lbl_tien_can_tra.getText());
        hd.setTongtien(tientra);
        return hd;
    }

    model.HoaDon read_HD2(List<HoaDonChiTiet> getListHDCT) {
        model.HoaDon hd = new model.HoaDon();
        index = tblHoaDon.getSelectedRow();
        int id = (int) tblHoaDon.getValueAt(index, 0);
        //List<HoaDonChiTiet> getListHDCT = HDSV.getAll_HDCT(id);
        tientra = Double.parseDouble(lbl_tien_can_tra.getText());
        hd.setTongtien(tientra);
        NguoiDung nd = ND_serVice.findBy_MaNV(Manv);
        hd.setNG(nd);
        KhachHang kh = KH_serVice.findBYTenKH(cbo_KH.getSelectedItem().toString());
        hd.setKH(kh);
        return hd;
    }

    void Show(model.HoaDon HD1) {
        HoaDon HD = HD1;
        lbl_maHD1.setText(String.valueOf(HD.getMahD()));
        //TxtSoLuong.setText(String.valueOf(sp.getSoluong()));
        //TxtGiaNhap.setText(String.valueOf(sp.getGiaNhap()));
        //txt_GiaBan.setText(String.valueOf(sp.getGiathanh()));
        Double tongtien = HD.getTongtien();
        String strSoThuc = String.format("%.0f", tongtien);
        lbl_tong_tien.setText(strSoThuc + "");
        cbo_KH.setSelectedItem(HD.getKH().getTenKH());
        cbo_Vouchers.setSelectedItem(HD.getVouchers().getMaVouchers());
        lbl_tennv.setText(HD.getNG().getTenND());
    }

    void name_tbl_HD() {
        model1 = (DefaultTableModel) tblHoaDon.getModel();
        String[] row = new String[]{
            "ID HD", "MãHD", "TenNV", "Ngày tạo", "Tên KH"
        };
        model1.setColumnIdentifiers(row);
    }

    void name_tbl_SP() {
        model2 = (DefaultTableModel) tbl_sanpham.getModel();
        String[] row = new String[]{
            "ID SP", "Loại hàng", "Mã SP", "Tên SP", "Chất liệu", "Màu sắc", "Thương hiệu", "Độ cận", "Đơn giá", "Số Lượng", "Mô tả", "Trạng Thái"
        };
        model2.setColumnIdentifiers(row);
    }

    void name_tbl_GH() {
        model3 = (DefaultTableModel) tbl_GioHang.getModel();
        String[] row = new String[]{
            "ID sản phẩm", "Mã sản phẩm", "Tên sản phẩm", "Chất", "Màu", "Thương hiệu", "Độ cận", "Đơn giá", "Số lượng", "Tổng tiền"
        };
        model3.setColumnIdentifiers(row);
    }

    public void CountDB_HD(int trangthai) {
        try {
            String query = "Select count(*) from hoa_don where trangthai =" + trangthai + "";
            conn = DBconnect.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                countHD = rs.getLong(1);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void CountDB_SP() {
        try {
            String query = "Select count(*) from san_pham_chi_tiet";
            conn = DBconnect.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                countSP = rs.getLong(1);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void CountDB_HDCT(int idHD) {
        try {
            String query = "Select count(*) from hoa_don_chi_tiet hdct\n"
                    + "INNER JOIN hoa_don hd ON hd.idHoaDon = hdct.idHoaDon\n"
                    + "WHERE hd.idHoaDon = " + idHD + "";
            conn = DBconnect.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                countHDCT = rs.getLong(1);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadData_tbl_HD(long trangHD, int trangthai) {
        name_tbl_HD();
        model1.getDataVector().removeAllElements();
        try {
            String query = "SELECT top 5 idHoaDon,maHoaDon,nd.tenNguoi_dung,hd.ngayban,kh.tenKhachHang\n"
                    + "                    from hoa_don hd LEFT JOIN\n"
                    + "                    Nguoi_dung nd ON nd.idnguoi_dung = hd.idnguoi_dung LEFT JOIN\n"
                    + "                    khach_hang kh ON kh.idKhachHang = hd.idKhachHang\n"
                    + "                    where maHoaDon not in (Select top " + (trangHD * 5 - 5) + " maHoaDon from "
                    + "hoa_don where trangthai =" + trangthai + ") AND trangthai = " + trangthai + "";
            conn = DBconnect.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Vector v = new Vector();
                Integer idHD = rs.getInt(1);
                String maHD = rs.getString(2);
                String tenNV = rs.getString(3);
                Date ngayBan = rs.getDate(4);
                String tenKH = rs.getString(5);
                v.add(idHD);
                v.add(maHD);
                v.add(tenNV);
                v.add(ngayBan);
                v.add(tenKH);
                model1.addRow(v);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("");
        }
    }

    public void loadData_tbl_SP(long trangSP) {
        name_tbl_SP();
        model2.getDataVector().removeAllElements();
        try {
            String query = "SELECT top 5 spct.id_sp_chi_tiet,lsp.tenloai_sp,sp.masp,sp.tensp,cl.tenChatLieu,ms.tenMauSac,\n"
                    + "                    th.tenThuongHieu,spct.doCan,spct.giaThanh,spct.soLuong,spct.moTa,spct.trangThai\n"
                    + "                    from san_pham_chi_tiet spct LEFT JOIN\n"
                    + "                    san_pham sp ON sp.idsp = spct.idsp LEFT JOIN\n"
                    + "                    thuong_hieu th ON th.idThuongHieu = sp.idThuongHieu LEFT JOIN\n"
                    + "                    loai_sp lsp ON lsp.idloai_sp = sp.idloai_sp LEFT JOIN\n"
                    + "                    chat_lieu cl ON cl.idChatLieu = spct.idChatLieu LEFT JOIN\n"
                    + "                    mau_sac ms ON ms.idMauSac = spct.idMauSac\n"
                    + "                    where spct.id_sp_chi_tiet not in (Select top " + (trangSP * 5 - 5) + " id_sp_chi_tiet from san_pham_chi_tiet) ";
            conn = DBconnect.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Vector v = new Vector();
                Integer idspct = rs.getInt(1);
                String tenLSP = rs.getString(2);
                String masp = rs.getString(3);
                String tensp = rs.getString(4);
                String tenChatLieu = rs.getString(5);
                String tenMauSac = rs.getString(6);
                String tenThuongHieu = rs.getString(7);
                Double doCan = rs.getDouble(8);
                Integer giaThanh = rs.getInt(9);
                Integer soLuong = rs.getInt(10);
                String moTa = rs.getString(11);
                String trangthai = rs.getString(12);
                v.add(idspct);
                v.add(tenLSP);
                v.add(masp);
                v.add(tensp);
                v.add(tenChatLieu);
                v.add(tenMauSac);
                v.add(tenThuongHieu);
                v.add(doCan);
                v.add(giaThanh);
                v.add(soLuong);
                v.add(moTa);
                v.add(trangthai);
                model2.addRow(v);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("");
        }
    }

    void fillTable(List<model.SanPhamChiTiet> list) {
        model = (DefaultTableModel) tbl_sanpham.getModel();
        model.setRowCount(0);
        for (model.SanPhamChiTiet spct : list) {
            model.addRow(spct.todata2());
        }
    }

    public void loadData_tbl_GioHang(int idHD, long trangHD) {
        name_tbl_GH();
        model3.getDataVector().removeAllElements();
        try {
            String query = "SELECT top 5 spct.id_sp_chi_tiet,sp.masp,sp.tensp,cl.tenChatLieu,ms.tenMauSac,th.tenThuongHieu,spct.doCan,"
                    + "spct.giaThanh,hdct.soluong,hdct.dongia\n"
                    + "from san_pham_chi_tiet spct LEFT JOIN\n"
                    + "san_pham sp ON sp.idsp = spct.idsp LEFT JOIN\n"
                    + "thuong_hieu th ON th.idThuongHieu = sp.idThuongHieu LEFT JOIN\n"
                    + "hoa_don_chi_tiet hdct ON hdct.id_sp_chi_tiet = spct.id_sp_chi_tiet LEFT JOIN\n"
                    + "hoa_don hd ON hd.idHoaDon = hdct.idHoaDon LEFT JOIN\n"
                    + "chat_lieu cl ON cl.idChatLieu = spct.idChatLieu LEFT JOIN\n"
                    + "mau_sac ms ON ms.idMauSac = spct.idMauSac\n"
                    + "where hdct.idHoaDonChiTiet not in\n"
                    + "(Select top " + (trangHD * 5 - 5) + " hdct.idHoaDonChiTiet from hoa_don_chi_tiet hdct "
                    + "INNER JOIN hoa_don hd ON hdct.idHoaDon = hd.idHoaDon\n"
                    + "where hd.idHoaDon = " + idHD + ") AND hd.idHoaDon = " + idHD + "";

            conn = DBconnect.getConnection();
            Statement st = conn.createStatement();
            //ps.setObject(1, id);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Vector v = new Vector();
                Integer idspct = rs.getInt(1);
                String masp = rs.getString(2);
                String tensp = rs.getString(3);
                String tenChatLieu = rs.getString(4);
                String tenMauSac = rs.getString(5);
                String tenThuongHieu = rs.getString(6);
                Double doCan = rs.getDouble(7);
                Integer dongia = rs.getInt(8);
                Integer soLuong = rs.getInt(9);
                Integer thanhtien = rs.getInt(10);
                v.add(idspct);
                v.add(masp);
                v.add(tensp);
                v.add(tenChatLieu);
                v.add(tenMauSac);
                v.add(tenThuongHieu);
                v.add(doCan);
                v.add(dongia);
                v.add(soLuong);
                v.add(thanhtien);
                model3.addRow(v);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("");
        }
    }

//    private void getListSP() {
//        model = (DefaultTableModel) tbl_sanpham.getModel();
//        model.setRowCount(0);
//        List<SPChiTiet> getList = spSer.getListSPCT("Đang bán");
//        Object[] row = new Object[10];
//        for (SPChiTiet x : getList) {
//            row[0] = x.getLoai().getTenLoaiSP();
//            row[1] = x.getSp().getMaSP();
//            row[2] = x.getSp().getTenSP();
//            row[3] = x.getColor().getTenMauSac();
//            row[4] = x.getMaterial().getTenChatLieu();
//            row[5] = x.getSp().getBrand().getTenThuongHieu();
//            row[6] = x.getDoCan();
//            row[7] = String.format("%.0f", x.getGiathanh());
//            row[8] = x.getSoluong();
//            row[9] = x.getMota();
//            model.addRow(row);
//        }
//    }
//    void fillTable(List<HoaDon> list) {
//        if (hd.getTrangThai() == 0) {
//            model = (DefaultTableModel) tblHoaDon.getModel();
//            model.setRowCount(0);
//            for (HoaDon hoaDon : list) {
//                model.addRow(hoaDon.todata_HD());
//            }
//        }
//
//    }
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
        NguoiDung nd = new NguoiDung();
        nd = ND_serVice.findBy_MaNV(Manv);
        hd.setNG(nd);
        KhachHang kh = new KhachHang();

        kh.setIdKH(1); // set id = 1 tương ứng với Vãng lai

        hd.setKH(kh);
        return hd;
    }

    public void Reset() {
        CountDB_HD(0);
        if (countHD % 5 == 0) {
            soTrangHD = countHD / 5;
        } else {
            soTrangHD = countHD / 5 + 1;
        }
        lbl_soTrang_HD.setText("1/" + soTrangHD);
        loadData_tbl_HD(1, 0);
    }

    public void ResetSLmua() {
        txt_sl_mua.setText("");
    }

    model.HoaDonChiTiet read() {
        model.HoaDonChiTiet hdct = new model.HoaDonChiTiet();
        int rowsp = tbl_sanpham.getSelectedRow();
        int idspct = Integer.parseInt(tbl_sanpham.getValueAt(rowsp, 0).toString());
        hdct.setSanPham(new SanPhamChiTiet(idspct));

        int rowhd = tblHoaDon.getSelectedRow();
        int idhd = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
        hdct.setHoaDon(new HoaDon(idhd));

        //Integer sl_mua = Integer.valueOf(txt_sl_mua.getText());
        hdct.setSoluong(1);

        Double donGia = Double.parseDouble(tbl_sanpham.getValueAt(rowsp, 8).toString());
        Double thanhTien = donGia * 1;
        hdct.setDonGia(thanhTien);
        return hdct;
    }

    model.SanPhamChiTiet read_SPCT() {
        model.SanPhamChiTiet spct = new model.SanPhamChiTiet();

        //Integer sl_mua = Integer.valueOf(txt_sl_mua.getText());
        int rowsp = tbl_sanpham.getSelectedRow();
        Integer SL_sp_old = Integer.parseInt(tbl_sanpham.getValueAt(rowsp, 9).toString());
        System.out.println(SL_sp_old);
        Integer SL_sp_new = SL_sp_old - 1;
        System.out.println(SL_sp_new);
        spct.setSoluong(SL_sp_new);
        return spct;
    }

    model.HoaDonChiTiet update_SL_GH() {
        model.HoaDonChiTiet hdct = new model.HoaDonChiTiet();

        Integer sl_mua = Integer.valueOf(txt_sl_mua.getText());
        hdct.setSoluong(sl_mua);

        int rowgh = tbl_GioHang.getSelectedRow();
        Double donGia = Double.parseDouble(tbl_GioHang.getValueAt(rowgh, 7).toString());
        Double thanhTien = donGia * sl_mua;
        hdct.setDonGia(thanhTien);
        return hdct;
    }

    model.SanPhamChiTiet update_SL_SPCT() {
        model.SanPhamChiTiet spct = new model.SanPhamChiTiet();
        Integer sl_mua = Integer.valueOf(txt_sl_mua.getText());
        System.out.println(sl_mua);
        int rowgh = tbl_GioHang.getSelectedRow();
        Integer idspct = Integer.parseInt(tbl_GioHang.getValueAt(rowgh, 0).toString());
        spct = spctService.selectByID(idspct);
        Integer SL_sp_SP = Integer.valueOf(spct.getSoluong());
        System.out.println(SL_sp_SP);
        Integer SL_sp_GH = Integer.parseInt(tbl_GioHang.getValueAt(rowgh, 8).toString());
        System.out.println(SL_sp_GH);
        Integer SL_sp_new = SL_sp_SP - sl_mua + SL_sp_GH;
        spct.setSoluong(SL_sp_new);
        System.out.println(SL_sp_new);
        return spct;
    }

    model.Voucher update_SL_VC() {
        model.Voucher vc = new model.Voucher();
        vc = VC_serVice.findByMaVoucher(cbo_Vouchers.getSelectedItem().toString());
        Integer soluong = vc.getSoluong() - 1;
        Integer sldadung = vc.getSoluongdadung() + 1;
        System.out.println(soluong);
        System.out.println(sldadung);
        vc.setSoluong(soluong);
        vc.setSoluongdadung(sldadung);
        //hdct.setDonGia(thanhTien);
        return vc;
    }

    model.SanPhamChiTiet delete_SL_SPCT() {
        model.SanPhamChiTiet spct = new model.SanPhamChiTiet();
        int rowgh = tbl_GioHang.getSelectedRow();
        Integer idspct = Integer.parseInt(tbl_GioHang.getValueAt(rowgh, 0).toString());
        spct = spctService.selectByID(idspct);
        Integer SL_sp_SP = Integer.valueOf(spct.getSoluong());
        System.out.println(SL_sp_SP);
        Integer SL_sp_GH = Integer.parseInt(tbl_GioHang.getValueAt(rowgh, 8).toString());
        System.out.println(SL_sp_GH);
        Integer SL_sp_new = SL_sp_SP + SL_sp_GH;
        spct.setSoluong(SL_sp_new);
        System.out.println(SL_sp_new);
        return spct;
    }

    public boolean check_Add_SP_To_GioHang() {
        int row = -1;
        int rowHD = -1;
        row = tbl_sanpham.getSelectedRow();
        rowHD = tblHoaDon.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn sản phẩm để thêm giỏ hàng");
            return false;
        } else {
            Integer SL_sp_old = Integer.parseInt(tbl_sanpham.getValueAt(row, 9).toString());
            if (SL_sp_old <= 0) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã hết");
                return false;
            }
        }
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn hóa đơn để thêm sản phẩm!");
            return false;
        }

        return true;
    }

    public boolean check_Update_SL() {
        int row = tbl_GioHang.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn hóa đơn để thay đổi số lượng");
            return false;
        }
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn giỏ hàng để thay đổi số lượng");
            return false;
        }

        if (txt_sl_mua.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng");
            return false;
        }
        try {
            int soLuong = Integer.parseInt(txt_sl_mua.getText());
            System.out.println(soLuong);
            Integer idspct = Integer.parseInt(tbl_GioHang.getValueAt(row, 0).toString());
            spct = spctService.selectByID(idspct);
            Integer SL_sp_SP = Integer.valueOf(spct.getSoluong());
            System.out.println(SL_sp_SP);
            Integer SL_sp_GH = Integer.parseInt(tbl_GioHang.getValueAt(row, 8).toString());
            System.out.println(SL_sp_GH);
            Integer SL_sp_new = SL_sp_SP + SL_sp_GH - soLuong;
            System.out.println(SL_sp_new);
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng lớn hơn 0");
                return false;
            } else if (soLuong >= 10) {
                JOptionPane.showMessageDialog(this, "Vượt quá số lượng cho phép");
                return false;
            }
            if (SL_sp_new < 0) {
                JOptionPane.showMessageDialog(this, "Không đủ số lượng sản phẩm");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên");
            return false;
        }

        return true;
    }

    public boolean Check_Delete_SP_from_GioHang() {
        int row = tbl_GioHang.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn hóa đơn để xóa sản phẩm khỏi gỏi hàng");
            return false;
        }
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn giỏ hàng để xóa sản phẩm khỏi gỏi hàng");
            return false;
        }

        return true;
    }

    public boolean Check_SP_from_GioHang() {
        int row = tbl_GioHang.getRowCount();
        System.out.println(row);
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn hóa đơn để thanh toán");
            return false;
        }
        if (row < 1) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng trống");
            return false;
        }

        return true;
    }

    public void ResetHD() {
        cbo_KH.setSelectedIndex(0);
        lbl_tennv.setText("");
        cbo_Vouchers.setSelectedIndex(0);
        lbl_tong_tien.setText("");
        lbl_tien_can_tra.setText("");
        lbl_maHD1.setText("");
    }

    public static String removeVietnameseAccent(String input) {
        input = Normalizer.normalize(input, Normalizer.Form.NFD);
        input = input.replaceAll("\\p{M}", "");

        Map<Character, Character> vietnameseCharacters = new HashMap<>();
        vietnameseCharacters.put('đ', 'd');
        vietnameseCharacters.put('Đ', 'D');

        StringBuilder output = new StringBuilder();
        for (char ch : input.toCharArray()) {
            Character replacedChar = vietnameseCharacters.get(ch);
            if (replacedChar != null) {
                output.append(replacedChar);
            } else {
                output.append(ch);
            }
        }

        return output.toString();
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
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnXoaGH = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_GioHang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        lbl_soTrang_GH = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_sl_mua = new javax.swing.JTextField();
        btn_TaoHD = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_sanpham = new javax.swing.JTable();
        txt_timsp = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        lbl_soTrang_SP = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        a = new javax.swing.JLabel();
        ok = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        c = new javax.swing.JLabel();
        x = new javax.swing.JLabel();
        btn_Pay = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbl_tien_can_tra = new javax.swing.JLabel();
        lbl_tong_tien = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        lbl_maHD1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        cbo_Vouchers = new javax.swing.JComboBox<>();
        cbo_KH = new javax.swing.JComboBox<>();
        lbl_tennv = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        lbl_soTrang_HD = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(tblHoaDon);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 18, 437, 140));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ Hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnXoaGH.setText("Xóa sản phẩm");
        btnXoaGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGHActionPerformed(evt);
            }
        });

        tbl_GioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_GioHang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl_GioHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_GioHang.setDragEnabled(true);
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
            tbl_GioHang.getColumnModel().getColumn(8).setResizable(false);
            tbl_GioHang.getColumnModel().getColumn(9).setResizable(false);
        }

        jButton1.setText("Đổi số lượng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton9.setText("<");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        lbl_soTrang_GH.setText("0");

        jButton10.setText(">");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel3.setText("Số lượng mua");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_soTrang_GH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnXoaGH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_sl_mua, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(151, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton9)
                        .addComponent(jButton10))
                    .addComponent(lbl_soTrang_GH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaGH)
                    .addComponent(jButton1)
                    .addComponent(jLabel3)
                    .addComponent(txt_sl_mua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 194, 693, -1));

        btn_TaoHD.setText("Tạo Hóa Đơn");
        btn_TaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoHDActionPerformed(evt);
            }
        });
        jPanel1.add(btn_TaoHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 52, -1, 31));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        tbl_sanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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
            tbl_sanpham.getColumnModel().getColumn(10).setResizable(false);
            tbl_sanpham.getColumnModel().getColumn(11).setResizable(false);
        }

        txt_timsp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_timspFocusLost(evt);
            }
        });
        txt_timsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_timspMousePressed(evt);
            }
        });
        txt_timsp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timspKeyReleased(evt);
            }
        });

        jButton4.setText("Thêm giỏ hàng");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lbl_soTrang_SP.setText("jLabel7");

        jButton7.setText("<");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText(">");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
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
                .addComponent(txt_timsp, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_soTrang_SP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addGap(515, 515, 515))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_timsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(lbl_soTrang_SP)
                    .addComponent(jButton8))
                .addGap(45, 45, 45))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 434, 1030, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn"));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        a.setText("Mã giảm giá");
        jPanel4.add(a, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 196, 78, -1));

        ok.setText("Tổng tiền");
        jPanel4.add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 156, 84, -1));

        jLabel7.setText("Tên NV");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 33, 78, -1));

        c.setText("Khách hàng");
        jPanel4.add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 113, 78, 22));

        x.setText("Mã HD");
        jPanel4.add(x, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 79, 78, -1));

        btn_Pay.setText("Thanh Toán");
        btn_Pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PayActionPerformed(evt);
            }
        });
        jPanel4.add(btn_Pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 125, -1));

        jLabel1.setText("Tiền cần trả");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 236, 84, -1));

        lbl_tien_can_tra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tien_can_tra.setText("0");
        jPanel4.add(lbl_tien_can_tra, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 217, 22));

        lbl_tong_tien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tong_tien.setText("0");
        jPanel4.add(lbl_tong_tien, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 217, 22));

        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 102, -1));

        lbl_maHD1.setBackground(new java.awt.Color(255, 255, 255));
        lbl_maHD1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_maHD1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.add(lbl_maHD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 200, 22));

        jButton3.setText("Voucher");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 80, -1));

        cbo_Vouchers.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        cbo_Vouchers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(cbo_Vouchers, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 180, -1));

        cbo_KH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_KHActionPerformed(evt);
            }
        });
        jPanel4.add(cbo_KH, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 160, -1));

        lbl_tennv.setBackground(new java.awt.Color(255, 255, 255));
        lbl_tennv.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tennv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.add(lbl_tennv, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 200, 22));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Microsoft-Fluentui-Emoji-Mono-Plus.24.png"))); // NOI18N
        jButton11.setBorder(null);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 30, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 24, 400, 370));

        jButton5.setText("<");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        lbl_soTrang_HD.setText("0");
        jPanel1.add(lbl_soTrang_HD, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, -1, -1));

        jButton6.setText(">");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1119, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked

        index = tblHoaDon.getSelectedRow();
        int id = (int) tblHoaDon.getValueAt(index, 0);
        model.HoaDon hd = HDSV.selectHDByID(id);
        Show(hd);
        try {
            read_HD(HDSV.getAll_HDCT(id));
        } catch (Exception e) {
            System.out.println(e);
        }
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }

        try {
            int idHD = Integer.parseInt(tblHoaDon.getValueAt(row, 0).toString());
            CountDB_HDCT(idHD);
            if (countHDCT % 5 == 0) {
                soTrangHDCT = countHDCT / 5;
            } else {
                soTrangHDCT = countHDCT / 5 + 1;
            }
            lbl_soTrang_GH.setText("1/" + soTrangHDCT);
            loadData_tbl_GioHang(idHD, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonMouseEntered

    private void btnXoaGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGHActionPerformed

        if (Check_Delete_SP_from_GioHang()) {
            int a = JOptionPane.showConfirmDialog(this, "bạn muốn xóa sản phẩm này khỏi giỏ hàng");
            if (a == 0) {
                int rowhdct = tbl_GioHang.getSelectedRow();
                int idspct = Integer.parseInt(tbl_GioHang.getValueAt(rowhdct, 0).toString());
                int rowhd = tblHoaDon.getSelectedRow();
                int idhd = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
                model.SanPhamChiTiet spct = this.delete_SL_SPCT();
                if (HDSV.deleteHDCT(idspct, idhd) > 0) {
                    if (spctService.updateSL(spct, idspct) > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa thanh cong");
                        int idHD = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
                        CountDB_HDCT(idHD);
                        if (countHDCT % 5 == 0) {
                            soTrangHDCT = countHDCT / 5;
                        } else {
                            soTrangHDCT = countHDCT / 5 + 1;
                        }
                        lbl_soTrang_GH.setText("1/" + soTrangHDCT);
                        loadData_tbl_GioHang(idHD, 1);
                        loadData_tbl_SP(1);
                        index = tblHoaDon.getSelectedRow();
                        int id = (int) tblHoaDon.getValueAt(index, 0);
                        try {
                            read_HD(HDSV.getAll_HDCT(id));
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        //reset();
                    }
                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnXoaGHActionPerformed

    private void tbl_GioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_GioHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_GioHangMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (check_Update_SL()) {

            int a = JOptionPane.showConfirmDialog(this, "bạn muốn thay đổi số lượng sản phẩm này ");
            if (a == 0) {
                int row = tblHoaDon.getSelectedRow();
                model.HoaDonChiTiet hdct = this.update_SL_GH();
                model.SanPhamChiTiet spct = this.update_SL_SPCT();
                int rowhdct = tbl_GioHang.getSelectedRow();
                int idspct = Integer.parseInt(tbl_GioHang.getValueAt(rowhdct, 0).toString());
                int rowhd = tblHoaDon.getSelectedRow();
                int idhd = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
                if (HDSV.updateSL(hdct, idspct, idhd) > 0) {
                    if (spctService.updateSL(spct, idspct) > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thanh cong");
                        int idHD = Integer.parseInt(tblHoaDon.getValueAt(row, 0).toString());
                        CountDB_HDCT(idHD);
                        if (countHDCT % 5 == 0) {
                            soTrangHDCT = countHDCT / 5;
                        } else {
                            soTrangHDCT = countHDCT / 5 + 1;
                        }
                        lbl_soTrang_GH.setText("1/" + soTrangHDCT);
                        loadData_tbl_GioHang(idHD, 1);
                        loadData_tbl_SP(1);
                        ResetSLmua();
                        index = tblHoaDon.getSelectedRow();
                        int id = (int) tblHoaDon.getValueAt(index, 0);
                        try {
                            read_HD(HDSV.getAll_HDCT(id));
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        //reset();
                    }
                }
            }
        } else {
            return;
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if (trangHDCT > 1) {
            trangHDCT--;
            int row = tblHoaDon.getSelectedRow();
            int idHD = Integer.parseInt(tblHoaDon.getValueAt(row, 0).toString());
            CountDB_HDCT(idHD);
            if (countHDCT % 5 == 0) {
                soTrangHDCT = countHDCT / 5;
            } else {
                soTrangHDCT = countHDCT / 5 + 1;
            }
            //lbl_soTrang_GH.setText("1/" + soTrangHDCT);
            loadData_tbl_GioHang(idHD, trangHDCT);
            lbl_soTrang_GH.setText(trangHDCT + "/" + soTrangHDCT);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if (trangHDCT < soTrangHDCT) {
            trangHDCT++;
            int row = tblHoaDon.getSelectedRow();
            int idHD = Integer.parseInt(tblHoaDon.getValueAt(row, 0).toString());
            CountDB_HDCT(idHD);
            if (countHDCT % 5 == 0) {
                soTrangHDCT = countHDCT / 5;
            } else {
                soTrangHDCT = countHDCT / 5 + 1;
            }
            //lbl_soTrang_GH.setText("1/" + soTrangHDCT);
            loadData_tbl_GioHang(idHD, trangHDCT);
            lbl_soTrang_GH.setText(trangHDCT + "/" + soTrangHDCT);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btn_TaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoHDActionPerformed
        try {
            HoaDon hd1 = Read();

            if (HDSV.Insert(hd1) > 0) {
                System.out.println("thanh cong");
                Reset();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_TaoHDActionPerformed

    private void tbl_sanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanphamMouseClicked

    }//GEN-LAST:event_tbl_sanphamMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (check_Add_SP_To_GioHang()) {
            int rowsp = tbl_sanpham.getSelectedRow();
            int idspct = Integer.parseInt(tbl_sanpham.getValueAt(rowsp, 0).toString());
            String trangthaiSP = tbl_sanpham.getValueAt(rowsp, 11).toString();
            System.out.println(trangthaiSP);
            if (trangthaiSP.equals("Ngưng bán")) {
                JOptionPane.showMessageDialog(this, "Sản Phẩm này tạm thời ngừng bán");
            } else {
                int row = tblHoaDon.getSelectedRow();
                model.HoaDonChiTiet hdct = this.read();
                model.SanPhamChiTiet spct = this.read_SPCT();

                int rowhd = tblHoaDon.getSelectedRow();
                int idhd = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());

                if (HDSV.find_HDCT_ByID(idspct, idhd) != null) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm này đã được cho vào giỏ hàng!");
                } else {
                    if (HDSV.InsertHDCT(hdct) > 0) {
                        if (spctService.updateSL(spct, idspct) > 0) {
                            JOptionPane.showMessageDialog(this, "Them thanh cong");
                            int idHD = Integer.parseInt(tblHoaDon.getValueAt(row, 0).toString());
                            CountDB_HDCT(idHD);
                            if (countHDCT % 5 == 0) {
                                soTrangHDCT = countHDCT / 5;
                            } else {
                                soTrangHDCT = countHDCT / 5 + 1;
                            }
                            lbl_soTrang_GH.setText("1/" + soTrangHDCT);
                            loadData_tbl_GioHang(idHD, 1);
                            loadData_tbl_SP(1);
                            index = tblHoaDon.getSelectedRow();
                            int id = (int) tblHoaDon.getValueAt(index, 0);
                            try {
                                read_HD(HDSV.getAll_HDCT(id));
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                        //reset();
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if (trangSP > 1) {
            trangSP--;
            loadData_tbl_SP(trangSP);
            lbl_soTrang_SP.setText(trangSP + "/" + soTrangSP);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if (trangSP < soTrangSP) {
            trangSP++;
            loadData_tbl_SP(trangSP);
            lbl_soTrang_SP.setText(trangSP + "/" + soTrangSP);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btn_PayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PayActionPerformed
        // TODO add your handling code here:
        if (Check_SP_from_GioHang()) {
            int thanhtoan = JOptionPane.showConfirmDialog(this, "Bạn muốn thanh toán hóa đơn");
            if (thanhtoan == 0) {
                int inhoadon = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn ra không");
                if (lbl_maHD1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để thực hiện");
                } else {
                    index = tblHoaDon.getSelectedRow();
                    int id = (int) tblHoaDon.getValueAt(index, 0);
                    String mahd = tblHoaDon.getValueAt(index, 1).toString();
                    int rowhd = tblHoaDon.getSelectedRow();
                    int idhd = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
                    model.HoaDon hd = this.read_HD2(HDSV.getAll_HDCT(id));
                    JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                    if (inhoadon == 0) {
                        try {
                            String mahoadon = lbl_maHD1.getText();
                            String filePath = "D:\\game\\HoaDon\\hoadon_" + mahoadon + ".pdf";

                            Document document = new Document();
                            PdfWriter.getInstance(document, new FileOutputStream(filePath));
                            document.open();
                            Font font_shop = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD);
//                            font_shop.setSize(20);
                            Paragraph shop = new Paragraph("SIX STORE", font_shop);
                            document.add(shop);
                            shop.setAlignment(Element.ALIGN_LEFT);
                            Paragraph newLine = new Paragraph("\n"); // Tạo một dòng mới
                            document.add(newLine);

                            // Sử dụng font Times New Roman thay vì Arial Unicode MS
                            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
                            String tieude = "HÓA ĐƠN BÁN HÀNG";
                            String tieudecondau = Normalizer.normalize(tieude, Normalizer.Form.NFD);
                            String tieudekhongdau = tieudecondau.replaceAll("Đ", "D");
                            Paragraph title = new Paragraph(tieudekhongdau, font);
                            title.setAlignment(Element.ALIGN_CENTER);
                            document.add(title);
                            String mahoadon2 = tblHoaDon.getValueAt(index, 1).toString();
                            Paragraph newLinee = new Paragraph("\n"); // Tạo một dòng mới
                            document.add(newLinee);
                            Paragraph mahoadonIN = new Paragraph("Ma Hoa Don : " + mahoadon2);
                            document.add(mahoadonIN);
                            Paragraph newLine3 = new Paragraph("\n"); // Tạo một dòng mới
                            document.add(newLine3);
                            String tennhanvien = lbl_tennv.getText();
                            Date ngayban = (Date) tblHoaDon.getValueAt(index, 3);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            String tennvcondau = Normalizer.normalize(tennhanvien, Normalizer.Form.NFD);
                            String tennvkhongdau = tennvcondau.replaceAll("Đ", "D");
                            Paragraph customer = new Paragraph("Nguoi Ban: " + tennvkhongdau + "               " + "Ngay ban:" + sdf.format(ngayban));
                            document.add(customer);
                            Paragraph newLine2 = new Paragraph("\n"); // Tạo một dòng mới
                            document.add(newLine);
                            KhachHang khachhang = KH_serVice.findBYTenKH(cbo_KH.getSelectedItem().toString());
                            String tenKh = khachhang.getTenKH();
                            String tenKhcondau = Normalizer.normalize(tenKh, Normalizer.Form.NFD);
                            String tenKhkhongdau = tenKhcondau.replaceAll("Đ", "D");
                            Paragraph khachhangmua = new Paragraph("Khach Hang :" + tenKhkhongdau);
                            document.add(khachhangmua);
                            document.add(newLine);
                            PdfPTable products = new PdfPTable(9);
                            products.setWidthPercentage(100);
//                products.addCell("STT");
                            products.addCell("The Loai");
                            products.addCell("Ten SP");
                            products.addCell("Chat Lieu");
                            products.addCell("Mau Sac");
                            products.addCell("Thuong Hieu");
                            products.addCell("Do can");
                            products.addCell("Don gia");
                            products.addCell("So luong");
                            products.addCell("Thanh Tien");
                            int stt = 1;
                            //           Populate table with data from tbl_GioHang
                            for (int i = 0; i < tbl_GioHang.getRowCount(); i++) {
                                String ma = (String) tbl_GioHang.getValueAt(i, 1);
                                String ten1 = (String) tbl_GioHang.getValueAt(i, 2);
                                String tencl = (String) tbl_GioHang.getValueAt(i, 3);
                                String tenms = (String) tbl_GioHang.getValueAt(i, 4);
                                String tentt = (String) tbl_GioHang.getValueAt(i, 5);
                                Number docan = (Number) tbl_GioHang.getValueAt(i, 6);
                                Number dongia = (Number) tbl_GioHang.getValueAt(i, 7);
                                int soluong = (int) tbl_GioHang.getValueAt(i, 8);
                                Number thanhtien = (Number) tbl_GioHang.getValueAt(i, 9);
                                String macondau = Normalizer.normalize(ma, Normalizer.Form.NFD);
                                String makhongdau = macondau.replaceAll("Đ", "D");
                                products.addCell(makhongdau);
                                String ten1condau = Normalizer.normalize(ten1, Normalizer.Form.NFD);
                                String ten1khongdau = ten1condau.replaceAll("Đ", "D");
                                products.addCell(ten1khongdau);
                                String tenclcondau = Normalizer.normalize(tencl, Normalizer.Form.NFD);
                                String tenclkhongdau = tenclcondau.replaceAll("Đ", "D");
                                products.addCell(tenclkhongdau);
                                String tenmscondau = Normalizer.normalize(tenms, Normalizer.Form.NFD);
                                String tenmskhongdau = tenmscondau.replaceAll("Đ", "D");
                                products.addCell(tenmskhongdau);
                                String tenttcondau = Normalizer.normalize(tentt, Normalizer.Form.NFD);
                                String tenttkhongdau = tenttcondau.replaceAll("Đ", "D");
                                products.addCell(tenttkhongdau);
                                products.addCell(String.valueOf(docan.doubleValue()));
                                products.addCell(String.valueOf(dongia.doubleValue()));
                                products.addCell(String.valueOf(soluong));
                                products.addCell(String.valueOf(thanhtien.doubleValue()));
                            }

                            document.add(products);
                            Paragraph newLine1 = new Paragraph("\n"); // Tạo một dòng mới
                            document.add(newLine1);
                            String tongtienptra = lbl_tien_can_tra.getText();
                            Paragraph total = new Paragraph("Tong tien phai tra:" + tongtienptra, font);
                            total.setAlignment(Element.ALIGN_RIGHT);
                            document.add(total);
                            Paragraph thank = new Paragraph("Cam on quy khach da ung ho");
                            thank.setAlignment(Element.ALIGN_RIGHT);
                            document.add(thank);
                            document.close();

                            JOptionPane.showMessageDialog(null, "In hóa đơn thành công!");
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "In hóa đơn thất bại!");
                        }
                    }

                    if (HDSV.update_TrangThai_HD1(hd, idhd) > 0) {
                        int idHD = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
                        CountDB_HDCT(idHD);
                        if (countHDCT % 5 == 0) {
                            soTrangHDCT = countHDCT / 5;
                        } else {
                            soTrangHDCT = countHDCT / 5 + 1;
                        }
                        lbl_soTrang_GH.setText("1/" + soTrangHDCT);
                        loadData_tbl_HD(1, 0);
                        loadData_tbl_GioHang(idHD, 1);
                        loadData_tbl_SP(1);
                        ResetHD();
                        modelGioHang = (DefaultTableModel) tbl_GioHang.getModel();
                        modelGioHang.setRowCount(0);
                        //reset(); 
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_PayActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (lbl_tennv.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để thực hiện");
        } else {
            index = tblHoaDon.getSelectedRow();
            int id = (int) tblHoaDon.getValueAt(index, 0);
            int rowhd = tblHoaDon.getSelectedRow();
            int idhd = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
            model.HoaDon hd = this.read_HD(HDSV.getAll_HDCT(id));
            if (HDSV.update_Vouchers_HD(hd, idhd) > 0) {
                model.Voucher vc = this.update_SL_VC();
                String tenVC = vc.getTenVouchers();
                Integer soluong = vc.getSoluong();
                Integer sldadung = vc.getSoluongdadung();
                if (VC_serVice.updateSL(vc, tenVC) > 0) {
                    if (soluong < 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng voucher đã hết");
                    } else {
                        System.out.println(soluong);
                        System.out.println(sldadung);
                    }
                    try {
                        vc = VC_serVice.findByMaVoucher(cbo_Vouchers.getSelectedItem().toString());
                        Float giamGia = vc.getGiatri();
                        System.out.println(giamGia);
                        tientra = Double.valueOf(lbl_tong_tien.getText()) - giamGia;
                        System.out.println(tientra);
                        String str = String.format("%.0f", tientra);
                        lbl_tien_can_tra.setText(str);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    JOptionPane.showMessageDialog(this, "Giảm giá thanh cong");
                    //reset();
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (trangHD > 1) {
            trangHD--;
            loadData_tbl_HD(trangHD, 0);
            lbl_soTrang_HD.setText(trangHD + "/" + soTrangHD);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (trangHD < soTrangHD) {
            trangHD++;
            loadData_tbl_HD(trangHD, 0);
            lbl_soTrang_HD.setText(trangHD + "/" + soTrangHD);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (lbl_maHD1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để thực hiện");
        } else {
            index = tblHoaDon.getSelectedRow();
            int id = (int) tblHoaDon.getValueAt(index, 0);
            int rowhd = tblHoaDon.getSelectedRow();
            int idhd = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
            model.HoaDon hd = this.read_HD1(HDSV.getAll_HDCT(id));
            if (HDSV.update_TrangThai_HD2(hd, idhd) > 0) {
                JOptionPane.showMessageDialog(this, "Hủy thành công");
                int idHD = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
                CountDB_HDCT(idHD);
                if (countHDCT % 5 == 0) {
                    soTrangHDCT = countHDCT / 5;
                } else {
                    soTrangHDCT = countHDCT / 5 + 1;
                }
                lbl_soTrang_GH.setText("1/" + soTrangHDCT);
                loadData_tbl_HD(1, 0);
                loadData_tbl_GioHang(idHD, 1);
                loadData_tbl_SP(1);
                ResetHD();
                //reset();
                modelGioHang = (DefaultTableModel) tbl_GioHang.getModel();
                modelGioHang.setRowCount(0);

            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        // TODO add your handling code here:
        AddKH dialog = new AddKH(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
        FILL_TO_CBO_KhachHang();
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void txt_timspFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timspFocusLost
        // TODO add your handling code here:
        if (txt_timsp.getText().isEmpty()) {
            txt_timsp.setText("Tìm Kiếm");
            loadData_tbl_SP(1);
        }
    }//GEN-LAST:event_txt_timspFocusLost

    private void txt_timspMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_timspMousePressed
        // TODO add your handling code here:
        txt_timsp.setText(null);
    }//GEN-LAST:event_txt_timspMousePressed

    private void txt_timspKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timspKeyReleased
        // TODO add your handling code here:
        try {
            String search = "%" + txt_timsp.getText() + "%";
            if (txt_timsp.getText().isEmpty()) {
                loadData_tbl_SP(1);
            } else {
                List<model.SanPhamChiTiet> listsearch = spctService.seach1(search);
                fillTable(listsearch);
            }
        } catch (Exception e) {
            return;
        }
    }//GEN-LAST:event_txt_timspKeyReleased

    private void cbo_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_KHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_KHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a;
    private javax.swing.JButton btnXoaGH;
    private javax.swing.JButton btn_Pay;
    private javax.swing.JButton btn_TaoHD;
    private javax.swing.JLabel c;
    private javax.swing.JComboBox<String> cbo_KH;
    private javax.swing.JComboBox<String> cbo_Vouchers;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbl_maHD1;
    private javax.swing.JLabel lbl_soTrang_GH;
    private javax.swing.JLabel lbl_soTrang_HD;
    private javax.swing.JLabel lbl_soTrang_SP;
    private javax.swing.JLabel lbl_tennv;
    private javax.swing.JLabel lbl_tien_can_tra;
    private javax.swing.JLabel lbl_tong_tien;
    private javax.swing.JLabel ok;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tbl_GioHang;
    private javax.swing.JTable tbl_sanpham;
    private javax.swing.JTextField txt_sl_mua;
    private javax.swing.JTextField txt_timsp;
    private javax.swing.JLabel x;
    // End of variables declaration//GEN-END:variables
}
