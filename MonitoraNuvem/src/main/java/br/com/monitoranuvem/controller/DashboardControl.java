package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.InstanceProvider;
import br.com.monitoranuvem.model.MNComputeService;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderBD;
import br.com.monitoranuvem.model.ProviderService;
import br.com.monitoranuvem.model.ProviderServiceBD;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;

/**
 *
 * @author Cristiano Leonardi, Márcio Bolzan
 */
public class DashboardControl {

    //Instancias necessárias para ComputeService
    Provider pn = new Provider();
    ProviderService ps = new ProviderService();
    ProviderControl pnc = new ProviderControl();
    ProviderServiceControl psc = new ProviderServiceControl();
    ProviderDialogControl pdc = new ProviderDialogControl();
    private NovaApi novaApi;
    private Set<String> zones;
    private InstanceProvider inst;
    private ProviderInstanceControl pic;

    public DashboardControl() throws ClassNotFoundException, SQLException {

    }

    public ArrayList<MNComputeService> startComputeService() throws ClassNotFoundException, SQLException {
        ArrayList<Provider> listaProvider = pnc.listaProvider();
        ArrayList<MNComputeService> listaComputeService = new ArrayList<>();

        for (Provider provedor : listaProvider) {
            pn = provedor;
            ps = psc.buscaProviderService(pn.getId());

            if (ps != null && ps.getProviderService().equalsIgnoreCase("aws-ec2")) {
                //Iniciar Monitorações de Compute Service
                ComputeService compute = pdc.getListCServ(ps);

                for (ComputeMetadata node : compute.listNodes()) {
                    NodeMetadata metadata = compute.getNodeMetadata(node.getId());

                    MNComputeService mncs = new MNComputeService();
                    mncs.setId(metadata.getId());
                    mncs.setStatus(metadata.getStatus().name());
                    mncs.setProvedor(ps.getProvider().getNome());

                    //Monta Array para enviar para a View
                    listaComputeService.add(mncs);
                }
            }
        }
        return listaComputeService;
    }

    public Map<String, Integer> instancesByStatus() throws ClassNotFoundException, SQLException {
        ArrayList<Provider> listaProvider = pnc.listaProvider();
        Map<String, Integer> instancias = new HashMap<>();

        for (Provider provedor : listaProvider) {
            pn = provedor;
            ps = psc.buscaProviderService(pn.getId());

            if (ps.getProviderService().equalsIgnoreCase("aws-ec2")) {
                //Iniciar Monitorações de Compute Service
                ComputeService compute = pdc.getListCServ(ps);

                for (ComputeMetadata node : compute.listNodes()) {
                    NodeMetadata metadata = compute.getNodeMetadata(node.getId());

                    if (metadata.getStatus().name().equalsIgnoreCase("RUNNING")) {

                    } else if (metadata.getStatus().name().equalsIgnoreCase("ERROR")) {

                    } else if (metadata.getStatus().name().equalsIgnoreCase("PENDING")) {

                    } else if (metadata.getStatus().name().equalsIgnoreCase("SUSPENDED")) {

                    } else if (metadata.getStatus().name().equalsIgnoreCase("TERMINATED")) {

                    } else if (metadata.getStatus().name().equalsIgnoreCase("UNRECOGNIZED")) {

                    }

                }
            }
        }
        return instancias;
    }

    public boolean monitoraNuvem() throws ClassNotFoundException, SQLException {
        ps = new ProviderServiceBD().buscaProviderService(3);
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
                inst.setProvider(new ProviderBD().buscaProvider(3));
                inst.setDataCreate(server.getCreated());
                inst.setDataUpdate(server.getUpdated());                
                for (ComputeMetadata node : compute.listNodes()) {
                    if (node.getProviderId().equals(server.getId())) {
                        NodeMetadata metadata = compute.getNodeMetadata(node.getId());
                        inst.setStatus(metadata.getStatus().name());                     
                    }
                }
                pic.criarInstancia(inst);
            }
        }
        return true;
    }
}
