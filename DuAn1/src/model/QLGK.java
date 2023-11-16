/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dat
 */
public class QLGK {
    private String id;
    private String maGK;
    private String tenGK;

    public QLGK() {
    }

    public QLGK(String id, String maGK, String tenGK) {
        this.id = id;
        this.maGK = maGK;
        this.tenGK = tenGK;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaGK() {
        return maGK;
    }

    public void setMaGK(String maGK) {
        this.maGK = maGK;
    }

    public String getTenGK() {
        return tenGK;
    }

    public void setTenGK(String tenGK) {
        this.tenGK = tenGK;
    }

    @Override
    public String toString() {
        return "QLGK{" + "id=" + id + ", maGK=" + maGK + ", tenGK=" + tenGK + '}';
    }
    
    
}
