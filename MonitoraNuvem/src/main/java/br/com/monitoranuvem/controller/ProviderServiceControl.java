/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderService;
import br.com.monitoranuvem.model.ProviderServiceBD;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ProviderServiceControl {

    public boolean criarProviderService(int provider, String providerService, String accessKey, String secretAccessKey) throws ClassNotFoundException, SQLException {
        ProviderControl pc = new ProviderControl();
        Provider p = pc.buscaProvider(provider);
        
        ProviderService ps = new ProviderService();
        ps.setProviderService(providerService);
        ps.setProvider(p);
        ps.setAcessKey(accessKey);
        ps.setSecretKey(secretAccessKey);
        
        return new ProviderServiceBD().criarProviderService(ps);
    }

    public ProviderService buscaProviderService(int id) throws ClassNotFoundException, SQLException {
        return new ProviderServiceBD().buscaProviderService(id);
    }

    public ArrayList<ProviderService> listaProviderService() throws ClassNotFoundException, SQLException {
        return new ProviderServiceBD().listaProviderService();
    }

    public boolean deletaProviderServide(int id) throws ClassNotFoundException, SQLException {
        ProviderService psc = this.buscaProviderService(id);
        
        return new ProviderServiceBD().deletaProviderService(psc);
    }

    public boolean atualizaProvider(int id, int provider, String providerService, String accessKey, String secretAccessKey) throws ClassNotFoundException, SQLException {
        ProviderControl pc = new ProviderControl();
        Provider p = pc.buscaProvider(provider);
        
        ProviderService old = this.buscaProviderService(id);
        old.setProvider(p);
        old.setProviderService(providerService);
        old.setAcessKey(accessKey);
        old.setSecretKey(secretAccessKey);
        
        return new ProviderServiceBD().atualizaProvider(old);
    }
}
