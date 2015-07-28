package android.coding.interview.makeitawesome.fragment;

import android.coding.interview.makeitawesome.R;
import android.coding.interview.makeitawesome.model.Album;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;


public class DetailFragment extends Fragment {

    public static Fragment newInstance(Album album) {
        DetailFragment detail = new DetailFragment();
        detail.setAlbum(album);
        detail.setRetainInstance(true);
        return detail;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    Album album;
    ImageView mImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.picture_detail, container, false);
        mImage = (ImageView) v.findViewById(R.id.item_icon);
        showImage();
        return v;
    }


    public void showImage() {

        Glide.with(getActivity()).load(album.getUrl()).asBitmap().into(new SimpleTarget<Bitmap>(350, 350) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                mImage.setImageBitmap(resource);
            }
        });
    }

}
