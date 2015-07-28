package android.coding.interview.makeitawesome.fragment;

import android.app.ProgressDialog;
import android.coding.interview.makeitawesome.R;
import android.coding.interview.makeitawesome.adapter.PhotosAdapter;
import android.coding.interview.makeitawesome.model.Album;
import android.coding.interview.makeitawesome.networking.AlbumEvent;
import android.coding.interview.makeitawesome.networking.RestClient;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.halfbit.tinybus.Subscribe;
import de.halfbit.tinybus.TinyBus;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * This is Your TASK:<br>
 * This is a fragment where you need to show list of pictures and details fetched from API<br>
 * Most of skeleton for showing UI is implemented. You need to take care of getting the data from server, updating adapter and displaying results
 */
public class PicturesFragment extends Fragment {

    public static Fragment newInstance() {
        PicturesFragment pictures = new PicturesFragment();
        pictures.setRetainInstance(true);
        return pictures;
    }

    RecyclerView rv;
    ProgressDialog mProgressDialog;
    List<Album> mData;
    boolean loading = false;
    private TinyBus mBus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rv = (RecyclerView) inflater.inflate(R.layout.pictures_list_fragment, container, false);

        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();

        mProgressDialog = new ProgressDialog(container.getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        mBus = TinyBus.from(getActivity());

        if (mData == null) {
            if (!loading) {
                RestClient restClient = RestClient.getInstance();
                restClient.getPhotos(mBus);
                loading = true;
            }
        } else {
            drawList();
        }
        return rv;
    }

    @Override
    public void onResume() {
        super.onResume();
        mBus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mBus.unregister(this);
    }

    @Subscribe
    public void onAlbumEvent(AlbumEvent apiEvent) {

        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        if (apiEvent.getError() != null) {
            Crouton.makeText(getActivity(), apiEvent.getError().getMessage(), Style.ALERT).show();
        } else {
            mData = apiEvent.getObject();
            drawList();
        }
        loading = false;
    }

    public void drawList() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        PhotosAdapter photosAdapter = new PhotosAdapter();
        photosAdapter.setmData(mData);
        photosAdapter.setFragmentManager(getActivity().getSupportFragmentManager());
        rv.setAdapter(photosAdapter);
    }

}
