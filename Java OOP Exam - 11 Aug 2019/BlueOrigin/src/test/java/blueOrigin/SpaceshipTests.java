package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Spaceship
    private Spaceship spaceship;
    private Astronaut astronaut;

    @Before
    public void constructor() {
        this.spaceship = new Spaceship("test", 3);
        this.astronaut = new Astronaut("Asen", 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetUpNameNull() {
        Spaceship spaceship = new Spaceship(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testSetUpNameEmpty() {
        Spaceship spaceship = new Spaceship("", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCapacity() {
        Spaceship spaceship = new Spaceship("test", -1);
    }

    @Test
    public void testGetNameShip() {
        String expectedName = "test";
        Assert.assertEquals(expectedName, spaceship.getName());

    }

    @Test
    public void testGetCapacity() {
        int expectedCapacity = 3;
        Assert.assertEquals(expectedCapacity, spaceship.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSpaceshipIsFull() {
        spaceship.add(astronaut);
        spaceship.add(new Astronaut("test1", 22));
        spaceship.add(new Astronaut("test2", 22));
        spaceship.add(new Astronaut("test3", 22));


    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddExistingAstronaut() {
        spaceship.add(astronaut);
        spaceship.add(astronaut);
    }

    @Test
    public void testAddAstonaut() {
        spaceship.add(astronaut);

        Assert.assertEquals(1, spaceship.getCount());
    }


        @Test
    public void  testRemoveAstronautTrue(){
        spaceship.add(astronaut);
       ;
        Assert.assertTrue( spaceship.remove("Asen"));
        }
    @Test
    public void  testRemoveAstronautFalse(){
        spaceship.add(astronaut);
        ;
        Assert.assertFalse( spaceship.remove("WrongName"));
    }
}
