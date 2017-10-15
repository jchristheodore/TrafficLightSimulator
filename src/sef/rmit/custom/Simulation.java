/*
 * @author:
 */

package sef.rmit.custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import sef.rmit.controller.*;
import sef.rmit.email.Email;
import sef.rmit.email.TemplateData;

@SuppressWarnings("unused")
public class Simulation extends Thread
{	
	private long totalTime;
	private long startTime;
	private long currentTime;
	private Grid grid;
	private UserGridController gui;
	public boolean threadRunning = true;
	private HashMap<Integer,Integer> carPlaceMap = new HashMap<Integer,Integer>();
	private HashMap<Integer,String> trafficLightColour = new HashMap<Integer,String>();
	private HashMap<Integer, Double>lightTimings = new HashMap<Integer, Double>();
	private Graph graph = new Graph();
	private int unavailableCarIndex = 0;

	//Integer represents Traffic Light ID
	private HashMap<Integer,Long> greenLightTotalTime = new HashMap<Integer,Long>();
	private HashMap<Integer,Long> greenLightStartTime = new HashMap<Integer,Long>();
	private HashMap<Integer,Long> amberLightStartTime = new HashMap<Integer,Long>();
	private Date simulationStartDate;
	

	public Date getSimulationStartDate()
	{
		return simulationStartDate;
	}
	
	public void setSimulationStartDate()
	{
		this.simulationStartDate = new Date();
	}
	
