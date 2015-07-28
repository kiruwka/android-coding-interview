package android.coding.interview.makeitawesome.adapter;


import android.coding.interview.makeitawesome.R;
import android.coding.interview.makeitawesome.fragment.DetailFragment;
import android.coding.interview.makeitawesome.model.Album;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter keeping data for list of photos
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private List<Album> mData = null;
    android.support.v4.app.FragmentManager fragmentManager;

    public android.support.v4.app.FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(android.support.v4.app.FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public List<Album> getmData() {
        return mData;
    }

    public void setmData(List<Album> mData) {
        this.mData = mData;
    }

    public PhotosAdapter() {
        mData = new ArrayList<Album>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.mText.setText(mData.get(i).getTitle());

        final ImageView img = holder.mImage;
        Glide.with(holder.mImage.getContext()).load(mData.get(i).getThumbnailUrl()).asBitmap().into(new SimpleTarget<Bitmap>(80, 80) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                img.setImageBitmap(resource);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView mText;
        final ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mText = (TextView) itemView.findViewById(R.id.item_title);
            mImage = (ImageView) itemView.findViewById(R.id.item_icon);
        }

        @Override
        public void onClick(final View view) {
            int itemPosition = getPosition();
            fragmentManager.beginTransaction().replace(R.id.content_frame,DetailFragment.newInstance(mData.get(itemPosition)), "PICTURES_FRAGMENT").addToBackStack(null).commit();
        }
    }
}
