/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Gongkinh;
import model.chatLieu;
import model.mausac;
import model.thuonghieu;
//l

/**
 *
 * @author Asus
 */
public class GongKinh_Service {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    public List<Gongkinh> selectAll(String ma) {
        sql = "SELECT     chat_lieu.tenChatLieu,mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, gong_kinh_chi_tiet.giaThanh, gong_kinh_chi_tiet.soLuong, gong_kinh_chi_tiet.hinhanh, gong_kinh_chi_tiet.moTa, \n"
                + "                      gong_kinh_chi_tiet.trangThai\n"
                + "FROM         gong_kinh INNER JOIN\n"
                + "                      gong_kinh_chi_tiet ON gong_kinh.idGongKinh = gong_kinh_chi_tiet.idGongKinh INNER JOIN\n"
                + "                      chat_lieu ON gong_kinh_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN\n"
                + "                      thuong_hieu ON gong_kinh_chi_tiet.idThuongHieu = thuong_hieu.idThuongHieu INNER JOIN\n"
                + "                      mau_sac ON gong_kinh_chi_tiet.idMauSac = mau_sac.idMauSac where maGongKinh =? ";
        List<Gongkinh> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                chatLieu cl = new chatLieu(null, rs.getString(1));
                mausac ms = new mausac(null, rs.getString(2));
                thuonghieu th = new thuonghieu(null, rs.getString(3));
                Gongkinh gk = new Gongkinh(null, cl, ms, th, rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
                list.add(gk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public Gongkinh selectByID(String maGK) {
//        sql = "SELECT    gong_kinh.maGongKinh, gong_kinh.tenGongKinh, chat_lieu.tenChatLieu, mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, gong_kinh.giaThanh, gong_kinh.soLuong, gong_kinh.hinhanh, gong_kinh.moTa\n"
//                + "FROM         chat_lieu INNER JOIN\n"
//                + "                      gong_kinh ON chat_lieu.idChatLieu = gong_kinh.idChatLieu INNER JOIN\n"
//                + "                      mau_sac ON gong_kinh.idMauSac = mau_sac.idMauSac INNER JOIN\n"
//                + "                      thuong_hieu ON gong_kinh.idThuongHieu = thuong_hieu.idThuongHieu where gong_kinh.maGongKinh =?";
//        try {
//            con = DBconnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, maGK);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                chatLieu cl = new chatLieu(null, rs.getString(3));
//                mausac ms = new mausac(null, rs.getString(4));
//                thuonghieu th = new thuonghieu(null, rs.getString(5));
//                Gongkinh gk = new Gongkinh(rs.getString(1),
//                        rs.getString(2),
//                        cl,
//                        ms,
//                        th,
//                        rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9));
//                return gk;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return null;
//    }
//
//    public int Insert(Gongkinh gk) {
//        sql = "INSERT INTO gong_kinh(maGongKinh,tenGongKinh,idChatLieu,idMauSac,idThuongHieu,giaThanh,soLuong,hinhAnh,moTa)\n"
//                + "VALUES (?,?,?,?,?,?,?,?,?)";
//        try {
//            con = DBconnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, gk.getMaGongKinh());
//            ps.setObject(2, gk.getTenGongKinh());
//            ps.setObject(3, gk.getTenChatLieu().getIdChatLieu());
//            ps.setObject(4, gk.getTenMauSac().getIdMauSac());
//            ps.setObject(5, gk.getTenThuongHieu().getIdThuongHieu());
//            ps.setObject(6, gk.getGiaThanh());
//            ps.setObject(7, gk.getSoLuong());
//            ps.setObject(8, gk.getHinhAnh());
//            ps.setObject(9, gk.getMoTa());
//            return ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    public int delete(String ma) {
//        sql = "Delete from gong_kinh where maGongKinh =?";
//        try {
//            con = DBconnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, ma);
//            return ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    public int update(Gongkinh gk, String ma) {
//        sql = "UPDATE gong_kinh SET tenGongKinh = ?, idChatLieu = ?, idMauSac = ?, idThuongHieu = ?, giaThanh = ?, soLuong = ?, hinhanh = ?, moTa = ? "
//                + "WHERE maGongKinh = ?";
//        try {
//            con = DBconnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, gk.getTenGongKinh());
//            ps.setObject(2, gk.getTenChatLieu().getIdChatLieu());
//            ps.setObject(3, gk.getTenMauSac().getIdMauSac());
//            ps.setObject(4, gk.getTenThuongHieu().getIdThuongHieu());
//            ps.setObject(5, gk.getGiaThanh());
//            ps.setObject(6, gk.getSoLuong());
//            ps.setObject(7, gk.getHinhAnh());
//            ps.setObject(8, gk.getMoTa());
//            ps.setObject(9, ma);
//            return ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    public List<Gongkinh> check_Cbo(String thuongHieu) {
//        sql = "SELECT    gong_kinh.maGongKinh, gong_kinh.tenGongKinh, chat_lieu.tenChatLieu, mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, gong_kinh.giaThanh, gong_kinh.soLuong, gong_kinh.hinhanh, gong_kinh.moTa\n"
//                + "FROM chat_lieu INNER JOIN\n"
//                + " gong_kinh ON chat_lieu.idChatLieu = gong_kinh.idChatLieu INNER JOIN\n"
//                + "  mau_sac ON gong_kinh.idMauSac = mau_sac.idMauSac INNER JOIN\n"
//                + " thuong_hieu ON gong_kinh.idThuongHieu = thuong_hieu.idThuongHieu\n"
//                + " where thuong_hieu.tenThuongHieu = ? ";
//        List<Gongkinh> gk = new ArrayList<>();
//        try {
//            con = DBconnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, thuongHieu);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                chatLieu cl = new chatLieu(null, rs.getString(3));
//                mausac ms = new mausac(null, rs.getString(4));
//                thuonghieu th = new thuonghieu(null, rs.getString(5));
//                Gongkinh gongkinh = new Gongkinh(rs.getString(1),
//                        rs.getString(2),
//                        cl,
//                        ms,
//                        th,
//                        rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9));
//                gk.add(gongkinh);
//            }
//            return gk;
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return null;
//    }
//
//    public List<Gongkinh> seach(String a) {
//        List<Gongkinh> list = new ArrayList<>();
//        sql = "SELECT    gong_kinh.maGongKinh, gong_kinh.tenGongKinh, chat_lieu.tenChatLieu, mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, gong_kinh.giaThanh, gong_kinh.soLuong, gong_kinh.hinhanh, gong_kinh.moTa\n"
//                + "FROM chat_lieu INNER JOIN\n"
//                + "  gong_kinh ON chat_lieu.idChatLieu = gong_kinh.idChatLieu INNER JOIN\n"
//                + " mau_sac ON gong_kinh.idMauSac = mau_sac.idMauSac INNER JOIN\n"
//                + " thuong_hieu ON gong_kinh.idThuongHieu = thuong_hieu.idThuongHieu\n"
//                + " where gong_kinh.maGongKinh like ? or gong_kinh.tenGongKinh like ? or chat_lieu.tenChatLieu like ? or mau_sac.tenMauSac like ? or thuong_hieu.tenThuongHieu like ?";
//        try {
//            con = DBconnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, a);
//            ps.setObject(2, a);
//            ps.setObject(3, a);
//            ps.setObject(4, a);
//            ps.setObject(5, a);
//            rs = ps.executeQuery();
//            while (rs.next()) {                
//                chatLieu cl = new chatLieu(null, rs.getString(3));
//                mausac ms = new mausac(null, rs.getString(4));
//                thuonghieu th = new thuonghieu(null, rs.getString(5));
//                Gongkinh gongkinh = new Gongkinh(rs.getString(1),
//                        rs.getString(2),
//                        cl,
//                        ms,
//                        th,
//                        rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9));
//                list.add(gongkinh);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
