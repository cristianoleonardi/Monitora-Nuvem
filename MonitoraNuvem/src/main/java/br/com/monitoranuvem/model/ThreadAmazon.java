/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import br.com.monitoranuvem.controller.ProviderDialogControl;
import br.com.monitoranuvem.controller.ProviderInstanceControl;
import java.sql.SQLException;
import java.text.ParseException;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;

/**
 *
 * @author Marcio
 */
public class ThreadAmazon implements Runnable {

    Provider pn;
    ProviderDialogControl pdc = new ProviderDialogControl();
    private InstanceProvider inst;
    private ProviderInstanceControl pic;

    public ThreadAmazon(Provider pv) {
        pn = pv;
    }

    public void run() {
        while (true) {
            try {
                for (ProviderService ps : new ProviderServiceBD().buscaProviderServiceProvider(pn.getId())) {
                    if (ps.getProviderService().equals("aws-ec2")) {
                        ComputeService compute = pdc.getListCServ(ps);
                        for (ComputeMetadata node : compute.listNodes()) {
                            inst = new InstanceProvider();
                            pic = new ProviderInstanceControl();
                            NodeMetadata metadata = compute.getNodeMetadata(node.getId());
                            inst.setIdInstance(metadata.getId());
                            inst.setInstanceProvider(metadata.getName());
                            inst.setProvider(new ProviderBD().buscaProvider(pn.getId()));
                            inst.setStatus(metadata.getStatus().name());
//                            inst.setDataCreate(server.getCreated());
//                            inst.setDataUpdate(server.getUpdated());
                            pic.criarAtualizarInstancia(inst);
                        }
                    }
                }
                pic.atualizaIntanciaold(pn);
                Thread.sleep(60000);
            } catch (InterruptedException | ClassNotFoundException | SQLException | ParseException ex) {
                System.out.println(ex);
            }
        }
    }
}
