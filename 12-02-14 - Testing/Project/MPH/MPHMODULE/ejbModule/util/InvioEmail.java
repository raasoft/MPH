package util;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class InvioEmail {



 private static String filePath = "mph/";
 private List<String> settings = new ArrayList<String>(); 
 private String user ="";
 private String psw ="";
 
 
    /*public static void main(String[] args) throws Exception{
       new InvioEmail().test();
    }*/
 
 
    private Properties loadGmailProperties() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", settings.get(0));
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", settings.get(3));
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
        return props;
    }
   
    public void test(String To, String Oggetto, String Contenuto) throws Exception{
        leggiFile();
        this.user = settings.get(1);
        this.psw = settings.get(2);
    	Authenticator auth = new SMTPAuthenticator();
    	Session mailSession=Session.getDefaultInstance(loadGmailProperties(),auth);
  
    	// con questo parametro settato a true sono inseriti
		// nello standar output i log dell'invio mail
		mailSession.setDebug(true);
		Transport transport = mailSession.getTransport();
		MimeMessage message = new MimeMessage(mailSession);
		message.setContent(Contenuto, "text/plain");
		message.setSubject(Oggetto);
		message.setFrom(new InternetAddress("noreply@test.it"));
		message.addRecipients(Message.RecipientType.TO,To); 
		transport.connect();
		System.out.println("Invio....");
		transport.sendMessage(message,
		message.getRecipients(Message.RecipientType.TO));
		transport.close();
	    System.out.println("Inviato....");
    }


    private void leggiFile () throws Exception{
    	File name = new File(filePath +"setting.txt");
  		if (name.isFile()) {
  			try {
  				BufferedReader input = new BufferedReader(new FileReader(name));
	  			StringBuffer buffer = new StringBuffer();
	  			String text;
	  			while ((text = input.readLine()) != null)
	  				settings.add(text);
	  			input.close();
  			} 
  			catch (IOException ioException) {
  			}
  		}
    }
    
    
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = user;
           String password = psw;
           return new PasswordAuthentication(username, password);
        }
    }
}