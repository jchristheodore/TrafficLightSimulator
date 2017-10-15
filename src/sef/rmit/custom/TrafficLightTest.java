/*
 * @author:
 */

package sef.rmit.custom;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TrafficLightTest {

	
	private TrafficLight trafficLight;

	@Before 
	public void setUp() {
		int trafficLightID = 1;
		trafficLight = new TrafficLight(trafficLightID);
	}
	
	@Test
	public void testSetGreen() {
		boolean isGreen = true;
		trafficLight.setGreen(isGreen);
		assertTrue(trafficLight.isGreen());
		assertFalse(trafficLight.isRed());
		assertFalse(trafficLight.isAmber());
	}
	
	@Test
	public void testSetRed() {
		boolean isRed = true;
		trafficLight.setRed(isRed);
		assertFalse(trafficLight.isGreen());
		assertTrue(trafficLight.isRed());
		assertFalse(trafficLight.isAmber());
	}
	
	@Test
	public void testSetAmber() {
		boolean isAmber = true;
		trafficLight.setAmber(isAmber);
		assertFalse(trafficLight.isGreen());
		assertFalse(trafficLight.isRed());
		assertTrue(trafficLight.isAmber());
	}

}
