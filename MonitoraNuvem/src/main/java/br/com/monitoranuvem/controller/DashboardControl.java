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

    public DashboardControl() throws ClassNotFoundException, SQLException {

    }

    public void startThread() throws ClassNotFoundException, SQLException, ParseException {
//        for (Provider prov : new ProviderBD().listaProvider()) {
//            new InstanceProviderBD().atualizaChecked(prov);
//            if (prov.getNome().equals("OpenStack")) {
//                threadOpen = new ThreadOpenStack(prov, 60000);
//                thrOpen = new Thread(threadOpen);
//                thrOpen.start();
//            } else if (prov.getNome().equals("Amazon")) {
//                threadAmazon = new ThreadAmazon(prov, 60000);
//                thrAmazon = new Thread(threadAmazon);
//                thrAmazon.start();
//            }
//        }
        threadAlert = new ThreadAlerts(6000);
        thrAlert = new Thread(threadAlert);
        thrAlert.start();
    }

    public void stopThread() {
        thrAlert.interrupt();
        thrOpen.interrupt();
        thrAmazon.interrupt();
    }
    
    public String statusThreadAlerts(){
        return thrAlert.getState().toString();
    }
    
    public String statusThreadAmazon(){
        return thrAmazon.getState().toString();
    }
    
    public String statusThreadOpen(){
        return thrOpen.getState().toString();
    }
}
