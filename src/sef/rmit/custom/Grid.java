/*
 * @author:
 */

package sef.rmit.custom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import javafx.collections.ObservableList;
import sef.rmit.controller.LoginController;
import sef.rmit.db.*;
import sef.rmit.util.*;

@SuppressWarnings({"serial","rawtypes","unused"})
public class Grid
{
	//HashMap to map the DB Column values to the variable names
	private static final ConcurrentHashMap<String, String> GRID_MAPPER = new ConcurrentHashMap<String, String>() {{
		put("ADMIN_GRID_ID","adminGridID");
		put("GRID_XML","gridXML");
		put("STATUS","gridStatus");
		put("GRID_ID","gridID");
		put("GRID_NAME","gridName");
		put("CREATED_USER","createdUser");
		put("CREATED_DATE","createdDate");
		put("LAST_MODIFIED_USER","lastModifiedUser");
		put("LAST_MODIFIED_DATE","lastModifiedDate");
		put("SIMULATION_TIME","simulationTime");
		put("ROAD1_SIDE1","road1Side1");
		put("ROAD1_SIDE2","road1Side2");
		put("ROAD2_SIDE1","road2Side1");
		put("ROAD2_SIDE2","road2Side2");
		put("ROAD3_SIDE1","road3Side1");
		put("ROAD3_SIDE2","road3Side2");
		put("ROAD4_SIDE1","road4Side1");
		put("ROAD4_SIDE2","road4Side2");
		put("ROAD5_SIDE1","road5Side1");
		put("ROAD5_SIDE2","road5Side2");
		put("ROAD6_SIDE1","road6Side1");
		put("ROAD6_SIDE2","road6Side2");
	}};

	/*
	 * Observable List column1List
	 * String Column1
	 * function: used to display combo box to the ADMIN in EDIT Grid UI
	 * 
	 */
	private ObservableList<String> column1List;

	private String column1;

	//Retrieve the column list of combo box
	public ObservableList<String> getColumn1List() 
	{
		return column1List;
	}

	//Set the column list for combox box
	public void setColumn1List(ObservableList<String> observableArrayList) 
	{
		this.column1List = observableArrayList;
	}

	//Set the Status Column Values in Combo box
	public void setStatusColumn(String newValue) 
	{
		this.column1 = newValue;			
	}

	private ArrayList<Intersection> intersections = new ArrayList<Intersection>();
	private ArrayList<Road> roads = new ArrayList<Road>();
	private String gridXML;
	private String gridStatus;
	private String gridID;
	private String createdUser;
	private String createdDate;
	private String lastModifiedUser;
	private String lastModifiedDate;
	private String simulationTime;
	private String gridName;
	private String road1Side1;
	private String road1Side2;
	private String road2Side1;
	private String road2Side2;
	private String road3Side1;
	private String road3Side2;
	private String road4Side1;
	private String road4Side2;
	private String road5Side1;
	private String road5Side2;
	private String road6Side1;
	private String road6Side2;
	private String adminGridID;
	
	public ArrayList<Car>availableCars = new ArrayList<Car>();
	public ArrayList<Car> getAvailableCars() {
		return availableCars;
	}
	
	//Get the AdminGridID from the Object
	public Double getAdminGridID() 
	{
		return Double.parseDouble(adminGridID);
	}

	//Set the Admin Grid ID to the grid OBject
	public void setAdminGridID(Double adminGridID) 
	{
		this.adminGridID = adminGridID.toString();
	}

	//Get all the intersections of the Grid Object
	public ArrayList<Intersection> getIntersections() 
	{
		return intersections;
	}

	//Set all the intersections to the Grid Object
	public void setIntersections(ArrayList<Intersection> intersections) 
	{
		this.intersections = intersections;
	}

	private boolean containsIntersectingRoads(int i, int j) 
	{
		for(Road road1: this.getRoads())
		{
			if(road1.getRoadID()==i)
				for(Road road2: this.getRoads())
				{
					if(road2.getRoadID()==j)
						return true;
				}
		}
		return false;
	}

