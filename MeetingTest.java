import org.junit.*;
import static org.junit.Assert.*;

public class MeetingTest {

    Meeting meeting;
    
    @Before
    public void buildMeeting() {
        meeting = new MeetingImpl();
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