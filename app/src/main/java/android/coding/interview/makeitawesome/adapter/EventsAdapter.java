package android.coding.interview.makeitawesome.adapter;

import android.coding.interview.makeitawesome.R;
import android.coding.interview.makeitawesome.domain.entity.Event;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * adapter keeping data for list of movie events
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    //private static final String TAG = PhotosAdapter.class.getSimpleName();
    private List<Event> mData;
    private Context mContext;

    public EventsAdapter(List<Event> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_list_item, parent, false);
        this.mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.mMovieTitle.setText(mData.get(i).getTitle());
        Picasso.with(mContext)
                .load(mData.get(i).getImages().getEventMediumImagePortrait())
                .fit()
                .centerCrop()
                .into(holder.mMovieCover);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<Event> getData() {
        return mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mMovieTitle;
        final ImageView mMovieCover;
        final View container;

        public ViewHolder(View itemView) {
            super(itemView);
            mMovieTitle = (TextView) itemView.findViewById(R.id.item_movie_title);
            mMovieCover = (ImageView) itemView.findViewById(R.id.item_movie_cover);
            container = itemView;
        }
    }

    public void refreshData(List<Event> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
