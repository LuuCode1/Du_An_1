/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
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

    public NguoiDung dangNhap(String email, String maKhau) {
        sql = "SELECT * FROM   Nguoi_dung where email like ? and matKhau like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, email);
            ps.setObject(2, maKhau);
            rs = ps.executeQuery();
            while (rs.next()) {                
                NguoiDung nd = new NguoiDung();
                
                nd = new NguoiDung(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8));
                return nd;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     public NguoiDung dangNhap_email(String email) {
        sql = "SELECT * FROM   Nguoi_dung where email like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {                
                NguoiDung nd = new NguoiDung();
                
                nd = new NguoiDung(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8));
                return nd;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
