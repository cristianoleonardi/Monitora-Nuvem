package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.MNBlobStoreContext;
import br.com.monitoranuvem.model.MNComputeServiceContext;
import br.com.monitoranuvem.model.ProviderN;
import br.com.monitoranuvem.model.ProviderBD;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public boolean registraProvider(String provider) throws ClassNotFoundException, SQLException {
        ProviderBD rp = new ProviderBD();
        rp.createProvider(provider);
        return true;
    }

    public ArrayList<ProviderN> buscaProvider() throws ClassNotFoundException, SQLException {
        return new ProviderBD().getProvider();
    }

    public boolean deletaProvider(ProviderN np) throws ClassNotFoundException, SQLException {
        return new ProviderBD().deletaProvider(np);
    }

    public boolean atualizaProvider(ProviderN np, String provider) throws ClassNotFoundException, SQLException {
        return new ProviderBD().atualizaProvider(np, provider);
    }
}
