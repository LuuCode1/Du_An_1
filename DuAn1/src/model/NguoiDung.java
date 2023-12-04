/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class NguoiDung {
    private int idND;
    private String maND;
    private String tenND;
    private Date ngaySinh;
    private String matKhau;
    private String SDT;
    private int GioiTinh;
    private String email;
    private int vaiTro;
private int trangthai;
    public NguoiDung() {
    }

    public NguoiDung(int idND, String maND, String tenND, Date ngaySinh, String matKhau, String SDT, int GioiTinh, String email, int vaiTro) {
        this.idND = idND;
        this.maND = maND;
        this.tenND = tenND;
        this.ngaySinh = ngaySinh;
        this.matKhau = matKhau;
        this.SDT = SDT;
        this.GioiTinh = GioiTinh;
        this.email = email;
        this.vaiTro = vaiTro;
    }

    public NguoiDung(String maND, String tenND, Date ngaySinh, String matKhau, String SDT, int GioiTinh, String email, int vaiTro) {
        this.maND = maND;
        this.tenND = tenND;
        this.ngaySinh = ngaySinh;
        this.matKhau = matKhau;
        this.SDT = SDT;
        this.GioiTinh = GioiTinh;
        this.email = email;
        this.vaiTro = vaiTro;
    }

    public NguoiDung(String maND, String tenND, Date ngaySinh, String matKhau, String SDT, int GioiTinh, String email, int vaiTro, int trangthai) {
        this.maND = maND;
        this.tenND = tenND;
        this.ngaySinh = ngaySinh;
        this.matKhau = matKhau;
        this.SDT = SDT;
        this.GioiTinh = GioiTinh;
        this.email = email;
        this.vaiTro = vaiTro;
        this.trangthai = trangthai;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getIdND() {
        return idND;
    }

    public void setIdND(int idND) {
        this.idND = idND;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
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

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

    

        public String getGT(){
        if (GioiTinh==0) {
            return "Nam";
        }else{
            return "Nữ";
        }
    }
    public String getVT(){
        if (vaiTro==0) {
            return "Quản Lý";
        }else{
            return "Nhân Viên";
        }
    }
    public String getTT(){
        if (trangthai==0) {
            return "Đang Làm";
        }else{
            return "Đã Nghỉ";
        }
    }
     public Object[] todata_NguoiDung(){
         return new Object[]{this.maND,
             this.tenND,
             this.ngaySinh,
             this.matKhau,
             this.SDT,
             this.getGT(),
             this.email,
             this.getVT(),
             this.getTT()
         };
     }
}
