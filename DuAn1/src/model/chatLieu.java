/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class ChatLieu {
    private int idChatLieu;
    private String maChatLieu;
    private String tenChatLieu;

    public ChatLieu() {
    }

    public ChatLieu(int idChatLieu, String maChatLieu, String tenChatLieu) {
        this.idChatLieu = idChatLieu;
        this.maChatLieu = maChatLieu;
        this.tenChatLieu = tenChatLieu;
    }

    public ChatLieu(String maChatLieu, String tenChatLieu) {
        this.maChatLieu = maChatLieu;
        this.tenChatLieu = tenChatLieu;
    }

    public ChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public int getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(int idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public String getMaChatLieu() {
        return maChatLieu;
    }

    public void setMaChatLieu(String maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }
    public Object[] todata_ChatLieu(){
        return new Object[]{this.maChatLieu,this.tenChatLieu};
    }
}