	//Get the Current Grid XML Data
	public String getgridXML()
	{
		return gridXML;
	}

	//Get the Current Grid simulation time
	public long getGridSimulationTime()
	{
		return Long.parseLong(simulationTime);
	}

	//Get the Current Grid Status
	public String getGridStatus()
	{
		return gridStatus;
	}

	//Get the current Grid ID
	public String getGridID()
	{
		return gridID;
	}

	//Get Road1Side1 car frequency
	public double getRoad1Side1Value()
	{
		return Double.parseDouble(road1Side1);
	}

	//Get Road1Side2 car frequency
	public double getRoad1Side2Value()
	{
		return Double.parseDouble(road1Side2);
	}

	//Get Road2Side1 car frequency
	public double getRoad2Side1Value()
	{
		return Double.parseDouble(road2Side1);
	}

	//Get Road2Side2 car frequency
	public double getRoad2Side2Value()
	{
		return Double.parseDouble(road2Side2);
	}

	//Get Road3Side1 car frequency
	public double getRoad3Side1Value()
	{
		return Double.parseDouble(road3Side1);
	}

	//Get Road3Side2 car frequency
	public double getRoad3Side2Value()
	{
		return Double.parseDouble(road3Side2);
	}

	//Get Road4Side1 car frequency
	public double getRoad4Side1Value()
	{
		return Double.parseDouble(road4Side1);
	}

	//Get Road4Side2 car frequency
	public double getRoad4Side2Value()
	{
		return Double.parseDouble(road4Side2);
	}

	//Get Road5Side1 car frequency
	public double getRoad5Side1Value()
	{
		return Double.parseDouble(road5Side1);
	}

	//Get Road5Side2 car frequency
	public double getRoad5Side2Value()
	{
		return Double.parseDouble(road5Side2);
	}

	//Get Road6Side1 car frequency
	public double getRoad6Side1Value()
	{
		return Double.parseDouble(road6Side1);
	}

	//Get Road6Side2 car frequency
	public double getRoad6Side2Value()
	{
		return Double.parseDouble(road6Side2);
	}

	//Set the created User in object
	public String getcreatedUser() 
	{
		return createdUser;
	}

	//Get the last modified User from DoubleObject
	public String getLastModifiedUser()
	{
		return lastModifiedUser;
	}

	//Get the grid name in the object
	public String getGridName()
	{
		return gridName;
	}

	//Set the current Grid Status
	public void setGridStatus(String status) 
	{
		this.gridStatus = status;
	}

	//Set the current Grid Data
	public void setGridXML(String data) 
	{
		this.gridXML = data;
	}

	//Set the current Grid Simulation Time
	public void setGridSimulationTime(String time) 
	{
		this.simulationTime = time;
	}

	//Set the current Grid Status
	public void setcreatedUser(String createduser) 
	{
		this.createdUser = createduser;
	}

	//Set Grid Name
	public void setGridName(String gridName)
	{
		this.gridName = gridName;
	}

	//Get All Roads in a Grid
	public ArrayList<Road> getRoads() 
	{
		return roads;
	}

	//Get All Roads in a Grid
	public Road getRoadByRoadID(int roadID) 
	{
		Road road=null;

		for(Road i:this.getRoads())
		{
			if(i.getRoadID()==roadID)
				road=i;
		}

		return road;
	}

	//Set particular road to a Grid
	public void setRoads(ArrayList<Road> roads) 
	{
		this.roads = roads;
	}

	//Set the last modified User of the Grid
	public void setLastModifiedUser(String modifieduser)
	{
		this.lastModifiedUser = modifieduser;
	}


	//Set the current Grid ID
	public void setGridID(String id)
	{
		this.gridID = id;
	}


	//get true if Current Grid is active else false
	public boolean isActive() 
	{
		return this.getGridStatus().equals("Active");
	}

	//sets the current grid as being in Active Status
	public void setAsActive() 
	{
		this.setGridStatus("Active");
	}

