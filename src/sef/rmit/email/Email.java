/*
 * @author:
 */

package sef.rmit.email;

import java.util.*;
import javax.mail.*;
import sef.rmit.custom.Graph;
import sef.rmit.custom.Simulation;
import sef.rmit.email.*;
import sef.rmit.util.*;

@SuppressWarnings("unused")
public class Email 
{
	private static final Properties propertyObj = new Properties(); 
	private static Session sessionObj = null;
	
	//Created an Constructor to initialize Authenticator Object
	public Email(String templateName,String userName) throws Exception 
	{
		//Putting all the necessary email connection parameters
        propertyObj.put("mail.smtp.user", ConnectionData.SENDER_EMAIL_USERNAME.getValue());
        propertyObj.put("mail.smtp.host", ConnectionData.EMAIL_SMTP_SERVER.getValue());
        propertyObj.put("mail.smtp.port", ConnectionData.EMAIL_SMTP_SERVER_PORT.getValue());
        propertyObj.put("mail.smtp.socketFactory.port", ConnectionData.EMAIL_SMTP_SERVER_PORT.getValue());
        propertyObj.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        propertyObj.put("mail.smtp.auth", "true");
        propertyObj.put("mail.smtp.starttls.enable", "true");
        //creating an authenticator object before sending an email
        Authenticator auth = new Authenticator();
        sessionObj = Session.getInstance(propertyObj, auth);
	}
	
	/*
	 * 
	 * @parameters: Template Name, User Name and Email Subject
	 * @Throws Exception
	 * @returns True is Email is sent successfully to reciptent else returns false 
	 */
	public static boolean sendEmailToReciptent(String templateName, String userName, String emailSubject, Simulation simulationObj) throws Exception
	{
		try
		{
			Email sendEmailObj = new Email(templateName, userName);
			//Send the email to user
		    Transport.send(EmailTemplate.formTemplate(sessionObj, templateName, userName, emailSubject, simulationObj));
			return true;
		}
		catch(Exception e)
		{
			throw new Exception(e);
		}
		finally
		{
			sessionObj = null;
		}
	}
	
	/*
	 * 
	 * Used to overwrite Authenticator Default java method to give password of Sender's email
	 */
	private class Authenticator extends javax.mail.Authenticator 
	{
		public PasswordAuthentication getPasswordAuthentication() 
		{
			return new PasswordAuthentication(ConnectionData.SENDER_EMAIL_USERNAME.getValue(), 
	            		ConnectionData.SENDER_EMAIL_PASSWORD.getValue());
		}
	}	
}