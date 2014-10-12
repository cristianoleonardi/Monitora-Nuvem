/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.InstanceProvider;
import br.com.monitoranuvem.model.InstanceProviderBD;
import br.com.monitoranuvem.model.QtdStatusProvider;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ProviderInstanceControl {

    public boolean criarAtualizarInstancia(InstanceProvider inst) throws ClassNotFoundException, SQLException, ParseException {
        int num = new InstanceProviderBD().existeInstancia(inst);
        if (num > 0) {
            new InstanceProviderBD().atualizaIntancia(inst);
            new InstanceProviderBD().criarHistorico(inst);
        } else {
            new InstanceProviderBD().criarInstancia(inst);
            new InstanceProviderBD().criarHistorico(inst);
        }
        return true;
    }
    
    public InstanceProvider buscaInstanceProvider(String idInstance) throws ClassNotFoundException, SQLException, ParseException {
        return new InstanceProviderBD().buscaInstanceProvider(idInstance);
    }

    public ArrayList<QtdStatusProvider> listaQDTStatusProvider() throws ClassNotFoundException, SQLException {
        return new InstanceProviderBD().listaQDTStatusProvider();
    }

    public ArrayList<QtdStatusProvider> listaQDTStatusProvider(String status) throws ClassNotFoundException, SQLException {
        return new InstanceProviderBD().listaQDTStatusProvider(status);
    }

    public ArrayList<InstanceProvider> listaStatusProvider() throws ClassNotFoundException, SQLException {
        return new InstanceProviderBD().listaStatusProvider();
    }

    public ArrayList<InstanceProvider> listaStatusProvider(String status) throws ClassNotFoundException, SQLException {
        return new InstanceProviderBD().listaStatusProvider(status);
    }
}
