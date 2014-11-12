/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

/**
 *
 * @author Cristiano
 */
public class ThreadCost  implements Runnable {
    private int delay;
 
    public ThreadCost(int tempoDelay) {
        delay = tempoDelay;
    }

    @Override
    public void run() {
        try {
            for (;;) {
                
                System.out.println("passei");
                 
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            return;
        }
    }
}
