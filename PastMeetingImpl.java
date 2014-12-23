import java.util.Set;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
*
*   A class implementing the PastMeeting Interface
*
*   Contains the MeetingImpl object of a meeting held in the past
*   also contains a String of notes about the Meeting
*
*/
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
    
    private String notes;
    private Meeting meeting;
    
    public PastMeetingImpl(Calendar calendar, Set<Contact> contacts, String notes) {
        super((GregorianCalendar)calendar, contacts);
        this.notes = notes;
    }
    
    /**
    *
    *   getNotes() method for retrieving the notes about the Meeting
    *
    *   @return String of information about occurrences during the Meeting
    *
    */
    @Override
    public String getNotes() {
        return new String(notes);
    }
}