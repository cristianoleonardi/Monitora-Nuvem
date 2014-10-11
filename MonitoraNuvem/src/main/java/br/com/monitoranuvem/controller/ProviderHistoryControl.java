package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.HistoryProvider;
import br.com.monitoranuvem.model.HistoryProviderBD;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ProviderHistoryControl {
    
    public boolean criarHistorico(HistoryProvider hist) throws ClassNotFoundException, SQLException, ParseException {
             new HistoryProviderBD().criarHistorico(hist);
        return true;
    }

    public HistoryProvider buscaHistoryProvider(int id) throws ClassNotFoundException, SQLException, ParseException {
        return new HistoryProviderBD().buscaHistoryProvider(id);
    }

    public ArrayList<HistoryProvider> listaHistoryProvider() throws ClassNotFoundException, SQLException, ParseException {
        return new HistoryProviderBD().listaHistoryProvider();
    }
    
    public String historyLastThirtyDays() throws ClassNotFoundException, SQLException, ParseException{
        return new HistoryProviderBD().historyLastThirtyDays();
    }
    
    public long[] getFirstLastDay() throws ClassNotFoundException, SQLException, ParseException{
        return new HistoryProviderBD().getFirstLastDays();
    }
}
