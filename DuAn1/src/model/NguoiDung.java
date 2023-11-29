/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class NguoiDung {
    private int idND;
    private String tenND;
    private String ngaySinh;
    private String matKhau;
    private int SDT;
    private int GioiTinh;
    private String email;
    private String trangThai;

    public NguoiDung() {
    }

    public NguoiDung(int idND, String tenND, String ngaySinh, String matKhau, int SDT, int GioiTinh, String email, String trangThai) {
        this.idND = idND;
        this.tenND = tenND;
        this.ngaySinh = ngaySinh;
        this.matKhau = matKhau;
        this.SDT = SDT;
        this.GioiTinh = GioiTinh;
        this.email = email;
        this.trangThai = trangThai;
    }

    public NguoiDung(String tenND, String ngaySinh, String matKhau, int SDT, int GioiTinh, String email, String trangThai) {
        this.tenND = tenND;
        this.ngaySinh = ngaySinh;
        this.matKhau = matKhau;
        this.SDT = SDT;
        this.GioiTinh = GioiTinh;
        this.email = email;
        this.trangThai = trangThai;
    }

    public int getIdND() {
        return idND;
    }

    public void setIdND(int idND) {
        this.idND = idND;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    
     
}
