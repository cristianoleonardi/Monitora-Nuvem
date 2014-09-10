/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.monitoranuvem.view;

import br.com.monitoranuvem.connection.ConectionCloud;
import br.com.monitoranuvem.controller.ProviderDialogControl;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ViewProperties;
import java.io.IOException;
import org.jclouds.compute.ComputeService;

/**
 *
 * @author Cristiano
 */
public class Teste {
    public static void main(String[] args) throws IOException {
        ViewProperties prop = new ViewProperties();

        //Efetua a conexao com o provedor
        ConectionCloud con = new ConectionCloud();

        //Instancia de Compute
        ComputeService compute = null;

        if (con.conection(Provider.AMAZON, prop.getAcessKey(), prop.getSecretKey())) {
            ProviderDialogControl pdc = new ProviderDialogControl();
            compute = pdc.getListCServ();
        }
        System.out.println(compute.listNodes().toString());
        
    }
    
}
