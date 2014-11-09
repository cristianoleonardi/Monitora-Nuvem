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
public class HistorySendAlertsBD {

    private Connection conn;

    public boolean criarHistoricoAlerts(int idSendAlerts) throws ClassNotFoundException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateForMySql = "";
        Date data = new Date();
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO HISTORICOSENDALERTS (IDSENDALERTS,DATE) "
                + "VALUES (?,?)"
        );
        stmt.setInt(1, idSendAlerts);
        dateForMySql = sdf.format(data);
        stmt.setString(2, dateForMySql);
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        }
        return false;
    }

    public String diferenca(int IdsendAlerts) throws ClassNotFoundException, SQLException {
        String diff ="";
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT CONCAT(TIMEDIFF(MAX(DATE),MIN(DATE)),'') AS DIFERENCA "
                + "FROM HISTORICOSENDALERTS "
                + "WHERE IDSENDALERTS =?");
        stmt.setInt(1, IdsendAlerts);
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            diff = resultado.getString("DIFERENCA");
        }
        conn.close();
        return diff;

    }
}
