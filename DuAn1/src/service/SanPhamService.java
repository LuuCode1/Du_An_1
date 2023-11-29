/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.LoaiSP;
import model.Mausac;
import model.SPChiTiet;
import model.SanPham;
import model.SanPhamChiTiet;
import model.Thuonghieu;

/**
 *
 * @author Lenovo
 */
public class SanPhamService {

    public List<SPChiTiet> getListSPCT(String tt) {
        String sql = "select tenloai_sp, sp.masp, sp.tensp, tenMauSac, tenChatLieu,\n"
                + "tenThuongHieu, spct.doCan, spct.giaThanh, spct.soLuong, spct.moTa\n"
                + "from san_pham_chi_tiet spct join san_pham sp on spct.idsp = sp.idsp\n"
                + "join loai_sp l on sp.idloai_sp = l.idloai_sp\n"
                + "join mau_sac m on spct.idMauSac = m.idMauSac\n"
                + "join chat_lieu c on spct.idChatLieu = c.idChatLieu\n"
                + "join thuong_hieu t on t.idThuongHieu = sp.idThuongHieu\n"
                + "where spct.trangThai = ?";
        List<SPChiTiet> list = new ArrayList<>();
        try (Connection con = DBconnect.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SPChiTiet ct = new SPChiTiet();
                ct.setLoai(new LoaiSP(null, rs.getString(1)));
//                SanPham s = new SanPham();
//                s.setMaSP(rs.getString(2));
//                s.setTenSP(rs.getString(3));
                ct.setSp(new SanPham(rs.getString(2), rs.getString(3), null, new Thuonghieu(null, rs.getString(6))));
                ct.setColor(new Mausac(null, rs.getString(4)));
                ct.setMaterial(new ChatLieu(null, rs.getString(5)));
                ct.setDoCan(rs.getDouble(7));
                ct.setGiathanh(rs.getDouble(8));
                ct.setSoluong(rs.getInt(9));
                ct.setMota(rs.getString(10));
                list.add(ct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
