/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import model.MauSacc;

/**
 *
 * @author Dat
 */
public class MauSaccService {

    public ArrayList<MauSacc> getAllMauSacc() {
        ArrayList<MauSacc> list = new ArrayList<>();
        String sql = "SELECT * FROM mau_sac";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                MauSacc ms = new MauSacc();
                ms.setMaMauSac(rs.getString("maMauSac"));
                ms.setTenMauSac(rs.getString("tenMauSac"));
                list.add(ms);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Integer AddMauSacc(MauSacc ms) {
        Integer row = null;
        String sql = "insert into mau_sac(maMauSac, tenMauSac)\n"
                + "values(?, ?)";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, ms.getMaMauSac());
            pstm.setString(2, ms.getTenMauSac());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public Integer UpdateMauSacc(MauSacc ms) {
        Integer row = null;
        String sql = "update mau_sac\n"
                + "set tenMauSac = ?\n"
                + "where maMauSac like ?";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(2, ms.getMaMauSac());
            pstm.setString(1, ms.getTenMauSac());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public Integer DeleteMauSacc(String maMauSac) {
        Integer row = null;
        String sql = "delete from mau_sac\n"
                + "where maMauSac like ?";
        Connection con = DBconnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maMauSac);
//            pstm.setString(2, ms.getTenMauSac());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }
}
