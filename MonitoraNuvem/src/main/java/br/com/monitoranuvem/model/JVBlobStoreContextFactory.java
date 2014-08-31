/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import br.com.monitoranuvem.connection.Connection;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.blobstore.BlobStoreContextFactory;

/**
 *
 * @author Marcio
 */
public class JVBlobStoreContextFactory {

    public static BlobStoreContext createContext() {
        Connection con = Configuration.getInstance().getConnection();
        Provider provider = Configuration.getInstance().getProvider();

        BlobStoreContext blobContext = new BlobStoreContextFactory().createContext(
                provider.getProviderBService(), con.getLogin(),
                con.getPassword());
        return blobContext;
    }

}
