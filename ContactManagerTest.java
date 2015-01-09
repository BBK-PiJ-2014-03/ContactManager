import java.util.Calendar;
import java.util.List;
import java.util.Set;
import org.junit.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;


public class ContactManagerTest {
    
    ContactManager cm;

    @Before
    public void buildContactManager() {
        cm = new ContactManagerImpl();
    }
    
    @After
    public void cleanContactManager() {
        cm = null;
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(cm);
        System.out.println(GregorianCalendar.getInstance());
    }
    
    @Test
    public void testAddNewContact() {
        String name1 = "Bertram Wooster";
        String notes1 = "Gentleman";
        cm.addNewContact(name1, notes1);
    }
    
    @Test
    public void testAddAnotherContact() {
        String name2 = "Reginald Jeeves";
        String notes2 = "Gentleman's Gentleman";
        cm.addNewContact(name2, notes2);
    }
    
    @Test
    public void testCalendar() {
        Calendar futureDate = new GregorianCalendar(2015, 03, 20);
        Calendar pastDate = new GregorianCalendar(2014, 01, 4);
        
        assertTrue(futureDate.after(Calendar.getInstance()));
        assertTrue(pastDate.before(GregorianCalendar.getInstance()));
    }
    
    @Test
    public void testFlush() {
        cm.flush();
    }
}