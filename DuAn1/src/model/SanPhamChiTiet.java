/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leduc
 */
public class SanPhamChiTiet {
    private SanPham sp;
    private int    idSPChiTiet;
    private Mausac color;
    private ChatLieu material;
    private double giathanh;
    private Integer soluong;
    private String hinhanh;
    private String mota;
    private String trangthai;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(SanPham sp, int idSPChiTiet, Mausac color, ChatLieu material, double giathanh, Integer soluong, String hinhanh, String mota, String trangthai) {
        this.sp = sp;
        this.idSPChiTiet = idSPChiTiet;
        this.color = color;
        this.material = material;
        this.giathanh = giathanh;
        this.soluong = soluong;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.trangthai = trangthai;
    }

    public SanPhamChiTiet(int idSPChiTiet, Mausac color, ChatLieu material, double giathanh, Integer soluong, String hinhanh, String mota, String trangthai) {
        this.idSPChiTiet = idSPChiTiet;
        this.color = color;
        this.material = material;
        this.giathanh = giathanh;
        this.soluong = soluong;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.trangthai = trangthai;
    }
    
    

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public int getIdSPChiTiet() {
        return idSPChiTiet;
    }

    public void setIdSPChiTiet(int idSPChiTiet) {
        this.idSPChiTiet = idSPChiTiet;
    }

    public Mausac getColor() {
        return color;
    }

    public void setColor(Mausac color) {
        this.color = color;
    }

    public ChatLieu getMaterial() {
        return material;
    }

    public void setMaterial(ChatLieu material) {
        this.material = material;
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

    
    
    public Object[] todata(){
        return new Object[]{this.idSPChiTiet,
            this.material.getTenChatLieu(),this.color.getTenMauSac(),this.giathanh,this.soluong,this.hinhanh,this.mota,this.trangthai};
    }
    
}
