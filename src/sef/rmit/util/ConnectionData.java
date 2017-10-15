package sef.rmit.util;

 /*
 * 
 * @Store String Constant Values
 * @Return string values
 */

public enum ConnectionData 
{
	DB_USERNAME("s3620636"),
	DB_PASSWORD("rBMhL69y"),
	DATABASE("GENERAL"),
	SERVER("localhost"),
	DB_PORT("9922"),
	SENDER_EMAIL_USERNAME("roadtrafficsimulation@gmail.com"),
	SENDER_EMAIL_PASSWORD("traffic123"),
	EMAIL_SMTP_SERVER("smtp.gmail.com"),
	EMAIL_SMTP_SERVER_PORT("587");
	
	private String value;

	ConnectionData(final String value) 
	{
		this.value = value; 
	}
	
    public String getValue() 
    {
    	return value; 
    }
}
