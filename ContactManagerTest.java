import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import org.junit.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;


public class ContactManagerTest {
    
    ContactManagerImpl cm;
    Contact c1;
    Contact c2;
    Contact c3;
    Set<Contact> contactSet;
    Calendar pastDate;
    Calendar futureDate;
    

    @Before
    public void buildContactManager() {
        cm = new ContactManagerImpl();
        
        pastDate = new GregorianCalendar(2014, 3, 4);
        futureDate = new GregorianCalendar(2016, 2, 6);
        
        cm.addNewContact("Bertram Wooster", "Gentleman");
        cm.addNewContact("Reginald Jeeves", "Gentleman's Gentleman");
        
    }
     
    @Test
    public void testContactList() {
        List<Contact> newList = (ArrayList<Contact>)cm.contactList;
        for (Contact c : newList) {
            System.out.println(c);
        }
    }   
    
    
    @After
    public void cleanContactManager() {
        cm.flush();
        cm = null;
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(cm);
    }
    
    /* @Test
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
    } */
    
    @Test
    public void testCalendar() {
        assertTrue(futureDate.after(Calendar.getInstance()));
        assertTrue(pastDate.before(GregorianCalendar.getInstance()));
    }
    
   /*  @Test
    public void testAddNewPastMeeting() {
        cm.addNewPastMeeting(contactSet, pastDate, "This is a past meeting");
    } */
    
    /* @Test
    public void testAddMeetingNotes() {
        cm.addMeetingNotes(0, "Meeting Notes");
        assertEquals(cm.getPastMeeting(0).getNotes(), "MeetingNotes");
    } */
    
    /* @Test
    public void testAddFutureMeeting() {
        assertEquals(cm.addFutureMeeting(contactSet, futureDate), cm.getFutureMeeting(0).getId());
    } */
        
    /* @Test
    public void testGetPastMeeting() {
        assertEquals(cm.getPastMeeting(0).getId(), 0);
        assertEquals(cm.getPastMeeting(1).getId(), 1);
    } */
    
    /* @Test
    public void testGetFutureMeeting() {
        assertEquals(cm.getFutureMeeting(3).getId(), 3);
    } */
    
    /* @Test
    public void testGetMeeting() {
        assertEquals(cm.getMeeting(0).getId(), 0);
        assertEquals(cm.getMeeting(1).getId(), 1);
        assertEquals(cm.getMeeting(3).getId(), 3);
    }
    
    @Test
    public void testGetFutureMeetingListContact() {
        cm.getFutureMeetingList(c1);
    }
    
    @Test
    public void testGetFutureMeetingListCalendar() {
        cm.getFutureMeetingList(pastDate);
    }
    
    @Test
    public void testGetPastMeetingListContact() {
        cm.getPastMeetingList(c1);
    } */
    
    /* @Test
    public void testGetContactsInt() {
        // System.out.println(cm.getContacts(0));
        cm.addNewContact("Bertram Wooster", "Gentleman");
        List<Contact> newList = (ArrayList<Contact>)cm.contactList;
        for (Contact c : newList) {
            System.out.println(c);
        }
    } */
    
   /*  @Test
    public void testGetContactsString() {
        cm.addNewContact("Bertram Wooster", "Gentleman");
        
        Set<Contact> newSet = cm.getContacts("Bertram Wooster");
        
        List<Contact> newList = Collections.list(Collections.enumeration(newSet));
        
        for (Contact c : newList) {
            System.out.println(c);
        }
    } */
    
    @Test
    public void testFlush() {
        cm.flush();
    }
}