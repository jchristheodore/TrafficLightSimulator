/*
 * @author:
 */

package sef.rmit.custom;

import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import sef.rmit.db.DBConnect;
import sef.rmit.db.Query;
import sef.rmit.util.Util;

@SuppressWarnings({"serial","rawtypes"})
public class Graph 
{
	//Database column to String Mapper
	private static final ConcurrentHashMap<String, String> GRAPH_MAPPER = new ConcurrentHashMap<String, String>() {{
		put("ADMIN_GRID_ID","adminGridID");
		put("LIGHT_TIMINGS","lightTimings");
		put("CREATED_USER","createdUser");
		put("CREATED_DATE","createdDate");
		put("LAST_MODIFIED_USER","lastModifiedUser");
		put("LAST_MODIFIED_DATE","lastModifiedDate");
		put("USER_COUNT","totalGraphCount");
		put("LIGHT1_TIME","light1Time");
		put("LIGHT2_TIME","light2Time");
		put("LIGHT3_TIME","light3Time");
		put("LIGHT4_TIME","light4Time");
		put("LIGHT5_TIME","light5Time");
		put("LIGHT6_TIME","light6Time");
		put("LIGHT7_TIME","light7Time");
		put("LIGHT8_TIME","light8Time");
		put("LIGHT9_TIME","light9Time");
		put("LIGHT10_TIME","light10Time");
		put("LIGHT11_TIME","light11Time");
		put("LIGHT12_TIME","light12Time");
		put("LIGHT13_TIME","light13Time");
		put("LIGHT14_TIME","light14Time");
		put("LIGHT15_TIME","light15Time");
		put("LIGHT16_TIME","light16Time");
		put("LIGHT17_TIME","light17Time");
		put("LIGHT18_TIME","light18Time");
		put("ROAD1_SIDE1_CARS","road1Side1Cars");
		put("ROAD1_SIDE2_CARS","road1Side2Cars");
		put("ROAD2_SIDE1_CARS","road2Side1Cars");
		put("ROAD2_SIDE2_CARS","road2Side2Cars");
		put("ROAD3_SIDE1_CARS","road3Side1Cars");
		put("ROAD3_SIDE2_CARS","road3Side2Cars");
		put("ROAD4_SIDE1_CARS","road4Side1Cars");
		put("ROAD4_SIDE2_CARS","road4Side2Cars");
		put("ROAD5_SIDE1_CARS","road5Side1Cars");
		put("ROAD5_SIDE2_CARS","road5Side2Cars");
		put("ROAD6_SIDE1_CARS","road6Side1Cars");
		put("ROAD6_SIDE2_CARS","road6Side2Cars");
		put("GRAPH_ID","graphID");
		put("GRID_NAME","gridName");
		
	}};
	
	private String graphID;
	private String adminGridID;
	private String createdUser;
	private String createdDate;
	private String lastModifiedUser;
	private String lastModifiedDate;
	private String totalGraphCount;
	private String light1Time;
	private String light2Time;
	private String light3Time;
	private String light4Time;
	private String light5Time;
	private String light6Time;
	private String light7Time;
	private String light8Time;
	private String light9Time;
	private String light10Time;
	private String light11Time;
	private String light12Time;
	private String light13Time;
	private String light14Time;
	private String light15Time;
	private String light16Time;
	private String light17Time;
	private String light18Time;
	private String road1Side1Cars;
	private String road1Side2Cars;
	private String road2Side1Cars;
	private String road2Side2Cars;
	private String road3Side1Cars;
	private String road3Side2Cars;
	private String road4Side1Cars;
	private String road4Side2Cars;
	private String road5Side1Cars;
	private String road5Side2Cars;
	private String road6Side1Cars;
	private String road6Side2Cars;
	private String gridName;
	
	//Get the traffic light1 time from object
	public double getLight1Time() 
	{
		return Double.parseDouble(light1Time);
	}

	//Set the traffic light1 time in Object
	public void setLight1Time(Double light1Time) 
	{
		this.light1Time = light1Time.toString();
	}

	//Get the traffic light2 time from object
	public double getLight2Time() 
	{
		return Double.parseDouble(light2Time);
	}

	//Set the traffic light2 time in object
	public void setLight2Time(Double light2Time) 
	{
		this.light2Time = light2Time.toString();
	}

	//Get the traffic light3 time from object
	public double getLight3Time() 
	{
		return Double.parseDouble(light3Time);
	}
	//Set the traffic light3 time in object
	public void setLight3Time(Double light3Time) 
	{
		this.light3Time = light3Time.toString();
	}
	
	//get the traffic light4 time from object
	public double getLight4Time() 
	{
		return Double.parseDouble(light4Time);
	}

