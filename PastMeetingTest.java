import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Calendar;

public class PastMeetingTest {
    
    Meeting meeting;
    PastMeeting pastMeeting;
    
    @Before
    public void buildPastMeeting() {
        meeting = new MeetingImpl(Calendar.getInstance(), new HashSet<Contact>(10));
        pastMeeting = new PastMeetingImpl("These are notes about a previous meeting", meeting);
    }
    
   /*  @Test
    public void testConstructor() {
        assertNotNull(pastMeeting);
    } */
    
    /* @Test
    public void testGetNotes() {
        assertNotNull(pastMeeting.getNotes());
        System.out.println(pastMeeting.getNotes());
    } */
    
    @Test
    public void testGetContacts() {
        assertNotNull(pastMeeting.getContacts());
        System.out.println(pastMeeting.getContacts());
    }
    
    /* @Test
    public void testGetDate() {
        assertNotNull(pastMeeting.getDate());
        System.out.println(pastMeeting.getDate().getTime());
    } */
    
   /*  @Test
    public void testGetId() {
        assertNotNull(pastMeeting.getId());
        System.out.println(pastMeeting.getId());
    } */
    
    @After
    public void cleanPastMeeting() {
        meeting = null;
        pastMeeting = null;
    }
    
}