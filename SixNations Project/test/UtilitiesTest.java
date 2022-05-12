import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class UtilitiesTest {
    private Country ireland, france;
    private ArrayList<Country> countries;
    private ArrayList<Club> clubListPopulated, clubListEmpty;
    private Club munster, leinster, rouen, ulster;
    private ArrayList<Player> players;
    private ArrayList<Club> handyClubList;
    Player playerJonny, playerPeter, playerLydon, playernewbie;


    @Before
    public void setUp() throws Exception {
        munster = new Club("Munster",
                "Johann van Graan", 70000);
        leinster = new Club("Leinster",
                "Leo Cullen", 100000);
        rouen = new Club("Rouen",
                "Richard Hill", 50000);
        ulster = new Club("Ulster", "Dan McFarland", 60000);
        players = new ArrayList<Player>();
        playerJonny = new Player("Jonny Sexton", "jonny@irfu.com", "08710");
        playerPeter = new Player("Peter O Mahoney", "peter@irfu.com", "08706");
        playerLydon = new Player("Peter Lydon", "peter@rouen.com", "08710");
        playernewbie = new Player("I'm just in", "newbie@excited.com", "08723");
        munster.addPlayer(playerPeter);
        leinster.addPlayer(playerJonny);
        rouen.addPlayer(playerLydon);

        handyClubList = new ArrayList<Club>();
        handyClubList.add(munster);
        handyClubList.add(leinster);
        handyClubList.add(rouen);
        //Setting up a Populated List
        clubListPopulated = new ArrayList<>();
        clubListPopulated.addAll(handyClubList);

        //Setting up an Empty List
        clubListEmpty = new ArrayList<>();

        ireland = new Country("Ireland", "Andy Farrell", 4);
        ireland.addClub(munster);
        ireland.addClub(leinster);

        france = new Country("France", "Fabien Galthié", 14);
        countries = new ArrayList<Country>();
        countries.add(ireland);
        countries.add(france);
    }

    @After
    public void teardown() {
        clubListPopulated = clubListEmpty = null;
        handyClubList = null;
        players = null;
        munster = leinster = rouen = ulster = null;
        playerJonny = playerPeter = playerLydon = playernewbie = null;
        ireland = france = null;
        countries = null;
    }


    @Test
    public void onlyContainsNumbers() {
        assertTrue(Utilities.onlyContainsNumbers("1234"));
        assertFalse(Utilities.onlyContainsNumbers("1aa234"));
    }

    @Test
    public void max30Chars() {
        assertTrue(Utilities.max30Chars("1234567890"));
        assertFalse(Utilities.max30Chars("123456789012345678901234567890-123"));
        assertTrue(Utilities.max30Chars(""));
    }

    @Test
    public void validEmail() {
        assertTrue(Utilities.validEmail("mmeagher@wit.ie"));
        assertFalse(Utilities.validEmail("mmeagherwit.ie"));
        assertFalse(Utilities.validEmail("mmeagher@witie"));
        assertFalse(Utilities.validEmail("mmeagherwitie"));
        assertFalse(Utilities.validEmail(""));
    }

    @Test
    public void validIntRange() {
        assertTrue(Utilities.validIntRange(1, 10, 5));
        assertTrue(Utilities.validIntRange(1, 10, 1));
        assertTrue(Utilities.validIntRange(1, 10, 10));
        assertFalse(Utilities.validIntRange(1, 10, 0));
        assertFalse(Utilities.validIntRange(1, 10, 11));
    }

    @Test
    public void validClub() {
        assertEquals(munster, Utilities.validClub("Munster", countries));
        assertNull(Utilities.validClub("exeter", countries));
        ArrayList<Country> empty = new ArrayList<Country>();
        assertNull(Utilities.validClub("Munster", empty));
    }
    @Test
    public void validPlayer() {
        assertEquals(playerPeter, Utilities.validPlayer("Peter O Mahoney", munster));
        assertEquals(playerJonny, Utilities.validPlayer("Jonny Sexton", leinster));
        assertNull(Utilities.validPlayer("Jonny Sexton", munster));
        assertNull(Utilities.validPlayer("Mairead Meagher", munster));
    }
    @Test
    public void validIndex() {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("One");
        strings.add("Two");
        strings.add("Three");
        strings.add("Four");
        assertTrue(Utilities.validIndex(2, strings));
        assertTrue(Utilities.validIndex(0, strings));
        assertFalse(Utilities.validIndex(-1, strings));
        assertTrue(Utilities.validIndex(3, strings));
        assertFalse(Utilities.validIndex(4, strings));
    }

    @Test
    public void validPositive() {
        assertTrue(Utilities.validPositive(10));
        assertTrue(Utilities.validPositive(1));
        assertFalse(Utilities.validPositive(0));
        assertFalse(Utilities.validPositive(-1));
    }

    @Test
    public void validNonNegative() {
        assertTrue(Utilities.validNonNegative(10));
        assertTrue(Utilities.validNonNegative(0));
        assertFalse(Utilities.validNonNegative(-1));
        assertFalse(Utilities.validNonNegative(-10));
    }

}
