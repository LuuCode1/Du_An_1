/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class Giongkinh {
    private String maGongKinh;
    private String tenGongKinh;
    private chatLieu tenChatLieu;
    private mausac tenMauSac;
    private thuonghieu tenThuongHieu;
    private double giaThanh;
    private int soLuong;
    private String hinhAnh;
    private String moTa;

    public Giongkinh() {
    }

    public Giongkinh(String maGongKinh, String tenGongKinh, chatLieu tenChatLieu, mausac tenMauSac, thuonghieu tenThuongHieu, double giaThanh, int soLuong, String hinhAnh, String moTa) {
        this.maGongKinh = maGongKinh;
        this.tenGongKinh = tenGongKinh;
        this.tenChatLieu = tenChatLieu;
        this.tenMauSac = tenMauSac;
        this.tenThuongHieu = tenThuongHieu;
        this.giaThanh = giaThanh;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
    }

    public String getMaGongKinh() {
        return maGongKinh;
    }

    public void setMaGongKinh(String maGongKinh) {
        this.maGongKinh = maGongKinh;
    }

    public String getTenGongKinh() {
        return tenGongKinh;
    }

    public void setTenGongKinh(String tenGongKinh) {
        this.tenGongKinh = tenGongKinh;
    }

    public chatLieu getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(chatLieu tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public mausac getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(mausac tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public thuonghieu getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(thuonghieu tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public double getGiaThanh() {
        return giaThanh;
    }

    public void setGiaThanh(double giaThanh) {
        this.giaThanh = giaThanh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    public Object[] todata(){
        return new Object[]{this.maGongKinh,this.tenGongKinh,this.tenChatLieu.getTenChatLieu(),
            this.tenMauSac.getTenMauSac(),this.tenThuongHieu.getTenThuongHieu(),this.giaThanh,
            this.soLuong,this.hinhAnh,this.moTa};
    }
}
