/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.Cost;
import br.com.monitoranuvem.model.CostProvider;
import br.com.monitoranuvem.model.HistoryCostBD;
import br.com.monitoranuvem.model.HistoryCostProvider;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ProviderCostControl {
    
    public ArrayList<Cost> custoProvider() throws ClassNotFoundException, SQLException, ParseException{
        return new CostProvider().custoProvider();
    }   
    
    public ArrayList<HistoryCostProvider> listHistoryCostProvider() throws ClassNotFoundException, SQLException, ParseException{
        return new HistoryCostBD().listHistoryCostProvider();
    }
    public ArrayList<HistoryCostProvider> listHistoryCostProvider(int idProvider) throws ClassNotFoundException, SQLException, ParseException{
        return new HistoryCostBD().listHistoryCostProvider(idProvider);
    }
}
