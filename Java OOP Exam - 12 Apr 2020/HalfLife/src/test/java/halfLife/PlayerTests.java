package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayerTests {

    private Player player;
    private Gun gun;


    @Before
    public void constructor() {
        this.player = new Player("test", 2);
        this.gun = new Gun("testGun", 1);

    }

    @Test(expected = NullPointerException.class)
    public void testSetNameNull() {
        Player player = new Player(null, 1);


    }

    @Test(expected = NullPointerException.class)
    public void testSetNameEmpty() {
        Player player = new Player("", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHealtUnderZero() {
        Player player = new Player("a", -1);
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("test", player.getUsername());
    }

    @Test
    public void test() {
        Assert.assertEquals(1, player.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testNullGun() {
        player.addGun(null);
    }

    @Test
    public void testRemoveExistingGun(){
        player.addGun(gun);

        Assert.assertTrue(player.removeGun(gun));
    }

    @Test
    public void testRemoveWrongGun(){
        player.addGun(gun);

        Assert.assertFalse(player.removeGun(new Gun("112",11)));
    }

    @Test
    public void  testGetGunByName(){
        player.addGun(gun);
        Assert.assertEquals(gun,player.getGun("testGun"));


    }

    @Test
    public void testGetGunCollection(){
        List<Gun>guns=new ArrayList<>();
        guns.add(gun);
        guns.add(gun);
        player.addGun(gun);
        player.addGun(gun);
        Assert.assertEquals(guns,player.getGuns());

    }
    @Test(expected = IllegalStateException.class)
    public void testIfPlayerIsDead(){
        player.takeDamage(2);
        player.takeDamage(2);
    }
@Test
    public void testPlayerTakeDMG(){
        player.takeDamage(1);
        Assert.assertEquals(1,player.getHealth());

}

}
