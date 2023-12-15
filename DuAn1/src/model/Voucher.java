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
public class Voucher {

    private int idVouchers;
    private String maVouchers;
    private String tenVouchers;
    private int Soluong;
    private Date ngaytao;
    private Date ngayketthuc;
    private float giatri;
    private int soluongdadung;
    private String trangthai;

    public Voucher() {
    }

    public Voucher(int idVouchers, String maVouchers, String tenVouchers, int Soluong, Date ngaytao, Date ngayketthuc, float giatri, int soluongdadung, String trangthai) {
        this.idVouchers = idVouchers;
        this.maVouchers = maVouchers;
        this.tenVouchers = tenVouchers;
        this.Soluong = Soluong;
        this.ngaytao = ngaytao;
        this.ngayketthuc = ngayketthuc;
        this.giatri = giatri;
        this.soluongdadung = soluongdadung;
        this.trangthai = trangthai;
    }

    public Voucher(String maVouchers, String tenVouchers, int Soluong, Date ngaytao, Date ngayketthuc, float giatri, int soluongdadung, String trangthai) {
        this.maVouchers = maVouchers;
        this.tenVouchers = tenVouchers;
        this.Soluong = Soluong;
        this.ngaytao = ngaytao;
        this.ngayketthuc = ngayketthuc;
        this.giatri = giatri;
        this.soluongdadung = soluongdadung;
        this.trangthai = trangthai;
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
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Date getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(Date ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public float getGiatri() {
        return giatri;
    }

    public void setGiatri(float giatri) {
        this.giatri = giatri;
    }

    public int getSoluongdadung() {
        return soluongdadung;
    }

    public void setSoluongdadung(int soluongdadung) {
        this.soluongdadung = soluongdadung;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getSLCon() {
        return (this.Soluong - this.soluongdadung);
    }

}
