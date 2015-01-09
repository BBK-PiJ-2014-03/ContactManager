import java.util.Calendar;
import java.util.List;
import java.util.Set;
import org.junit.*;
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
    public void testFlush() {
        cm.flush();
    }
}