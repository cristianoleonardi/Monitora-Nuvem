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
public class SendAlerts {

    private int idSendAlerts;
    private Alerts alerts;
    private Date dateSendAlerts;
    private int send;

    public int getIdSendAlerts() {
        return idSendAlerts;
    }

    public void setIdSendAlerts(int idSendAlerts) {
        this.idSendAlerts = idSendAlerts;
    }

    public Alerts getAlerts() {
        return alerts;
    }

    public void setAlerts(Alerts alerts) {
        this.alerts = alerts;
    }

    public Date getDateSendAlerts() {
        return dateSendAlerts;
    }

    public void setDateSendAlerts(Date dateSendAlerts) {
        this.dateSendAlerts = dateSendAlerts;
    }

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }
}
