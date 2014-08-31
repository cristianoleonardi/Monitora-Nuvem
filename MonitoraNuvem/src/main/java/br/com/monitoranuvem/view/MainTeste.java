/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.monitoranuvem.view;

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
        for (int i = 0; i < p.getAllProvider().size(); i++) {
            System.out.println(p.getAllProvider().get(i));
        }
       
    }
}
