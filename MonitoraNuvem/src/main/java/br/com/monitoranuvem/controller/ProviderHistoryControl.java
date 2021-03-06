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

    public ArrayList<HistoryProvider> listaHistoryProvider(int initial, int fim) throws ClassNotFoundException, SQLException, ParseException {
        return new HistoryProviderBD().listaHistoryProvider(initial, fim);
    }

    public ArrayList<String> montaHistorico(int numDias) throws ClassNotFoundException, SQLException, ParseException {
        return new HistoryProviderBD().montaHistorico(numDias);
    }
}
