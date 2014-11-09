/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.SendAlerts;
import br.com.monitoranuvem.model.SendAlertsBD;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class SendAlertsControl {
    
    public ArrayList<SendAlerts> listaSendAlerts() throws ClassNotFoundException, SQLException, ParseException{
        return new SendAlertsBD().listaSendAlerts();
    }
    
    public ArrayList<SendAlerts> listaSendAlertsHistory() throws ClassNotFoundException, SQLException, ParseException{
        return new SendAlertsBD().listaSendAlertsHistory();
    }
}
