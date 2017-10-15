/*
 * @author:
 */

package sef.rmit.email;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import sef.rmit.db.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import sef.rmit.util.*;
import sef.rmit.custom.*;

@SuppressWarnings("serial")
public class EmailTemplate 
{
	//Used to Map DB Column Values to Object Attributes
	private static final ConcurrentHashMap<String, String> TEMPLATE_MAPPER = new ConcurrentHashMap<String, String>() {{
	    put("EMAIL_TEMPLATE","emailTemplate");
	    put("EMAIL_TEMPLATE_ID","emailTemplateID");
	}};
	
	public String emailTemplate;
	public String emailTemplateID;
	
	//Get Email Template from Object
	public String getEmailTemplate()
	{
		return emailTemplate;
	}
	
	/*
	 * 
	 * @parameter: Template Name from Template ENUM
	 * @Throws Exception
	 * @returns entire HTML Template from DB else NULL
	 */
	public static String getTemplate(String templateName) throws Exception
	{
		try
		{
			//Get the email template based on ID from EMAIL_TEMPLATE table in DB
			String queryText = Query.GET_EMAIL_TEMPLATE+"WHERE EMAIL_TEMPLATE_ID = '"+templateName+"'";
			EmailTemplate emailTempObj = (EmailTemplate) DBConnect.getQuery(queryText, TEMPLATE_MAPPER, EmailTemplate.class).get(0);
		    return emailTempObj.getEmailTemplate();
		}
		catch(Exception e)
		{
		    return null;
		}
	}
	
	/*
	 * 
	 * @Parameters: Email Session Object, TemplateName, Username and Email Subject
	 * @Returns Entire HTML Message with Actual Values of User
	 * @throws Exception
	 * 
	 */
	public static Message formTemplate(Session sessionObj, String templateName, String userName, String emailSubject, Simulation simulationObj) throws Exception
	{
		if(Util.isNullorEmpty(userName))
		{
			throw new Exception("Username is Empty");
		}
		else
		{
			//Get the User Object from USERS Table in DB
			User userObj = (User) User.getUserDetails(userName);
			if(userObj != null)
			{
				//Get the Email Template in HTML Format from EMAIL_TEMPLATE table in db
				String emailTemplate = getTemplate(templateName);
				if(!Util.isNullorEmpty(emailTemplate))
				{
					//Form the HTML Data to be sent to the user
					MimeMessage messageObj = new MimeMessage(sessionObj);
					messageObj.setSubject(emailSubject);
					messageObj.setFrom(new InternetAddress(ConnectionData.SENDER_EMAIL_USERNAME.getValue()));	
					
					if(emailTemplate.contains("%USERNAME%") && !Util.isNullorEmpty(userObj.getUsername()))
					{
						emailTemplate  = emailTemplate.replaceAll("%USERNAME%", userObj.getUsername());
					}
					if(emailTemplate.contains("%PASSWORD%") && !Util.isNullorEmpty(userObj.getPassword()))
					{
						emailTemplate  = emailTemplate.replaceAll("%PASSWORD%", userObj.getPassword());
					}
					if(emailTemplate.contains("%FAMILYNAME%") && !Util.isNullorEmpty(userObj.getFamilyName()))
					{
						emailTemplate  = emailTemplate.replaceAll("%FAMILYNAME%", userObj.getFamilyName());
					}
					if(emailTemplate.contains("%GIVENNAME%") && !Util.isNullorEmpty(userObj.getGivenName()))
					{
						emailTemplate  = emailTemplate.replaceAll("%GIVENNAME%", userObj.getGivenName());
					}
					if(!Util.isNullorEmpty(userObj.getEmailAddress()))
					{
						String userEmailAddress = userObj.getEmailAddress();
						messageObj.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmailAddress));
						if(emailTemplate.contains("%EMAILADDRESS%"))
						{
							emailTemplate  = emailTemplate.replaceAll("%EMAILADDRESS%", userEmailAddress);
						}
					}
					else
					{
						throw new Exception("Receiptent Email Address is Mandatory");
					}
					if(emailTemplate.contains("%ROLE%") && !Util.isNullorEmpty(userObj.getUserRole()))
					{
						emailTemplate  = emailTemplate.replaceAll("%ROLE%", userObj.getUserRole());
					}
					if(emailTemplate.contains("%DATE%"))
					{
						emailTemplate  = emailTemplate.replaceAll("%DATE%", new Date().toString().toString());
					}
					if(simulationObj != null)
					{
						if(emailTemplate.contains("%GRAPH_CREATED_DATE%"))
						{
							emailTemplate  = emailTemplate.replaceAll("%GRAPH_CREATED_DATE%",simulationObj.getSimulationStartDate().toString());
						}
					}
					messageObj.setContent(emailTemplate, "text/html" );
					return messageObj;
				}
				else
				{
					return null;
				}
			}
			else
			{
				throw new Exception("Username does not exists in DB.");
			}	


		}
	}
}
