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
public class InstanceProvider {
    
    private int idInstance;
    private String instanceProvider;
    private Provider provider;
    private String status;

    public int getIdInstance() {
        return idInstance;
    }

    public void setIdInstance(int idInstance) {
        this.idInstance = idInstance;
    }

    public String getInstanceProvider() {
        return instanceProvider;
    }

    public void setInstanceProvider(String instanceProvider) {
        this.instanceProvider = instanceProvider;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
