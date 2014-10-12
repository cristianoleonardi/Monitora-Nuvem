package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.InstanceProviderBD;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderBD;
import br.com.monitoranuvem.model.ThreadAmazon;
import br.com.monitoranuvem.model.ThreadOpenStack;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Cristiano Leonardi, Márcio Bolzan
 */
public class DashboardControl {

    private ExecutorService executor;
    private ThreadOpenStack threadOpen;
    private ThreadAmazon threadAmazon;

    public DashboardControl() throws ClassNotFoundException, SQLException {

    }

    public void monitoraNuvem() throws ClassNotFoundException, SQLException, ParseException {
        executor = Executors.newFixedThreadPool(new ProviderBD().listaProvider().size());
        for (Provider prov : new ProviderBD().listaProvider()) {
            new InstanceProviderBD().atualizaChecked(prov);
            if (prov.getNome().equals("OpenStack")) {
                threadOpen = new ThreadOpenStack(prov);
                executor.execute(threadOpen);
            } else if (prov.getNome().equals("Amazon")) {
                threadAmazon = new ThreadAmazon(prov);
                executor.execute(threadAmazon);
            }
        }
        executor.shutdown();
    }

    public void stopThread() {
        threadOpen.terminate();
        threadAmazon.terminate();
    }
}
