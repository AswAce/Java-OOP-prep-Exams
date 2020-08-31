package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {
    private Hero hero;
    private Item testItem;

    @Before
    public void createHero() {
        this.testItem = new Item(1, 1, 1);
        String nameTest = "test";
        int level = 1;
        this.hero = new Hero(nameTest, level, testItem);
    }

    @Test
    public void test_ConstructorSetUp() {

        Assert.assertEquals(hero.getName(), "test");
        Assert.assertEquals(hero.getLevel(), 1);
        Assert.assertEquals(hero.getItem().getAgility(), 1);
        Assert.assertEquals(hero.getItem().getStrength(), 1);
        Assert.assertEquals(hero.getItem().getIntelligence(), 1);
    }

    @Test
    public void test_IfHeroEqualsObject() {
        Hero heroExpected = new Hero("test", 1, testItem);
        assertTrue(this.hero.equals(heroExpected));
    }

    @Test
    public void testHeroNameEqualsOnjectName() {
        Hero heroExpected = new Hero("test", 1, testItem);
        assertEquals(this.hero.getName(), heroExpected.getName());

    }

    @Test
    public void testHeroEqualsNullFalseREturn() {
        assertFalse(this.hero.equals(null));

    }
@Test
    public void testHeroGetCLassNotEqualToObjectGetClass(){
        assertFalse(this.hero.equals(new Object()));


}

}