	//Set the traffic light4 time in Object
	public void setLight4Time(Double light4Time) 
	{
		this.light4Time = light4Time.toString();
	}

	//Get the traffic light5 time from object
	public double getLight5Time() 
	{
		return Double.parseDouble(light5Time);
	}

	//Set the traffic light5 time in object
	public void setLight5Time(Double light5Time) 
	{
		this.light5Time = light5Time.toString();
	}

	//Get the traffic light6 time from object
	public double getLight6Time() 
	{
		return Double.parseDouble(light6Time);
	}

	//Set the traffic light 6 time in object
	public void setLight6Time(Double light6Time) 
	{
		this.light6Time = light6Time.toString();
	}

	//Get the traffic light 7 time from object
	public double getLight7Time() 
	{
		return Double.parseDouble(light7Time);
	}

	//Set the traffic light7 time in Object
	public void setLight7Time(Double light7Time) 
	{
		this.light7Time = light7Time.toString();
	}
	//get the traffic light8 time from object
	public double getLight8Time() 
	{
		return Double.parseDouble(light8Time);
	}
	
	//Set the traffic light 8 time in object
	public void setLight8Time(Double light8Time) 
	{
		this.light8Time = light8Time.toString();
	}

	//get the traffic light9 from object
	public double getLight9Time() 
	{
		return Double.parseDouble(light9Time);
	}

	//Set the traffic light 9 time in object
	public void setLight9Time(Double light9Time) 
	{
		this.light9Time = light9Time.toString();
	}

	//Get the traffic light 10 time from object
	public double getLight10Time() 
	{
		return Double.parseDouble(light10Time);
	}

	//Set the traffic light10 time in object
	public void setLight10Time(Double light10Time) 
	{
		this.light10Time = light10Time.toString();
	}

	//Get the traffic light 11 time from object
	public double getLight11Time() 
	{
		return Double.parseDouble(light11Time);
	}

	//Set the traffic light 11 time in object
	public void setLight11Time(Double light11Time) 
	{
		this.light11Time = light11Time.toString();
	}
	
	//get the traffic light 12 from object
	public double getLight12Time() 
	{
		return Double.parseDouble(light12Time);
	}
	
	//set the traffic light 12 time in object
	public void setLight12Time(Double light12Time) 
	{
		this.light12Time = light12Time.toString();
	}
	
	//Get the grid name from object
	public String getGridName()
	{
		return gridName;
	}
	
	//get the traffic light 13 time from object
	public double getLight13Time() 
	{
		return Double.parseDouble(light13Time);
	}

	//set the traffic light 12 time in object
	public void setLight13Time(Double light13Time) 
	{
		this.light13Time = light13Time.toString();
	}

	//get the traffic light14 time from object
	public double getLight14Time() 
	{
		return Double.parseDouble(light14Time);
	}

	//set the traffic light 14 time in object
	public void setLight14Time(Double light14Time) 
	{
		this.light14Time = light14Time.toString();
	}

	//get the traffic light 15 time from object
	public double getLight15Time() 
	{
		return Double.parseDouble(light15Time);
	}

	//set the traffic light 15 time in object
	public void setLight15Time(Double light15Time) 
	{
		this.light15Time = light15Time.toString();
	}

	//get the traffic light 16 time from object
	public double getLight16Time() 
	{
		return Double.parseDouble(light16Time);
	}

	//set the traffic light 16 in object
	public void setLight16Time(Double light16Time) 
	{
		this.light16Time = light16Time.toString();
	}

	//get the traffic light 17 time from object
	public double getLight17Time() 
	{
		return Double.parseDouble(light17Time);
	}

	//set the traffic light 17 time in object
	public void setLight17Time(Double light17Time) 
	{	
		this.light17Time = light17Time.toString();
	}

	//get the traffic light 18 time from object
	public double getLight18Time() 
	{
		return Double.parseDouble(light18Time);
	}

	//set the traffic light 18 time in object
	public void setLight18Time(Double light18Time) 
	{
		this.light18Time = light18Time.toString();
	}
	
	//get the road1side1 car frequency from object
	public Integer getRoad1Side1Cars() 
	{
		return Integer.parseInt(road1Side1Cars);
	}

	//set the road1side1 car frequency in object
	public void setRoad1Side1Cars(Integer road1Side1Cars) 
	{
		this.road1Side1Cars = road1Side1Cars.toString();
	}
	
	//get the road1side2 car frequency from object
	public Integer getRoad1Side2Cars() 
	{
		return Integer.parseInt(road1Side2Cars);
	}
	
