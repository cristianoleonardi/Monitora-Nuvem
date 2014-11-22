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
                                inst.setTypeinstance(metadata.getHardware().getId().toString());
                                inst.setHwRam(metadata.getHardware().getRam());
                                //Informacoes sobre Volume
                                inst.setVolumes((String.valueOf(metadata.getHardware().getVolumes().get(0).getSize())));
                                inst.setCores(String.valueOf(metadata.getHardware().getProcessors().get(0).getCores()));
                                inst.setSpeed(String.valueOf(metadata.getHardware().getProcessors().get(0).getSpeed()));
                            } else {
                                inst.setTypeinstance("t2.micro");
                                inst.setHwRam(0);
                                inst.setCores(null);
                                inst.setSpeed(null);
                                inst.setVolumes(null);
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
