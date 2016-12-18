package uk.co.donnellyit.jobtrak.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import uk.co.donnellyit.jobtrak.interactors.JobFetchInteractor;

/**
 * Created by chrisdonnelly on 18/09/2016.
 */

public class ListPresenterTest {
    @Mock
    JobFetchInteractor mJobFetchInteractor;

    @Mock
    ListView mListView;

    private ListPresenter mListPresenter;

    @Before
    public void setupListPresenter() {
        MockitoAnnotations.initMocks(this);

        mListPresenter = new ListPresenterImpl(mListView, mJobFetchInteractor);
    }

    @Test
    public void fetchJobList() {
        mListPresenter.fetchJobList();

        verify(mListView).hideProgress();
        verify(mListView).displayList(any(List.class));
    }

}
