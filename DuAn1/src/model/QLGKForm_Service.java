/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import model.QLGK;

/**
 *
 * @author Dat
 */
public class QLGKForm_Service {

    public ArrayList<QLGK> getALLQLGK() {
        ArrayList<QLGK> list = new ArrayList<>();
        String sql = "select idGongKinh, maGongKinh, tenGongKinh\n"
                + "from gong_kinh";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                QLGK qlgk = new QLGK();
                qlgk.setId(rs.getString("idGongKinh"));
                qlgk.setMaGK(rs.getString("maGongKinh"));
                qlgk.setTenGK(rs.getString("tenGongKinh"));
                list.add(qlgk);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Integer AddQLGK(QLGK qlgk) {
        Integer row = null;
        String sql = "insert into gong_kinh(maGongKinh, tenGongKinh)\n"
                + "values (?, ?)";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, qlgk.getMaGK());
            pstm.setString(2, qlgk.getTenGK());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public Integer UpdateQLGK(QLGK qlgk) {
        Integer row = null;
        String sql = "update gong_kinh\n"
                + "set tenGongKinh = ?\n"
                + "where maGongKinh like ?";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(2, qlgk.getMaGK());
            pstm.setString(1, qlgk.getTenGK());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public Integer DeleteQLGK(String maGongKinh) {
        Integer row = null;
        String sql = "delete from gong_kinh\n"
                + "where maGongKinh like ?";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maGongKinh);
            //pstm.setString(2, qlgk.getTenGK());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }
}
