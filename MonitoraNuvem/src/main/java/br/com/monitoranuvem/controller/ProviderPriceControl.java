package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderPrice;
import br.com.monitoranuvem.model.ProviderPriceBD;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Cristiano
 */
public class ProviderPriceControl {
    
    public boolean criarProviderPrice(String instanceType, Double price, int provider) throws ClassNotFoundException, SQLException {
        ProviderControl pc = new ProviderControl();
        Provider p = pc.buscaProvider(provider);
        
        ProviderPrice pp = new ProviderPrice(p, instanceType, price);
                
        return new ProviderPriceBD().criarProviderPrice(pp);
    }

    public ProviderPrice buscaProviderPrice(int id) throws ClassNotFoundException, SQLException {
        return new ProviderPriceBD().buscaProviderPrice(id);
    }
    
    public ArrayList<ProviderPrice> listaProviderPrice() throws ClassNotFoundException, SQLException {
        return new ProviderPriceBD().listaProviderPrice();
    }

    public boolean deletaProviderPrice(int id) throws ClassNotFoundException, SQLException {
        ProviderPrice pp = this.buscaProviderPrice(id);
        
        return new ProviderPriceBD().deletaProviderPrice(pp);
    }

    public boolean atualizaProviderPrice(int id, int provider, String instanceType, Double price) throws ClassNotFoundException, SQLException {
        ProviderControl pc = new ProviderControl();
        Provider p = pc.buscaProvider(provider);
        
        ProviderPrice old = this.buscaProviderPrice(id);
        old.setProvider(p);
        old.setInstanceType(instanceType);
        old.setPrice(price);
                
        return new ProviderPriceBD().atualizaProviderPrice(old);
    }
}