	//Get true if Grid status is Planned else false
	public boolean isPlanned() 
	{
		return this.getGridStatus().equals("Planned");
	}

	//Sets the current grid as being in planned status
	public void setAsPlanned() 
	{
		this.setGridStatus("Planned");
	}

	//Set Road1Side1 car frequency
	public void setRoad1Side1Value(String carValue)
	{
		this.road1Side1 = carValue;
	}

	//Set Road1Side2 car frequency
	public void setRoad1Side2Value(String carValue)
	{
		this.road1Side2 = carValue;
	}

	//Set Road2Side1 car frequency
	public void setRoad2Side1Value(String carValue)
	{
		this.road2Side1 = carValue;
	}

	//Set Road2Side2 car frequency
	public void setRoad2Side2Value(String carValue)
	{
		this.road2Side2 = carValue;
	}

	//Set Road3Side1 car frequency
	public void setRoad3Side1Value(String carValue)
	{
		this.road3Side1 = carValue;
	}

	//Set Road3Side2 car frequency
	public void setRoad3Side2Value(String carValue)
	{
		this.road3Side2 = carValue;
	}

	//Set Road4Side1 car frequency
	public void setRoad4Side1Value(String carValue)
	{
		this.road4Side1 = carValue;
	}

	//Set Road4Side2 car frequency
	public void setRoad4Side2Value(String carValue)
	{
		this.road4Side2 = carValue;
	}

	//Set Road5Side1 car frequency
	public void setRoad5Side1Value(String carValue)
	{
		this.road5Side1 = carValue;
	}

	//Set Road5Side2 car frequency
	public void setRoad5Side2Value(String carValue)
	{
		this.road5Side2 = carValue;
	}

	//Set Road6Side1 car frequency
	public void setRoad6Side1Value(String carValue)
	{
		this.road6Side1 = carValue;
	}

	//Set Road6Side2 car frequency
	public void setRoad6Side2Value(String carValue)
	{
		this.road6Side2 = carValue;
	}

	public void moveCarsInGrid()
	{
		for(Road road:this.getRoads())
		{
			road.moveCarsOnRoad();
		}
	}

	/*
	 * 
	 * @returnType: String
	 * @Description: Used to Print details of Grid object
	 */
	@Override
	public String toString() 
	{
		return "Grid [roads=" + roads + ", gridData=" + gridXML + ", gridStatus=" + gridStatus
				+ ", gridID=" + gridID + ", createdUser=" + createdUser + ", createdDate=" + createdDate
				+ ", lastModifiedUser=" + lastModifiedUser + ", lastModifiedDate=" + lastModifiedDate + "]";
	}

	/*
	 * 
	 * @returnType: void
	 * @Description: Used initialize a Grid by creating road, place and intersection objects according to its ID
	 */
	public void initializeGridByGridID() 
	{
		///Create Roads and Places for the grid
		for (int roadID = 1; roadID <= 6; roadID++)
		{
			if(this.gridID.charAt(roadID-1) == '1')
			{
				Road road = new Road(roadID);
				road.setGrid(this);
				this.getRoads().add(road);
			}
		}	

		//Set All Intersection
		this.setIntersections();

		//Set Car frequency 
		for( Road tempRoad : this.getRoads())
		{
			if(this.gridID.charAt(tempRoad.getRoadID()-1) == '1')
			{

				tempRoad.setCarFrequency1(this.getCarRoadFrequency(tempRoad.getRoadID(),1));
				tempRoad.setCarFrequency2(this.getCarRoadFrequency(tempRoad.getRoadID(),2));
			}
		}
		this.initializeTrafficLights();
	}

