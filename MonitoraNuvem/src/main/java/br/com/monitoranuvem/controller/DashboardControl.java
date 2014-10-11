package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderBD;
import br.com.monitoranuvem.model.ThreadAmazon;
import br.com.monitoranuvem.model.ThreadOpenStack;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Cristiano Leonardi, Márcio Bolzan
 */
public class DashboardControl {

    private ExecutorService executor;

    public DashboardControl() throws ClassNotFoundException, SQLException {

    }

    public void monitoraNuvem() throws ClassNotFoundException, SQLException, ParseException {
        executor = Executors.newFixedThreadPool(new ProviderBD().listaProvider().size());
        for (Provider prov : new ProviderBD().listaProvider()) {
            if (prov.getNome().equals("OpenStack")) {
                System.out.println("openstak comentada, quando tiver provedor é so descomentar");
//                executor.execute(new ThreadOpenStack(prov));
            } else if (prov.getNome().equals("Amazon")) {
                executor.execute(new ThreadAmazon(prov));
            }
        }
//        executor.shutdown();
    }

    public void stopThread() {
        executor.shutdown();
        try {
             if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Cancel currently executing tasks
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
