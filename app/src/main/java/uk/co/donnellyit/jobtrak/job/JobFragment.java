package uk.co.donnellyit.jobtrak.job;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.donnellyit.jobtrak.BaseFragment;
import uk.co.donnellyit.jobtrak.R;
import uk.co.donnellyit.jobtrak.fragment.DatePickerFragment;
import uk.co.donnellyit.jobtrak.fragment.TimePickerFragment;
import uk.co.donnellyit.jobtrak.main.ListAdapter;
import uk.co.donnellyit.jobtrak.main.MainActivity;
import uk.co.donnellyit.jobtrak.model.Company;
import uk.co.donnellyit.jobtrak.model.Event;
import uk.co.donnellyit.jobtrak.model.Job;
import uk.co.donnellyit.jobtrak.ui.LineDividerItemDecoration;

/**
 * Created by chrisdonnelly on 20/12/15.
 */
public class JobFragment extends BaseFragment implements JobView, EventAdapter.ListCLickListener,
        View.OnClickListener {
    public final static String TAG = "JobFragment";
    private final static String PARAM_JOB_ID = "jobId";

    @BindView(R.id.job_title_edit) EditText mJobTitleET;
    @BindView(R.id.job_company_edit) EditText mCompanyNameET;
    @BindView(R.id.event_list) RecyclerView mEventList;
    @BindView(R.id.job_fab) FloatingActionButton mFloatingActionButton;
    @BindView(R.id.job_salary_spinner) Spinner mSalarySpinner;
    @BindView(R.id.job_salary_edit) EditText mSalaryEdit;
    @BindView(R.id.job_salary) TextView mJobSalary;
    @BindView(R.id.job_title) TextView mJobTitle;
    @BindView(R.id.job_company) TextView mJobCompany;

    @Inject
    JobPresenter mPresenter;
    private String mJobId;
    private Map<String, Object> mJobMap;
    private EventAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean mDisplayEdit;


    public static JobFragment getInstance(String jobId) {
        JobFragment fragment = new JobFragment();
        if(jobId != null) {
            Bundle arguments = new Bundle();
            arguments.putString(PARAM_JOB_ID, jobId);
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null && getArguments().containsKey(PARAM_JOB_ID)) {
            mJobId = getArguments().getString(PARAM_JOB_ID);
            mDisplayEdit = false;
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(mDisplayEdit) {
            inflater.inflate(R.menu.job_edit, menu);
        } else {
            inflater.inflate(R.menu.job_save, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.job_edit:

                break;

            case R.id.job_save:

                break;
        }
        return true;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_job, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mJobMap = new HashMap<>();

        mLayoutManager = new LinearLayoutManager(getActivity());
        mEventList.setLayoutManager(mLayoutManager);
        //mEventList.addItemDecoration(new LineDividerItemDecoration(getActivity()));

        List<Event> dataset = new ArrayList<>();
        mAdapter = new EventAdapter(dataset, this);
        mEventList.setAdapter(mAdapter);

        mFloatingActionButton.setOnClickListener(this);

    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new JobModule(this));
    }

    @Override
    public void onPause() {
        updateEvent();

        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();

        if(mJobId != null) {
            mDisplayEdit = false;
            mPresenter.fetchJob(mJobId);
        } else {
            mDisplayEdit = true;
            mPresenter.createJob();

        }
    }

    private void updateEvent(){

        mJobMap.put(Job.TITLE, mJobTitleET.getText().toString().trim());
        mJobMap.put(Company.NAME, mCompanyNameET.getText().toString().trim());
        mJobMap.put(Job.SALARY, mSalaryEdit.getText().toString().trim());
        mJobMap.put(Job.SALARY_TYPE, mSalarySpinner.getSelectedItem());
        mPresenter.updateJob(mJobMap);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void displayJob(Job job) {
        mJobMap.put(Job.ID, job.getId());
        getParentActivity().refreshList();

        mJobId = job.getId();

        mJobTitleET.setText(job.getTitle());
        mCompanyNameET.setText(job.getCompany().getName());
        if(job.getSalary() != null) {
            mSalaryEdit.setText(job.getSalary());
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.salary_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSalarySpinner.setAdapter(adapter);
        if(job.getSalaryType() != null) {
            String salaryType = job.getSalaryType();
            int index = getSalaryTypeIndex(salaryType);
            mSalarySpinner.setSelection(index);
        }
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getActivity()).build();
        Realm realm = Realm.getInstance(realmConfig);

        List<Event> events = realm.copyFromRealm(job.getEventList());
        mAdapter.setDataset(events);
    }

    @Override
    public void displayJobCreated(Job job) {
        mJobMap.put(Job.ID, job.getId());
        getParentActivity().refreshList();

        mJobId = job.getId();

        mJobTitleET.setText(job.getTitle());
        mCompanyNameET.setText(job.getCompany().getName());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.salary_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSalarySpinner.setAdapter(adapter);


        //List<Event> events = job.getEventList();
        mAdapter.setDataset(job.getEventList());
    }

    @Override
    public void displayList(List<Event> eventList) {

    }

    @Override
    public void onError(String msg) {
        
    }

    private MainActivity getParentActivity() {
        return (MainActivity) getActivity();
    }


    @Override
    public void onClick(View v) {
        getParentActivity().loadEventEntryFragment(mJobId, null);
    }

    @Override
    public void onItemCLicked(int position) {

    }

    private int getSalaryTypeIndex(String type) {
        String[] types = getResources().getStringArray(R.array.salary_type);
        for(int i = 0; i < types.length; i++) {
            if(types[i].equals(type)) {
                return i;
            }
        }
        return 0;
    }
}
