/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class SPChiTiet {

    private int idCT;
    private LoaiSP loai;
    private SanPham sp;
    private ChatLieu material;
    private Mausac color;
    private double doCan;
    private double giathanh;
    private Integer soluong;
    private String hinhanh;
    private String mota;
    private String trangthai;

    public SPChiTiet() {
    }

    public SPChiTiet(int idCT, LoaiSP loai, SanPham sp, ChatLieu material, Mausac color, double doCan, double giathanh, Integer soluong, String hinhanh, String mota, String trangthai) {
//        this.idCT = idCT;
        this.loai = loai;
        this.sp = sp;
        this.material = material;
        this.color = color;
        this.doCan = doCan;
        this.giathanh = giathanh;
        this.soluong = soluong;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.trangthai = trangthai;
    }

    public SPChiTiet(LoaiSP loai, SanPham sp, ChatLieu material, Mausac color, double doCan, double giathanh, Integer soluong, String hinhanh, String mota, String trangthai) {
        this.loai = loai;
        this.sp = sp;
        this.material = material;
        this.color = color;
        this.doCan = doCan;
        this.giathanh = giathanh;
        this.soluong = soluong;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.trangthai = trangthai;
    }

    public SPChiTiet(LoaiSP loai, SanPham sp, ChatLieu material, Mausac color, double doCan, double giathanh, Integer soluong, String mota, String trangthai) {
        this.loai = loai;
        this.sp = sp;
        this.material = material;
        this.color = color;
        this.doCan = doCan;
        this.giathanh = giathanh;
        this.soluong = soluong;
        this.mota = mota;
        this.trangthai = trangthai;
    }

    public int getIdCT() {
        return idCT;
    }

    public void setIdCT(int idCT) {
        this.idCT = idCT;
    }

    public LoaiSP getLoai() {
        return loai;
    }

    public void setLoai(LoaiSP loai) {
        this.loai = loai;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public ChatLieu getMaterial() {
        return material;
    }

    public void setMaterial(ChatLieu material) {
        this.material = material;
    }

    public Mausac getColor() {
        return color;
    }

    public void setColor(Mausac color) {
        this.color = color;
    }

    public double getDoCan() {
        return doCan;
    }

    public void setDoCan(double doCan) {
        this.doCan = doCan;
    }

    public double getGiathanh() {
        return giathanh;
    }

    public void setGiathanh(double giathanh) {
        this.giathanh = giathanh;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

}
