/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.InstanceProviderBD;
import br.com.monitoranuvem.model.QtdStatusProvider;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ProviderInstanceControl {
    
    public ArrayList<QtdStatusProvider> listaQDTStatusProvider()throws ClassNotFoundException, SQLException {
        return new InstanceProviderBD().listaQDTStatusProvider();
    }     
}
