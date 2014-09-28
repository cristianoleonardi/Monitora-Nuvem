/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import org.jclouds.ContextBuilder;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.openstack.nova.v2_0.NovaApi;

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

    public static NovaApi createContexStack(ProviderService ps) {
        NovaApi novaApi = ContextBuilder.newBuilder(ps.getProviderService())
                .endpoint(ps.getEndPoint())
                .credentials(ps.getAcessKey().trim(), ps.getSecretKey().trim())
                .buildApi(NovaApi.class);
        return novaApi;
    }
    public static ComputeServiceContext createContextCSStack(ProviderService ps) {
        ComputeServiceContext context = ContextBuilder.newBuilder(ps.getProviderService())
                .endpoint(ps.getEndPoint())
                .credentials(ps.getAcessKey().trim(), ps.getSecretKey().trim())
                .buildView(ComputeServiceContext.class);
        return context;
    }
}
