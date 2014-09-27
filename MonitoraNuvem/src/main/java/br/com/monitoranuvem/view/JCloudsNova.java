package br.com.monitoranuvem.view;

import com.google.common.io.Closeables;
import org.jclouds.ContextBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.util.Set;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;

public class JCloudsNova implements Closeable {

    private final NovaApi novaApi;
    private final Set<String> zones;
    public static void main(String[] args) throws IOException {
        JCloudsNova jcloudsNova = new JCloudsNova();

        try {
            jcloudsNova.listServers();
            jcloudsNova.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jcloudsNova.close();
        }
    }

    public JCloudsNova() {
//        Iterable<Module> modules = ImmutableSet.<Module>of(new SLF4JLoggingModule());
//        System.out.println("passei");
        String provider = "openstack-nova";
        String identity = "admin:admin"; // tenantName:userName
        String credential = "openstack";
//        ComputeServiceContext context = ContextBuilder.newBuilder(provider)
//                .endpoint("http://192.168.221.130:5000/v2.0/")
//                .credentials(identity, credential)
//                .buildView(ComputeServiceContext.class);
//
//        ComputeService compute = context.getComputeService();
//        for (ComputeMetadata node : compute.listNodes()) {
//            NodeMetadata metadata = compute.getNodeMetadata(node.getId());
////            System.out.println(node.getProviderId());
////            System.out.println(metadata.getId()); 
////            System.out.println(metadata.getName());
//            System.out.println(metadata.getHardware().getHypervisor());
////            System.out.println(metadata.getHardware().toString());
////            System.out.println(metadata.getStatus().name());
//        }
//    }
        novaApi = ContextBuilder.newBuilder(provider)
                .endpoint("http://192.168.221.130:5000/v2.0/")
                .credentials(identity, credential)
                .buildApi(NovaApi.class);
        zones = novaApi.getConfiguredZones();
    }

    private void listServers() {
        
        for (String zone : zones) {
            ServerApi serverApi = novaApi.getServerApiForZone(zone);
            System.out.println(serverApi.getMetadata(zone));

            System.out.println("Servers in " + zone);

            for (Server server : serverApi.listInDetail().concat()) {
                System.out.println("  " + server);
                System.out.println(server.getId());
                
            }
        }
    }

    public void close() throws IOException {
        Closeables.close(novaApi, true);
    }
}
