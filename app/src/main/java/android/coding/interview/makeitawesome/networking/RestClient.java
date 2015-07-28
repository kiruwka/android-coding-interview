package android.coding.interview.makeitawesome.networking;

import android.coding.interview.makeitawesome.model.Album;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import de.halfbit.tinybus.TinyBus;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by antonio on 24/07/15.
 */
public class RestClient {

    private ApiPhoto api;


    public static RestClient restClient= null;
    private static final String URL = "http://jsonplaceholder.typicode.com/photos/";

    protected RestClient(){

        OkHttpClient okHttpClient = new OkHttpClient();
        File cacheDir = new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString());
        Cache cache = null;
        cache = new Cache(cacheDir, 10 * 1024 * 1024);


        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("content-type", "application/json");
                request.addHeader("accept-encoding", "gzip");  // Here is the problem
            }
        };

        okHttpClient.setCache(cache);
        okHttpClient.setConnectTimeout(60000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(60000, TimeUnit.MILLISECONDS);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL)
                .setClient(new OkClient(okHttpClient))
                .setRequestInterceptor(requestInterceptor)
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

    public void getPhotos(final TinyBus mBus){

        api.listPhotos(new Callback<List<Album>>() {

            @Override
            public void success(List<Album> albums, Response response) {
                mBus.post(new AlbumEvent(albums));
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post(new AlbumEvent(error));
            }
        });
    }

}
