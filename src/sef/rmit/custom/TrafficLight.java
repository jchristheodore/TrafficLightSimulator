/*
 * @author:
 */

package sef.rmit.custom;

public class TrafficLight
{
	private int trafficLightID;
	private boolean isGreen=true;
	private boolean isAmber=false;
	private boolean isRed=false;
	private float timeForGreen=3; //DEFAULT VALUE is 3 seconds
	
	
	public TrafficLight() 
	{
		super();
	}
	
	public TrafficLight(int trafficLightID) 
	{
		super();
		this.trafficLightID = trafficLightID;
	}


	@Override
	public String toString() 
	{
		return "TrafficLight [trafficLightID=" + trafficLightID + ", isGreen=" + isGreen + ", isAmber=" + isAmber
				+ ", isRed=" + isRed + ", timeforGreen=" + timeForGreen + "]";
	}


	public int getTrafficLightID() 
	{
		return trafficLightID;
	}
	
	public void setTrafficLightID(int trafficLightID) 
	{
		this.trafficLightID = trafficLightID;
	}
	
	/*
	 * 
	 * @returnType: boolean
	 * @Description: returns true if the light is green
	 */
	public boolean isGreen() 
	{
		return isGreen;
	}
	
	/*
	 * 
	 * @returnType: void
	 * @Description: sets the Traffic light to green
	 */
	public void setGreen(boolean isGreen) 
	{
		this.isGreen = isGreen;
		if(isGreen)
		{	this.isRed = false;
			this.isAmber = false;
		}
	}
	
	/*
	 * 
	 * @returnType: boolean
	 * @Description: returns true if the light is amber
	 */
	public boolean isAmber() 
	{
		return isAmber;
	}
	
	/*
	 * 
	 * @returnType: void
	 * @Description: sets the Traffic light to amber
	 */
	public void setAmber(boolean isAmber) 
	{
		this.isAmber = isAmber;
		if(isAmber)
		{	this.isRed = false;
			this.isGreen = false;
		}
	}
	
	/*
	 * 
	 * @returnType: boolean
	 * @Description: returns true if the light is red
	 */
	public boolean isRed() 
	{
		return isRed;
	}
	
	/*
	 * 
	 * @returnType: void
	 * @Description: sets the Traffic light to red
	 */
	public void setRed(boolean isRed) 
	{
		this.isRed = isRed;
		if(isRed)
		{	this.isAmber = false;
			this.isGreen = false;
		}
	}
	
	/*
	 * 
	 * @returnType: float
	 * @Description: gets the time for which the light has to stay green
	 */
	public float getTimeForGreen() 
	{
		return timeForGreen;
	}

	/*
	 * 
	 * @returnType: void
	 * @Parameter: float value of time
	 * @Description: sets the time for which the light has to stay green
	 */
	public void setTimeForGreen(float timeForGreen) 
	{
		this.timeForGreen = timeForGreen;
	}
	
}
