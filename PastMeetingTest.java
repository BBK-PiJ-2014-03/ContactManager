import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Calendar;

public class PastMeetingTest {
    
    MeetingImpl meeting;
    PastMeetingImpl pastMeeting;
    
    @Before
    public void buildPastMeeting() {
        meeting = new MeetingImpl(Calendar.getInstance(), new HashSet<Contact>(10));
        pastMeeting = new PastMeetingImpl("These are notes about a previous meeting", meeting);
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(pastMeeting);
    }
    
    @After
    public void cleanPastMeeting() {
        meeting = null;
        pastMeeting = null;
    }
    
}