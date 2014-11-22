/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Marcio
 */
public class InstanceProviderBD {

    private Connection conn;

    public boolean criarInstancia(InstanceProvider inst) throws ClassNotFoundException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateForMySql = "";
        Date data = new Date();
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO INSTANCEPROVIDER (INSTANCEPROVIDER,STATUSPROVIDER, IDPROVIDER,"
                + "IDINSTANCE,DATECREATE,DATEUPDATE,ISCHECKED,DATECHECKED,TYPEINSTANCE,SONAME, SOTYPE, SOVERSION, SOFAMILY, HWRAM, VOLUMES, CORES, SPEED) "
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
        );
        stmt.setString(1, inst.getInstanceProvider());
        stmt.setString(2, inst.getStatus());
        stmt.setInt(3, inst.getProvider().getId());
        stmt.setString(4, inst.getIdInstance());
        if (inst.getDataCreate() != null) {
            dateForMySql = sdf.format(inst.getDataCreate());
            stmt.setString(5, dateForMySql);
        } else {
            stmt.setString(5, null);
        }

        if (inst.getDataUpdate() != null) {
            dateForMySql = sdf.format(inst.getDataUpdate());
            stmt.setString(6, dateForMySql);
        } else {
            stmt.setString(6, null);
        }

        stmt.setInt(7, 1);
        dateForMySql = sdf.format(data);
        stmt.setString(8, dateForMySql);
        if (inst.getTypeinstance() != null) {
            stmt.setString(9, inst.getTypeinstance());
        } else {
            stmt.setString(9, null);
        }
        if (inst.getSoName() != null) {
            stmt.setString(10, inst.getSoName());
        } else {
            stmt.setString(10, null);
        }
        if (inst.getSoType() != null) {
            stmt.setString(11, inst.getSoType());
        } else {
            stmt.setString(11, null);
        }
        if (inst.getSoVersion() != null) {
            stmt.setString(12, inst.getSoVersion());
        } else {
            stmt.setString(12, null);
        }
        if (inst.getSoFamily() != null) {
            stmt.setString(13, inst.getSoFamily());
        } else {
            stmt.setString(13, null);
        }
        stmt.setInt(14, inst.getHwRam());
        if (inst.getVolumes().toString() != null) {
            stmt.setString(15, inst.getVolumes().toString());
        } else {
            stmt.setString(15, null);
        }
        if (inst.getCores() != null) {
            stmt.setString(16, inst.getCores().toString());
        } else {
            stmt.setString(16, null);
        }
        if (inst.getSpeed() != null) {
            stmt.setString(17, inst.getSpeed().toString());
        } else {
            stmt.setString(17, null);
        }
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        }
        return false;
    }

    public boolean criarHistorico(InstanceProvider inst) throws ClassNotFoundException, SQLException, ParseException {
        InstanceProvider instol = buscaInstanceProvider(inst.getIdInstance());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = new Date();
        String dateForMySql = "";
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO HISTORICOPROVIDER (IDINSTANCEPROVIDER,STATUSPROVIDER, DATAATUALIZACAO) "
                + " VALUES (?,?,?)"
        );
        stmt.setInt(1, instol.getIdInstanceProvider());
        stmt.setString(2, inst.getStatus());
        dateForMySql = sdf.format(data);
        stmt.setString(3, dateForMySql);
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        }
        return false;
    }

    public int existeInstancia(InstanceProvider inst) throws ClassNotFoundException, SQLException {
        int num = 0;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT COUNT(*) AS QUANTIDADE \n"
                + "FROM INSTANCEPROVIDER	\n"
                + "WHERE IDINSTANCE=?");
        stmt.setString(1, inst.getIdInstance());
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            num = resultado.getInt("QUANTIDADE");
        }
        conn.close();
        return num;
    }

    public boolean atualizaIntancia(InstanceProvider inst) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = new Date();
        String dateForMySql = "";
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE INSTANCEPROVIDER SET STATUSPROVIDER=?, "
                + "DATEUPDATE=?,ISCHECKED=?, DATECHECKED=?, "
                + "INSTANCEPROVIDER=?, TYPEINSTANCE=?, SONAME=?, "
                + "SOTYPE=?, SOVERSION=?, SOFAMILY=?, HWRAM=?, VOLUMES=?, CORES=?, SPEED=? "
                + " WHERE IDINSTANCE=?"
        );
        stmt.setString(1, inst.getStatus());
        if (inst.getDataUpdate() != null) {
            dateForMySql = sdf.format(inst.getDataUpdate());
            stmt.setString(2, dateForMySql);
        } else {
            stmt.setString(2, null);
        }
        stmt.setInt(3, 1);
        dateForMySql = sdf.format(data);
        stmt.setString(4, dateForMySql);
        stmt.setString(5, inst.getInstanceProvider());
        if (inst.getTypeinstance() != null) {
            stmt.setString(6, inst.getTypeinstance());
        } else {
            stmt.setString(6, null);
        }
        if (inst.getSoName() != null) {
            stmt.setString(7, inst.getSoName());
        } else {
            stmt.setString(7, null);
        }
        if (inst.getSoType() != null) {
            stmt.setString(8, inst.getSoType());
        } else {
            stmt.setString(8, null);
        }
        if (inst.getSoVersion() != null) {
            stmt.setString(9, inst.getSoVersion());
        } else {
            stmt.setString(9, null);
        }
        if (inst.getSoFamily() != null) {
            stmt.setString(10, inst.getSoFamily());
        } else {
            stmt.setString(10, null);
        }
        stmt.setInt(11, inst.getHwRam());
        if (inst.getVolumes() != null) {
            stmt.setString(12, inst.getVolumes().toString());
        } else {
            stmt.setString(12, null);
        }
        if (inst.getCores() != null) {
            stmt.setString(13, inst.getCores().toString());
        } else {
            stmt.setString(13, null);
        }
        if (inst.getSpeed() != null) {
            stmt.setString(14, inst.getSpeed().toString());
        } else {
            stmt.setString(14, null);
        }
        stmt.setString(15, inst.getIdInstance());
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizaChecked(Provider prov) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE INSTANCEPROVIDER SET ISCHECKED=? "
                + "WHERE IDPROVIDER=? AND STATUSPROVIDER!=?"
        );
        stmt.setInt(1, 0);
        stmt.setInt(2, prov.getId());
        stmt.setString(3, "TERMINATED");
        int ret = stmt.executeUpdate();
        conn.close();
        if (ret > 0) {
            return true;
        } else {
            return false;
        }
    }

    public InstanceProvider buscaInstanceProvider(String idInstance) throws ClassNotFoundException, SQLException, ParseException {
        InstanceProvider instance = null;
        String date_s;
        SimpleDateFormat dt;
        Date date;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDINSTANCEPROVIDER,INSTANCEPROVIDER,STATUSPROVIDER, IDPROVIDER,"
                + "IDINSTANCE,DATECREATE,DATEUPDATE, ISCHECKED, DATECHECKED, TYPEINSTANCE, SONAME, SOTYPE, SOVERSION, SOFAMILY, HWRAM, VOLUMES, CORES, SPEED "
                + "FROM INSTANCEPROVIDER WHERE IDINSTANCE=?"
        );
        stmt.setString(1, idInstance);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            instance = new InstanceProvider();
            instance.setIdInstanceProvider(resultado.getInt("IDINSTANCEPROVIDER"));
            instance.setInstanceProvider(resultado.getString("INSTANCEPROVIDER"));
            instance.setStatus(resultado.getString("STATUSPROVIDER"));
            instance.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            instance.setIdInstance(resultado.getString("IDINSTANCE"));
            date_s = resultado.getString("DATECREATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instance.setDataCreate(date);
            } else {
                instance.setDataCreate(null);
            }
            date_s = resultado.getString("DATEUPDATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instance.setDataUpdate(date);
            } else {
                instance.setDataUpdate(null);
            }
            instance.setIsChecked(resultado.getInt("ISCHECKED"));
            date_s = resultado.getString("DATECHECKED");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instance.setDateChecked(date);
            } else {
                instance.setDateChecked(null);
            }
            instance.setTypeinstance(resultado.getString("TYPEINSTANCE"));
            instance.setSoName(resultado.getString("SONAME"));
            instance.setSoType(resultado.getString("SOTYPE"));
            instance.setSoVersion(resultado.getString("SOVERSION"));
            instance.setSoFamily(resultado.getString("SOFAMILY"));
            instance.setHwRam(resultado.getInt("HWRAM"));
            instance.setVolumes(resultado.getString("VOLUMES"));
            instance.setCores(resultado.getString("CORES"));
            instance.setSpeed(resultado.getString("SPEED"));
        }
        conn.close();
        return instance;
    }

    public InstanceProvider buscaInstanceProvider(int id) throws ClassNotFoundException, SQLException, ParseException {
        InstanceProvider instance = null;
        String date_s;
        SimpleDateFormat dt;
        Date date;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDINSTANCEPROVIDER,INSTANCEPROVIDER,STATUSPROVIDER, IDPROVIDER,"
                + "IDINSTANCE,DATECREATE,DATEUPDATE, TYPEINSTANCE, SONAME, SOTYPE, SOVERSION, SOFAMILY, HWRAM, VOLUMES, CORES, SPEED "
                + "FROM INSTANCEPROVIDER WHERE IDINSTANCEPROVIDER=?"
        );
        stmt.setInt(1, id);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            instance = new InstanceProvider();
            instance.setIdInstanceProvider(resultado.getInt("IDINSTANCEPROVIDER"));
            instance.setInstanceProvider(resultado.getString("INSTANCEPROVIDER"));
            instance.setStatus(resultado.getString("STATUSPROVIDER"));
            instance.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            instance.setIdInstance(resultado.getString("IDINSTANCE"));
            date_s = resultado.getString("DATECREATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instance.setDataCreate(date);
            } else {
                instance.setDataCreate(null);
            }
            date_s = resultado.getString("DATEUPDATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instance.setDataUpdate(date);
            } else {
                instance.setDataUpdate(null);
            }
            instance.setTypeinstance(resultado.getString("TYPEINSTANCE"));
            instance.setSoName(resultado.getString("SONAME"));
            instance.setSoType(resultado.getString("SOTYPE"));
            instance.setSoVersion(resultado.getString("SOVERSION"));
            instance.setSoFamily(resultado.getString("SOFAMILY"));
            instance.setHwRam(resultado.getInt("HWRAM"));
            instance.setVolumes(resultado.getString("VOLUMES"));
            instance.setCores(resultado.getString("CORES"));
            instance.setSpeed(resultado.getString("SPEED"));
        }
        conn.close();
        return instance;
    }

    public ArrayList<String> listaQDTStatusProviderDay() throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateForMySql = "";
        Date data = new Date();
        dateForMySql = sdf.format(data);
        Map<String, String> statusProvider = new HashMap<String, String>();
        Map<String, String> provider = new HashMap<String, String>();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDPROVIDER, STATUSPROVIDER, COUNT(*) AS QUANTIDADE \n"
                + "FROM INSTANCEPROVIDER	\n"
                + "WHERE DATE(DATECHECKED) = ? "
                + "GROUP BY IDPROVIDER, STATUSPROVIDER \n"
                + "ORDER BY IDPROVIDER");

        stmt.setString(1, dateForMySql);
        ResultSet resultado = stmt.executeQuery();
        ArrayList<QtdStatusProvider> lista = new ArrayList<>();
        QtdStatusProvider qtdStatus;
        int count = 0;
        while (resultado.next()) {
            qtdStatus = new QtdStatusProvider();
            qtdStatus.setQuantidade(resultado.getInt("QUANTIDADE"));
            qtdStatus.setStatus(resultado.getString("STATUSPROVIDER"));
            qtdStatus.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            statusProvider.put(resultado.getString("STATUSPROVIDER"), "");
            provider.put(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")).getNome(), "");
            lista.add(qtdStatus);
        }
        conn.close();
        return montaGrafico(lista, statusProvider, provider);
    }

    public ArrayList<QtdStatusProvider> listaQDTStatusProvider(String status) throws ClassNotFoundException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateForMySql = "";
        Date data = new Date();
        dateForMySql = sdf.format(data);
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDPROVIDER, STATUSPROVIDER, COUNT(*) AS QUANTIDADE \n"
                + "FROM INSTANCEPROVIDER	\n"
                + "WHERE STATUSPROVIDER=? "
                + "AND DATE(DATECHECKED) = ?"
                + "GROUP BY IDPROVIDER, STATUSPROVIDER \n"
                + "ORDER BY IDPROVIDER");
        stmt.setString(1, status);
        stmt.setString(2, dateForMySql);
        ResultSet resultado = stmt.executeQuery();
        ArrayList<QtdStatusProvider> lista = new ArrayList<>();
        QtdStatusProvider qtdStatus;
        while (resultado.next()) {
            qtdStatus = new QtdStatusProvider();
            qtdStatus.setQuantidade(resultado.getInt("QUANTIDADE"));
            qtdStatus.setStatus(resultado.getString("STATUSPROVIDER"));
            qtdStatus.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            lista.add(qtdStatus);
        }
        conn.close();
        return lista;
    }

    public ArrayList<QtdStatusProvider> listaQDTStatusProvider(int idProvider) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDPROVIDER, STATUSPROVIDER, COUNT(*) AS QUANTIDADE \n"
                + "FROM INSTANCEPROVIDER	\n"
                + "WHERE IDPROVIDER=?	\n"
                + "GROUP BY IDPROVIDER, STATUSPROVIDER \n"
                + "ORDER BY IDPROVIDER");
        stmt.setInt(1, idProvider);
        ResultSet resultado = stmt.executeQuery();
        ArrayList<QtdStatusProvider> lista = new ArrayList<>();
        QtdStatusProvider qtdStatus;
        while (resultado.next()) {
            qtdStatus = new QtdStatusProvider();
            qtdStatus.setQuantidade(resultado.getInt("QUANTIDADE"));
            qtdStatus.setStatus(resultado.getString("STATUSPROVIDER"));
            qtdStatus.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            lista.add(qtdStatus);
        }
        conn.close();
        return lista;
    }

    public ArrayList<InstanceProvider> listaQDTStatusProviderDay(int idProvider, String status) throws ClassNotFoundException, SQLException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateForMySql = "";
        Date data = new Date();
        dateForMySql = sdf.format(data);
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDINSTANCEPROVIDER,INSTANCEPROVIDER,STATUSPROVIDER, IDPROVIDER,"
                + "IDINSTANCE,DATECREATE,DATEUPDATE, ISCHECKED, DATECHECKED,TYPEINSTANCE, SONAME, SOTYPE, SOVERSION, SOFAMILY, HWRAM, VOLUMES, CORES, SPEED "
                + "FROM INSTANCEPROVIDER WHERE IDPROVIDER=? AND DATE(DATECHECKED) = ? AND STATUSPROVIDER=?");
        stmt.setInt(1, idProvider);
        stmt.setString(2, dateForMySql);
        stmt.setString(3, status);
        ResultSet resultado = stmt.executeQuery();
        ArrayList<InstanceProvider> lista = new ArrayList<>();
        InstanceProvider instance;
        String date_s;
        SimpleDateFormat dt;
        Date date;
        while (resultado.next()) {
            instance = new InstanceProvider();
            instance.setIdInstanceProvider(resultado.getInt("IDINSTANCEPROVIDER"));
            instance.setInstanceProvider(resultado.getString("INSTANCEPROVIDER"));
            instance.setStatus(resultado.getString("STATUSPROVIDER"));
            instance.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            instance.setIdInstance(resultado.getString("IDINSTANCE"));
            date_s = resultado.getString("DATECREATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instance.setDataCreate(date);
            } else {
                instance.setDataCreate(null);
            }
            date_s = resultado.getString("DATEUPDATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instance.setDataUpdate(date);
            } else {
                instance.setDataUpdate(null);
            }
            instance.setIsChecked(resultado.getInt("ISCHECKED"));
            date_s = resultado.getString("DATECHECKED");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instance.setDateChecked(date);
            } else {
                instance.setDateChecked(null);
            }
            if (resultado.getString("TYPEINSTANCE") != null) {
                instance.setTypeinstance(resultado.getString("TYPEINSTANCE"));
            } else {
                instance.setTypeinstance(null);
            }
            if (resultado.getString("SONAME") != null) {
                instance.setSoName(resultado.getString("SONAME"));
            } else {
                instance.setSoName(null);
            }
            if (resultado.getString("SOTYPE") != null) {
                instance.setSoType(resultado.getString("SOTYPE"));
            } else {
                instance.setSoType(null);
            }
            if (resultado.getString("SOVERSION") != null) {
                instance.setSoVersion(resultado.getString("SOVERSION"));
            } else {
                instance.setSoVersion(null);
            }
            if (resultado.getString("SOFAMILY") != null) {
                instance.setSoFamily(resultado.getString("SOFAMILY"));
            } else {
                instance.setSoFamily(null);
            }
            if (resultado.getInt("HWRAM") != 0) {
                instance.setHwRam(resultado.getInt("HWRAM"));
            } else {
                instance.setSoName(null);
            }
            if (resultado.getString("VOLUMES") != null) {
                instance.setVolumes(resultado.getString("VOLUMES"));
            } else {
                instance.setVolumes(null);
            }           
            if (resultado.getString("CORES") != null) {
                instance.setCores(resultado.getString("CORES"));
            } else {
                instance.setCores(null);
            }
            if (resultado.getString("SPEED") != null) {
                instance.setSpeed(resultado.getString("SPEED"));
            } else {
                instance.setSpeed(null);
            }
            lista.add(instance);
        }
        conn.close();
        return lista;
    }

    public ArrayList<InstanceProvider> listaProviderDay(int idProvider) throws ClassNotFoundException, SQLException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateForMySql = "";
        Date data = new Date();
        dateForMySql = sdf.format(data);
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT IDINSTANCEPROVIDER,INSTANCEPROVIDER,STATUSPROVIDER, IDPROVIDER,"
                + "IDINSTANCE,DATECREATE,DATEUPDATE, ISCHECKED, DATECHECKED,TYPEINSTANCE, SONAME, SOTYPE, SOVERSION, SOFAMILY, HWRAM, VOLUMES, CORES, SPEED "
                + "FROM INSTANCEPROVIDER WHERE IDPROVIDER=? AND DATE(DATECHECKED) = ? AND STATUSPROVIDER=? ORDER BY IDPROVIDER");
        stmt.setInt(1, idProvider);
        stmt.setString(2, dateForMySql);
        stmt.setString(3, "RUNNING");
        ResultSet resultado = stmt.executeQuery();
        ArrayList<InstanceProvider> lista = new ArrayList<>();
        InstanceProvider instance;
        String date_s;
        SimpleDateFormat dt;
        Date date;
        while (resultado.next()) {
            instance = new InstanceProvider();
            instance.setIdInstanceProvider(resultado.getInt("IDINSTANCEPROVIDER"));
            instance.setInstanceProvider(resultado.getString("INSTANCEPROVIDER"));
            instance.setStatus(resultado.getString("STATUSPROVIDER"));
            instance.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            instance.setIdInstance(resultado.getString("IDINSTANCE"));
            date_s = resultado.getString("DATECREATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instance.setDataCreate(date);
            } else {
                instance.setDataCreate(null);
            }
            date_s = resultado.getString("DATEUPDATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instance.setDataUpdate(date);
            } else {
                instance.setDataUpdate(null);
            }
            instance.setIsChecked(resultado.getInt("ISCHECKED"));
            date_s = resultado.getString("DATECHECKED");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instance.setDateChecked(date);
            } else {
                instance.setDateChecked(null);
            }
            if (resultado.getString("TYPEINSTANCE") != null) {
                instance.setTypeinstance(resultado.getString("TYPEINSTANCE"));
            } else {
                instance.setTypeinstance(null);
            }
            if (resultado.getString("SONAME") != null) {
                instance.setSoName(resultado.getString("SONAME"));
            } else {
                instance.setSoName(null);
            }
            if (resultado.getString("SOTYPE") != null) {
                instance.setSoType(resultado.getString("SOTYPE"));
            } else {
                instance.setSoType(null);
            }
            if (resultado.getString("SOVERSION") != null) {
                instance.setSoVersion(resultado.getString("SOVERSION"));
            } else {
                instance.setSoVersion(null);
            }
            if (resultado.getString("SOFAMILY") != null) {
                instance.setSoFamily(resultado.getString("SOFAMILY"));
            } else {
                instance.setSoFamily(null);
            }
            if (resultado.getInt("HWRAM") != 0) {
                instance.setHwRam(resultado.getInt("HWRAM"));
            } else {
                instance.setSoName(null);
            }
            if (resultado.getString("VOLUMES") != null) {
                instance.setVolumes(resultado.getString("VOLUMES"));
            } else {
                instance.setVolumes(null);
            }
            if (resultado.getString("CORES") != null) {
                instance.setCores(resultado.getString("CORES"));
            } else {
                instance.setCores(null);
            }
            if (resultado.getString("SPEED") != null) {
                instance.setSpeed(resultado.getString("SPEED"));
            } else {
                instance.setSpeed(null);
            }
            lista.add(instance);
        }
        conn.close();
        return lista;
    }

    public ArrayList<InstanceProvider> listaStatusProvider(String status) throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(""
                + "SELECT * FROM INSTANCEPROVIDER WHERE STATUSPROVIDER=? ORDER BY IDPROVIDER");
        stmt.setString(1, status);
        ResultSet resultado = stmt.executeQuery();
        ArrayList<InstanceProvider> lista = new ArrayList<>();
        InstanceProvider instanceProvider;
        while (resultado.next()) {
            instanceProvider = new InstanceProvider();
            instanceProvider.setIdInstanceProvider(resultado.getInt("IDINSTANCEPROVIDER"));
            instanceProvider.setInstanceProvider(resultado.getString("INSTANCEPROVIDER"));
            instanceProvider.setStatus(resultado.getString("STATUSPROVIDER"));
            instanceProvider.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            lista.add(instanceProvider);
        }
        conn.close();
        return lista;
    }

    public ArrayList<InstanceProvider> listaStatusProvider() throws ClassNotFoundException, SQLException {
        conn = new ConnectionMySql().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT * FROM INSTANCEPROVIDER ORDER BY IDPROVIDER ");
        ArrayList<InstanceProvider> lista = new ArrayList<>();
        InstanceProvider instanceProvider;
        while (resultado.next()) {
            instanceProvider = new InstanceProvider();
            instanceProvider.setIdInstanceProvider(resultado.getInt("IDINSTANCEPROVIDER"));
            instanceProvider.setInstanceProvider(resultado.getString("INSTANCEPROVIDER"));
            instanceProvider.setStatus(resultado.getString("STATUSPROVIDER"));
            instanceProvider.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            lista.add(instanceProvider);
        }
        conn.close();
        return lista;
    }

    public ArrayList<InstanceProvider> listaInstanceProvider(Provider prov) throws ClassNotFoundException, SQLException, ParseException {
        String date_s;
        SimpleDateFormat dt;
        Date date;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(""
                + "SELECT IDINSTANCEPROVIDER,INSTANCEPROVIDER,STATUSPROVIDER, IDPROVIDER, "
                + "IDINSTANCE,DATECREATE,DATEUPDATE,ISCHECKED,TYPEINSTANCE,SONAME, SOTYPE, SOVERSION, SOFAMILY, HWRAM, VOLUMES, CORES, SPEED "
                + "FROM INSTANCEPROVIDER WHERE IDPROVIDER=? ");
        stmt.setInt(1, prov.getId());
        ResultSet resultado = stmt.executeQuery();
        ArrayList<InstanceProvider> lista = new ArrayList<>();
        InstanceProvider instanceProvider;
        while (resultado.next()) {
            instanceProvider = new InstanceProvider();
            instanceProvider.setIdInstanceProvider(resultado.getInt("IDINSTANCEPROVIDER"));
            instanceProvider.setInstanceProvider(resultado.getString("INSTANCEPROVIDER"));
            instanceProvider.setStatus(resultado.getString("STATUSPROVIDER"));
            instanceProvider.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            instanceProvider.setIdInstance(resultado.getString("IDINSTANCE"));
            date_s = resultado.getString("DATECREATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instanceProvider.setDataCreate(date);
            } else {
                instanceProvider.setDataCreate(null);
            }
            date_s = resultado.getString("DATEUPDATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instanceProvider.setDataUpdate(date);
            } else {
                instanceProvider.setDataUpdate(null);
            }
            instanceProvider.setIsChecked(resultado.getInt("ISCHECKED"));
            if (resultado.getString("TYPEINSTANCE") != null) {
                instanceProvider.setTypeinstance(resultado.getString("TYPEINSTANCE"));
            } else {
                instanceProvider.setTypeinstance(null);
            }
            if (resultado.getString("SONAME") != null) {
                instanceProvider.setSoName(resultado.getString("SONAME"));
            } else {
                instanceProvider.setSoName(null);
            }
            if (resultado.getString("SOTYPE") != null) {
                instanceProvider.setSoType(resultado.getString("SOTYPE"));
            } else {
                instanceProvider.setSoType(null);
            }
            if (resultado.getString("SOVERSION") != null) {
                instanceProvider.setSoVersion(resultado.getString("SOVERSION"));
            } else {
                instanceProvider.setSoVersion(null);
            }
            if (resultado.getString("SOFAMILY") != null) {
                instanceProvider.setSoFamily(resultado.getString("SOFAMILY"));
            } else {
                instanceProvider.setSoFamily(null);
            }
            if (resultado.getInt("HWRAM") != 0) {
                instanceProvider.setHwRam(resultado.getInt("HWRAM"));
            } else {
                instanceProvider.setSoName(null);
            }
            if (resultado.getString("VOLUMES") != null) {
                instanceProvider.setVolumes(resultado.getString("VOLUMES"));
            } else {
                instanceProvider.setVolumes(null);
            }
            if (resultado.getString("CORES") != null) {
                instanceProvider.setCores(resultado.getString("CORES"));
            } else {
                instanceProvider.setCores(null);
            }
            if (resultado.getString("SPEED") != null) {
                instanceProvider.setSpeed(resultado.getString("SPEED"));
            } else {
                instanceProvider.setSpeed(null);
            }
            lista.add(instanceProvider);
        }
        conn.close();
        return lista;
    }

    private ArrayList<String> montaGrafico(ArrayList<QtdStatusProvider> list, Map<String, String> statusProvider, Map<String, String> provider) {
        ArrayList<String> linha = new ArrayList<>();
        int count = 0;
        int count1 = 0;
        String dadosGrafico = "[";
        String labels = "[";
        Map<String, String> providerAux = new HashMap<String, String>();
        boolean passei;

        for (String keyprovider : provider.keySet()) {
            providerAux.put(keyprovider, String.valueOf(count));
            count++;
        }
        for (String key : statusProvider.keySet()) {
            dadosGrafico += "{label: \"" + key + "\", data: [";
            for (String keyprovider : providerAux.keySet()) {
                passei = false;
                for (QtdStatusProvider qtd : list) {
                    if (key.equals(qtd.getStatus()) && keyprovider.equals(qtd.getProvider().getNome())) {
                        if (Integer.parseInt(providerAux.get(keyprovider)) > 0) {
                            dadosGrafico += ",";
                        }
                        dadosGrafico += "[" + providerAux.get(keyprovider) + "," + qtd.getQuantidade() + "]";
                        passei = true;
                    }
                }
                if (passei == false) {
                    if (Integer.parseInt(providerAux.get(keyprovider)) > 0) {
                        dadosGrafico += ",";
                    }
                    dadosGrafico += "[" + providerAux.get(keyprovider) + "," + 0.01 + "]";
                }
            }
            if (count1 < statusProvider.size() - 1) {
                dadosGrafico += "], bars: {order: " + (count1 + 1) + "}},";
            } else {
                dadosGrafico += "], bars: {order: " + (count1 + 1) + "}}";
            }
            count1++;
        }
        for (String prov : providerAux.keySet()) {
            if (Integer.parseInt(providerAux.get(prov)) > 0) {
                labels += ",";
            }
            labels += "[" + providerAux.get(prov) + ", \"" + prov + "\"]";
        }

        labels += "]";
        dadosGrafico += "]";
        linha.add(dadosGrafico);
        linha.add(labels);
        return linha;
    }

    public ArrayList<InstanceProvider> listaInstanceProvider(Provider prov, String status) throws ClassNotFoundException, SQLException, ParseException {
        String date_s;
        SimpleDateFormat dt;
        Date date;
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(""
                + "SELECT IDINSTANCEPROVIDER,INSTANCEPROVIDER,STATUSPROVIDER, IDPROVIDER, "
                + "IDINSTANCE,DATECREATE,DATEUPDATE,ISCHECKED,TYPEINSTANCE,SONAME, SOTYPE, SOVERSION, SOFAMILY, HWRAM, VOLUMES, CORES, SPEED "
                + "FROM INSTANCEPROVIDER WHERE IDPROVIDER=? AND STATUSPROVIDER=? ");
        stmt.setInt(1, prov.getId());
        stmt.setString(2, status);
        ResultSet resultado = stmt.executeQuery();
        ArrayList<InstanceProvider> lista = new ArrayList<>();
        InstanceProvider instanceProvider;
        while (resultado.next()) {
            instanceProvider = new InstanceProvider();
            instanceProvider.setIdInstanceProvider(resultado.getInt("IDINSTANCEPROVIDER"));
            instanceProvider.setInstanceProvider(resultado.getString("INSTANCEPROVIDER"));
            instanceProvider.setStatus(resultado.getString("STATUSPROVIDER"));
            instanceProvider.setProvider(new ProviderBD().buscaProvider(resultado.getInt("IDPROVIDER")));
            instanceProvider.setIdInstance(resultado.getString("IDINSTANCE"));
            date_s = resultado.getString("DATECREATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instanceProvider.setDataCreate(date);
            } else {
                instanceProvider.setDataCreate(null);
            }
            date_s = resultado.getString("DATEUPDATE");
            if (date_s != null) {
                dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dt.parse(date_s);
                instanceProvider.setDataUpdate(date);
            } else {
                instanceProvider.setDataUpdate(null);
            }
            instanceProvider.setIsChecked(resultado.getInt("ISCHECKED"));
            instanceProvider.setTypeinstance(resultado.getString("TYPEINSTANCE"));
            instanceProvider.setSoName(resultado.getString("SONAME"));
            instanceProvider.setSoType(resultado.getString("SOTYPE"));
            instanceProvider.setSoVersion(resultado.getString("SOVERSION"));
            instanceProvider.setSoFamily(resultado.getString("SOFAMILY"));
            instanceProvider.setHwRam(resultado.getInt("HWRAM"));
            instanceProvider.setVolumes(resultado.getString("VOLUMES"));
            instanceProvider.setCores(resultado.getString("CORES"));
            instanceProvider.setSpeed(resultado.getString("SPEED"));
            lista.add(instanceProvider);
        }
        conn.close();
        return lista;
    }

    public int totalInstaceProvider(int IdProvider) throws ClassNotFoundException, SQLException {
        int contador = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateForMySql = "";
        Date data = new Date();
        dateForMySql = sdf.format(data);
        conn = new ConnectionMySql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT COUNT(*) AS CONTADOR "
                + "FROM INSTANCEPROVIDER WHERE IDPROVIDER=? AND DATE(DATECHECKED) = ? ");
        stmt.setInt(1, IdProvider);
        stmt.setString(2, dateForMySql);
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            contador = resultado.getInt("CONTADOR");
        }
        conn.close();
        return contador;
    }
}
