/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.NguoiDung;

/**
 *
 * @author Asus
 */
public class NguoiDung_Service {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<NguoiDung> check_All(String a) {
        List<NguoiDung> listnd = new ArrayList<>();
        sql = "SELECT    maNguoiDung, tenNguoi_dung, ngaysinh, matKhau, sdt, gioitinh, email, vaitro \n"
                + "FROM         Nguoi_dung   Nguoi_dung where maNguoiDung like ? or tenNguoi_dung like ? or email like ? ";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, a);
            ps.setObject(2, a);
            ps.setObject(3, a);
            rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung(
                        rs.getString("maNguoiDung"),
                        rs.getString("tenNguoi_dung"),
                        rs.getDate("ngaysinh"),
                        rs.getString("matKhau"), // Change here
                        rs.getString("sdt"),
                        rs.getInt("gioitinh"),
                        rs.getString("email"),
                        rs.getInt("vaitro"));
                listnd.add(nd);
            }
            return listnd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int update(NguoiDung nd, String ma) {
        sql = "Update Nguoi_dung set tenNguoi_dung =? ,ngaysinh = ? ,matKhau =?,sdt =?,gioitinh =?,email =?,vaitro =? where maNguoiDung =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, nd.getTenND());
            ps.setObject(2, nd.getNgaySinh());
            ps.setObject(3, nd.getMatKhau());
            ps.setObject(4, nd.getSDT());
            ps.setObject(5, nd.getGioiTinh());
            ps.setObject(6, nd.getEmail());
            ps.setObject(7, nd.getVaiTro());
            ps.setObject(8, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(String ma) {
        sql = "Delete from Nguoi_dung where maNguoiDung =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int Insert(NguoiDung nd) {
        sql = "INSERT INTO Nguoi_dung(maNguoiDung,tenNguoi_dung, ngaysinh,matKhau, sdt, gioitinh, email,vaitro)\n"
                + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nd.getMaND());
            ps.setObject(2, nd.getTenND());
            ps.setObject(3, nd.getNgaySinh());
            ps.setObject(4, nd.getMatKhau());
            ps.setObject(5, nd.getSDT());
            ps.setObject(6, nd.getGioiTinh());
            ps.setObject(7, nd.getEmail());
            ps.setObject(8, nd.getVaiTro());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public NguoiDung Check_MaNV(String manv) {
        sql = "SELECT    maNguoiDung, tenNguoi_dung, ngaysinh, matKhau, sdt, gioitinh, email, vaitro \n"
                + "FROM         Nguoi_dung   Nguoi_dung where maNguoiDung like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, manv);
            rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung(
                        rs.getString("maNguoiDung"),
                        rs.getString("tenNguoi_dung"),
                        rs.getDate("ngaysinh"),
                        rs.getString("matKhau"), // Change here
                        rs.getString("sdt"),
                        rs.getInt("gioitinh"),
                        rs.getString("email"),
                        rs.getInt("vaitro"));
                return nd;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NguoiDung> SelectAll() {
        List<NguoiDung> list = new ArrayList<>();
        sql = "SELECT     maNguoiDung, tenNguoi_dung, ngaysinh, matKhau, sdt, gioitinh, email, vaitro\n"
                + "FROM         Nguoi_dung";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung(
                        rs.getString("maNguoiDung"),
                        rs.getString("tenNguoi_dung"),
                        rs.getDate("ngaysinh"),
                        rs.getString("matKhau"), // Change here
                        rs.getString("sdt"),
                        rs.getInt("gioitinh"),
                        rs.getString("email"),
                        rs.getInt("vaitro"));
                list.add(nd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public NguoiDung dangNhap(String email, String maKhau) {
        sql = "SELECT  maNguoiDung, tenNguoi_dung, ngaysinh, matKhau, sdt, gioitinh, email, vaitro \n"
                + "FROM         Nguoi_dung where email like ? and matKhau like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, email);
            ps.setObject(2, maKhau);
            rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung(
                        rs.getString("maNguoiDung"),
                        rs.getString("tenNguoi_dung"),
                        rs.getDate("ngaysinh"),
                        rs.getString("matKhau"), // Change here
                        rs.getString("sdt"),
                        rs.getInt("gioitinh"),
                        rs.getString("email"),
                        rs.getInt("vaitro"));
                return nd;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NguoiDung dangNhap_email(String email) {
        sql = "SELECT     maNguoiDung, tenNguoi_dung, ngaysinh, matKhau, sdt, gioitinh, email, vaitro \n"
                + "FROM         Nguoi_dung   Nguoi_dung where email like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung(
                        rs.getString("maNguoiDung"),
                        rs.getString("tenNguoi_dung"),
                        rs.getDate("ngaysinh"),
                        rs.getString("matKhau"), // Change here
                        rs.getString("sdt"),
                        rs.getInt("gioitinh"),
                        rs.getString("email"),
                        rs.getInt("vaitro"));
                return nd;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public NguoiDung Check_ma(int ma) {
        sql = "SELECT     maNguoiDung, tenNguoi_dung, ngaysinh, matKhau, sdt, gioitinh, email, vaitro \n"
                + "FROM         Nguoi_dung   Nguoi_dung where idnguoi_dung = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung(
                        rs.getString("maNguoiDung"),
                        rs.getString("tenNguoi_dung"),
                        rs.getDate("ngaysinh"),
                        rs.getString("matKhau"), // Change here
                        rs.getString("sdt"),
                        rs.getInt("gioitinh"),
                        rs.getString("email"),
                        rs.getInt("vaitro"));
                return nd;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int update_matKhau(NguoiDung nd ,String email){
        sql ="Update Nguoi_dung set matKhau =? where email =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nd.getMatKhau());
            ps.setObject(2, email);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
