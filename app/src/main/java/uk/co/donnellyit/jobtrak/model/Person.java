package uk.co.donnellyit.jobtrak.model;

import com.couchbase.lite.Database;
import com.couchbase.lite.Document;

import java.util.Map;

import io.realm.RealmObject;
import uk.co.donnellyit.jobtrak.database.CrudHandler;

public class Person extends RealmObject {
	public final static String TYPE_RECRUITER = "TypeRecruiter";
	public final static String TYPE_COMPANY_CONTACT = "TypeContact";
    public final static String LASTNAME = "lastName";
    public final static String FIRSTNAME = "firstName";
    public final static String PHONENO = "phoneNo";
    public final static String EMAIL = "email";
    public final static String COMPANY = "company";
    public final static String COMPANY_ID = "company_id";
    public final static String TYPE = "type";

	private String firstName;
    private String lastName;
	private String phoneNo;
	private String email;
	private Company company;
    private String type;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*
    public Person(Document doc, Database db){
        super(doc);
        Map<String, Object> properties = doc.getProperties();
        if(properties != null) {
            String company_id = (String) properties.get(COMPANY_ID);
            Document companyDoc = CrudHandler.read(db, company_id);
            company = new Company(companyDoc);


            firstName = (String) properties.get(FIRSTNAME);
            lastName = (String) properties.get(LASTNAME);
            phoneNo = (String) properties.get(PHONENO);
            email = (String) properties.get(EMAIL);
            type = (String) properties.get(TYPE);
        }
    }

    @Override
    public Map<String, Object> buildProperties(Map<String, Object> properties) {
        properties.put(COMPANY_ID, company.getId());
        properties.put(FIRSTNAME, firstName);
        properties.put(LASTNAME, lastName);
        properties.put(PHONENO, phoneNo);
        properties.put(EMAIL, email);
        properties.put(TYPE, type);
        return properties;
    }
    */



}