	//set the road1side2 car frequency in object
	public void setRoad1Side2Cars(Integer road1Side2Cars) 
	{
		this.road1Side2Cars = road1Side2Cars.toString();
	}
	
	//get the road2side1 car frequency from object
	public Integer getRoad2Side1Cars() 
	{
		return Integer.parseInt(road2Side1Cars);
	}
	
	//set the road2side1 car frequency in object
	public void setRoad2Side1Cars(Integer road2Side1Cars) 
	{
		this.road2Side1Cars = road2Side1Cars.toString();
	}
	
	//get the road2side2 car frequency from object
	public Integer getRoad2Side2Cars() 
	{
		return Integer.parseInt(road2Side2Cars);
	}
	//set the road2side2 car frequency in object
	public void setRoad2Side2Cars(Integer road2Side2Cars) 
	{
		this.road2Side2Cars = road2Side2Cars.toString();
	}

	//get the road3side1 car frequency from object
	public Integer getRoad3Side1Cars() 
	{
		return Integer.parseInt(road3Side1Cars);
	}
	
	//set the road3side1 car frequency in object
	public void setRoad3Side1Cars(Integer road3Side1Cars) 
	{
		this.road3Side1Cars = road3Side1Cars.toString();
	}

	//get the road3side2 car frequency from object
	public Integer getRoad3Side2Cars() 
	{
		return Integer.parseInt(road3Side2Cars);
	}
	
	//set the road3side2 car frequency in object
	public void setRoad3Side2Cars(Integer road3Side2Cars) 
	{
		this.road3Side2Cars = road3Side2Cars.toString();
	}
	
	//get the road4side1 car frequency from object
	public Integer getRoad4Side1Cars() 
	{
		return Integer.parseInt(road4Side1Cars);
	}

	//set the road4side1 car frequency in object
	public void setRoad4Side1Cars(Integer road4Side1Cars) 
	{
		this.road4Side1Cars = road4Side1Cars.toString();
	}
	//get the road4side2 car frequency from object
	public Integer getRoad4Side2Cars() 
	{
		return Integer.parseInt(road4Side2Cars);
	}

	//set the road4side2 car frequency in object
	public void setRoad4Side2Cars(Integer road4Side2Cars) 
	{
		this.road4Side2Cars = road4Side2Cars.toString();
	}
	//get the road5side1 car frequency from object
	public Integer getRoad5Side1Cars() 
	{
		return Integer.parseInt(road5Side1Cars);
	}

	//set the road5side1 car frequency in object
	public void setRoad5Side1Cars(Integer road5Side1Cars) 
	{
		this.road5Side1Cars = road5Side1Cars.toString();
	}
	
	//get the road5side2 car frequency from object
	public Integer getRoad5Side2Cars() 
	{
		return Integer.parseInt(road5Side2Cars);
	}

	//set the road5side2 car frequency in object
	public void setRoad5Side2Cars(Integer road5Side2Cars) 
	{
		this.road5Side2Cars = road5Side2Cars.toString();
	}
	
	//get the road6side1 car frequency from object
	public Integer getRoad6Side1Cars() 
	{
		return Integer.parseInt(road6Side1Cars);
	}

	//set the road6side1 car frequency in object
	public void setRoad6Side1Cars(Integer road6Side1Cars) 
	{
		this.road6Side1Cars = road6Side1Cars.toString();
	}
	
	//get the road6side2 car frequency from object
	public Integer getRoad6Side2Cars() 
	{
		return Integer.parseInt(road6Side2Cars);
	}
	
	//set the road6side2 car frequency in object
	public void setRoad6Side2Cars(Integer road6Side2Cars) 
	{
		this.road6Side2Cars = road6Side2Cars.toString();
	}
	
	//Get the admin grid id from object
	public Double getAdminGridID()
	{
		return Double.valueOf(adminGridID);
	}
	
	//get the created user from Object
	public String getCreatedUser()
	{
		return createdUser;
	}
	
	//get the created date from object
	public Date getCreatedDate() throws ParseException
	{
		return Util.inputFormat.parse(createdDate);
	}
	
	//get the lastModifiedUser from object
	public String getLastModifiedUser()
	{
		return lastModifiedUser;
	}
	
	//get the totalGraphCount from Object
	public int getTotalGraphCount()
	{
		return Integer.parseInt(totalGraphCount);
	}
	
	//Get the LastModifiedDate from object
	public Date getLastModifiedDate()throws ParseException
	{
		return Util.inputFormat.parse(lastModifiedDate);
	}
	
	//Set Created Date in Object
	public void setCreatedDate(Date date)
	{
		this.createdDate = date.toString();
	}
	
	//Set the adminGridID in Object
	public void setAdminGridID(Double id)
	{
		this.adminGridID = String.valueOf(id);
	}
	
