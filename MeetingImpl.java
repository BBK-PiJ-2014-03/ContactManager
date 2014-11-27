import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.atomic.*;

public class MeetingImpl implements Meeting {
    
    public static AtomicInteger meetingIDCounter = new AtomicInteger(0);
    
    private int meetingID;
    private Calendar meetindDate;
    private Set<Contact> attendees;
    
    public MettingImpl() {
        this.meetingID = getAtomicMeetingID();
    }
    
    public int getAtomicMeetingID() {
        return meetingIDCounter.getAndIncrement();
    }
    
    /**
    * Returns the id of the meeting.
    *
    * @return the id of the meeting.
    */
    public int getId() {
        return new Integer(meetingID);
    }
    
    /**
    * Return the date of the meeting.
    *
    * @return the date of the meeting.
    */
    public Calendar getDate() {
        
    }
    
    /**
    * Return the details of people that attended the meeting.
    *
    * The list contains a minimum of one contact (if there were
    * just two people: the user and the contact) and may contain an
    * arbitraty number of them.
    *
    * @return the details of people that attended the meeting.
    */
    public Set<Contact> getContacts() {
        
    }
}