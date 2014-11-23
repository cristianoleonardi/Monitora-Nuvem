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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Marcio
 */
public class HistoryCostBD {

    private Connection conn;

    public boolean criarHistoricoCusto(Provider prov, String custo, String tempo) throws ClassNotFoundException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = new Date();
        String dateForMySql = "";
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO HISTORYCOSTPROVIDER (IDPROVIDER, COST, TIME, DATE)"
                + " VALUES (?,?,?,?)"
        );
        stmt.setInt(1, prov.getId());
        stmt.setString(2, custo);
        stmt.setString(3, tempo);
        dateForMySql = sdf.format(data);
        stmt.setString(4, dateForMySql);
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<HistoryCostProvider> listHistoryCostProvider() throws ClassNotFoundException, SQLException, ParseException {
        conn = new ConnectionMySql().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery(""
                + "SELECT * "
                + "FROM HISTORYCOSTPROVIDER "
                + "ORDER BY IDPROVIDER, DATE ");
        ArrayList<HistoryCostProvider> lista = new ArrayList<>();
        HistoryCostProvider hist;
        String date_s;
        SimpleDateFormat dt;
        Date date;
        while (resultado.next()) {
            hist = new HistoryCostProvider();
            hist.setIdHistoryCostProvider(resultado.getInt("IDHISTORYCOSTPROVIDER"));
            hist.setProv(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            hist.setCost(resultado.getString("COST"));
            hist.setTime(resultado.getString("TIME"));
            date_s = resultado.getString("DATE");
            dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = dt.parse(date_s);
            hist.setData(date);
            lista.add(hist);
        }
        conn.close();
        return lista;
    }

    public ArrayList<HistoryCostProvider> listHistoryCostProvider(int idProvider) throws ClassNotFoundException, SQLException, ParseException {
        ArrayList<HistoryCostProvider> lista = new ArrayList<>();
        HistoryCostProvider hist;
        String date_s;
        SimpleDateFormat dt;
        Date date;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * "
                + "FROM HISTORYCOSTPROVIDER "
                + "WHERE IDPROVIDER = ? "
        );
        stmt.setInt(1, idProvider);
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            hist = new HistoryCostProvider();
            hist.setIdHistoryCostProvider(resultado.getInt("IDHISTORYCOSTPROVIDER"));
            hist.setProv(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            hist.setCost(resultado.getString("COST"));
            hist.setTime(resultado.getString("TIME"));
            date_s = resultado.getString("DATE");
            dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = dt.parse(date_s);
            hist.setData(date);
            lista.add(hist);
        }
        conn.close();
        return lista;
    }
    
    public ArrayList<HistoryCostProvider> listHistoryCostDay() throws ClassNotFoundException, SQLException, ParseException {
        ArrayList<HistoryCostProvider> lista = new ArrayList<>();
        HistoryCostProvider hist;
        String date_s;
        SimpleDateFormat dt;
        Date date;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT A.* "
                + "FROM HISTORYCOSTPROVIDER A "
                + "WHERE A.IDHISTORYCOSTPROVIDER  IN ( "
                        + "SELECT MAX(B.IDHISTORYCOSTPROVIDER) "
                        + "FROM HISTORYCOSTPROVIDER B "
                        + "GROUP BY B.IDPROVIDER, DATE(B.DATE)) ORDER BY A.DATE DESC"
        );
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            hist = new HistoryCostProvider();
            hist.setIdHistoryCostProvider(resultado.getInt("IDHISTORYCOSTPROVIDER"));
            hist.setProv(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            hist.setCost(resultado.getString("COST"));
            hist.setTime(resultado.getString("TIME"));
            date_s = resultado.getString("DATE");
            dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = dt.parse(date_s);
            hist.setData(date);
            lista.add(hist);
        }
        conn.close();
        return lista;
    }
           

}
