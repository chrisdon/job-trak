package uk.co.donnellyit.jobtrak.evententry;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.donnellyit.jobtrak.R;
import uk.co.donnellyit.jobtrak.model.Job;

/**
 * Created by chrisdonnelly on 12/12/15.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    private List<Job> mDataset;
    private ListCLickListener mListListener;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public @BindView(R.id.item_job_title) TextView jobTitle;
        public @BindView(R.id.item_company_name) TextView companyName;
        public @BindView(R.id.item_date) TextView time;
        private ListCLickListener mListener;

        public ViewHolder(View v, ListCLickListener listener) {
            super(v);
            ButterKnife.bind(this, v);
            mListener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemCLicked(getAdapterPosition());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EventListAdapter(List<Job> myDataset, ListCLickListener listener) {
        mDataset = myDataset;
        mListListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v, mListListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Job job = mDataset.get(position);
        if(job.getTitle() != null && !job.getTitle().isEmpty()) {
            holder.jobTitle.setText(job.getTitle());
        } else {
            holder.jobTitle.setVisibility(View.GONE);
        }

        if(job.getCompany().getName() != null &&
                !job.getCompany().getName().isEmpty()) {
            holder.companyName.setText(job.getCompany().getName());
        } else {
            holder.companyName.setVisibility(View.GONE);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setDataset(List<Job> dataset) {
        int newSetSize = dataset.size();
        int existingSetSize = mDataset.size();
        if(newSetSize > existingSetSize) {
            int difference = newSetSize - existingSetSize;
            for(int i = 0; i < existingSetSize; i++) {
                mDataset.set(i, dataset.get(i));
                notifyItemChanged(i);
            }
            for(int j=0; j < difference; j++) {
                mDataset.add(dataset.get(existingSetSize+j));
                notifyItemInserted(existingSetSize+j);
            }
        } else if(newSetSize < existingSetSize) {
            int difference = existingSetSize - newSetSize;
            for(int i = 0; i < newSetSize; i++) {
                mDataset.set(i, dataset.get(i));
                notifyItemChanged(i);
            }
            for(int j=difference-1; j >= 0; j--) {
                mDataset.remove(existingSetSize + j);
                notifyItemRemoved(newSetSize+j);
            }
        } else {
            for(int i = 0; i < newSetSize; i++) {
                mDataset.set(i, dataset.get(i));
                notifyItemChanged(i);
            }
        }
    }

    private String convertToDate(Date date) {
        String dateString = DateFormat.format("MMM dd yyyy HH:mm", date).toString();
        return dateString;
    }

    public List<Job> getDataset() {
        return mDataset;
    }

    public interface ListCLickListener {
        void onItemCLicked(int position);
    }
}
