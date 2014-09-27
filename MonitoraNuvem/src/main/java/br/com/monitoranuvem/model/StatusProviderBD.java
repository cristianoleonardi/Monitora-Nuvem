/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import br.com.monitoranuvem.connection.ConnectionMySql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class StatusProviderBD {

    private Connection conn;

    public ArrayList<StatusProvider> listaStatusProvider() throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT * FROM STATUSPROVIDER");
        ArrayList<StatusProvider> lista = new ArrayList<>();
        StatusProvider statusProvider;
        while (resultado.next()) {
            statusProvider = new StatusProvider();
            statusProvider.setIdStatusProvider(resultado.getInt("IDSTATUSPROVIDER"));
            statusProvider.setStatusProvider(resultado.getString("STATUSPROVIDER"));
            statusProvider.setInstanceProvider(new InstanceProviderBD().buscaInstanceProvider(resultado.getInt("IDINSTANCEPROVIDER")));
            lista.add(statusProvider);
        }
        conn.close();
        return lista;
    }

    public ArrayList<QtdStatusProvider> listaQDTStatusProvider() throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT A.IDPROVIDER, B.STATUSPROVIDER, COUNT(*) AS QUANTIDADE \n"
                + "FROM INSTANCEPROVIDER	A,\n"
                + "	 STATUSPROVIDER		b \n"
                + "WHERE	A.IDINSTANCEPROVIDER = B.IDINSTANCEPROVIDER\n"
                + "GROUP BY A.IDPROVIDER,B.STATUSPROVIDER");
        ArrayList<QtdStatusProvider> lista = new ArrayList<>();
        QtdStatusProvider qtdStatus;
        while (resultado.next()) {
            qtdStatus = new QtdStatusProvider();
            qtdStatus.setQuantidade(resultado.getInt("QUANTIDADE"));
            qtdStatus.setStatus(resultado.getString("STATUSPROVIDER"));
            qtdStatus.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            lista.add(qtdStatus);
        }
        conn.close();
        return lista;
    }
}
