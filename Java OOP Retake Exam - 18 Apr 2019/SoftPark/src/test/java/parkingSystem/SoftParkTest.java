package parkingSystem;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SoftParkTest {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS SoftPark
    private SoftPark softPark;
    private Car car;

    @Before
    public void constructor() {
        this.softPark = new SoftPark();
        this.car = new Car("test", "1234");
    }

    @Test
    public void testCreationConstructor() {
        Map<String, Car> parking = new HashMap<>();
        parking.put("A1", null);
        parking.put("A2", null);
        parking.put("A3", null);
        parking.put("A4", null);
        parking.put("B1", null);
        parking.put("B2", null);
        parking.put("B3", null);
        parking.put("B4", null);
        parking.put("C1", null);
        parking.put("C2", null);
        parking.put("C3", null);
        parking.put("C4", null);
        Assert.assertEquals(parking.size(), softPark.getParking().size());


    }


    @Test
    public void testGetParking() {
        testCreationConstructor();
    }

    @Test
    public void testParkCar() {
        softPark.parkCar("A1", car);
        Car parkedCar = softPark.getParking().get("A1");
        Assert.assertEquals(car, parkedCar);


    }

    @Test(expected = IllegalStateException.class)
    public void testParkExistingCar() {
        softPark.parkCar("A1", car);
        softPark.parkCar("A2", car);


    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkCarWrongSpot() {
        this.softPark.parkCar("z1", car);
    }

    @Test
    public void testAddCarSuccessfulMassage() {

        String msg = "Car:1234 parked successfully!";
        Assert.assertEquals(msg, softPark.parkCar("A1", car));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testTryingToParkOnOccupiedSpot() {
        softPark.parkCar("A1", car);
        softPark.parkCar("A1", new Car("ASw", "123"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNotFoundCar() {
        softPark.removeCar("A1", car);


    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCarFromSpotThatDoesNotexist() {

        softPark.removeCar("z1", car);


    }

    @Test
    public void testRemoveCarFromSpot() {
        softPark.parkCar("A1", car);
        softPark.removeCar("A1", car);
        Assert.assertNull(softPark.getParking().get("A1"));
    }

    @Test
    public void testMsgFromRemovedCar() {
        softPark.parkCar("A1", car);
        String msg = "Remove car:1234 successfully!";
        Assert.assertEquals(msg, softPark.removeCar("A1", car));


    }
}