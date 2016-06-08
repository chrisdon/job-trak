package uk.co.donnellyit.jobtrak.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.couchbase.lite.CouchbaseLiteException;

import java.util.Calendar;

import uk.co.donnellyit.jobtrak.database.CrudHandler;
import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 23/02/16.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = "datePicker";


    private DatePickerDialog.OnDateSetListener mListener;

    private Calendar mCalendar;

    public void setCalendar(Calendar calendar) {
        mCalendar = calendar;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        if(mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }

        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user


        mListener.onDateSet(view, year, month, day);
    }

    public DatePickerDialog.OnDateSetListener getListener() {
        return mListener;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.mListener = listener;
    }
}
