/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Asus
 */
public class DBconnect {
    public static final String URL
            = "jdbc:sqlserver://localhost;databaseName=Ung_Dung_Ban_Kinh;user=sa;password=1234;encrypt=true;trustServerCertificate=true";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.out.println("Chua co Driver !" + e.toString());
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            //Loi sai ten database;
            //Loi ten dang nhap va mat khau;
            System.out.println("Loi connect" + e.toString());
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("thanh cong");
        } else {
            System.out.println("that bai");
        }
    }
}
