import java.util.concurrent.atomic.*;
/**
*
*   A ContactImpl contains information about a Contact
*
*   it stores information about the contact's ID number, name and notes about the contact
*   Static Class variable IDCounter is an AtomicInteger which gives each contact a unique ID.
*   
*/


public class ContactImpl implements Contact {

    public static AtomicInteger IDCounter = new AtomicInteger(0);
    
    private final int IDNUM;
    private final String NAME;
    private String notes;
    
    public ContactImpl(String name) {
        this.NAME = name;
        this.IDNUM = getAtomicIDNum();
        this.notes = "";
    }
    
    /**
    *
    *   getAtomicIDNum() returns the int result of calling getAndIncrement()
    *   on the static IDCounter field.
    *
    */
    private int getAtomicIDNum() {
        return IDCounter.getAndIncrement();
    }
    
    /**
    *   Returns the ID of the contact.
    *
    *   @return the ID of the contact.
    */
    @Override
    public int getId() {
        return new Integer(IDNUM);
    }
    
    /**
    *   Returns the name of the contact.
    *
    *   @return the name of the contact.
    */
    @Override
    public String getName() {
        return new String(NAME);
    }
    
    /**
    *   Returns our notes about the contact, if any.
    *
    *   If we have not written anything about the contact, the empty
    *   string is returned.
    *
    *   @return a string with notes about the contact, maybe empty.
    */
    @Override
    public String getNotes() {
        return new String(notes);
    }
    
    /**
    *
    *   Initialises a new String with a copy of the notes already held in the object
    *   adds the argument note to the end of the copied String
    *   replaces the notes field with noteCopy.
    *
    *   @param note the notes to be added.
    *
    */
    @Override
    public void addNotes(String note) { 
        String noteCopy = new String(notes);
        noteCopy += note;
        notes = noteCopy;
    }
    
    /**
    *
    *   toString() method returns a String with details about the state of the
    *   ContactImpl object.
    *
    *   @return String with the ContactImpl object's NAME, IDNUM and the object's notes.
    *
    */
    @Override
    public String toString() {
        String result = ("Name; " + NAME + " \nID Number: " + IDNUM + 
                        " \nNotes about " + NAME + ": " + notes + "\n");
        return result;
    }
}