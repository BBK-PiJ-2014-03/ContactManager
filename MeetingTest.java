import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Calendar;

public class MeetingTest {

    Meeting meeting;
    
    @Before
    public void buildMeeting() {
        meeting = new MeetingImpl(Calendar.getInstance(), new HashSet<Contact>(10));
    }
    
    @Test
    public void testMeetingConstructor() {
        assertEquals(meeting.getId(), 0);
    }
    
    @After
    public void cleanMeeting() {
        meeting = null;
    }
}