package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.ProviderDialogControl;
import br.com.monitoranuvem.model.Provider;
import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class ProviderDialog {

    ProviderDialogControl pro;

    public ProviderDialog() {
        pro = new ProviderDialogControl();
    }

    public ArrayList<Provider> getAllProvider() {
        ArrayList<Provider> p = new ArrayList<>();
        for (Provider provider : Provider.values()) {
            p.add(provider);
        }
        return p;
    }

    public boolean connectProvider(Provider p, String login, String password) {
        return pro.connecta(p, login, password);
    }

}
