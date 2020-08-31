package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class HeroRepositoryTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS HeroRepository
    private HeroRepository heroRepository;
    private Hero hero;

    @Before
    public void constructor() {
        this.heroRepository = new HeroRepository();
        this.hero = new Hero("Asen", 1);
    }

    @Test
    public void testConstructorCreation() {
        ;

        Assert.assertEquals(0, heroRepository.getCount());

    }


    @Test
    public void testConstructorSze() {
        heroRepository.create(hero);
        Assert.assertEquals(1, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testHeroCreation() {
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHeroAlreadyCreated() {
        heroRepository.create(hero);
        heroRepository.create(hero);

    }

    @Test
    public void testSuccessfullyAddedHero() {
        Assert.assertEquals(String.format("Successfully added hero %s with level %d",
                this.hero.getName(), hero.getLevel()),
                heroRepository.create(hero));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullHero() {
        heroRepository.remove(null);
    }


    @Test(expected = NullPointerException.class)
    public void testRemoveEmptyHero() {
        heroRepository.remove("");
    }

    @Test
    public void testSuccessesfullyRemovedHero() {
        heroRepository.create(hero);
        Assert.assertTrue(heroRepository.remove("Asen"));

    }

    @Test
    public void testWrongNameRemoved() {
        heroRepository.create(hero);
        Assert.assertFalse(heroRepository.remove("AAsen"));

    }

    @Test
    public void testGetHeroWithHighestLevel() {
        heroRepository.create(hero);
        Hero hero1 = new Hero("TestLevel", 99);
        heroRepository.create(hero1);

        Assert.assertEquals(hero1, heroRepository.getHeroWithHighestLevel());

    }
    @Test
    public void testGetHeroByName(){
        heroRepository.create(hero);

        Assert.assertEquals(hero, heroRepository.getHero("Asen"));
    }

    @Test
    public void testGetHeroCollection(){
        HeroRepository heroRepository1 =new HeroRepository();

        heroRepository.create(hero);
        Collection<Hero>expected= new ArrayList<>();
        expected.add(hero);

        Assert.assertEquals(expected, new ArrayList<>(heroRepository.getHeroes()));
    }
}
