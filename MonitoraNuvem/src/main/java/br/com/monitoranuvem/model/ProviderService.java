/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

/**
 *
 * @author Marcio
 */
public class ProviderService {
    private int idProviderService;
    private String providerService;
    private String acessKey;
    private String secretKey;
    private ProviderN provider;

    public int getIdProviderService() {
        return idProviderService;
    }

    public void setIdProviderService(int idProviderService) {
        this.idProviderService = idProviderService;
    }

    public String getProviderService() {
        return providerService;
    }

    public void setProviderService(String providerService) {
        this.providerService = providerService;
    }

    public String getAcessKey() {
        return acessKey;
    }

    public void setAcessKey(String acessKey) {
        this.acessKey = acessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public ProviderN getProvider() {
        return provider;
    }

    public void setProvider(ProviderN provider) {
        this.provider = provider;
    }
   
}
