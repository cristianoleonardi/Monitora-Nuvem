package br.com.monitoranuvem.model;

/**
 *
 * @author Marcio
 */
public class Configuration {

    private static Configuration instance;
    private Provider provider;
    private Credentials credentials;

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

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
