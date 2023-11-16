/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.QLTK;

/**
 *
 * @author Lenovo
 */
public class QLTK_Serivce {

    public Integer Save(QLTK t) {
        String sql = "insert into trong_kinh (maTrongKinh, tenTrongKinh) values (?, ?)";
        try (Connection conn = DBconnect.getConnection()) {
            PreparedStatement ptm = conn.prepareStatement(sql);
//            ptm.setObject(1, t.getIdTK());
            ptm.setObject(1, t.getMaTK());
            ptm.setObject(2, t.getTenTK());
            return ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Integer Update(QLTK t) {
        String sql = "update trong_kinh set tenTrongKinh = ? where maTrongKinh = ?";
        try (Connection conn = DBconnect.getConnection()) {
            PreparedStatement psm = conn.prepareStatement(sql);
            psm.setObject(1, t.getTenTK());
            psm.setObject(2, t.getMaTK());
            return psm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Integer Delete(String ma) {
        String sql = "delete from trong_kinh where maTrongKinh = ?";
        try (Connection conn = DBconnect.getConnection()) {
            PreparedStatement psm = conn.prepareStatement(sql);
            psm.setObject(1, ma);
            return psm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public QLTK findById(String ma) {
        try (Connection conn = DBconnect.getConnection()) {
            String sql = "select * from trong_kinh where maTrongKinh = ?";
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setObject(1, ma);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                QLTK t = new QLTK();
                t.setIdTK(rs.getInt(1));
                t.setMaTK(rs.getString(2));
                t.setTenTK(rs.getString(3));
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<QLTK> getGK() {
        List<QLTK> list = new ArrayList<>();
        String sql = "select * from trong_kinh";
        try (Connection conn = DBconnect.getConnection()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                QLTK tk = new QLTK();
                tk.setIdTK(rs.getInt(1));
                tk.setMaTK(rs.getString(2));
                tk.setTenTK(rs.getString(3));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<QLTK> Search(String text) {
        List<QLTK> list = new ArrayList<>();
        String sql = "select * from trong_kinh where maTrongKinh like ? or tenTrongKinh like ?";
        try (Connection conn = DBconnect.getConnection()) {
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setObject(1, text);
            ptm.setObject(2, text);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                QLTK tk = new QLTK();
                tk.setIdTK(rs.getInt(1));
                tk.setMaTK(rs.getString(2));
                tk.setTenTK(rs.getString(3));
                list.add(tk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
