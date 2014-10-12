/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.connection;

import java.sql.Connection;
import java.sql.DriverManager;
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
    final private String senha = "marcio";
    private Connection conexao=null;

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        if (conexao== null) {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
         }
        return  conexao;
    }
}
