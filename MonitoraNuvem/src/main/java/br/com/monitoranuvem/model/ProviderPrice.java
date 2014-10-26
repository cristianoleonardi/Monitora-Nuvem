package br.com.monitoranuvem.model;

/**
 *
 * @author Cristiano
 */
public class ProviderPrice {
    
    private int idProviderPrice;
    private Provider provider;
    private String instanceType;
    private Double price;

    public ProviderPrice(int id, Provider provider, String instanceType, Double price) {
        this.idProviderPrice = id;
        this.provider = provider;
        this.instanceType = instanceType;
        this.price = price;
    }
    
    public ProviderPrice(Provider provider, String instanceType, Double price) {
        this.provider = provider;
        this.instanceType = instanceType;
        this.price = price;
    }

    public int getIdProviderPrice() {
        return idProviderPrice;
    }

    public void setIdProviderPrice(int id) {
        this.idProviderPrice = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    
}
