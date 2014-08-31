package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.connection.Connection;
import br.com.monitoranuvem.model.Configuration;
import br.com.monitoranuvem.model.Provider;

/**
 *
 * @author Cristiano
 */
public class ProviderDialogControl {

    public ProviderDialogControl() {
        //      dialog = new ProviderDialog();
    }

    public boolean connecta(Provider p, String login, String password) {
        Connection conn = new Connection();
        conn.setLogin(login);
        conn.setPassword(password);
        Configuration.getInstance().setProvider(p);
        Configuration.getInstance().setConnection(conn);
        return true;
    }

}
