package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ComputerTest {
    private Computer computer;
    private Part part;

    @Before
    public void constructor() {
        this.computer = new Computer("test");
        this.part = new Part("test", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNameWithNull() {
        Computer computer = new Computer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNameWithEmpty() {
        Computer computer = new Computer("");
    }

    @Test
    public void testGetName() {

        Assert.assertEquals("test", computer.getName());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullPart() {
        computer.addPart(null);
    }

    @Test
    public void testListOfParts() {
        computer.addPart(part);
        List<Part> parts = new ArrayList<>();
        parts.add(part);
        Assert.assertEquals(computer.getParts(), parts);
    }

    @Test
    public void testRemoveCorrectPart() {
        computer.addPart(part);
        Assert.assertTrue(computer.removePart(part));
    }

    //
    @Test
    public void testRemoveWrongPartFalse() {
        computer.addPart(part);
        Assert.assertFalse(computer.removePart(new Part("12", 22)));
    }

    @Test
    public void testGetPricesForParts() {
        computer.addPart(part);
        computer.addPart(part);
        boolean result = false;
        if (2 == computer.getTotalPrice()) {
            result = true;
        }
        Assert.assertTrue(result);
    }

    @Test
    public void testGetPartByName() {
        computer.addPart(part);
        Assert.assertEquals(part, computer.getPart("test"));


    }
}