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

/**
 *
 * @author Marcio
 */
public class InstanceProviderBD {
    private Connection conn;
   
   public InstanceProvider buscaInstanceProvider(int id) throws ClassNotFoundException, SQLException {
        InstanceProvider instance=null;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT INSTANCEPROVIDER FROM INSTANCEPROVIDER WHERE IDINSTANCEPROVIDER=?"
        );
        stmt.setInt(1, id);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            String instanceProvider = resultado.getString("INSTANCEPROVIDER");
            instance = new InstanceProvider();
            instance.setIdInstance(id);
            instance.setInstanceprovider(instanceProvider);
        }
        conn.close();
        return instance;
    }
//    public Provider buscaProvider(String provider) throws ClassNotFoundException, SQLException {
//        Provider pn=null;
//        conn = new ConnectionMySql().getConnection();
//        PreparedStatement stmt = conn.prepareStatement(
//                "SELECT IDPROVIDER FROM PROVIDER WHERE PROVIDER=?"
//        );
//        stmt.setString(1, provider);
//        ResultSet resultado = stmt.executeQuery();
//        if (resultado.next()) {
//            pn = new Provider();
//            pn.setId(resultado.getInt("IDPROVIDER"));
//            pn.setNome(provider);
//        }
//        conn.close();
//        return pn;
//    }
//
//    public ArrayList<Provider> listaProvider() throws ClassNotFoundException, SQLException {
//        conn = new ConnectionMySql().getConnection();
//        Statement stmt = conn.createStatement();
//        ResultSet resultado = stmt.executeQuery("SELECT * FROM PROVIDER ORDER BY PROVIDER");
//        ArrayList<Provider> lista = new ArrayList<>();
//        Provider provider;
//        while (resultado.next()) {
//            int codigo = resultado.getInt("IDPROVIDER");
//            String provedor = resultado.getString("PROVIDER");
//            provider = new Provider();
//            provider.setId(codigo);
//            provider.setNome(provedor);
//            lista.add(provider);
//        }
//        conn.close();
//        return lista;
//    }
    
}
