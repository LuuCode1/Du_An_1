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
import model.TrongKinh;
import model.chatLieu;
import model.mausac;
import model.thuonghieu;
//l

/**
 *
 * @author leduc
 */
public class TrongKinhService {

    public List<TrongKinh> getAllTK() {
        List<TrongKinh> Listtk = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT idTrongKinh,maTrongKinh,tenTrongKinh,tk.idMauSac,maMauSac,tenMauSac,tk.idChatLieu,maChatLieu,tenChatLieu,\n"
                    + "       tk.idThuongHieu,maThuongHieu,tenThuongHieu,giaThanh,doCan,soLuong,hinhanh,moTa,trangThai\n"
                    + "FROM trong_kinh tk inner join mau_sac ms on tk.idMauSac = ms.idMauSac \n"
                    + "                   inner join chat_lieu cl on tk.idChatLieu = cl.idChatLieu \n"
                    + "	              inner join thuong_hieu th on tk.idThuongHieu = th.idThuongHieu";
            conn = DBconnect.getConnection();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                TrongKinh tk = new TrongKinh();
                tk.setIdTrongKinh(rs.getInt(1));
                tk.setMaTrongKinh(rs.getString(2));
                tk.setTenTrongKinh(rs.getString(3));
                tk.setColor(new mausac(rs.getInt(4), rs.getString(5), rs.getString(6)));
                tk.setMaterial(new chatLieu(rs.getInt(7), rs.getString(8), rs.getString(9)));
                tk.setBrand(new thuonghieu(rs.getInt(10), rs.getString(11), rs.getString(12)));
                tk.setGiathanh(rs.getDouble(13));
                tk.setDocan(rs.getDouble(14));
                tk.setSoluong(rs.getInt(15));
                tk.setHinhanh(rs.getString(16));
                tk.setMota(rs.getString(17));
                tk.setTrangthai(rs.getString(18));
                Listtk.add(tk);
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
        return Listtk;
    }

    public int addTrongKinh(TrongKinh tk) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "INSERT INTO trong_kinh(maTrongKinh,tenTrongKinh,idChatLieu,idMauSac,idThuongHieu,giaThanh,doCan,soLuong,hinhanh,moTa,trangThai) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            conn = DBconnect.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, tk.getMaTrongKinh());
            sttm.setString(2, tk.getTenTrongKinh());
            sttm.setInt(3, tk.getColor().getIdMauSac());
            sttm.setInt(4, tk.getMaterial().getIdChatLieu());
            sttm.setInt(5, tk.getBrand().getIdThuongHieu());
            sttm.setDouble(6, tk.getGiathanh());
            sttm.setDouble(7, tk.getDocan());
            sttm.setInt(8, tk.getSoluong());
            sttm.setString(9, tk.getHinhanh());
            sttm.setString(10, tk.getMota());
            sttm.setString(11, tk.getTrangthai());
            if (sttm.executeUpdate() > 0) {
                System.out.println("Them thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        } finally {
            try {
                conn.close();
                sttm.close();
            } catch (Exception e) {
            }
        }
        return -1;//Neu ko them thanh cong
    }

