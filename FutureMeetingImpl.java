import java.util.Set;
import java.util.Calendar;
import java.io.Serializable;

/**
* A meeting to be held in the future
*/
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
    
    public FutureMeetingImpl(Calendar cal, Set<Contact> contacts) {
        super(cal, contacts);
    }
}