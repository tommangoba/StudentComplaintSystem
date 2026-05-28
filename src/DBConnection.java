/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RODELYN MANZO
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
     public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/login_db";
        String user = "root";
        String password = ""; // default in XAMPP
        return DriverManager.getConnection(url, user, password);
     }
}
