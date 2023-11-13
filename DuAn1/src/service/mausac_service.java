/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.mausac;
/**
 *
 * @author Asus
 */
public class mausac_service {
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;
    public List<mausac> FILL_TO_CBO_MauSac() {
        List<mausac> list = new ArrayList<>();
        sql ="SELECT  maMauSac, tenMauSac FROM  mau_sac";
        try {
            con = DBconnect.getConnection();
            ps =  con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                 mausac ms = new mausac(rs.getString(1), rs.getString(2));
                 list.add(ms);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        }
    public mausac tenMauSac(String mau){
        sql ="SELECT * FROM  mau_sac where tenMauSac =?";
        try {
            con = DBconnect.getConnection();
            ps =  con.prepareStatement(sql);
            ps.setObject(1, mau);
            rs = ps.executeQuery();
            while (rs.next()) {                
                mausac ms = new mausac(rs.getInt(1), rs.getString(2),rs.getString(3));
                return ms;
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;
    }
    public int insert(mausac ms){
         sql ="insert into mau_sac( maMauSac, tenMauSac) values (?,?) ";
         try {
             con = DBconnect.getConnection();
            ps =  con.prepareStatement(sql);
            ps.setObject(1, ms.getMaMauSac());
            ps.setObject(2, ms.getTenMauSac());
            return ps.executeUpdate();
         } catch (Exception e) {
             e.printStackTrace();
             return 0;
         }
     }
}
