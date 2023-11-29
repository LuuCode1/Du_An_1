/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leduc
 */
public class SanPham {
    
    private int idSP;
    private String maSP;
    private String tenSP;
    private LoaiSP loaisp;
    private Thuonghieu brand;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
    }
    
    

    public SanPham(int idSP, String maSP, String tenSP, LoaiSP loaisp, Thuonghieu brand) {
        this.idSP = idSP;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaisp = loaisp;
        this.brand = brand;
    }

    public SanPham(String maSP, String tenSP, LoaiSP loaisp, Thuonghieu brand) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaisp = loaisp;
        this.brand = brand;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public LoaiSP getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(LoaiSP loaisp) {
        this.loaisp = loaisp;
    }

    public Thuonghieu getBrand() {
        return brand;
    }

    public void setBrand(Thuonghieu brand) {
        this.brand = brand;
    }
    
    public Object[] todata(){
        return new Object[]{this.idSP,this.maSP,this.tenSP,this.loaisp.getTenLoaiSP(),this.brand.getTenThuongHieu()};
    }
}
