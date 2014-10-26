/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.model;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Cristiano
 */
public class JavaMailSend {

    private String mailSMTPServer;
    private String mailSMTPServerPort;
    private final String from = "tccmonitoranuvem@gmail.com";
    private final String user = "tccmonitoranuvem";
    private final String password = "899ec9084d82eccd00603e2881eab984";

    public JavaMailSend() { //Para o GMAIL   
        mailSMTPServer = "smtp.gmail.com";
        mailSMTPServerPort = "465";
    }

    public JavaMailSend(String mailSMTPServer, String mailSMTPServerPort) { //Para outro Servidor  
        this.mailSMTPServer = mailSMTPServer;
        this.mailSMTPServerPort = mailSMTPServerPort;
    }

    public boolean sendMail(String[] to, String subject, String message) throws AddressException, MessagingException {

        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP  
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL  
        props.put("mail.smtp.auth", "true"); //ativa autenticacao  
        props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)  
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", mailSMTPServerPort); //porta  
        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket  
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        //Cria um autenticador que sera usado a seguir  
        SimpleAuth auth = null;
        auth = new SimpleAuth(user, password);

        //Session - objeto que ira realizar a conexão com o servidor  
        /*Como há necessidade de autenticação é criada uma autenticacao que 
         * é responsavel por solicitar e retornar o usuário e senha para  
         * autenticação */
        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(false); //Habilita o LOG das ações executadas durante o envio do email  

        //Objeto que contém a mensagem  
        Message msg = new MimeMessage(session);
        //Setando o destinatário  

        for (int i = 0; i < to.length; i++) {
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
        }

//        //Setando o destinatário quando for 1 destinatario 
//        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

        //Setando a origem do email  
        msg.setFrom(new InternetAddress(from));
        //Setando o assunto  
        msg.setSubject(subject);
        //Setando o conteúdo/corpo do email  
        msg.setContent(message, "text/html");

        //Objeto encarregado de enviar os dados para o email  
        Transport tr;
        tr = session.getTransport("smtp"); //define smtp para transporte  
            /* 
         *  1 - define o servidor smtp 
         *  2 - seu nome de usuario do gmail 
         *  3 - sua senha do gmail 
         */
        tr.connect(mailSMTPServer, user, password);
        msg.saveChanges(); // don't forget this  
        //envio da mensagem  
        tr.sendMessage(msg, msg.getAllRecipients());
        tr.close();
        return true;
    }
}

//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp  
class SimpleAuth extends Authenticator {

    public String username = null;
    public String password = null;

    public SimpleAuth(String user, String pwd) {
        username = user;
        password = pwd;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
