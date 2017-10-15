/*
 * @author:
 */

package sef.rmit.custom;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimulationTest {

	private Grid grid;
	Simulation simulation;

	@Before 
	public void setUp() { 
		Grid g = new Grid();
		grid = g.createDefaultGrid();
		simulation = new Simulation();
		simulation.setGrid(grid);
	}

	@Test
	public void testGenerateNewCars()
	{
		grid.initializeTrafficLights();
		for(Road road: grid.getRoads())
		{ 
			simulation.generateNewCars(road, 1);
			simulation.generateNewCars(road, 2);

			assertTrue(road.getFirstPlace().hasCar());
			assertTrue(road.getFirstPlaceOtherSide().hasCar());
		}
	}
	
	@Test
	public void testSetCarFrequencyForRoad()
	{
		grid.initializeTrafficLights();
		double frequency1 = 3.0;
		double frequency2 = 4.0;
		double epsilon = 0.01;
		for(Road road: grid.getRoads())
		{ 
			simulation.setCarFrequencyForRoad(road.getRoadID(), frequency1, frequency2);

			assertEquals(road.getCarFrequency1(),frequency1,epsilon);
			assertEquals(road.getCarFrequency2(),frequency2,epsilon);
		}
	}
	
	@Test
	public void testInitializeGreenLightStartTime()
	{
		simulation.initializeGreenLightStartTime();
		for(Intersection intersection: grid.getIntersections())
		{ 
			TrafficLight horrizontal = intersection.getHorizontalTrafficLight();
			TrafficLight vertical = intersection.getVerticalTrafficLight();
			
			assertEquals(simulation.getGreenLightStartTime().get(horrizontal.getTrafficLightID()), System.currentTimeMillis(), 0.01);
			assertEquals(simulation.getGreenLightStartTime().get(vertical.getTrafficLightID()), 0, 0.01);
		}
	}
	

}