	/*
	 * 
	 * @returnType: void
	 * @Description: run method for the thread to run. The entire simulation takes place within this method
	 */
	public void run()
	{	
		this.startTime = System.currentTimeMillis();
		this.currentTime = this.startTime;
		long timePassed = this.currentTime-this.startTime;
		long[][] time = {{startTime,startTime},{startTime,startTime},{startTime,startTime},{startTime,startTime},{startTime,startTime},{startTime,startTime}};
		long lightStartTime;
		long lightCurrentTime;
		long lightTotalTime;
		long guiTime = 0;
		double frequency;
	
		Grid grid = this.getGrid();
		grid.initializeTrafficLights();
		this.initializeGreenLightStartTime();
		this.initializeGreenLightTotalTime();
		
		while(timePassed < this.totalTime)
		{	
			for(Road road:grid.getRoads())
			{	
				frequency=road.getCarFrequency1();
	
				if(timePassed%((int)(1000/frequency))==0 && currentTime > time[road.getRoadID()-1][0])
				{			
					this.generateNewCars(road,1);
					time[road.getRoadID()-1][0]=currentTime;
				}
	
				frequency=road.getCarFrequency2();
	
				if(timePassed%((int)(1000/frequency))==0 && currentTime > time[road.getRoadID()-1][1])
				{			
					this.generateNewCars(road,2);
					time[road.getRoadID()-1][1]=currentTime;
				}
			}
	
			for(Intersection intersection : grid.getIntersections())
			{
				TrafficLight horizontalTrafficLight = intersection.getHorizontalTrafficLight();
				TrafficLight verticalTrafficLight = intersection.getVerticalTrafficLight();
	
				if(horizontalTrafficLight.isGreen())
				{
					lightStartTime=greenLightStartTime.get(horizontalTrafficLight.getTrafficLightID());
					lightTotalTime=greenLightTotalTime.get(horizontalTrafficLight.getTrafficLightID());
	
					if(this.currentTime-lightStartTime > lightTotalTime)
					{
						horizontalTrafficLight.setAmber(true);
						amberLightStartTime.put(horizontalTrafficLight.getTrafficLightID(),System.currentTimeMillis());
	
					}
	
				}
				else if(horizontalTrafficLight.isAmber())
				{
					lightStartTime=amberLightStartTime.get(horizontalTrafficLight.getTrafficLightID());
					lightTotalTime=1000;
	
					if(this.currentTime-lightStartTime > lightTotalTime)
					{
						horizontalTrafficLight.setRed(true);
						verticalTrafficLight.setGreen(true);
						greenLightStartTime.replace(verticalTrafficLight.getTrafficLightID(),System.currentTimeMillis());
	
					}
	
				}
	
				else if(verticalTrafficLight.isGreen())
				{
					lightStartTime=greenLightStartTime.get(verticalTrafficLight.getTrafficLightID());
					lightTotalTime=greenLightTotalTime.get(verticalTrafficLight.getTrafficLightID());
	
					if(this.currentTime-lightStartTime > lightTotalTime)
					{
						verticalTrafficLight.setAmber(true);
						amberLightStartTime.put(verticalTrafficLight.getTrafficLightID(),System.currentTimeMillis());
	
					}
				}
				else if(verticalTrafficLight.isAmber())
				{
					lightStartTime=amberLightStartTime.get(verticalTrafficLight.getTrafficLightID());
					lightTotalTime=1000;
	
					if(this.currentTime-lightStartTime > lightTotalTime)
					{
						verticalTrafficLight.setRed(true);
						horizontalTrafficLight.setGreen(true);
						greenLightStartTime.replace(horizontalTrafficLight.getTrafficLightID(),System.currentTimeMillis());
					}
	
				}
			}
	
	
	
			this.currentTime = System.currentTimeMillis(); 
			timePassed = this.currentTime-this.startTime;
	
	
			if(timePassed%((int)(1000))==0 && timePassed > guiTime)
			{		
				guiTime=timePassed;
				this.updateCarPlaceMap();
				this.updateTrafficLightColour();
				this.getGrid().moveCarsInGrid();
				System.out.println(timePassed);
				gui.simulater(this.carPlaceMap,this.trafficLightColour);
			}
		}
	
		this.setGraphObject();
		try 
		{
			UserGridController.clearALLMaps();
			boolean isGraphInserted = this.getGraph().insertGraph(graph);
			if(isGraphInserted)
			{
				Email.sendEmailToReciptent(TemplateData.SIMULATION_COMPLETE_TEMPLATE.getValue(), LoginController.getLoggedInData().getUsername(), "Traffic Simulator Simulation Completed", this);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		this.threadRunning = false;	
	}


	public HashMap<Integer, Long> getGreenLightStartTime() {
		return greenLightStartTime;
	}

	/*
	 * 
	 * @returnType: boolean
	 * @Description: Records data generated from the simulation in a graph object
	 */
	public void setGraphObject()
	{
		Graph graph = this.getGraph();
	
		System.out.println("Number of cars that passed through: ");
	
	InitializeGraphObject(graph);
	
	graph.setAdminGridID(this.getGrid().getAdminGridID());
	graph.setCreatedUser(LoginController.getLoggedInData().getUsername());
	graph.setLastModifiedUser(LoginController.getLoggedInData().getUsername());
	
	for(Road road:this.getGrid().getRoads())
	{
		System.out.println("Road"+road.getRoadID()+" Side 1 :" + road.getCarsPassedSide1());
		System.out.println("Road"+road.getRoadID()+" Side 2 :" + road.getCarsPassedSide2());
	
			if(road.getRoadID()==1)
			{
				graph.setRoad1Side1Cars(road.getCarsPassedSide1());
				graph.setRoad1Side2Cars(road.getCarsPassedSide2());
			}
			else if(road.getRoadID()==2)
			{
				graph.setRoad2Side1Cars(road.getCarsPassedSide1());
				graph.setRoad2Side2Cars(road.getCarsPassedSide2());
			}
			else if(road.getRoadID()==3)
			{
				graph.setRoad3Side1Cars(road.getCarsPassedSide1());
				graph.setRoad3Side2Cars(road.getCarsPassedSide2());
			}
			else if(road.getRoadID()==4)
			{
				graph.setRoad4Side1Cars(road.getCarsPassedSide1());
				graph.setRoad4Side2Cars(road.getCarsPassedSide2());
			}
			else if(road.getRoadID()==5)
			{
				graph.setRoad5Side1Cars(road.getCarsPassedSide1());
				graph.setRoad5Side2Cars(road.getCarsPassedSide2());
			}
			else if(road.getRoadID()==6)
			{
				graph.setRoad6Side1Cars(road.getCarsPassedSide1());
				graph.setRoad6Side2Cars(road.getCarsPassedSide2());
			}
	
		}
	
		for(int lightID : lightTimings.keySet())
		{
			if(lightID == 1)
				graph.setLight1Time(lightTimings.get(lightID));
			else if(lightID == 2)
				graph.setLight2Time(lightTimings.get(lightID));
			else if(lightID == 3)
				graph.setLight3Time(lightTimings.get(lightID));
			else if(lightID == 4)
				graph.setLight4Time(lightTimings.get(lightID));
			else if(lightID == 5)
				graph.setLight5Time(lightTimings.get(lightID));
			else if(lightID == 6)
				graph.setLight6Time(lightTimings.get(lightID));
			else if(lightID == 7)
				graph.setLight7Time(lightTimings.get(lightID));
			else if(lightID == 8)
				graph.setLight8Time(lightTimings.get(lightID));
			else if(lightID == 9)
				graph.setLight9Time(lightTimings.get(lightID));
			else if(lightID == 10)
				graph.setLight10Time(lightTimings.get(lightID));
			else if(lightID == 11)
				graph.setLight11Time(lightTimings.get(lightID));
			else if(lightID == 12)
				graph.setLight12Time(lightTimings.get(lightID));
			else if(lightID == 13)
				graph.setLight13Time(lightTimings.get(lightID));
			else if(lightID == 14)
				graph.setLight14Time(lightTimings.get(lightID));
			else if(lightID == 15)
				graph.setLight15Time(lightTimings.get(lightID));
			else if(lightID == 16)
				graph.setLight16Time(lightTimings.get(lightID));
			else if(lightID == 17)
				graph.setLight17Time(lightTimings.get(lightID));
			else if(lightID == 18)
				graph.setLight18Time(lightTimings.get(lightID));
	
		}
	}

	/*
	 * 
	 * @returnType: void
	 * @Description: Initializes Graph object that will store data from the simulation 
	 */
	private void InitializeGraphObject(Graph graph) 
	{
		//this is set to negative 1 as the road might not be a part of the grid
		int initialValueOfCarsPassed = -1; 
		double initialValueLightTiming = 0.0; 
		
		graph.setRoad1Side1Cars(initialValueOfCarsPassed);
		graph.setRoad1Side2Cars(initialValueOfCarsPassed);
		graph.setRoad2Side1Cars(initialValueOfCarsPassed);
		graph.setRoad2Side2Cars(initialValueOfCarsPassed);
		graph.setRoad3Side1Cars(initialValueOfCarsPassed);
		graph.setRoad3Side2Cars(initialValueOfCarsPassed);
		graph.setRoad4Side1Cars(initialValueOfCarsPassed);
		graph.setRoad4Side2Cars(initialValueOfCarsPassed);
		graph.setRoad5Side1Cars(initialValueOfCarsPassed);
		graph.setRoad5Side2Cars(initialValueOfCarsPassed);
		graph.setRoad6Side1Cars(initialValueOfCarsPassed);
		graph.setRoad6Side2Cars(initialValueOfCarsPassed);
	
		graph.setLight1Time(initialValueLightTiming);
		graph.setLight2Time(initialValueLightTiming);
		graph.setLight3Time(initialValueLightTiming);
		graph.setLight4Time(initialValueLightTiming);
		graph.setLight5Time(initialValueLightTiming);
		graph.setLight6Time(initialValueLightTiming);
		graph.setLight7Time(initialValueLightTiming);
		graph.setLight8Time(initialValueLightTiming);
		graph.setLight9Time(initialValueLightTiming);
		graph.setLight10Time(initialValueLightTiming);
		graph.setLight11Time(initialValueLightTiming);
		graph.setLight12Time(initialValueLightTiming);
		graph.setLight13Time(initialValueLightTiming);
		graph.setLight14Time(initialValueLightTiming);
		graph.setLight15Time(initialValueLightTiming);
		graph.setLight16Time(initialValueLightTiming);
		graph.setLight17Time(initialValueLightTiming);
		graph.setLight18Time(initialValueLightTiming);
	}


	public Graph getGraph() 
	{
		return graph;
	}
	
	@Override
	public String toString() 
	{
		return "Simulation [totalTime=" + totalTime + ", startTime=" + startTime + ", currentTime=" + currentTime
				+ ", grid=" + grid + ", carPlaceMap=" + carPlaceMap + ", trafficLightColour=" + trafficLightColour
				+ ", lightTimings=" + lightTimings + ", greenLightTotalTime=" + greenLightTotalTime
				+ ", greenLightStartTime=" + greenLightStartTime + ", amberLightStartTime=" + amberLightStartTime + "]";
	}

	/*
	 * 
	 * @CONSTRUCTOR with parameters: GRID, HashMap<Integer, Double>
	 * @Description: creates a simulation object with the specified grid and traffic lights
	 */
	public Simulation(Grid grid, HashMap<Integer, Double> lightTimings) 
	{
		super();
		this.totalTime = grid.getGridSimulationTime()*1000*60;
	
		this.grid = grid;
	
		grid.initializeGridByGridID();
	
		this.lightTimings = lightTimings;
	}

	/*
	 * 
	 * @returnType: void
	 * @Description: Initializes the time at which the traffic lights have been turned on
	 */
	public void initializeGreenLightStartTime() 
	{
		long notTurnedOnYet = 0;
		for(Intersection intersection : grid.getIntersections())
		{
			TrafficLight horizontalTrafficLight = intersection.getHorizontalTrafficLight();
			TrafficLight verticalTrafficLight = intersection.getVerticalTrafficLight();
	
			greenLightStartTime.put(horizontalTrafficLight.getTrafficLightID(), System.currentTimeMillis());
			greenLightStartTime.put(verticalTrafficLight.getTrafficLightID(), notTurnedOnYet);
	
		}		
	}

	/*
	 * 
	 * @returnType: void
	 * @Description: Initializes the time for which the lights will remain green
	 */
	private void initializeGreenLightTotalTime() 
	{
		long defaultValue = 5000;
		if( this.lightTimings.size() < this.getGrid().getIntersections().size()*2)
		{
			for(Intersection intersection : grid.getIntersections())
			{
				TrafficLight horizontalTrafficLight = intersection.getHorizontalTrafficLight();
				TrafficLight verticalTrafficLight = intersection.getVerticalTrafficLight();
	
				greenLightTotalTime.put(horizontalTrafficLight.getTrafficLightID(), defaultValue);
				greenLightTotalTime.put(verticalTrafficLight.getTrafficLightID(), defaultValue);
	
			}	
		}
		else
		{
			for(Intersection intersection : grid.getIntersections())
			{
				TrafficLight horizontalTrafficLight = intersection.getHorizontalTrafficLight();
				TrafficLight verticalTrafficLight = intersection.getVerticalTrafficLight();
				double horizontalTime=this.lightTimings.get(horizontalTrafficLight.getTrafficLightID());
				double verticalTime=this.lightTimings.get(verticalTrafficLight.getTrafficLightID());
	
				greenLightTotalTime.put(horizontalTrafficLight.getTrafficLightID(), (long) horizontalTime*1000);
				greenLightTotalTime.put(verticalTrafficLight.getTrafficLightID(), (long) verticalTime*1000);
	
			}	
	
		}
	}

	//Set number of Cars per seconds
	public void setCarFrequencyForRoad(int roadID, double frequency1, double frequency2 )
	{
		Grid grid = this.getGrid();
		Road road=grid.getRoadByRoadID(roadID);
		road.setCarFrequency1(frequency1);
		road.setCarFrequency2(frequency2);
	}


	public Simulation(long totalTime, Grid grid) 
	{
		super();
		this.totalTime = totalTime;
		this.grid = grid;	
	}


	public long getTotalTime() 
	{
		return totalTime;
	}

	public void setTotalTime(long totalTime) 
	{
		this.totalTime = totalTime;
	}

	/*
	 * 
	 * @returnType: void
	 * @parameter: UserGridController
	 * @Description: runs simulation by starting the thread
	 */
	public void runSimulation(UserGridController gui) 
	{
		this.gui = gui;
		this.start();
	}
	
	/*
	 * 
	 * @returnType: void
	 * @Description: updates hashmap of the current traffic light colors for the GUI
	 */
	private void updateTrafficLightColour() 
	{
		Grid grid = this.getGrid();
		this.trafficLightColour.clear();
		for(Intersection intersection: grid.getIntersections())
		{
			TrafficLight vl=intersection.getVerticalTrafficLight();
			TrafficLight hl=intersection.getHorizontalTrafficLight();
			if(hl.isGreen())
				trafficLightColour.put(hl.getTrafficLightID(), "Green");
			else if(hl.isAmber())
				trafficLightColour.put(hl.getTrafficLightID(), "Amber");
			else 
				trafficLightColour.put(hl.getTrafficLightID(), "Red");
	
			if(vl.isGreen())
				trafficLightColour.put(vl.getTrafficLightID(), "Green");
			else if(vl.isAmber())
				trafficLightColour.put(vl.getTrafficLightID(), "Amber");
			else 
				trafficLightColour.put(vl.getTrafficLightID(), "Red");
		}
	
	}

	/*
	 * 
	 * @returnType: void
	 * @Description: updates hashmap of the current positions of cars for the GUI
	 */
	private void updateCarPlaceMap() 
	{
		Grid grid = this.getGrid();
		this.carPlaceMap.clear();
		for(Road tempRoad: grid.getRoads())
		{
			for(Place place:tempRoad.getPlaces())
			{
				if(place.hasCar()) 
					this.carPlaceMap.put(place.getCar().getRegNo(), place.getPlaceID());
			}
		}
	}

	/*
	 * 
	 * @returnType: void
	 * @Parameter: Road
	 * @Description: generates a new car at the start of the given road if possible
	 */
	public void generateNewCars(Road road, int side) 
	{
		Place place;
		if(side==1)
		{
			place = road.getFirstPlace();
	
			if(!place.hasCar())
			{
				if(unavailableCarIndex <= 99)
				{
					place.setCar(new Car(unavailableCarIndex));
					unavailableCarIndex++;
				}
				else
				{
					place.setCar(this.getGrid().getAvailableCars().get(0));
					this.getGrid().getAvailableCars().remove(0);
				}
	
			}
		}
		if(side==2)
		{
	
			place = road.getFirstPlaceOtherSide();
			if(!place.hasCar())
			{
				if(unavailableCarIndex <= 99)
				{
					place.setCar(new Car(unavailableCarIndex));
					unavailableCarIndex++;
	
				}
				else
				{
					place.setCar(this.getGrid().getAvailableCars().get(0));
					this.getGrid().getAvailableCars().remove(0);
				}
	
			}
		}
	}

	public Grid getGrid() 
	{
		return grid;
	}
	
	public void setGrid(Grid grid) 
	{
		this.grid = grid;
	}
	
	public Simulation() 
	{
		super();
	}
}
