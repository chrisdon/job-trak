package uk.co.donnellyit.jobtrak;

import android.app.Fragment;
import android.os.Bundle;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by chrisdonnelly on 10/12/15.
 */
public abstract class BaseFragment extends Fragment {

    private ObjectGraph activityGraph;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGraph = ((JobApplication) getActivity().getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    protected abstract List<Object> getModules();
}
