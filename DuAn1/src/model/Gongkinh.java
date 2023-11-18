/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class Gongkinh {
    private int gongKinhChiTiet;
    private QLGK gongKinh;
    private chatLieu tenChatLieu;
    private mausac tenMauSac;
    private thuonghieu tenThuongHieu;
    private double giaThanh;
    private int soLuong;
    private String hinhAnh;
    private String moTa;
    private String trangThai;
//b
    public Gongkinh() {
    }

    public Gongkinh(int gongKinhChiTiet, QLGK gongKinh, chatLieu tenChatLieu, mausac tenMauSac, thuonghieu tenThuongHieu, double giaThanh, int soLuong, String hinhAnh, String moTa, String trangThai) {
        this.gongKinhChiTiet = gongKinhChiTiet;
        this.gongKinh = gongKinh;
        this.tenChatLieu = tenChatLieu;
        this.tenMauSac = tenMauSac;
        this.tenThuongHieu = tenThuongHieu;
        this.giaThanh = giaThanh;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public Gongkinh(QLGK gongKinh, chatLieu tenChatLieu, mausac tenMauSac, thuonghieu tenThuongHieu, double giaThanh, int soLuong, String hinhAnh, String moTa, String trangThai) {
        this.gongKinh = gongKinh;
        this.tenChatLieu = tenChatLieu;
        this.tenMauSac = tenMauSac;
        this.tenThuongHieu = tenThuongHieu;
        this.giaThanh = giaThanh;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public int getGongKinhChiTiet() {
        return gongKinhChiTiet;
    }

    public void setGongKinhChiTiet(int gongKinhChiTiet) {
        this.gongKinhChiTiet = gongKinhChiTiet;
    }

    public QLGK getGongKinh() {
        return gongKinh;
    }

    public void setGongKinh(QLGK gongKinh) {
        this.gongKinh = gongKinh;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    

    public Object[] todata(){
        return new Object[]{this.gongKinhChiTiet,this.tenChatLieu.getTenChatLieu(),this.tenMauSac.getTenMauSac(),this.tenThuongHieu.getTenThuongHieu(),this.giaThanh,this.soLuong,this.hinhAnh,this.moTa,this.trangThai};
    }
}
