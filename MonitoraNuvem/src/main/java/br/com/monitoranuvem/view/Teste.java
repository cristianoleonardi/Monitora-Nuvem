/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.DashboardControl;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author Cristiano
 */
public class Teste {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, InterruptedException {
        DashboardControl d = DashboardControl.getDashboardControl();
        System.out.println(d);
        d.startThread();
        System.out.println(d.statusThreadAlerts());
        System.out.println(d.statusThreadAmazon());
        System.out.println(d.statusThreadOpen());
        d.stopThread();
        Thread.sleep(70000);
        System.out.println(d.statusThreadAlerts());
        System.out.println(d.statusThreadAmazon());
        System.out.println(d.statusThreadOpen());
//        System.out.println(new InstanceProviderBD().totalInstaceProvider(1));
//        System.out.println(new InstanceProviderBD().totalInstaceProvider(3));
    }

}
