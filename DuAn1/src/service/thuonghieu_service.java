/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Mausac;
import model.Thuonghieu;

/**
 *
 * @author Asus
 */
//l
public class thuonghieu_service {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    public List<Thuonghieu> FILL_TO_CBO_ThuongHieu() {
        List<Thuonghieu> list = new ArrayList<>();
        sql = "SELECT  maThuongHieu, tenThuongHieu FROM  thuong_hieu";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Thuonghieu th = new Thuonghieu(rs.getString(1), rs.getString(2));
                list.add(th);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Thuonghieu tenThuongHieu(String th) {
        sql = "SELECT * FROM  thuong_hieu where tenThuongHieu =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, th);
            rs = ps.executeQuery();
            while (rs.next()) {
                Thuonghieu ms = new Thuonghieu(rs.getInt(1), rs.getString(2), rs.getString(3));
                return ms;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public int insert(Thuonghieu th) {
        sql = "INSERT INTO thuong_hieu(maThuongHieu, tenThuongHieu)\n"
                + "VALUES (?, ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, th.getMaThuongHieu());
            ps.setObject(2, th.getTenThuongHieu());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
        public Thuonghieu maThuongHieu(String th) {
        sql = "SELECT * FROM  thuong_hieu where maThuongHieu =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, th);
            rs = ps.executeQuery();
            while (rs.next()) {
                Thuonghieu ms = new Thuonghieu(rs.getInt(1), rs.getString(2), rs.getString(3));
                return ms;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public int update_TH(Thuonghieu th,String ma) {
        sql = """
              Update thuong_hieu set tenThuongHieu = ? where maThuongHieu =?
              """;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, th.getTenThuongHieu());
            ps.setObject(2, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int delete_TH(String ma) {
        sql = """
              Delete from thuong_hieu where maThuongHieu =?
              """;
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
}
