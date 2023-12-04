/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class Thuonghieu {
    private int idThuongHieu;
    private String maThuongHieu;
    private String tenThuongHieu;

    public Thuonghieu() {
    }

    public Thuonghieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public Thuonghieu(int idThuongHieu, String maThuongHieu, String tenThuongHieu) {
        this.idThuongHieu = idThuongHieu;
        this.maThuongHieu = maThuongHieu;
        this.tenThuongHieu = tenThuongHieu;
    }

    public Thuonghieu(String maThuongHieu, String tenThuongHieu) {
        this.maThuongHieu = maThuongHieu;
        this.tenThuongHieu = tenThuongHieu;
    }

    public Thuonghieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public int getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(int idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public String getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(String maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }
    public Object[] todata(){
        return new Object[]{this.maThuongHieu,this.tenThuongHieu};
    }
}
