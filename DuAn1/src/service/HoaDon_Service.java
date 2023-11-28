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
        sql = "SELECT    maHoaDon, idKhachHang, idnguoi_dung, ngayban,trangthai FROM hoa_don";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
//            ps.setObject(1, tt);
            rs = ps.executeQuery();
            while (rs.next()) {
               HoaDon hd = new HoaDon();
               hd.setIdHD(rs.getInt("id"));
               hd.setMahD(rs.getString("maHoaDon"));
               hd.setKH(khsv.Check_ma(rs.getInt("idKhachHang")));
               hd.setNG(ndsv.Check_ma(rs.getInt("idnguoi_dung")));
               hd.setNgayban(rs.getDate("ngayban"));
               hd.setTrangThai(rs.getInt("trangthai"));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
}
