import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.atomic.*;
import java.util.HashSet;
import java.util.GregorianCalendar;
import java.io.Serializable;
import java.util.Comparator;

/**
*
*   A class implementing the Meeting Interface
*
*   MeetingImpl holds information about the ID number of a Meeting
*   the date of the Meeting and the Set of Contacts attending the Meeting
*
*/
public class MeetingImpl implements Meeting,
                                    Serializable,
                                    Comparator<Meeting> {
    
    private static AtomicInteger meetingIDCounter = new AtomicInteger(0);
    
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
        this.meetingDate = (GregorianCalendar)meetingDate.clone();
        this.attendees = new HashSet<Contact>(attendees);
        this.meetingID = getAtomicMeetingID();
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
    @Override
    public int getId() {
        return new Integer(meetingID);
    }
    
    /**
    * Return the date of the meeting.
    *
    * @return a clone of the meetingDate;
    */
    @Override
    public Calendar getDate() {
        return (GregorianCalendar)meetingDate.clone();
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
    @Override
    public Set<Contact> getContacts() {
        return new HashSet<Contact>(attendees);
    }
    
    /**
    *
    *   Static resumeMeetingIDCounter() method accepts an int parameter 
    *   re-initialises the meetingIDCounter field with the next highest unique IDNumber
    *
    *   @param highest integer value previously stored in the Contacts.txt file.
    *
    */
    public static void resumeMeetingIDCounter(int highestNumber) {
        AtomicInteger newCounter = new AtomicInteger(highestNumber);
        meetingIDCounter = newCounter;
    }
    
     /**
    *
    *   method for accessing the current value of the IDCounter
    *
    *   @return the current value of the IDCounter
    *
    */
    public static int getMeetingIDCounter() {
        return (Integer) meetingIDCounter.get();
    }
    
    /**
    *
    *   compare method from the Comparator Interface
    *
    *   used by the ContactManagerImpl methods for return an ordered list
    *
    *   @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
    *
    */
    @Override
    public int compare(Meeting meeting1, Meeting meeting2) {
        int result = 0;
        
        if (meeting1.getDate().before(meeting2.getDate())) {
            result = -1;
        }
        else if (meeting1.getDate().after(meeting2.getDate())) {
            result = 1;
        }
        
        return result;
    }
}