/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dat
 */
public class KhachHang {
    private int idKH;
    private String maKH;
    private String tenKH;
    private String diaChi;
    private String sdt;

    public KhachHang() {
    }

    public KhachHang(int idKH, String maKH, String tenKH, String diaChi, String sdt) {
        this.idKH = idKH;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public KhachHang(String maKH, String tenKH, String diaChi, String sdt) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public KhachHang(int idKH) {
        this.idKH = idKH;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

   
    
}
