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
    
    @Test
    public void testConstructor() {
        assertNotNull(cm);
    }
    
    @Test
    public void testFlush() {
        cm.flush();
    }
    
    @After
    public void cleanContactManager() {
        cm = null;
    }
}