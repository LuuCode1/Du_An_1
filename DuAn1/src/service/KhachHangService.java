/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import model.KhachHang;
import model.NguoiDung;

/**
 *
 * @author Dat
 */
public class KhachHangService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

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

    public ArrayList<KhachHang> SearchKhachHang(String texttk) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "select * from khach_hang\n"
                + "where maKhachHang like ?\n"
                + "	  or tenKhachHang like ?\n"
                + "	  or diachi like ?\n"
                + "	  or sdt like ?";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, texttk);
            pstm.setString(2, texttk);
            pstm.setString(3, texttk);
            pstm.setString(4, texttk);
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

    public KhachHang Check_ma(int ma) {
        sql = "SELECT * FROM khach_hang where idKhachHang like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("maKhachHang"));
                kh.setTenKH(rs.getString("tenKhachHang"));
                kh.setDiaChi(rs.getString("diaChi"));
                kh.setSdt(rs.getString("sdt"));
                return kh;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public KhachHang Check_maKH(String ma) {
        sql = "SELECT * FROM khach_hang where maKhachHang like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("maKhachHang"));
                kh.setTenKH(rs.getString("tenKhachHang"));
                kh.setDiaChi(rs.getString("diaChi"));
                kh.setSdt(rs.getString("sdt"));
                return kh;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public KhachHang findBYTenKH(String tenKH) {
        sql = "SELECT * FROM  khach_hang where tenKhachHang =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tenKH);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
    public KhachHang findBYIDKH(int id) {
        sql = "SELECT * FROM  khach_hang where idKhachHang =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