    public List<TrongKinh> seach(String a) {
        List<TrongKinh> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sSQL = "SELECT  trong_kinh.idTrongKinh,trong_kinh.maTrongKinh, trong_kinh.tenTrongKinh,mau_sac.tenMauSac,  thuong_hieu.tenThuongHieu, chat_lieu.tenChatLieu, trong_kinh.giaThanh,\n"
                + "          trong_kinh.doCan, trong_kinh.soLuong, trong_kinh.hinhanh, trong_kinh.moTa\n"
                + "          FROM chat_lieu INNER JOIN\n"
                + "          trong_kinh ON chat_lieu.idChatLieu = trong_kinh.idChatLieu INNER JOIN\n"
                + "          mau_sac ON trong_kinh.idMauSac = mau_sac.idMauSac INNER JOIN\n"
                + "          thuong_hieu ON trong_kinh.idThuongHieu = thuong_hieu.idThuongHieu\n"
                + "          where trong_kinh.maTrongKinh like ? or trong_kinh.tenTrongKinh like ? or chat_lieu.tenChatLieu like ? or mau_sac.tenMauSac like ? or thuong_hieu.tenThuongHieu like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sSQL);
            ps.setObject(1, a);
            ps.setObject(2, a);
            ps.setObject(3, a);
            ps.setObject(4, a);
            ps.setObject(5, a);
            rs = ps.executeQuery();
            while (rs.next()) {
                chatLieu cl = new chatLieu(null, rs.getString(3));
                mausac ms = new mausac( null, rs.getString(4));
                thuonghieu th = new thuonghieu( null, rs.getString(5));
                TrongKinh tk = new TrongKinh(rs.getInt(1), rs.getString(2), rs.getString(3), ms, th, cl, rs.getDouble(7), rs.getDouble(8), rs.getInt(9), rs.getString(10), rs.getString(11), null);
                list.add(tk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int Delete(String maNV) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sql = "DELETE trong_kinh WHERE maTrongKinh = ?";
            conn = DBconnect.getConnection();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, maNV);
            if (sttm.executeUpdate() > 0) {
                System.out.println("delete thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                conn.close();
                sttm.close();
            } catch (Exception e) {
            }
        }
        return -1;
    }

    public int update(TrongKinh tk) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sql = "UPDATE trong_kinh SET tenTrongKinh = ?,idMauSac = ?,idChatLieu = ?, idThuongHieu = ?, giaThanh = ?, doCan = ?, soLuong = ?, hinhanh = ?,moTa = ?, trangThai = ?\n"
                    + "       WHERE maTrongKinh = ?";
            conn = DBconnect.getConnection();
            sttm = conn.prepareStatement(sql);
            sttm.setString(11, tk.getMaTrongKinh());
            sttm.setString(1, tk.getTenTrongKinh());
            sttm.setInt(2, tk.getColor().getIdMauSac());
            sttm.setInt(3, tk.getMaterial().getIdChatLieu());
            sttm.setInt(4, tk.getBrand().getIdThuongHieu());
            sttm.setDouble(5, tk.getGiathanh());
            sttm.setDouble(6, tk.getDocan());
            sttm.setInt(7, tk.getSoluong());
            sttm.setString(8, tk.getHinhanh());
            sttm.setString(9, tk.getMota());
            sttm.setString(10, tk.getTrangthai());
            if (sttm.executeUpdate() > 0) {
                System.out.println("update thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                conn.close();
                sttm.close();
            } catch (Exception e) {
            }
        }
        return -1;
    }

    public List<TrongKinh> search_Cbo_thuonghieu(String tenthuonghieu) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  trong_kinh.idTrongKinh,trong_kinh.maTrongKinh, trong_kinh.tenTrongKinh,mau_sac.tenMauSac,  thuong_hieu.tenThuongHieu, chat_lieu.tenChatLieu, trong_kinh.giaThanh,\n"
                + "          trong_kinh.doCan, trong_kinh.soLuong, trong_kinh.hinhanh, trong_kinh.moTa\n"
                + "          FROM chat_lieu INNER JOIN\n"
                + "          trong_kinh ON chat_lieu.idChatLieu = trong_kinh.idChatLieu INNER JOIN\n"
                + "          mau_sac ON trong_kinh.idMauSac = mau_sac.idMauSac INNER JOIN\n"
                + "          thuong_hieu ON trong_kinh.idThuongHieu = thuong_hieu.idThuongHieu\n"
                + "          where thuong_hieu.tenThuongHieu =?";
        List<TrongKinh> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tenthuonghieu);
            rs = ps.executeQuery();
            while (rs.next()) {
                chatLieu cl = new chatLieu( null, rs.getString(3));
                mausac ms = new mausac( null, rs.getString(4));
                thuonghieu th = new thuonghieu( null, rs.getString(5));
                TrongKinh tk = new TrongKinh(rs.getInt(1), rs.getString(2), rs.getString(3), ms, th, cl, rs.getDouble(7), rs.getDouble(8), rs.getInt(9), rs.getString(10), rs.getString(11), null);
                list.add(tk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

}
