package android.coding.interview.makeitawesome.fragment;

import android.coding.interview.makeitawesome.Picture;
import android.coding.interview.makeitawesome.R;
import android.coding.interview.makeitawesome.adapter.PhotosAdapter;
import android.coding.interview.makeitawesome.data.ImageData;
import android.coding.interview.makeitawesome.utils.DividerItemDecoration;
import android.coding.interview.makeitawesome.utils.JSONResponseHandler;
import android.coding.interview.makeitawesome.utils.QueryResult;
import android.content.Intent;
import android.location.Location;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * This is Your TASK:<br>
 * This is a fragment where you need to show list of pictures and details fetched from API<br>
 * Most of skeleton for showing UI is implemented. You need to take care of getting the data from server, updating adapter and displaying results
 */
public class PicturesFragment extends Fragment implements PhotosAdapter.ClickListener{
    private static final String TAG = "PicturesFragment";
    private final static String URL = "http://jsonplaceholder.typicode.com/photos/";
    private PhotosAdapter mPhotosAdapter;
    protected RecyclerView mRecyclerView;
    private ArrayList<ImageData> mData = new ArrayList<>();

    public static Fragment newInstance() {
        return new PicturesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPhotosAdapter = new PhotosAdapter(mData, getActivity());

        //todo if there's no results show it
        //tv_no_results = (TextView) findViewById(R.id.tv_no_results);

        new HttpGetTask().execute(); //start background task


        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.pictures_list_fragment, container, false);
        setupRecyclerView(mRecyclerView);
        return mRecyclerView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        mRecyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mPhotosAdapter);
        mPhotosAdapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), Picture.class);
        String url = mData.get(position).getUrl();

        intent.putExtra(Picture.IMAGEURL, url);
        startActivity(intent);
    }

    /**
     * download the data
     */
    private class HttpGetTask extends AsyncTask<Void, Void, ArrayList<ImageData>> {
        AndroidHttpClient mClient = AndroidHttpClient.newInstance("");


        @Override
        protected ArrayList<ImageData> doInBackground(Void... unused) {
            Log.v(TAG, "doInBackground");

            HttpGet request = new HttpGet(URL);
            JSONResponseHandler searchresponseHandler = new JSONResponseHandler();

            try {
                return mClient.execute(request, searchresponseHandler);
            } catch (ClientProtocolException exception) {
                exception.printStackTrace();
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            } finally {
                if (mClient != null) mClient.close();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<ImageData> result) {
            Log.v(TAG, "onPostExecute");
            ArrayList<ImageData> searchResults = new ArrayList<>();

            if (mClient != null && result != null) {
                Log.v(TAG, "search complete");
                /*for (Map.Entry<String, ImageData> mLibraryItem : result.getphotos().entrySet()) {
                    searchResults.add(mLibraryItem.getValue());
                }*/
                //for (ImageData data : )
                for (ImageData mData : result){
                    searchResults.add(mData);
                }
            }
            if ((result == null || result.size() == 0)) {
                //todo: if there's no results show no results found
                //tv_no_results.setVisibility(View.VISIBLE);
            } else {
                //tv_no_results.setVisibility(View.GONE);
                int counter = 0;
                for (ImageData imageData: searchResults) {
                    mPhotosAdapter.insertmData(imageData, counter);
                    counter++;
                }
            }
        }
    }
}
