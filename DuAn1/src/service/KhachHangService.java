/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;


import java.sql.*;
import java.util.ArrayList;
import model.KhachHang;

/**
 *
 * @author Dat
 */
public class KhachHangService {

    public ArrayList<KhachHang> getAllKhachHang() {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("maKhachHang"));
                kh.setTenKH(rs.getString("tenKhachHang"));
                kh.setDiaChi(rs.getString("diaChi"));
                kh.setSdt(rs.getString("sdt"));
                list.add(kh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Integer addKhachHang(KhachHang kh) {
        Integer row = null;
        String sql = "insert into khach_hang(maKhachHang, tenKhachHang, diachi, sdt)\n"
                + "values(?, ?, ?, ?)";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, kh.getMaKH());
            pstm.setString(2, kh.getTenKH());
            pstm.setString(3, kh.getDiaChi());
            pstm.setString(4, kh.getSdt());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public Integer updateKhachHang(KhachHang kh) {
        Integer row = null;
        String sql = "update khach_hang\n"
                + "set tenKhachHang = ?, diachi = ?, sdt = ?\n"
                + "where maKhachHang = ?";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(4, kh.getMaKH());
            pstm.setString(1, kh.getTenKH());
            pstm.setString(2, kh.getDiaChi());
            pstm.setString(3, kh.getSdt());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public Integer deleteKhachHang(String maKH) {
        Integer row = null;
        String sql = "delete from khach_hang\n"
                + "where maKhachHang like ?";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maKH);
//            pstm.setString(2, kh.getTenKH());
//            pstm.setString(3, kh.getDiaChi());
//            pstm.setString(4, kh.getSdt());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public KhachHang findById(String maKH) {
        String sql = "SELECT * FROM khach_hang where maKhachHang = ? ";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            pstm.setString(1, maKH);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("maKhachHang"));
                kh.setTenKH(rs.getString("tenKhachHang"));
                kh.setDiaChi(rs.getString("diaChi"));
                kh.setSdt(rs.getString("sdt"));
                
                return kh;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
