package uk.co.donnellyit.jobtrak.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.couchbase.lite.LiveQuery;

import uk.co.donnellyit.jobtrak.R;
import uk.co.donnellyit.jobtrak.database.StorageManager;
import uk.co.donnellyit.jobtrak.event.EventFragment;

public class MainActivity extends AppCompatActivity {

    private LiveQuery liveQuery;
    private StorageManager mStorageManager;
    private ListFragment mListFragment;
    private boolean mIsDisplayingEvent = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        mListFragment = new ListFragment();
        fragmentTransaction.add(R.id.master_container, mListFragment);
        fragmentTransaction.commit();

        /*mStorageManager = new StorageManager(this);
        try {
            startLiveQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private boolean isTwoPane() {
        return findViewById(R.id.detail_container) != null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadEventFragment(String eventId) {
        // Create new fragment and transaction
        EventFragment newFragment = EventFragment.getInstance(eventId);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if(isTwoPane()) {
            transaction.add(R.id.detail_container, newFragment);
        } else {
            mIsDisplayingEvent = true;
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.master_container, newFragment);
            transaction.addToBackStack(null);

        }

        // Commit the transaction
        transaction.commit();
    }

    private void startLiveQuery() throws Exception {

        //showProgress();

        if (liveQuery == null) {

            liveQuery = mStorageManager.getEventView().createQuery().toLiveQuery();

            liveQuery.addChangeListener(new LiveQuery.ChangeListener() {
                public void changed(final LiveQuery.ChangeEvent event) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Event changed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            liveQuery.start();

        }

    }

    public void refreshList() {
        if(mListFragment != null) {
            mListFragment.refreshList();
        }
    }

    @Override
    public void onBackPressed() {
        if(isTwoPane()) {
            super.onBackPressed();
        } else {
            if(mIsDisplayingEvent) {
                if(mListFragment == null) {
                    mListFragment = new ListFragment();
                }
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.master_container, mListFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            } else {
                super.onBackPressed();
            }
        }
    }

}
