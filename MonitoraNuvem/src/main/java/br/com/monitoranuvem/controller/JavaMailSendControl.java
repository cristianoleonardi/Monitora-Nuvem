/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.controller;

import br.com.monitoranuvem.model.JavaMailSend;
import javax.mail.MessagingException;

/**
 *
 * @author Cristiano
 */
public class JavaMailSendControl {

    public boolean sendEmail(String[] destino,String assunto, String mensagem) throws MessagingException{
        return new JavaMailSend().sendMail(destino, assunto, mensagem);
    }
}
