/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStoreContext;

/**
 *
 * @author Marcio
 */
public class MNBlobStoreContext {

    public static BlobStoreContext createContext() {
        Credentials cred = Configuration.getInstance().getCredentials();
        Provider provider = Configuration.getInstance().getProvider();
        
        BlobStoreContext context = ContextBuilder.newBuilder(provider.getProviderBService())
                .credentials(cred.getAcessKey(), cred.getSecretKey())
                .buildView(BlobStoreContext.class);
        return context;
    }

}
