package uk.co.donnellyit.jobtrak.model;

import com.couchbase.lite.Database;
import com.couchbase.lite.Document;

import java.util.Map;

import io.realm.RealmObject;
import uk.co.donnellyit.jobtrak.database.CrudHandler;

public class Job extends RealmObject {
    public final static String TITLE = "title";
    public final static String TYPE = "type";
    public final static String COMPANY = "company";
    public final static String RECRUITER = "recruiter";
    public final static String SALARY = "salary";
    public final static String EMPLOYMENT_TYPE = "employmentType";
    public final static String COMPANY_ID = "company_id";
    public final static String RECRUITER_ID = "person_id";

	private String title;
    private String type;
    private Company company;
    private Person recruiter;
    private String salary;
    private String employmentType;

    /*
    public Job(Document doc, Database db) {
        super(doc);
        Map<String, Object> properties = doc.getProperties();
        if(properties != null) {
            title = (String) properties.get(TITLE);
            type = (String) properties.get(TYPE);
            salary = (String) properties.get(SALARY);
            employmentType = (String) properties.get(EMPLOYMENT_TYPE);


            String company_id = (String) properties.get(COMPANY_ID);
            Document companyDoc = CrudHandler.read(db, company_id);
            company = new Company(companyDoc);
            String recruiter_id = (String) properties.get(RECRUITER_ID);
            Document recruiterDoc = CrudHandler.read(db, recruiter_id);
            recruiter = new Person(recruiterDoc, db);


        }
    }



    @Override
    public Map<String, Object> buildProperties(Map<String, Object> properties) {
        properties.put(SALARY, salary);
        properties.put(EMPLOYMENT_TYPE, employmentType);
        properties.put(TYPE, type);
        properties.put(TITLE, title);
        properties.put(COMPANY_ID, company.getId());
        properties.put(RECRUITER_ID, recruiter.getId());
        return properties;
    }
    */


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Person getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Person recruiter) {
        this.recruiter = recruiter;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }
}
