import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class SixNationsDriverTest {
    SixNationsDriver sixNations;
    private ArrayList<Country> countries;
    private Country  ireland, france, wales;

    private Player playerJonny, playerGarry, playerPeter, playerLydon, playerAaron, playerAlun, playerJustin, playerLeigh, playerCraig, playerThomas, playerAndrew;
    private Club munster, leinster, rouen, ospreys, dragons;


    @Before
    public void setUp() throws Exception {
        sixNations = new SixNationsDriver(1);
        countries = new ArrayList<Country>();

        ireland = new Country("Ireland", "Andy Farrell", 4);

        munster = new Club("Munster",
                "Johann van Graan", 70000);
        leinster = new Club("Leinster",
                "Leo Cullen", 100000);
        rouen = new Club("Rouen",
                "Richard Hill", 500000);   //"biggest club" (by number of members)

        ospreys = new Club("Ospreys", "Toby Booth", 15000);
        dragons = new Club("Dragons", "Dean Ryan", 20000);

        playerJonny = new Player("Jonny Sexton", "jonny@irfu.com", "08710");
        playerGarry = new Player("Garry Ringrose", "garry@irfu.com", "08712");
        playerPeter = new Player("Peter O Mahoney", "peter@irfu.com", "08706");
        playerAndrew = new Player("Andrew Conway", "andrew@munster.ie", "08714");
        playerThomas = new Player("Thomas Aherne", "thomas@munster.ie", "08704");
        playerCraig = new Player("Craig Casey", "little@munster.com", " 08709");
        //france
        playerLydon = new Player("Peter Lydon", "peter@rouen.com", "08710");
        //welsh players
        playerAlun = new Player("Alun Wyn Jones", "alan@wales.com", " 08709");
        playerJustin = new Player("Justin Tipric", "justin@wales.com", " 087013");
        playerLeigh = new Player("Leigh Halpenny", "leigh@wales.com", " 08715");
        playerAaron = new Player("Aaron Davies", "aaron@wales.com", "08712");

        munster.addPlayer(playerPeter);
        munster.addPlayer(playerCraig);
        munster.addPlayer(playerAndrew);
        munster.addPlayer(playerThomas);

        leinster.addPlayer(playerJonny);
        leinster.addPlayer(playerGarry);

        rouen.addPlayer(playerLydon);

        ospreys.addPlayer(playerAlun);
        ospreys.addPlayer(playerJustin);
        ospreys.addPlayer(playerLeigh);
        dragons.addPlayer(playerAaron);


        ireland.addClub(munster);
        ireland.addClub(leinster);

        france = new Country("France", "Fabien Galthié", 14);
        france.addClub(rouen);

        wales = new Country("Wales", "Wayne Pivac", 10);
        wales.addClub(ospreys);
        wales.addClub(dragons);

        countries.add(france);
        countries.add(ireland);
        countries.add(wales);

        ireland.addNationalPlayer(playerJonny);
        ireland.addNationalPlayer(playerGarry);
        ireland.addNationalPlayer(playerPeter);

        france.addNationalPlayer(playerLydon);

        wales.addNationalPlayer(playerAlun);
        wales.addNationalPlayer(playerLeigh);
        wales.addNationalPlayer(playerJustin);
        wales.addNationalPlayer(playerAaron);

        sixNations.setCountries(countries);
    }

    @After
    public void tearDown() throws Exception {
        munster = leinster =  rouen = ospreys = dragons =  null;
        playerJonny = playerPeter = playerLydon = playerJustin = playerAaron = playerAlun = playerCraig =
                    playerThomas = playerAndrew = playerGarry = playerLeigh = null;
        ireland = france = wales = null;
        countries = null;
    }


    @Test
    public void setCountries() {   //set is used in setUp so can directly check set here
        assertEquals(3, sixNations.getCountries().size());
    }


    @Test
    public void listCountries() {
        assertTrue(sixNations.listCountries().contains("Ireland"));
        assertTrue(sixNations.listCountries().contains("Jonny Sexton"));
        assertTrue(sixNations.listCountries().contains("Andy Farrell"));
        assertTrue(sixNations.listCountries().contains("France"));
        assertTrue(sixNations.listCountries().contains("Peter Lydon"));
        assertTrue(sixNations.listCountries().contains("Wales"));
        assertTrue(sixNations.listCountries().contains("Alun Wyn Jones"));
    }


    @Test
    public void countryWithMostPlayers() {
        assertEquals("Wales", sixNations.countryWithMostPlayers().getName());
        //add more ireland players to change ireland to be the country with the most players
        //ireland is at pos 1
        sixNations.getCountries().get(1).addNationalPlayer(playerAndrew);
        sixNations.getCountries().get(1).addNationalPlayer(playerCraig);
        sixNations.getCountries().get(1).addNationalPlayer(playerThomas);
        assertEquals("Ireland", sixNations.countryWithMostPlayers().getName());
    }

    @Test
    public void largestClub() {
        assertEquals("Rouen", sixNations.largestClub().getName());
        //change munster's number of members to be larger
        sixNations.getCountries().get(1).getClubs().get(0).setMembers(1000000);
        assertEquals("Munster", sixNations.largestClub().getName());
        //empty countries to check that this works
        sixNations.setCountries(new ArrayList<Country>());
        assertNull(sixNations.largestClub());
        sixNations.getCountries().add(new Country("Empty-no clubs", "not busy", 3));
        assertNull(sixNations.largestClub());
    }
}

