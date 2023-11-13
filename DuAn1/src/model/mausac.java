/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class mausac {
    private int idMauSac;
   private String maMauSac;
   private String tenMauSac;

    public mausac() {
    }

    public mausac(int idMauSac, String maMauSac, String tenMauSac) {
        this.idMauSac = idMauSac;
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
    }

    public mausac(String maMauSac, String tenMauSac) {
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
    }

    public int getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(int idMauSac) {
        this.idMauSac = idMauSac;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }
   public Object[] todata() {
    return new Object[] {this.maMauSac, this.tenMauSac};
  }

   }
