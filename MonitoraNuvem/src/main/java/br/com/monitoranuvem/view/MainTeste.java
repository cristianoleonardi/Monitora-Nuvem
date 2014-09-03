/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.view;

import br.com.monitoranuvem.model.Provider;
import javax.swing.JOptionPane;

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
        JOptionPane.showMessageDialog(null,"Pra ti não esquecer quem é FODA no bagulho!");
        try {
            if (p.connectProvider(Provider.AMAZON, "", "")) {
            }

        } catch (Exception e) {
            System.out.println("teste");
        }
    }
}
