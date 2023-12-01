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
import model.ChatLieu;
import model.Mausac;
import model.SanPham;
import model.SanPhamChiTiet;

/**
 *
 * @author leduc
 */
public class SPCT1_Service {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    public List<SanPhamChiTiet> selectAll(int id) {
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.doCan,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
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
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl,rs.getDouble(4) ,rs.getDouble(5) ,rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
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
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.doCan,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"

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
SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl,rs.getDouble(4) ,rs.getDouble(5) ,rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
                return spct;
                
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public int Insert(SanPhamChiTiet spct) {

 

        sql = "INSERT INTO san_pham_chi_tiet(idsp,idChatLieu,idMauSac,doCan,giaThanh,giaNhap,soLuong,hinhanh,moTa,trangThai)\n"
                + "VALUES (?, ? ,? ,?,?,?,?,?,?,?)";

        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getSp().getIdSP());
            ps.setObject(2, spct.getMaterial().getIdChatLieu());
            ps.setObject(3, spct.getColor().getIdMauSac());
            ps.setObject(4, spct.getDoCan());


            ps.setObject(5, spct.getGiathanh());
            ps.setObject(6, spct.getGiaNhap());

            ps.setObject(7, spct.getSoluong());
            ps.setObject(8, spct.getHinhanh());
            ps.setObject(9, spct.getMota());
            ps.setObject(10, spct.getTrangthai());
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
                + "doCan = ?,giaNhap = ? ,giaThanh = ?, soLuong = ?, hinhanh = ?, moTa = ? ,trangThai = ? "
                + "WHERE san_pham_chi_tiet.id_sp_chi_tiet = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getMaterial().getIdChatLieu());
            ps.setObject(2, spct.getColor().getIdMauSac());
            ps.setObject(3, spct.getDoCan());


            ps.setObject(4, spct.getGiathanh());
            ps.setObject(5, spct.getGiaNhap());

            ps.setObject(6, spct.getSoluong());
            ps.setObject(7, spct.getHinhanh());
            ps.setObject(8, spct.getMota());
            ps.setObject(9, spct.getTrangthai());
            ps.setObject(10, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<SanPhamChiTiet> check_Cbo_CL(int ma, String chatlieu) {
sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.doCan,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"

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
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl,rs.getDouble(4) ,rs.getDouble(5) ,rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPhamChiTiet> check_Cbo_MS(int ma, String chatlieu) {

        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.doCan,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"

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

                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl,rs.getDouble(4) ,rs.getDouble(5) ,rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));

                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPhamChiTiet> seach(int id, String a) {
        List<SanPhamChiTiet> list = new ArrayList<>();

        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.doCan,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
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

                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1),
                        ms, cl, rs.getDouble(4), rs.getDouble(5),
                        rs.getDouble(6), rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getString(10));

                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SanPhamChiTiet findByID(int id, int idcl, int idms, Double doCan) {
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {

            String sSQL = "SELECT spct.id_sp_chi_tiet,spct.idChatLieu,spct.idMauSac,spct.doCan,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
                    + "from san_pham_chi_tiet spct INNER JOIN \n"
                    + "san_pham sp ON spct.idsp = sp.idsp\n"
                    + "where sp.idsp = '" + id + "' and (spct.idChatLieu = '" + idcl + "' and spct.idMauSac = '" + idms + "' and spct.doCan = '" + doCan + "')";
            conn = DBconnect.getConnection();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(null, rs.getString(2));
                Mausac ms = new Mausac(null, rs.getString(3));
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl,rs.getDouble(4) ,rs.getDouble(5) ,rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
                return spct;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        } finally {
            try {
                rs.close();
                sttm.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return null;
    }
}