	private double getCarRoadFrequency(int roadID, int side) 
	{
		double frequency;
		switch(roadID)
		{
		case(1): if(side == 1)
			frequency = this.getRoad1Side1Value();
		else
			frequency = this.getRoad1Side2Value();
		break;
		case(2): if(side == 2) 
			frequency = this.getRoad2Side1Value();
		else 
			frequency = this.getRoad2Side2Value();
		break;
		case(3): if(side == 3) 
			frequency = this.getRoad3Side1Value();
		else 
			frequency = this.getRoad3Side2Value();
		break;
		case(4):if(side == 4) 
			frequency = this.getRoad4Side1Value();
		else 
			frequency = this.getRoad4Side2Value();
		break;
		case(5): if(side == 5) 
			frequency = this.getRoad5Side1Value();
		else 
			frequency = this.getRoad5Side2Value();
		break;
		case(6):if(side == 6) 
			frequency = this.getRoad6Side1Value();
		else 
			frequency = this.getRoad6Side2Value();
		break;
		default: frequency = 1;
		}
		return frequency;
	}

	/*
	 * 
	 * @returnType: void
	 * @Description: Used create a default Grid with all six roads and all intersections
	 */
	public Grid createDefaultGrid() 
	{
		String simulationTime = "100000";
		///Create a test GRID
		Grid testGrid = new Grid();
		testGrid.setGridSimulationTime(simulationTime);
		///Create Roads and Places for the test grid
		for (int roadID = 1; roadID <= 6; roadID++)
		{
			Road road = new Road(roadID);
			testGrid.getRoads().add(road);
		}		
		//Set All Intersections
		testGrid.setIntersections();
		///Set Car frequency as 1 every three secs
		for( Road tempRoad : testGrid.getRoads())
		{
			if(tempRoad.getRoadID()==2)
			{
				tempRoad.setCarFrequency1(0.3);
				tempRoad.setCarFrequency2(0.3);
			}
			else
			{
				tempRoad.setCarFrequency1(0.3);
				tempRoad.setCarFrequency2(0.3);
			}
		}
		testGrid.initializeTrafficLights();
		return testGrid;
	}

	/*
	 * 
	 * @returnType: void
	 * @Description: Used to initialize initial colors for all Traffic Lights
	 */
	public void initializeTrafficLights()
	{
		for(Intersection intersection:this.getIntersections())
		{
			intersection.getHorizontalTrafficLight().setGreen(true);
			intersection.getVerticalTrafficLight().setRed(true);
		}
	}

