
  
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class StartUtilitiesTest {
    
    Player playerJonny, playerPeter, playerLydon, playernewbie;


    @Before
    public void setUp() throws Exception {
      
        playerJonny = new Player("Jonny Sexton", "jonny@irfu.com", "08710");
        playerPeter = new Player("Peter O Mahoney", "peter@irfu.com", "08706");
        playerLydon = new Player("Peter Lydon", "peter@rouen.com", "08710");
        playernewbie = new Player("I'm just in", "newbie@excited.com", "08723");
        
     
    }

    @After
    public void teardown() {

        playerJonny = playerPeter = playerLydon = playernewbie = null;

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

   
}

