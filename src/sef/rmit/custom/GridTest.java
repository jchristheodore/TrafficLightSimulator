/*
 * @author:
 */

package sef.rmit.custom;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GridTest {

	private Grid defaultGrid;
	
	@Before 
	public void setUp() { 
		Grid grid = new Grid();
		defaultGrid = grid.createDefaultGrid();
	}

	@Test
	public void testIsActive() {
		defaultGrid.setAsActive();
		assertTrue(defaultGrid.isActive());
	}

	
	@Test
	public void testIsPlanned() {
		defaultGrid.setAsPlanned();
		assertTrue(defaultGrid.isPlanned());
	}
	
	@Test
	public void testGetIntersectionAt() {
		Intersection intersection = new Intersection(12);
		defaultGrid.getIntersections().add(intersection);
		assertEquals(defaultGrid.getIntersectionAt(1,2), intersection);
	}
	
	@Test
	public void testUpdateTrafficLightColour()
	{
		defaultGrid.initializeTrafficLights();
		for(Intersection intersection: defaultGrid.getIntersections())
		{ 
			TrafficLight horrizontal = intersection.getHorizontalTrafficLight();
			TrafficLight vertical = intersection.getVerticalTrafficLight();
			
			assertTrue(horrizontal.isGreen());
			assertTrue(vertical.isRed());
		}
	}	


	
}
