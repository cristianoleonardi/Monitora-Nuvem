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

    public boolean criarProvider(Provider provider) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO PROVIDER (PROVIDER) VALUES (?)"
        );
        stmt.setString(1, provider.getNome());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return this.setIdProvider(provider);
        }
        return false;
    }

    private boolean setIdProvider(Provider provider) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDPROVIDER FROM PROVIDER WHERE PROVIDER=?"
        );
        stmt.setString(1, provider.getNome());
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            int codigo = resultado.getInt("IDPROVIDER");
            provider.setId(codigo);
        }
        conn.close();
        return true;
    }

    public Provider buscaProvider(int id) throws ClassNotFoundException, SQLException {
        Provider pn=null;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT PROVIDER FROM PROVIDER WHERE IDPROVIDER=?"
        );
        stmt.setInt(1, id);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            String provider = resultado.getString("PROVIDER");
            pn = new Provider();
            pn.setId(id);
            pn.setNome(provider);
        }
        conn.close();
        return pn;
    }
    public Provider buscaProvider(String provider) throws ClassNotFoundException, SQLException {
        Provider pn=null;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDPROVIDER FROM PROVIDER WHERE PROVIDER=?"
        );
        stmt.setString(1, provider);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            pn = new Provider();
            pn.setId(resultado.getInt("IDPROVIDER"));
            pn.setNome(provider);
        }
        conn.close();
        return pn;
    }

    public ArrayList<Provider> listaProvider() throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT * FROM PROVIDER ORDER BY PROVIDER");
        ArrayList<Provider> lista = new ArrayList<>();
        Provider provider;
        while (resultado.next()) {
            int codigo = resultado.getInt("IDPROVIDER");
            String provedor = resultado.getString("PROVIDER");
            provider = new Provider();
            provider.setId(codigo);
            provider.setNome(provedor);
            lista.add(provider);
        }
        conn.close();
        return lista;
    }

    public boolean deletaProvider(Provider np) throws ClassNotFoundException, SQLException {
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

    public boolean atualizaProvider(Provider np, String provider) throws ClassNotFoundException, SQLException {
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
