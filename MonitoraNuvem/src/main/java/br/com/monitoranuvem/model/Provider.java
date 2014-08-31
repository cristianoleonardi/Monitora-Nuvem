package br.com.monitoranuvem.model;

/**
 *
 * @author Marcio
 */
public enum Provider {

    AMAZON("Amazon", "aws-ec2", "aws-s3"), GOOGLE("Google", "", "googlestorage"),
    GOGRID("GoGrid", "gogrid", "");
    
    private String providerName;
    private String providerCService;
    private String providerBService;

    private Provider(String providerName, String providerCService, String providerBService) {
        this.providerName = providerName;
        this.providerCService = providerCService;
        this.providerBService = providerBService;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderCService() {
        return providerCService;
    }

    public void setProviderCService(String providerCService) {
        this.providerCService = providerCService;
    }

    public String getProviderBService() {
        return providerBService;
    }

    public void setProviderBService(String providerBService) {
        this.providerBService = providerBService;
    }

}
