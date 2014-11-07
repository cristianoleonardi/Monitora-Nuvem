/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import br.com.monitoranuvem.controller.ProviderDialogControl;
import br.com.monitoranuvem.controller.ProviderInstanceControl;
import java.util.Date;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;

/**
 *
 * @author Marcio
 */
public class ThreadAmazon implements Runnable {

    private Provider pn;
    private int delay;
    private InstanceProvider inst;
    private ProviderInstanceControl pic;
    ProviderDialogControl pdc = new ProviderDialogControl();

    public ThreadAmazon(Provider prov, int tempoDelay) {
        pn = prov;
        delay = tempoDelay;
    }

    @Override
    public void run() {
        try {
            for (;;) {
                new InstanceProviderBD().atualizaChecked(pn);
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
                            if (!(metadata.getHardware() == null)) {
                                inst.setTypeinstance(metadata.getHardware().getId().toString());
                            }else{
                                inst.setTypeinstance("t2.micro");
                            }
                            inst.setDataCreate(new Date());
                            inst.setDataUpdate(new Date());
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
