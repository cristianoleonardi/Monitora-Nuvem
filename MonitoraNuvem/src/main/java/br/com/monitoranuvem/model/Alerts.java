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
public class Alerts {
    
    private int idAlerts;
    private String nameAlerts;
    private Provider prov;
    private String statusProvider;
    private String metrics;
    private String operation;
    private String valueMetrics;

    public int getIdAlerts() {
        return idAlerts;
    }

    public void setIdAlerts(int idalerts) {
        this.idAlerts = idalerts;
    }

    public String getNameAlerts() {
        return nameAlerts;
    }

    public void setNameAlerts(String nameAlerts) {
        this.nameAlerts = nameAlerts;
    }

    public Provider getProv() {
        return prov;
    }

    public void setProv(Provider prov) {
        this.prov = prov;
    }

    public String getStatusProvider() {
        return statusProvider;
    }

    public void setStatusProvider(String statusProvider) {
        this.statusProvider = statusProvider;
    }

    public String getMetrics() {
        return metrics;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getValueMetrics() {
        return valueMetrics;
    }

    public void setValueMetrics(String valueMetrics) {
        this.valueMetrics = valueMetrics;
    }
        
}
