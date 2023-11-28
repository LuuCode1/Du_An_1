/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.KhachHang;
import model.NguoiDung;

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

    public List<HoaDon> Select() {
        List<HoaDon> list = new ArrayList<>();
        sql = "SELECT    hoa_don.idHoaDon, hoa_don.maHoaDon, Nguoi_dung.maNguoiDung, khach_hang.maKhachHang, hoa_don.ngayban, hoa_don.trangthai\n"
                + "FROM         hoa_don LEFT  JOIN\n"
                + "                      Nguoi_dung ON hoa_don.idnguoi_dung = Nguoi_dung.idnguoi_dung LEFT  JOIN\n"
                + "                      khach_hang ON hoa_don.idKhachHang = khach_hang.idKhachHang ";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
//            ps.setObject(1, tt);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(null, rs.getString(4), null, null);
                NguoiDung nd = new NguoiDung(null, rs.getString(3), null, null, null, 0, null, 0);
                HoaDon hd = new HoaDon();
                hd = new HoaDon(rs.getInt(1), rs.getString(2), kh, nd, rs.getDate(5), 0, 0);
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
}
