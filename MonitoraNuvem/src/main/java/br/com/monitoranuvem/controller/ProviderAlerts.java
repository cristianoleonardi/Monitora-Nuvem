/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.Alerts;
import br.com.monitoranuvem.model.AlertsBD;
import br.com.monitoranuvem.model.ProviderBD;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ProviderAlerts {

    public boolean criarAlerts(String nameAlerts, int idProvider, String statusProvider, String metrics, String operation, String valueMetrics, String mail, String typeAlert) throws ClassNotFoundException, SQLException {
        Alerts alert = new Alerts();
        alert.setNameAlerts(nameAlerts);
        alert.setProv(new ProviderBD().buscaProvider(idProvider));
        alert.setStatusProvider(statusProvider);
        alert.setMetrics(metrics);
        alert.setOperation(operation);
        alert.setValueMetrics(valueMetrics);
        alert.setMail(mail);
        alert.setTypeAlert(typeAlert);
        return new AlertsBD().criarAlerts(alert);
    }

    public Alerts buscaAlerts(int idAlerts) throws ClassNotFoundException, SQLException {
        return new AlertsBD().buscaAlerts(idAlerts);
    }

    public ArrayList<Alerts> listaAlerts() throws ClassNotFoundException, SQLException {
        return new AlertsBD().listaAlerts();
    }

    public boolean deletaAlerts(int idAlerts) throws ClassNotFoundException, SQLException {
        return new AlertsBD().deletaAlerts(idAlerts);
    }

    public boolean atualizaAlerts(String nameAlerts, int idProvider, String statusProvider, String metrics, String operation, String valueMetrics, int idAlerts, String mail, String typeAlert) throws ClassNotFoundException, SQLException {
        Alerts alert = new Alerts();
        alert.setNameAlerts(nameAlerts);
        alert.setProv(new ProviderBD().buscaProvider(idProvider));
        alert.setStatusProvider(statusProvider);
        alert.setMetrics(metrics);
        alert.setOperation(operation);
        alert.setValueMetrics(valueMetrics);
        alert.setMail(mail);
        alert.setTypeAlert(typeAlert);
        return new AlertsBD().atualizaAlerts(alert, idAlerts);
    }
}
