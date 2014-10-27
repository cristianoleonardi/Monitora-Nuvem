/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import java.util.ArrayList;

/**
 *
 * @author Marcio
 */
public class InstanceType {

    private ArrayList<String> instance;

    public static ArrayList<String> listaTypeInstance() {
        return new InstanceType().lista();
    }

    private ArrayList<String> lista() {
        instance = new ArrayList<>();
        instance.add("m3.medium");
        instance.add("m3.large");
        instance.add("m3.xlarge");
        instance.add("m3.2xlarge");
        instance.add("c3.large");
        instance.add("c3.xlarge");
        instance.add("c3.2xlarge");
        instance.add("c3.4xlarge");
        instance.add("c3.8xlarge");
        instance.add("g2.2xlarge");
        instance.add("r3.large");
        instance.add("r3.xlarge");
        instance.add("r3.2xlarge");
        instance.add("r3.4xlarge");
        instance.add("r3.8xlarge");
        instance.add("i2.xlarge");
        instance.add("i2.2xlarge");
        instance.add("i2.4xlarge");
        instance.add("i2.8xlarge");
        instance.add("hs1.8xlarge");
        instance.add("t1.micro");
        instance.add("m1.small");
        instance.add("m1.medium");
        instance.add("m1.large");
        return instance;
    }
}
