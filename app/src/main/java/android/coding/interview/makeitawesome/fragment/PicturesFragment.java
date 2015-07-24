package android.coding.interview.makeitawesome.fragment;

import android.app.Activity;
import android.coding.interview.makeitawesome.R;
import android.coding.interview.makeitawesome.adapter.PhotosAdapter;
import android.coding.interview.makeitawesome.model.Album;
import android.coding.interview.makeitawesome.networking.RestClient;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

/**
 * This is Your TASK:<br>
 * This is a fragment where you need to show list of pictures and details fetched from API<br>
 * Most of skeleton for showing UI is implemented. You need to take care of getting the data from server, updating adapter and displaying results
 */
public class PicturesFragment extends Fragment {

    ListView rv;
    public static Fragment newInstance() {
        return new PicturesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.album_list_fragment, container, false);
        setupRecyclerView(rv);
        */
        rv = (ListView) inflater.inflate(R.layout.album_list_fragment, container, false);
        DownloadFilesTask d = new DownloadFilesTask();
        d.execute();
        return rv;
    }

    private void drawList(){

    }
    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new PhotosAdapter());
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, List<Album>> {
        protected List<Album> doInBackground(URL... urls) {
            RestClient restClient = RestClient.getInstance();
            restClient.getPhotos();
            List<Album> l = restClient.getPhotos();
            return l;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(List<Album> l) {
            // update the adapter
            rv.setAdapter(new AlbumAdapter(getActivity(),l));

        }
    }

    class AlbumAdapter extends ArrayAdapter<Album> {

        Activity activity;
        List<Album> data;

        AlbumAdapter(Activity activity, List<Album> data) {
            super(activity.getApplicationContext(), R.layout.album_list_item, data);
            this.activity = activity;
            this.data = data;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(activity);

            View item = inflater.inflate(R.layout.album_list_item, null);

            TextView txt = (TextView) item.findViewById(R.id.name);
            txt.setText(data.get(position).getTitle());

            final ImageView img = (ImageView) item.findViewById(R.id.album);
            Glide.with(getActivity()).load(data.get(position).getThumbnailUrl()).asBitmap().into(new SimpleTarget<Bitmap>(100, 100) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                    img.setImageBitmap(resource);
                }
            });
            return (item);
        }
    }
}
