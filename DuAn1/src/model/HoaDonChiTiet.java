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

    private int id_hdct;
    private int Soluong;
    private Double DonGia;
    private HoaDon hoaDon;
    private SanPhamChiTiet sanPham;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int id_hdct, int Soluong, Double DonGia, HoaDon hoaDon, SanPhamChiTiet sanPham) {
        this.id_hdct = id_hdct;
        this.Soluong = Soluong;
        this.DonGia = DonGia;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
    }

    public HoaDonChiTiet(Integer idHoaDon, Integer idCTSP, int Soluong, Double DonGia, HoaDon hoaDon, SanPhamChiTiet sanPham) {
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

    public HoaDonChiTiet(int Soluong, Double DonGia, HoaDon haDon, SanPhamChiTiet sanPham) {
        this.Soluong = Soluong;
        this.DonGia = DonGia;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;

    }

    public HoaDonChiTiet(int Soluong, SanPhamChiTiet sanPham) {
        this.Soluong = Soluong;
        this.sanPham = sanPham;
    }

    public int getId_hdct() {
        return id_hdct;
    }

    public void setId_hdct(int id_hdct) {
        this.id_hdct = id_hdct;
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

    public Object[] todata_hdct() {
        return new Object[]{this.sanPham.getSp().getLoaisp().getTenLoaiSP(),
            this.sanPham.getSp().getTenSP(),
            this.sanPham.getSp().getBrand().getTenThuongHieu(),
            this.sanPham.getColor().getTenMauSac(),
            this.sanPham.getMaterial().getTenChatLieu(),
            this.sanPham.getDoCan(),
            this.Soluong,
            this.sanPham.getGiathanh(),
            this.sanPham.getMota()
            };
    }
}
