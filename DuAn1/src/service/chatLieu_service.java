/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.chatLieu;
import model.mausac;
/**
 *
 * @author Asus
 */
//s
public class chatLieu_service {
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;
    public List<chatLieu> FILL_TO_CBO_ChatLieu() {
        List<chatLieu> list = new ArrayList<>();
        sql ="SELECT  maChatLieu, tenChatLieu FROM  chat_lieu";
        try {
            con = DBconnect.getConnection();
            ps =  con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                 chatLieu cl = new chatLieu(rs.getString(1), rs.getString(2));
                 list.add(cl);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        }
     public chatLieu tenchatLieu(String chatlieu){
        sql ="SELECT * FROM  chat_lieu where tenChatLieu =?";
        try {
            con = DBconnect.getConnection();
            ps =  con.prepareStatement(sql);
            ps.setObject(1, chatlieu);
            rs = ps.executeQuery();
            while (rs.next()) {                
                chatLieu ms = new chatLieu(rs.getInt(1), rs.getString(2),rs.getString(3));
                return ms;
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;
    }
     public int insert(chatLieu cl){
         sql ="insert into chat_lieu( maChatLieu, tenChatLieu) values (?,?) ";
         try {
             con = DBconnect.getConnection();
            ps =  con.prepareStatement(sql);
            ps.setObject(1, cl.getMaChatLieu());
            ps.setObject(2, cl.getTenChatLieu());
            return ps.executeUpdate();
         } catch (Exception e) {
             e.printStackTrace();
             return 0;
         }
     }
    
}
