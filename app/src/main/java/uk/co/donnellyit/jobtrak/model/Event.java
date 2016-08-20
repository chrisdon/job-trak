package uk.co.donnellyit.jobtrak.model;

import com.couchbase.lite.Document;

import java.util.Date;
import java.util.Map;

import io.realm.RealmObject;


public class Event extends RealmObject implements Comparable<Event> {
	public static final String TABLE_EVENT = "event";
	public static final String COLUMN_EVENTDATE = "eventDate";
	public static final String COLUMN_JOBTITLE = "jobTitle";
	public static final String COLUMN_COMPANYNAME = "companyName";
	public static final String COLUMN_COMPANYADDRESS = "companyAddress";
	public static final String COLUMN_COMPANYCONTACTNAME = "companyContactName";
	public static final String COLUMN_COMPANYCONTACTPHONE = "companyContactPhoneNo";
	public static final String COLUMN_COMPANYCONTACTEMAIL = "companyContactEmail";
	public static final String COLUMN_CORRESPONDANT_NAME = "correspondantName";
	public static final String COLUMN_CORRESPONDANT_COMPANY = "correspondantCompany";
	public static final String COLUMN_CORRESPONDANT_PHONE = "correspondantPhoneNo";
	public static final String COLUMN_CORRESPONDANT_EMAIL = "correspondantEmail";
	public final static String COLUMN_ID = "id";
	public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_JOB = "job";
    public static final String COLUMN_DATE = "date";
	public static final String COLUMN_CONTACT_TYPE = "contactType";
	public static final String COLUMN_EVENT_TYPE = "eventType";
    public static final String COLUMN_JOB_ID = "job_id";
	public static final String EVENT_TYPE_CONTACT = "conversation";
	public static final String EVENT_TYPE_INTERVIEW = "interview";
    public static final String DB_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSZ";
    public static final String DATE_FORMAT_YEAR = "dd MMM yyyy";
    public static final String DATE_FORMAT = "dd MMM";
    public static final String TIME_FORMAT = "HH:mm";

	public static final String TYPE_EMAIL = "email";
	public static final String TYPE_PHONE = "phone";
	public static final String TYPE_LINKEDIN = "linkedIn";
	public static final String TYPE_SMS = "sms";
	public static final String TYPE_OTHER = "other";
	public static final String TYPE_FACE2FACE = "face2face";
	public static final int SPINNER_POS_PHONE = 0;
	public static final int SPINNER_POS_EMAIL = 1;
	public static final int SPINNER_POS_LINKEDIN = 2;
	public static final int SPINNER_POS_SMS = 3;
	public static final int SPINNER_POS_FACE2FACE = 4;
	public static final int SPINNER_POS_OTHER = 5;

    private String id;
    private String eventType;
    private String contactType;
    private Date date;
    private String notes;
    private Date eventDate;
    private Person correspondant;

    private String outcome;

    public Event(){}


    public Event(Document document) {
 /*       super(document);
        Map<String, Object> properties = document.getProperties();
        eventType = properties.get(COLUMN_EVENT_TYPE) != null ? (String) properties.get(COLUMN_EVENT_TYPE) : null;
        contactType = properties.get(COLUMN_CONTACT_TYPE) != null ? (String) properties.get(COLUMN_CONTACT_TYPE) : null;
        date = properties.get(COLUMN_DATE) != null ? (Long) properties.get(COLUMN_DATE) : 0;
        notes = properties.get(COLUMN_NOTES) != null ? (String) properties.get(COLUMN_NOTES) : null;
        eventDate = properties.get(COLUMN_EVENTDATE) != null ? (Long) properties.get(COLUMN_EVENTDATE) : 0;
        jobTitle = (String) properties.get(COLUMN_JOBTITLE);
        companyName = (String) properties.get(COLUMN_COMPANYNAME);
        companyAddress = (String) properties.get(COLUMN_COMPANYADDRESS);
        companyContactName = (String) properties.get(COLUMN_COMPANYCONTACTNAME);
        companyContactEmail = (String) properties.get(COLUMN_COMPANYCONTACTEMAIL);
        companyContactPhone = (String) properties.get(COLUMN_COMPANYCONTACTPHONE);
        correspondantName = (String) properties.get(COLUMN_CORRESPONDANT_NAME);
        correspondantCompany = (String) properties.get(COLUMN_CORRESPONDANT_COMPANY);
        correspondantPhone = (String) properties.get(COLUMN_CORRESPONDANT_PHONE);
        correspondantEmail = (String) properties.get(COLUMN_CORRESPONDANT_EMAIL);
        */
    }

   // @Override
    public Map<String, Object> buildProperties(Map<String, Object> properties) {
        properties.put(COLUMN_EVENT_TYPE, eventType);
        properties.put(COLUMN_CONTACT_TYPE, contactType);
        properties.put(COLUMN_DATE, date);
        properties.put(COLUMN_NOTES, notes);
        properties.put(COLUMN_EVENTDATE, eventDate);
        /*
        properties.put(COLUMN_JOBTITLE, jobTitle);
        properties.put(COLUMN_COMPANYNAME, companyName);
        properties.put(COLUMN_COMPANYADDRESS, companyAddress);
        properties.put(COLUMN_COMPANYCONTACTNAME, companyContactName);
        properties.put(COLUMN_COMPANYCONTACTPHONE, companyContactPhone);
        properties.put(COLUMN_COMPANYCONTACTEMAIL, companyContactEmail);
        properties.put(COLUMN_CORRESPONDANT_NAME, correspondantName);
        properties.put(COLUMN_CORRESPONDANT_PHONE, correspondantPhone);
        properties.put(COLUMN_CORRESPONDANT_EMAIL, correspondantEmail);
        properties.put(COLUMN_CORRESPONDANT_COMPANY, correspondantCompany);
        */
        return properties;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Person getCorrespondant() {
        return correspondant;
    }

    public void setCorrespondant(Person correspondant) {
        this.correspondant = correspondant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (eventType != null ? !eventType.equals(event.eventType) : event.eventType != null)
            return false;
        if (contactType != null ? !contactType.equals(event.contactType) : event.contactType != null)
            return false;
        if (date != null ? !date.equals(event.date) : event.date != null) return false;
        if (notes != null ? !notes.equals(event.notes) : event.notes != null) return false;
        if (eventDate != null ? !eventDate.equals(event.eventDate) : event.eventDate != null)
            return false;
        return correspondant != null ? correspondant.equals(event.correspondant) : event.correspondant == null;

    }

    @Override
    public int hashCode() {
        int result = eventType != null ? eventType.hashCode() : 0;
        result = 31 * result + (contactType != null ? contactType.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (eventDate != null ? eventDate.hashCode() : 0);
        result = 31 * result + (correspondant != null ? correspondant.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Event aThat) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        //this optimization is usually worthwhile, and can
        //always be added
        if (this == aThat) return EQUAL;

        return this.date.compareTo(aThat.date);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
