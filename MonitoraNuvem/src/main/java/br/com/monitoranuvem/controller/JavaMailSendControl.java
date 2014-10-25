/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.JavaMailSend;

/**
 *
 * @author Cristiano
 */
public class JavaMailSendControl {

    private JavaMailSend jms;
    
    public JavaMailSendControl() {
        jms = new JavaMailSend("tccmonitoranuvem@gmail.com", "899ec9084d82eccd00603e2881eab984");
    }
    
    public boolean sendEmail(String assunto, String mensagem, String destino) throws Exception{
        if(jms.sendEmail(assunto, mensagem, destino)){
            return true;
        }
        return false;
    }
    
}
