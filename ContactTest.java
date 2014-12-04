import org.junit.*;
import static org.junit.Assert.*;


public class ContactTest {

    Contact contact1;
    Contact contact2;
    Contact contact3;
    
    /* @Before
    public void buildContact() {
        contact1 = new ContactImpl("Sam Smith");
        contact2 = new ContactImpl("Craig Craigson");
        contact3 = new ContactImpl("James Johnson");
    }
    
    @Test
    public void testConstructor() {
        assertEquals(contact1.getName(), "Sam Smith");
        assertEquals(contact1.getId(), 6);
        assertNotNull(contact1);
        System.out.println(contact1);
        System.out.println(contact2);
        System.out.println(contact3);
    }
    
    @Test
    public void testMultipleContacts() {
        assertEquals(contact2.getName(), "Craig Craigson");
        assertEquals(contact2.getId(), 1);
        assertNotNull(contact2);
        
        assertEquals(contact3.getName(), "James Johnson");
        assertEquals(contact3.getId(), 2);
        assertNotNull(contact3);
        System.out.println(contact1);
        System.out.println(contact2);
        System.out.println(contact3);
    }
    
    @Test
    public void testMoreContacts() {
        assertEquals(contact2.getName(), "Craig Craigson");
        assertEquals(contact2.getId(), 4);
        assertNotNull(contact2);
        
        assertEquals(contact3.getName(), "James Johnson");
        assertEquals(contact3.getId(), 5);
        assertNotNull(contact3);
        System.out.println(contact1);
        System.out.println(contact2);
        System.out.println(contact3);
    }
    
    @After
    public void cleanContact() {
        contact1 = null;
        contact2 = null;
        contact3 = null;
        System.out.println(contact1);
        System.out.println(contact2);
        System.out.println(contact3);
    } */
    
    /* @Before 
    public void buildContact() {
        contact1 = new ContactImpl("Sam Smith");
    }
    
    @Test
    public void testAddNotes() {
        contact1.addNotes("These are some notes");
    }
    
    @Test
    public void testGetNotes() {
        contact1.addNotes("These are some notes");
        assertEquals(contact1.getNotes(), "These are some notes"); 
        System.out.println(contact1.getNotes());
    }
    
    @After
    public void cleanContact() {
        contact1 = null;
    } */
    
    /* @Test
    public void testReConstructor() {
        contact1 = new ContactImpl("Jim Smith", 45, "This is Jim Smith's Contact Info");
        assertNotNull(contact1);
        assertEquals(contact1.getName(), "Jim Smith");
        assertEquals(contact1.getId(), 45);
        assertEquals(contact1.getNotes(), "This is Jim Smith's Contact Info");
        System.out.println(contact1);
    } */
    
    /* @Test
    public void testResumeIDCounter() {
        ContactImpl.resumeIDCounter(5);
        System.out.println(ContactImpl.IDCounter);
    } */
    @Test
    public void testReEnterContacts() {
        ContactImpl.resumeIDCounter(5);
        System.out.println(ContactImpl.IDCounter);
        
        contact1 = new ContactImpl("Jim Smith");
        System.out.println(contact1);
        System.out.println(ContactImpl.IDCounter);
        contact2 = new ContactImpl("Pete Smithe");
        System.out.println(contact2);
        System.out.println(ContactImpl.IDCounter);
        
    }
}