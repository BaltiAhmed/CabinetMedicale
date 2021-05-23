/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sanabenfadhel
 */public class DataSource {

    private static DataSource data;
    private Connection con;


    //   static String url="jdbc:mysql://localhost:3306/testelife";
//   static String login="root";
//   static String pwd="";

    static String url = "jdbc:mysql://localhost:3306/cabinetmedicale";

    static String login = "root";
    static String pwd = "";

    private DataSource() {

        try {
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static DataSource getInstance() {
        if (data == null) {
            data = new DataSource();
        }
        return data;
    }

}