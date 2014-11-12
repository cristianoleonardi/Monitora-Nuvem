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
public class HistoryCostProvider {
    private int idHistoryCostProvider;
    private Provider prov;
    private String cost;
    private String time;
    private Date data;

    public int getIdHistoryCostProvider() {
        return idHistoryCostProvider;
    }

    public void setIdHistoryCostProvider(int idHistoryCostProvider) {
        this.idHistoryCostProvider = idHistoryCostProvider;
    }

    public Provider getProv() {
        return prov;
    }

    public void setProv(Provider prov) {
        this.prov = prov;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
   
}
