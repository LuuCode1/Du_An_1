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
import model.SanPhamChiTiet;
import model.ChatLieu;
import model.Mausac;
import model.SanPham;
import model.Thuonghieu;

/**
 *
 * @author leduc
 */
public class SPCT_Service {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    public List<SanPhamChiTiet> selectAll(int id) {
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.giaThanh,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
                + "from san_pham_chi_tiet spct INNER JOIN\n"
                + "san_pham sp ON sp.idsp = spct.idsp INNER JOIN\n"
                + "chat_lieu cl ON cl.idChatLieu = spct.idChatLieu INNER JOIN\n"
                + "mau_sac ms ON ms.idMauSac = spct.idMauSac\n"
                + " where sp.idsp = ?";
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SanPham Show(int id) {

        sql = "SELECT masp, tensp FROM san_pham WHERE idsp = ?";

        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maSP = rs.getString("masp");
                String tenSP = rs.getString("tensp");
                SanPham sp = new SanPham(maSP, tenSP);
                return sp;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public SanPhamChiTiet selectByID(int id) {
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.giaThanh,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
                + "from san_pham_chi_tiet spct INNER JOIN\n"
                + "san_pham sp ON sp.idsp = spct.idsp INNER JOIN\n"
                + "chat_lieu cl ON cl.idChatLieu = spct.idChatLieu INNER JOIN\n"
                + "mau_sac ms ON ms.idMauSac = spct.idMauSac\n"
                + " where spct.id_sp_chi_tiet = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
                return spct;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
    
    

    public int Insert(SanPhamChiTiet spct) {
        sql = "INSERT INTO san_pham_chi_tiet(idsp,idChatLieu,idMauSac,giaThanh,soLuong,hinhanh,moTa,trangThai)\n"
                + "VALUES (?, ? , ? ,?,?,?,?,?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getSp().getIdSP());
            ps.setObject(2, spct.getMaterial().getIdChatLieu());
            ps.setObject(3, spct.getColor().getIdMauSac());
            ps.setObject(4, spct.getGiathanh());
            ps.setObject(5, spct.getSoluong());
            ps.setObject(6, spct.getHinhanh());
            ps.setObject(7, spct.getMota());
            ps.setObject(8, spct.getTrangthai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    
    public int delete(int id) {
        sql = "Delete from san_pham_chi_tiet where id_sp_chi_tiet =?";
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
    
    public int update(SanPhamChiTiet spct, int ma) {
        sql = "UPDATE san_pham_chi_tiet SET  idChatLieu = ?, idMauSac = ?, "
                + "giaThanh = ?, soLuong = ?, hinhanh = ?, moTa = ? ,trangThai = ? "
                + "WHERE san_pham_chi_tiet.id_sp_chi_tiet = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getMaterial().getIdChatLieu());
            ps.setObject(2, spct.getColor().getIdMauSac());
            ps.setObject(3, spct.getGiathanh());
            ps.setObject(4, spct.getSoluong());
            ps.setObject(5, spct.getHinhanh());
            ps.setObject(6, spct.getMota());
            ps.setObject(7, spct.getTrangthai());
            ps.setObject(8, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public List<SanPhamChiTiet> check_Cbo_CL(int ma, String chatlieu) {
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.giaThanh,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
                + "from san_pham_chi_tiet spct INNER JOIN\n"
                + "san_pham sp ON sp.idsp = spct.idsp INNER JOIN\n"
                + "chat_lieu cl ON cl.idChatLieu = spct.idChatLieu INNER JOIN\n"
                + "mau_sac ms ON ms.idMauSac = spct.idMauSac\n"
                + "where sp.idsp = ? and cl.tenChatLieu = ? ";
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            ps.setObject(2, chatlieu);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public List<SanPhamChiTiet> check_Cbo_MS(int ma, String chatlieu) {
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.giaThanh,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
                + "from san_pham_chi_tiet spct INNER JOIN\n"
                + "san_pham sp ON sp.idsp = spct.idsp INNER JOIN\n"
                + "chat_lieu cl ON cl.idChatLieu = spct.idChatLieu INNER JOIN\n"
                + "mau_sac ms ON ms.idMauSac = spct.idMauSac\n"
                + "where sp.idsp = ? and ms.tenMauSac = ? ";
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            ps.setObject(2, chatlieu);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public SanPhamChiTiet check(int chatlieu, int mausac){
        sql="SELECT * from san_pham_chi_tiet where idChatLieu =?  and idMauSac =?";
        try {
           con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, chatlieu);
            ps.setObject(2, mausac);
            rs = ps.executeQuery();
            while (rs.next()) {                
                 ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
                return spct;
            }
        } catch (Exception e) {
            e.printStackTrace();
           
        }
         return null;
    }
    
    
    public List<SanPhamChiTiet> seach(int id, String a) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.giaThanh,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
                + "from san_pham_chi_tiet spct INNER JOIN\n"
                + "san_pham sp ON sp.idsp = spct.idsp INNER JOIN\n"
                + "chat_lieu cl ON cl.idChatLieu = spct.idChatLieu INNER JOIN\n"
                + "mau_sac ms ON ms.idMauSac = spct.idMauSac\n"
                + "where sp.idsp = ? and (cl.tenChatLieu like ? or ms.tenMauSac like ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            ps.setObject(2, a);
            ps.setObject(3, a);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

}
