/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Cristiano
 */
public class ThreadCost implements Runnable {

    private int delay;

    public ThreadCost(int tempoDelay) {
        delay = tempoDelay;
    }

    @Override
    public void run() {
        try {
            for (;;) {
                Date data = new Date();
                long tempo = 0;
                ArrayList<Cost> c = new ArrayList<>();
                double priceprovider = 0;
                ArrayList<ProviderPrice> provPrice = new ProviderPriceBD().listaProviderPrice();
                ArrayList<Provider> lstProvider = new ProviderBD().listaProvider();
                for (Provider lstPrv : lstProvider) {
                    priceprovider = 0;
                    tempo = 0;
                    ArrayList<InstanceProvider> listInstance = new InstanceProviderBD().listaProviderDay(lstPrv.getId());
                    for (InstanceProvider lstInstance : listInstance) {
                        for (ProviderPrice pp : provPrice) {
                            if (pp.getProvider().getId() == lstInstance.getProvider().getId()
                                    && pp.getInstanceType().equals(lstInstance.getTypeinstance())) {
                                priceprovider += ((data.getTime() - lstInstance.getDataCreate().getTime()) / (double) (1000.0 * 60.0 * 60.0)) * pp.getPrice();
                                tempo += (data.getTime() - lstInstance.getDataCreate().getTime());
                            }
                        }
                    }
                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(tempo),
                            TimeUnit.MILLISECONDS.toMinutes(tempo) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(tempo)),
                            TimeUnit.MILLISECONDS.toSeconds(tempo) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tempo)));
                    DecimalFormat df = new DecimalFormat("####0.00");
                    new HistoryCostBD().criarHistoricoCusto(lstPrv, df.format(priceprovider), hms);
                 }
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            return;
        }
    }
}
