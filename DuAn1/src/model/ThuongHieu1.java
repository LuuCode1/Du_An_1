/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class ThuongHieu1 {

    String maTH;
    String tenTH;

    public ThuongHieu1() {
    }

    public ThuongHieu1(String maTH, String tenTH) {
        this.maTH = maTH;
        this.tenTH = tenTH;
    }

    public String getMaTH() {
        return maTH;
    }

    public void setMaTH(String maTH) {
        this.maTH = maTH;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    public Object[] toData() {
        return new Object[]{this.maTH, this.tenTH};
    }
}
