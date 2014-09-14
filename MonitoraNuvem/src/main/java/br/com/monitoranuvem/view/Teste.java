/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.view;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;
import java.io.Closeable;
import java.io.IOException;
import java.util.Set;

import org.jclouds.ContextBuilder;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;

/**
 *
 * @author Marcio
 */
public class Teste {

    private ComputeService compute;

    public static void main(String[] args) throws IOException {
//        ViewProperties v = new ViewProperties();
//        ProviderDialogControl pr = new ProviderDialogControl();
//        ComputeService computer;
//        ConectionCloud conn = new ConectionCloud();
////        System.out.println(v.getAcessKey());
////        System.out.println(v.getSecretKey());
//        conn.conection(Provider.AMAZON, v.getAcessKey().trim(), v.getSecretKey());
//        computer = pr.getListaCServ();
//        
//        ArrayList<ComputeMetadata> metadatas = new ArrayList<ComputeMetadata>();
//        for (ComputeMetadata node : computer.listNodes()) {
//            metadatas.add(node);
//            NodeMetadata metadata = computer.getNodeMetadata(node.getId());
//            System.out.println(metadata.getName());
//            System.out.println(metadata.getLocation().getId());
//            System.out.println(metadata.getProviderId()); 
//        }
        new Teste().executa();

    }

    public void executa() {
        String provider = "openstack-nova";
        String identity = "admin";   // login name
        String password = "openstack";   // password
        ComputeServiceContext context = ContextBuilder.newBuilder(provider)
                .endpoint("http://192.168.221.130:5000/v2.0/")
                .credentials(identity, password)
                .buildView(ComputeServiceContext.class);
        compute = context.getComputeService();
        listServers();
    }

    private void listServers() {
//        for (ComputeMetadata node : compute.listNodes()) {
//            NodeMetadata metadata = compute.getNodeMetadata(node.getId());
//            System.out.println(metadata.getName());
//            System.out.println(metadata.getLocation().getId());
//            System.out.println(metadata.getProviderId());
    }
}
