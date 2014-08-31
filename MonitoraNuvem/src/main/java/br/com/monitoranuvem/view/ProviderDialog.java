package br.com.monitoranuvem.view;

import br.com.monitoranuvem.model.Provider;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ProviderDialog {

    public ProviderDialog() {

    }

    public ArrayList<Provider> getAllProvider() {
        ArrayList<Provider> p = new ArrayList<>();
        for (Provider provider : Provider.values()) {
            p.add(provider);
        }
        return p;
    }

}
