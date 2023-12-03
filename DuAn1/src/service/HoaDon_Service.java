/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.Mausac;
import model.NguoiDung;
import model.SanPham;
import model.SanPhamChiTiet;
import model.Thuonghieu;

/**
 *
 * @author Asus
 */
public class HoaDon_Service {

    NguoiDung_Service ndsv = new NguoiDung_Service();
    KhachHangService khsv = new KhachHangService();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<HoaDon> Select(int trangthai) {
        List<HoaDon> list = new ArrayList<>();
        sql = "SELECT    hoa_don.maHoaDon, Nguoi_dung.tenNguoi_dung, khach_hang.tenKhachHang, hoa_don.ngayban\n" +
"FROM         hoa_don LEFT JOIN\n" +
"                      Nguoi_dung ON hoa_don.idnguoi_dung = Nguoi_dung.idnguoi_dung LEFT JOIN\n" +
"                      khach_hang ON hoa_don.idKhachHang = khach_hang.idKhachHang where hoa_don.trangthai=?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(null, rs.getString(3), null, null);
                NguoiDung nd = new NguoiDung(null, rs.getString(2), null, null, null, 0, null, 0);
                HoaDon hd = new HoaDon(rs.getString(1), kh, nd, rs.getDate(4));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public int Insert(HoaDon hd) {
        sql = "INSERT INTO hoa_don(maHoaDon,trangthai)\n"
                + "VALUES ( ?,?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getMahD());
            ps.setObject(2, hd.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<HoaDonChiTiet> getListHoaDonChiTiet(String MaHD) {
        List<HoaDonChiTiet> getList = new ArrayList<>();
        try {
            String sql = "select sp.masp, sp.tensp, m.tenMauSac, c.tenChatLieu, t.tenThuongHieu, hdct.dongia, hdct.soluong\n"
                    + "from hoa_don hd left join hoa_don_chi_tiet hdct on hd.idHoaDon = hdct.idHoaDon\n"
                    + "left join san_pham_chi_tiet spct on hdct.id_sp_chi_tiet = spct.id_sp_chi_tiet\n"
                    + "left join san_pham sp on sp.idsp = spct.idsp\n"
                    + "left join mau_sac m on spct.idMauSac = m.idMauSac\n"
                    + "left join chat_lieu c on spct.idChatLieu = c.idChatLieu\n"
                    + "left join thuong_hieu t on sp.idThuongHieu = t.idThuongHieu"
                    + "  WHERE hd.maHoaDon = ?";
            Connection conn = DBconnect.getConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, MaHD);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setSp(new SanPham(rs.getString(1), rs.getString(2), null, new Thuonghieu(null, rs.getString(5))));
//                spct.setDoCan(rs.getDouble(7));
                spct.setColor(new Mausac(null, rs.getString(3)));
                spct.setMaterial(new ChatLieu(null, rs.getString(4)));
                hdct.setSanPham(spct);
                hdct.setDonGia(rs.getDouble(6));
                hdct.setSoluong(rs.getInt(7));
                getList.add(hdct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getList;
    }

    public List<HoaDon> LichSuHoaDon(int a, int b) {
        List<HoaDon> listhd = new ArrayList<>();
        sql = """
             SELECT hoa_don.idHoaDon, hoa_don.maHoaDon, Nguoi_dung.tenNguoi_dung, khach_hang.tenKhachHang, hoa_don.ngayban,hoa_don.tongtien, hoa_don.trangthai
             FROM hoa_don
             LEFT JOIN Nguoi_dung ON hoa_don.idnguoi_dung = Nguoi_dung.idnguoi_dung
             LEFT JOIN khach_hang ON hoa_don.idKhachHang = khach_hang.idKhachHang
             WHERE hoa_don.trangthai IN (?, ?)
             """;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, a);
            ps.setObject(2, b);
            rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung(null, rs.getString(3), null, null, null, 0, null, 0);
                KhachHang kh = new KhachHang(null, rs.getString(4), null, null);
                HoaDon hd = new HoaDon(rs.getInt(1),
                        rs.getString(2), kh, nd,
                        rs.getDate(5),
                        rs.getDouble(6),
                        rs.getInt(7));
                listhd.add(hd);
            }
            return listhd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> LichSuHoaDon_ngaythang(int a, int b, Date n, Date m) {
        List<HoaDon> listhd = new ArrayList<>();
        sql = """
             SELECT hoa_don.idHoaDon, hoa_don.maHoaDon, Nguoi_dung.tenNguoi_dung, khach_hang.tenKhachHang, hoa_don.ngayban, hoa_don.trangthai
                                   FROM hoa_don
                                   LEFT JOIN Nguoi_dung ON hoa_don.idnguoi_dung = Nguoi_dung.idnguoi_dung
                                   LEFT JOIN khach_hang ON hoa_don.idKhachHang = khach_hang.idKhachHang
                                   WHERE hoa_don.trangthai IN (?, ?)
                                   AND hoa_don.ngayban BETWEEN ? AND ?;
             """;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, a);
            ps.setObject(2, b);
            ps.setObject(3, n);
            ps.setObject(4, m);
            rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung(null, rs.getString(3), null, null, null, 0, null, 0);
                KhachHang kh = new KhachHang(null, rs.getString(4), null, null);
                HoaDon hd = new HoaDon(rs.getInt(1),
                        rs.getString(2), kh, nd,
                        rs.getDate(5),
                        rs.getDouble(6),
                        rs.getInt(7));
                listhd.add(hd);
            }
            return listhd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
