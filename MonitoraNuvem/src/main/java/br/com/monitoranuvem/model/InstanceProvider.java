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
    private String typeinstance;
    private String soName;
    private String soType;
    private String soVersion;
    private String soFamily;
    private int hwRam;
    private String Volumes;
    private String Cores;
    private String speed;
            

    public String getVolumes() {
        return Volumes;
    }

    public void setVolumes(String Volumes) {
        this.Volumes = Volumes;
    }

    public int getHwRam() {
        return hwRam;
    }

    public void setHwRam(int hwRam) {
        this.hwRam = hwRam;
    }

    public String getSoFamily() {
        return soFamily;
    }

    public void setSoFamily(String soFamily) {
        this.soFamily = soFamily;
    }

    public String getSoVersion() {
        return soVersion;
    }

    public void setSoVersion(String soVersion) {
        this.soVersion = soVersion;
    }

    public String getSoType() {
        return soType;
    }

    public void setSoType(String soType) {
        this.soType = soType;
    }

    public String getSoName() {
        return soName;
    }

    public void setSoName(String soName) {
        this.soName = soName;
    }

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

    public String getTypeinstance() {
        return typeinstance;
    }

    public void setTypeinstance(String typeinstance) {
        this.typeinstance = typeinstance;
    } 

    public String getCores() {
        return Cores;
    }

    public void setCores(String Cores) {
        this.Cores = Cores;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }   
}
