/*
 * @author:
 */

package sef.rmit.custom;


import java.util.ArrayList;
import java.util.Collections;

public class Road 
{
	private int roadID;
	private double carFrequency1 = 1.0; //Default value set as 1 car per second
	private double carFrequency2 = 1.0;
	private int carsPassedSide1 = 0;
	private int carsPassedSide2 = 0;
	private Grid grid;
	private final int placeIdMultiplier = 100;
	private final int sideOneStartPlaceIndex = 11;
	private final int sideOneLastPlaceIndex = 19;
	private final int sideTwoStartPlaceIndex = 21;
	private final int sideTwoLastPlaceIndex = 29;
	
	
	public Grid getGrid() 
	{
		return grid;
	}

	public void setGrid(Grid grid) 
	{
		this.grid = grid;
	}

	ArrayList<Place> places = new ArrayList<Place>();


	@Override
	public String toString() {
		return "Road [roadID=" + roadID + ", carFrequency1=" + carFrequency1 + ", carFrequency2=" + carFrequency2
				+ ", places=" + places + "]";
	}

	public double getCarFrequency2() {
		return carFrequency2;
	}

	public void setCarFrequency2(double carFrequency2) {
		this.carFrequency2 = carFrequency2;
	}

	public void setCarFrequency1(double d) 
	{
		this.carFrequency1 = d;
	}

	public double getCarFrequency1() 
	{
		return carFrequency1;
	}

	public ArrayList<Place> getPlaces() 
	{
		return places;
	}

	public void setPlaces(ArrayList<Place> places)
	{
		this.places = places;
	}

	public Road(int roadID, ArrayList<Place> places) 
	{
		super();
		this.roadID = roadID;
		this.places = places;
	}

	/*
	 * 
	 * @CONSTRUCTOR
	 * @Description: creates a road object and its place objects
	 */
	public Road(int roadID) 
	{
		super();
		int firstPlaceSideOneID = roadID*this.placeIdMultiplier+ this.sideOneStartPlaceIndex;
		int lastPlaceSideOneID = roadID*this.placeIdMultiplier+this.sideOneLastPlaceIndex;
		int firstPlaceSideTwoID = roadID*this.placeIdMultiplier+this.sideTwoStartPlaceIndex;
		int lastPlaceSideTwoID = roadID*this.placeIdMultiplier+this.sideTwoLastPlaceIndex;
		this.roadID = roadID;
		for (int placeID = firstPlaceSideOneID; placeID<=lastPlaceSideOneID; placeID++)
		{
			Place place = new Place(placeID);
			if(roadID<4) place.setOnHorizontalRoad(false);
			else  place.setOnHorizontalRoad(true);
			this.places.add(place);
		}
		for (int placeID = firstPlaceSideTwoID; placeID<=lastPlaceSideTwoID; placeID++)
		{
			Place place = new Place(placeID);
			if(roadID<4) place.setOnHorizontalRoad(false);
			else  place.setOnHorizontalRoad(true);	
			this.places.add(place);
		}
	}

	public int getRoadID() 
	{
		return roadID;
	}

	public void setRoadID(int roadID) 
	{
		this.roadID = roadID;
	}

	public ArrayList<Place> getList() 
	{
		return places;
	}

	public void setList(ArrayList<Place> places) 
	{
		this.places = places;
	}

	/*
	 * 
	 * @returnType: boolean
	 * @Description: Moves all the cars on this road
	 */
	public void moveCarsOnRoad()
	{
		Collections.reverse(this.getPlaces());
		for(Place place:this.getPlaces())
		{
			moveCarAhead(place);
		}
		Collections.reverse(this.getPlaces());
	}

	/*
	 * 
	 * @returnType: Place; Returns the place where the car has moved to
	 * @Parameter: Place; The place object on which the car currently is
	 * @Description: Moves car ahead by one place, only if it is possible to move the car
	 */
	public Place moveCarAhead(Place place) 
	{
		if(place.hasCar()) 
		{
			Car car = place.getCar();
			if(carCanMoveFrom(place)) 
			{
				Place nextPlace = this.getNextPlace(place.getPlaceID());
				if(nextPlace != null) 
				{
					nextPlace.setCar(car);
					place.setCar(null);

				}
				else
				{
					this.getGrid().availableCars.add(place.getCar());
					place.setCar(null);
					
					if(place.getPlaceID()%100==19)
					this.setCarsPassedSide1(this.getCarsPassedSide1()+1);
					else
					this.setCarsPassedSide2(this.getCarsPassedSide2()+1);
				}
				place=nextPlace;
			}
		}
		return place;
	}

	/*
	 * 
	 * @returnType: integer
	 * @Description: Returns the number of cars that passed on side 1 of this road
	 */
	public int getCarsPassedSide1() 
	{
		return carsPassedSide1;
	}

	public void setCarsPassedSide1(int carsPassedSide1) 
	{
		this.carsPassedSide1 = carsPassedSide1;
	}

	/*
	 * 
	 * @returnType: integer
	 * @Description: Returns the number of cars that passed on side 2 of this road
	 */
	public int getCarsPassedSide2() 
	{
		return carsPassedSide2;
	}

	public void setCarsPassedSide2(int carsPassedSide2) 
	{
		this.carsPassedSide2 = carsPassedSide2;
	}

	/*
	 * 
	 * @returnType: boolean
 	 * @Parameter: Place; The place object on which the car currently is
	 * @Description: Returns true if it is possible for the car to move ahead from this place
	 */
	public boolean carCanMoveFrom(Place place) 
	{
		Place nextPlace = this.getNextPlace(place.getPlaceID());
		if(nextPlace == null)
		{
			return true;		
		}
		else 
		{
			if(nextPlace.hasCar())
			{
				return false;
			}
			else 
			{
				if(nextPlace.isIntersection()){
					Intersection intersection = nextPlace.getIntersection();
					TrafficLight tl;

					if(nextPlace.isOnHorizontalRoad()) {
						tl = intersection.getHorizontalTrafficLight();

						if(tl.isGreen() )
						{
							for(Place p: intersection.getPlaces())
							{
								if(!p.isOnHorizontalRoad() && p.hasCar())
								{
									return false;
								}
							}

							return true;
						}
						else
						{
							return false;
						}
					}
					else {
						tl = intersection.getVerticalTrafficLight();
						if(tl.isGreen())
						{
							for(Place p: intersection.getPlaces())
							{
								if(p.isOnHorizontalRoad() && p.hasCar())
								{
									return false;
								}
							}
							return true;
						}
						else
						{
							return false;
						}
					}


				}
				else
				{
					return true;
				}
			}
		}
	}


	public Place getNextPlace(int placeID) 
	{
		for(Place tempPlace : this.places)
		{
			if(tempPlace.getPlaceID() == (placeID+1))
				return tempPlace;
		}

		return null;
	}

	public Place getThisPlace(int placeID) 
	{
		for(Place tempPlace : this.places)
		{
			if(tempPlace.getPlaceID() == (placeID))
				return tempPlace;
		}

		return null;
	}

	public Place getFirstPlace() 
	{
		int firstPlaceID = 0;
		return this.places.get(firstPlaceID);
	}

	public Place getFirstPlaceOtherSide() 
	{	
		int lastPlaceID = 9;
		return this.places.get(lastPlaceID);
	}

}
