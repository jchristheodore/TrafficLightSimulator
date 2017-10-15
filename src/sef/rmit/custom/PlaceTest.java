/*
 * @author:
 */

package sef.rmit.custom;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlaceTest {

	Place place;
	
	@Before 
	public void setUp() { 
		place = new Place(0);
	}

	@Test
	public void testHasCar() {
		Car car = new Car(0);
		place.setCar(car);
		assertTrue(place.hasCar());
		
	}

	@Test
	public void testIsOnHorrizontalRoad() {
		place.setOnHorizontalRoad(true);
		assertTrue(place.isOnHorizontalRoad());
	}

	@Test
	public void testIsOnNotHorrizontalRoad() {
		place.setOnHorizontalRoad(false);
		assertFalse(place.isOnHorizontalRoad());
	}


}
