/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import br.com.monitoranuvem.controller.ProviderDialogControl;
import br.com.monitoranuvem.controller.ProviderInstanceControl;
import java.util.List;
import java.util.Set;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.compute.domain.Volume;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;

/**
 *
 * @author Marcio
 */
public class ThreadOpenStack implements Runnable {

    private Provider pn;
    private int delay;
    private NovaApi novaApi;
    private Set<String> zones;
    private InstanceProvider inst;
    private ProviderInstanceControl pic;
    ProviderDialogControl pdc = new ProviderDialogControl();

    public ThreadOpenStack(Provider prov, int tempoDelay) {
        pn = prov;
        delay = tempoDelay;
    }

    @Override
    public void run() {
        try {
            for (;;) {
                new InstanceProviderBD().atualizaChecked(pn);
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
                                    inst.setTypeinstance(metadata.getHardware().getName().toString());
                                    //Informacoes sobre Sistema Operacional
                                    inst.setSoName(metadata.getOperatingSystem().getName());
                                    if (metadata.getOperatingSystem().is64Bit()) {
                                        inst.setSoType("64Bit");
                                    } else {
                                        inst.setSoType("32Bit");
                                    }
                                    inst.setSoVersion(metadata.getOperatingSystem().getVersion());
                                    inst.setSoFamily(metadata.getOperatingSystem().getFamily().name());
                                    //Informacoes sobre Hardware
                                    if (!(metadata.getHardware() == null)) {
                                        inst.setHwRam(metadata.getHardware().getRam());
                                        //Informacoes sobre Volume
                                        inst.setVolumes((List<Volume>) metadata.getHardware().getVolumes());
                                    } else {
                                        inst.setHwRam(1);
                                        inst.setVolumes(null);
                                    }
                                }
                            }
                            pic.criarAtualizarInstancia(inst);
                        }
                    }
                }
                pic.atualizaIntanciaold(pn);
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            return;
        }

    }

}
