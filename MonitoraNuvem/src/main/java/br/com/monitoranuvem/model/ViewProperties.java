/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Marcio
 */
public class ViewProperties {
    private String acessKey;
    private String secretKey;

    public ViewProperties() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./src/main/java/br/com/monitoranuvem/properties/user.properties");
        props.load(file);
        this.acessKey = props.getProperty("acessKey");
        this.secretKey = props.getProperty("secretKey");
    }

    public String getAcessKey() {
        return acessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
