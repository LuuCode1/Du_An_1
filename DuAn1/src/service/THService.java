/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.ThuongHieu1;

/**
 *
 * @author Lenovo
 */
public class THService {

    public Integer Save(ThuongHieu1 t) {
        try (Connection conn = DBconnect.getConnection()) {
            String sql = "insert into thuong_hieu (maThuongHieu, tenThuongHieu) values( ?, ?)";
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setString(1, t.getMaTH());
            ptm.setString(2, t.getTenTH());
            return ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Integer Update(ThuongHieu1 t, String ma) {
        try (Connection conn = DBconnect.getConnection()) {
            String sql = "update thuong_hieu set tenThuongHieu = ? where maThuongHieu = ?";
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setString(2, ma);
            ptm.setString(1, t.getTenTH());
            return ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Integer Delete(String ma) {
        try (Connection conn = DBconnect.getConnection()) {
            String sql = "delete from thuong_hieu where maThuongHieu = ?";
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setObject(1, ma);
            return ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ThuongHieu1 selectByID(String maTH) {
        try (Connection conn = DBconnect.getConnection()) {
            String sql = "select maThuongHieu, tenThuongHieu from thuong_hieu where maThuongHieu = ?";
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setString(1, maTH);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                ThuongHieu1 th = new ThuongHieu1();
                th.setMaTH(rs.getString(1));
                th.setTenTH(rs.getString(2));
                return th;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ThuongHieu1> getThuongHieu() {
        List<ThuongHieu1> list = new ArrayList<>();
        try (Connection conn = DBconnect.getConnection()) {
            String sql = "select maThuongHieu, tenThuongHieu from thuong_hieu";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ThuongHieu1 th = new ThuongHieu1();
                th.setMaTH(rs.getString(1));
                th.setTenTH(rs.getString(2));
                list.add(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ThuongHieu1> Search(String text) {
        List<ThuongHieu1> list = new ArrayList<>();
        String sql = "select maThuongHieu, tenThuongHieu from thuong_hieu where maThuongHieu like ? or tenThuongHieu like ?";
        try (Connection conn = DBconnect.getConnection()) {
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setObject(1, text);
            ptm.setObject(2, text);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                ThuongHieu1 th = new ThuongHieu1();
                th.setMaTH(rs.getString(1));
                th.setTenTH(rs.getString(2));
                list.add(th);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
