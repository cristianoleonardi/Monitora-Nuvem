/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Cristiano
 */
public class JavaMailSend {

    private String email;
    private Session session;

    public JavaMailSend(String email, String password) {
        this.email = email;

        //Cria sessao autenticada para envie de email
        //Necessário usuário e senha do provedor SMTP ex.: Gmail
        this.session = Session.getDefaultInstance(getProperties(), new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        //Ativa Debug para session
        session.setDebug(true);
    }

    private Properties getProperties() {
        Properties props = new Properties();

        //Servidor SMTP
        props.put("mail.smtp.host", "smtp.gmail.com");
        //Porta Servidor
        props.put("mail.smtp.socketFactory.port", "465");
        //Habilitar Seguranca SSL
        props.put("nail.smtp.socketFactory.class", "javax.net.SSLSocketFactory");
        //Requer Autenticação
        props.put("mail.smtp.auth", "true");
        //Porta SMTP
        props.put("mail.smtp.port", "587");

        return props;
    }

    public boolean sendEmail(String assunto, String mensagem, String emailDestino) throws MessagingException {
        Message message = new MimeMessage(session);
        //Remetente
        message.setFrom(new InternetAddress(this.email));

        //Destinatario(s), separe varios destinatarios por virgula na string emailDestino
        Address[] toUser = InternetAddress.parse(emailDestino);

        //Monta e-mail
        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject(assunto);
        message.setText(mensagem);

        //Enviar mensagem
        Transport.send(message);
        return true;
    }
}
