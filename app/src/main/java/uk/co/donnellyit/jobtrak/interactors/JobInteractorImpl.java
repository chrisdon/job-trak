package uk.co.donnellyit.jobtrak.interactors;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import uk.co.donnellyit.jobtrak.job.OnJobFetchedListener;
import uk.co.donnellyit.jobtrak.model.Company;
import uk.co.donnellyit.jobtrak.model.Event;
import uk.co.donnellyit.jobtrak.model.Job;
import uk.co.donnellyit.jobtrak.model.Person;

/**
 * Created by chrisdonnelly on 16/06/2016.
 */

public class JobInteractorImpl implements JobInteractor {

    private Context mContext;

    public JobInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public void fetchJob(String id, OnJobFetchedListener listener) {
// Create the Realm configuration
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(mContext).build();
        // Open the Realm for the UI thread.
        Realm realm = Realm.getInstance(realmConfig);
        // TODO convert to Observable
        Job job = realm.where(Job.class).equalTo(Job.ID, id).findFirst();
        listener.onJobFetched(job);

        realm.close();
    }

    @Override
    public void createJob(final OnJobFetchedListener listener) {
// Create the Realm configuration
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(mContext).build();
        // Open the Realm for the UI thread.
        Realm realm = Realm.getInstance(realmConfig);

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Job job = realm.createObject(Job.class);
                RealmList<Event> eventList = new RealmList<>();
                job.setEventList(eventList);
                job.setId(UUID.randomUUID().toString());
                Date createDate = Calendar.getInstance().getTime();
                job.setCreateDate(createDate);
                Company company = realm.createObject(Company.class);
                job.setCompany(company);

                listener.onJobCreated(job);

            }
        });

        realm.close();
    }

    @Override
    public void updateJob(final Map<String, Object> jobMap) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(mContext).build();
        // Open the Realm for the UI thread.
        Realm realm = Realm.getInstance(realmConfig);

        final Job persistedJob = realm.where(Job.class)
                .equalTo(Job.ID, (String) jobMap.get(Job.ID))
                .findFirst();

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                if(jobMap.containsKey(Job.TITLE)) {
                    String title = (String) jobMap.get(Job.TITLE);
                    persistedJob.setTitle(title);
                }

                if(jobMap.containsKey(Company.NAME)) {
                    String companyName = (String) jobMap.get(Company.NAME);
                    persistedJob.getCompany().setName(companyName);
                }

                if(jobMap.containsKey(Job.SALARY)) {
                    String salary = (String) jobMap.get(Job.SALARY);
                    persistedJob.setSalary(salary);
                }

                if(jobMap.containsKey(Job.SALARY_TYPE)) {
                    String salaryType = (String) jobMap.get(Job.SALARY);
                    persistedJob.setSalaryType(salaryType);
                }
            }
        });

        realm.close();
    }
}
