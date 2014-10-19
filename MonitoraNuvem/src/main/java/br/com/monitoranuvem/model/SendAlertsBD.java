/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import br.com.monitoranuvem.connection.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public int criarAlerts(int idAlerts) throws ClassNotFoundException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateForMySql = "";
        Date data = new Date();
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO SENDALERTS (IDALERTS,DATESENDALERTS,SEND) "
                + "VALUES (?,?,?)"
        );
        stmt.setInt(1, idAlerts);
        dateForMySql = sdf.format(data);
        stmt.setString(2, dateForMySql);
        stmt.setInt(3, 0);
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return montaId();
        }
        return 0;
    }

    private int montaId() throws ClassNotFoundException, SQLException {
        int num = 0;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT MAX(IDSENDALERTS) ID \n"
                + "FROM SENDALERTS ");
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            num = resultado.getInt("ID");
        }
        conn.close();
        return num;
    }

    public int existeAlert(int idAlert) throws ClassNotFoundException, SQLException {
        int num = 0;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT COUNT(*) AS QUANTIDADE \n"
                + "FROM SENDALERTS	\n"
                + "WHERE IDALERTS=? AND SEND =?");
        stmt.setInt(1, idAlert);
        stmt.setInt(2, 0);
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            num = resultado.getInt("QUANTIDADE");
        }
        conn.close();
        return num;
    }
    public int buscaSendAlert(int idAlerts) throws ClassNotFoundException, SQLException {
        int idSendAlert=0;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDSENDALERTS FROM SENDALERTS WHERE IDALERTS=? AND SEND =?"
        );
        stmt.setInt(1, idAlerts);
        stmt.setInt(2, 0);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
           idSendAlert = resultado.getInt("IDSENDALERTS");
        }
        conn.close();
        return idSendAlert;
    }

}