	/*
	 * 
	 * @returnType: void
	 * @Description: Used create intersection and traffic light objects in the grid
	 */
	public void setIntersections()
	{

		Intersection intersection14 = new Intersection(14, new TrafficLight(1), new TrafficLight(2));
		Intersection intersection24 = new Intersection(24, new TrafficLight(3), new TrafficLight(4));
		Intersection intersection34 = new Intersection(34, new TrafficLight(5), new TrafficLight(6));
		Intersection intersection15 = new Intersection(15, new TrafficLight(7), new TrafficLight(8));
		Intersection intersection25 = new Intersection(25, new TrafficLight(9), new TrafficLight(10));
		Intersection intersection35 = new Intersection(35, new TrafficLight(11), new TrafficLight(12));
		Intersection intersection16 = new Intersection(16, new TrafficLight(13), new TrafficLight(14));
		Intersection intersection26 = new Intersection(26, new TrafficLight(15), new TrafficLight(16));
		Intersection intersection36 = new Intersection(36, new TrafficLight(17), new TrafficLight(18));

		if(containsIntersectingRoads(1,4)) this.getIntersections().add(intersection14);
		if(containsIntersectingRoads(2,4)) this.getIntersections().add(intersection24);
		if(containsIntersectingRoads(3,4)) this.getIntersections().add(intersection34);
		if(containsIntersectingRoads(1,5)) this.getIntersections().add(intersection15);
		if(containsIntersectingRoads(2,5)) this.getIntersections().add(intersection25);
		if(containsIntersectingRoads(3,5)) this.getIntersections().add(intersection35);
		if(containsIntersectingRoads(1,6)) this.getIntersections().add(intersection16);
		if(containsIntersectingRoads(2,6)) this.getIntersections().add(intersection26);
		if(containsIntersectingRoads(3,6)) this.getIntersections().add(intersection36);

		for(Road road: this.getRoads())
		{
			for(Place place: road.getPlaces())
			{
				int placeID= place.getPlaceID();

				if(containsIntersectingRoads(1,4))
				{
					if(placeID == 412 || placeID == 118 || placeID == 122 || placeID == 428)
					{
						place.setIntersection(intersection14);
						intersection14.getPlaces().add(place);
					}
				}
				if(containsIntersectingRoads(2,4))
				{
					if(placeID == 415 || placeID == 218 || placeID == 222 || placeID == 425)
					{
						place.setIntersection(intersection24);
						intersection24.getPlaces().add(place);
					}
				}
				if(containsIntersectingRoads(3,4))
				{
					if(placeID == 418 || placeID == 318||placeID == 322 || placeID == 422)
					{
						place.setIntersection(intersection34);
						intersection34.getPlaces().add(place);
					}
				}
				if(containsIntersectingRoads(1,5))
				{
					if(placeID == 512 || placeID == 115 || placeID == 125 || placeID == 528)
					{
						place.setIntersection(intersection15);
						intersection15.getPlaces().add(place);
					}
				}
				if(containsIntersectingRoads(2,5))
				{
					if(placeID == 515 || placeID == 215 || placeID == 225 || placeID == 525)
					{
						place.setIntersection(intersection25);
						intersection25.getPlaces().add(place);
					}
				}
				if(containsIntersectingRoads(3,5))
				{
					if(placeID == 518 || placeID == 315 || placeID == 325 || placeID == 522)
					{
						place.setIntersection(intersection35);
						intersection35.getPlaces().add(place);
					}
				}
				if(containsIntersectingRoads(1,6))
				{
					if(placeID == 612 || placeID == 112 || placeID==128 || placeID== 628)
					{
						place.setIntersection(intersection16);
						intersection16.getPlaces().add(place);

					}
				}
				if(containsIntersectingRoads(2,6))
				{
					if(placeID == 615 || placeID == 212|| placeID == 228 || placeID == 625)
					{
						place.setIntersection(intersection26);
						intersection26.getPlaces().add(place);

					}
				}
				if(containsIntersectingRoads(3,6))
				{
					if(placeID == 618 || placeID == 312|| placeID == 328|| placeID == 622)
					{
						place.setIntersection(intersection36);
						intersection36.getPlaces().add(place);

					}
				}
			}
		}
	}




	/*
	 * 
	 * @parameters: status
	 * @Returns Specific Grid Object
	 * @Throws Exception
	 */
	public static Grid getDefaultGridByStatus(String status) throws Exception
	{
		try
		{
			//Get Query for GRID
			String queryText = Query.GET_ADMIN_GRID_DATA;
			if(Util.isNullorEmpty(status))
			{
				queryText += "WHERE STATUS = 'DEFAULT'";
			}
			else
			{
				queryText += "WHERE STATUS = '"+status+"'";
			}
			//System.out.println("Executed query: "+queryText);
			return (Grid) DBConnect.getQuery(queryText, GRID_MAPPER, Grid.class).get(0);	
		}
		catch(Exception e)
		{
			return null;
		}
	}

	/*
	 * 
	 * @parameters: status
	 * @Returns Specific Grid Objects By status
	 * @Throws Exception
	 */
	public static ArrayList getAllGridsByStatus(String status) throws Exception
	{
		try
		{
			//Get Query for GRID
			String queryText = Query.GET_ADMIN_GRID_DATA;
			if(!Util.isNullorEmpty(status))
			{
				queryText += "WHERE STATUS ='DEFAULT' OR STATUS = '"+status+"'";
			}
			//System.out.println("Executed query: "+queryText);
			return DBConnect.getQuery(queryText, GRID_MAPPER, Grid.class);	
		}
		catch(Exception e)
		{
			return null;
		}
	}

