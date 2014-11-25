import java.util.concurrent.atomic.*;

public class ContactImpl implements Contact {

    private static AtomicInteger IDCounter = new AtomicInteger(0);
    
    private final int IDNUM;
    private final String NAME;
    private String notes;
    
    public ContactImpl(String name) {
        this.NAME = name;
        this.IDNUM = getAtomicIDNum();
        this.notes = "";
    }
    
    private int getAtomicIDNum() {
        return IDCounter.getAndIncrement();
    }
    
    /**
    * Returns the ID of the contact.
    *
    * @return the ID of the contact.
    */
    public int getId() {
        return new Integer(IDNUM);
    }
    
    /**
    * Returns the name of the contact.
    *
    * @return the name of the contact.
    */
    public String getName() {
        return new String(NAME);
    }
    
    /**
    * Returns our notes about the contact, if any.
    *
    * If we have not written anything about the contact, the empty
    * string is returned.
    *
    * @return a string with notes about the contact, maybe empty.
    */
    public String getNotes() {
        return "Hello";
    }
    
    /**
    * Add notes about the contact.
    *
    * @param note the notes to be added
    */
    public void addNotes(String note) { 
        
    }
}