package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.connection.ConnectionMySql;
import br.com.monitoranuvem.model.Configuration;
import br.com.monitoranuvem.model.MNBlobStoreContext;
import br.com.monitoranuvem.model.MNComputeServiceContext;
import br.com.monitoranuvem.model.RegisterProviderBD;
import java.sql.Connection;
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

    public ComputeService getListCServ() {
        ComputeServiceContext context = MNComputeServiceContext.createContext();
        ComputeService compute = context.getComputeService();
        return compute;
    }

    public BlobStore getsListBServ() {
        BlobStoreContext context = MNBlobStoreContext.createContext();
        BlobStore blobstore = context.getBlobStore();
        return blobstore;
    }

    public boolean RegisterProvider(String provider) throws ClassNotFoundException, SQLException {
        RegisterProviderBD rp = new RegisterProviderBD();
        rp.createProvider(provider);
        return true;
    }
}
