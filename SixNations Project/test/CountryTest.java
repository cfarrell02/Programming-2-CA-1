
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CountryTest {
    private Country validData1, validData2, inValidData1, inValidData2, ireland, france;
    private Player playerJonny, playerPeter, playerLydon;
    private Club munster, leinster, rouen, ulster;

    private ArrayList<Club> clubListPopulated;
    private ArrayList<Club> handyClubList;

    @Before
    public void setUp() throws Exception {

        ireland = new Country("Ireland", "Andy Farrell", 4);
        validData1 = new Country("012345678901234567890123456789", "012345678901234567890123456789", 2);    //012345678901234567890123456789-123
        validData2 = new Country("", "", 1);

        inValidData1 = new Country("012345678901234567890123456789-1234", "012345678901234567890123456789-1234", -1);
        inValidData2 = new Country("012345678901234567890123456789", "012345678901234567890123456789", 0);
        munster = new Club("Munster",
                "Johann van Graan", 70000);
        leinster = new Club("Leinster",
                "Leo Cullen", 100000);
        rouen = new Club("Rouen",
                "Richard Hill", 500000);
        ulster = new Club("Ulster", "Dan McFarland", 60000);

        //Setting up a Populated List

        clubListPopulated = new ArrayList<Club>();
        clubListPopulated.add(munster);
        clubListPopulated.add(ulster);
        clubListPopulated.add(leinster);

        playerJonny = new Player("Jonny Sexton", "jonny@irfu.com", "08710");
        playerPeter = new Player("Peter O Mahoney", "peter@irfu.com", "08706");
        playerLydon = new Player("Peter Lydon", "peter@rouen.com", "08710");

        munster.addPlayer(playerPeter);
        leinster.addPlayer(playerJonny);
        rouen.addPlayer(playerLydon);

        ireland.addClub(munster);
        ireland.addClub(leinster);

        france = new Country("France", "Fabien Galthié", 14);
        france.addClub(rouen);

        handyClubList = new ArrayList<Club>();
        handyClubList.add(munster);
        handyClubList.add(leinster);
        handyClubList.add(rouen);
        //Setting up a Populated List
        clubListPopulated = new ArrayList<>();
        clubListPopulated.addAll(handyClubList);
    }

    @After
    public void tearDown() throws Exception {
        clubListPopulated = null;
        handyClubList = null;
        munster = leinster = rouen = ulster = null;
        playerJonny = playerPeter = playerLydon =  null;
        ireland = france = null;
    }

    @Test
    public void validDataInConstructorAccepted() {
        assertEquals("012345678901234567890123456789", validData1.getName());
        assertEquals("012345678901234567890123456789", validData1.getManager());
        assertEquals(2, validData1.getMaxNumOfClubs());
        assertNotNull(validData1.getNationalPlayers());
        assertNotNull(validData1.getClubs());

        // at lower boundary
        assertEquals("", validData2.getName());
        assertEquals("", validData2.getManager());
        assertEquals(1, validData2.getMaxNumOfClubs());
        assertNotNull(validData1.getNationalPlayers());
        assertNotNull(validData1.getClubs());
    }

    @Test
    public void inValidDataInConstructorDefaultsAssigned() {
        assertEquals("012345678901234567890123456789", inValidData1.getName());
        assertEquals("012345678901234567890123456789", inValidData1.getManager());
        assertEquals(10, inValidData1.getMaxNumOfClubs());
        assertNotNull(inValidData1.getNationalPlayers());
        assertNotNull(inValidData1.getClubs());

        assertEquals("012345678901234567890123456789", inValidData2.getName());
        assertEquals("012345678901234567890123456789", inValidData2.getManager());
        assertEquals(10, inValidData2.getMaxNumOfClubs());   // ensuring positive rather than non-negative
        assertNotNull(inValidData2.getNationalPlayers());
        assertNotNull(inValidData2.getClubs());

    }

    @Test
    public void setName() {
        assertEquals("012345678901234567890123456789", validData1.getName());

        validData1.setName("new name");
        assertEquals("new name", validData1.getName());

        validData1.setName("012345678901234567890123456789-1234");
        assertEquals("new name", validData1.getName());
    }

    @Test
    public void setManager() {
        assertEquals("012345678901234567890123456789", validData1.getManager());

        validData1.setManager("new name");
        assertEquals("new name", validData1.getManager());

        validData1.setName("012345678901234567890123456789-1234");
        assertEquals("new name", validData1.getManager());
    }

    @Test
    public void setNoClubs() {
        assertEquals(2, validData1.getMaxNumOfClubs());

        validData1.setMaxNumOfClubs(1);
        assertEquals(1, validData1.getMaxNumOfClubs());

        validData1.setMaxNumOfClubs(0);
        assertEquals(1, validData1.getMaxNumOfClubs());

        validData1.setMaxNumOfClubs(-1);
        assertEquals(1, validData1.getMaxNumOfClubs());

    }

    @Test
    public void setPlayers() {   // Players list can only be set if all are eligible
        ArrayList<Player> plFromIrishCLubs, plOneFromFrance;
        plOneFromFrance = new ArrayList<Player>();
        plFromIrishCLubs = new ArrayList<Player>();
        plFromIrishCLubs.add(playerJonny);
        plFromIrishCLubs.add(playerPeter);
        plOneFromFrance.add(playerJonny);
        plOneFromFrance.add(playerLydon);  //in rouen (France)
        plOneFromFrance.add(playerPeter);
        assertEquals(2, plFromIrishCLubs.size());
        assertTrue(ireland.setNationalPlayers(plFromIrishCLubs));
        assertFalse(ireland.setNationalPlayers(plOneFromFrance));
    }

    @Test
    public void setCLubs() {
        ireland.setClubs(clubListPopulated);
        ArrayList<Club> testclubList = new ArrayList<>();
        testclubList.add(rouen);
        testclubList.add(ulster);

        ireland.setClubs(testclubList);
        assertEquals(rouen, ireland.getClubs().get(0));
        assertEquals(ulster, ireland.getClubs().get(1));
        assertEquals(2, ireland.getClubs().size());
    }

    @Test
    public void addPlayer() {
        ireland.addNationalPlayer(playerJonny);   // eligible so should be added
        ireland.addNationalPlayer(playerPeter); // eligible so should be added
        assertTrue(playerJonny.equals(ireland.getNationalPlayers().get(0)));
        assertTrue(playerPeter.equals(ireland.getNationalPlayers().get(1)));
        assertEquals(2, ireland.getNationalPlayers().size());

        ireland.addNationalPlayer(playerLydon);    // not in an Irish club so should not be added
        assertTrue(playerJonny.equals(ireland.getNationalPlayers().get(0)));
        assertTrue(playerPeter.equals(ireland.getNationalPlayers().get(1)));
        assertEquals(2, ireland.getNationalPlayers().size());

        Player newBlood = new Player("Thomas Aherne", "thom@munster.com", "08705");
        //not in any club so should not be added
        ireland.addNationalPlayer(newBlood);
        assertTrue(playerJonny.equals(ireland.getNationalPlayers().get(0)));
        assertTrue(playerPeter.equals(ireland.getNationalPlayers().get(1)));
        assertEquals(2, ireland.getNationalPlayers().size());

    }

    @Test
    public void deletePlayer() {
        ireland.addNationalPlayer((playerJonny));
        ireland.addNationalPlayer((playerPeter));

        assertEquals(2, ireland.getNationalPlayers().size());
        assertFalse(ireland.deleteNationalPlayer(-1));   //invalid index
        assertEquals(playerJonny, ireland.getNationalPlayer(0));
        ireland.deleteNationalPlayer(0);
        assertEquals(1, ireland.getNationalPlayers().size());
        assertEquals(playerPeter, ireland.getNationalPlayer(0));

        ireland.deleteNationalPlayer(0);
        assertEquals(0, ireland.getNationalPlayers().size());
        assertFalse(ireland.deleteNationalPlayer(0));  // invalid index

    }

    @Test
    public void addClub() {
        assertEquals(2, ireland.numberOfClubs());
        ireland.addClub(ulster);
        assertEquals(3, ireland.numberOfClubs());
        assertEquals(ulster, ireland.getClubs().get(2));
        Club connacht = new Club("Connacht", "Andy Friend", 30000);
        ireland.addClub(connacht);
        assertEquals(4, ireland.numberOfClubs());
        assertEquals(connacht, ireland.getClubs().get(3));
        //now at 4 teams - try to add another
        ireland.addClub(rouen);
        assertEquals(4, ireland.numberOfClubs());
        //add to an empty country
        assertEquals(1, france.numberOfClubs());
        assertEquals(rouen, france.getClubs().get(0));
    }


    @Test
    public void deleteClub() {
        assertEquals(2, ireland.numberOfClubs());
        ireland.deleteClub(1);
        assertEquals(1, ireland.numberOfClubs());
        assertEquals(munster, ireland.getClubs().get(0));
        ireland.deleteClub(0);
        assertEquals(0, ireland.numberOfClubs());
        assertNull(ireland.deleteClub(2));
        assertNull(france.deleteClub(2));
    }

    @Test
    public void numberOfClubs() {
        assertEquals(2, ireland.numberOfClubs());
        assertEquals(1, france.numberOfClubs());
    }

    @Test
    public void largestClubInCountry() {
        assertEquals(leinster, ireland.largestClubInCountry());
        Club connacht = new Club("Connacht", "Andy Friend", 30000);
        ireland.addClub(connacht);
        assertEquals(leinster, ireland.largestClubInCountry());
        ireland.addClub(rouen);
        assertEquals(rouen, ireland.largestClubInCountry());
    }

    @Test
    public void listOfPlayers() {
        ireland.addNationalPlayer(playerJonny);
        ireland.addNationalPlayer(playerPeter);
        assertEquals(2, ireland.getNationalPlayers().size());
        assertTrue(ireland.listOfNationalPlayers().contains("Jonny Sexton"));
        assertTrue(ireland.listOfNationalPlayers().contains("Peter O Mahoney"));
        ireland.deleteNationalPlayer(0);
        assertFalse(ireland.listOfNationalPlayers().contains("Jonny Sexton"));
    }

    @Test
    public void listOfPlayersByClub() {
        ireland.addNationalPlayer(playerJonny);
        ireland.addNationalPlayer(playerPeter);
        france.addNationalPlayer(playerLydon);
        assertEquals(2, ireland.getNationalPlayers().size());
        assertEquals(1, france.getNationalPlayers().size());
        assertTrue(ireland.listOfNationalPlayersByClub().contains("Jonny Sexton"));
        assertTrue(ireland.listOfNationalPlayersByClub().contains("Leinster"));
        assertTrue(ireland.listOfNationalPlayersByClub().contains("Peter O Mahoney"));
        assertTrue(ireland.listOfNationalPlayersByClub().contains("Munster"));
        assertTrue(france.listOfNationalPlayersByClub().contains("Peter Lydon"));
        assertTrue(france.listOfNationalPlayersByClub().contains("Rouen"));

        ireland.deleteNationalPlayer(0);
        assertFalse(ireland.listOfNationalPlayersByClub().contains("Jonny Sexton"));
        assertFalse(ireland.listOfNationalPlayersByClub().contains("Leinster"));

        france.deleteNationalPlayer(0);
        assertTrue(france.listOfNationalPlayersByClub().contains("There are no players registered for the national team"));

    }

    @Test
    public void listOfClubs() {
        assertTrue(france.listOfClubs().contains("Rouen"));
        assertTrue(ireland.listOfClubs().contains("Munster"));
        assertTrue(ireland.listOfClubs().contains("Leinster"));

        france.deleteClub(0);
        assertTrue(france.listOfClubs().contains("There are no clubs registered for the country"));

    }

    @Test
    public void noOfPlayersByClub() {
        ireland.addNationalPlayer(playerJonny);
        ireland.addNationalPlayer(playerPeter);
        france.addNationalPlayer(playerLydon);
        assertEquals(2, ireland.getNationalPlayers().size());
        assertEquals(1, ireland.noOfNationalPlayersByClub(leinster));
        assertEquals(-1, ireland.noOfNationalPlayersByClub(rouen));
        assertEquals(1, france.noOfNationalPlayersByClub(rouen));
        france.deleteClub(0);
        assertEquals(-1, france.noOfNationalPlayersByClub(rouen));
    }

    @Test
    public void isEligible() {
        assertTrue(france.isEligible(playerLydon));
        assertFalse(ireland.isEligible(playerLydon));

    }
}


