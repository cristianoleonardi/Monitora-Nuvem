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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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


    public ArrayList<String> montaHistorico(int numDias) throws ClassNotFoundException, SQLException, ParseException {
        Map<String, String> statusProvider = new HashMap<String, String>();
        ArrayList<HistoricoInterna> historico = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateForMySql = "";
        Date data = new Date();
        dateForMySql = sdf.format(data);
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(""
                + "SELECT DATE(DATAATUALIZACAO) AS DATA, STATUSPROVIDER, COUNT(*) AS CONTADOR \n"
                + "FROM HISTORICOPROVIDER "
                + "WHERE IDHISTORICOPROVIDER IN "
                + "( SELECT MAX(IDHISTORICOPROVIDER) "
                + "FROM HISTORICOPROVIDER "
                + "WHERE DATE(DATAATUALIZACAO) BETWEEN DATE_SUB(?, INTERVAL ? DAY) AND ? "
                + "GROUP BY DATE(DATAATUALIZACAO),IDINSTANCEPROVIDER) "
                + "GROUP BY DATE(DATAATUALIZACAO), STATUSPROVIDER "
                + "ORDER BY DATE(DATAATUALIZACAO) ");
        stmt.setString(1, dateForMySql);
        stmt.setInt(2, numDias);
        stmt.setString(3, dateForMySql);
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            statusProvider.put(resultado.getString("STATUSPROVIDER"), "");
            historico.add(new HistoricoInterna(resultado.getString("DATA"), resultado.getString("STATUSPROVIDER"), resultado.getInt("CONTADOR")));
        }
        conn.close();
        return montaGrafico(statusProvider, historico, numDias);
    }

    private ArrayList<String> montaGrafico(Map<String, String> status, ArrayList<HistoricoInterna> hist, int numDias) throws ParseException {
        String[] chartColours = new String[]{"#B5DDFF", "#FFBDBD", "#E2FFE7", "#CDE1FF", "#FFE386", "#D2FF90", "#A3EEFF"};
        int count = 0;
        ArrayList<String> grafico = new ArrayList<>();
        String hltd = "";
        hltd += "{";
        String linha = "";
        SimpleDateFormat dt;
        dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        ArrayList<String> arDatas = geraDatas(numDias);
        for (String key : status.keySet()) {
            hltd += "label: \"" + key + "\",";
            hltd += "data: [";
            for (int i = 0; i < arDatas.size(); i++) {
                date = dt.parse(arDatas.get(i) + " 00:00:00");
                linha = "[" + date.getTime() + "," + "0" + "]";
                for (int j = 0; j < hist.size(); j++) {
                    if (arDatas.get(i).equals(hist.get(j).data)
                            && key.equals(hist.get(j).status)) {
                        date = dt.parse(hist.get(j).data + " 00:00:00");
                        linha = "[" + date.getTime() + "," + hist.get(j).quantidade + "]";
                        break;
                    }
                }
                hltd += linha;
                if (i < arDatas.size() - 1) {
                    hltd += ",";
                }
            }
            hltd += "],";
            hltd += "lines: {fillColor: \"" + chartColours[count] + "\"},";
            hltd += "points: {fillColor: \"#fff\"}";
            hltd += "}";
            if (count < status.size() - 1) {
                hltd += ";{";
            }
            count++;
        }
        date = dt.parse(arDatas.get(arDatas.size() - 1) + " 00:00:00");
        grafico.add(String.valueOf(date.getTime()));
        date = dt.parse(arDatas.get(0) + " 00:00:00");
        grafico.add(String.valueOf(date.getTime()));
        grafico.add(hltd);
        return grafico;
    }

    private ArrayList<String> geraDatas(int numDias) {
        ArrayList<String> arrDatas = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateForMySql = "";
        Date data = new Date();
        dateForMySql = sdf.format(data);
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i <= numDias; i++) {
            calendar.setTime(data);
            calendar.add(Calendar.DAY_OF_MONTH, -i);
            arrDatas.add(sdf.format(calendar.getTime()));
        }
        return arrDatas;
    }

    class HistoricoInterna {

        private final String data;
        private final String status;
        private final int quantidade;

        public HistoricoInterna(String data, String status, int quantidade) {
            this.data = data;
            this.status = status;
            this.quantidade = quantidade;
        }
    }
}
