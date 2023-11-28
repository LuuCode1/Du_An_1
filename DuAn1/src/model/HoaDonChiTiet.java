/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class HoaDonChiTiet {

    private int Soluong;
    private Double DonGia;
    private HoaDon hoaDon;
    private SanPhamChiTiet sanPham;
    private SanPham th;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(Integer idHoaDon, Integer idCTSP, int Soluong, Double DonGia, HoaDon hoaDon, SanPhamChiTiet sanPham, SanPham th) {
        this.Soluong = Soluong;
        this.DonGia = DonGia;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.th = th;
    }

    public HoaDonChiTiet(int Soluong, Double DonGia, HoaDon haDon, SanPhamChiTiet sanPham, SanPham th) {
        this.Soluong = Soluong;
        this.DonGia = DonGia;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.th = th;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public Double getDonGia() {
        return DonGia;
    }

    public void setDonGia(Double DonGia) {
        this.DonGia = DonGia;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SanPham getTh() {
        return th;
    }

    public void setTh(SanPham th) {
        this.th = th;
    }

    public SanPhamChiTiet getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPhamChiTiet sanPham) {
        this.sanPham = sanPham;
    }

    public double getThanhTien() {
        return DonGia * Soluong;
    }
}
