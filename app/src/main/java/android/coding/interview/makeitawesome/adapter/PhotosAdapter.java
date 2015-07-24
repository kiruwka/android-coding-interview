package android.coding.interview.makeitawesome.adapter;

import android.coding.interview.makeitawesome.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * adapter keeping data for list of photos
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    
    // TODO this is a dummy data that you have to replace. You probably need List of some objects representing pictures
    // rather than just strings
    private List<String> mData = Arrays.asList("dummy 1", "dummy 2", "dummy 3", "dummy 4", "etc");

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.mText.setText(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mText;
        public ViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView;
        }
    }
}
