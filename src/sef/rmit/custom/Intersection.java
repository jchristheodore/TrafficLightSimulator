/*
 * @author:
 */

package sef.rmit.custom;

import java.util.ArrayList;

public class Intersection 
{
	private int intersectionID;
	private TrafficLight horizontalTrafficLight;
	private TrafficLight verticalTrafficLight;
	private ArrayList<Place> places = new ArrayList<Place>();
	
	public ArrayList<Place> getPlaces() 
	{
		return places;
	}

	public void setPlaces(ArrayList<Place> places) 
	{
		this.places = places;
	}

	public TrafficLight getHorizontalTrafficLight() 
	{
		return horizontalTrafficLight;
	}
	
	public void setHorizontalTrafficLight(TrafficLight horizontalTrafficLight) 
	{
		this.horizontalTrafficLight = horizontalTrafficLight;
	}
	
	public Intersection(int intersectionID, TrafficLight horizontalTrafficLight, TrafficLight verticalTrafficLight) 
	{
		super();
		this.intersectionID = intersectionID;
		this.horizontalTrafficLight = horizontalTrafficLight;
		this.verticalTrafficLight = verticalTrafficLight;
	}
	
	public TrafficLight getVerticalTrafficLight() 
	{
		return verticalTrafficLight;
	}
	
	public void setVerticalTrafficLight(TrafficLight verticalTrafficLight) 
	{
		this.verticalTrafficLight = verticalTrafficLight;
	}
	
	public int getIntersectionID() 
	{
		return intersectionID;
	}
	
	public void setIntersectionID(int intersectionID) 
	{
		this.intersectionID = intersectionID;
	}

	
	
	@Override
	public String toString() 
	{
		return "Intersection [intersectionID=" + intersectionID + ", horizontalTrafficLight=" + horizontalTrafficLight
				+ ", verticalTrafficLight=" + verticalTrafficLight + ", places=" + places + "]";
	}

	public Intersection(int intersectionID) 
	{
		super();
		this.intersectionID = intersectionID;
	}

}
