/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

/**
 *
 * @author Marcio
 */
public enum IntanceTypeEnum {

    M3MEDIUM("m3.medium"), M3LARGE("m3.large"), M3XLARGE("m3.xlarge"),
    M32XLARGE("m3.2xlarge"), C3LARGE("c3.large"), C3XLARGE("c3.xlarge"),
    C32XLARGE("c3.2xlarge"), C34XLARGE("c3.4xlarge"), C38XLARGE("c3.8xlarge"),
    G22XLARGE("g2.2xlarge"), R3LARGE("r3.large"), R3XLARGE("r3.xlarge"),
    R32XLARGE("r3.2xlarge"), R34XLARGE("r3.4xlarge"), R38XLARGE("r3.8xlarge"),
    I2XLARGE("i2.xlarge"), I22XLARGE("i2.2xlarge"), I24XLARGE("i2.4xlarge"),
    I28XLARGE("i2.8xlarge"), HS18XLARGE("hs1.8xlarge"), T1MICRO("t1.micro"),
    M1SMALL("m1.small"), M1MEDIUM("m1.medium"), M1LARGE("m1.large");

    private String nameType;

    private IntanceTypeEnum(String name) {
        this.nameType = name;
    }

    public String getNameType() {
        return nameType;
    }
}
