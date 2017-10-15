/*
 * @author:
 */

package sef.rmit.custom;


public class Place
{
	private int placeID;
	private Car car;
	private Intersection intersection;
	private boolean isOnHorizontalRoad;
	
	/*
	 * 
	 * @returnType: boolean
	 * @Description: Returns true if the place is on a horizontal road
	 */
	public boolean isOnHorizontalRoad() 
	{
		return isOnHorizontalRoad;
	}

	/*
	 * 
	 * @returnType: void
	 * @Description: Sets place on horrizontal Road
	 */
	public void setOnHorizontalRoad(boolean isOnHorizontalRoad) 
	{
		this.isOnHorizontalRoad = isOnHorizontalRoad;
	}

	public Intersection getIntersection() 
	{
		return intersection;
	}

	public void setIntersection(Intersection intersection) 
	{
		this.intersection = intersection;
	}

	public Place(int placeID) 
	{
		super();
		this.placeID = placeID;
	}
	
	public Place(int placeID, boolean isOnHorizontalRoad) 
	{
		super();
		this.placeID = placeID;
		this.isOnHorizontalRoad = isOnHorizontalRoad;
	}

	@Override
	public String toString() {
		return "Place [placeID=" + placeID + ", car=" + car + ", isOnHorizontalRoad="
				+ isOnHorizontalRoad + "]";
	}

	public int getPlaceID() 
	{
		return placeID;
	}
	
	public void setPlaceID(int placeID) 
	{
		this.placeID = placeID;
	}
	
	public Car getCar() 
	{
		return car;
	}
	
	public void setCar(Car car) 
	{
		this.car = car;
	}
	
	public boolean hasCar() 
	{
		return this.getCar() != null;
	}
	
	/*
	 * 
	 * @returnType: boolean
	 * @Description: Returns true if the place is on an Intersection
	 */
	public boolean isIntersection() 
	{
		return this.getIntersection() != null;
	}
	
	
}
