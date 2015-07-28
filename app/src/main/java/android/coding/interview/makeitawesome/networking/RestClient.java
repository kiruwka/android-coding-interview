package android.coding.interview.makeitawesome.networking;

import android.coding.interview.makeitawesome.model.Album;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.http.GET;

/**
 * Created by antonio on 24/07/15.
 */
public class RestClient {

    private ApiPhoto api;


    public static RestClient restClient= null;
    private static final String URL = "http://jsonplaceholder.typicode.com/photos/";

    protected RestClient(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("RETROFIT"))
                .build();
        api = restAdapter.create(ApiPhoto.class);
    }

    public static synchronized RestClient getInstance(){
        if(restClient==null){
            restClient = new RestClient();
        }
        return restClient;
    }

    public List<Album> getPhotos(){
        return api.listPhotos();
    }



}
