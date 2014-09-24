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

    public boolean criarProviderService(ProviderService ps) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO PROVIDERSERVICE (PROVIDERSERVICE, ACESSKEY, SECRETKEY, IDPROVIDER) VALUES (?,?,?,?)"
        );
        stmt.setString(1, ps.getProviderService());
        stmt.setString(2, ps.getAcessKey());
        stmt.setString(3, ps.getSecretKey());
        stmt.setInt(4, ps.getProvider().getId());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return this.setIdProviderService(ps);
        } else {
            return false;
        }
    }

    private boolean setIdProviderService(ProviderService ps) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDPROVIDERSERVICE FROM PROVIDERSERVICE WHERE PROVIDERSERVICE=? AND IDPROVIDER=?"
        );
        stmt.setString(1, ps.getProviderService());
        stmt.setInt(2, ps.getProvider().getId());
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

    public ArrayList<ProviderService> listaProviderService() throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT * FROM PROVIDERSERVICE ORDER BY PROVIDERSERVICE");
        ArrayList<ProviderService> lista = new ArrayList<>();
        ProviderService providerService;
        while (resultado.next()) {
            providerService = new ProviderService();
            providerService.setIdProviderService(resultado.getInt("IDPROVIDERSERVICE"));
            providerService.setProviderService(resultado.getString("PROVIDERSERVICE"));
            providerService.setAcessKey(resultado.getString("ACESSKEY"));
            providerService.setSecretKey(resultado.getString("SECRETKEY"));
            providerService.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            lista.add(providerService);
        }
        conn.close();
        return lista;
    }

    public boolean deletaProviderService(ProviderService ps) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM PROVIDERSERVICE WHERE IDPROVIDERSERVICE = ?"
        );
        stmt.setInt(1, ps.getIdProviderService());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizaProvider(ProviderService old) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE PROVIDERSERVICE SET PROVIDERSERVICE=?, ACESSKEY=?, SECRETKEY=?, IDPROVIDER=? WHERE IDPROVIDERSERVICE=?"
        );
        stmt.setString(1, old.getProviderService());
        stmt.setString(2, old.getAcessKey());
        stmt.setString(3, old.getSecretKey());
        stmt.setInt(4, old.getProvider().getId());
        stmt.setInt(5, old.getIdProviderService());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        } else {
            return false;
        }
    }
}
