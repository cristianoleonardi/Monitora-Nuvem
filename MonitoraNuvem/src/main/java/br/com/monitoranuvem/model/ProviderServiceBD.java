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
public class ProviderServiceBD {

    private Connection conn;

    public boolean criarProviderService(ProviderService ps, ProviderN pn) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO PROVIDERSERVICE (PROVIDERSERVICE,ACESSKEY,SECRETKEY,IDPROVIDER) VALUES (?,?,?,?)"
        );
        stmt.setString(1, ps.getProviderService());
        stmt.setString(2, ps.getAcessKey());
        stmt.setString(3, ps.getSecretKey());
        stmt.setInt(4, pn.getId());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return this.setIdProviderService(ps, pn);
        } else {
            return false;
        }
    }

    private boolean setIdProviderService(ProviderService ps, ProviderN pn) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDPROVIDERSERVICE FROM PROVIDERSERVICE WHERE PROVIDERSERVICE=? AND IDPROVIDER=?"
        );
        stmt.setString(1, ps.getProviderService());
        stmt.setInt(2, pn.getId());
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            int codigo = resultado.getInt("IDPROVIDERSERVICE");
            ps.setIdProviderService(codigo);
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }

    public ProviderService buscaProviderService(int id) throws ClassNotFoundException, SQLException {
        ProviderService ps = null;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT PROVIDERSERVICE,ACESSKEY,SECRETKEY,IDPROVIDER FROM PROVIDERSERVICE WHERE IDPROVIDERSERVICE=?"
        );
        stmt.setInt(1, id);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            ps = new ProviderService();
            ps.setIdProviderService(id);
            ps.setProviderService(resultado.getString("PROVIDERSERVICE"));
            ps.setAcessKey(resultado.getString("ACESSKEY"));
            ps.setSecretKey(resultado.getString("SECRETKEY"));
            ps.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
        }
        conn.close();
        return ps;
    }

    public ArrayList<ProviderN> listaProvider() throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT * FROM PROVIDER ORDER BY PROVIDER");
        ArrayList<ProviderN> lista = new ArrayList<>();
        ProviderN provider;
        while (resultado.next()) {
            int codigo = resultado.getInt("IDPROVIDER");
            String provedor = resultado.getString("PROVIDER");
            provider = new ProviderN();
            provider.setId(codigo);
            provider.setNome(provedor);
            lista.add(provider);
        }
        conn.close();
        return lista;
    }

    public boolean deletaProvider(ProviderN np) throws ClassNotFoundException, SQLException {
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

    public boolean atualizaProvider(ProviderN np, String provider) throws ClassNotFoundException, SQLException {
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
