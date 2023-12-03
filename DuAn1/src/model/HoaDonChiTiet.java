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
 private int idHoaDonCHiTiet;
    private int Soluong;
    private Double DonGia;
    private HoaDon hoaDon;
    private SanPhamChiTiet sanPham;
    private double thanhtien;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int Soluong, Double DonGia, HoaDon hoaDon, SanPhamChiTiet sanPham) {
        this.Soluong = Soluong;
        this.DonGia = DonGia;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
    }

    public HoaDonChiTiet(int Soluong, Double DonGia, SanPhamChiTiet sanPham) {
        this.Soluong = Soluong;
        this.DonGia = DonGia;
        this.sanPham = sanPham;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public int getIdHoaDonCHiTiet() {
        return idHoaDonCHiTiet;
    }

    public void setIdHoaDonCHiTiet(int idHoaDonCHiTiet) {
        this.idHoaDonCHiTiet = idHoaDonCHiTiet;
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

    public SanPhamChiTiet getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPhamChiTiet sanPham) {
        this.sanPham = sanPham;
    }

    public HoaDonChiTiet(int Soluong, Double DonGia, SanPhamChiTiet sanPham, double thanhtien) {
        this.Soluong = Soluong;
        this.DonGia = DonGia;
        this.sanPham = sanPham;
        this.thanhtien = thanhtien;
    }

  

    public Object[] todata_hdct() {
        return new Object[]{this.hoaDon.getMahD(), this.sanPham.getSp().getTenSP(), this.Soluong, this.DonGia};
    }

    public Object[] todata_giohang() {
        return new Object[]{
            this.sanPham.getSp().getMaSP(),
            this.sanPham.getSp().getTenSP(),
            this.sanPham.getMaterial().getTenChatLieu(),
            this.sanPham.getColor().getTenMauSac(),
            this.sanPham.getSp().getBrand().getTenThuongHieu(),
            this.Soluong,
            this.DonGia
        };
    }
}
