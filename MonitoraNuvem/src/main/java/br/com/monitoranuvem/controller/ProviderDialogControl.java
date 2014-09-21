package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.MNComputeServiceContext;
import br.com.monitoranuvem.model.ProviderService;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;

/**
 *
 * @author Cristiano
 */
public class ProviderDialogControl {

    public ComputeService getListCServ(ProviderService ps) {
        ComputeServiceContext context = MNComputeServiceContext.createContext(ps);
        ComputeService compute = context.getComputeService();
        return compute;
    }

//    public BlobStore getsListBServ() {
//        BlobStoreContext context = MNBlobStoreContext.createContext();
//        BlobStore blobstore = context.getBlobStore();
//        return blobstore;
//    }
}
