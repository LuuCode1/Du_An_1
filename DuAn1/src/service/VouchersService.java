/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Voucher;
import model.Voucher;

/**
 *
 * @author Lenovo
 */
public class VouchersService {

    public Integer Save(Voucher k) {
        String sql = "insert into vouchers (maVouchers, tenVouchers, giatri, soluong, ngayketthuc, trangthai)\n"
                + "values (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBconnect.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, k.getMaVouchers());
            ps.setObject(2, k.getTenVouchers());
            ps.setObject(3, k.getGiatri());
            ps.setObject(4, k.getSoluong());
            ps.setObject(5, k.getNgayketthuc());
            ps.setObject(6, k.getTrangthai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Integer Update(Voucher km, String ma) {
        String sql = "UPDATE vouchers SET tenVouchers = ?, giatri = ?, soluong = ?, "
                + "ngayketthuc = ?, trangthai =  ? WHERE maVouchers = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, km.getTenVouchers());
            pstm.setObject(2, km.getGiatri());
            pstm.setObject(3, km.getSoluong());
            pstm.setObject(4, km.getNgayketthuc());
            pstm.setObject(5, km.getTrangthai());
//            pstm.setDouble(5, km.getGiaTriGiam());
            pstm.setObject(6, ma);
            return pstm.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Voucher> getAll() {
        List<Voucher> list = new ArrayList<>();
        String sql = "select maVouchers, tenVouchers, giatri, soluong,soluongdadung,ngaytao, ngayketthuc, trangthai from vouchers";
        try (Connection conn = DBconnect.getConnection()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Voucher km = new Voucher();
                km.setMaVouchers(rs.getString(1));
                km.setTenVouchers(rs.getString(2));
                km.setSoluong(rs.getInt(4));
                km.setSoluongdadung(rs.getInt(5));
                km.setGiatri(rs.getFloat(3));
                km.setNgaytao(rs.getDate(6));
                km.setNgayketthuc(rs.getDate(7));
                km.setTrangthai(rs.getString(8));
                list.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String checkMa(String ma,String ten) {
        String sql = " SELECT maVouchers,tenVouchers FROM vouchers WHERE maVouchers= ? and tenVouchers =?";
        String box = null;
        try (Connection conn = DBconnect.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, ma);
            pstm.setString(2, ten);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box = rs.getString(1);
                box = rs.getString(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return box;
    }

    public String checkTen(String ten) {
        String sql = " SELECT tenVouchers    FROM vouchers WHERE tenVouchers= ?";
        String box = null;
        try (Connection conn = DBconnect.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, ten);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return box;
    }

    public List<Voucher> FindDateBD(String date) {
        List<Voucher> list = new ArrayList<>();
        try (Connection conn = DBconnect.getConnection()) {
            String sql = "select maVouchers, tenVouchers, giatri, soluong, ngaytao, ngayketthuc, trangthai \n"
                    + "from vouchers Where ngaytao = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, date);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Voucher km = new Voucher();
                km.setMaVouchers(rs.getString(1));
                km.setTenVouchers(rs.getString(2));
                km.setGiatri(rs.getFloat(3));
                km.setSoluong(rs.getInt(4));
                km.setNgaytao(rs.getDate(5));
                km.setNgayketthuc(rs.getDate(6));
                km.setTrangthai(rs.getString(7));
                list.add(km);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VouchersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Voucher> FindDateKT(String date) {
        List<Voucher> list = new ArrayList<>();
        try (Connection conn = DBconnect.getConnection()) {
            String sql = "select maVouchers, tenVouchers, giatri, soluong, ngaytao, ngayketthuc, soluongdadung, trangthai \n"
                    + "from vouchers Where ngayketthuc = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, date);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Voucher km = new Voucher();
                km.setMaVouchers(rs.getString(1));
                km.setTenVouchers(rs.getString(2));
                km.setGiatri(rs.getFloat(3));
                km.setSoluong(rs.getInt(4));
                km.setNgaytao(rs.getDate(5));
                km.setNgayketthuc(rs.getDate(6));
                km.setSoluongdadung(rs.getInt(7));
                km.setTrangthai(rs.getString(8));
                list.add(km);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VouchersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Voucher> SearchByDate(String datedb, String datekt) {
        List<Voucher> list = new ArrayList<>();
        try (Connection conn = DBconnect.getConnection()) {
            String sql = "select maVouchers, tenVouchers, giatri, soluong, ngaytao, ngayketthuc, soluongdadung, trangthai \n"
                    + "from vouchers Where ngaytao = ? and ngayketthuc = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, datedb);
            pstm.setObject(2, datekt);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Voucher km = new Voucher();
                km.setMaVouchers(rs.getString(1));
                km.setTenVouchers(rs.getString(2));
                km.setGiatri(rs.getFloat(3));
                km.setSoluong(rs.getInt(4));
                km.setNgaytao(rs.getDate(5));
                km.setNgayketthuc(rs.getDate(6));
                km.setSoluongdadung(rs.getInt(7));
                km.setTrangthai(rs.getString(8));
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Voucher> SearchAll(String text) {
        List<Voucher> list = new ArrayList<>();
        try (Connection conn = DBconnect.getConnection()) {
            String sql = "select maVouchers, tenVouchers, giatri, soluong, ngaytao, ngayketthuc, soluongdadung, trangthai \n"
                    + "from vouchers Where maVouchers like ? or tenVouchers like ? or trangthai like ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, text);
            pstm.setObject(2, text);
            pstm.setObject(3, text);
//            pstm.setString(4, text);
//            pstm.setString(5, text);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Voucher km = new Voucher();
                km.setMaVouchers(rs.getString(1));
                km.setTenVouchers(rs.getString(2));
                km.setGiatri(rs.getFloat(3));
                km.setSoluong(rs.getInt(4));
                km.setNgaytao(rs.getDate(5));
                km.setNgayketthuc(rs.getDate(6));
                km.setSoluongdadung(rs.getInt(7));
                km.setTrangthai(rs.getString(8));
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
