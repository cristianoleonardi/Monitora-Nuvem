/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.view;

import br.com.monitoranuvem.connection.ConectionCloud;
import br.com.monitoranuvem.controller.ProviderDialogControl;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ViewProperties;
import java.io.IOException;
import java.util.ArrayList;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;

/**
 *
 * @author Marcio
 */
public class Teste {

    public static void main(String[] args) throws IOException {
        ViewProperties v = new ViewProperties();
        ProviderDialogControl pr = new ProviderDialogControl();
        ComputeService computer;
        ConectionCloud conn = new ConectionCloud();
//        System.out.println(v.getAcessKey());
//        System.out.println(v.getSecretKey());
        conn.conection(Provider.AMAZON, v.getAcessKey().trim(), "HzRJ4lAn8ImL8zvEziWPxS8MbRrJEtdJdhjM2pXy");
//        conn.conection(Provider.AMAZON, "AKIAIBWQEFUF5AQBOZGQ", "HzRJ4lAn8ImL8zvEziWPxS8MbRrJEtdJdhjM2pXy");
        computer = pr.getListaCServ();
        
        ArrayList<ComputeMetadata> metadatas = new ArrayList<ComputeMetadata>();
        for (ComputeMetadata node : computer.listNodes()) {
            metadatas.add(node);
            NodeMetadata metadata = computer.getNodeMetadata(node.getId());
            System.out.println(metadata.getName());
            System.out.println(metadata.getLocation().getId());
            System.out.println(metadata.getProviderId()); 
        }
    }

}
