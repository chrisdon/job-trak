package uk.co.donnellyit.jobtrak.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.couchbase.lite.CouchbaseLiteException;

import java.util.Calendar;

import uk.co.donnellyit.jobtrak.database.CrudHandler;
import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 23/02/16.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public final static String TAG = "timePicker";



    private TimePickerDialog.OnTimeSetListener mListener;

    private Calendar mCalendar;

    public void setCalendar(Calendar calendar) {
        mCalendar = calendar;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        if(mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }

        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user

        mListener.onTimeSet(view, hourOfDay, minute);
    }

    public TimePickerDialog.OnTimeSetListener getListener() {
        return mListener;
    }

    public void setListener(TimePickerDialog.OnTimeSetListener listener) {
        this.mListener = listener;
    }
}
