package application.controller;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendMail {
	
		 
	public void inviaMail(String to, String data, String nome, String cognome ) {
	  
		 try {
	            HtmlEmail email = new HtmlEmail();
	            String user = "gamahospital.noreply@gmail.com";
	            String pwd = "Venus1234";
	            email.setSmtpPort(587);
	            email.setAuthenticator(new DefaultAuthenticator(user, pwd));
	            email.setDebug(true);
	            email.setHostName("smtp.gmail.com");
	            email.getMailSession().getProperties().put("mail.smtps.auth", "true");
	            email.getMailSession().getProperties().put("mail.debug", "true");
	            email.getMailSession().getProperties().put("mail.smtps.port", "587");
	            email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", "587");
	            email.getMailSession().getProperties().put("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	            email.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");
	            email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");
	            email.addTo(to, "Nome");
	            email.setFrom(user, "Me");
	            email.setSubject("Gama Hospital: conferma prenotazione");
	            URL url = new URL("https://www.mattepuffo.com/blog/images/logo.png");
	            //String cid = email.embed(url, "Apache logo");
	            email.setHtmlMsg("Gentilissimo " + cognome + " " + nome + ", le inviamo la mail in oggetto per "
	    				   + " ricordarle di confermare, attraverso la nostra applicazione nella sessione 'Gestisci Prenotazione', la prestazione sanitaria prenotata per " + data
	    				   + "\n" + "Cordiali Saluti lo staff");
	            email.setTextMsg("Gentilissimo " + cognome + " " + nome + ", le inviamo la mail in oggetto per "
	    				   + " ricordarle di confermare, attraverso la nostra applicazione nella sessione 'Gestisci Prenotazione', la prestazione sanitaria prenotata per " + data
	    				   + "\n" + "Cordiali Saluti lo staff");
	            email.send();
 

    		   System.out.println("Sent message successfully....");

    	      } catch (EmailException | MalformedURLException ex) {
    	            System.out.println(ex.getMessage());
    	        }
	}

}
