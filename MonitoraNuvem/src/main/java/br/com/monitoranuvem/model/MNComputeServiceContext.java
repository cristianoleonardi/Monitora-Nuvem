/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import org.jclouds.ContextBuilder;
import org.jclouds.compute.ComputeServiceContext;

/**
 *
 * @author Marcio
 */
public class MNComputeServiceContext {

    public static ComputeServiceContext createContext(ProviderService ps) {
        ComputeServiceContext context = ContextBuilder.newBuilder(ps.getProviderService())
                .credentials(ps.getAcessKey().trim(), ps.getSecretKey().trim())
                .buildView(ComputeServiceContext.class);
        return context;
    }
}
