
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ClubTest {

    private Club clubOne, clubTwo, clubThree;
    private ArrayList<Player> players;
    Player playerJonny, playerPeter, playerFinn;



    @Before
    public void setUp() throws Exception {

        //name, headCoach  on boundary 30 chars, members valid

        clubOne = new Club("012345678901234567890123456789",
                "012345678901234567890123456789", 0);
        //name just below boundary - 29 chars, members positive
        clubTwo = new Club("01234567890123456789012345678",
                "01234567890123456789012345678",  10);
        // (invalid values) name, headCoach longer than allowed, members negative
        clubThree = new Club("012345678901234567890123456789XXXX",
                "012345678901234567890123456789XXX",  -10);
        players = new ArrayList<Player>();
        playerJonny = new Player("Jonny Sexton", "jonny@irfu.com", "08710");
        playerPeter = new Player("Peter O Mahoney", "peter@irfu.com", "08706") ;
        playerFinn = new Player("Finn Russell", "finn@sru.com", "08710")  ;
        players.add(playerJonny);
        players.add(playerPeter);
        players.add(playerFinn);
        clubOne.setPlayers(players);

    }

    @After
    public void tearDown() throws Exception {
        clubOne = clubTwo = clubThree = null;
        players = null;
        playerJonny = playerFinn = playerPeter = null;
    }

    @Test
    public void validDataInConstructorAccepted() {

        //name on boundary 30 chars, valid  number of members
        assertEquals("012345678901234567890123456789", clubOne.getName());
        assertEquals("012345678901234567890123456789", clubOne.getHeadCoach());
        assertEquals(0, clubOne.getMembers());
        assertNotNull(clubOne.getPlayers());


        //name just below boundary - 29 chars, members positive
        assertEquals("01234567890123456789012345678", clubTwo.getName());
        assertEquals("01234567890123456789012345678", clubTwo.getHeadCoach());
        assertEquals(10, clubTwo.getMembers());
        assertNotNull(clubTwo.getPlayers());
    }
    @Test
    public void invalidDataInConstructorDefaultsAssigned() {
        // neg no of members

        assertEquals("012345678901234567890123456789", clubThree.getName());
        assertEquals("012345678901234567890123456789", clubThree.getHeadCoach());
        assertEquals(0, clubThree.getMembers());
        assertNotNull(clubThree.getPlayers());

    }


    @Test
    public void setClubName() {

        //name on boundary 30 chars
        assertEquals("012345678901234567890123456789", clubOne.getName());

        clubOne.setName("01234567890123456789012345678");   //29 chars
        assertEquals("01234567890123456789012345678", clubOne.getName());

        clubOne.setName("012345678901234567890123456789");  //30 chars
        assertEquals("012345678901234567890123456789", clubOne.getName());

        clubOne.setName("012345678901234567890123456789X"); //31 chars
        assertEquals("012345678901234567890123456789", clubOne.getName());
    }
    @Test
    public void setHeadCoach() {

        //name on boundary 30 chars
        assertEquals("012345678901234567890123456789", clubOne.getHeadCoach());

        clubOne.setHeadCoach("01234567890123456789012345678");   //29 chars
        assertEquals("01234567890123456789012345678", clubOne.getHeadCoach());

        clubOne.setHeadCoach("012345678901234567890123456789");  //30 chars
        assertEquals("012345678901234567890123456789", clubOne.getHeadCoach());

        clubOne.setHeadCoach("012345678901234567890123456789X"); //31 chars
        assertEquals("012345678901234567890123456789", clubOne.getHeadCoach());
    }
    @Test
    public void setMembers() {
        //valid song length set
        assertEquals(0, clubOne.getMembers());

        clubOne.setMembers(-1);     //-1 (lower boundary)
        assertEquals(0, clubOne.getMembers());
        clubOne.setMembers(50);     //0 (lower boundary + 1)
        assertEquals(50, clubOne.getMembers());


        clubOne.setMembers(1200);     //1200 (valid data)
        assertEquals(1200, clubOne.getMembers());
    }


    @Test
    public void addPLayer() {
        assertEquals(clubOne.getPlayers().size(), 3);
        Player playerLydon = new Player("Peter Lydon", "peter@rouen.com", "08710")  ;
        clubOne.addPlayer(playerLydon);
        assertTrue(clubOne.isPlayerInClub(playerLydon));
        assertEquals(clubOne.getPlayers().size(), 4);
        assertEquals(clubOne.getPlayers().get(3), playerLydon);
    }
    @Test
    public void deletePLayer() {
        assertEquals(clubOne.getPlayers().size(), 3);
        assertTrue(clubOne.isPlayerInClub(playerJonny));
        clubOne.deletePlayer(0);
        assertFalse(clubOne.isPlayerInClub(playerJonny));
        assertEquals(clubOne.getPlayers().size(), 2);
        assertEquals(clubOne.getPlayers().get(0), playerPeter);
    }

    @Test
    public void toStringUsesAllFields() {
        assertThat(clubOne.toString().contains("012345678901234567890123456789"), is(true));
        assertThat(clubOne.toString().contains("012345678901234567890123456789"), is(true));
        assertThat(clubOne.toString().contains("0"), is(true));

    }


    @Test
    public void listOfPlayers() {
        assertThat(clubOne.listOfPlayers().contains("Jonny Sexton"), is(true));
        assertThat(clubOne.listOfPlayers().contains("jonny@irfu.com"), is(true));
        assertThat(clubOne.listOfPlayers().contains("08710"), is(true));

        assertThat(clubOne.listOfPlayers().contains("Peter O Mahoney"), is(true));
        assertThat(clubOne.listOfPlayers().contains("peter@irfu.com"), is(true));
        assertThat(clubOne.listOfPlayers().contains("08706"), is(true));

        assertThat(clubOne.listOfPlayers().contains("Finn Russell"), is(true));
        assertThat(clubOne.listOfPlayers().contains("finn@sru.com"), is(true));
        assertThat(clubOne.listOfPlayers().contains("08710"), is(true));
        assertThat(clubThree.listOfPlayers().contains("There are no players registered in this club"),is(true));
    }
    @Test
    public void isPlayerInClub() {

        Player newPlayer = new Player("Newbie Rugby", "newbie@munster.com", "08703");
        assertTrue(clubOne.isPlayerInClub(playerJonny));
        assertFalse(clubOne.isPlayerInClub(newPlayer));
        assertFalse(clubTwo.isPlayerInClub(null));
        assertFalse(clubTwo.isPlayerInClub(playerPeter));
    }


}
