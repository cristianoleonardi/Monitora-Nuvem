package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.connection.Connection;
import br.com.monitoranuvem.model.Configuration;
import br.com.monitoranuvem.model.JVComputeServiceContextFactory;
import br.com.monitoranuvem.model.Provider;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.domain.ComputeMetadata;

/**
 *
 * @author Cristiano
 */
public class ProviderDialogControl {

    public ProviderDialogControl() {
        //      dialog = new ProviderDialog();
    }

    public boolean connecta(Provider p, String login, String password) {
        Connection conn = new Connection();
        conn.setLogin(login);
        conn.setPassword(password);
        Configuration.getInstance().setProvider(p);
        Configuration.getInstance().setConnection(conn);
//        listaBlob();
        listaCServ();
        return true;
    }

//    public void listaBlob() {
//        BlobStoreContext context = JVBlobStoreContextFactory.createContext();
//        BlobStore blobStore = context.getBlobStore();
//        DefaultListModel list = new DefaultListModel();
//        
//        for (StorageMetadata storage : blobStore.list()) {
//            System.out.println(storage.getName());
//            list.addElement(storage);
//        }
//    }
    
    public void listaCServ(){
        ComputeServiceContext context = JVComputeServiceContextFactory.createContext();
        ComputeService compute = context.getComputeService();
        for (ComputeMetadata node : compute.listNodes()) {
            System.out.println(node.getName());
        }
    }
}
