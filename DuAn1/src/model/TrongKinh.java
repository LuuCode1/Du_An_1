/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leduc
 */
public class TrongKinh {
    private int    idTrongKinh;
    private String maTrongKinh;
    private String tenTrongKinh;
    private mausac color;
    private thuonghieu brand;
    private chatLieu material;
    private double giathanh;
    private double docan;
    private Integer soluong;
    private String hinhanh;
    private String mota;
    private String trangthai;

    public TrongKinh() {
    }

    public TrongKinh(int idTrongKinh, String maTrongKinh, String tenTrongKinh, mausac color, thuonghieu brand, chatLieu material, double giathanh, double docan, Integer soluong, String hinhanh, String mota, String trangthai) {
        this.idTrongKinh = idTrongKinh;
        this.maTrongKinh = maTrongKinh;
        this.tenTrongKinh = tenTrongKinh;
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

    public String getMaTrongKinh() {
        return maTrongKinh;
    }

    public void setMaTrongKinh(String maTrongKinh) {
        this.maTrongKinh = maTrongKinh;
    }

    public String getTenTrongKinh() {
        return tenTrongKinh;
    }

    public void setTenTrongKinh(String tenTrongKinh) {
        this.tenTrongKinh = tenTrongKinh;
    }

    public mausac getColor() {
        return color;
    }

    public void setColor(mausac color) {
        this.color = color;
    }

    public thuonghieu getBrand() {
        return brand;
    }

    public void setBrand(thuonghieu brand) {
        this.brand = brand;
    }

    public chatLieu getMaterial() {
        return material;
    }

    public void setMaterial(chatLieu material) {
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
        return new Object[]{this.maTrongKinh,this.tenTrongKinh,this.color.getTenMauSac(),
            this.brand.getTenThuongHieu(),this.material.getTenChatLieu(),this.giathanh,this.docan,
            this.soluong,this.hinhanh,this.mota};
    }
    
}
