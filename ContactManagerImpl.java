import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
*
*   A Class to allow a start up company to manage their busy schedule of meetings
*   and their burgeoning list of contacts
*
*/
public class ContactManagerImpl implements ContactManager {

    private List<Contact> contactList;
    private List<Meeting> meetingList;
    private List<PastMeeting> pastMeetingList;

    /**
    *
    *   Constructor initialises the three List structures which contain the Contacts,
    *   Meetings and PastMeetings.
    *
    *   creates a new link to the "Contacts.txt" File
    *   If the File already exists a FileInputStream/ObjectInputStream combo is used to
    *   retrieve the data stored as Objects from the File.
    *
    *   Objects are read in order
    *   First an object representing the counter for the ContactImpl class, cast as an Integer
    *   Second an object representing the counter for the MeetingImpl class, cast as an Integer
    *   Thirdly an object for the List of Contacts, cast as an ArrayList<Contact>
    *   Fourthly an object for the List of Meetings, cast as an ArrayList<Meeting>
    *   Fifthly an object for the list of PastMeetings, cast as an ArrayList<PastMeeting>
    *
    *   @SuppressWarnings required as there is an unchecked cast of the objects read from to objectStream
    *   to the ArrayList structures storing the Contact and Meeting Objects.
    *
    */
    @SuppressWarnings("unchecked")
    public ContactManagerImpl() {
    
        contactList = new ArrayList<Contact>();
        meetingList = new ArrayList<Meeting>();
        pastMeetingList = new ArrayList<PastMeeting>();
        
        File file = new File("Contacts.txt");
        if (file.exists() && file.isFile()) {
            try {
                FileInputStream fileStream = new FileInputStream(file);
                ObjectInputStream objectStream = new ObjectInputStream(fileStream);
                Integer contactCounter = (Integer)objectStream.readObject();
                ContactImpl.resumeIDCounter(contactCounter);
                Integer meetingCounter = (Integer)objectStream.readObject();
                MeetingImpl.resumeMeetingIDCounter(meetingCounter);
                this.contactList = (ArrayList<Contact>) objectStream.readObject();
                this.meetingList = (ArrayList<Meeting>) objectStream.readObject();
                this.pastMeetingList = (ArrayList<PastMeeting>) objectStream.readObject();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
            
    }
    
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        return 0;
    }

    public PastMeeting getPastMeeting(int id) {
        return null;
    }
    
    public FutureMeeting getFutureMeeting(int id) {
        return null;
    }
    
    public Meeting getMeeting(int id) {
        return null;
    }
    
    public List<Meeting> getFutureMeetingList(Contact contact) {
        return null;
    }
    
    public List<Meeting> getFutureMeetingList(Calendar date) {
        return null;
    }
    
    public List<PastMeeting> getPastMeetingList(Contact contact) {
        return null;
    }
    
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
        
    }

    public void addMeetingNotes(int id, String text) {
        
    }
    
    public void addNewContact(String name, String notes) {
        
    }
    
    public Set<Contact> getContacts(int... ids) {
        return null;
    }
    
    public Set<Contact> getContacts(String name) {
        return null;
    }
    
    /**
    *
    *   Flush method for wiping the contents of the "Contacts.txt" file
    *   and writing the new data to the file.
    *
    *   If statement tests to see if the file exists and is a File object
    *   links the file to a new PrintWriter
    *
    */
    public void flush() {
        File file = new File("Contacts.txt");
        
        if (file.exists() && file.isFile()) {
            try {
                PrintWriter printWriter = new PrintWriter(file);
                printWriter.print("");
                printWriter.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(ContactImpl.getIDCounter());
            objectOutputStream.writeObject(MeetingImpl.getMeetingIDCounter());
            objectOutputStream.writeObject(contactList);
            objectOutputStream.writeObject(meetingList);
            objectOutputStream.writeObject(pastMeetingList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}