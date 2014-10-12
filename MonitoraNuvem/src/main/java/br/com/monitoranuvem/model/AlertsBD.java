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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class AlertsBD {

    private Connection conn;

    public boolean criarAlerts(Alerts alert) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO ALERTS (NAMEALERTS,IDPROVIDER,STATUSPROVIDER,METRICS,"
                + "OPERATION,VALUEMETRICS) VALUES (?,?,?,?,?,?)"
        );
        stmt.setString(1, alert.getNameAlerts());
        stmt.setInt(2, alert.getProv().getId());
        stmt.setString(3, alert.getStatusProvider());
        stmt.setString(4, alert.getMetrics());
        stmt.setString(5, alert.getMetrics());
        stmt.setString(6, alert.getValueMetrics());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return this.setIdAlerts(alert);
        }
        return false;
    }

    private boolean setIdAlerts(Alerts alert) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IFNULL(MAX(IDALERTS),0) AS IDALERTS FROM ALERTS"
        );
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            alert.setIdalerts(resultado.getInt("IDALERTS"));
        }
        conn.close();
        return true;
    }

    public Alerts buscaAlerts(int idAlert) throws ClassNotFoundException, SQLException {
        Alerts alert = new Alerts();
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDALERTS,NAMEALERTS,IDPROVIDER,STATUSPROVIDER,METRICS,"
                + "OPERATION,VALUEMETRICS FROM ALERTS WHERE IDALERTS=?"
        );
        stmt.setInt(1, idAlert);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            alert.setIdalerts(resultado.getInt("IDALERTS"));
            alert.setNameAlerts(resultado.getString("NAMEALERTS"));
            alert.setProv(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            alert.setStatusProvider(resultado.getString("STATUSPROVIDER"));
            alert.setMetrics(resultado.getString("METRICS"));
            alert.setOperation(resultado.getString("OPERATION"));
            alert.setValueMetrics(resultado.getString("VALUEMETRICS"));
        }
        conn.close();
        return alert;
    }

    public ArrayList<Alerts> listaAlerts() throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT * FROM ALERTS ");
        ArrayList<Alerts> lista = new ArrayList<>();
        Alerts alert = null;
        while (resultado.next()) {
            alert = new Alerts();
            alert.setIdalerts(resultado.getInt("IDALERTS"));
            alert.setNameAlerts(resultado.getString("NAMEALERTS"));
            alert.setProv(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            alert.setStatusProvider(resultado.getString("STATUSPROVIDER"));
            alert.setMetrics(resultado.getString("METRICS"));
            alert.setOperation(resultado.getString("OPERATION"));
            alert.setValueMetrics(resultado.getString("VALUEMETRICS"));
            lista.add(alert);
        }
        conn.close();
        return lista;
    }

    public boolean deletaAlerts(int idAlerts) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM ALERTS WHERE IDALERTS = ?"
        );
        stmt.setInt(1, idAlerts);
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizaAlerts(Alerts alert, int idAlerts) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE ALERTS SET NAMEALERTS=?,IDPROVIDER=?,"
                        + "STATUSPROVIDER=?,METRICS=?,OPERATION=?,VALUEMETRICS=? WHERE IDALERTS=?"
        );
        stmt.setString(1, alert.getNameAlerts());
        stmt.setInt(2, alert.getProv().getId());
        stmt.setString(3, alert.getStatusProvider());
        stmt.setString(4, alert.getMetrics());
        stmt.setString(5, alert.getOperation());
        stmt.setString(6, alert.getValueMetrics());
        stmt.setInt(7, idAlerts);
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        } else {
            return false;
        }
    }    
}
