/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.GongKinhChiTiet;
import model.QLGK;
import model.ChatLieu;
import model.Mausac;
import model.Thuonghieu;
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

    public List<GongKinhChiTiet> selectAll(int ma) {
        sql = "SELECT  gong_kinh_chi_tiet.idGongKinhCT,chat_lieu.tenChatLieu,mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, gong_kinh_chi_tiet.giaThanh, gong_kinh_chi_tiet.soLuong, gong_kinh_chi_tiet.hinhanh, gong_kinh_chi_tiet.moTa, \n"
                + "                      gong_kinh_chi_tiet.trangThai\n"
                + "FROM         gong_kinh INNER JOIN\n"
                + "                      gong_kinh_chi_tiet ON gong_kinh.idGongKinh = gong_kinh_chi_tiet.idGongKinh INNER JOIN\n"
                + "                      chat_lieu ON gong_kinh_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN\n"
                + "                      thuong_hieu ON gong_kinh_chi_tiet.idThuongHieu = thuong_hieu.idThuongHieu INNER JOIN\n"
                + "                      mau_sac ON gong_kinh_chi_tiet.idMauSac = mau_sac.idMauSac\n"
                + "					  where gong_kinh.idGongKinh = ?";
        List<GongKinhChiTiet> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                Thuonghieu th = new Thuonghieu(null, rs.getString(4));
                GongKinhChiTiet gk = new GongKinhChiTiet(rs.getInt(1), null, cl, ms, th, rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
                list.add(gk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public QLGK Show(int id) {

        sql = "SELECT maGongKinh, tenGongKinh FROM gong_kinh WHERE idGongKinh = ?";

        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maGongKinh = rs.getString("maGongKinh");
                String tenGongKinh = rs.getString("tenGongKinh");
                QLGK gk = new QLGK(maGongKinh, tenGongKinh);
                return gk;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public GongKinhChiTiet selectByID(int gkct) {
        sql = "SELECT     gong_kinh_chi_tiet.idGongKinhCT,chat_lieu.tenChatLieu,mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, gong_kinh_chi_tiet.giaThanh, gong_kinh_chi_tiet.soLuong, gong_kinh_chi_tiet.hinhanh, gong_kinh_chi_tiet.moTa, \n"
                + "                      gong_kinh_chi_tiet.trangThai\n"
                + "FROM         gong_kinh INNER JOIN\n"
                + "                      gong_kinh_chi_tiet ON gong_kinh.idGongKinh = gong_kinh_chi_tiet.idGongKinh INNER JOIN\n"
                + "                      chat_lieu ON gong_kinh_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN\n"
                + "                      thuong_hieu ON gong_kinh_chi_tiet.idThuongHieu = thuong_hieu.idThuongHieu INNER JOIN\n"
                + "                      mau_sac ON gong_kinh_chi_tiet.idMauSac = mau_sac.idMauSac\n"
                + "					  where gong_kinh_chi_tiet.idGongKinhCT = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, gkct);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                Thuonghieu th = new Thuonghieu(null, rs.getString(4));
                GongKinhChiTiet gk = new GongKinhChiTiet(rs.getInt(1), null, cl, ms, th, rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
                return gk;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public int Insert(GongKinhChiTiet gk) {
        sql = "INSERT INTO gong_kinh_chi_tiet(idGongKinh,idChatLieu,idMauSac,idThuongHieu,giaThanh,soLuong,hinhAnh,moTa,trangThai)\n"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, gk.getGongKinh().getId());
            ps.setObject(2, gk.getTenChatLieu().getIdChatLieu());
            ps.setObject(3, gk.getTenMauSac().getIdMauSac());
            ps.setObject(4, gk.getTenThuongHieu().getIdThuongHieu());
            ps.setObject(5, gk.getGiaThanh());
            ps.setObject(6, gk.getSoLuong());
            ps.setObject(7, gk.getHinhAnh());
            ps.setObject(8, gk.getMoTa());
            ps.setObject(9, gk.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(int ma) {
        sql = "Delete from gong_kinh_chi_tiet where idGongKinhCT =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(GongKinhChiTiet gk, int ma) {
        sql = "UPDATE gong_kinh_chi_tiet SET  idChatLieu = ?, idMauSac = ?, idThuongHieu = ?, giaThanh = ?, soLuong = ?, hinhanh = ?, moTa = ? ,trangThai = ? WHERE gong_kinh_chi_tiet.idGongKinhCT = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, gk.getTenChatLieu().getIdChatLieu());
            ps.setObject(2, gk.getTenMauSac().getIdMauSac());
            ps.setObject(3, gk.getTenThuongHieu().getIdThuongHieu());
            ps.setObject(4, gk.getGiaThanh());
            ps.setObject(5, gk.getSoLuong());
            ps.setObject(6, gk.getHinhAnh());
            ps.setObject(7, gk.getMoTa());
            ps.setObject(8, gk.getTrangThai());
            ps.setObject(9, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<GongKinhChiTiet> check_Cbo(int ma, String thuongHieu) {
        sql = "SELECT  gong_kinh_chi_tiet.idGongKinhCT,chat_lieu.tenChatLieu,mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, gong_kinh_chi_tiet.giaThanh, gong_kinh_chi_tiet.soLuong, gong_kinh_chi_tiet.hinhanh, gong_kinh_chi_tiet.moTa, \n"
                + "                      gong_kinh_chi_tiet.trangThai\n"
                + "FROM         gong_kinh INNER JOIN\n"
                + "                      gong_kinh_chi_tiet ON gong_kinh.idGongKinh = gong_kinh_chi_tiet.idGongKinh INNER JOIN\n"
                + "                      chat_lieu ON gong_kinh_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN\n"
                + "                      thuong_hieu ON gong_kinh_chi_tiet.idThuongHieu = thuong_hieu.idThuongHieu INNER JOIN\n"
                + "                      mau_sac ON gong_kinh_chi_tiet.idMauSac = mau_sac.idMauSac\n"
                + "					  where gong_kinh.idGongKinh = ? and thuong_hieu.tenThuongHieu = ? ";
        List<GongKinhChiTiet> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            ps.setObject(2, thuongHieu);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                Thuonghieu th = new Thuonghieu(null, rs.getString(4));
                GongKinhChiTiet gk = new GongKinhChiTiet(rs.getInt(1), null, cl, ms, th, rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
                list.add(gk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GongKinhChiTiet> seach(int ma, String a) {
        List<GongKinhChiTiet> list = new ArrayList<>();
        sql = "SELECT  gong_kinh_chi_tiet.idGongKinhCT,chat_lieu.tenChatLieu,mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, gong_kinh_chi_tiet.giaThanh, gong_kinh_chi_tiet.soLuong, gong_kinh_chi_tiet.hinhanh, gong_kinh_chi_tiet.moTa, \n"
                + "                      gong_kinh_chi_tiet.trangThai\n"
                + "FROM         gong_kinh INNER JOIN\n"
                + "                      gong_kinh_chi_tiet ON gong_kinh.idGongKinh = gong_kinh_chi_tiet.idGongKinh INNER JOIN\n"
                + "                      chat_lieu ON gong_kinh_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN\n"
                + "                      thuong_hieu ON gong_kinh_chi_tiet.idThuongHieu = thuong_hieu.idThuongHieu INNER JOIN\n"
                + "                      mau_sac ON gong_kinh_chi_tiet.idMauSac = mau_sac.idMauSac\n"
                + "					  where gong_kinh.idGongKinh =? and (chat_lieu.tenChatLieu like ? or mau_sac.tenMauSac like ? or thuong_hieu.tenThuongHieu like ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            ps.setObject(2, a);
            ps.setObject(3, a);
            ps.setObject(4, a);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                Thuonghieu th = new Thuonghieu(null, rs.getString(4));
                GongKinhChiTiet gk = new GongKinhChiTiet(rs.getInt(1), null, cl, ms, th, rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
                list.add(gk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public GongKinhChiTiet check_MS_CL_TH(String mausac, String chatlieu, String thuonghieu) {
    sql = "SELECT  gong_kinh_chi_tiet.idGongKinhCT, chat_lieu.tenChatLieu, mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, gong_kinh_chi_tiet.giaThanh, gong_kinh_chi_tiet.soLuong, gong_kinh_chi_tiet.hinhanh, \n"
        + "           gong_kinh_chi_tiet.moTa, gong_kinh_chi_tiet.trangThai\n"
        + "FROM     gong_kinh_chi_tiet INNER JOIN\n"
        + "           gong_kinh ON gong_kinh_chi_tiet.idGongKinh = gong_kinh.idGongKinh INNER JOIN\n"
        + "           mau_sac ON gong_kinh_chi_tiet.idMauSac = mau_sac.idMauSac INNER JOIN\n"
        + "           chat_lieu ON gong_kinh_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN\n"
        + "           thuong_hieu ON gong_kinh_chi_tiet.idThuongHieu = thuong_hieu.idThuongHieu\n"
        + "					 WHERE gong_kinh_chi_tiet.tenChatLieu = ? AND gong_kinh_chi_tiet.tenMauSac = ? AND gong_kinh_chi_tiet.tenThuongHieu = ?";

    try {
        con = DBconnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setObject(1, mausac);
        ps.setObject(2, chatlieu);
        ps.setObject(3, thuonghieu);

        rs = ps.executeQuery();

        while (rs.next()) {
            ChatLieu cl = new ChatLieu(null, rs.getString(2));
            Mausac ms = new Mausac(null, rs.getString(3));
            Thuonghieu th = new Thuonghieu(null, rs.getString(4));
            GongKinhChiTiet gk = new GongKinhChiTiet(rs.getInt(1), null, cl, ms, th, rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));

            return gk;
        }

        return null;
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

}
