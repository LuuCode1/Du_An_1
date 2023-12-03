/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.Mausac;
import model.SanPham;
import model.SanPhamChiTiet;
import model.Thuonghieu;

/**
 *
 * @author Asus
 */
public class HoaDonChiTiet_service {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<HoaDonChiTiet> select_idHD(int id) {
        sql = """
             SELECT    hoa_don.maHoaDon, san_pham.tensp, hoa_don_chi_tiet.soluong, hoa_don_chi_tiet.dongia
             FROM         hoa_don INNER JOIN
                                   hoa_don_chi_tiet ON hoa_don.idHoaDon = hoa_don_chi_tiet.idHoaDon INNER JOIN
                                   san_pham_chi_tiet ON hoa_don_chi_tiet.id_sp_chi_tiet = san_pham_chi_tiet.id_sp_chi_tiet INNER JOIN
                                   san_pham ON san_pham_chi_tiet.idsp = san_pham.idsp where hoa_don.idHoaDon = ?
             """;
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString(1));
                SanPhamChiTiet cpct = new SanPhamChiTiet(new SanPham(rs.getString(2)));
                HoaDonChiTiet hdct = new HoaDonChiTiet(rs.getInt(3), rs.getDouble(4), hd, cpct);
                list.add(hdct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public List<HoaDonChiTiet> Select_ma(String ma) {
//        List<HoaDonChiTiet> list = new ArrayList<>();
//        sql = """
//             SELECT    san_pham.masp, san_pham.tensp, mau_sac.tenMauSac, chat_lieu.tenChatLieu, thuong_hieu.tenThuongHieu, hoa_don_chi_tiet.soluong, hoa_don_chi_tiet.dongia
//             FROM         hoa_don_chi_tiet INNER JOIN
//                                   hoa_don ON hoa_don_chi_tiet.idHoaDon = hoa_don.idHoaDon INNER JOIN
//                                   san_pham_chi_tiet ON hoa_don_chi_tiet.id_sp_chi_tiet = san_pham_chi_tiet.id_sp_chi_tiet INNER JOIN
//                                   chat_lieu ON san_pham_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN
//                                   san_pham ON san_pham_chi_tiet.idsp = san_pham.idsp INNER JOIN
//                                   thuong_hieu ON san_pham.idThuongHieu = thuong_hieu.idThuongHieu INNER JOIN
//                                   mau_sac ON san_pham_chi_tiet.idMauSac = mau_sac.idMauSac where hoa_don.maHoaDon=?
//             """;
//        try {
//            con = DBconnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, ma);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Thuonghieu thuonghieu = new Thuonghieu(rs.getString(5));
//                SanPham sanpham = new SanPham(rs.getString(1), rs.getString(2), thuonghieu);
//                Mausac mausac = new Mausac(rs.getString(3));
//                ChatLieu chatlieu = new ChatLieu(rs.getString(4));
//                SanPhamChiTiet spct = new SanPhamChiTiet(sanpham, mausac, chatlieu);
//                HoaDonChiTiet hdct = new HoaDonChiTiet(rs.getInt(6),rs.getDouble(7), spct);
//                list.add(hdct);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
