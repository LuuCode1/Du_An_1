/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Gongkinh;
import model.QLGK;
/**
 *
 * @author Asus
 */
public class QLGK_service {
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
                qlgk.setId(rs.getInt("idGongKinh"));
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

    public List<QLGK> selectByID(String a){
        String sql  = "select idGongKinh, maGongKinh, tenGongKinh from gong_kinh where maGongKinh like ? or tenGongKinh like ?";
        List<QLGK> list = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setObject(1, a);
            pstm.setObject(2, a);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                QLGK qlgk = new QLGK();
                qlgk.setId(rs.getInt("idGongKinh"));
                qlgk.setMaGK(rs.getString("maGongKinh"));
                qlgk.setTenGK(rs.getString("tenGongKinh"));
                list.add(qlgk);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;

    }
    public QLGK byID(String a){
        String sql  = "select idGongKinh, maGongKinh, tenGongKinh from gong_kinh where maGongKinh like ? ";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setObject(1, a);
            

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                QLGK qlgk = new QLGK();
                qlgk.setId(rs.getInt("idGongKinh"));
                qlgk.setMaGK(rs.getString("maGongKinh"));
                qlgk.setTenGK(rs.getString("tenGongKinh"));
                return  qlgk;
            }
        } catch (Exception e) {
        }
        return null;
}
}
