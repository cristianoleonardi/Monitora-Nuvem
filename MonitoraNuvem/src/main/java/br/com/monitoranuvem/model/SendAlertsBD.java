/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import br.com.monitoranuvem.connection.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marcio
 */
public class SendAlertsBD {

    private Connection conn;

    public boolean criarAlerts(SendAlerts sendAlert) throws ClassNotFoundException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateForMySql = "";
        Date data = new Date();
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO SENDALERTS (IDALERTS,DATESENDALERTS,SEND) "
                + "VALUES (?,?,?)"
        );
        stmt.setInt(1, sendAlert.getIdAlerts());
        dateForMySql = sdf.format(data);
        stmt.setString(2, dateForMySql);
        stmt.setInt(3, 0);
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        }
        return false;
    }
}
