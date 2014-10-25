/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import br.com.monitoranuvem.controller.ProviderAlerts;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ThreadAlerts implements Runnable {

    private int delay;
    private int count;

    public ThreadAlerts(int tempoDelay) {
        delay = tempoDelay;
    }

    @Override
    public void run() {
        try {
            for (;;) {
                ArrayList<Alerts> alert = new ProviderAlerts().listaAlerts();
                int totalInstance = 0;
                double vPerc = 0;

                for (Alerts a : alert) {
                    count = 0;
                    ArrayList<InstanceProvider> listInstance = new InstanceProviderBD().listaQDTStatusProviderDay(a.getProv().getId(), a.getStatusProvider().toUpperCase().trim());
                    switch (a.getMetrics().toUpperCase()) {
                        case "N":
                            switch (a.getOperation()) {
                                case "=":
                                    if (listInstance.size() == Integer.valueOf(a.getValueMetrics())) {
                                        atualizaSendAlert(a.getIdAlerts());
                                        count = 1;
                                    }
                                    break;
                                case ">":
                                    if (listInstance.size() > Integer.valueOf(a.getValueMetrics())) {
                                        atualizaSendAlert(a.getIdAlerts());
                                        count = 1;
                                    }
                                    break;
                                case ">=":
                                    if (listInstance.size() >= Integer.valueOf(a.getValueMetrics())) {
                                        atualizaSendAlert(a.getIdAlerts());
                                        count = 1;
                                    }
                                    break;
                                case "<":
                                    if (listInstance.size() < Integer.valueOf(a.getValueMetrics())) {
                                        atualizaSendAlert(a.getIdAlerts());
                                        count = 1;
                                    }
                                    break;
                                case "<=":
                                    if (listInstance.size() <= Integer.valueOf(a.getValueMetrics())) {
                                        atualizaSendAlert(a.getIdAlerts());
                                        count = 1;
                                    }
                                    break;
                            }
                            break;
                        case "%":
                            totalInstance = new InstanceProviderBD().totalInstaceProvider(a.getProv().getId());
                            vPerc = listInstance.size() * 100.0 / totalInstance;
                            switch (a.getOperation()) {
                                case "=":
                                    if (vPerc == Double.parseDouble(a.getValueMetrics())) {
                                        atualizaSendAlert(a.getIdAlerts());
                                        count = 1;
                                    }
                                    break;
                                case ">":
                                    if (vPerc > Double.parseDouble(a.getValueMetrics())) {
                                        atualizaSendAlert(a.getIdAlerts());
                                        count = 1;
                                    }
                                    break;
                                case ">=":
                                    if (vPerc >= Double.parseDouble(a.getValueMetrics())) {
                                        atualizaSendAlert(a.getIdAlerts());
                                        count = 1;
                                    }
                                    break;
                                case "<":
                                    if (vPerc < Double.parseDouble(a.getValueMetrics())) {
                                        atualizaSendAlert(a.getIdAlerts());
                                        count = 1;
                                    }
                                    break;
                                case "<=":
                                    if (vPerc <= Double.parseDouble(a.getValueMetrics())) {
                                        atualizaSendAlert(a.getIdAlerts());
                                        count = 1;
                                    }
                                    break;
                            }
                            break;
                    }
                    if (count == 0) {
                        atualizaSendAlert(a.getIdAlerts());
                    }
                }
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            return;
        }
    }

    private void atualizaSendAlert(int idAlert) throws ClassNotFoundException, SQLException {
        int num = new SendAlertsBD().existeAlert(idAlert);
        int idSend;
        if (num > 0) {
            idSend = new SendAlertsBD().buscaSendAlert(idAlert);
            if (count == 0) {
                new SendAlertsBD().atualizaSendAlert(idSend);
            }
            new HistorySendAlertsBD().criarHistoricoAlerts(idSend);
        } else {
            if (count > 0) {
                idSend = new SendAlertsBD().criarAlerts(idAlert);
                new HistorySendAlertsBD().criarHistoricoAlerts(idSend);
            }
        }
    }
}
