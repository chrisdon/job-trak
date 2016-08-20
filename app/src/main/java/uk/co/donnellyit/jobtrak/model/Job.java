package uk.co.donnellyit.jobtrak.model;

import com.couchbase.lite.Database;
import com.couchbase.lite.Document;

import java.util.Date;
import java.util.Map;

import io.realm.RealmList;
import io.realm.RealmObject;
import uk.co.donnellyit.jobtrak.database.CrudHandler;

public class Job extends RealmObject {
    public final static String TITLE = "title";
    public final static String TYPE = "type";
    public final static String COMPANY = "company";
    public final static String RECRUITER = "recruiter";
    public final static String SALARY = "salary";
    public final static String SALARY_TYPE = "salaryType";
    public final static String EMPLOYMENT_TYPE = "employmentType";
    public final static String COMPANY_ID = "company_id";
    public final static String RECRUITER_ID = "person_id";
    public final static String ID = "id";

    private String id;
    private Date createDate;
	private String title;
    private String type;
    private Company company;
    private String salary;
    private String salaryType;
    private String employmentType;
    private RealmList<Event> eventList;

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

    public RealmList<Event> getEventList() {
        return eventList;
    }

    public void setEventList(RealmList<Event> eventList) {
        this.eventList = eventList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }
}
