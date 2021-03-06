package uk.co.donnellyit.jobtrak.evententry;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
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
import uk.co.donnellyit.jobtrak.job.JobFragment;
import uk.co.donnellyit.jobtrak.job.JobModule;
import uk.co.donnellyit.jobtrak.job.JobPresenter;
import uk.co.donnellyit.jobtrak.job.JobView;
import uk.co.donnellyit.jobtrak.fragment.DatePickerFragment;
import uk.co.donnellyit.jobtrak.fragment.TimePickerFragment;
import uk.co.donnellyit.jobtrak.main.MainActivity;
import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 15/06/2016.
 */

public class EventEntryFragment extends BaseFragment implements EventEntryView, View.OnClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public final static String TAG = "EventEntryFragment";
    private final static String PARAM_EVENT_ID = "eventId";
    private final static String PARAM_JOB_ID = "jobId";

    @BindView(R.id.event_date)
    TextView mEventDateTV;
    @BindView(R.id.event_time) TextView mEventTimeTV;
    @BindView(R.id.job_notes) TextView mEventNotesTV;
    @Inject
    EventEntryPresenter mPresenter;
    private String mEventId;
    private String mJobId;
    private Calendar mEventTime;
    private Map<String, Object> mEventMap;
    SimpleDateFormat datesdf = new SimpleDateFormat(Event.DATE_FORMAT);
    SimpleDateFormat timesdf = new SimpleDateFormat(Event.TIME_FORMAT);
    TimePicker timeView;
    DatePicker dateView;


    public static EventEntryFragment getInstance(String eventId, String jobId) {
        EventEntryFragment fragment = new EventEntryFragment();
        Bundle arguments = new Bundle();
        if(eventId != null) {
            arguments.putString(PARAM_EVENT_ID, eventId);
            fragment.setArguments(arguments);
        }
        if(jobId != null) {
            arguments.putString(PARAM_JOB_ID, jobId);
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
        if(getArguments() != null && getArguments().containsKey(PARAM_JOB_ID)) {
            mJobId = getArguments().getString(PARAM_JOB_ID);
        }

        mEventTime = Calendar.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_enter_details, container, false);

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
            mPresenter.fetchEvent(mEventId, mJobId);
        } else {
            mPresenter.createEvent(mJobId);
        }





    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new EventEntryModule(this));
    }

    @Override
    public void onPause() {
        updateEvent();

        super.onPause();
    }

    private void updateEvent(){

        mEventMap.put(Event.COLUMN_NOTES, mEventNotesTV.getText().toString().trim());
        mEventMap.put(Event.COLUMN_EVENTDATE, mEventTime.getTime());
        mPresenter.updateEvent(mJobId, mEventMap);
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

        mEventNotesTV.setText(event.getNotes());

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
