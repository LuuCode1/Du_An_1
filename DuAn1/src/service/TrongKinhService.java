/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.GongKinhChiTiet;
import model.QLTK;
import model.TrongKinhChiTiet;
import model.ChatLieu;
import model.Mausac;
import model.Thuonghieu;
//l
/**
 *
 * @author leduc
 */
public class TrongKinhService {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    public List<TrongKinhChiTiet> selectAll(int id) {
        sql = "SELECT  trong_kinh_chi_tiet.idTrongKinhCT,chat_lieu.tenChatLieu,mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, trong_kinh_chi_tiet.giaThanh,trong_kinh_chi_tiet.doCan ,trong_kinh_chi_tiet.soLuong, trong_kinh_chi_tiet.hinhanh, trong_kinh_chi_tiet.moTa, \n"
                + "    trong_kinh_chi_tiet.trangThai\n"
                + "    FROM  trong_kinh INNER JOIN\n"
                + "      trong_kinh_chi_tiet ON trong_kinh.idTrongKinh = trong_kinh_chi_tiet.idTrongKinh INNER JOIN\n"
                + "      chat_lieu ON trong_kinh_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN\n"
                + "      thuong_hieu ON trong_kinh_chi_tiet.idThuongHieu = thuong_hieu.idThuongHieu INNER JOIN\n"
                + "      mau_sac ON trong_kinh_chi_tiet.idMauSac = mau_sac.idMauSac\n"
                + "	 where trong_kinh.idTrongKinh = ?";
        List<TrongKinhChiTiet> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                Thuonghieu th = new Thuonghieu(null, rs.getString(4));
                TrongKinhChiTiet gk = new TrongKinhChiTiet(rs.getInt(1), null, ms, th, cl, rs.getDouble(5), rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
                list.add(gk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public QLTK Show(int id) {

        sql = "SELECT maTrongKinh, tenTrongKinh FROM trong_kinh WHERE idTrongKinh = ?";

        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maTrongKinh = rs.getString("maTrongKinh");
                String tenTrongKinh = rs.getString("tenTrongKinh");
                QLTK tk = new QLTK(maTrongKinh, tenTrongKinh);
                return tk;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public int Insert(TrongKinhChiTiet tk) {
        sql = "INSERT INTO trong_kinh_chi_tiet(idTrongKinh,idChatLieu,idMauSac,idThuongHieu,giaThanh,doCan,soLuong,hinhAnh,moTa,trangThai)\n"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tk.getTk().getIdTK());
            ps.setObject(2, tk.getMaterial().getIdChatLieu());
            ps.setObject(3, tk.getColor().getIdMauSac());
            ps.setObject(4, tk.getBrand().getIdThuongHieu());
            ps.setObject(5, tk.getGiathanh());
            ps.setObject(6, tk.getDocan());
            ps.setObject(7, tk.getSoluong());
            ps.setObject(8, tk.getHinhanh());
            ps.setObject(9, tk.getMota());
            ps.setObject(10, tk.getTrangthai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<TrongKinhChiTiet> seach(int ma, String a) {
        List<TrongKinhChiTiet> list = new ArrayList<>();
        sql = "SELECT  trong_kinh_chi_tiet.idTrongKinhCT,chat_lieu.tenChatLieu,mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, trong_kinh_chi_tiet.giaThanh, trong_kinh_chi_tiet.doCan,trong_kinh_chi_tiet.soLuong, trong_kinh_chi_tiet.hinhanh, trong_kinh_chi_tiet.moTa, \n"
                + "    trong_kinh_chi_tiet.trangThai\n"
                + "FROM  trong_kinh INNER JOIN\n"
                + "      trong_kinh_chi_tiet ON trong_kinh.idTrongKinh = trong_kinh_chi_tiet.idTrongKinh INNER JOIN\n"
                + "      chat_lieu ON trong_kinh_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN\n"
                + "      thuong_hieu ON trong_kinh_chi_tiet.idThuongHieu = thuong_hieu.idThuongHieu INNER JOIN\n"
                + "      mau_sac ON trong_kinh_chi_tiet.idMauSac = mau_sac.idMauSac\n"
                + "      where trong_kinh.idTrongKinh =? and (chat_lieu.tenChatLieu like ? or mau_sac.tenMauSac like ? or thuong_hieu.tenThuongHieu like ?)";
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
                TrongKinhChiTiet tk = new TrongKinhChiTiet(rs.getInt(1), null, ms, th, cl, rs.getDouble(5), rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
                list.add(tk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int delete(int id) {
        sql = "Delete from trong_kinh_chi_tiet where idTrongKinhCT =?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public TrongKinhChiTiet selectByID(int id) {
        sql = "SELECT     trong_kinh_chi_tiet.idTrongKinhCT,chat_lieu.tenChatLieu,mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, trong_kinh_chi_tiet.giaThanh,trong_kinh_chi_tiet.doCan ,trong_kinh_chi_tiet.soLuong, trong_kinh_chi_tiet.hinhanh, trong_kinh_chi_tiet.moTa, \n"
                + "       trong_kinh_chi_tiet.trangThai\n"
                + "FROM        trong_kinh INNER JOIN\n"
                + "            trong_kinh_chi_tiet ON trong_kinh.idTrongKinh = trong_kinh_chi_tiet.idTrongKinh INNER JOIN\n"
                + "            chat_lieu ON trong_kinh_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN\n"
                + "            thuong_hieu ON trong_kinh_chi_tiet.idThuongHieu = thuong_hieu.idThuongHieu INNER JOIN\n"
                + "            mau_sac ON trong_kinh_chi_tiet.idMauSac = mau_sac.idMauSac\n"
                + "	       where trong_kinh_chi_tiet.idTrongKinhCT = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                Thuonghieu th = new Thuonghieu(null, rs.getString(4));
                TrongKinhChiTiet tk = new TrongKinhChiTiet(rs.getInt(1), null, ms, th, cl, rs.getDouble(5), rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
                return tk;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
//

    public int update(TrongKinhChiTiet tk, int id) {
        sql = "UPDATE trong_kinh_chi_tiet SET  idChatLieu = ?, idMauSac = ?, idThuongHieu = ?, giaThanh = ?, doCan = ?,soLuong = ?, hinhanh = ?, moTa = ? ,trangThai = ? WHERE trong_kinh_chi_tiet.idTrongKinhCT = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tk.getMaterial().getIdChatLieu());
            ps.setObject(2, tk.getColor().getIdMauSac());
            ps.setObject(3, tk.getBrand().getIdThuongHieu());
            ps.setObject(4, tk.getGiathanh());
            ps.setObject(5, tk.getDocan());
            ps.setObject(6, tk.getSoluong());
            ps.setObject(7, tk.getHinhanh());
            ps.setObject(8, tk.getMota());
            ps.setObject(9, tk.getTrangthai());
            ps.setObject(10, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<TrongKinhChiTiet> check_Cbo(int id, String thuongHieu) {
        sql = "SELECT  trong_kinh_chi_tiet.idTrongKinhCT,chat_lieu.tenChatLieu,mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, trong_kinh_chi_tiet.giaThanh,trong_kinh_chi_tiet.doCan ,trong_kinh_chi_tiet.soLuong, trong_kinh_chi_tiet.hinhanh, trong_kinh_chi_tiet.moTa, \n"
                + "    trong_kinh_chi_tiet.trangThai\n"
                + "FROM         trong_kinh INNER JOIN\n"
                + "             trong_kinh_chi_tiet ON trong_kinh.idTrongKinh = trong_kinh_chi_tiet.idTrongKinh INNER JOIN\n"
                + "             chat_lieu ON trong_kinh_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN\n"
                + "             thuong_hieu ON trong_kinh_chi_tiet.idThuongHieu = thuong_hieu.idThuongHieu INNER JOIN\n"
                + "             mau_sac ON trong_kinh_chi_tiet.idMauSac = mau_sac.idMauSac\n"
                + "		where trong_kinh.idTrongKinh = ? and thuong_hieu.tenThuongHieu = ? ";
        List<TrongKinhChiTiet> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            ps.setObject(2, thuongHieu);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                Thuonghieu th = new Thuonghieu(null, rs.getString(4));
                TrongKinhChiTiet tk = new TrongKinhChiTiet(rs.getInt(1), null, ms, th, cl, rs.getDouble(5), rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
                list.add(tk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
