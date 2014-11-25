import org.junit.*;
import static org.junit.Assert.*;


public class ContactTest {

    Contact contact;
    
    /* @Before
    public void buildContact() {
        
    } */
    
    @Test
    public void testConstructor() {
        contact = new ContactImpl("Sam Smith");
        assertEquals(contact.getName(), "Sam Smith");
        assertEquals(contact.getId(), 0);
        System.out.println(contact.getName());
        System.out.println(contact.getId());
    }
    
    /* @After
    public void cleanContact() {
        contact = null;
    } */
}