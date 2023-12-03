/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.Mausac;
import model.NguoiDung;
import model.SanPham;
import model.SanPhamChiTiet;
import model.Thuonghieu;
import model.Vouchers;

/**
 *
 * @author Asus
 */
public class HoaDon_Service {

    NguoiDung_Service ndsv = new NguoiDung_Service();
    KhachHangService khsv = new KhachHangService();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    
    public List<HoaDonChiTiet> getAll_HDCT(int idhd) {
        sql = "Select * from hoa_don_chi_tiet where idHoaDon = ?";
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {          
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idhd);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet(rs.getInt(1), rs.getInt(4), rs.getDouble(5), new HoaDon(rs.getInt(3)), new SanPhamChiTiet(rs.getInt(2)));
                list.add(hdct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public HoaDon selectHDByID(int id) {
        sql = "SELECT hd.idHoaDon,hd.maHoaDon,vc.maVouchers,kh.tenKhachHang,nd.tenNguoi_dung,hd.ngayban,hd.tongtien,hd.trangthai \n"
                + "from hoa_don hd LEFT JOIN\n"
                + "vouchers vc ON vc.idVouchers = hd.idVouchers LEFT JOIN\n"
                + "khach_hang kh ON kh.idKhachHang = hd.idKhachHang LEFT JOIN\n"
                + "Nguoi_dung nd ON nd.idnguoi_dung = hd.idnguoi_dung \n"
                + "WHERE hd.idHoaDon = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vouchers vc = new Vouchers(0, rs.getString(3), null, 0, null, null, null, null);
                KhachHang kh = new KhachHang(0, null, rs.getString(4), null, null);
                NguoiDung nd = new NguoiDung(0, null, rs.getString(5), null, null, null, 0, null, 0);
                HoaDon hd = new HoaDon(rs.getInt(1), rs.getString(2), kh, nd, rs.getDate(6), rs.getDouble(7), rs.getInt(8), vc);
                return hd;

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
    
    public int update_Vouchers_HD(HoaDon hd, int idhd) {
        sql = "update hoa_don set idVouchers = ?  where idHoaDon = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getVouchers().getIdVouchers());
            ps.setObject(2, idhd);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int update_TrangThai_HD(HoaDon hd, int idhd) {
        sql = "update hoa_don set idnguoi_dung = ?,idKhachHang = ? ,tongtien = ?,trangthai = ? where idHoaDon = ? ";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getNG().getIdND());
            ps.setObject(2, hd.getKH().getIdKH());
            ps.setObject(3, hd.getTongtien());
            ps.setObject(4, 1);
            ps.setObject(5, idhd);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

//    public List<HoaDon> Select() {
//        List<HoaDon> list = new ArrayList<>();
//        sql = "SELECT    hoa_don.idHoaDon, hoa_don.maHoaDon, Nguoi_dung.tenNguoi_dung, khach_hang.tenKhachHang, hoa_don.ngayban, hoa_don.trangthai\n"
//                + "FROM         hoa_don LEFT  JOIN\n"
//                + "                      Nguoi_dung ON hoa_don.idnguoi_dung = Nguoi_dung.idnguoi_dung LEFT  JOIN\n"
//                + "                      khach_hang ON hoa_don.idKhachHang = khach_hang.idKhachHang ";
//        try {
//            con = DBconnect.getConnection();
//            ps = con.prepareStatement(sql);
////            ps.setObject(1, tt);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                KhachHang kh = new KhachHang(null, rs.getString(4), null, null);
//                NguoiDung nd = new NguoiDung(null, rs.getString(3), null, null, null, 0, null, 0);
//                HoaDon hd = new HoaDon();
//                hd = new HoaDon(rs.getInt(1), rs.getString(2), kh, nd, rs.getDate(5), 0, 0);
//                list.add(hd);
//            }
//            return list;
//        } catch (Exception e) {
//        }
//        return null;
//    }
//    
    public int Insert(HoaDon hd) {
        sql = "INSERT INTO hoa_don(maHoaDon,trangthai)\n"
                + "VALUES ( ?,?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getMahD());
            ps.setObject(2, hd.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
//    
//    public List<HoaDonChiTiet> getListHoaDonChiTiet(String MaHD) {
//        List<HoaDonChiTiet> getList = new ArrayList<>();
//        try {
//            String sql = "select sp.masp, sp.tensp, m.tenMauSac, c.tenChatLieu, t.tenThuongHieu, spct.doCan,hdct.soluong,hdct.dongia\n"
//                    + "from hoa_don hd left join hoa_don_chi_tiet hdct on hd.idHoaDon = hdct.idHoaDon\n"
//                    + "left join san_pham_chi_tiet spct on hdct.id_sp_chi_tiet = spct.id_sp_chi_tiet\n"
//                    + "left join san_pham sp on sp.idsp = spct.idsp\n"
//                    + "left join mau_sac m on spct.idMauSac = m.idMauSac\n"
//                    + "left join chat_lieu c on spct.idChatLieu = c.idChatLieu\n"
//                    + "left join thuong_hieu t on sp.idThuongHieu = t.idThuongHieu"
//                    + "  WHERE hd.maHoaDon = ?";
//            Connection conn = DBconnect.getConnection();
//            PreparedStatement pr = conn.prepareStatement(sql);
//            pr.setString(1, MaHD);
//            ResultSet rs = pr.executeQuery();
//            while (rs.next()) {
//                HoaDonChiTiet hdct = new HoaDonChiTiet();
//                SanPhamChiTiet spct = new SanPhamChiTiet();
//                spct.setSp(new SanPham(rs.getString(1), rs.getString(2), null, new Thuonghieu(null, rs.getString(5))));
//                spct.setDoCan(rs.getDouble(6));
//                spct.setColor(new Mausac(null, rs.getString(3)));
//                spct.setMaterial(new ChatLieu(null, rs.getString(4)));
//                hdct.setSanPham(spct);
//                hdct.setDonGia(rs.getDouble(8));
//                hdct.setSoluong(rs.getInt(7));
//                getList.add(hdct);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return getList;
//    }

    public int InsertHDCT(HoaDonChiTiet hdct) {
        sql = "INSERT INTO hoa_don_chi_tiet(id_sp_chi_tiet, idHoaDon, soluong, dongia)\n"
                + "VALUES (?,?,?,?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hdct.getSanPham().getIdSPChiTiet());
            ps.setObject(2, hdct.getHoaDon().getIdHD());
            ps.setObject(3, hdct.getSoluong());
            ps.setObject(4, hdct.getDonGia());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public HoaDonChiTiet find_HDCT_ByID(int idspct, int idhd) {
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "Select * from hoa_don_chi_tiet where id_sp_chi_tiet = " + idspct + " AND idHoaDon = " + idhd + "";
            conn = DBconnect.getConnection();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet(rs.getInt(1), rs.getInt(4), rs.getDouble(5), new HoaDon(rs.getInt(3)), new SanPhamChiTiet(rs.getInt(2)));
                return hdct;
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

    public int updateSL(HoaDonChiTiet hdct, int idspct, int idhd) {
        sql = "update hoa_don_chi_tiet set soluong = ? ,dongia = ? where id_sp_chi_tiet = ? AND idHoaDon = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hdct.getSoluong());
            ps.setObject(2, hdct.getDonGia());
            ps.setObject(3, idspct);
            ps.setObject(4, idhd);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteHDCT(int idspct, int idhd) {
        sql = "Delete from hoa_don_chi_tiet where id_sp_chi_tiet = ? AND idHoaDon = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idspct);
            ps.setObject(2, idhd);
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
