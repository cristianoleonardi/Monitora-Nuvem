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
public class StatusProvider {
    private int idStatusProvider;
    private String statusProvider;
    private InstanceProvider instanceProvider;

    public int getIdStatusProvider() {
        return idStatusProvider;
    }

    public void setIdStatusProvider(int idStatusProvider) {
        this.idStatusProvider = idStatusProvider;
    }

    
    public String getStatusProvider() {
        return statusProvider;
    }

    public void setStatusProvider(String statusProvider) {
        this.statusProvider = statusProvider;
    }

    public InstanceProvider getInstanceProvider() {
        return instanceProvider;
    }

    public void setInstanceProvider(InstanceProvider instanceProvider) {
        this.instanceProvider = instanceProvider;
    }
    
}
