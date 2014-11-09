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
 * @author Cristiano
 */
public class ProviderPriceBD {
    
    private Connection conn;

    public boolean criarProviderPrice(ProviderPrice pp) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO PROVIDERPRICE (INSTANCETYPE, PRICE, IDPROVIDER) VALUES (?,?,?)"
        );
        stmt.setString(1, pp.getInstanceType());
        stmt.setDouble(2, pp.getPrice());
        stmt.setInt(3, pp.getProvider().getId());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return this.setIdProviderPrice(pp);
        } else {
            return false;
        }
    }

    private boolean setIdProviderPrice(ProviderPrice pp) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDPROVIDERPRICE FROM PROVIDERPRICE WHERE INSTANCETYPE=?"
        );
        stmt.setString(1, pp.getInstanceType());
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            int codigo = resultado.getInt("IDPROVIDERPRICE");
            pp.setIdProviderPrice(codigo);
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }

    public ProviderPrice buscaProviderPrice(int id) throws ClassNotFoundException, SQLException {
        ProviderPrice pp = null;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT INSTANCETYPE,PRICE,IDPROVIDER FROM PROVIDERPRICE WHERE IDPROVIDERPRICE=?"
        );
        stmt.setInt(1, id);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            pp = new ProviderPrice(id, new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")),resultado.getString("INSTANCETYPE"), resultado.getDouble("PRICE"));
        }
        conn.close();
        return pp;
    }

    public ArrayList<ProviderPrice> listaProviderPrice() throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT * FROM PROVIDERPRICE ORDER BY INSTANCETYPE");
        ArrayList<ProviderPrice> lista = new ArrayList<>();
        ProviderPrice providerPrice;
        while (resultado.next()) {
            providerPrice = new ProviderPrice(resultado.getInt("IDPROVIDERPRICE"), new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")),resultado.getString("INSTANCETYPE"), resultado.getDouble("PRICE"));
            lista.add(providerPrice);
        }
        conn.close();
        return lista;
    }

    public boolean deletaProviderPrice(ProviderPrice pp) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM PROVIDERPRICE WHERE IDPROVIDERPRICE = ?"
        );
        stmt.setInt(1, pp.getIdProviderPrice());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizaProviderPrice(ProviderPrice old) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE PROVIDERPRICE SET INSTANCETYPE=?, PRICE=?, "
                        + "IDPROVIDER=? WHERE IDPROVIDERPRICE=?"
        );
        stmt.setString(1, old.getInstanceType());
        stmt.setDouble(2, old.getPrice());
        stmt.setInt(3, old.getProvider().getId());
        stmt.setInt(4, old.getIdProviderPrice());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        } else {
            return false;
        }
    }
 }
