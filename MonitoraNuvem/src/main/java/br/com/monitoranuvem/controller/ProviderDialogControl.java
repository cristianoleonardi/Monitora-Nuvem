package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.Configuration;
import br.com.monitoranuvem.model.Credentials;
import br.com.monitoranuvem.model.MNComputeServiceContextFactory;
import br.com.monitoranuvem.model.Provider;
import java.util.ArrayList;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.domain.Location;
import org.jclouds.domain.LocationScope;





/**
 *
 * @author Cristiano
 */
public class ProviderDialogControl {

    public ProviderDialogControl() {

    }

    public boolean connecta(Provider p, String login, String password) {
        Credentials cred = new Credentials();
        cred.setAcessKey(login);
        cred.setSecretKey(password);
        Configuration.getInstance().setProvider(p);
        Configuration.getInstance().setCredentials(cred);
        listaCServ();
        return true;
    }
//
////    public void listaBlob() {
////        BlobStoreContext context = JVBlobStoreContextFactory.createContext();
////        BlobStore blobStore = context.getBlobStore();
////        DefaultListModel list = new DefaultListModel();
////        
////        for (StorageMetadata storage : blobStore.list()) {
////            System.out.println(storage.getName());
////            list.addElement(storage);
////        }
////    }

    public void listaCServ() {
        ComputeServiceContext context = MNComputeServiceContextFactory.createContext();
        ComputeService compute = context.getComputeService();
        ArrayList<ComputeMetadata> metadatas = new ArrayList<ComputeMetadata>();

        for (ComputeMetadata node : compute.listNodes()) {
            metadatas.add(node);
            NodeMetadata metadata = compute.getNodeMetadata(node.getId());
            System.out.println(metadata.getName());
            System.out.println(metadata.getLocation().getId());
            System.out.println(metadata.getProviderId());

//            CloudWatch cw = new CloudWatch();
//            Dimension instanceIdDimension = new Dimension(EC2Constants.Dimension.INSTANCE_ID, node.getId());
//            ListMetricsOptions lmOptions = ListMetricsOptions.builder()
//                    .metricName(EC2Constants.MetricName.CPU_UTILIZATION)
//                    .namespace(Namespaces.EC2)
//                    .dimension(instanceIdDimension)
//                    .build();
//
//            String region = getRegion(node.getLocation());
//            Credentials cred = Configuration.getInstance().getCredentials();
//            RestContext<CloudWatchClient, CloudWatchAsyncClient> cloudWatchContext = null;
//            cloudWatchContext = ContextBuilder.newBuilder(new AWSCloudWatchProviderMetadata())
//                    .credentials(cred.getAcessKey(), cred.getSecretKey())
//                    .build();
//            MetricClient metricClient= cloudWatchContext.getApi().getMetricClientForRegion(region);
//            
//            System.out.println(metricClient.listMetrics(lmOptions));
            
        }
    }

    private static String getRegion(Location location) {
        // Just to be safe
        if (location == null) {
            return null;
        }

        String region = null;
        while (region == null && location.getParent() != null) {
            if (location.getScope() == LocationScope.REGION) {
                region = location.getId();
            } else {
                location = location.getParent();
            }
        }
        return region;
    }
}
