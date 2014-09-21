package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.MNBlobStoreContext;
import br.com.monitoranuvem.model.MNComputeServiceContext;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderService;
import br.com.monitoranuvem.model.ProviderServiceBD;
import java.sql.SQLException;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
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
