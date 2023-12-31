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
import model.SanPhamChiTiet;
import model.ChatLieu;
import model.LoaiSP;
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
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
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
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
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
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
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
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
                return spct;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public int Insert(SanPhamChiTiet spct) {
        sql = "INSERT INTO san_pham_chi_tiet(idsp,idChatLieu,idMauSac,doCan,giaThanh,giaNhap,soLuong,hinhanh,moTa,trangThai)\n"
                + "VALUES (?, ? ,? ,? ,?,?,?,?,?,?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getSp().getIdSP());
            ps.setObject(2, spct.getMaterial().getIdChatLieu());
            ps.setObject(3, spct.getColor().getIdMauSac());
            ps.setObject(4, null);
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
                + "giaThanh = ?,giaNhap = ?, soLuong = ?, hinhanh = ?, moTa = ? ,trangThai = ? "
                + "WHERE san_pham_chi_tiet.id_sp_chi_tiet = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getMaterial().getIdChatLieu());
            ps.setObject(2, spct.getColor().getIdMauSac());
            ps.setObject(3, spct.getGiathanh());
            ps.setObject(4, spct.getGiaNhap());
            ps.setObject(5, spct.getSoluong());
            ps.setObject(6, spct.getHinhanh());
            ps.setObject(7, spct.getMota());
            ps.setObject(8, spct.getTrangthai());
            ps.setObject(9, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<SanPhamChiTiet> check_Cbo_CL(int ma, String chatlieu) {
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
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
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPhamChiTiet> check_Cbo_MS(int ma, String chatlieu) {
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
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
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
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
        sql = "SELECT spct.id_sp_chi_tiet,cl.tenChatLieu,ms.tenMauSac,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
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
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SanPhamChiTiet findByID(int id, int idcl, int idms) {
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT spct.id_sp_chi_tiet,spct.idChatLieu,spct.idMauSac,spct.giaThanh,spct.giaNhap,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai\n"
                    + "from san_pham_chi_tiet spct INNER JOIN \n"
                    + "san_pham sp ON spct.idsp = sp.idsp\n"
                    + "where sp.idsp = '" + id + "' and (spct.idChatLieu = '" + idcl + "' and spct.idMauSac = '" + idms + "')";
            conn = DBconnect.getConnection();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(rs.getInt(2), null, null);
                Mausac ms = new Mausac(rs.getInt(3), null, null);
                SanPhamChiTiet spct = new SanPhamChiTiet(null, rs.getInt(1), ms, cl, rs.getDouble(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
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

    public List<SanPhamChiTiet> ShowSP(String trangthai) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        sql = """
              SELECT    loai_sp.tenloai_sp, san_pham.tensp, thuong_hieu.tenThuongHieu, mau_sac.tenMauSac, chat_lieu.tenChatLieu, san_pham_chi_tiet.doCan, san_pham_chi_tiet.giaThanh, san_pham_chi_tiet.soLuong, 
                                                    san_pham_chi_tiet.hinhanh, san_pham_chi_tiet.moTa
                              FROM         san_pham INNER JOIN
                                                    san_pham_chi_tiet ON san_pham.idsp = san_pham_chi_tiet.idsp INNER JOIN
                                                    mau_sac ON san_pham_chi_tiet.idMauSac = mau_sac.idMauSac INNER JOIN
                                                    loai_sp ON san_pham.idloai_sp = loai_sp.idloai_sp INNER JOIN
                                                    chat_lieu ON san_pham_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN
                                                    thuong_hieu ON san_pham.idThuongHieu = thuong_hieu.idThuongHieu where san_pham_chi_tiet.trangThai =  ?
              """;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while (rs.next()) {
                Thuonghieu th = new Thuonghieu(rs.getString(3));
                Mausac ms = new Mausac(rs.getString(4));
                ChatLieu cl = new ChatLieu(rs.getString(5));
                LoaiSP lsp = new LoaiSP(rs.getString(1));
                SanPham sp = new SanPham(rs.getString(2), lsp, th);
                SanPhamChiTiet spct = new SanPhamChiTiet(sp, ms, cl, rs.getDouble(6), rs.getDouble(8), rs.getInt(7), rs.getString(9), rs.getString(10));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
