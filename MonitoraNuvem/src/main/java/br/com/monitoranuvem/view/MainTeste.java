/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.view;

import br.com.monitoranuvem.model.Provider;

/**
 *
 * @author Marcio
 */
/**
 *
 * Classe para teste durante o projeto
 */
public class MainTeste {

    public static void main(String[] args) {
        ProviderDialog p = new ProviderDialog();
//        for (int i = 0; i < p.getAllProvider().size(); i++) {
//            System.out.println(p.getAllProvider().get(i));
//        }
        try {
            if (p.connectProvider(Provider.AMAZON, "AKIAI57KBUTAC4I3RVCQ", "NtBOtdX+dX0Qf51xo1PlU92laHn7fs/6OyVvaYjW")) {
                System.out.println("AMAZON OK");
            }

        } catch (Exception e) {
            System.out.println("teste");
        }
    }
}
