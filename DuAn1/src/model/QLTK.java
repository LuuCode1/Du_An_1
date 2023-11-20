/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class QLTK {

    private int idTK;
    private String maTK;
    private String tenTK;

    public QLTK() {
    }

    public QLTK(String maTK, String tenTK) {
        this.maTK = maTK;
        this.tenTK = tenTK;
    }
    
    

    public QLTK(int idTK, String maTK, String tenTK) {
        this.idTK = idTK;
        this.maTK = maTK;
        this.tenTK = tenTK;
    }

    public int getIdTK() {
        return idTK;
    }

    public void setIdTK(int idTK) {
        this.idTK = idTK;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public Object[] toData() {
        return new Object[]{this.idTK, this.maTK, this.tenTK};
    }
}
