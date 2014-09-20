/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.ProviderN;
import br.com.monitoranuvem.model.ProviderService;
import br.com.monitoranuvem.model.ProviderServiceBD;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ProviderServiceControl {

    public boolean criarProviderService(ProviderService ps, ProviderN pn) throws ClassNotFoundException, SQLException {
        return new ProviderServiceBD().criarProviderService(ps, pn);
    }

    public ProviderService buscaProviderService(int id) throws ClassNotFoundException, SQLException {
        return new ProviderServiceBD().buscaProviderService(id);
    }
    public ArrayList<ProviderService> listaProviderService() throws ClassNotFoundException, SQLException{
        return new ProviderServiceBD().listaProviderService();
    }
}
