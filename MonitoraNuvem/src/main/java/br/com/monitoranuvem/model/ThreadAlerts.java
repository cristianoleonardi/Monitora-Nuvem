/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import br.com.monitoranuvem.controller.ProviderAlerts;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ThreadAlerts implements Runnable{
    private int delay;

    public ThreadAlerts(int tempoDelay) {
        delay = tempoDelay;
    }

    @Override
    public void run() {
        try {
            for (;;) {
              ArrayList<Alerts> alert = new ProviderAlerts().listaAlerts();
                for (Alerts a : alert){
                    
                    System.out.println(a.getIdAlerts() + "   "+ a.getNameAlerts());
                    
                }
//                for (ProviderService ps : new ProviderServiceBD().buscaProviderServiceProvider(pn.getId())) {
//                    novaApi = pdc.getListServiceOStack(ps);
//                    zones = novaApi.getConfiguredZones();
//                    ComputeService compute = pdc.getContextCSStack(ps);
//                    for (String zone : zones) {
//                        ServerApi serverApi = novaApi.getServerApiForZone(zone);
//                        for (Server server : serverApi.listInDetail().concat()) {
//                            inst = new InstanceProvider();
//                            pic = new ProviderInstanceControl();
//                            inst.setIdInstance(server.getId());
//                            inst.setInstanceProvider(server.getName());
//                            inst.setProvider(new ProviderBD().buscaProvider(pn.getId()));
//                            inst.setDataCreate(server.getCreated());
//                            inst.setDataUpdate(server.getUpdated());
//                            for (ComputeMetadata node : compute.listNodes()) {
//                                if (node.getProviderId().equals(server.getId())) {
//                                    NodeMetadata metadata = compute.getNodeMetadata(node.getId());
//                                    inst.setStatus(metadata.getStatus().name());
//                                }
//                            }
//                            pic.criarAtualizarInstancia(inst);
//                        }
//                    }
//                }
//                pic.atualizaIntanciaold(pn);
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            return;
        }
    }
}
