package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.MNBlobStore;
import br.com.monitoranuvem.model.MNComputeService;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;

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

    public DashboardControl() throws ClassNotFoundException, SQLException {
        
    }
    
    public ArrayList<MNComputeService> startComputeService() throws ClassNotFoundException, SQLException{
        ArrayList<Provider> listaProvider = pnc.listaProvider();
        ArrayList<MNComputeService> listaComputeService = new ArrayList<>();
        
        for (Provider provedor : listaProvider) {
            pn = provedor;
            ps = psc.buscaProviderService(pn.getId());

            if (ps.getProviderService().equalsIgnoreCase("aws-ec2")) {
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
    
    public Map<String,Integer> instancesByStatus() throws ClassNotFoundException, SQLException {
        ArrayList<Provider> listaProvider = pnc.listaProvider();
        Map<String,Integer> instancias = new HashMap<>();
        
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
    
    public ArrayList<MNBlobStore> startBlobStore(){
        if (ps.getProviderService().equalsIgnoreCase("BlobStore")) {
            
        }
        return null;
    }

}
