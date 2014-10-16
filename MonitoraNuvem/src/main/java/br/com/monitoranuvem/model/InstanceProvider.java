/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import java.util.Date;

/**
 *
 * @author Marcio
 */
public class InstanceProvider {
    
    private int idInstanceProvider;
    private String instanceProvider;
    private Provider provider;
    private String status;
    private String idInstance;
    private Date dataCreate;
    private Date dataUpdate;
    private int isChecked;
    private Date dateChecked;

    public int getIdInstanceProvider() {
        return idInstanceProvider;
    }

    public void setIdInstanceProvider(int idInstanceProvider) {
        this.idInstanceProvider = idInstanceProvider;
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

    public String getIdInstance() {
        return idInstance;
    }

    public void setIdInstance(String idInstance) {
        this.idInstance = idInstance;
    }

    public Date getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(Date dataCreate) {
        this.dataCreate = dataCreate;
    }

    public Date getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(Date dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public Date getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(Date dateChecked) {
        this.dateChecked = dateChecked;
    }
}
