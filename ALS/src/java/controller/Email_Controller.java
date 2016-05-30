/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author DemiXsoft
 */
public class Email_Controller {

    
     // Send Email
    public static void email( String content, String toEmail){
        
              String SMTP_HOST_NAME = "smtp.gmail.com";  
              String SMTP_PORT = "587";   
              
              String SMTP_FROM_ADDRESS = "chronic.care.solution@gmail.com";  
              String SMTP_TO_ADDRESS = toEmail;  
              String subject="Your Password";  
           
              Properties props = new Properties();  
  
              props.put("mail.smtp.host", SMTP_HOST_NAME);  
              props.put("mail.smtp.auth", "true");  
              props.put("mail.debug", "true");  
              props.put("mail.smtp.starttls.enable", "true");
              props.put("mail.smtp.port", SMTP_PORT );  
              Session session = Session.getInstance(props,new javax.mail.Authenticator()  
                {   
                    @Override   
                    protected javax.mail.PasswordAuthentication   
                    getPasswordAuthentication()   
                {return new javax.mail.PasswordAuthentication("chronic.care.solution@gmail.com", "chronic187");}}
                      );  
              try{  
              
              Message msg = new MimeMessage(session);  
          
            
              msg.setFrom(new InternetAddress(SMTP_FROM_ADDRESS));  
              // create the message part   
              MimeBodyPart messageBodyPart = new MimeBodyPart();  
              //fill message  
              messageBodyPart.setText(content);  
              Multipart multipart = new MimeMultipart();  
              multipart.addBodyPart(messageBodyPart);  
          
              //Put parts in message  
              msg.setContent(multipart);  
           
  
              msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(SMTP_TO_ADDRESS));  
  
              msg.setSubject(subject);  
            //msg.setContent(content, "text/plain");  
  
              Transport.send(msg);  
              System.out.println("success........");  
              }  
              catch(Exception e){  
                  e.printStackTrace();        
              } 
        
    }
    
}
