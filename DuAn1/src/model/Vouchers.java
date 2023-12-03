/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author leduc
 */
public class Vouchers {
    private int idVouchers;
    private String maVouchers;
    private String tenVouchers;
    private int soluong;
    private Date ngayTao;
    private Date ngayHet;
    private Double giaTri;
    private String trangThai;

    public Vouchers() {
    }

    public Vouchers(int idVouchers, String maVouchers, String tenVouchers, int soluong, Date ngayTao, Date ngayHet, Double giaTri, String trangThai) {
        this.idVouchers = idVouchers;
        this.maVouchers = maVouchers;
        this.tenVouchers = tenVouchers;
        this.soluong = soluong;
        this.ngayTao = ngayTao;
        this.ngayHet = ngayHet;
        this.giaTri = giaTri;
        this.trangThai = trangThai;
    }

    public int getIdVouchers() {
        return idVouchers;
    }

    public void setIdVouchers(int idVouchers) {
        this.idVouchers = idVouchers;
    }

    public String getMaVouchers() {
        return maVouchers;
    }

    public void setMaVouchers(String maVouchers) {
        this.maVouchers = maVouchers;
    }

    public String getTenVouchers() {
        return tenVouchers;
    }

    public void setTenVouchers(String tenVouchers) {
        this.tenVouchers = tenVouchers;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayHet() {
        return ngayHet;
    }

    public void setNgayHet(Date ngayHet) {
        this.ngayHet = ngayHet;
    }

    public Double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Double giaTri) {
        this.giaTri = giaTri;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
}

