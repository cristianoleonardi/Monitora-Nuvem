package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.InstanceProvider;
import br.com.monitoranuvem.model.MNComputeService;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderBD;
import br.com.monitoranuvem.model.ProviderService;
import br.com.monitoranuvem.model.ThreadAmazon;
import br.com.monitoranuvem.model.ThreadOpenStack;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.openstack.nova.v2_0.NovaApi;

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

    public void monitoraNuvem() throws ClassNotFoundException, SQLException, ParseException {
        //       ArrayList<Provider> list = ;
        ExecutorService executor = Executors.newFixedThreadPool(new ProviderBD().listaProvider().size());
        for (Provider prov : new ProviderBD().listaProvider()) {
            if (prov.getNome().equals("OpenStack")) {
                System.out.println("openstak comentada, quando tiver provedor é so descomentar");
                executor.execute(new ThreadOpenStack(prov));
            } else if (prov.getNome().equals("Amazon")) {
//                executor.execute(new ThreadAmazon(prov));
            }
        }
        executor.shutdown();
    }
}
