package android.coding.interview.makeitawesome.adapter;

import android.coding.interview.makeitawesome.R;
import android.coding.interview.makeitawesome.data.ImageData;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * adapter keeping data for list of photos
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    // TODO this is a dummy data that you have to replace. You probably need List of some objects representing pictures
    // rather than just strings
    //private List<String> mData = Arrays.asList("dummy 1", "dummy 2", "dummy 3", "dummy 4", "etc");
    private List<ImageData> mData;
    private Context context;
    private ClickListener clickListener;

    public PhotosAdapter(ArrayList<ImageData> mData, Context context){
        this.mData = mData;
        this.context = context;
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public void insertmData(ImageData mImage, int position){
        mData.add(position, mImage);
        notifyItemInserted(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        //holder.mText.setText(mData.get(i));
        holder.getImageTitle().setText(mData.get(i).getTitle());
        //holder.getTv_name_search().setImageResource(R.drawable.abc_ab_share_pack_mtrl_alpha);
        Glide.with(context).load(mData.get(i).getThumbnailUrl()).into(holder.getIv_image());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mTitle;
        private final ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_image_title);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_image);
            itemView.setOnClickListener(this);
        }

        public TextView getImageTitle() {
            return mTitle;
        }

        public ImageView getIv_image() {
            return mImageView;
        }

        @Override
        public void onClick(View v) {
            if (clickListener!= null){
                clickListener.itemClicked(v, getAdapterPosition());
            }
        }
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }
}
