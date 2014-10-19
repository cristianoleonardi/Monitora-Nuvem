package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.InstanceProviderBD;
import br.com.monitoranuvem.model.ThreadAmazon;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderBD;
import br.com.monitoranuvem.model.ThreadAlerts;
import br.com.monitoranuvem.model.ThreadOpenStack;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author Cristiano Leonardi, Márcio Bolzan
 */
public class DashboardControl {

    private Runnable threadAmazon;
    private Runnable threadOpen;
    private Runnable threadAlert;
    private Thread thrAlert;
    private Thread thrOpen;
    private Thread thrAmazon;
    private static DashboardControl dc = null;

    public static synchronized DashboardControl getInstance() throws ClassNotFoundException, SQLException {
        if (dc == null) {
            dc = new DashboardControl();
        }
        return dc;
    }

    public void startThread() throws ClassNotFoundException, SQLException, ParseException {
        for (Provider prov : new ProviderBD().listaProvider()) {
            new InstanceProviderBD().atualizaChecked(prov);
            if (prov.getNome().equals("OpenStack")) {
                threadOpen = new ThreadOpenStack(prov, 60000);
                thrOpen = new Thread(threadOpen);
                thrOpen.start();
            } else if (prov.getNome().equals("Amazon")) {
                threadAmazon = new ThreadAmazon(prov, 60000);
                thrAmazon = new Thread(threadAmazon);
                thrAmazon.start();
            }
        }
        threadAlert = new ThreadAlerts(60000);
        thrAlert = new Thread(threadAlert);
        thrAlert.start();
    }

    public void stopThread() {
        if (statusThreadAlerts() != null) {
            thrAlert.interrupt();
        }

        if (statusThreadOpen() != null) {
            thrOpen.interrupt();
        }

        if (statusThreadAmazon() != null) {
            thrAmazon.interrupt();
        }
    }

    public String statusThreadAlerts() {
        if (thrAlert != null) {
            return thrAlert.getState().toString();
        }
        return null;
    }

    public String statusThreadAmazon() {
        if (thrAmazon != null) {
            return thrAmazon.getState().toString();
        }
        return null;
    }

    public String statusThreadOpen() {
        if (thrOpen != null) {
            return thrOpen.getState().toString();
        }
        return null;
    }
}
