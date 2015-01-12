import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
                fileStream.close();
                objectStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
            
    }
    
    /**
    * Add a new meeting to be held in the future.
    *
    * @param contacts a list of contacts that will participate in the meeting
    * @param date the date on which the meeting will take place
    * @return the ID for the meeting
    * @throws IllegalArgumentException if the meeting is set for a time in the past,
    *         of if any contact is unknown / non-existent
    */
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        
        if (date.before(GregorianCalendar.getInstance())) {
            throw new IllegalArgumentException("Meeting is set for a time in the past.");
        }
        
        boolean contactExists = false;
        Iterator<Contact> contactIterator = contacts.iterator();
        while (contactIterator.hasNext()) {
            Contact newContact = contactIterator.next();
            for (Contact c : contactList) {
                if (newContact.equals(c)) {
                    contactExists = true;
                }
            }
        }
        if (contactExists) {
            throw new IllegalArgumentException("Contact is unknown / non-existent.");
        }
        else {
            Meeting newMeeting = new MeetingImpl(date, contacts);
            meetingList.add(newMeeting);
            return newMeeting.getId();
        }
    }

    /**
    * Returns the PAST meeting with the requested ID, or null if it there is none.
    *
    * @param id the ID for the meeting
    * @return the meeting with the requested ID, or null if it there is none.
    * @throws IllegalArgumentException if there is a meeting with that ID happening in the future
    */
    public PastMeeting getPastMeeting(int id) {
        PastMeeting newPastMeeting = null;
        
        for (Meeting m : meetingList) {
            if (m.getId() == id) {
                throw new IllegalArgumentException("The meeting with ID: " + id + " is happening in the future.");
            }
        }
        for (Meeting m : pastMeetingList) {
            if (m.getId() == id) {
                newPastMeeting = (PastMeeting)m;
            }
        }
        return newPastMeeting;
    }
    
     /**
    * Returns the FUTURE meeting with the requested ID, or null if there is none.
    *
    * @param id the ID for the meeting
    * @return the meeting with the requested ID, or null if it there is none.
    * @throws IllegalArgumentException if there is a meeting with that ID happening in the past
    */
    public FutureMeeting getFutureMeeting(int id) {
        FutureMeeting futureMeeting = null;
        
        for (Meeting m : pastMeetingList) {
            if (m.getId() == id) {
                throw new IllegalArgumentException("The meeting with ID: " + id + " is happening in the past.");
            }
        }
        for (Meeting m : meetingList) {
            if (m.getId() == id) {
                futureMeeting = (FutureMeeting)m;
            }
        }
        return futureMeeting;
    }
    
    /**
    * Returns the meeting with the requested ID, or null if it there is none.
    *
    * @param id the ID for the meeting
    * @return the meeting with the requested ID, or null if it there is none.
    */
    public Meeting getMeeting(int id) {
        Meeting newMeeting = null;
        
        for (Meeting m : meetingList) {
            if (m.getId() == id) {
                newMeeting = m;
            }
        }
        for (Meeting m : pastMeetingList) {
            if (m.getId() == id) {
                newMeeting = m;
            }
        }
        return newMeeting;
    }
    
    /**
    * Returns the list of future meetings scheduled with this contact.
    *
    * If there are none, the returned list will be empty. Otherwise,
    * the list will be chronologically sorted and will not contain any
    * duplicates.
    *
    * @param contact one of the user’s contacts
    * @return the list of future meeting(s) scheduled with this contact (maybe empty).
    * @throws IllegalArgumentException if the contact does not exist
    */
    public List<Meeting> getFutureMeetingList(Contact contact) {
        List<Meeting> newMeetingList = new ArrayList<Meeting>();
        
        for (Meeting m : meetingList) {
            if (m.getContacts().contains(contact)) {
                newMeetingList.add(m);
            }
        }
        
        if (newMeetingList.isEmpty()) {
            throw new IllegalArgumentException("The contact does not exist.");
        }
        
        Collections.sort(newMeetingList, new MeetingImpl(Calendar.getInstance(), new HashSet<Contact>()));
        
        return newMeetingList;
    }
    
    /**
    * Returns the list of meetings that are scheduled for, or that took
    * place on, the specified date
    *
    * If there are none, the returned list will be empty. Otherwise,
    * the list will be chronologically sorted and will not contain any
    * duplicates.
    *
    * @param date the date
    * @return the list of meetings
    */
    public List<Meeting> getFutureMeetingList(Calendar date) {
        List<Meeting> newMeetingList = new ArrayList<Meeting>();
        
        if (date.before(Calendar.getInstance())) {
            for (Meeting m : pastMeetingList) {
                if (compareDate(m.getDate(), date)) {
                        newMeetingList.add(m);
                }
            }
        }
        else {
            for (Meeting m : meetingList) {
                if (compareDate(m.getDate(), date)) {
                        newMeetingList.add(m);
                }
            }
        }
        
        Collections.sort(newMeetingList, new MeetingImpl(date, new HashSet<Contact>()));
        
        return newMeetingList;
    }
    
    /**
    *
    *   compareDate method is a re-factoring of the functionality from getFutureMeetingList(Calendar)
    *
    *   compares two Calendar objects to see if they represent Calendars of the same date
    *   extracts the YEAR, MONTH and DAY_OF_WEEK fields to compare only the date value
    *   and not the time value of a Calendar
    *
    *   @param the two Calendar objects to be compared
    *   @return true if the Calendars represent the same date, false if otherwise
    *
    */
    private boolean compareDate(Calendar date1, Calendar date2) {
        boolean sameDate = false;
        
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
            date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) &&
            date1.get(Calendar.DAY_OF_WEEK) == date2.get(Calendar.DAY_OF_WEEK)) {
            sameDate = true;
        }
        
        return sameDate;
    }
    
    /**
    * Returns the list of past meetings in which this contact has participated.
    *
    * If there are none, the returned list will be empty. Otherwise,
    * the list will be chronologically sorted and will not contain any
    * duplicates.
    *
    * @param contact one of the user’s contacts
    * @return the list of future meeting(s) scheduled with this contact (maybe empty).
    * @throws IllegalArgumentException if the contact does not exist
    */
    public List<PastMeeting> getPastMeetingList(Contact contact) {
        List<PastMeeting> newPastMeetingList = new ArrayList<PastMeeting>();
        
        for (Meeting m : pastMeetingList) {
            if (m.getContacts().contains(contact)) {
                newPastMeetingList.add((PastMeeting)m);
            }
        }
        
        if (newPastMeetingList.isEmpty()) {
            throw new IllegalArgumentException("The contact does not exist.");
        }
        
        return newPastMeetingList;
    }
    
    /**
    * Create a new record for a meeting that took place in the past.
    *
    * @param contacts a list of participants
    * @param date the date on which the meeting took place
    * @param text messages to be added about the meeting.
    * @throws IllegalArgumentException if the list of contacts is
    * empty, or any of the contacts does not exist. Or if the date supplied is not in the past
    * @throws NullPointerException if any of the arguments is null
    */
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
        if (contacts == null || date == null || text == null) {
            throw new NullPointerException("One or more of the parameters is null.");
        }
        if (contacts.isEmpty()) { 
            throw new IllegalArgumentException("List of Contacts is empty");
        }
        if (date.after(GregorianCalendar.getInstance())) {
            throw new IllegalArgumentException("Date supplied is not in the past.");
        }
        else {
            PastMeeting pastMeeting = new PastMeetingImpl(date, contacts, text);
            pastMeetingList.add(pastMeeting);
        }
    }

    /**
    * Add notes to a meeting.
    *
    * This method is used when a future meeting takes place, and is
    * then converted to a past meeting (with notes).
    *
    * It can be also used to add notes to a past meeting at a later date.
    *
    * @param id the ID of the meeting
    * @param text messages to be added about the meeting.
    * @throws IllegalArgumentException if the meeting does not exist
    * @throws IllegalStateException if the meeting is set for a date in the future
    * @throws NullPointerException if the notes are null
    */
    public void addMeetingNotes(int id, String text) {
        
        Meeting newMeeting = null;
        ArrayList<Meeting> newMeetingList = new ArrayList<Meeting>();
        for (Meeting m : meetingList) {
            if (m.getId() != id) {
                newMeetingList.add(m);
            }
            else if (m.getId() == id) {
                newMeeting = m;
            }
        }
        if (newMeeting == null) {
            throw new IllegalArgumentException("Meeting does not exist.");
        }
        if (newMeeting.getDate().after(GregorianCalendar.getInstance())) {
            throw new IllegalStateException("Meeting is set for a date in the future.");
        }
        if (text == null) {
            throw new NullPointerException("No notes to be added.");
        }
        else {
            PastMeeting pastMeeting = new PastMeetingImpl(newMeeting.getDate(), newMeeting.getContacts(), text);
            pastMeetingList.add(pastMeeting);
            meetingList = newMeetingList;
        }
    }
    
    /**
    * Create a new contact with the specified name and notes.
    *
    * @param name the name of the contact.
    * @param notes notes to be added about the contact.
    * @throws NullPointerException if the name or the notes are null
    */
    public void addNewContact(String name, String notes) {
        
        if (name == null || notes == null) {
            throw new NullPointerException("Name and notes are Null values");
        }
        else {
            Contact newContact = new ContactImpl(name);
            newContact.addNotes(notes);
            contactList.add(newContact);
        }
        
    }
    
    /**
    * Returns a list containing the contacts that correspond to the IDs.
    *
    * @param ids an arbitrary number of contact IDs
    * @return a list containing the contacts that correspond to the IDs.
    * @throws IllegalArgumentException if any of the IDs does not correspond to a real contact
    */
    public Set<Contact> getContacts(int... ids) {
        Set<Contact> newContactSet = new HashSet<Contact>();
        
        for (int id : ids) {
            for (Contact c : contactList) {
                if (c.getId() == id) {
                    newContactSet.add(c);
                }
                else {
                    throw new IllegalArgumentException("ID does not correspond to a real contact.");
                }
            }
        }
        
        return newContactSet;
    }
    
    /**
    * Returns a list with the contacts whose name contains that string.
    *
    * @param name the string to search for
    * @return a list with the contacts whose name contains that string.
    * @throws NullPointerException if the parameter is null
    */
    public Set<Contact> getContacts(String name) {
        if (name == null) {
            throw new NullPointerException("Name parameter is null.");
        }
        
        Set<Contact> newContactSet = new HashSet<Contact>();
        
        for (Contact c : contactList) {
            if (c.getName().equals(name)) {
                newContactSet.add(c);
            }
        }
        
        
        return newContactSet;
    }
    
    /**
    *
    *   Flush method for wiping the contents of the "Contacts.txt" file
    *   and writing the new data to the file.
    *
    *   File file object is now simply deleted and re-created instead of over-written
    *
    *   Code inside the try block creates a FileOutputStream/ ObjectOutputStream combo
    *   and writes the Contact IDCounter, MeetingIDCounter, contactList, meetingList and pastMeetingList
    *   Objects to the "Contacts.txt" file, in the same order as they are written.
    *
    */
    public void flush() {
        File file = new File("Contacts.txt");
        
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(ContactImpl.getIDCounter());
            objectOutputStream.writeObject(MeetingImpl.getMeetingIDCounter());
            objectOutputStream.writeObject(contactList);
            objectOutputStream.writeObject(meetingList);
            objectOutputStream.writeObject(pastMeetingList);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}