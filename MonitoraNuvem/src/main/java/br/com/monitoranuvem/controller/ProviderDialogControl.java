package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.MNBlobStoreContext;
import br.com.monitoranuvem.model.MNComputeServiceContext;
import br.com.monitoranuvem.model.ProviderN;
import br.com.monitoranuvem.model.ProviderBD;
import br.com.monitoranuvem.model.ProviderService;
import br.com.monitoranuvem.model.ProviderServiceBD;
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

    public boolean criarProvider(ProviderN provider) throws ClassNotFoundException, SQLException {
        return new ProviderBD().criarProvider(provider);
    }
    
    public ProviderN buscaProvider(int id) throws ClassNotFoundException, SQLException{
        return new ProviderBD().buscaProvider(id);
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
    
    public boolean criarProviderService(ProviderService ps, ProviderN pn) throws ClassNotFoundException, SQLException{
        return new ProviderServiceBD().criarProviderService(ps, pn);
    }
}
