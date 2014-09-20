/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.ProviderBD;
import br.com.monitoranuvem.model.ProviderN;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ProviderControl {

    public boolean criarProvider(ProviderN provider) throws ClassNotFoundException, SQLException {
        return new ProviderBD().criarProvider(provider);
    }

    public ProviderN buscaProvider(int id) throws ClassNotFoundException, SQLException {
        return new ProviderBD().buscaProvider(id);
    }

    public ProviderN buscaProvider(String provider) throws ClassNotFoundException, SQLException {
        return new ProviderBD().buscaProvider(provider);
    }
    public ArrayList<ProviderN> listaProvider() throws ClassNotFoundException, SQLException {
        return new ProviderBD().listaProvider();
    }

    public boolean deletaProvider(ProviderN pn) throws ClassNotFoundException, SQLException {
        return new ProviderBD().deletaProvider(pn);
    }

    public boolean atualizaProvider(ProviderN pn, String provider) throws ClassNotFoundException, SQLException {
        return new ProviderBD().atualizaProvider(pn, provider);
    }

}
