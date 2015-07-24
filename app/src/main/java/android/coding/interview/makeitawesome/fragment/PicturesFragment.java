package android.coding.interview.makeitawesome.fragment;

import android.coding.interview.makeitawesome.R;
import android.coding.interview.makeitawesome.adapter.PhotosAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This is Your TASK:<br>
 * This is a fragment where you need to show list of pictures and details fetched from API<br>
 * Most of skeleton for showing UI is implemented. You need to take care of getting the data from server, updating adapter and displaying results
 */
public class PicturesFragment extends Fragment {

    public static Fragment newInstance() {
        return new PicturesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.pictures_list_fragment, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new PhotosAdapter());
    }
}
