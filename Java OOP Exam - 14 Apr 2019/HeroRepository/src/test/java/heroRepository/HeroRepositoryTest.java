package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class HeroRepositoryTest {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS HeroRepository
    private HeroRepository heroRepository;
    private Hero hero;
    private Item item;

    @Before
    public void constructor() {
        this.heroRepository = new HeroRepository();
        this.item = new Item(1, 1, 1);
        this.hero = new Hero("test", 1, item);
    }

    @Test
    public void constructorCreationArrayList() {
        Assert.assertEquals(0, this.heroRepository.getCount());
    }

    @Test
    public void testDataCount() {
        heroRepository.add(hero);
        Assert.assertEquals(1, heroRepository.getCount());
    }

    @Test
    public void testAddRealHero() {
        heroRepository.add(hero);
        Assert.assertEquals(1, heroRepository.getCount());
        Assert.assertEquals(hero, heroRepository.getHeroWithHighestIntelligence());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testDatContainsAddedHero() {
        this.heroRepository.add(this.hero);
        this.heroRepository.add(this.hero);
    }

    @Test(expected = NullPointerException.class)
    public void testRemovedHeroDoesNotExist() {
        this.heroRepository.remove("Jamal");
    }

    @Test
    public void testRemoveExistingHero() {
        this.heroRepository.add(hero);
        this.heroRepository.remove("test");
        Assert.assertEquals(0, this.heroRepository.getCount());
    }


    @Test(expected = NullPointerException.class)
    public void testNoHeroStrength() {
        this.heroRepository.getHeroWithHighestStrength();

    }

    @Test
    public void testHeroWithHighestStr() {
        Hero highStr = new Hero("Str", 1, new Item(2, 1, 1));

        this.heroRepository.add(hero);
        this.heroRepository.add(highStr);

        Assert.assertEquals(highStr, heroRepository.getHeroWithHighestStrength());

    }


    @Test(expected = NullPointerException.class)
    public void testNoHerosAgility() {
        this.heroRepository.getHeroWithHighestAgility();

    }

    @Test
    public void testHeroWithHighestAgility() {

        Hero highAgility = new Hero("Str", 1, new Item(1, 2, 1));

        this.heroRepository.add(hero);
        this.heroRepository.add(highAgility);
        Assert.assertEquals(highAgility, heroRepository.getHeroWithHighestAgility());


    }


    @Test(expected = NullPointerException.class)
    public void testNoHeroIntelligence() {
        this.heroRepository.getHeroWithHighestStrength();

    }

    @Test
    public void testHeroWithHighestInt() {

        Hero highInt = new Hero("Str", 1, new Item(1, 1, 2));

        this.heroRepository.add(hero);
        this.heroRepository.add(highInt);
        Assert.assertEquals(highInt, heroRepository.getHeroWithHighestIntelligence());
    }

    @Test
    public void testToStringMethod() {
        heroRepository.add(hero);
        Assert.assertEquals(hero.toString(), heroRepository.toString());

    }

}