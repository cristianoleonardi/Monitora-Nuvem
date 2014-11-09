/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.Custo;
import br.com.monitoranuvem.model.CustoProvider;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ProviderCustoControl {
    
    public ArrayList<Custo> custoProvider() throws ClassNotFoundException, SQLException, ParseException{
        return new CustoProvider().custoProvider();
    }    
}
