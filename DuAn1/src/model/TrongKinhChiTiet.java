/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leduc
 */
public class TrongKinhChiTiet {
    private int    idTrongKinh;
    private QLTK   tk;
    private Mausac color;
    private Thuonghieu brand;
    private ChatLieu material;
    private double giathanh;
    private double docan;
    private Integer soluong;
    private String hinhanh;
    private String mota;
    private String trangthai;
//e

    public TrongKinhChiTiet() {
    }

    public TrongKinhChiTiet(QLTK tk, Mausac color, Thuonghieu brand, ChatLieu material, double giathanh, double docan, Integer soluong, String hinhanh, String mota, String trangthai) {
        this.tk = tk;
        this.color = color;
        this.brand = brand;
        this.material = material;
        this.giathanh = giathanh;
        this.docan = docan;
        this.soluong = soluong;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.trangthai = trangthai;
    }
    
    

    public TrongKinhChiTiet(int idTrongKinh, QLTK tk, Mausac color, Thuonghieu brand, ChatLieu material, double giathanh, double docan, Integer soluong, String hinhanh, String mota, String trangthai) {
        this.idTrongKinh = idTrongKinh;
        this.tk = tk;
        this.color = color;
        this.brand = brand;
        this.material = material;
        this.giathanh = giathanh;
        this.docan = docan;
        this.soluong = soluong;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.trangthai = trangthai;
    }

    public int getIdTrongKinh() {
        return idTrongKinh;
    }

    public void setIdTrongKinh(int idTrongKinh) {
        this.idTrongKinh = idTrongKinh;
    }

    public QLTK getTk() {
        return tk;
    }

    public void setTk(QLTK tk) {
        this.tk = tk;
    }

    public Mausac getColor() {
        return color;
    }

    public void setColor(Mausac color) {
        this.color = color;
    }

    public Thuonghieu getBrand() {
        return brand;
    }

    public void setBrand(Thuonghieu brand) {
        this.brand = brand;
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

    public double getDocan() {
        return docan;
    }

    public void setDocan(double docan) {
        this.docan = docan;
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
        return new Object[]{this.idTrongKinh,this.color.getTenMauSac(),
            this.brand.getTenThuongHieu(),this.material.getTenChatLieu(),this.giathanh,this.docan,
            this.soluong,this.hinhanh,this.mota,this.trangthai};
    }
    
}