	/*
	 * 
	 * @parameter: Grid Object
	 * @throws Exception
	 * @returns true if data inserted into DB else returns False
	 */
	public static boolean insertAdminGrid(Grid gridObj) throws Exception
	{
		if(gridObj == null || Util.isNullorEmpty(gridObj.getGridID())
				|| Util.isNullorEmpty(gridObj.getGridStatus()) || Util.isNullorEmpty(gridObj.getcreatedUser())
				|| gridObj.getGridSimulationTime() <= 0 || Util.isNullorEmpty(gridObj.getLastModifiedUser())
				|| Util.isNullorEmpty(gridObj.getGridName()))
		{
			throw new Exception("Mandatory Parameters are missing.");
		}
		else
		{
			//Query to insert the grid in the ADMIN_GRID table
			String queryText = Query.INSERT_ADMIN_GRID;
			queryText += "'"+gridObj.getGridID()+"','"+gridObj.getGridStatus()+"','"+gridObj.getGridSimulationTime()+"','"
					+gridObj.getGridName()+"',"+gridObj.getRoad1Side1Value()+","+gridObj.getRoad1Side2Value()+","
					+gridObj.getRoad2Side1Value()+","+gridObj.getRoad2Side2Value()+","+gridObj.getRoad3Side1Value()+","
					+gridObj.getRoad3Side2Value()+","+gridObj.getRoad4Side1Value()+","+gridObj.getRoad4Side2Value()+","
					+gridObj.getRoad5Side1Value()+","+gridObj.getRoad5Side2Value()+","+gridObj.getRoad6Side1Value()+","
					+gridObj.getRoad6Side2Value()+ ",'"+gridObj.getcreatedUser()+"',sysdate,'"
					+gridObj.getLastModifiedUser()+"',sysdate)";
			//System.out.println("Executed query: "+queryText);
			return DBConnect.insertQuery(queryText);
		}
	}

	/*
	 * 
	 * @parameters: NULL
	 * @throws Exception
	 * @returns true if default grid already exists else false
	 */
	public static boolean checkIfDefaultGridAlreadyExists() throws Exception
	{
		int defaultGridCount = 0;
		ArrayList updateGrids = Grid.getAllGridsByStatus("");
    	for(int incre = 0; incre < updateGrids.size(); incre++)
    	{
    		Grid gridObj = (Grid) updateGrids.get(incre);
    		if(gridObj.getGridStatus().equals("DEFAULT"))
			{
    			defaultGridCount++;
			}
    	}
    	if(defaultGridCount == 1)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
	}
	
	/*
	 * 
	 * @Parameters : Grid Object
	 * @throws Exception
	 * @return true if Grid is Updated else return false
	 */
	public static boolean updateAdminGrid(Grid gridObj) throws Exception
	{
		if(gridObj != null && !Util.isNullorEmpty(gridObj.getGridID()))
		{
			//Query to update the GRID Status in the ADMIN_GRID table
			String queryText = Query.UPDATE_GRID;
			if(!Util.isNullorEmpty(gridObj.getGridStatus()))
			{
				queryText += "STATUS = '"+gridObj.getGridStatus()+"',";
			}			
			if(!Util.isNullorEmpty(gridObj.getLastModifiedUser()))
			{
				queryText += "LAST_MODIFIED_USER = '"+gridObj.getLastModifiedUser()+"'";
			}	
			queryText += "WHERE ADMIN_GRID_ID = '"+gridObj.getAdminGridID()+"'";
			//System.out.println("Executed query: "+queryText);
			return DBConnect.insertQuery(queryText);
		}
		else
		{
			//Throw Exception if the grid cannot be found in the ADMIN_GRID table
			throw new Exception ("GRID Object is Empty! or Grid ID is not present.");
		}
	}

	public Intersection getIntersectionAt(int i, int j) 
	{
		for(Intersection intersection:this.getIntersections())
		{
			if(intersection.getIntersectionID()==i*10+j)
				return intersection;
		}
		return null;
	}


}