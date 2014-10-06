package br.com.monitoranuvem.model;

import java.util.Date;

public class HistoryProvider {
    private int idHistoryProvider;
    private InstanceProvider instanceProvider;
    private String status;
    private Date dataUpdate;

    public int getIdHistoryProvider() {
        return idHistoryProvider;
    }

    public void setIdHistoryProvider(int idHistoryProvider) {
        this.idHistoryProvider = idHistoryProvider;
    }

    public InstanceProvider getInstanceProvider() {
        return instanceProvider;
    }

    public void setInstanceProvider(InstanceProvider instanceProvider) {
        this.instanceProvider = instanceProvider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(Date dataUpdate) {
        this.dataUpdate = dataUpdate;
    }
}
