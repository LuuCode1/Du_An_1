/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class HoaDon {

    private int idHD;
    private String mahD;
    private KhachHang KH;
    private NguoiDung NG;
    private Date ngayban;
    private double tongtien;
    private int TrangThai;
    private Vouchers vouchers;
            
    public HoaDon() {
    }

    public HoaDon(int idHD) {
        this.idHD = idHD;
    }
    
    

    public HoaDon(int idHD, String mahD, KhachHang KH, NguoiDung NG, Date ngayban, double tongtien, int TrangThai) {
        this.idHD = idHD;
        this.mahD = mahD;
        this.KH = KH;
        this.NG = NG;
        this.ngayban = ngayban;
        this.tongtien = tongtien;
        this.TrangThai = TrangThai;
    }

    public HoaDon(String mahD, KhachHang KH, NguoiDung NG, Date ngayban, double tongtien, int TrangThai) {
        this.mahD = mahD;
        this.KH = KH;
        this.NG = NG;
        this.ngayban = ngayban;
        this.tongtien = tongtien;
        this.TrangThai = TrangThai;
    }

    public HoaDon(int idHD, String mahD, KhachHang KH, NguoiDung NG, Date ngayban, double tongtien, int TrangThai, Vouchers vouchers) {
        this.idHD = idHD;
        this.mahD = mahD;
        this.KH = KH;
        this.NG = NG;
        this.ngayban = ngayban;
        this.tongtien = tongtien;
        this.TrangThai = TrangThai;
        this.vouchers = vouchers;
    }

    public Vouchers getVouchers() {
        return vouchers;
    }

    public void setVouchers(Vouchers vouchers) {
        this.vouchers = vouchers;
    }
    
    

    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
    }

    public String getMahD() {
        return mahD;
    }

    public void setMahD(String mahD) {
        this.mahD = mahD;
    }

    public KhachHang getKH() {
        return KH;
    }

    public void setKH(KhachHang KH) {
        this.KH = KH;
    }

    public NguoiDung getNG() {
        return NG;
    }

    public void setNG(NguoiDung NG) {
        this.NG = NG;
    }

    public Date getNgayban() {
        return ngayban;
    }

    public void setNgayban(Date ngayban) {
        this.ngayban = ngayban;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Object[] todata_HD() {
        return new Object[]{this.mahD, this.NG.getTenND(), this.ngayban, this.KH.getTenKH()};
    }

}
