package uk.co.donnellyit.jobtrak.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.couchbase.lite.LiveQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.donnellyit.jobtrak.BaseFragment;
import uk.co.donnellyit.jobtrak.R;
import uk.co.donnellyit.jobtrak.database.StorageManager;
import uk.co.donnellyit.jobtrak.model.Event;
import uk.co.donnellyit.jobtrak.ui.LineDividerItemDecoration;

/**
 * A fragment containing the list of events
 */
public class ListFragment extends BaseFragment implements ListView, View.OnClickListener,
        ListAdapter.ListCLickListener {

    @Inject
    ListPresenter presenter;
    @BindView(R.id.event_list) RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.placeholder) TextView mPlaceholder;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.event_fab) FloatingActionButton mFloatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new LineDividerItemDecoration(getActivity()));

        List<Event> dataset = new ArrayList<>();
        mAdapter = new ListAdapter(dataset, this);
        mRecyclerView.setAdapter(mAdapter);

        mFloatingActionButton.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();

        refreshList();
    }

    public void refreshList() {
        presenter.fetchEventList();
    }

    @Override
    public void displayList(List<Event> eventList) {
        if(eventList.size() > 0) {
            mAdapter.setDataset(eventList);
            mPlaceholder.setVisibility(View.INVISIBLE);
        } else {
            mPlaceholder.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError() {
        mPlaceholder.setVisibility(View.VISIBLE);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ListModule(this));
    }

    private MainActivity getParentActivity() {
        return (MainActivity) getActivity();
    }

    @Override
    public void onClick(View v) {

        getParentActivity().loadEventFragment(null);
    }

    @Override
    public void onItemCLicked(int position) {
        Event event = mAdapter.getDataset().get(position);
        getParentActivity().loadEventFragment(event.getId());
    }


}