	//Set the createdUser in Object
	public void setCreatedUser(String user)
	{
		this.createdUser = user;
	}
	
	//Set the lastModifiedUser in Object
	public void setLastModifiedUser(String user)
	{
		this.lastModifiedUser = user;
	}
	
	//Get the graphID from Object
	public double getGraphID()
	{
		return Double.parseDouble(this.graphID);
	}
			
	/*
	 * 
	 * @parameters: NULL
	 * @throws Exception
	 * @functions: Stores all the graphs from DB in a ConcurrentHashMap
	 */
	public static ArrayList getAllGraphs() throws Exception
	{
		String queryText = Query.GET_GRAPHS;
		//System.out.println(queryText);
		return DBConnect.getQuery(queryText, GRAPH_MAPPER, Graph.class);
	}
	
	/*
	 * @authorL Chris
	 * @parameters: graphID
	 * @throws: Exception
	 * @returns Graph Object
	 */
	public static Graph getGraphByID(double graphID) throws Exception
	{
		if(graphID > 0)
		{
			String queryText = Query.GET_GRAPH;
			queryText += "WHERE GRAP.GRAPH_ID="+graphID+" ";
			return (Graph) DBConnect.getQuery(queryText, GRAPH_MAPPER, Graph.class).get(0);
		}
		else
		{
			throw new Exception("GRAPH ID is Mandatory.");
		}
	}
	
	/*
	 * 
	 * @parameters: Created User, graphID
	 * @returns ArrayList
	 * @throws Exception
	 */
	public static ArrayList getGraphForUser(String createdUser, double graphID) throws Exception
	{
		//The query to get graph from DB
		String queryText = Query.GET_GRAPH;
		if(!Util.isNullorEmpty(createdUser))
		{
			queryText += "WHERE GRAP.CREATED_USER='"+createdUser+"' ";
		}
		if(graphID > 0)
		{
			if(Util.isNullorEmpty(createdUser))
			{
				queryText += "WHERE GRAP.GRAPH_ID="+graphID+" ";
			}
			else
			{
				queryText += "AND GRAP.GRAPH_ID="+graphID+" ";
			}
		}
		queryText += "ORDER BY GRAP.CREATED_DATE DESC";
		//System.out.println(queryText);
		return DBConnect.getQuery(queryText, GRAPH_MAPPER, Graph.class);
	}
	
	/*
	 * 
	 * @parameter: GraphObj
	 * @throws Exception
	 * @returns true if insert record Successful else returns false
	 */
	public boolean insertGraph(Graph graphObj) throws Exception
	{
		try
		{
			if(graphObj.getAdminGridID()> 0	&& !Util.isNullorEmpty(graphObj.getCreatedUser()))
			{
				String queryText = Query.INSERT_GRAPH;
				queryText += graphObj.getAdminGridID()+",'"+graphObj.getCreatedUser()
							+"',SYSTIMESTAMP,'"+graphObj.getLastModifiedUser()+"',SYSDATE,"
							+graphObj.getLight1Time()+","+graphObj.getLight2Time()+","
							+graphObj.getLight3Time()+","+graphObj.getLight4Time()+","
							+graphObj.getLight5Time()+","+graphObj.getLight6Time()+","+graphObj.getLight7Time()+","
							+graphObj.getLight8Time()+","+graphObj.getLight9Time()+","
							+graphObj.getLight10Time()+","+graphObj.getLight11Time()+","
							+graphObj.getLight12Time()+","+graphObj.getLight13Time()+","
							+graphObj.getLight14Time()+","+graphObj.getLight15Time()+","
							+graphObj.getLight16Time()+","+graphObj.getLight17Time()+","
							+graphObj.getLight18Time()+","+graphObj.getRoad1Side1Cars()+","
							+graphObj.getRoad1Side2Cars()+","+graphObj.getRoad2Side1Cars()+","
							+graphObj.getRoad2Side2Cars()+","+graphObj.getRoad3Side1Cars()+","
							+graphObj.getRoad3Side2Cars()+","+graphObj.getRoad4Side1Cars()+","
							+graphObj.getRoad4Side2Cars()+","+graphObj.getRoad5Side1Cars()+","
							+graphObj.getRoad5Side2Cars()+","+graphObj.getRoad6Side1Cars()+","
							+graphObj.getRoad6Side2Cars()+"";
				queryText += ")";
				//System.out.println("Executed query: "+queryText);
				return DBConnect.insertQuery(queryText);
			}
			else
			{
				throw new Exception("Mandatory Parameters are missing.");
			}
		}
		catch(Exception exp)
		{
			return false;
		}		
	}

}
