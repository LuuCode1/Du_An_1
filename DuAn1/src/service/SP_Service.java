/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.SanPham;
import java.sql.*;
import model.LoaiSP;
import model.Thuonghieu;

/**
 *
 * @author leduc
 */
public class SP_Service {

    public List<SanPham> getAllSP() {
        List<SanPham> Listtk = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT idsp,masp,tensp,lsp.idloai_sp,lsp.tenloai_sp,th.idThuongHieu,th.tenThuongHieu\n"
                    + "from san_pham sp INNER JOIN\n"
                    + "loai_sp lsp ON lsp.idloai_sp = sp.idloai_sp INNER JOIN\n"
                    + "thuong_hieu th ON th.idThuongHieu = sp.idThuongHieu ";
            conn = DBconnect.getConnection();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSP(rs.getInt(1));
                sp.setMaSP(rs.getString(2));
                sp.setTenSP(rs.getString(3));
                sp.setLoaisp(new LoaiSP(rs.getString(4), rs.getString(5)));
                sp.setBrand(new Thuonghieu(rs.getString(6), rs.getString(7)));
                Listtk.add(sp);
            }
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        } finally {
            try {
                rs.close();
                sttm.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return Listtk;
    }

    public int addSP(SanPham sp) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "INSERT INTO san_pham(masp,tensp,idloai_sp,idThuongHieu)\n"
                    + "VALUES (?, ? , ? , ? )";
            conn = DBconnect.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, sp.getMaSP());
            sttm.setString(2, sp.getTenSP());
            sttm.setInt(3, sp.getLoaisp().getIdLoaiSP());
            sttm.setInt(4, sp.getBrand().getIdThuongHieu());
            if (sttm.executeUpdate() > 0) {
                System.out.println("Them thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        } finally {
            try {
                conn.close();
                sttm.close();
            } catch (Exception e) {
            }
        }
        return -1;//Neu ko them thanh cong
    }

    public int updateSP(SanPham sp) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sql = "UPDATE san_pham SET tensp = ?,idloai_sp = ?,idThuongHieu = ?\n"
                    + "WHERE masp = ?";
            conn = DBconnect.getConnection();
            sttm = conn.prepareStatement(sql);
            sttm.setString(4, sp.getMaSP());
            sttm.setString(1, sp.getTenSP());
            sttm.setInt(2, sp.getLoaisp().getIdLoaiSP());
            sttm.setInt(3, sp.getBrand().getIdThuongHieu());
            if (sttm.executeUpdate() > 0) {
                System.out.println("update thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                conn.close();
                sttm.close();
            } catch (Exception e) {
            }
        }
        return -1;
    }

    public int DeleteSP(String maSP) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sql = "DELETE san_pham WHERE masp = ?";
            conn = DBconnect.getConnection();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, maSP);
            if (sttm.executeUpdate() > 0) {
                System.out.println("delete thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                conn.close();
                sttm.close();
            } catch (Exception e) {
            }
        }
        return -1;
    }

    public List<SanPham> seach(String a) {
        List<SanPham> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sSQL = "SELECT sp.idsp,sp.masp,sp.tensp,lsp.tenloai_sp,th.tenThuongHieu\n"
                + "from san_pham sp INNER JOIN\n"
                + "loai_sp lsp ON lsp.idloai_sp = sp.idloai_sp INNER JOIN\n"
                + "thuong_hieu th ON th.idThuongHieu = sp.idThuongHieu\n"
                + " where sp.masp like ? or sp.tensp like ? or lsp.tenloai_sp like ? or th.tenThuongHieu like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sSQL);
            ps.setObject(1, a);
            ps.setObject(2, a);
            ps.setObject(3, a);
            ps.setObject(4, a);
            rs = ps.executeQuery();
            while (rs.next()) {
                LoaiSP lsp = new LoaiSP(null, rs.getString(4));
                Thuonghieu th = new Thuonghieu(null, rs.getString(5));
                SanPham sp = new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3), lsp, th);
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;
    
    public SanPham selectByID(int id) {
        sql = " SELECT    san_pham.idsp, san_pham.masp, san_pham.tensp, loai_sp.tenloai_sp, thuong_hieu.tenThuongHieu\n" +
"FROM         san_pham INNER JOIN\n" +
"                      loai_sp ON san_pham.idloai_sp = loai_sp.idloai_sp INNER JOIN\n" +
"                      thuong_hieu ON san_pham.idThuongHieu = thuong_hieu.idThuongHieu where san_pham.idsp = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                LoaiSP lsp = new LoaiSP(null, rs.getString(4));
                Thuonghieu th = new Thuonghieu(null, rs.getString(5));
                SanPham sp = new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3), lsp, th);
                
                return sp;
                
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
