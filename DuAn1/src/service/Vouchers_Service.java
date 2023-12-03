/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.sql.*;
import model.Vouchers;
/**
 *
 * @author leduc
 */
public class Vouchers_Service {
    
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;
    
    public Vouchers findByMaVoucher(String maVC) {
        sql = "SELECT * FROM  vouchers where maVouchers =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maVC);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vouchers vc = new Vouchers(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getDouble(7), rs.getString(8));
                return vc;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
