/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

/**
 *
 * @author Cristiano
 */
public class MNComputeService {
    String id;
    String status;
    String provedor;

    public String getProvedor() {
        return provedor;
    }

    public void setProvedor(String provedor) {
        this.provedor = provedor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
