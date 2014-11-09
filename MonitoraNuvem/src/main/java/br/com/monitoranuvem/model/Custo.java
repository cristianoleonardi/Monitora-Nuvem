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
public class Custo {

    private Provider prov;
    private String custo;
    private String totalHoras;

    public Custo(Provider prov, String custo, String totalHoras) {
        this.prov = prov;
        this.custo = custo;
        this.totalHoras = totalHoras;
    }


    public Provider getProv() {
        return prov;
    }

    public void setProv(Provider prov) {
        this.prov = prov;
    }

    public String getCusto() {
        return custo;
    }

    public void setCusto(String custo) {
        this.custo = custo;
    }

    public String getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(String totalHoras) {
        this.totalHoras = totalHoras;
    }

}
