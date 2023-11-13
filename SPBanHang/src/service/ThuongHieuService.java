/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.ThuongHieu;

/**
 *
 * @author Lenovo
 */
public class ThuongHieuService {

    public List<ThuongHieu> getThuongHieu() {
        List<ThuongHieu> list = new ArrayList<>();
        try (Connection conn = DBConnect.getDBConnect()) {
            String sql = "SELECT * FROM thuong_hieu";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setMaTH(rs.getString(1));
                th.setTenTH(rs.getString(2));
                list.add(th);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
