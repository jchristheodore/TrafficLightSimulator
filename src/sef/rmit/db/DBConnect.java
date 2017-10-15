/*
 * @author:
 */

package sef.rmit.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import sef.rmit.util.*;

import sef.rmit.util.ConnectionData;

@SuppressWarnings({"rawtypes","unchecked"})
public class DBConnect 
{
	//Stores Global Connection Object
	private static Connection conObj;
		
	/*
	 * Initializes DB Connection Object
	 * Stores Connection Data
	 * Executed on start of Class Methods
	 */
	private static void initializeConnection() throws Exception
	{
		if(conObj == null)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		    Driver driverObj = new oracle.jdbc.driver.OracleDriver();
		    DriverManager.registerDriver(driverObj);
		    String oracleJdbcUrl = "jdbc:oracle:thin:@" + ConnectionData.SERVER.getValue() + ":" + ConnectionData.DB_PORT.getValue() 
		    				+ ":" + ConnectionData.DATABASE.getValue();
		    conObj = DriverManager.getConnection(oracleJdbcUrl, ConnectionData.DB_USERNAME.getValue(), 
		    				ConnectionData.DB_PASSWORD.getValue());
		}
	}
	
	/*
	 * 
	 * @param Query to be executed
	 * @return Arraylist of Class Objects
	 * @throws Exception if connection object is NULL
	 */

	public static ArrayList<Object> getQuery(String queryText, ConcurrentHashMap columnMapper, Class className) throws Exception
	{
		//Initialize DB Connection Object
		initializeConnection();
		//Execute query via Connection Object
	    Statement statement = conObj.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
	    //Get the resultSet after query execution
	    ResultSet resultObj = statement.executeQuery(queryText);
	    ResultSetMetaData resultMeta = resultObj.getMetaData();
	    //Get the total number of columns in the table from  the resultset object
	    int columns = resultMeta.getColumnCount();
	    //Create an arraylist of class object
		ArrayList<Object> allDbObjects = new ArrayList();
		//create an object for each class
		Object object = Class.forName(className.getName().toString()).getConstructor().newInstance();
		while(resultObj.next())
		{
			for(int incre = 1; incre <= columns; incre++)
		   	{
				//Set the value of the result to the field in the class
				String columnName = resultMeta.getColumnName(incre);
				Field field = className.getDeclaredField(columnMapper.get(columnName).toString());
				field.setAccessible(true);
				if(!Util.isNullorEmpty(resultObj.getString(columnName)))
				{
					field.set(object, resultObj.getString(columnName));
				}
		   	}
			allDbObjects.add(object);
			//intialize a new object for the class for every iteration
			object = Class.forName(className.getName().toString()).getConstructor().newInstance();
		}
		return allDbObjects;
	}
	
	/*
	 * 
	 * @param: Query to be executed
	 * @return true if data is inserted else false
	 * @throws Exception if connection object is NULL
	 */
	public static boolean insertQuery(String queryText) throws Exception
	{
		//Initialize DB Connection Object
		initializeConnection();
		//Execute the query via connection obj
		PreparedStatement ps = conObj.prepareStatement(queryText);
		if(ps.executeUpdate() == 1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

}
