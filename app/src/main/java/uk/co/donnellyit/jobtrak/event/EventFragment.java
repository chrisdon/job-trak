package uk.co.donnellyit.jobtrak.event;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.donnellyit.jobtrak.BaseFragment;
import uk.co.donnellyit.jobtrak.R;
import uk.co.donnellyit.jobtrak.fragment.DatePickerFragment;
import uk.co.donnellyit.jobtrak.fragment.TimePickerFragment;
import uk.co.donnellyit.jobtrak.main.MainActivity;
import uk.co.donnellyit.jobtrak.model.Company;
import uk.co.donnellyit.jobtrak.model.Event;
import uk.co.donnellyit.jobtrak.model.Job;

/**
 * Created by chrisdonnelly on 20/12/15.
 */
public class EventFragment extends BaseFragment implements EventView, View.OnClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private final static String PARAM_EVENT_ID = "eventId";

    @BindView(R.id.job_title_edit) EditText mJobTitleET;
    @BindView(R.id.job_company_edit) EditText mCompanyNameET;
    @BindView(R.id.event_date) TextView mEventDateTV;
    @BindView(R.id.event_time) TextView mEventTimeTV;
    @BindView(R.id.job_notes) TextView mEventNotesTV;
    @Inject
    EventPresenter mPresenter;
    private String mEventId;
    private Calendar mEventTime;
    private Map<String, Object> mEventMap;
    SimpleDateFormat datesdf = new SimpleDateFormat(Event.DATE_FORMAT);
    SimpleDateFormat timesdf = new SimpleDateFormat(Event.TIME_FORMAT);
    TimePicker timeView;
    DatePicker dateView;


    public static EventFragment getInstance(String eventId) {
        EventFragment fragment = new EventFragment();
        if(eventId != null) {
            Bundle arguments = new Bundle();
            arguments.putString(PARAM_EVENT_ID, eventId);
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null && getArguments().containsKey(PARAM_EVENT_ID)) {
            mEventId = getArguments().getString(PARAM_EVENT_ID);
        }

        mEventTime = Calendar.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_event, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mEventMap = new HashMap<>();

        mEventDateTV.setOnClickListener(this);
        mEventTimeTV.setOnClickListener(this);

        if(mEventId != null) {
            mPresenter.fetchEvent(mEventId);
        } else {
            mPresenter.createEvent();
        }





    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new EventModule(this));
    }

    @Override
    public void onPause() {
        updateEvent();

        super.onPause();
    }

    private void updateEvent(){

        mEventMap.put(Job.TITLE, mJobTitleET.getText().toString().trim());
        mEventMap.put(Company.NAME, mCompanyNameET.getText().toString().trim());
        mEventMap.put(Event.COLUMN_NOTES, mEventNotesTV.getText().toString().trim());
        mEventMap.put(Event.COLUMN_EVENTDATE, mEventTime.getTime());
        mPresenter.updateEvent(mEventMap);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void displayEvent(Event event) {
        mEventMap.put(Event.COLUMN_ID, event.getId());
        getParentActivity().refreshList();

        mJobTitleET.setText(event.getJob().getTitle());
        mEventNotesTV.setText(event.getNotes());
        mCompanyNameET.setText(event.getJob().getCompany().getName());

        Calendar cal = Calendar.getInstance();
        if(event.getEventDate() == null) {
            event.setEventDate(cal.getTime());
        }
        mEventDateTV.setText(datesdf.format(event.getEventDate()));
        mEventTimeTV.setText(timesdf.format(event.getEventDate()));

    }

    @Override
    public void onError(String msg) {
        
    }

    private MainActivity getParentActivity() {
        return (MainActivity) getActivity();
    }

    public void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setListener(this);
        newFragment.setCalendar(mEventTime);
        newFragment.show(getFragmentManager(), DatePickerFragment.TAG);
    }

    public void showTimePickerDialog() {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setListener(this);
        newFragment.setCalendar(mEventTime);
        newFragment.show(getFragmentManager(), TimePickerFragment.TAG);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.event_date:
                showDatePickerDialog();
                break;
            case R.id.event_time:
                showTimePickerDialog();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        mEventTime.set(year, monthOfYear, dayOfMonth);
        mEventDateTV.setText(datesdf.format(mEventTime.getTimeInMillis()));
        dateView = view;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        mEventTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mEventTime.set(Calendar.MINUTE, minute);
        mEventTimeTV.setText(timesdf.format(mEventTime.getTimeInMillis()));
        timeView = view;

    }


}
