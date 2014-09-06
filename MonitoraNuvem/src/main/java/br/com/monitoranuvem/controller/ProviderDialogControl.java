package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.Credentials;
import br.com.monitoranuvem.model.Configuration;
import br.com.monitoranuvem.model.MNComputeServiceContextFactory;
import br.com.monitoranuvem.model.Provider;
import java.util.ArrayList;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;

/**
 *
 * @author Cristiano
 */
public class ProviderDialogControl {

    public ProviderDialogControl() {

    }

    public boolean connecta(Provider p, String login, String password) {
        Credentials cred = new Credentials();
        cred.setAcessKey(login);
        cred.setSecretKey(password);
        Configuration.getInstance().setProvider(p);
        Configuration.getInstance().setCredentials(cred);
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
    public void listaCServ() {
        
        
        ComputeServiceContext context = MNComputeServiceContextFactory.createContext();
        ComputeService compute = context.getComputeService();

        ArrayList<ComputeMetadata> metadatas = new ArrayList<ComputeMetadata>();

        for (ComputeMetadata node : compute.listNodes()) {
            metadatas.add(node);
            NodeMetadata metadata = compute.getNodeMetadata(node.getId());
            System.out.println("ID = " + metadata.getId());
            System.out.println("ID Provedor = " + metadata.getProviderId());
            System.out.println("Nome = "+metadata.getName());
            System.out.println("Localização = " + metadata.getLocation().getDescription());
            System.out.println("Familia = "+metadata.getOperatingSystem().getFamily().name());
            System.out.println("Estatus = "+metadata.getState().name());
            System.out.println("Host/IP = "+metadata.getHostname());
            System.out.println("Sistema Operando = " + metadata.getOperatingSystem().getDescription());
        }
    }
}
