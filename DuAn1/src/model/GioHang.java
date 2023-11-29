/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class GioHang {

    private String MaSP;
    private String TenSP;
    private int SoLuong;
    private Double DonGia;
    private Double ThanhTien;
    private String mauSac;
    private String chatLieu;
    private String thuongHieu;

    public GioHang() {
    }

    public GioHang(String MaSP, String TenSP, int SoLuong, Double DonGia, Double ThanhTien, String mauSac, String chatLieu, String thuongHieu) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.thuongHieu = thuongHieu;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public Double getDonGia() {
        return DonGia;
    }

    public void setDonGia(Double DonGia) {
        this.DonGia = DonGia;
    }

    public Double getThanhTien() {
        return DonGia * SoLuong;
    }

}
