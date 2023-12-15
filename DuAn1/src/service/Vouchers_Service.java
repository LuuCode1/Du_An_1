/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.sql.*;
import model.Voucher;

/**
 *
 * @author leduc
 */
public class Vouchers_Service {
    
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;
    
    public Voucher findByMaVoucher(String tenVC) {
        sql = "SELECT * FROM  vouchers where tenVouchers =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tenVC);
            rs = ps.executeQuery();
            while (rs.next()) {
                Voucher vc = new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getInt(7),rs.getInt(8),rs.getString(9));
                return vc;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
    
    public int updateSL(Voucher vc, String tenVC) {
        sql = "UPDATE vouchers SET soluong = ? ,soluongdadung = ? WHERE tenVouchers = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, vc.getSoluong());
            ps.setObject(2, vc.getSoluongdadung());
            ps.setObject(3, tenVC);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
