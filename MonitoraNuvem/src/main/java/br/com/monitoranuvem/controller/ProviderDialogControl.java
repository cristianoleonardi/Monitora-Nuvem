package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.MNComputeServiceContextFactory;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;

/**
 *
 * @author Cristiano
 */
public class ProviderDialogControl {

    public ComputeService getListaCServ() {
        ComputeServiceContext context = MNComputeServiceContextFactory.createContext();
        ComputeService compute = context.getComputeService();
        return compute;
    }
}
