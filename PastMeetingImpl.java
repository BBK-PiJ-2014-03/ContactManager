import java.util.Set;
import java.util.Calendar;

/**
*
*   A class implementing the PastMeeting Interface
*
*   Contains the MeetingImpl object of a meeting held in the past
*   also contains a String of notes about the Meeting
*
*/
public class PastMeetingImpl implements PastMeeting {
    
    private String notes;
    private MeetingImpl meeting;
    
    public PastMeetingImpl(String notes, MeetingImpl meeting) {
        this.notes = notes;
        this.meeting = meeting;
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
    
    /**
    *
    *   Overridden getContacts() method
    *
    *   @return the return value from calling the meeting field's getContacts() method
    *
    */
    @Override
    public Set<Contact> getContacts() {
        return meeting.getContacts();
    }
    
    /**
    *
    *   Overridden getDate() method
    *
    *   @return the return value from calling the meeting field's getDate() method
    *
    */
    @Override
    public Calendar getDate() {
        return meeting.getDate();
    }
    
    /**
    *
    *   Overridden getId() method
    *
    *   @return the return value from calling the meeting field's getId() method
    *
    */
    @Override
    public int getId() {
        return meeting.getId();
    }
    
}