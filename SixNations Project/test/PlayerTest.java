import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerTest {

    private Player playerValid,playerInvalid1, playerInvalid2;

    @Before
    public void setUp() throws Exception {

        playerValid = new Player("012345678901234567890123456789",
                "somePlayer@gmail.com",
                "01234567");
        playerInvalid1 = new Player("",
                "somePlayer@gmailcom",
                "ABC01234567");
        playerInvalid2 = new Player("0123456789012345678901234567890",
                "somePlayergmail.com",
                "01-234567");

    }

    @After
    public void tearDown() throws Exception {
        playerValid = playerInvalid1 = playerInvalid2 =  null;
    }

    @Test
    public void validDataInConstructorAccepted() {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("012345678901234567890123456789", playerValid.getName());
        assertEquals("somePlayer@gmail.com", playerValid.getEmail());
        assertEquals("01234567", playerValid.getPhone());

        //name under boundary (29 chars), valid email, valid phone (trailing zero)
        playerValid= new Player("01234567890123456789012345678",
                "somePlayer@gmail.com",
                "12345670");
        assertEquals("01234567890123456789012345678", playerValid.getName());
        assertEquals("somePlayer@gmail.com", playerValid.getEmail());
        assertEquals("12345670", playerValid.getPhone());


    }

    @Test
    public void inValidDataInConstructorDefaultsAssigned() {
        //name (0 chars), invalid email (missing .), invalid phone (contains chars)

        assertEquals("", playerInvalid1.getName());
        assertEquals("invalid format email", playerInvalid1.getEmail().toLowerCase());
        assertEquals("unknown", playerInvalid1.getPhone().toLowerCase());

        //name over boundary 31 chars, invalid email (missing @), invalid phone (contains -)

        assertEquals("012345678901234567890123456789", playerInvalid2.getName ());
        assertEquals("invalid format email", playerInvalid2.getEmail().toLowerCase());
        assertEquals("unknown", playerInvalid2.getPhone().toLowerCase());
    }

    @Test
    public void setName() {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("012345678901234567890123456789", playerValid.getName());

        playerValid.setName("01234567890123456789012345678");   //29 chars
        assertEquals("01234567890123456789012345678", playerValid.getName());

        playerValid.setName("012345678901234567890123456789");  //30 chars
        assertEquals("012345678901234567890123456789", playerValid.getName());

        playerValid.setName("0123456789012345678901234567890"); //31 chars
        assertEquals("012345678901234567890123456789", playerValid.getName());
    }

    @Test
    public void setEmail() {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("somePlayer@gmail.com", playerValid.getEmail());

        //no @
        playerValid.setEmail("someOtherPlayergmail.com");
        assertEquals("somePlayer@gmail.com", playerValid.getEmail());

        //no .
        playerValid.setEmail("someOtherPlayer@gmailcom");
        assertEquals("somePlayer@gmail.com", playerValid.getEmail());

        //valid
        playerValid.setEmail("someOtherPlayer@gmail.com");
        assertEquals("someOtherPlayer@gmail.com", playerValid.getEmail());
    }

    @Test
    public void setPhone() {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("01234567", playerValid.getPhone());
        //contains -
        playerValid.setPhone("01-234567");
        assertEquals("01234567", playerValid.getPhone());

        //contains chars
        playerValid.setPhone("PH: 01234567");
        assertEquals("01234567", playerValid.getPhone());

        //valid
        playerValid.setPhone("019122347");
        assertEquals("019122347", playerValid.getPhone());
    }


    @Test
    public void toStringUsesAllFields() {
        assertThat(playerValid.toString().contains("012345678901234567890123456789"), is(true));
        assertThat(playerValid.toString().contains("omePlayer@gmail.com"), is(true));
        assertThat(playerValid.toString().contains("01234567"), is(true));
    }
}
