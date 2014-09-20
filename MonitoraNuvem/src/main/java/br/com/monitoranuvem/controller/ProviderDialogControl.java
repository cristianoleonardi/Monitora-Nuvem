package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.MNBlobStoreContext;
import br.com.monitoranuvem.model.MNComputeServiceContext;
import br.com.monitoranuvem.model.ProviderN;
import br.com.monitoranuvem.model.ProviderBD;
import br.com.monitoranuvem.model.ProviderService;
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

    public boolean criarProvider(String provider) throws ClassNotFoundException, SQLException {
        return new ProviderBD().criarProvider(provider);
    }

    public ArrayList<ProviderN> listaProvider() throws ClassNotFoundException, SQLException {
        return new ProviderBD().listaProvider();
    }

    public boolean deletaProvider(ProviderN pn) throws ClassNotFoundException, SQLException {
        return new ProviderBD().deletaProvider(pn);
    }

    public boolean atualizaProvider(ProviderN pn, String provider) throws ClassNotFoundException, SQLException {
        return new ProviderBD().atualizaProvider(pn, provider);
    }
    
    public boolean registraProviderService(String providerService, String acessKey, String secretKey,ProviderN pn) throws ClassNotFoundException, SQLException{
        return new ProviderService().createProviderService(providerService, acessKey, secretKey, pn);
    }
}
