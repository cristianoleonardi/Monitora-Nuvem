package br.com.monitoranuvem.model;

import br.com.monitoranuvem.connection.Credentials;

/**
 *
 * @author Marcio
 */
public class Configuration {

    private static Configuration instance;
    private Provider provider;
    private Credentials connection;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public Credentials getConnection() {
        return connection;
    }

    public void setConnection(Credentials connection) {
        this.connection = connection;
    }
}
