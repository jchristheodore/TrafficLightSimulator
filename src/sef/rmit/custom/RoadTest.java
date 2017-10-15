/*
 * @author:
 */

package sef.rmit.custom;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RoadTest {
	
	private Road road;

	@Before
	public void setUp() {
		int roadId = 1;
		road = new Road(roadId);
	}

	@Test
	public void testMoveCarAhead() {
		int carID = 0;
		Car car = new Car(carID);
		Place place = road.getFirstPlace();
		place.setCar(car);
		road.moveCarAhead(place);
		assertTrue(road.getNextPlace(place.getPlaceID()).hasCar());
	}
	
	@Test
	public void testCanCarMoveFrom() {
		int carID = 0;
		int car2ID = 1;
		Car car = new Car(carID);
		Car car2 = new Car(car2ID);

		Place place = road.getFirstPlace();
		place.setCar(car);
		assertTrue(road.carCanMoveFrom(place));
		
		road.getNextPlace(place.getPlaceID()).setCar(car2);
		assertFalse(road.carCanMoveFrom(place));
	}
	
	@Test
	public void testMoveCarsOnRoad() {
		Car car0 = new Car(0);
		Car car1 = new Car(1);
		Car car2 = new Car(2);
		Car car3 = new Car(3);

		road.getPlaces().get(0).setCar(car0);
		road.getPlaces().get(1).setCar(car1);
		road.getPlaces().get(2).setCar(car2);
		road.getPlaces().get(5).setCar(car3);
		road.moveCarsOnRoad();

		assertTrue(road.getPlaces().get(1).getCar().getRegNo()==0);
		assertTrue(road.getPlaces().get(2).getCar().getRegNo()==1);
		assertTrue(road.getPlaces().get(3).getCar().getRegNo()==2);
		assertTrue(road.getPlaces().get(6).getCar().getRegNo()==3);
	}

}
