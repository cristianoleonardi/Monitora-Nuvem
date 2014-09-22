/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.ProviderBD;
import br.com.monitoranuvem.model.Provider;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ProviderControl {

    public boolean criarProvider(String provider) throws ClassNotFoundException, SQLException {
        Provider p = new Provider();
        p.setNome(provider);
        return new ProviderBD().criarProvider(p);
    }

    public Provider buscaProvider(int id) throws ClassNotFoundException, SQLException {
        return new ProviderBD().buscaProvider(id);
    }

    public Provider buscaProvider(String provider) throws ClassNotFoundException, SQLException {
        return new ProviderBD().buscaProvider(provider);
    }
    public ArrayList<Provider> listaProvider() throws ClassNotFoundException, SQLException {
        return new ProviderBD().listaProvider();
    }

    public boolean deletaProvider(int id) throws ClassNotFoundException, SQLException {
        return new ProviderBD().deletaProvider(id);
    }

    public boolean atualizaProvider(int id, String provider) throws ClassNotFoundException, SQLException {
        Provider p = buscaProvider(id);
        return new ProviderBD().atualizaProvider(p, provider);
    }

}
