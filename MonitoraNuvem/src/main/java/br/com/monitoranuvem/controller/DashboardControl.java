package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.InstanceProviderBD;
import br.com.monitoranuvem.model.ThreadAmazon;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderBD;
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

    public DashboardControl() throws ClassNotFoundException, SQLException {

    }

    public void monitoraNuvem() throws ClassNotFoundException, SQLException, ParseException {
        for (Provider prov : new ProviderBD().listaProvider()) {
            new InstanceProviderBD().atualizaChecked(prov);
            if (prov.getNome().equals("OpenStack")) {
                threadOpen = new ThreadOpenStack(prov, 60000);
                new Thread(threadOpen).start();
            } else if (prov.getNome().equals("Amazon")) {
                threadAmazon = new ThreadAmazon(prov, 60000);
                new Thread(threadAmazon).start();
            }
        }
    }

    public void stopThread() {
        
//        threadOpen.terminate();
//        threadAmazon.terminate();
    }
}
