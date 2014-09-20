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
public class ProviderBD {

    private Connection conn;

    public boolean createProvider(String provider) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO PROVIDER (PROVIDER) VALUES (?)"
        );
        stmt.setString(1, provider);
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<NProvider> getProvider() throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT * FROM PROVIDER ORDER BY PROVIDER");
        ArrayList<NProvider> lista = new ArrayList<>();
        NProvider provider;
        while (resultado.next()) {
            int codigo = resultado.getInt("IDPROVIDER");
            String provedor = resultado.getString("PROVIDER");
            provider = new NProvider(codigo, provedor);
            lista.add(provider);
        }
        conn.close();
        return lista;
    }

    public boolean deletaProvider(NProvider np) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM PROVIDER WHERE IDPROVIDER = ?"
        );
        stmt.setInt(1, np.getId());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizaProvider(NProvider np, String provider) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE PROVIDER SET PROVIDER=?WHERE IDPROVIDER=?"
        );
        stmt.setString(1, provider);
        stmt.setInt(2, np.getId());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        } else {
            return false;
        }
    }
}
