/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.QtdStatusProvider;
import br.com.monitoranuvem.model.StatusProviderBD;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class StatusProviderControl {
    
    public ArrayList<QtdStatusProvider> listaQDTStatusProvider()throws ClassNotFoundException, SQLException {
        return new StatusProviderBD().listaQDTStatusProvider();
    }     
}
