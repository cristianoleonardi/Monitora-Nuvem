/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.SendAlertsBD;
import java.sql.SQLException;

/**
 *
 * @author Marcio
 */
public class ProviderSendAlerts {

    public void atualizaStatusMail(int idSendAlerts) throws ClassNotFoundException, SQLException {
        new SendAlertsBD().atualizaStatusMail(idSendAlerts);
    }
}
