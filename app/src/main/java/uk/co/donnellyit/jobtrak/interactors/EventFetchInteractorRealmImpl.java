package uk.co.donnellyit.jobtrak.interactors;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.donnellyit.jobtrak.event.OnEventFetchedListener;
import uk.co.donnellyit.jobtrak.model.Company;
import uk.co.donnellyit.jobtrak.model.Event;
import uk.co.donnellyit.jobtrak.model.Job;
import uk.co.donnellyit.jobtrak.model.Person;

/**
 * Created by chrisdonnelly on 26/05/2016.
 */

public class EventFetchInteractorRealmImpl implements EventFetchInteractor {

    private Context mContext;

    public EventFetchInteractorRealmImpl(Context context) {
        mContext = context;
    }

    @Override
    public void fetchEvent(String id, OnEventFetchedListener listener) {
        // Create the Realm configuration
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(mContext).build();
        // Open the Realm for the UI thread.
        Realm realm = Realm.getInstance(realmConfig);
        Event event = realm.where(Event.class).equalTo(Event.COLUMN_ID, id).findFirst();
        listener.onEventFetched(event);
    }

    @Override
    public void createEvent(final OnEventFetchedListener listener) {
        // Create the Realm configuration
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(mContext).build();
        // Open the Realm for the UI thread.
        Realm realm = Realm.getInstance(realmConfig);

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                Event event = realm.createObject(Event.class);
                event.setId(UUID.randomUUID().toString());
                Date createDate = Calendar.getInstance().getTime();
                event.setDate(createDate);
                Job job = realm.createObject(Job.class);
                Company company = realm.createObject(Company.class);
                job.setCompany(company);
                event.setJob(job);
                company = realm.createObject(Company.class);
                Person person = realm.createObject(Person.class);
                person.setCompany(company);
                event.setCorrespondant(person);
                listener.onEventFetched(event);

            }
        });

    }

    @Override
    public void updateEvent(final Map<String, Object> eventMap) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(mContext).build();
        // Open the Realm for the UI thread.
        Realm realm = Realm.getInstance(realmConfig);

        final Event persistedEvent = realm.where(Event.class)
                .equalTo(Event.COLUMN_ID, (String) eventMap.get(Event.COLUMN_ID))
                .findFirst();

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add an event
                Date date = (Date) eventMap.get(Event.COLUMN_DATE);
                persistedEvent.setDate(date);
                if(eventMap.containsKey(Event.COLUMN_CONTACT_TYPE)) {
                    String contactType = (String) eventMap.get(Event.COLUMN_CONTACT_TYPE);
                    persistedEvent.setContactType(contactType);
                }

                //persistedEvent.setCorrespondant(event.getCorrespondant());
                if(eventMap.containsKey(Event.COLUMN_EVENTDATE)) {
                    Date eventDate = (Date) eventMap.get(Event.COLUMN_EVENTDATE);
                    persistedEvent.setEventDate(eventDate);
                }

                if(eventMap.containsKey(Event.COLUMN_EVENT_TYPE)) {
                    String eventType = (String) eventMap.get(Event.COLUMN_EVENT_TYPE);
                    persistedEvent.setEventType(eventType);
                }

                if(eventMap.containsKey(Job.TITLE)) {
                    String title = (String) eventMap.get(Job.TITLE);
                    persistedEvent.getJob().setTitle(title);
                }

                if(eventMap.containsKey(Event.COLUMN_NOTES)) {
                    String notes = (String) eventMap.get(Event.COLUMN_NOTES);
                    persistedEvent.setNotes(notes);
                }

                if(eventMap.containsKey(Company.NAME)) {
                    String companyName = (String) eventMap.get(Company.NAME);
                    persistedEvent.getJob().getCompany().setName(companyName);
                }
            }
        });
    }
}
