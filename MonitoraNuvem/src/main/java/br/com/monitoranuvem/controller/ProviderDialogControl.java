package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.connection.Connection;
import br.com.monitoranuvem.model.Configuration;
import br.com.monitoranuvem.model.JVBlobStoreContextFactory;
import br.com.monitoranuvem.model.Provider;
import javax.swing.DefaultListModel;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.blobstore.domain.StorageMetadata;

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
        lista();
        return true;
    }

    public void lista() {
        BlobStoreContext context = JVBlobStoreContextFactory.createContext();
        BlobStore blobStore = context.getBlobStore();
        DefaultListModel list = new DefaultListModel();
        
        for (StorageMetadata storage : blobStore.list()) {
            System.out.println(storage.getName());
            list.addElement(storage);
        }
    }

}
