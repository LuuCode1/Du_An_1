/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.LoaiSP;
import model.Mausac;

/**
 *
 * @author leduc
 */
public class loaisp_service {
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;
    public List<LoaiSP> FILL_TO_CBO_LOAISP() {
        List<LoaiSP> list = new ArrayList<>();
        sql ="SELECT  maloai_sp, tenloai_sp FROM  loai_sp";
        try {
            con = DBconnect.getConnection();
            ps =  con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                 LoaiSP lsp = new LoaiSP(rs.getString(1), rs.getString(2));
                 list.add(lsp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        }
    
    public LoaiSP tenLoaiSP(String lsp){
        sql ="SELECT * FROM  loai_sp where tenloai_sp = ?";
        try {
            con = DBconnect.getConnection();
            ps =  con.prepareStatement(sql);
            ps.setObject(1, lsp);
            rs = ps.executeQuery();
            while (rs.next()) {                
                LoaiSP ms = new LoaiSP(rs.getInt(1), rs.getString(2),rs.getString(3));
                return ms;
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;
    }
}
