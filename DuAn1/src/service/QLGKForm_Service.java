/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.QLGK;
import model.QLGK;

/**
 *
 * @author Dat
 */
public class QLGK_Service {

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

    public int UpdateQLGK(QLGK qlgk,String ma) {
        
        String sql = "update gong_kinh\n"
                + "set tenGongKinh = ?\n"
                + "where maGongKinh like ?";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(2, ma);
            pstm.setString(1, qlgk.getTenGK());
            return pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
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
<<<<<<<< HEAD:DuAn1/src/service/QLGKForm_Service.java
    public List<QLGK> selectByID(String a){
        String sql  = "select idGongKinh, maGongKinh, tenGongKinh from gong_kinh where maGongKinh like ? or tenGongKinh like ?";
        List<QLGK> list = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setObject(1, a);
            pstm.setObject(2, a);
========

    public ArrayList<QLGK> Search(String gktk) {
        ArrayList<QLGK> list = new ArrayList<>();
        String sql = "select idGongKinh, maGongKinh, tenGongKinh\n"
                + "from gong_kinh\n"
                + "where maGongKinh like ? \n"
                + "	  or tenGongKinh like ?";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, gktk);
            pstm.setString(2, gktk);
>>>>>>>> d2169ee4b1ad88832aa3823722258252853702a3:DuAn1/src/service/QLGK_Service.java
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                QLGK qlgk = new QLGK();
                qlgk.setId(rs.getString("idGongKinh"));
                qlgk.setMaGK(rs.getString("maGongKinh"));
                qlgk.setTenGK(rs.getString("tenGongKinh"));
                list.add(qlgk);
            }
<<<<<<<< HEAD:DuAn1/src/service/QLGKForm_Service.java
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;
========
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
>>>>>>>> d2169ee4b1ad88832aa3823722258252853702a3:DuAn1/src/service/QLGK_Service.java
    }
}
