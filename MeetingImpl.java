import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.atomic.*;
import java.util.HashSet;

public class MeetingImpl implements Meeting {
    
    public static AtomicInteger meetingIDCounter = new AtomicInteger(0);
    
    private int meetingID;
    private Calendar meetingDate;
    private Set<Contact> attendees;
    
    /**
    *
    *   Initial constructor
    *   accepts a Calendar meetingDate which is the date of the meeting,
    *   accepts a Set<Contact> of the attendees of the meeting
    *   generates a Unique ID number using the getAtomicMeetingID() method
    *
    *   @param meetingDate the date of the meeting
    *   @param attendees the Contacts attending the meeting
    *
    */
    public MeetingImpl(Calendar meetingDate, Set<Contact> attendees) {
        this.meetingDate = meetingDate;
        this.attendees = attendees;
        this.meetingID = getAtomicMeetingID();
    }
    
    /**
    *
    *   I/O Constructor
    *   used to re=create MeetingImpl Objects after retrieving data from the I/O file.
    *
    *   @param meetingID the ID Number for the meeting
    *   @param meetingDate the date of the meeting
    *   @param attendees the Contacts attending the meeting
    *
    */
    public MeetingImpl(int meetingID, Calendar meetingDate, Set<Contact> attendees) {
        this.meetingID = meetingID;
        this.meetingDate = meetingDate;
        this.attendees = attendees;
    }
    
    /**
    *
    *   getAtomicMeetingID() returns the int result of calling getAndIncrement()
    *   on the static meetingIDCounter field.
    *
    */
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
    * @return a clone of the meetingDate;
    */
    public Calendar getDate() {
        return (Calendar)meetingDate.clone();
    }
    
    /**
    * Return the details of people that attended the meeting.
    *
    * The list contains a minimum of one contact (if there were
    * just two people: the user and the contact) and may contain an
    * arbitrary number of them.
    *
    * @return the details of people that attended the meeting.
    */
    public Set<Contact> getContacts() {
        return new HashSet<Contact>(attendees);
    }
}