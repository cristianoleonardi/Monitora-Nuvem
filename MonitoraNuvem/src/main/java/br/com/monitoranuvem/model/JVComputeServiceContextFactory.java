/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import br.com.monitoranuvem.connection.Credentials;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.ComputeServiceContextFactory;

/**
 *
 * @author Marcio
 */
public class JVComputeServiceContextFactory {

    public static ComputeServiceContext createContext(){
        Credentials con = Configuration.getInstance().getConnection();
        Provider provider = Configuration.getInstance().getProvider();

        ComputeServiceContext computeServiceContext = new ComputeServiceContextFactory().createContext(
                provider.getProviderCService(), con.getAcessKey(),
                con.getSecretKey());
        return computeServiceContext;
    }
}
