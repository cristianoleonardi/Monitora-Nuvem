/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import br.com.monitoranuvem.controller.ProviderControl;
import br.com.monitoranuvem.controller.ProviderDialogControl;
import br.com.monitoranuvem.controller.ProviderInstanceControl;
import br.com.monitoranuvem.controller.ProviderServiceControl;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;

/**
 *
 * @author Marcio
 */
public class ThreadOpenStack implements Runnable {

    Provider pn;
    ProviderControl pnc = new ProviderControl();
    ProviderServiceControl psc = new ProviderServiceControl();
    ProviderDialogControl pdc = new ProviderDialogControl();
    private NovaApi novaApi;
    private Set<String> zones;
    private InstanceProvider inst;
    private ProviderInstanceControl pic;

    public ThreadOpenStack(Provider pv) {
        pn = pv;
    }

    public void run() {
        while (true) {
            try {
                for (ProviderService ps : new ProviderServiceBD().buscaProviderServiceProvider(pn.getId())) {
                    novaApi = pdc.getListServiceOStack(ps);
                    zones = novaApi.getConfiguredZones();
                    ComputeService compute = pdc.getContextCSStack(ps);
                    for (String zone : zones) {
                        ServerApi serverApi = novaApi.getServerApiForZone(zone);
                        for (Server server : serverApi.listInDetail().concat()) {
                            inst = new InstanceProvider();
                            pic = new ProviderInstanceControl();
                            inst.setIdInstance(server.getId());
                            inst.setInstanceProvider(server.getName());
                            inst.setProvider(new ProviderBD().buscaProvider(pn.getId()));
                            inst.setDataCreate(server.getCreated());
                            inst.setDataUpdate(server.getUpdated());
                            for (ComputeMetadata node : compute.listNodes()) {
                                if (node.getProviderId().equals(server.getId())) {
                                    NodeMetadata metadata = compute.getNodeMetadata(node.getId());
                                    inst.setStatus(metadata.getStatus().name());
                                }
                            }
                            pic.criarAtualizarInstancia(inst);
                        }
                    }
                }
                Thread.sleep(60000);
            } catch (ClassNotFoundException | SQLException | InterruptedException | ParseException ex) {
                System.out.println(ex);     
            }
        }
    }
}
