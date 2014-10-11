package br.com.monitoranuvem.model;

import br.com.monitoranuvem.connection.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryProviderBD {

    private Connection conn;

    public boolean criarHistorico(HistoryProvider hist) throws ClassNotFoundException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateForMySql = "";
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO HISTORICOPROVIDER (INSTANCEPROVIDER, STATUSPROVIDER, DATAATUALIZACAO)"
                + " VALUES (?,?,?)"
        );
        stmt.setString(1, hist.getInstanceProvider().getIdInstance());
        stmt.setString(2, hist.getStatus());
        dateForMySql = sdf.format(hist.getDataUpdate());
        stmt.setString(3, dateForMySql);
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        }
        return false;
    }

    public HistoryProvider buscaHistoryProvider(int id) throws ClassNotFoundException, SQLException, ParseException {
        HistoryProvider hist = null;
        String date_s;
        SimpleDateFormat dt;
        Date date;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDHISTORICOPROVIDER, IDINSTANCEPROVIDER, STATUSPROVIDER, DATAATUALIZACAO"
                + "FROM HISTORICOPROVIDER WHERE IDHISTORICOPROVIDER=?"
        );
        stmt.setInt(1, id);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            hist = new HistoryProvider();
            hist.setIdHistoryProvider(resultado.getInt("IDINSTANCEPROVIDER"));
            hist.setInstanceProvider(new InstanceProviderBD().buscaInstanceProvider(resultado.getInt("IDINSTANCEPROVIDER")));
            hist.setStatus(resultado.getString("STATUSPROVIDER"));
            date_s = resultado.getString("DATAATUALIZACAO");
            dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = dt.parse(date_s);
            hist.setDataUpdate(date);
        }
        conn.close();
        return hist;
    }

    public ArrayList<HistoryProvider> listaHistoryProvider() throws ClassNotFoundException, SQLException, ParseException {
        conn = new ConnectionMySql().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT * FROM HISTORICOPROVIDER ORDER BY IDHISTORICOPROVIDER ");
        ArrayList<HistoryProvider> lista = new ArrayList<>();
        HistoryProvider hist;
        String date_s;
        SimpleDateFormat dt;
        Date date;
        while (resultado.next()) {
            hist = new HistoryProvider();
            hist.setIdHistoryProvider(resultado.getInt("IDHISTORICOPROVIDER"));
            hist.setInstanceProvider(new InstanceProviderBD().buscaInstanceProvider(resultado.getInt("IDINSTANCEPROVIDER")));
            hist.setStatus(resultado.getString("STATUSPROVIDER"));
            date_s = resultado.getString("DATAATUALIZACAO");
            dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = dt.parse(date_s);
            hist.setDataUpdate(date);
            lista.add(hist);
        }
        conn.close();
        return lista;
    }

    public ArrayList<String> listaLastThirtyDays(String today) throws ClassNotFoundException, SQLException, ParseException {
        String date_s;
        SimpleDateFormat dt;
        String date;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT DATAATUALIZACAO FROM HISTORICOPROVIDER WHERE DATAATUALIZACAO BETWEEN ? - 30 AND ? ORDER BY DATAATUALIZACAO"
        );
        stmt.setString(1, today);
        stmt.setString(2, today);
        ResultSet resultado = stmt.executeQuery();
        ArrayList<String> dates = new ArrayList<>();
        while (resultado.next()) {
            date_s = resultado.getString("DATAATUALIZACAO");
            dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = date_s;
            dates.add(date);
        }
        conn.close();
        return dates;
    }

    public ArrayList<Long> listaLastThirtyDaysInt(String today) throws ClassNotFoundException, SQLException, ParseException {
        String date_s;
        SimpleDateFormat dt;
        Date date;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT DATAATUALIZACAO FROM HISTORICOPROVIDER WHERE DATAATUALIZACAO BETWEEN ? - 30 AND ? ORDER BY DATAATUALIZACAO"
        );
        stmt.setString(1, today);
        stmt.setString(2, today);
        ResultSet resultado = stmt.executeQuery();
        ArrayList<Long> dates = new ArrayList<>();
        while (resultado.next()) {
            date_s = resultado.getString("DATAATUALIZACAO");
            dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = dt.parse(date_s);
            //dates.add((int) date.getTime());
            dates.add(date.getTime());
        }
        conn.close();
        return dates;
    }

    public ArrayList<String> statusInPeriod(String today) throws ClassNotFoundException, SQLException, ParseException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT DISTINCT STATUSPROVIDER FROM HISTORICOPROVIDER WHERE DATAATUALIZACAO BETWEEN ? -30 AND ?"
        );
        stmt.setString(1, today);
        stmt.setString(2, today);
        ResultSet resultado = stmt.executeQuery();
        ArrayList<String> listaStatus = new ArrayList<>();
        while (resultado.next()) {
            listaStatus.add(resultado.getString("STATUSPROVIDER"));
        }
        conn.close();
        return listaStatus;
    }

    public int qtdStatusByDay(String day, String status) throws ClassNotFoundException, SQLException, ParseException {
        int quantidade = 0;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT STATUSPROVIDER, COUNT(*) AS QUANTIDADE FROM HISTORICOPROVIDER WHERE DATAATUALIZACAO = ? AND STATUSPROVIDER = ? GROUP BY STATUSPROVIDER"
        );
        stmt.setString(1, day);
        stmt.setString(2, status);
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            quantidade = resultado.getInt("QUANTIDADE");
        }
        conn.close();
        return quantidade;
    }

    public long[] getFirstLastDays() throws ClassNotFoundException, SQLException, ParseException{
        long[] dias = new long[2];
        ArrayList<Long> listaDatasNumero = listaLastThirtyDaysInt(Util.getDateTime());
        dias[0] = listaDatasNumero.get(0);
        dias[1] = listaDatasNumero.get(listaDatasNumero.size()-1);
        return dias;
    }
    
    public String historyLastThirtyDays() throws ClassNotFoundException, SQLException, ParseException {
        String hltd = "";

        ArrayList<String> listaStatus = statusInPeriod(Util.getDateTime());
        hltd += "{";
        String status = "";
        String dia = "";
        long diaNumero;
        for (int i = 0; i < listaStatus.size(); i++) {
            status = listaStatus.get(i);
            hltd += "label: \"" + status + "\",";
            ArrayList<String> listaDatas = listaLastThirtyDays(Util.getDateTime());
            ArrayList<Long> listaDatasNumero = listaLastThirtyDaysInt(Util.getDateTime());
            hltd += "data: [";
            for (int j = 0; j < listaDatas.size(); j++) {
                dia = listaDatas.get(j);
                diaNumero = listaDatasNumero.get(j);
                hltd += "[" + diaNumero + "," + qtdStatusByDay(dia, status) + "]";
                if (j < listaDatas.size() - 1) {
                    hltd += ",";
                }
            }
            hltd += "],";
            hltd += "lines: {fillColor: \"#f3faff\"},";
            hltd += "points: {fillColor: \"#fff\"}";
            hltd += "}";
            if (i < listaStatus.size() - 1) {
                hltd += ";{";
            }
        }
        return hltd;
    }
}
