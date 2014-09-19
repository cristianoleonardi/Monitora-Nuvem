/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marcio
 */
public class ConnectionMySql {    
    final private String driver = "com.mysql.jdbc.Driver";
    final private String serverName = "127.0.0.1:3306";
    final private String mydatabase = "tcc";
    final private String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
    final private String usuario = "root";
    final private String senha = "tcc";
    private Connection conexao;

    public boolean conecta() {
        boolean result = true;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException | SQLException e) {
            result = false;
        }
        return result;
    }
}

//
//    private static ConnectionMySql conn = null;
//
//    public static ConnectionMySql getInstance() throws ClassNotFoundException, SQLException {
//        if (conn == null) {
//            conn = new ConnectionMySql();
//            System.out.println("conectou");
//        }
//        return conn;
//    }
//
//    private Connection ConnectionMySql() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        String serverName = "127.0.0.1:3306";
//        String mydatabase = "tcc";
//        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
//        String username = "root";
//        String password = "tcc";
//        return DriverManager.getConnection(url, username, password);
//    }
//}
