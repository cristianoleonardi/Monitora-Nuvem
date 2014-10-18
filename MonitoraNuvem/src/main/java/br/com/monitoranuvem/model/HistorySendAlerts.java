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
public class HistorySendAlerts {
    private int idHistoricoSendAlerts;
    private int idSendAlerts;
    private Date date;

    public int getIdHistoricoSendAlerts() {
        return idHistoricoSendAlerts;
    }

    public void setIdHistoricoSendAlerts(int idHistoricoSendAlerts) {
        this.idHistoricoSendAlerts = idHistoricoSendAlerts;
    }

    public int getIdSendAlerts() {
        return idSendAlerts;
    }

    public void setIdSendAlerts(int idSendAlerts) {
        this.idSendAlerts = idSendAlerts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }   
}
