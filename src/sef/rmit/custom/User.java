/*
 * @author:
 */

package sef.rmit.custom;

import java.util.concurrent.ConcurrentHashMap;
import sef.rmit.db.*;
import sef.rmit.util.*;
import sef.rmit.email.*;

@SuppressWarnings({"serial","unused"})
public class User 
{
	//HashMap to map the DB Column values to the variable names
	private static final ConcurrentHashMap<String, String> USER_MAPPER = new ConcurrentHashMap<String, String>() {{
	    put("USERNAME","userName");
	    put("PASSWORD","password");
	    put("GIVENNAME","givenName");
	    put("FAMILYNAME","familyName");
	    put("EMAIL","emailAddress");
	    put("ROLE","role");
	    put("CREATED_USER","createdUser");
	    put("CREATED_DATE","createdDate");
	    put("LAST_MODIFIED_USER","lastModifiedUser");
	    put("LAST_MODIFIED_DATE","lastModifiedDate");
	}};
	
	private String userName;
	private String password;
	private String givenName;
	private String familyName;
	private String emailAddress;
	private String role;
	private String createdUser;
	private String createdDate;
	private String lastModifiedUser;
	private String lastModifiedDate;

	//Retrieve User Name from Object
	public String getUsername()
	{
		return userName;
	}
	//Retrieve Password from Object
	public String getPassword()
	{
		return password;
	}
	//Retrieve Given Name from Object
	public String getGivenName()
	{
		return givenName;
	}
	//Retrieve Family Name from Object
	public String getFamilyName()
	{
		return familyName;
	}
	//Retrieve Email Address from Object
	public String getEmailAddress()
	{
		return emailAddress;
	}
	//Retrieve User Role from Object
	public String getUserRole()
	{
		return role;
	}
	//Get the created User from Object
	public String getCreatedUser()
	{
		return createdUser;
	}
	//Get the last modified user from Object
	public String getLastModifiedUser()
	{
		return lastModifiedUser;
	}
	
	//Set User Name in Object
	public void setUsername(String name)
	{
		this.userName = name;
	}
	//Set Password in Object
	public void setPassword(String passWord)
	{
		this.password = passWord;
	}
	//Set Given Name in Object
	public void setGivenName(String givenname)
	{
		this.givenName = givenname;
	}
	//Set Family Name in Object
	public void setFamilyName(String familyname)
	{
		this.familyName = familyname;
	}
	//Set Email Address in Object
	public void setEmailAddress(String emailaddr)
	{
		this.emailAddress = emailaddr;
	}
	//Set User Role in Object
	public void setUserRole(String userRole)
	{
		this.role = userRole;
	}
	//Set the Created User in object
	public void setCreatedUser(String createduser)
	{
		this.createdUser = createduser;
	}
	//Set the last modified User in object
	public void setLastModifiedUser(String modifieduser)
	{
		this.lastModifiedUser = modifieduser;
	}
	
	/*
	 * 
	 * @param Username & Password
	 * @return true if user exists in DB and Password is Valid else returns false
	 * @throws Exception
	 */
	public static boolean validateUser(String userName, String password) throws Exception
	{
		if(Util.isNullorEmpty(userName) && Util.isNullorEmpty(password))
		{
			throw new Exception("Mandatory Parameters are missing.");
		}
		else
		{		
			String queryText = Query.GET_LOGIN_DETAILS_USER;
			queryText += "WHERE UPPER(USERNAME) = '"+userName.toUpperCase()+"' AND PASSWORD='"+password+"'";
			//System.out.println(queryText);
			if((User) DBConnect.getQuery(queryText, USER_MAPPER ,User.class).get(0) != null)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	/*
	 * @author: Chris
	 * @parameters: UserName
	 * @returns User Object of particular user
	 * @throws Exception
	 */
	public static User getUserDetails(String userName)
	{
		try
		{
			String queryText = Query.GET_USER+"WHERE USERNAME='"+userName+"'";
			//System.out.println(queryText);
			return (User) DBConnect.getQuery(queryText,USER_MAPPER, User.class).get(0);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/*
	 * 
	 * @param Username,Password...etc...
	 * @return true if user inserted/Updated in DB else returns false
	 */
	public static boolean insertUser(User userObj) throws Exception
	{
		boolean userInserted = false;
		if(userObj != null)
		{
			if(Util.isNullorEmpty(userObj.getUsername()) || Util.isNullorEmpty(userObj.getPassword()) 
					|| Util.isNullorEmpty(userObj.getGivenName())
					|| Util.isNullorEmpty(userObj.getFamilyName()) || Util.isNullorEmpty(userObj.getCreatedUser()))
			{
				throw new Exception("Mandatory Parameters are missing.");
			}

			String queryText = Query.INSERT_USER;
			queryText += "'"+userObj.getUsername()+"','"+userObj.getPassword()+"','"+userObj.getGivenName()+"','"+userObj.getFamilyName()+"','"+userObj.getCreatedUser()+"',sysdate "
					+ ",'"+userObj.getLastModifiedUser()+"',sysdate ";
			if(!Util.isNullorEmpty(userObj.getEmailAddress()))
			{
				queryText += ",'"+userObj.getEmailAddress()+"'";
			}
			else
			{
				queryText += ",''";
			}
			if(!Util.isNullorEmpty(userObj.getUserRole()) && userObj.getUserRole().toLowerCase().contains("admin"))
			{
				queryText += ",'ADMIN')";
			}
			else
			{
				queryText += ",'USER')";
			}
			//System.out.println(queryText);
			userInserted = DBConnect.insertQuery(queryText);
			if(userInserted)
			{
				//Send Email to the registered User
				Email.sendEmailToReciptent(TemplateData.REGISTER_TEMPLATE.getValue(), userObj.getUsername(), "Traffic Simulator Registration", null);
			}
			return userInserted;
		}
		else
		{
			return userInserted;
		}
	}
	
	/*
	 * @author: Chris
	 * @parameters: User Object
	 * @throws Exception
	 * @function: Update the user details in DB
	 * @returns true if user updated successfully else returns false
	 */
	public static boolean updateUser(User userObj) throws Exception
	{
		if(Util.isNullorEmpty(userObj.getGivenName()) || Util.isNullorEmpty(userObj.getFamilyName())
				|| Util.isNullorEmpty(userObj.getEmailAddress()) || Util.isNullorEmpty(userObj.getPassword())
				|| Util.isNullorEmpty(userObj.getUsername()))
		{
			throw new Exception("Mandatory Parameters are Missing!");
		}
		else
		{
			String queryText = Query.UPDATE_USER;
			queryText += "GIVENNAME ='"+userObj.getGivenName()+"', FAMILYNAME ='"+userObj.getFamilyName()+"'"
						+ ",EMAIL='"+userObj.getEmailAddress()+"',PASSWORD ='"+userObj.getPassword()+"' "
						+ "WHERE USERNAME ='"+userObj.getUsername()+"'";
			return DBConnect.insertQuery(queryText);
		}
	}
	
	/*
	 * @author: Chris
	 * @parameters: NULL
	 * @returns true if DB Connection Established successfully else returns false
	 * 
	 */
	public static boolean isDBConnectionEstablished()
	{
		try
		{
			String queryText = Query.GET_USER+"WHERE USERNAME = 'ADMIN'";
			User usrObj = (User) DBConnect.getQuery(queryText,USER_MAPPER, User.class).get(0);
			if(!Util.isNullorEmpty(usrObj.getUsername()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
		
}
